package model;

// Simple Tic Tac Toe implementation
public class TTTModelImpl implements TTTModel {

  private Player[][] board;      // 3x3 board
  private Player currentPlayer;  // whose turn
  private Player winner;         // winner
  private boolean gameOver;      // true if game finished

  // Constructor: start a new game
  public TTTModelImpl() {
    resetGame();
  }

  // Make a move at row,col
  @Override
  public void makeMove(int row, int col) {
    if (row < 0 || row > 2 || col < 0 || col > 2)
      throw new IllegalArgumentException("Out of bounds");
    if (board[row][col] != null)
      throw new IllegalStateException("Cell already filled");
    if (gameOver)
      throw new IllegalStateException("Game is over");

    board[row][col] = currentPlayer; // put mark

    // Check if current player wins
    if (checkWin(currentPlayer)) {
      winner = currentPlayer;
      gameOver = true;
    } else if (isBoardFull()) { // check draw
      winner = null;
      gameOver = true;
    } else {
      // Switch turns
      currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }
  }

  // Get player at row,col
  @Override
  public Player getCell(int row, int col) {
    return board[row][col];
  }

  // Get current player
  @Override
  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  // Check if game over
  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  // Get winner
  @Override
  public Player getWinner() {
    return winner;
  }

  // Reset the game
  @Override
  public void resetGame() {
    board = new Player[3][3]; // empty board
    currentPlayer = Player.X;  // X starts
    winner = null;
    gameOver = false;
  }

  // ===== helper methods =====

  // Check if board is full
  private boolean isBoardFull() {
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        if (board[i][j] == null) return false;
    return true;
  }

  // Check if player p has won
  private boolean checkWin(Player p) {
    // rows
    for (int i = 0; i < 3; i++)
      if (board[i][0] == p && board[i][1] == p && board[i][2] == p) return true;
    // columns
    for (int j = 0; j < 3; j++)
      if (board[0][j] == p && board[1][j] == p && board[2][j] == p) return true;
    // diagonals
    if (board[0][0] == p && board[1][1] == p && board[2][2] == p) return true;
    if (board[0][2] == p && board[1][1] == p && board[2][0] == p) return true;

    return false;
  }
}
