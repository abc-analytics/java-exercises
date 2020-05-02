import java.util.*;

/**
 * To simulate plays of a 3-door Monty Hall game and record results of both switch & no-switch choices.
 * Theoretical frequencies of switch & no-switch are 2/3 and 1/3.
 * 
 * @author ABC
 * @version 1.0
 * @since 6/12/2019
 */

public final class MontyHallGameSim{
  
  final static int totalPlay = 10000; //simulation runs, can be changed
  int totalSwitchWin;
  int totalKeepWin;
  
  /**
   * static method for instantiating an instance and calling gameSim().
   */
  public static void main(String[] args){
      MontyHallGameSim simulator = new MontyHallGameSim();
      simulator.gameSim();
    }
    
  /**
   * game simulator, for repeatedly initiating a game until reaching #simulation
   */
  public void gameSim(){
      totalSwitchWin = 0;
      totalKeepWin = 0;
      
      for (int iplay = 0; iplay < totalPlay; iplay++){
          playGame();
        }
        printResults();
    }
  
    /**
     * displaying results at the end
     */
    private void printResults(){
        System.out.println("Out of total of " + totalPlay + " games, stay(no-switch) strategy won " + totalKeepWin + " times and switch strategy won " + totalSwitchWin + " times.");
        System.out.println("Win ratios are respectively " + String.format("%.2f",(double)(totalKeepWin*100)/totalPlay) + "% and " + String.format("%.2f",(double)(totalSwitchWin*100)/totalPlay)+ "%.");    
    }
    
    /**
     * emulating each play of a Monty Hall game for both keep (no-switch) and switch plays
     */
    private void playGame(){
        //setting up doors
        Set<Byte> doorSet = new HashSet<>(); //container for all doors
        Set<Byte> emptyDoorSet = new HashSet<>(); //container for empty doors
        doorSet.add((byte)1); //3 doors in this game
        doorSet.add((byte)2);
        doorSet.add((byte)3);
        byte prizeDoor = (byte)(Math.random()*3 + 1); //U[1 ,3] for the prize door
        emptyDoorSet.addAll(doorSet);
        emptyDoorSet.remove(prizeDoor);
        
        //asking for player choice
        byte initialChoice = (byte)(Math.random() * 3 + 1);
        
        //configuring the empty door to open
        emptyDoorSet.remove(initialChoice); //if initialChoice is the prize door then emptyDoorSet remains unchanged as 2 empty doors, else result becomes 1 empty door 
        ArrayList<Byte> openableDoors = new ArrayList<>(emptyDoorSet); //an openable door is one that is both empty and unchosen
        byte doorOpened = openableDoors.get((byte)(Math.random() * emptyDoorSet.size())); //randomize which openable door(s) to actually open
        
        //assigning final choice values
        byte finalKeepChoice = initialChoice;
        byte finalSwitchChoice;
        doorSet.remove(initialChoice);
        doorSet.remove(doorOpened);
        Iterator i = doorSet.iterator();
        finalSwitchChoice = (byte)(i.next());
        
        //checking choices against right door
        if (finalKeepChoice == prizeDoor){
            totalKeepWin++;
        }
        else if (finalSwitchChoice == prizeDoor){
            totalSwitchWin++;
        }
    }
           
}  