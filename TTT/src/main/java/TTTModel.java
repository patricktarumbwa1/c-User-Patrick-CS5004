package model;

// Tic Tac Toe interface
public interface TTTModel {

  // make a move at row,col
  void makeMove(int row, int col);

  // get the player at row,col
  Player getCell(int row, int col);

  // get current player
  Player getCurrentPlayer();

  // check if game is over
  boolean isGameOver();

  // get winner (null if draw or not over)
  Player getWinner();

  // reset the board
  void resetGame();
}
