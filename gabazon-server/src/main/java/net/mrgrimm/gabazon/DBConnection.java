package net.mrgrimm.gabazon;


import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class DBConnection {
    private Statement stmt;
    private ResultSet result;
    private Connection con;
    private static DBConnection dbConn=null;
    private DBConnection(){
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){ System.out.println(e);}
    }
    public static DBConnection getConnection(){
        if(dbConn==null)
            dbConn=new DBConnection();
        return dbConn;
    }
    public ResultSet queryTheDatabase(String stm) throws SQLException {
            if (con == null || con.isClosed())
                con = DriverManager.getConnection("jdbc:mysql://localhost/minecraftitem","gabazon","server");
            stmt=con.createStatement();
            if(stm.toLowerCase(Locale.ROOT).contains("update"))
            {
                stmt.executeUpdate(stm);
                return null;
            }
            else
            result=stmt.executeQuery(stm);
        return result;
    }

    public void closeConn() throws SQLException {
        con.close();
    }
}
