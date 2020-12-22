package com.example.informationsecurity.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.informationsecurity.HomeRecyclerViewAdapter
import com.example.informationsecurity.R
import com.example.informationsecurity.activity.encodedecode.*
import com.example.informationsecurity.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

/**
 *
 *
 * @author qq1962247851
 * @date 2020/12/1 9:53
 **/
class HomeActivity : BaseActivity(), HomeRecyclerViewAdapter.OnButtonClick {
    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getMenuId(): Int = 0

    override fun initView() {
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter =
            HomeRecyclerViewAdapter(
                resources.getStringArray(R.array.spinnerEntries),
                resources.getStringArray(R.array.infoEntries),
                this
            )
    }

    override fun initData(savedInstanceState: Bundle?) {
        supportActionBar?.title = "请选择一个加解密算法"
    }

    override fun onClick(position: Int) {
        startActivity(Intent(this, DetailNewVersionActivity::class.java).apply {
            putExtra(DetailNewVersionActivity.KEY_FIRST_ITEM, position)
        })
//        when (position) {
//            0 -> {
//                startActivity(Intent(this, AESDetailActivity::class.java))
//            }
//            1 -> {
//                startActivity(Intent(this, DESDetailActivity::class.java))
//            }
//            2 -> {
//                startActivity(Intent(this, RC4DetailActivity::class.java))
//            }
//            3 -> {
//                startActivity(Intent(this, MD5DetailActivity::class.java))
//            }
//            4 -> {
//                startActivity(Intent(this, KaiSaDetailActivity::class.java))
//            }
//            5 -> {
//                startActivity(Intent(this, RSADetailActivity::class.java))
//            }
//            6 -> {
//                startActivity(Intent(this, Base64DetailActivity::class.java))
//            }
//            7 -> {
//                startActivity(Intent(this, SHA1DetailActivity::class.java))
//            }
//            8 -> {
//                startActivity(Intent(this, XORDetailActivity::class.java))
//            }
//            else -> {
//                //ignore
//            }
//        }
    }

}