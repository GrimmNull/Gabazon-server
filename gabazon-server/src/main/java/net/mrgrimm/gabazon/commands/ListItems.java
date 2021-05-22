package net.mrgrimm.gabazon.commands;

import net.mrgrimm.gabazon.App;
import net.mrgrimm.gabazon.Item;
import net.mrgrimm.gabazon.ItemDAO;

import java.util.List;

public class ListItems {
    public String execute(){
        StringBuilder result=new StringBuilder();
        List<Item> items= ItemDAO.findAll(App.myDB);
        for(Item temp : items){
            System.out.println(temp);
            result.append(temp.getName())
                    .append(":")
                    .append(temp.getQuantity())
                    .append(":")
                    .append(temp.getPrice())
                    .append(" ");
        }
        return result.toString();
    }
}
