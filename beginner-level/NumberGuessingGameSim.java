import java.util.Scanner;

/**
 * Class to simulate the number-guessing game of [1, 100] by using a binary-search strategy, in which the middle number of the remaining feasible range is chosen in each round.
* Success/failure for each play is accumulated and frequency displayed at the end.
* The probability of winning at ith guess P(i) is 2^(i-1)/100 and probability of winning by ith guess is simply summation of P(0) to P(i).
* 
* @author ABC
* @version 1.0
* @since 5/12/2019
*/

public final class NumberGuessingGameSim{
  
  int totalWin;
  int currentRun;
  final static int totalRun = 100000; //simulation #, can be changed
  byte guessPermitted; //# of guesses allowed per game
  
  /**
   * static method to instantiate an instance to start a sim.
   */
  public static void main(String[] args){
      NumberGuessingGameSim gameSim = new NumberGuessingGameSim();
      gameSim.startSim();
  }
  
  /**
   * method managing the simulation, including counting the sim runs, asking user for guess# input, and simulating the game.
   */
  public void startSim(){
      reset();
      this.guessPermitted = getGuessPermitted();
      
      while (currentRun < totalRun){
          currentRun++;
          playGame(guessPermitted);
        }
          
      printResult();
    }
  
    /**
     * overloaded simulation method to work with method call rather than cmd I/O.
     * @param guessPermittedInput # guesses allowed per game
     * @return simulated win ratio based on # of guesses allowed
     */
  public double startSim(byte guessPermittedInput){
      reset();
      this.guessPermitted = guessPermittedInput;
      
      while (currentRun < totalRun){
          currentRun++;
          playGame(guessPermitted);
        }
      
      return (((double)totalWin*100) / totalRun);
    }
    
    /**
     * asking user for guessPermitted input in the cmd.
     * @return # of guesses allowed.
     */
  private byte getGuessPermitted(){
      System.out.println("Enter the max # of guesses allowed per game");
      Scanner input = new Scanner(System.in);
      return input.nextByte();
    }
  
    /**
     * calculating the win raito in this game and displaying it.
     */
  private void printResult(){
      double winRatio;
      winRatio = ((double)totalWin*100) / totalRun;
      System.out.println("win ratio is " + winRatio + "%");
    }
    
  /**
   * Simulate the play of a number-guessing game where player can keep guessing until reaching # of guess permitted.
   * Player starts at 50 then keeps narrowing the range and guessing the middle.
   * @param guessPermitted the # of guesses permitted in each game.
   */  
  private void playGame(byte guessPermitted){
    int answer, guess, guessCount;
    int lowerRange, upperRange;
    answer = (int)(Math.random()*100) + 1;
    guessCount = 0;
    lowerRange = 1;
    upperRange = 101;
    while (guessCount < guessPermitted){
      guess = (upperRange - lowerRange)/2 + lowerRange;
      
      if (guess == answer){
        totalWin++;
        break;
      }
      else if (guess < answer){
        lowerRange = guess;
        guessCount++;
      }
      else {
        upperRange = guess;
        guessCount++;
      }
    }
  }
  
  /**
   * resetting instance variables to initial states.
   */
  private void reset(){
      this.currentRun = 0;
      this.totalWin = 0;
    }
    
}
      