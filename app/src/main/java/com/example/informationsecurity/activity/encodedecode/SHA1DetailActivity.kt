package com.example.informationsecurity.activity.encodedecode

import com.example.informationsecurity.base.BaseDetailActivity
import com.example.informationsecurity.service.impl.SHA1ServiceImpl

/**
 * 加解密实现算法、SHA-1加解密
 *
 * @author qq1962247851
 * @date 2020/12/21 23:40
 **/
class SHA1DetailActivity : BaseDetailActivity(SHA1ServiceImpl()) {
}