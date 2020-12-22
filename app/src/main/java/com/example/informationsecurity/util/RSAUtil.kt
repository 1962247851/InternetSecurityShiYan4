package com.example.informationsecurity.util

import android.util.Base64
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

/**
 * RSA工具类
 *
 * @author qq1962247851
 * @date 2020/12/21 20:52
 **/
object RSAUtil {
    private val keyMap = HashMap<Int, String>() //用于封装随机产生的公钥与私钥

    init {
        genKeyPair()
    }

    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    @Throws(NoSuchAlgorithmException::class)
    fun genKeyPair() {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        val keyPairGen: KeyPairGenerator = KeyPairGenerator.getInstance("RSA")
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, SecureRandom())
        // 生成一个密钥对，保存在keyPair中
        val keyPair: KeyPair = keyPairGen.generateKeyPair()
        val privateKey: RSAPrivateKey = keyPair.private as RSAPrivateKey // 得到私钥
        val publicKey: RSAPublicKey = keyPair.public as RSAPublicKey // 得到公钥
        val publicKeyString = String(Base64.encode(publicKey.encoded, Base64.DEFAULT))
        // 得到私钥字符串
        val privateKeyString = String(Base64.encode(privateKey.encoded, Base64.DEFAULT))
        // 将公钥和私钥保存到Map
        keyMap[0] = publicKeyString //0表示公钥
        keyMap[1] = privateKeyString //1表示私钥
    }

    fun getPublicKey(): String = keyMap[0].toString()

    fun getPrivateKey(): String = keyMap[1].toString()
}