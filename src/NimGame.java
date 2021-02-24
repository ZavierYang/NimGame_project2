import java.util.Scanner;
public class NimGame {
        /*
        Variables
            playerTurn: Current player who pick stones.
            removeStone: The number of stones that player wants to remove.
        */    
        private NimPlayer playerTurn;
        private int removeStone;
        
        public void game(int initialStone, int upperBound,
                         NimPlayer NimPlayer1, NimPlayer NimPlayer2, Scanner keyboard){   
          
        System.out.printf("\nInitial stone count: %d\n", initialStone);
        System.out.printf("Maximum stone removal: %d\n", upperBound);
        System.out.printf("Player 1: %s %s\n", NimPlayer1.getGivenName(), 
                                              NimPlayer1.getFamilyName());
        System.out.printf("Player 2: %s %s\n", NimPlayer2.getGivenName(), 
                                              NimPlayer2.getFamilyName());

        //Initialize first player.
        this.playerTurn = NimPlayer1; 

        //The while loop control whether or not this turn is finish.
        while(0 < initialStone){
            System.out.printf("\n%d stones left:", initialStone);

            //Display stones quatity.
            for(int i = 0; i < initialStone; i++){
                System.out.print(" *");
            }

            System.out.printf("\n%s's turn - remove how many?\n", this.playerTurn.getGivenName());
            this.removeStone = this.playerTurn.removeStone(keyboard);
            
            //Check whether or not the input is valid.
            if((this.removeStone > upperBound || this.removeStone < 1) 
                                                                    && initialStone > upperBound){
                /*The first case is when total stones are more than upperBound. Additionally,  
                  the input of number is bigger than upperBound or smaller than 1.*/
                System.out.printf("\nInvalid move. You must remove "
                                 + "between 1 and %d stones.\n", upperBound);
            }else if((this.removeStone < 1 || this.removeStone > initialStone) 
                                                                    && initialStone < upperBound){
                /*The second case is when total stones are less than upperBound. Additionally,  
                  the input of number is bigger than initialStone or smaller than 1*/
                System.out.printf("\nInvalid move. You must remove "
                                 + "between 1 and %d stones.\n", initialStone);
            }else{
                //Calculate how many stones are left.
                initialStone = initialStone - this.removeStone; 
                //Change next turn player.
                this.playerTurn = (this.playerTurn.equals(NimPlayer1)) ? NimPlayer2 : NimPlayer1;
            }  
        }
        
        System.out.printf("\nGame Over\n%s %s wins!\n", this.playerTurn.getGivenName(),  
                                                      this.playerTurn.getFamilyName());
        
        //To catch the \n after keyboard.nextInt().
        keyboard.nextLine();
        
        //Increase players' statistics.
        this.playerTurn.setGamesWon(this.playerTurn.getGamesWon() + 1);
        NimPlayer1.setGamesPlayed(NimPlayer1.getGamesPlayed() + 1);
        NimPlayer2.setGamesPlayed(NimPlayer2.getGamesPlayed() + 1);
    }
}