package com.example.informationsecurity.service.impl

import com.example.informationsecurity.service.BaseEncodeDecodeService
import com.example.informationsecurity.util.EncryptUtil


/**
 * SHA-1加解密实现类
 *
 * @author qq1962247851
 * @date 2020/12/21 23:41
 **/
class SHA1ServiceImpl : BaseEncodeDecodeService {

    /**
     * 获取算法名称
     * @return 算法名称
     */
    override fun getName() = "SHA-1加解密"

    /**
     * 获取算法是否需要密钥
     * @return 是否需要密钥
     */
    override fun getIsNeedKey(): Boolean? = null

    /**
     * 加密
     * @param key 密钥，可以为空
     * @param decodedString 密文
     * @return 明文
     */
    override fun encode(key: String?, decodedString: String): String {
        return EncryptUtil.getInstance().SHA1(decodedString, key)
    }

    /**
     * 解密
     *
     * @param key 密钥，可以为空
     * @param encodedString 明文
     * @return 密文
     */
    override fun decode(key: String?, encodedString: String): String {
        throw Exception("SHA-1不可逆")
    }

}