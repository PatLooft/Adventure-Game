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
public abstract class Enemy {
    
    private int points; //this holds the amount the player can hold or gain
    private int lives; //same for lives
  
    public Enemy(int points, int lives){
        this.points = points;
        this.lives = lives;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    public void changeLives(int lives, Player player){ //Gets current lives from the getLives method and adds it to the amount of lives that you are gaining or losing
        lives = player.getTotalLives() + lives;
        player.setTotalLives(lives);
    }
    
    public void changePoints(int points){
        points = getPoints() + points;
        setPoints(points);
    }
}