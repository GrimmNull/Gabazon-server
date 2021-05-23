package net.mrgrimm.gabazon;

import net.mrgrimm.gabazon.commands.Buy;
import net.mrgrimm.gabazon.commands.ListItems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

class SocketClient extends Thread {
    private Socket socket = null;

    public SocketClient(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request;
            Integer buget;
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String raspuns = "";
            Boolean running = true;
            request = null;
            request = in.readLine();
            buget=Integer.parseInt(in.readLine());
            String[] args = request.replaceAll("\"","").split(" ");
            switch (args[0]) {
                case "buy:": {
                    raspuns = new Buy().execute(request.replace("buy: ","").split(" "),buget);
                    break;
                }
                case "list": {
                    raspuns = new ListItems().execute();
                    break;
                }
                default: {
                    raspuns = "Error: Format not met";
                }
            }
            out.println(raspuns);
            out.flush();


        } catch (IOException | SQLException e) {
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