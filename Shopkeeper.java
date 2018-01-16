/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventuregame;
import java.util.ArrayList;
/**
 *
 * @author tuf39533
 */
public class Shopkeeper extends Enemy {
    private String itemsSold;
    ArrayList<String>items = new ArrayList<>();
   
    
    public Shopkeeper(String itemsSold, int points, int lives){
        super(points, lives);
        this.itemsSold = itemsSold;
    }

    public String getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(String itemsSold) {
        this.itemsSold = itemsSold;
    }
    
    public void giveItems(String items){
        items = getItemsSold() + items;
        setItemsSold(items);
    }
    
    public String toString(){
        return ("You have these items: " + items);
    }
}