public class BoardCheck {
  private Board board;
  private Cell[][] cells;
  private Cell[] winnerCells;

  public BoardCheck(Board board) {
    this.board = board;
    this.cells = board.getCells();
  }

  public boolean hasWon() {
    return (wonBy("rows") || wonBy("cols") || wonByDiagonal() || wonByInverseDiagonal());
  }

  public boolean wonBy(String by) {
    for (int row = 0; row < cells.length; row++) {
      int occurrences = 0;

      for (int col = 0; col < cells[row].length; col++) {
        if (by.equals("rows")) {
          if (cells[row][col].getPlayer() == board.getCurrentPlayer()) {
            occurrences++;
          }
        } else if (by.equals("cols")) {
          if (cells[col][row].getPlayer() == board.getCurrentPlayer()) {
            occurrences++;
          }
        }
      }

      if (occurrences == 3) {
        if (by.equals("rows")) {
          this.winnerCells = cells[row];
        } else if (by.equals("cols")) {
          this.winnerCells = new Cell[] { cells[0][row], cells[1][row], cells[2][row] };
        }

        return true;
      }
    }

    return false;
  }

  public boolean wonByDiagonal() {
    boolean firstCell = cells[0][0].getPlayer() == board.getCurrentPlayer();
    boolean secondCell = cells[1][1].getPlayer() == board.getCurrentPlayer();
    boolean thirdCell = cells[2][2].getPlayer() == board.getCurrentPlayer();

    if (firstCell && secondCell && thirdCell) {
      this.winnerCells = new Cell[] { cells[0][0], cells[1][1], cells[2][2] };
      return true;
    }

    return false;
  }

  public boolean wonByInverseDiagonal() {
    boolean firstCell = cells[0][2].getPlayer() == board.getCurrentPlayer();
    boolean secondCell = cells[1][1].getPlayer() == board.getCurrentPlayer();
    boolean thirdCell = cells[2][0].getPlayer() == board.getCurrentPlayer();

    if (firstCell && secondCell && thirdCell) {
      this.winnerCells = new Cell[] { cells[0][2], cells[1][1], cells[2][0] };
      return true;
    }

    return false;
  }

  public Cell[] getWinnerCells() {
    return this.winnerCells;
  }
}
