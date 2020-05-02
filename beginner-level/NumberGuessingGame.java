import java.util.Scanner;

/**
 * Eck's book Chapter 4 PlayGame class, where a randomly-generated number of [1,100] is repeatedly guessed.
 * After each failed guess, player is told if guess was too low or high.
 * Player must guess within k attempts. 
 * 
 * @author ABC
 * @version 1.0
 * @since 2019-12-5
 */

public final class NumberGuessingGame{
  
  int totalPlay;
  int totalWin;
  final static byte guessPermitted = 6; //# of guess permitted per game, can be changed
  
  /**
   * static method to instantiate an instance and starting a game.
   */
  public static void main(String[] args){
  NumberGuessingGame game = new NumberGuessingGame();
  game.startGame();
  }
  
  /**
   * method to control a game, including displaying introduction, counting play#, playing, asking for continue/quit, and showing results
   */
  public void startGame(){
      this.totalWin = 0;
      this.totalPlay = 0;
      printWelcome();
      do {
          this.totalPlay++;
          play();
        } while(playAgain());
      printResult();
    }
      
   /**
    * displaying game introduction.
    */   
  private void printWelcome(){
    System.out.println("This game is very easy.");
    System.out.println("I will pick a number between 1 and 100, and you guess it.");
    System.out.println("If you don't get it within "+ guessPermitted + " times, you are a fucking loser.");
    System.out.println("Now, let's rock and roll baby!");
}

/**
 * displaying play result and comparing it to a simulated result.
 */
private void printResult(){
    NumberGuessingGameSim gameSim = new NumberGuessingGameSim();
    double expectedWinRatio = gameSim.startSim(guessPermitted);
    double actualWinRatio = (double)totalWin*100 / totalPlay;
    String performance = (actualWinRatio>expectedWinRatio) ? ("outperformed") : ("underperformed");
    System.out.println("You have won " + totalWin + " games out of the total of " + totalPlay + " games played.");
    System.out.println("Your win ratio is " + String.format("%.1f", actualWinRatio) +"%, which " + performance + " the average of "+expectedWinRatio+"%");    
    System.out.println("Thanks for playing. Have a fucking good day!");
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
 * the playing of a game, which begins from generating a random number and repeatedly asking for player's guess, 
 * until either the guess is right or rounds run out.
 */
  private void play(){
    int answer, guess, guessCount;
    answer = (int)(Math.random() * 100) + 1;
    System.out.println("I rolled my dice. See if you can get it");
    guessCount = 0;
    while(guessCount < guessPermitted){
      System.out.println("What's your guess?");
      Scanner guessScan = new Scanner(System.in);
      guess = guessScan.nextInt();
      
      if (guess == answer){
        System.out.println("Congrats, it is indeed " + answer + ". You got it in " + (guessCount+1) + " guesses.");
        break;
      }
      else if (guess < answer)
        System.out.println("Wrong, guess is too low.");
      else System.out.println("Wrong, guess is too high.");
      guessCount++;
    } 
    if (guessCount == 6) {
      System.out.println("Sorry, you lost. My number was " + answer + ".");
    }
    else {
      totalWin++;
    }
}

}


      
            