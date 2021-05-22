package net.mrgrimm.gabazon;

import net.mrgrimm.gabazon.commands.Buy;
import net.mrgrimm.gabazon.commands.ListItems;
import net.mrgrimm.gabazon.commands.Sell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

class SocketClient extends Thread {
    private Socket socket = null;

    public SocketClient(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request;
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String raspuns = "";
            Boolean running = true;
            request = null;
            request = in.readLine();
            String[] args = request.split(" ");
            switch (args[0]) {
                case "buy": {
                    raspuns = new Buy().execute(args);
                    break;
                }
                case "sell": {
                    raspuns = new Sell().execute(args);
                    break;
                }
                case "list": {
                    raspuns = new ListItems().execute(args);
                    break;
                }
                default: {
                    raspuns = "I don't know this command";
                }
            }
            out.println(raspuns);
            out.flush();


        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}