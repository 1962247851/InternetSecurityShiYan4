package com.example.informationsecurity.activity.encodedecode

import com.example.informationsecurity.base.BaseDetailActivity
import com.example.informationsecurity.service.impl.RC4ServiceImpl

/**
 * 加解密实现算法一、AES加解密
 *
 * @author qq1962247851
 * @date 2020/12/18 9:58
 **/
class RC4DetailActivity : BaseDetailActivity(RC4ServiceImpl())