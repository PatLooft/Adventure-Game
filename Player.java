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
public class Player {
    private String name;
//    private int moves; //how many times they move in each room
    private int totalPoints; //total amount of points a player has
    private int totalLives;
    private boolean [][] location;//this represents player location in a 2d boolean array. The player will be wherever the value is true
    
    public Player(String name, int totalPoints, int totalLives, boolean [][] location){
        this.name = name;
        this.totalLives = totalLives;
        this.totalPoints = totalPoints;
//        this.moves = moves;
        this.location = location;
    }

    public int getTotalPoints() {
        return totalPoints;
    }


    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
    public boolean[][] getLocation() {
        return location;
    }

    public void setLocation(boolean[][] location) {
        this.location = location;
    }
    

    public int getTotalLives() {
        return totalLives;
    }

    public void setTotalLives(int totalLives) {
        this.totalLives = totalLives;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getMoves() {
//        return moves;
//    }
//
//    public void setMoves(int moves) {
//        this.moves = moves;
//    } 
    
    public void gridSize(int size){//This is the dimensions of location
        location = new boolean [size][size];
    }
    
    public void SwitchRoom(String move){
        int row = 0;
        int col = 0;
        for(int r = 0; r<location.length; r++){
            for(int c = 0; c<location[0].length; c++){
                if(location[r][c]){
                    row = r;
                    col = c;
                    break;
                }
            
            }
        }
        if(move.equalsIgnoreCase("R")){ //this is to move right on the map
           location[row][col] = false;
           location[row][col+1] = true;
        }
        
        //move left
        if(move.equalsIgnoreCase("L")){
            location[row][col] = false;
            location[row][col-1] = true;
        }
        //move up
        if(move.equalsIgnoreCase("U")){
            location[row][col] = false;
            location[row-1][col] = true;
        }
        //move down
        if(move.equalsIgnoreCase("D")){
            location[row][col] = false;
            location[row+1][col] = true;
        }
        
        //prints map in the map room
        if(location[row][col] == location[0][0]){
        	System.out.println();
        }
    }//end of switchrooms method
    
    public int[] playerLocation(){
    	int[] xy = new int[2];
    	int row = 0,col = 0;
    	
    	for(int r = 0; r<location.length; r++){
            for(int c = 0; c<location[0].length; c++){
                if(location[r][c]){
                    row = r;
                    col = c;
                    break;
                }
            }
        }//end of nested for loops
    	
    	xy[0] = row;
    	xy[1] = col;
    	
    	return xy;
    }//end of playerLocation  
}