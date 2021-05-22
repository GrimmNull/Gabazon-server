package net.mrgrimm.gabazon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ItemDAO {
    public static List<Item> findAll(DBConnection myDB){
        List<Item> res=new LinkedList<>();
        ResultSet set=myDB.queryTheDatabase("SELECT * FROM items");
        try {
            while(set.next())
                res.add(new Item(set.getInt(1),set.getString(2),set.getInt(3),set.getInt(4)));
        }catch(SQLException e){System.err.println(e);}
        return res;
    }
    public static Item findByName(DBConnection myDB, String name){
        StringBuilder temp=new StringBuilder();
        temp.append("SELECT * FROM items WHERE name='");
        temp.append(name);
        temp.append("'");
        ResultSet set=myDB.queryTheDatabase(temp.toString());
        try {
            if(set==null)
                return null;
            else{
                set.next();
                return new Item(set.getInt(1),set.getString(2),set.getInt(3),set.getInt(4));
            }
        }catch(SQLException e){System.err.println(e);}
        return null;
    }

    public static void updateItem(DBConnection myDB, Item item){
        StringBuilder temp=new StringBuilder();
        temp.append("UPDATE items");
        temp.append("SET quantity='");
        temp.append(item.getQuantity());
        temp.append("' WHERE id=");
        temp.append(item.getId());
        myDB.queryTheDatabase(temp.toString());
    }
}
