package com.example.informationsecurity.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import com.example.informationsecurity.R
import com.example.informationsecurity.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getMenuId(): Int = R.menu.menu_main

    override fun initView() {
        buttonEnterHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        buttonEnterNewHome.setOnClickListener {
            startActivity(Intent(this, DetailNewVersionActivity::class.java))
        }
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.blog) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.blog_url))))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}