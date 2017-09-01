package com.codingsimply.apps;

import com.codingsimply.apps.core.data.Computer;
import com.codingsimply.apps.utility.WakeUp;

import java.io.IOException;

public class WakeOnLan {

    private static final int PORT = 9;

    public static void main(String[] args) {
        Computer computer = new Computer("192.168.1.2", "00:00:00:00:00:00");
        try {
            WakeUp wakeUp = new WakeUp(PORT);
            wakeUp.now(computer);
            System.out.println("Wake-on-LAN packet sent.");
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Failed to send Wake-on-LAN packet: + e");
            System.exit(1);
        }
    }
}
