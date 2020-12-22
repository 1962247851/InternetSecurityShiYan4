package com.example.informationsecurity.service.impl

import com.example.informationsecurity.service.BaseEncodeDecodeService

/**
 * RC4加解密实现类
 *
 * @author qq1962247851
 * @date 2020/12/19 9:50
 **/
class RC4ServiceImpl : BaseEncodeDecodeService {

    /**
     * 获取算法名称
     * @return 算法名称
     */
    override fun getName() = "RC4加解密"

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
        return RC4(decodedString, key!!)
    }

    /**
     * 解密
     *
     * @param key 密钥，可以为空
     * @param encodedString 明文
     * @return 密文
     */
    override fun decode(key: String?, encodedString: String): String {
        return RC4(encodedString, key!!)
    }

    private fun RC4(aInput: String, aKey: String): String {
        val sBoxLength = 256
        val iS = IntArray(sBoxLength)
        val iK = ByteArray(sBoxLength)
        for (i in 0 until sBoxLength) {
            iS[i] = i
        }
        for (i in 0 until sBoxLength - 1) {
            iK[i] = aKey[i % aKey.length].toByte()
        }
        var j = 0
        for (i in 0 until sBoxLength - 1) {
            j = (j + iS[i] + iK[i]) % sBoxLength
            val temp = iS[i]
            iS[i] = iS[j]
            iS[j] = temp
        }
        var i = 0
        j = 0
        val iInputChar = aInput.toCharArray()
        val iOutputChar = CharArray(iInputChar.size)
        for (x in iInputChar.indices) {
            i = (i + 1) % sBoxLength
            j = (j + iS[i]) % sBoxLength
            val temp = iS[i]
            iS[i] = iS[j]
            iS[j] = temp
            val t = (iS[i] + iS[j] % sBoxLength) % sBoxLength
            val iY = iS[t]
            val iCY = iY.toChar()
            iOutputChar[x] = (iInputChar[x].toInt() xor iCY.toInt()).toChar()
        }
        return String(iOutputChar)
    }

}