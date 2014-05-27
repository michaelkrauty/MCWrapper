package me.michaelkrauty.MCWrapper.CrashDetection;

import me.michaelkrauty.MCWrapper.Commands.ForceRestart;
import me.michaelkrauty.MCWrapper.Server;
import me.michaelkrauty.MCWrapper.ServerInstance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by michael on 5/27/14.
 */
public class CrashDetector implements Runnable {

    private final Server server;
    private static Thread t;
    private final ServerInstance serverInstance;

    public CrashDetector(ServerInstance serverInstance) {
        this.serverInstance = serverInstance;
        this.server = serverInstance.getServer();
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    public void run() {
        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(
                server.getInputStream()));
        try {
            // TODO: put in SQL database or something later
            int threshold = 60;
            while ((line = in.readLine()) != null) {
                long currentTime = System.currentTimeMillis();
                if (serverInstance.getLastResponse() < (currentTime - (threshold * 1000))) {
                    new ForceRestart(server.getId());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}