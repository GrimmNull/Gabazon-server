package net.mrgrimm.gabazon.commands;

import net.mrgrimm.gabazon.App;
import net.mrgrimm.gabazon.Item;
import net.mrgrimm.gabazon.ItemDAO;

import java.sql.SQLException;

public class Buy {
    public String execute(String[] args,Integer buget) throws SQLException {
        Integer price=0,totalPrice=0;
        StringBuilder result=new StringBuilder();
        for(String temp : args){
            // temp e de forma NRxPRODUS
            temp=temp.replace("\"","").replace("x"," ");
            Item itemCumparat;
            try {
                    itemCumparat = ItemDAO.findByName(App.myDB, temp.split(" ")[1]);

                    if(itemCumparat==null)
                        return "Error: The product " + temp.split(" ")[1] + " doesn't exist";

                Integer quantity;
                try {
                    quantity = Math.min(Integer.parseInt(temp.split(" ")[0]), itemCumparat.getQuantity());
                }catch(NumberFormatException e){
                    return "Error:The order is not of the correct format";
                }
            price=quantity*itemCumparat.getPrice();
            if(totalPrice+price>buget)
                    break;
            itemCumparat.setQuantity(itemCumparat.getQuantity()-quantity);
            result.append(quantity)
                  .append("x")
                  .append(itemCumparat.getName())
                  .append(" ");
            }catch(ArrayIndexOutOfBoundsException e){
                return "Error:It seems you used the wrong delimiter";
            }
            totalPrice+=price;
            ItemDAO.updateItem(App.myDB,itemCumparat);
        }
        if(totalPrice==0)
            return "Error: Your budget is not big enough";
        buget-=price;
        result=new StringBuilder().append(buget)
                .append(" ")
                .append(price)
                .append(" ")
                .append(result.toString());
        return result.toString();
    }
}
