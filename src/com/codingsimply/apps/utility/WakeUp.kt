package com.codingsimply.apps.utility

import com.codingsimply.apps.core.data.Computer

import java.io.IOException
import java.net.*

class WakeUp(private val port: Int) {

    @Throws(IOException::class)
    fun now(computer: Computer) {
        val macBytes = getMacBytes(computer.mac)
        val bytes = ByteArray(6 + 16 * macBytes.size)
        for (i in 0..5) {
            bytes[i] = 0xff.toByte()
        }
        var i = 6
        while (i < bytes.size) {
            System.arraycopy(macBytes, 0, bytes, i, macBytes.size)
            i += macBytes.size
        }

        val address = InetAddress.getByName(computer.ip)
        val packet = DatagramPacket(bytes, bytes.size, address, port)
        DatagramSocket().use { socket -> socket.send(packet) }
    }

    @Throws(IllegalArgumentException::class)
    private fun getMacBytes(macStr: String?): ByteArray {
        val bytes = ByteArray(6)
        val hex = macStr!!.split("([:-])".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (hex.size != 6) {
            throw IllegalArgumentException("Invalid MAC address.")
        }
        for (i in 0..5) {
            bytes[i] = Integer.parseInt(hex[i], 16).toByte()
        }
        return bytes
    }
}
