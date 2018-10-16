package auxiliar;

import models.TabuleiroModel;
import views.TabuleiroView;

public class VerificadorResultado {
  private Celula[][] celulas;
  private Celula[] winnerCells;
  private TabuleiroModel model;
  private TabuleiroView view;

  public VerificadorResultado(TabuleiroModel model) {
    this.model = model;
    this.celulas = model.getCelulas();
  }

  public boolean hasWon() {
    return (wonBy("rows") || wonBy("cols") || wonByDiagonal() || wonByInverseDiagonal());
  }

  public boolean wonBy(String by) {
    System.out.println(celulas.length);
    for (int row = 0; row < celulas.length; row++) {
      int occurrences = 0;

      for (int col = 0; col < celulas[row].length; col++) {
        if (by.equals("rows")) {
          if (celulas[row][col].getPlayer() == model.getJogadorAtual()) {
            occurrences++;
          }
        } else if (by.equals("cols")) {
          if (celulas[col][row].getPlayer() == model.getJogadorAtual()) {
            occurrences++;
          }
        }
      }

      if (occurrences == 3) {
        if (by.equals("rows")) {
          this.winnerCells = celulas[row];
        } else if (by.equals("cols")) {
          this.winnerCells = new Celula[] { celulas[0][row], celulas[1][row], celulas[2][row] };
        }

        return true;
      }
    }

    return false;
  }

  public boolean wonByDiagonal() {
    boolean firstCell = celulas[0][0].getPlayer() == model.getJogadorAtual();
    boolean secondCell = celulas[1][1].getPlayer() == model.getJogadorAtual();
    boolean thirdCell = celulas[2][2].getPlayer() == model.getJogadorAtual();

    if (firstCell && secondCell && thirdCell) {
      this.winnerCells = new Celula[] { celulas[0][0], celulas[1][1], celulas[2][2] };
      return true;
    }

    return false;
  }

  public boolean wonByInverseDiagonal() {
    boolean firstCell = celulas[0][2].getPlayer() == model.getJogadorAtual();
    boolean secondCell = celulas[1][1].getPlayer() == model.getJogadorAtual();
    boolean thirdCell = celulas[2][0].getPlayer() == model.getJogadorAtual();

    if (firstCell && secondCell && thirdCell) {
      this.winnerCells = new Celula[] { celulas[0][2], celulas[1][1], celulas[2][0] };
      return true;
    }

    return false;
  }

  public Celula[] getWinnerCells() {
    return this.winnerCells;
  }
}
