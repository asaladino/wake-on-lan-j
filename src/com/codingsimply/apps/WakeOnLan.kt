package com.codingsimply.apps

import com.codingsimply.apps.core.data.Computer
import com.codingsimply.apps.utility.WakeUp

import java.io.IOException

object WakeOnLan {

    private val PORT = 9

    @JvmStatic
    fun main(args: Array<String>) {
        val ip = args[0]
        val mac = args[1]

        val computer = Computer(ip, mac)
        try {
            val wakeUp = WakeUp(PORT)
            wakeUp.now(computer)
            println("Wake-on-LAN packet sent.")
        } catch (e: IOException) {
            println("Failed to send Wake-on-LAN packet: + e")
            System.exit(1)
        } catch (e: IllegalArgumentException) {
            println("Failed to send Wake-on-LAN packet: + e")
            System.exit(1)
        }

    }
}
