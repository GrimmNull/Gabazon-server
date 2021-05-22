package net.mrgrimm.gabazon;


import java.io.IOException;

public class App
{
    public static DBConnection myDB=DBConnection.getConnection();
    public static void main( String[] args ) throws IOException {
        SocketServer server = new SocketServer();
    }
}
