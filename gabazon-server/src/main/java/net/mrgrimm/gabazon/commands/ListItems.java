package net.mrgrimm.gabazon.commands;

import net.mrgrimm.gabazon.App;
import net.mrgrimm.gabazon.Item;
import net.mrgrimm.gabazon.ItemDAO;

import java.util.List;

public class ListItems {
    public String execute(String[] args){
        List<Item> items= ItemDAO.findAll(App.myDB);
        return "yes";
    }
}
