/*********************************************************************
 *  Author:       Zavier Yang                                        *
 *  Description:  This algorithm is created for Nim game             *
 *                                                                   *
 *  Written:      17/04/2019                                         *
 *  Update:       28/04/2019                                         *
 *                                                                   *
 ********************************************************************/

import java.util.*;

public class Nimsys {
    
    public static void main(String [] args){
        /*
        Variables
            playerindex: The index in players array where the target player is.
            player1Check, player2CheckCheck: whether or not two players who want to play game are
                                             exist.
            players: This is an array which store all players.
            nimsys: Object of Nimsys in order to call function by object oriented.
            nimGame: Instance of Nim game.
            input: The whole string of the input.
            command: An array. After input separated by " ", put into command array.
                     Therefore, the operations(add player, remove player) and the input infomations
                     (player's name, displayer order) will be separated.
            nameInfo: An array which store the player's name from command[1](input infomations) and 
                      separated by ",".
            gameInfo:An array which game's information from command[1](input infomations) and 
                      separated by ",".
        */
        
        Scanner keyboard = new Scanner(System.in);
        int playerindex, player1Check, player2Check;
        NimPlayer [] players = new NimPlayer[0];
        Nimsys nimsys = new Nimsys();
        NimGame nimGame;
        String input;
        String [] command, nameInfo, gameInfo;
        boolean isLowerCase;
        
        System.out.println("Welcome to Nim");
        
        while (true) {            
            System.out.print("\n$");
            input = keyboard.nextLine();
            /*After this, command[0] is operation and command[1] will be the information 
              which want to input.*/
            command = input.split(" ");

            /*Because operation is all in lower case. Therefore, program need to 
              check is it a vaild operation.*/
            isLowerCase = true;
            for(int i = 0; i < command[0].length(); i++){
                if (!Character.isLowerCase(command[0].charAt(i))) {
                    isLowerCase = false;
                }
            }

            if(isLowerCase) {
                //Whether or not the operation is to start a game.
                if(command[0].equals("startgame")){
                    gameInfo = command[1].split(",");

                    //Check whether or not two players exist.
                    player1Check = nimsys.playerCheck(players, gameInfo[2]);
                    player2Check = nimsys.playerCheck(players, gameInfo[3]);

                    if(player1Check == -1 || player2Check == -1){
                        System.out.println("One of the players does not exist.");
                    }else{
                        nimGame = new NimGame();
                        nimGame.game(Integer.parseInt(gameInfo[0]), Integer.parseInt(gameInfo[1]), 
                                     players[player1Check], players[player2Check], keyboard);
                    }
                    
                }else{
                    /*If the length of command is 1, it means people want to operate whole data. So 
                      nameInfo will be null.
                      If not, it means that people want to operate specific data and nameInfo 
                      should store username,family_name,given_name from command[1].*/
                    if(command.length != 1){
                        nameInfo = command[1].split(",");

                    }else{
                        nameInfo = null;
                    }

                    /*If the lengths of players and command are 1 and operation is not rankings.
                      command[1] might be player's name. Therefore, use playerCheck function.
                      If the lengths of player is 0, set playerindex to -1 because it means no 
                      players.
                      If not previous two situations, it means the operation is for all players. 
                      Therefore, playerindex won't be important.
                    */
                    if(players.length != 0 && command.length != 1 
                                           && !command[0].equals("rankings")){

                        playerindex = nimsys.playerCheck(players, nameInfo[0]);
                    }else if(players.length == 0){
                        playerindex = -1;
                    }else{
                        playerindex = players.length;
                    }

                    if(command[0].equals("addplayer") && playerindex != -1){
                        /*If playerindex is not -1, it means player already exists. 
                          Therefore, don't add it.*/
                        System.out.println("The player already exists.");

                    }else if(command[0].equals("addplayer") && playerindex == -1){
                        /*If playerindex is -1, it means player not exists. 
                          Therefore, player can be added.*/
                        players = nimsys.add(players);
                        players[players.length - 1] = new NimPlayer(nameInfo[0], 
                                                                    nameInfo[1], 
                                                                    nameInfo[2]);

                    }else if(!command[0].equals("addplayer") && playerindex == -1 
                                                             && command.length != 1){
                       /*If playerindex is -1 and operation is not addplayer. 
                         The operation cannot be executed, because player does not exist.
                         But if the operation is for all player, do not execute this sentence.*/
                        System.out.println("The player does not exist.");

                    }else if(command[0].equals("removeplayer") ){
                        /*If the length of command is 1, remove all players. 
                          If not, remove the specific data.*/
                        if(command.length == 1){
                            System.out.println("Are you sure you want to remove " + 
                                               "all players? (y/n)");
                            
                            if(keyboard.next().equalsIgnoreCase("y")){
                                //To catch the \n after keyboard.next().
                                keyboard.nextLine();
                                
                                players = new NimPlayer[0];
                            }
                        }else{      
                            players = nimsys.delete(players, playerindex);
                        }

                    }else if(command[0].equals("editplayer") ){
                        //Edit a player's new information.
                        players[playerindex].setFamilyName(nameInfo[1]);
                        players[playerindex].setGivenName(nameInfo[2]);
                        
                    }else if(command[0].equals("resetstats") ){
                        /*If the length of command is 1, reset all players' statistics. 
                          If not, reset the specific player's statistics.*/
                        if(command.length == 1){
                            System.out.println("Are you sure you want to reset " +
                                              "all player statistics? (y/n)");

                            if(keyboard.next().equalsIgnoreCase("y")){
                                //To catch the \n after keyboard.next().
                                keyboard.nextLine();
                                
                                for(NimPlayer player : players){
                                    player.reset();
                                }
                            }
                        }else{
                            players[playerindex].reset(); 
                        }

                    }else if(command[0].equals("displayplayer")){
                        /*If the length of command is 1, display all players' information. 
                          If not, display the specific player's information.*/
                        nimsys.sortName(players);
                        
                        if(command.length == 1){
                            for(int i = players.length-1 ; i >= 0; i--){
                                System.out.println(players[i].toString());
                            }
                        }else{
                            System.out.println(players[playerindex].toString());
                        }
                    }else if(command[0].equals("rankings")){ 
                        /*Use sort function to sort players.
                          If the length of command is 1 or command[1] is desc, sort in descending 
                          If others, sort in ascending.*/
                        nimsys.sortName(players);
                        
                        if(command.length == 1){
                            nimsys.sortWin(players, "desc");
                        }else{
                            nimsys.sortWin(players, command[1]);
                        }
                        
                        //If players are more than 10, only displayer the top 10 players
                        for(int i = 0; i < 10; i++){
                            System.out.printf("%-4s | %02d games | %s %s\n", 
                                              Math.round((float)players[i].getGamesWon() / 
                                                        (float)players[i].getGamesPlayed()*100)+"%",
                                              players[i].getGamesPlayed(),
                                              players[i].getGivenName(),
                                              players[i].getFamilyName());
                            
                            //If players are less than 10, display the same number of players
                            if(i == players.length - 1){
                                break;
                            }
                        }   
                    }else if(command[0].equals("exit")){
                        System.out.println("");
                        keyboard.close();
                        System.exit(0);
                    }
                }
            }
        }
    }
    
