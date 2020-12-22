package com.example.informationsecurity.service

/**
 * 加解密基类
 *
 * @author qq1962247851
 * @date 2020/12/18 9:25
 **/
interface BaseEncodeDecodeService {

    /**
     * 获取算法名称
     * @return 算法名称
     */
    fun getName(): String

    /**
     * 获取算法是否需要密钥
     * @return 是否需要密钥，或者不设置
     */
    fun getIsNeedKey(): Boolean?

    /**
     * 加密
     * @param key 密钥，可以为空
     * @param decodedString 密文
     * @return 明文
     */
    fun encode(key: String?, decodedString: String): String

    /**
     * 解密
     *
     * @param key 密钥，可以为空
     * @param encodedString 明文
     * @return 密文
     */
    fun decode(key: String?, encodedString: String): String

}