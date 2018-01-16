/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventuregame;
import java.util.Random;
/**
 *
 * @author tuf39533
 */
public class Room {
	private int roomType;// empty = 0, item = 1, map = 2
	private Riddles riddle;
	private Spill spill;
	private Shopkeeper shopkeeper;
    private String item;
	
	public Room(int roomType, Riddles riddle, Spill spill, Shopkeeper shopkeeper){
		this.roomType = roomType;
		this.riddle = riddle;
		this.spill = spill;
		this.shopkeeper = shopkeeper;
		this.item = "";
	}
    
	//im not sure what this does but im gonna leave it just in case
    public String availableItem(String[] items){
        Random rand = new Random();
        return items[rand.nextInt(4)];
        
    }
    
	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}

	public Riddles getRiddle() {
		return riddle;
	}

	public void setRiddle(Riddles riddle) {
		this.riddle = riddle;
	}

	public Spill getSpill() {
		return spill;
	}

	public void setSpill(Spill spill) {
		this.spill = spill;
	}

	public Shopkeeper getShopkeeper() {
		return shopkeeper;
	}

	public void setShopkeeper(Shopkeeper shopkeeper) {
		this.shopkeeper = shopkeeper;
	}
        
	public String getItem(){
		return item;
	}
	
	public void setItem(String item){
		this.item = item;
	}
	
	//to be used to populate the map with items
	public static void randomizeItems(String[] items, Room[][] map ){
		Random rand = new Random();
		
		//populates the rooms with items
		for(int i = 0; i < items.length; i++){
			int tempRow = 0;
			int tempCol = 0;
			
			do{
				tempRow = rand.nextInt(3);
				tempCol = rand.nextInt(3);
			}while(map[tempRow][tempCol].getRoomType() == 1 || map[tempRow][tempCol].getRoomType() == 2);
			
			map[tempRow][tempCol].setRoomType(1);
			map[tempRow][tempCol].setItem(items[i]);
			
		}
	}
	
	public String toString() {
		if (roomType == 0) {// no item
			return ".";
		} else if (roomType == 1) {//item
			return "*";
		} else
			return "/";

	}
        

}//end of class