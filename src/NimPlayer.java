import java.util.Scanner;

//Create players.
public class NimPlayer {
    private String userName;
    private String givenName;
    private String familyName;
    private int gamesPlayed;
    private int gamesWon;
    
    //Initialize player's information.
    public NimPlayer(String userName, String familyName, String givenName){
        this.userName = userName;
        this.familyName = familyName;
        this.givenName = givenName;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getGivenName() {
        return this.givenName;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public int getGamesPlayed() {
        return this.gamesPlayed;
    }

    public int getGamesWon() {
        return this.gamesWon;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    //Reset player's statistics.
    public void reset(){
        this.gamesPlayed = 0;
        this.gamesWon = 0;
    }
    
    //Return how many stones do player want to reduce.
    public int removeStone(Scanner input){
        return input.nextInt();
    }
      
    //Override equals function to compare with current player.
    public boolean equals(NimPlayer player) {
        return this == player;
    } 
    
    public String toString(){                               
        return (this.getUserName() + "," + this.getGivenName()+ "," + this.getFamilyName()+ ","+
                this.getGamesPlayed()+ " games," + this.getGamesWon()+ " wins");
    }
}