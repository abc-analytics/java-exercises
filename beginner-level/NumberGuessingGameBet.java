import java.util.Scanner;

/**
 * A variation of the [1, 100] number-guessing game, used by Microsoft Steve Ballmer in interviews. 
 * The house pays $5, $4, $3, $2, $1, $0 for 1st, 2nd, 3nd, 4th, 5th, 6th guess, then player pays the house $1, $2...for 7th, 8th guess.
 * Recall for 100 numbers, a binary-search strategy will nail the number by 8th guess, (2^(8-1)/100)>100.
 * The expected return is about $0.2 if the number is indeed uniformly picked.
 * 
 * @author QuantumWalk
 * @version 1.0
 * @since 05/12/2019
 */

public final class NumberGuessingGameBet{
  int totalWin; //counter for total winnings
  int totalPlay; //counter for total #game played
  
  /**
   * empty/redundant constructor
   */
  public NumberGuessingGameBet(){
    }
  
    /**
     * static method to instantiate an instance and calling its startGame()
     */
  public static void main(String[] args){
    NumberGuessingGameBet game = new NumberGuessingGameBet();
    game.startGame();
  }
  
  /**
   * Starting a game series. Player asked for continue or stop playing after each game. Game series stops when player decides to quit.
   * Play statistics such as winnings, # plays are displayed at the end.
   */
  public void startGame(){
      this.totalWin = 0;
      this.totalPlay = 0;
      printWelcome();
      do{
          this.totalPlay++;
          play();
        } while(playAgain());
      printResult();
    }
          
  /**
   * Asking player if he wants to continue or quit.
   * Use the first letter of the input to decide if he wants to play or stop, any word starts with y is assumed to continue, else stop.
   * @return player's decision to continue or quit playing
   */
  private boolean playAgain(){
      String playAgain;
      System.out.println("Play again?");
      Scanner scanner = new Scanner(System.in);
      playAgain = scanner.next().trim().toLowerCase();
      return playAgain.startsWith("y");
    }
   
  /**
   * Beginning to play a game. Each game is based on one random [1 ,100] number.
   * A game continues until the player guesses right, the corresponding payout is then incremented to total winnings.
   */
  private void play(){
    int answer, guess;
    int payout = 5;
    answer = (int)(Math.random() * 100) + 1;
    System.out.println("Okay, bring it on");
    while(true){
      Scanner guessScan = new Scanner(System.in);
      System.out.println("What's your guess?");
      guess = guessScan.nextInt();
      if (guess == answer){
        System.out.println("yah, "+answer+" you got it.");
        break;
      }
      else if (guess < answer)
        System.out.println("Wrong, guess is too low.");
      else System.out.println("Wrong, guess is too high.");
      payout--;
    }
    System.out.println("The PL is "+payout);
    totalWin += payout;
  }
  
  /**
   * printing a welcome message on the console
   */
  private void printWelcome(){
      System.out.println("Thanks for interviewing at Microsoft. My name is Steve Ballmer."+"\n"+"I pick a number between 1 and 100 and you guess it."+"\n"+"I will tell you too high or too low after each wrong guess."+"\n"+"The payout is accordingly $5, $4, $3, $2, $1, 0, -$1, -$2...easy money.");
    }
  
  /**
   * printing play statistics on the console
   */
  private void printResult(){
    System.out.println("Your P&L is $" + totalWin + " after " + totalPlay + " rounds played.");
    System.out.println("Average P&L is $ " + String.format("%.2f",((double)totalWin/totalPlay)) + ", against the expected value of +$0.2");
    }
}

      