package com.example.informationsecurity.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.informationsecurity.base.BaseDetailActivity
import com.example.informationsecurity.service.BaseEncodeDecodeService
import com.example.informationsecurity.service.impl.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailNewVersionActivity : BaseDetailActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var serviceArray: Array<BaseEncodeDecodeService>
    private var position = 0

    /**
     * 初始化界面
     */
    override fun initView() {
        constraintLayoutHeader?.visibility = View.VISIBLE
        spinner.onItemSelectedListener = this
        super.initView()
    }

    /**
     * 初始化数据
     */
    override fun initData(savedInstanceState: Bundle?) {
        serviceArray = arrayOf(
            AESServiceImpl(),
            DESServiceImpl(),
            RC4ServiceImpl(),
            MD5ServiceImpl(),
            KaiSaServiceImpl(),
            RSAServiceImpl(),
            Base64ServiceImpl(),
            SHA1ServiceImpl(),
            XORServiceImpl(),
        )
        position =
            savedInstanceState?.getInt(KEY_FIRST_ITEM, 0) ?: intent.getIntExtra(KEY_FIRST_ITEM, 0)
        spinner.setSelection(position)
        super.initData(savedInstanceState)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        baseEncodeDecodeService = serviceArray[position]
        updateKeyEditText()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //ignore
    }

    companion object {
        const val KEY_FIRST_ITEM = "firstItem"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_FIRST_ITEM, position)
        super.onSaveInstanceState(outState)
    }

}