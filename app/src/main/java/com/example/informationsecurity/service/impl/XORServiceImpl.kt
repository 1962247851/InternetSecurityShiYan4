package com.example.informationsecurity.service.impl

import com.example.informationsecurity.service.BaseEncodeDecodeService
import com.example.informationsecurity.util.EncryptUtil

/**
 * 异或加解密
 *
 * @author qq1962247851
 * @date 2020/12/22 0:14
 **/
class XORServiceImpl : BaseEncodeDecodeService {
    override fun getName(): String = "异或加解密"

    override fun getIsNeedKey() = true

    override fun encode(key: String?, decodedString: String): String =
        EncryptUtil.getInstance().XORencode(decodedString, key)

    override fun decode(key: String?, encodedString: String): String =
        EncryptUtil.getInstance().XORdecode(encodedString, key)
}