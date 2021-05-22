package net.mrgrimm.gabazon.commands;

import net.mrgrimm.gabazon.App;
import net.mrgrimm.gabazon.Item;
import net.mrgrimm.gabazon.ItemDAO;

public class Buy {
    public String execute(String[] args,Integer buget){
        Integer price=0;
        StringBuilder result=new StringBuilder();
        for(String temp : args){
            // temp e de forma NRxPRODUS
            temp=temp.replace(" ","").replace("x"," ");
            Item itemCumparat=ItemDAO.findByName(App.myDB,temp.split(" ")[1]);
            Integer quantity=Math.min(Integer.parseInt(temp.split(" ")[0]), itemCumparat.getQuantity());
            price+=quantity*itemCumparat.getPrice();
            result.append(quantity)
                  .append("x")
                  .append(itemCumparat.getName())
                  .append(" ");
        }
        if(price>buget)
            return "Error: Your buget is not big enough";
        result=new StringBuilder().append(price).append(" ").append(result.toString());
        return result.toString();
    }
}
