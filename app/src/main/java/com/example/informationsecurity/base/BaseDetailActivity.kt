package com.example.informationsecurity.base

import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.informationsecurity.MyApplication
import com.example.informationsecurity.R
import com.example.informationsecurity.exception.DecodeFailedException
import com.example.informationsecurity.exception.EncodeFailedException
import com.example.informationsecurity.exception.KeyNotValidException
import com.example.informationsecurity.service.BaseEncodeDecodeService
import com.example.informationsecurity.util.EncryptUtil
import com.example.informationsecurity.util.RSAUtil
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * 加解密详情界面
 *
 * @author qq1962247851
 * @date 2020/12/18 9:09
 **/
abstract class BaseDetailActivity : BaseActivity, View.OnClickListener {

    var baseEncodeDecodeService: BaseEncodeDecodeService? = null

    constructor()
    constructor(baseEncodeDecodeService: BaseEncodeDecodeService) {
        this.baseEncodeDecodeService = baseEncodeDecodeService
    }

    override fun getMenuId(): Int = R.menu.menu_detail

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun initView() {
        buttonEncode.setOnClickListener(this)
        buttonDecode.setOnClickListener(this)
        buttonCopyDecodedString.setOnClickListener(this)
        buttonPasteDecodedString.setOnClickListener(this)
        buttonClearDecodedString.setOnClickListener(this)
        buttonCopyEncodedString.setOnClickListener(this)
        buttonPasteEncodedString.setOnClickListener(this)
        buttonClearEncodedString.setOnClickListener(this)
        updateKeyEditText()
    }

    fun updateKeyEditText() {
        (baseEncodeDecodeService == null || baseEncodeDecodeService?.getIsNeedKey() ?: true).also {
            editTextKey.isEnabled = it
            textInputLayoutKey.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
            if (!it) {
                Toast.makeText(
                    this, "${baseEncodeDecodeService?.getName() ?: ""}不需要密钥", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        if (baseEncodeDecodeService != null) {
            supportActionBar?.title = baseEncodeDecodeService!!.getName()
        }
    }

    private fun encode(key: String?, string: String) {
        try {
            val encodeString = baseEncodeDecodeService!!.encode(key, string)
            editTextEncoded.setText(encodeString)
        } catch (e: KeyNotValidException) {
            e.printStackTrace()
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        } catch (e: EncodeFailedException) {
            e.printStackTrace()
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            AlertDialog.Builder(this)
                .setTitle("加密出错")
                .setPositiveButton("确定", null)
                .setMessage(e.message)
                .show()
        }
    }

    private fun decode(key: String?, string: String) {
        try {
            val decodeString = baseEncodeDecodeService!!.decode(key, string)
            editTextDecoded.setText(decodeString)
        } catch (e: KeyNotValidException) {
            e.printStackTrace()
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        } catch (e: DecodeFailedException) {
            e.printStackTrace()
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            AlertDialog.Builder(this)
                .setTitle("解密出错")
                .setPositiveButton("确定", null)
                .setMessage(e.message)
                .show()
        }
    }

    override fun onClick(v: View?) {
        val key = editTextKey.text
        when (v?.id) {
            R.id.buttonEncode -> {
                if (!checkKeyValid(key)) return
                val decodedString = editTextDecoded.text
                if (decodedString.isNullOrBlank()) {
                    Toast.makeText(this, "明文不能为空或空格", Toast.LENGTH_SHORT).show()
                    return
                }
                encode(key.toString(), decodedString.toString())
            }
            R.id.buttonDecode -> {
                if (!checkKeyValid(key)) return
                val encodedString = editTextEncoded.text
                if (encodedString.isNullOrBlank()) {
                    Toast.makeText(this, "密文不能为空或空格", Toast.LENGTH_SHORT).show()
                    return
                }
                decode(key.toString(), encodedString.toString())
            }
            R.id.buttonCopyDecodedString -> {
                MyApplication.instance.setClipboard(editTextDecoded.text.toString())
            }
            R.id.buttonPasteDecodedString -> {
                editTextDecoded.setText(MyApplication.instance.getClipboard())
            }
            R.id.buttonClearDecodedString -> {
                editTextDecoded.text = null
            }
            R.id.buttonCopyEncodedString -> {
                MyApplication.instance.setClipboard(editTextEncoded.text.toString())
            }
            R.id.buttonPasteEncodedString -> {
                editTextEncoded.setText(MyApplication.instance.getClipboard())
            }
            R.id.buttonClearEncodedString -> {
                editTextEncoded.text = null
            }
        }
    }

    private fun checkKeyValid(key: Editable?): Boolean {
        if (baseEncodeDecodeService!!.getIsNeedKey() == true && key.isNullOrBlank()) {
            Toast.makeText(this, "密钥不能为空或空格", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.copySixTeenBitKey -> {
                editTextKey.setText(EncryptUtil.getInstance().generateAESKey())
                true
            }
            R.id.copyEightBitKey -> {
                editTextKey.setText(EncryptUtil.getInstance().generateDESKey())
                true
            }
            R.id.copyRsaPublicKey -> {
                editTextKey.setText(RSAUtil.getPublicKey())
                true
            }
            R.id.copyRsaPrivateKey -> {
                editTextKey.setText(RSAUtil.getPrivateKey())
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

}