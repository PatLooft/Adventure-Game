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
public class Riddles extends Enemy {
    private static String[] questions;
    private static String[] answers;
    private static String riddle, answer;
    
    public Riddles(String[] questions, String[] answers,int points, int lives){
        super(points, lives);
        
        Random rand = new Random();
        int r = rand.nextInt(10);
        
        String riddle = questions[r];
        String answer = answers[r];
    }
    
    public Riddles(){
    	super(0,0);
    	Random rand = new Random();
        int r = rand.nextInt(10);
        String[] questions = askQuestions();
        String[] answers = Answers();
        this.riddle = questions[r];
        this.answer = answers[r];
    }

    public static String[] getQuestions() {
        return questions;
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    public static String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
    
    public static String[] askQuestions(){
        String[] riddle = new String[10];
        riddle[0] = "What has a face and two hands but no arms or legs?";
        riddle[1] = "What is the easiest way to double your money?";
        riddle[2] = "What has a thumb and four fingers but is not alive?";
        riddle[3] = "What has to be broken before you can use it?";
        riddle[4] = "What has a neck but no head?";
        riddle[5] = "What gets wetter as it dries?";
        riddle[6] = "What goes up and doesn’t come back down?";
        riddle[7] = "What belongs to you but is used more by others?";
        riddle[8] = "Everyone has it and no one can lose it, what is it?";
        riddle[9] = "It’s been around for millions of years, but it’s no more than a month old. What is it?";
        
        return riddle;
        
    }
    public static String[] Answers(){
        String[] answers = new String[10];
        answers[0] = "A clock";
        answers[1] = "Put it in front of the mirror";
        answers[2] = "A glove";
        answers[3] = "An egg";
        answers[4] = "A bottle";
        answers[5] = "A towel";
        answers[6] = "Your age";
        answers[7] = "Your name";
        answers[8] = "A shadow";
        answers[9] = "The moon";
        
        return answers;
    }
    
    public String toString(){
    	return riddle;
    }
 
    public boolean checkAnswer(String response){//response is the users answer to the riddle,this checks to see if it is right
    	if(response.equalsIgnoreCase(answer))
    		return true;
    	else
    		return false;
    }
}