package net.mrgrimm.gabazon;


import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class SocketServer {
    public static final int PORT = 8100;
    private static Boolean running;
    public static List<Client> users = new ArrayList<>();

    public SocketServer() throws IOException {
        ServerSocket serverSocket;
        serverSocket = null;
        running = true;
        try {
            serverSocket = new ServerSocket(PORT);
            while (running) {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                System.out.println("The port is:" + socket.getPort());
                new SocketClient(socket).start();
            }
        } catch (IOException e) {
            System.err.println("Ooops... " + e);
        } finally {
            serverSocket.close();
        }
    }


    public static Client findUserByPort(Integer port) {
        for (Client temp : users) {
            if (temp.getConnectedPort().equals(port))
                return temp;
        }
        return null;
    }


}