    /*playerCheck function is to check whether or not is the player exist. 
      If find it, return index.
      if not, return -1 means does not find it.*/
    public int playerCheck(NimPlayer[] players, String name){
        int index = 0;
        
        for (NimPlayer player : players){
            if (name.equals(player.getUserName())) {
                return index;
            }
            index++;
        } 
        return -1;
    }
    
    //add and delete function is to dynamically control array length.
    public NimPlayer[] add(NimPlayer[] array){
        NimPlayer[] updatePlayer;
        
        if(array.length == 0){
            updatePlayer = new NimPlayer[array.length + 1];
            
        }else{
            updatePlayer = new NimPlayer[array.length + 1];
            System.arraycopy(array, 0, updatePlayer, 0, array.length);
        }
        
        return updatePlayer;
    }
    
    public NimPlayer[] delete(NimPlayer[] array, int index){
        NimPlayer[] updatePlayer = new NimPlayer[array.length - 1];
        
        System.arraycopy(array, 0, updatePlayer, 0, index);
        System.arraycopy(array, index + 1, updatePlayer, index, array.length - 1 - index);
        
        return updatePlayer;
    }
    
    //Sort players alphabetically
    public void sortName(NimPlayer[] array){
        Arrays.sort(array, new Comparator<NimPlayer>() { 
            @Override
            public int compare(NimPlayer player1,  NimPlayer player2) { 
                if(player1.getUserName().compareTo(player2.getUserName()) < 0){
                    return 1;
                }else{
                    return -1;
                }
            } 
        }); 
    }
    
    //Sort players by win percentage  
    public void sortWin(NimPlayer[] array, String way){
        Arrays.sort(array, new Comparator<NimPlayer>() { 
            
            @Override
            public int compare(NimPlayer player1,  NimPlayer player2) { 
                if(way.equals("desc")){
                    if (((float) player1.getGamesWon() / (float)player1.getGamesPlayed()* 100)  
                        < ((float) player2.getGamesWon() / (float)player2.getGamesPlayed()* 100)) 
                        return 1; 
                    else
                        return -1; 
                }else{
                    if (((float) player1.getGamesWon() / (float)player1.getGamesPlayed()* 100)  
                        > ((float) player2.getGamesWon() / (float)player2.getGamesPlayed()* 100)) 
                        return 1; 
                    else
                        return -1; 
                } 
            } 
        });  
    }
}