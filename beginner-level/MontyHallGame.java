import java.util.*;

/**
 * The Monty Hall game, where the player trying to guess the door with the Ferarri behind among 3 doors.
 * After the player chooses a door, the host intentionally opens another door to show nothing behind.
 * The player can then decide to stay or switch to another (remaining) door.
 * The question is is it better for the player to switch or not. 
 * P(stay)=1/3 and P(switch)=2/3.
 * 
 * @author ABC
 * @version 1.0
 * @since 6/12/2019
 */

public final class MontyHallGame{
  
  short totalWin;
  short totalPlay;
  
  /**
   * static method to instantiate and start a game
   */
  public static void main(String[] args){
      MontyHallGame game = new MontyHallGame();
      game.startGame();
    }
    
    /**
     * displaying game introduction
     */
  private void printWelcome(){
    System.out.println("There are 3 doors, namely 1, 2, and 3.");
    System.out.println("Behind one of them is a Ferrari and pigs behind the other two.");
    System.out.println("You are free to pick one of the three doors for the Ferrari. Good luck!");
}

/**
 * displaying game results
 */
private void printResult(){
    System.out.println("You have won " + totalWin + " games out of the total of " + totalPlay + " games played.");
    System.out.println("Your win ratio is " + String.format("%.1f",(double)totalWin*100/totalPlay) +"%");    
    System.out.println("Thanks for playing. Have a fucking good day!");
}

/**
 * main method that manages a game series by counting plays, initiating games, asking if player likes to continue, and displaying results
 */    
public void startGame(){
    printWelcome();
    gameReset();
    
    do{
        totalPlay++;
        playGame();
    } while (playAgain());
    
    printResult();
}

/**
 * reseting game statistics to initial states
 */  
private void gameReset(){
    this.totalWin = 0;
    this.totalPlay = 0;
}
   
/**
 * asking player for cmd input if he wants to continue or quit. any input starting with a y is assumed to continue.
 */    
private boolean playAgain(){
    String playAgain;
    System.out.println("Play again?");
    Scanner input = new Scanner(System.in);
    playAgain = input.next().trim().toLowerCase();
    return (playAgain.startsWith("y"));
}

/**
 * creating and managing the flows of a game, including setting up doors, getting player's pick, showing an empty door, and comparing final choice
 */
private void playGame(){
    //setting up the doors and prize# using sets
    Set<Byte> doorSet = new HashSet<>(); //container for all doors
    Set<Byte> emptyDoorSet = new HashSet<>(); //container for empty doors
    doorSet.add((byte)1); //3 doors in this game
    doorSet.add((byte)2);
    doorSet.add((byte)3);
    byte prizeDoor = (byte)(Math.random()*3 + 1); //U[1 ,3] for the prize door
    emptyDoorSet.addAll(doorSet);
    emptyDoorSet.remove(prizeDoor);
    
    //getting player's door pick from cmd
    byte initialChoice;
    do{
        try{
        Scanner input = new Scanner(System.in);
        System.out.println("Which door (1, 2, 3) do you choose?");
        initialChoice = input.nextByte();
        }
    catch (InputMismatchException ex){
        System.out.println("Enter a valid door");
        initialChoice = -1; //assign an invalid value to the choice
        }
    }while(!doorSet.contains(initialChoice));

    //configuring which doors can be opened and the one to open
    emptyDoorSet.remove(initialChoice); //if initialChoice is the prize door then emptyDoorSet remains unchanged as 2 empty doors, else result becomes 1 empty door 
    ArrayList<Byte> openableDoors = new ArrayList<>(emptyDoorSet); //an openable door is one that is both empty and unchosen
    byte doorOpened = openableDoors.get((byte)(Math.random() * emptyDoorSet.size())); //randomize which openable door(s) to actually open
    System.out.println("I have opened door " + doorOpened + ". There is a pig.");
    
    //asking player if he will keep or switch and finalizing the choice
    byte finalChoice;
    do{
        System.out.println("Keeping or switching door?");
        Scanner input = new Scanner(System.in);
        String playerChoice = input.next().trim().toLowerCase();
        if (playerChoice.startsWith("k")){
            finalChoice = initialChoice;
        }
        else if (playerChoice.startsWith("s")){
        doorSet.remove(initialChoice);
        doorSet.remove(doorOpened);
        Iterator i = doorSet.iterator();
        finalChoice = (byte)i.next();
        assert (!i.hasNext());
        }
        else {
        System.out.println("I am not sure if you wanna keep or switch.");
        finalChoice = -1;}
    } while(finalChoice < 0);
    
    //checking if final choice is right or wrong
    if (finalChoice == prizeDoor){
        System.out.println("Congrats, the Ferarri is indeed behind door " + prizeDoor + "!");
        totalWin++;
    }
    else {
        System.out.println("Too bad man, the Ferarri is behind door " + prizeDoor);
    }
}

}  