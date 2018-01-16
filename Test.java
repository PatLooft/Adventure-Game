/*
 * Patrick Looft, Emilia Pallante, Sarah Noshin, Priscilla Joye
 */
package adventuregame;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author tuf39533
 */
public class Test {
    public static void main(String[] args){
        String[] items = {"chocolate", "banana", "apple", "donut"};//the list of items the player has to get
        Map game = new Map(3);
        String[] itemList = {"","","",""};//the items that the player has obtained
        Scanner kb = new Scanner(System.in);
        
        //create printwriter object
        PrintWriter output = null;
        try {
			output = new PrintWriter("log");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //create map and randomize items throughout map
        Room[][] gameMap = game.populateMap(items);
        Room.randomizeItems(items, gameMap);
       
		String temp = "";
		for (int r = 0; r < gameMap.length; r++) {
			for (int c = 0; c < gameMap[0].length; c++) {
				temp = temp + gameMap[r][c].toString() + "\t";
			}
			temp = temp + "\n";
		}
        System.out.println(temp); 
        System.out.println("KEY: \n/ = map room\n* = item room\n. = room with nothing in it");
        
        output.println(temp);
        output.println("KEY: \n/ = map room\n* = item room\n. = room with nothing in it");
        
        boolean[][] loc = new boolean[3][3];
        loc[0][0] = true; 
        
        Player Sarah = new Player("Sarah", 0, 5, loc);
        
        Riddles first = new Riddles();
                
    	System.out.println(first.toString());//first riddle to get the map
    	output.println(first.toString());	//prints first riddle to log
		String answer = kb.nextLine();
		output.println("User response: " + answer);
		if (first.checkAnswer(answer)) {
			System.out.println("Here's the list of items: ");
			output.println("Here's the list of items:");
			for (int i = 0; i < items.length; i++) {
				System.out.println(" " + items[i]);
				output.println(items[i]);
			}
			System.out.println();
		}
		else{
			first.changeLives(-1, Sarah);
			System.out.println("You have "+ Sarah.getTotalLives()+" lives left.");
			output.println("You have "+ Sarah.getTotalLives()+" lives left.");
			System.out.println("Here's the list of items: ");
			output.println("Here's the list of items: ");
			for (int i = 0; i < items.length; i++) {
				System.out.println(items[i]);
				output.println(items[i]);
			}
			System.out.println();
		}
		
		System.out.println();
		output.println();
		
		int itemListCount = 0;
		while(Sarah.getTotalLives() > 0){
			move(Sarah);
			
			//checks to see if player is in map room. If the player is in map room, display map
			int[] loca = Sarah.playerLocation();
			if(gameMap[loca[0]][loca[1]] == gameMap[0][0]){
				String temps = "";
	            for(int r = 0; r<gameMap.length; r++){
	                for(int c = 0; c<gameMap[0].length; c++){
	                    temps = temps+ gameMap[r][c].toString() + "\t";
	                }
	                temps = temps + "\n";
	            }
	        System.out.println(temps);
	        output.println(temps);
	        System.out.println("KEY:\n/ = map room\n* = item room\n. = room with nothing in it");
	        output.println("KEY:\n/ = map room\n* = item room\n. = room with nothing in it");
			}
			
			//checks to see if room has an item in it
			if(gameMap[loca[0]][loca[1]].getRoomType() == 1 && gameMap[loca[0]][loca[1]].getItem() != ""){
				System.out.println("Theres an item in here! Would you like to pick it up?");
				System.out.println("Respond y for yes and n for no:");
				output.println("Theres an item in here! Would you like to pick it up?");
				output.println("Respond y for yes and n for no:");
				String response = kb.nextLine();
				output.println("User response was: "+response+".");
				if(response.equalsIgnoreCase("y")){
					itemList[itemListCount] = gameMap[loca[0]][loca[1]].getItem();
					System.out.println("You picked up a "+gameMap[loca[0]][loca[1]].getItem()+"!");
					output.println("You picked up a "+gameMap[loca[0]][loca[1]].getItem()+"!");
					gameMap[loca[0]][loca[1]].setItem("");
					itemListCount++;
				}
			}
			
			//checks to see if room has a spill in it, if it does you slip and lose a life
			if(gameMap[loca[0]][loca[1]].getSpill() != null){
				System.out.println("You slipped on a spill! You lose a life.");
				output.println("You slipped on a spill! You lose a life.");
				gameMap[loca[0]][loca[1]].getSpill().changeLives(-1, Sarah);
				System.out.println("You have "+ Sarah.getTotalLives()+" lives left.");
				output.println("You have "+ Sarah.getTotalLives()+" lives left.");
			}
			
			//checks to see if room has a shopkeeper in it. If it does, you get the chance to buy an item
			if(gameMap[loca[0]][loca[1]].getShopkeeper() != null){
				System.out.println("You've entered a room with a shopkeeper, would you like to shop?");
				System.out.println("Enter y for yes, and n for no:");
				output.println("You've entered a room with a shopkeeper, would you like to shop?");
				output.println("Enter y for yes, and n for no:");
				if(kb.nextLine().equals("y")){
					output.println("User response was: y.");
					System.out.println("Welcome to the shop!");
					output.println("Welcome to the shop!");
					Random rand = new Random();
					String shopItem = items[rand.nextInt(4)];
					boolean hasItemAlready = false;
					for(int i = 0; i<itemList.length; i++){
						if(shopItem.equals(itemList[i])){
							hasItemAlready = true;
							break;
						}
					}
					if(hasItemAlready){
						System.out.println("You already have the item thats up for sale.");
						output.println("You already have the item thats up for sale.");
					}
					else{
						System.out.println("Would you like to buy "+shopItem+"?\nIt will cost one life.");
						System.out.println("Enter y to buy the item, and n to not buy the item.");
						output.println("Would you like to buy "+shopItem+"?\nIt will cost one life.");
						output.println("Enter y to buy the item, and n to not buy the item.");
						if(kb.nextLine().equalsIgnoreCase("y")){
							output.println("User response was y.");
							//code block to destroy item that you bought in the room it was in
							for(int r = 0; r<gameMap.length; r++){
								for(int c = 0; c<gameMap[0].length; c++){
									if(gameMap[r][c].getItem().equals(shopItem)){
										gameMap[r][c].setItem("");
									}
								}
							}
							
							//code block to add item to players inventory
							itemList[itemListCount] = shopItem;
							System.out.println("You bought a "+shopItem+"!");
							output.println("You bought a "+shopItem+"!");
							gameMap[loca[0]][loca[1]].setItem("");
							itemListCount++;
							
							//code block to take away one life
							gameMap[loca[0]][loca[1]].getShopkeeper().changeLives(-1, Sarah);
							System.out.println("You have "+ Sarah.getTotalLives()+" lives left.");
							output.println("You have "+ Sarah.getTotalLives()+" lives left.");
							
						}
					}
					//find a way to make a temporary item for
				}
				else{
					output.println("User response was: n.");
				}
			}
			
			//ends game once the player has all the items
			if(endGame(itemList, items)){
				break;
			}
		}
		
		//end of game
		if(Sarah.getTotalLives() <= 0){
			System.out.println("You have run out of lives. You lose.");
			output.println("You have run out of lives. You lose.");
		}
		else{
			System.out.println("Congratulations! You got all of the items! You win!");
			output.println("Congratulations! You got all of the items! You win!");
		}
		output.close();
    }
    
    public static void move(Player name){
		Scanner kb = new Scanner(System.in);
		int t = name.getTotalLives();
		boolean loopcondition = true;
		String move = "";
		PrintWriter output = null;
			try {
				output = new PrintWriter(new FileOutputStream("log",true));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		//checks to see if user is entering u, d, r, or l. If they aren't keep looping until they do.
		do{
			
			
			System.out.println("You can move by entering u for up, d for down, l for left, r for right.");
			System.out.println("Please enter what room you would like to enter: ");
			
			
			output.println("You can move by entering u for up, d for down, l for left, r for right.");
			output.println("Please enter what room you would like to enter: ");
			move = kb.nextLine();
			output.println("User response was: "+move+".");
			if(move.equalsIgnoreCase("u") || move.equalsIgnoreCase("d") ||
					move.equalsIgnoreCase("l") || move.equalsIgnoreCase("r")){
				break;
			}
		}while(loopcondition);
		
		
		//try catch to check if the player is moving to a spot outside of the map.
		//If the player moves out of the map, they lose a life.
		try {
			int[] location = name.playerLocation();
			if (location[0] == 0 && move.equalsIgnoreCase("u")) {
				throw new Exception("Out of map. -1 lives.");
			} else if (location[0] == 2 && move.equalsIgnoreCase("d")) {
				throw new Exception("Out of map. -1 lives.");
			} else if (location[1] == 0 && move.equalsIgnoreCase("l")) {
				throw new Exception("Out of map. -1 lives.");
			} else if (location[1] == 2 && move.equalsIgnoreCase("r")) {
				throw new Exception("Out of map. -1 lives.");
			} else {
				Riddles nextRoom = new Riddles();
				System.out.println(nextRoom.toString());
				output.println(nextRoom.toString());
				String answer = kb.nextLine();
				if(nextRoom.checkAnswer(answer) == false){
					nextRoom.changeLives(-1, name);
					System.out.println("You have "+ name.getTotalLives()+" lives left.");
					output.println("You have "+ name.getTotalLives()+" lives left.");
				}
				name.SwitchRoom(move);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			output.println(e.getMessage());
			name.setTotalLives(t - 1);
			System.out.println("You have " + name.getTotalLives() + " lives left.");
			output.println("You have " + name.getTotalLives() + " lives left.");
		}
		output.close();
    }//end of move
    
    public static boolean endGame(String[] itemList, String[] items){
    	boolean allItems = false;
    	boolean[] itemLoc = new boolean[4];//used to see if each item in itemList is also in items, if the
    	//index of itemList is also in items, set itemLoc[index of itemList] to true
    	for(int i = 0; i < itemList.length; i++){
    		for(int j = 0; j < items.length; j++){
    			if(itemList[i].equals(items[j])){
    				itemLoc[i] = true;
    				break;
    			}
    		}	
    	}
    	//This loop checks to see if all items in itemLoc are true. If they are not, set allItems to false, break
    	//the loop, and return allItems
    	for(int i = 0; i < itemLoc.length; i++){
    		if(itemLoc[i]){
    			allItems = true;
    		}
    		else{
    			allItems = false;
    			break;
    		}
    	}
    	return allItems;
    }
        
}
