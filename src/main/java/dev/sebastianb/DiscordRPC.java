package dev.sebastianb;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRichPresence;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class DiscordRPC {
    static int seconds = 0;


    public DiscordRPC() {
        initDiscord();
        timeThread();
    }

    private void initDiscord() {
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
            System.out.println("Welcome " + user.username + "#" + user.discriminator + ".");
            DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder("Score: ");
            presence.setDetails("Running Test");
            net.arikia.dev.drpc.DiscordRPC.discordUpdatePresence(presence.build());
        }).build();
        net.arikia.dev.drpc.DiscordRPC.discordInitialize("415885161457123338", handlers, false);
        net.arikia.dev.drpc.DiscordRPC.discordRegister("415885161457123338", "");
    }


    private void timeThread() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                seconds++;

                net.arikia.dev.drpc.DiscordRPC.discordRunCallbacks();
                DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder("Time: " + seconds);

                net.arikia.dev.drpc.DiscordRPC.discordUpdatePresence(presence.build());
            }
        };

        Timer timer = new Timer("MyTimer");//create a new Timer

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }



}
