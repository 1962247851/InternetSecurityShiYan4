package com.example.informationsecurity

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast

/**
 * Application
 *
 * @author qq1962247851
 * @date 2020/12/20 17:07
 **/
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun setClipboard(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText(text, text))
        Toast.makeText(this, "已复制到剪切板", Toast.LENGTH_SHORT).show()
    }

    fun getClipboard(): String {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val primaryClip = clipboard.primaryClip
        if (primaryClip != null && primaryClip.itemCount > 0) {
            return primaryClip.getItemAt(0).coerceToText(this).toString()
        }
        return ""
    }

    companion object {
        lateinit var instance: MyApplication
    }

}