package com.example.informationsecurity.service.impl

import com.example.informationsecurity.service.BaseEncodeDecodeService
import com.example.informationsecurity.util.EncryptUtil


/**
 * AES加解密实现类
 *
 * @author qq1962247851
 * @date 2020/12/18 18:19
 **/
class AESServiceImpl : BaseEncodeDecodeService {

    /**
     * 获取算法名称
     * @return 算法名称
     */
    override fun getName() = "AES加解密"

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
        return EncryptUtil.getInstance().AESencode(decodedString, key)
    }

    /**
     * 解密
     *
     * @param key 密钥，可以为空
     * @param encodedString 明文
     * @return 密文
     */
    override fun decode(key: String?, encodedString: String): String {
        return EncryptUtil.getInstance().AESdecode(encodedString, key)
    }

}