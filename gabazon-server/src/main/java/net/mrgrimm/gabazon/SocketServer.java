package net.mrgrimm.gabazon;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer {
    public static final int PORT = 8100;

    public SocketServer() throws IOException {
        ServerSocket serverSocket;
        serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
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


}
