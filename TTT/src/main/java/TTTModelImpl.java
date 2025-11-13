package model;

// Simple beginner-friendly Tic Tac Toe implementation
public class TTTModelImpl implements TTTModel {

  private Player[][] board;      // 3x3 board
  private Player currentPlayer;  // whose turn
  private Player winner;         // winner if any
  private boolean gameOver;      // true if game finished

  // Constructor
  public TTTModelImpl() {
    resetGame(); // start a new game
  }

  // Make a move at row,col
  @Override
  public void makeMove(int row, int col) {
    // check valid position
    if (row < 0 || row > 2 || col < 0 || col > 2) {
      throw new IllegalArgumentException("Row or column out of bounds");
    }
    // check if cell is empty
    if (board[row][col] != null) {
      throw new IllegalStateException("Cell already filled");
    }
    // check if game is already over
    if (gameOver) {
      throw new IllegalStateException("Game is over");
    }

    board[row][col] = currentPlayer; // place mark

    // check if this move wins the game
    if (checkWin(currentPlayer)) {
      winner = currentPlayer;
      gameOver = true;
    } else if (isBoardFull()) { // check draw
      winner = null;
      gameOver = true;
    } else {
      // switch turns
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

  // Check if game is over
  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  // Get winner
  @Override
  public Player getWinner() {
    return winner;
  }

  // Reset game to empty board
  @Override
  public void resetGame() {
    board = new Player[3][3]; // empty 3x3 board
    currentPlayer = Player.X;  // X starts
    winner = null;
    gameOver = false;
  }

  // ===== helper methods =====

  // Check if the board is full
  private boolean isBoardFull() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == null) return false;
      }
    }
    return true;
  }

  // Check if player p has won
  private boolean checkWin(Player p) {
    // rows
    for (int i = 0; i < 3; i++) {
      if (board[i][0] == p && board[i][1] == p && board[i][2] == p) return true;
    }
    // columns
    for (int j = 0; j < 3; j++) {
      if (board[0][j] == p && board[1][j] == p && board[2][j] == p) return true;
    }
    // diagonals
    if (board[0][0] == p && board[1][1] == p && board[2][2] == p) return true;
    if (board[0][2] == p && board[1][1] == p && board[2][0] == p) return true;

    return false;
  }
}
