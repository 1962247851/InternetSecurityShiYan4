package com.example.informationsecurity.service.impl

import android.util.Base64
import com.example.informationsecurity.service.BaseEncodeDecodeService
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher


/**
 * RSA加解密实现类
 *
 * @author qq1962247851
 * @date 2020/12/20 22:52
 **/
class RSAServiceImpl : BaseEncodeDecodeService {

    /**
     * 获取算法名称
     * @return 算法名称
     */
    override fun getName() = "RSA加解密"

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
        //base64编码的公钥
        val decoded = Base64.decode(key, Base64.DEFAULT)
        //RSA加密
        val cipher = Cipher.getInstance("RSA")
        cipher.init(
            Cipher.ENCRYPT_MODE,
            KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(decoded))
        )
        return String(
            Base64.encode(
                cipher.doFinal(decodedString.toByteArray(Charsets.UTF_8)),
                Base64.DEFAULT
            )
        )
    }

    /**
     * 解密
     *
     * @param key 密钥，可以为空
     * @param encodedString 明文
     * @return 密文
     */
    override fun decode(key: String?, encodedString: String): String {
        //64位解码加密后的字符串
        val inputByte = Base64.decode(encodedString.toByteArray(Charsets.UTF_8), Base64.DEFAULT)
        //base64编码的私钥
        val decoded = Base64.decode(key, Base64.DEFAULT)
        val priKey = KeyFactory.getInstance("RSA")
            .generatePrivate(PKCS8EncodedKeySpec(decoded)) as RSAPrivateKey
        //RSA解密
        val cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.DECRYPT_MODE, priKey)
        return String(cipher.doFinal(inputByte))
    }

}