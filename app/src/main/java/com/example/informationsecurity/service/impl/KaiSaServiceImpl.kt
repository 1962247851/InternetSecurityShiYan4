package com.example.informationsecurity.service.impl

import com.example.informationsecurity.exception.KeyNotValidException
import com.example.informationsecurity.service.BaseEncodeDecodeService

/**
 * 凯撒加解密
 *
 * @author qq1962247851
 * @date 2020/12/19 23:21
 **/
class KaiSaServiceImpl : BaseEncodeDecodeService {

    /**
     * 获取算法名称
     * @return 算法名称
     */
    override fun getName() = "凯撒加解密"

    /**
     * 获取算法是否需要密钥
     * @return 是否需要密钥
     */
    override fun getIsNeedKey() = true

    /**
     * 加密
     * @param key 密钥，可以为空
     * @param decodedString 密文
     * @return 明文
     */
    override fun encode(key: String?, decodedString: String): String {
        val k: Int
        try {
            k = key!!.toInt()
        } catch (e: NumberFormatException) {
            throw KeyNotValidException("密钥输入有误")
        }
        var string = ""
        for (i in decodedString.indices) {
            var c: Char = decodedString[i]
            if (c in 'a'..'z') {
                c += k % 26
                if (c < 'a') c += 26
                if (c > 'z') c -= 26
            }
            if (c in 'A'..'Z') {
                c += k % 26
                if (c < 'a') c += 26
                if (c > 'z') c -= 26
            }
            string += c
        }
        return string
    }

    /**
     * 解密
     *
     * @param key 密钥，可以为空
     * @param encodedString 明文
     * @return 密文
     */
    override fun decode(key: String?, encodedString: String): String {
        val k: Int
        try {
            k = -key!!.toInt()
        } catch (e: NumberFormatException) {
            throw KeyNotValidException("密钥输入有误")
        }
        var string = ""
        for (i in encodedString.indices) {
            var c: Char = encodedString[i]
            if (c in 'a'..'z') {
                c += (k % 26).toChar().toInt()
                if (c < 'a') c += 26
                if (c > 'z') c -= 26
            } else if (c in 'A'..'Z') {
                c += k % 26
                if (c < 'A') c += 26
                if (c > 'Z') c -= 26
            }
            string += c
        }
        return string
    }
}