package net.mrgrimm.gabazon;


import net.mrgrimm.gabazon.commands.ListItems;

import java.io.IOException;

public class App
{
    public static DBConnection myDB;
    public static void main( String[] args ) throws IOException {
        myDB=DBConnection.getConnection();
        new ListItems().execute();
        SocketServer server = new SocketServer();
    }
}
