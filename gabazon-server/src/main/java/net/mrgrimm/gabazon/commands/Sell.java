package net.mrgrimm.gabazon.commands;

import net.mrgrimm.gabazon.App;
import net.mrgrimm.gabazon.Item;
import net.mrgrimm.gabazon.ItemDAO;

public class Sell {
    public String execute(String[] args,Integer buget){
        Integer moneyFromProducts=0;
        for(String temp : args){
            temp=temp.replace(" ","").replace("x"," ");
            Item itemCumparat= ItemDAO.findByName(App.myDB,temp.split(" ")[1]);
            Integer quantity=Integer.parseInt(temp.split(" ")[0]);
            itemCumparat.setQuantity(itemCumparat.getQuantity()+quantity);
            moneyFromProducts+=quantity*itemCumparat.getPrice();
            ItemDAO.updateItem(App.myDB,itemCumparat);
        }
        return moneyFromProducts.toString();
    }
}
