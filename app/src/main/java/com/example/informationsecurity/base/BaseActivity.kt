package com.example.informationsecurity.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.informationsecurity.R

/**
 * BaseActivity
 *
 * @author qq1962247851
 * @date 2020/12/1 9:45
 **/
abstract class BaseActivity : AppCompatActivity() {

    /**
     * 获取布局文件id
     */
    abstract fun getLayoutId(): Int

    /**
     * 获取右上角溢出菜单文件id
     */
    abstract fun getMenuId(): Int

    /**
     * 初始化界面
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initData(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            if (getLayoutId() != R.layout.activity_main) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            }
        }
        initView()
        initData(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (getMenuId() > 0) {
            menuInflater.inflate(getMenuId(), menu)
            return true
        }
        return super.onCreateOptionsMenu(menu)
    }

}