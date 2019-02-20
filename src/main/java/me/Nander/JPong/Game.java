package me.Nander.JPong;


import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import me.Nander.JPong.Controller.PongController;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        PongController Controller = new PongController();
        loadPresence();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Controller.Spielstart();
    }

    private static void loadPresence() {
        DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = "547827070143692811";
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000; // epoch second
        presence.details = "https://JPong.Nander.me/";
        presence.partySize = 2;
        presence.partyMax = 2;
        presence.largeImageKey = "jpong_logo";
        presence.largeImageText = "Made by Nander#0001";
        lib.Discord_UpdatePresence(presence);
        // in a worker thread
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC-Callback-Handler").start();
    }

}
