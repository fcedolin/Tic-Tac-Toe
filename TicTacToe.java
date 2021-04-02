class TicTacToe {
  static char max = 'X';
  static char min = 'O';
  public static void main(String[] args) {
    char[] state = {'O', 'X', 'O', 'O', '_', 'X', 'X', 'O', 'O'};
    printBoard(state);
    System.out.println(termTest(state));

  }

  public static int miniMax(char[] state, boolean maxPlayer){

    int finalStateResult = termTest(state);
    if(finalStateResult != -2)
      return finalStateResult;

    int maxEval = -2;
    if(maxPlayer){
      //max turn
    } else {
      //min turn
    }
  }

  /**
   * termTest determines if the state is a final state and return winner
   * @param state to be printed
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
    return 0; //draw

  }//termTest

  /**
   * termTest determines if the state is a final state and return winner
   * @param state to be printed
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
