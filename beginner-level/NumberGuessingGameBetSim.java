/**
 *Simulation of the NumberGuessingGameBet class to confirm the expected winning of $0.2/game
 *
 *@author QuantumWalk
 *@version 1.0
 */

public final class NumberGuessingGameBetSim{
  int currentRun;
  final static int totalRun = 10000; //# simulation run, can be changed
  int totalWin;
  int answer, guess, payout, lowerRange, upperRange;
  
  /**
   * main method to control simulations, including managing the runs#, playing each game, and displaying results.
   */
  public void startSim(){
      this.currentRun = 0;
      this.totalWin = 0;
      
      while (currentRun < totalRun){
          currentRun++;
          playGame();
        }
      printResult();
    }
  
    /**
     * static method to instantiate an instance and calling its startSim().
     */
  public static void main(String[] args){
  NumberGuessingGameBetSim gameSim = new NumberGuessingGameBetSim();
  gameSim.startSim();
}

/**
 * simulate the playing of each game, by repeatedly guessing the midpoint of an adjusting range.
 * the range adjusts upward or downward, after receiving the feedback whether the guess was too high or low.
 * payout starts from $5 as defined and decrements by 1 after each guess.
 */
  private void playGame(){
      answer = (int)(Math.random()*100)+1; //generate the random number for this current game
      payout = 5; //initial payout amount, arbitrarily defined for the game rule
      lowerRange = 1;
      upperRange = 101;
      while(true){ //repeatedly generate guess at the midpoint of the range
        guess = ((upperRange - lowerRange)/2 + lowerRange);
        if (guess == answer){
          totalWin += payout;
          break;}
        else if (guess < answer){ //case when guess is low, so lowerRange revised up to the guess
          lowerRange = guess;
          payout--;}
        else {
          upperRange = guess; //case when guess is high, so upperRange revised down to the guess
          payout--;}
      }
    }
  
    /**
     * displaying the simulation results.
     */
  private void printResult(){
      System.out.println("After " + totalRun + " plays, the total P&L is $" + totalWin + ", averaging $"+ String.format("%.2f",(double)totalWin/totalRun)+" per play.");
    }
          
  }

        
      
      
    