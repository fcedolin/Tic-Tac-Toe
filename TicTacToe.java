/**
 * class TicTacToe
 * TicTacToe implementation using miniMax algorithm
 *
 * @author Federico Cedolini
 * @version 1.0
 * @since 04/04/2021
 * Known issues: none
 * To be implemented:
 *    Low priority: GUI
 */

import java.util.*;
import java.lang.*;

class TicTacToe {
  static char max;
  static char min;

  public static void main(String[] args) {
    int winner = -2;
    char[] state = {'_', '_', '_', '_', '_', '_', '_', '_', '_'};
    Scanner sc = new Scanner(System.in);
    //initial setup
    boolean aiTurn = welcomeSetup(sc);
    //play game
    while(winner == -2){
      if(aiTurn){
        nextMove(state);
        aiTurn = false;
      }else{
        userMove(state, sc);
        aiTurn = true;
      }
      winner = termTest(state);
    }
    //determine winner
    switch(winner){
      case 1:
        System.out.printf("%c is the winner\n", max);
        break;
      case -1:
        System.out.printf("%c is the winner\n", min);
        break;
      default:
        System.out.println("It is a tie!");
    }
  }//main

  /**
   * welcomeSetup asks user if they want to go first and updates variable accordingly
   * @param sc System Scanner
   * @return boolean true if ai first, false otherwise
   */
  public static boolean welcomeSetup(Scanner sc){
    System.out.println("Welcome to TicTacToe!");
    System.out.println("Do you want to be X or O? X goes first.");
    min = Character.toUpperCase(sc.next().charAt(0));
    if(min == 'X'){
      max = 'O';
      return false;
    }else{
      max = 'X';
      return true;
    }
  }//welcomeSetup

  /**
   * userMove gets user input and updates state accordingly
   * @param state is the current state of the board
   * @param sc System Scanner
   */
  public static void userMove(char[] state, Scanner sc){
    System.out.println("Make your move (row-major order):");
    int userNext = sc.nextInt()-1;
    while(state[userNext] != '_'){
      System.out.println("Invalid selection. Please select an empty location:");
      userNext = sc.nextInt()-1;
    }
    state[userNext] = min;
    printBoard(state);
  }//userMove

  /**
   * nextMove calls miniMax to determine best next move to follow
   * @param state is the current state of the board
   */
  public static void nextMove(char[] state){
    int next = -1;
    int eval;
    int maxEval = -2;
    for(int i = 0; i < 9; i++){
      if(state[i] == '_'){
        state[i] = max;
        eval = miniMax(state, false);
        if(eval > maxEval){
          maxEval = eval;
          next = i;
        }
        state[i] = '_';
      }
  }
  state[next] = max;
  System.out.println("My move is:");
  printBoard(state);
}//nextMove

/**
 * miniMax performs miniMax algorithm to find best movement to follow
 * @param state is the current state of the board
 * @param maxPlayer true if max's turns, false otherwise
 * @return result from evaluation function termTest
 */
  public static int miniMax(char[] state, boolean maxPlayer){
    int eval;
    int finalStateResult = termTest(state);
    if(finalStateResult != -2)
      return finalStateResult;

    if(maxPlayer){ //max turn
      int maxEval = -2;
      for(int i = 0; i < 9; i++){
        if(state[i] == '_'){
          state[i] = max;
          eval = miniMax(state, false);
          maxEval = Math.max(eval, maxEval);
          state[i] = '_';
        }
      }
      return maxEval;
    } else { //min turn
      int minEval = 2;
      for(int i = 0; i < 9; i++){
        if(state[i] == '_'){
          state[i] = min;
          eval = miniMax(state, true);
          minEval = Math.min(eval, minEval);
          state[i] = '_';
        }
      }
      return minEval;
    }
  }//miniMax

  /**
   * termTest determines if the state is a final state and return winner
   * @param state to be printed
   * @return evaluation of current state, -1 loss, 1 win, 0 tie, -2 no final state
   */
  public static int termTest(char[] state){

    //horizontal evaluation
    if(state[0]!= '_' && state[0] == state[1] && state[1] == state[2])
      return getWinner(state[2]);

    if(state[3]!= '_' && state[3] == state[4] && state[4] == state[5])
      return getWinner(state[5]);

    if(state[6]!= '_' && state[6] == state[7] && state[7] == state[8])
      return getWinner(state[8]);

    //vertical evaluation
    if(state[0]!= '_' && state[0] == state[3] && state[3] == state[6])
      return getWinner(state[6]);

    if(state[1]!= '_' && state[1] == state[4] && state[4] == state[7])
      return getWinner(state[7]);

    if(state[2]!= '_' && state[2] == state[5] && state[5] == state[8])
      return getWinner(state[8]);

    //diagonal evaluation
    if(state[0]!= '_' && state[0] == state[4] && state[4] == state[8])
      return getWinner(state[8]);

    if(state[6]!= '_' && state[6] == state[4] && state[4] == state[2])
      return getWinner(state[2]);

    //search for empty location
    for(char c : state){
      if(c == '_')
        return -2; //no final state - replace for constant
    }
    return 0; //tie
  }//termTest

  /**
   * getWinner determines the winner based on the char passed to it
   * @param c is the char of the state winner
   * @return winner of the state based on the character
   */
  public static int getWinner(char c){
    if(c == max)
      return 1;
    return -1;
  }//getWinner


/**
 * printBoard prints individual states
 * @param state to be printed
 */
  public static void printBoard(char[] state){
    for(int i = 0; i < 9; i++){
      if(i % 3 == 0)
        System.out.println("");
      System.out.printf("%c ", state[i]);
    }
    System.out.println("\n");
  }//printBoard

}
