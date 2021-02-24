# NimGame_project2
This project (Project 2), and the next one (Project 3), will continue with the same theme introduced in Project 1 - namely, an implementation of the game of Nim. Please refer to the Project A specification for a description of the game and its rules.

[Go to Project 1](https://github.com/ZavierYang/NimGame_project1)

## Key Knowledge Points Covered in Project 2:
1. Design of the class structure for the project requires the knowledge of UML diagrams.
2. Implementation requires understanding of Classes and Arrays **Cannot use Arraylist**

## Requirements
In this project, we introduce a third class NimGame. The game playing process is delegated from Nimsys  to NimGame. Since only one game will be active at any given time, only a single NimGame instance  is required at any time by Nimsys. Nimsys should also maintain a collection of players. Initially, this  collection will be empty - players will need to be added in order to play a game.

* NimGame need to havse some information (more information can added in order to finish project):
  1. The current stone count
  2. An upper bound on stone removal
  3. Two players

* NimPlayer need to havse some information (more information can added in order to finish project):
  1. A username
  2. A given name
  3. A family name
  4. Number of games played
  5. Number of games won

The system should allow players to be added. It should also allow for players to be deleted, or for their  details to be edited. Players should not be able to directly edit their game statistics, although they  should be able to reset them.

The system is a text based interactive program that reads and executes commands from standard input  (the keyboard) until an ‘exit’ command is issued, which will terminate the program. If a command  produces output, it should be printed to standard output (the terminal).

* Input syntax descriptions
  1. addplayer - Allows new players to be added to the game. If a player with the given **username** already exists, the system should indicate this.

      Syntax: 
    
          addplayer username,family_name,given_name
    
  2. removeplayer - Allows players to be removed from the game. The username of the player to be  removed is given as an argument to the command. If no username is given, the command should remove all players, but in this case, it should display a confirmation question first (ask whether user wants to delete all or not and then user need to input y or n). If a username for a non-existent player is given, the system should indicate that the player does not exist. 
    * remove one player
    
        Syntax: 
        
          removeplayer username
    
    * remove all player: enter removeplayer than display the second sentence.
    
        Syntax: 
        
           removeplayer
           Are you sure you want to remove all players? (y/n)
            
  3. editplayer - Allows player details to be edited. Note that the player’s username cannot be changed  after the player is created. If a username for a non-existent player is given, the system should  indicate that the player does not exist.

      Syntax: 
    
          editplayer username,new_family_name,new_given_name
   
  4. resetstats - Allows player statistics to be reset. The username of the player whose statistics are to  be reset is given as an argument to the command. If no username is given, the command should  reset all player statistics, but as with the ‘removeplayer’ command, a confirmation question should  be displayed in this case. If a username for a non-existent player is given, the system should indicate  that the player does not exist.
  
      Syntax: 
    
        resetstats username
    
  5. displayplayer - Displays player information. The username of the player whose information is to  be displayed is given as an argument to the command. If no username is given, the command  should display information for all players, ordered by username alphabetically. If a username for a  non-existent player is given, the system should indicate that the player does not exist, as illustrated  in the example execution. **Please note when displaying player, the sequence of syntax is** username,givenname,familyname,number of games played,number of games won.
 
      Syntax: 
    
          displayplayer username
    
  6. rankings - Outputs a list of player rankings. There are three columns displayed. The first column  displays percentage wins or winning ratio, the second column displays the number of games played,  and the final column shows the player’s full name, that is, first name followed by last name. This  command takes the sort order as an argument. The sort order is desc or descending by default.  That is, if no argument or desc is provided, the program should rank the players by the percentage  of games they have won in descending order, i.e., players with highest percentage wins should  be displayed first. If the user provides asc as an argument, the players should be ranked by the  percentage of games they have won in ascending order. Round the percentages to the nearest integer  value. However, you should use the exact values of winning ratios when comparing and sorting two  users’ winning ratios. Ties should be resolved by sorting on usernames alphabetically. Only the first 10 players should be displayed, if there are more than 10. The output should be formatted according to the example below. For the purposes of formatting the output, you may assume that no player has played more than 99 games. Note that the vertical lines need to be aligned, with a single space appearing on either side. This means that in the first column you **must** have 5 characters consisting of a number, ’%’, and spaces. The first column must be left-justified.
  
      Syntax: 
  
        rankings [asc|desc]
  
      Example Execution:
    
          100% | 03 games | XXXX XXXXXX
          75%  | 04 games | ZZZ ZZZZZ
          14%  | 07 games | YYY YYYYYY
    
  7. startgame - Creates and commences a game of Nim. The game’s rules, and the usernames of the two players, are provided as arguments. You may assume that the initial stones and upperbound arguments are **valid and correct**. However, if at least one (i.e. one or two) of the usernames doesn’t correspond to an actual player, the system should indicate this by the output “One of the players does not exist.”, and the game should not commence. Otherwise, the ‘startgame’ command will commence a game, i.e., after executing it, the system is in the game state. When a game is in progress, the system should proceed according to the game play mechanics discussed in Project A, i.e., players should, in an alternating fashion, be asked to enter the number of stones they would like to remove, with the game state being updated accordingly. In this project, bounds on stone removal should be enforced. That is, players should only be allowed to remove between 1 and N stones inclusive, where N is the upper bound or the number of stones remaining, whichever is smaller. Once all the stones are gone, a winner should be announced, and the statistics for the two players should be updated accordingly. The system should then return to the idle state, and a command prompt should be displayed again.
  
      Syntax: 
      
          startgame initialstones,upperbound,username1,username2
        
























