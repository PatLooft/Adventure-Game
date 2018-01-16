/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventuregame;

/**
 *
 * @author tuf39533
 */
import java.util.Random;

public class Map {
	private Room[][] map;//map of all of the rooms
	private boolean[][] lock;//boolean 2d array to track what rooms you've been in
	
	//creates a blank map grid of whatever size is passed in
	/**
	 * Map			-the constructor for Map
	 * @param size	-the size of the map
	 */
	public Map(int size){
		map = new Room[size][size];
	}

        public Room[][] getMap() {
            return map;
        }

        public void setMap(Room[][] map) {
            this.map = map;
        }

        public boolean[][] getLock() {
            return lock;
        }

        public void setLock(boolean[][] lock) {
            this.lock = lock;
        }
	
	/**
	 * populateMap 	- creates a randomized map (a 2d array of rooms)
	 * @return		- the randomized map (a 2d array of rooms)
	 */
	public Room[][] populateMap(String[] item){
		Room mapRoom;//create a random room and then set the room type to map room
		Random rand = new Random();
		mapRoom = randomRoom(item);
		mapRoom.setRoomType(2);
		
		map[0][0] = mapRoom;
		
		for(int r = 0; r < map.length; r++){
			for(int c = 0; c < map[0].length; c++){
				if(r == 0 && c == 0){//skips first room because that will always be the map room
					c=1;
				}//end of if statement
				map[r][c] = randomRoom(item);
			}//end of nested for loop (columns)
		}//end of nested for loop (rows)
		
		return map;
	}
	
	/**
	 * randomRoom 	- a method that creates a room and randomizes each of the rooms parameters
	 * @return		- a randomized room
	 */
	public Room randomRoom(String[] items){
		//make it so rooms can only be type 0 (empty) or 1 (item). map will be created in the populateMap method
		Random rand = new Random();
		Room room;
		int type;
		boolean shopkeeper, spill, riddle;
		Enemy sk = null, s = null, r = null;//sk = shopkeeper, s = spill, r = riddle
		
		//creates room of type 0(empty) or 1(item room)
		//type = rand.nextInt(2);
		type=0;
		
		//decides if an enemy will be in the room or not by creating a random boolean
		//true means the enemy will be in the room, false means the enemy will not be in the room
		shopkeeper = rand.nextBoolean();
		spill = rand.nextBoolean();
		riddle = rand.nextBoolean();
		
		//if the enemy will be in the room, create enemy. 
		if(shopkeeper)
			sk = new Shopkeeper(items[0], 0, 0);
		if(spill)
			s = new Spill(0,0);
		if(riddle)
			r = new Riddles(Riddles.askQuestions(), Riddles.Answers(),0,0);
		
		room = new Room(type, (Riddles)r, (Spill)s, (Shopkeeper)sk);//creates a room object thats been randomized using the random variables created in
																   //this method
		
		return room;
	}//end of randomRoom method
        
     
}//end of class