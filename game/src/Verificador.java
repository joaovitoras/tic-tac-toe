package src;

import models.JogadorModel;
import models.TabuleiroModel;

public class Verificador {
  private Celula[][] celulas;
  private Celula[] winnerCells;
  private TabuleiroModel model;
  private Celula qualFalta;

  public Verificador(TabuleiroModel model) {
    this.model = model;
    this.celulas = model.getCelulas();
  }

  public boolean hasWon() {
    return (wonBy("linhas") || wonBy("colunas") || wonByDiagonal() || wonByInverseDiagonal());
  }

  private boolean wonBy(String by) {
    for (int row = 0; row < celulas.length; row++) {
      int occurrences = 0;

      for (int col = 0; col < celulas[row].length; col++) {
        if (by.equals("linhas")) {
          if (celulas[row][col].getJogador() == model.getJogadorAtual()) {
            occurrences++;
          }
        } else if (by.equals("colunas")) {
          if (celulas[col][row].getJogador() == model.getJogadorAtual()) {
            occurrences++;
          }
        }
      }

      if (occurrences == 3) {
        if (by.equals("linhas")) {
          this.winnerCells = celulas[row];
        } else if (by.equals("colunas")) {
          this.winnerCells = new Celula[] { celulas[0][row], celulas[1][row], celulas[2][row] };
        }

        return true;
      }
    }

    return false;
  }

  private boolean wonByDiagonal() {
    boolean firstCell = celulas[0][0].getJogador() == model.getJogadorAtual();
    boolean secondCell = celulas[1][1].getJogador() == model.getJogadorAtual();
    boolean thirdCell = celulas[2][2].getJogador() == model.getJogadorAtual();

    if (firstCell && secondCell && thirdCell) {
      this.winnerCells = new Celula[] { celulas[0][0], celulas[1][1], celulas[2][2] };
      return true;
    }

    return false;
  }

  private boolean wonByInverseDiagonal() {
    boolean firstCell = celulas[0][2].getJogador() == model.getJogadorAtual();
    boolean secondCell = celulas[1][1].getJogador() == model.getJogadorAtual();
    boolean thirdCell = celulas[2][0].getJogador() == model.getJogadorAtual();

    if (firstCell && secondCell && thirdCell) {
      this.winnerCells = new Celula[] { celulas[0][2], celulas[1][1], celulas[2][0] };
      return true;
    }

    return false;
  }

  public Celula[] getWinnerCells() {
    return this.winnerCells;
  }

  public boolean faltaUm() {
    JogadorModel jogadorRival = jogadorRival();
    return (
      faltaUmPor("linhas", model.getJogadorAtual()) || 
      faltaUmPor("colunas", model.getJogadorAtual()) || 
      faltaUmDiagonal(model.getJogadorAtual()) || 
      faltaUmDiagonalInversa(model.getJogadorAtual()) ||
      faltaUmPor("linhas", jogadorRival) || 
      faltaUmPor("colunas", jogadorRival) || 
      faltaUmDiagonal(jogadorRival) || 
      faltaUmDiagonalInversa(jogadorRival)
    );
  }

  private boolean faltaUmPor(String by, JogadorModel jogadorModel) {
    for (int row = 0; row < celulas.length; row++) {
      int ocorrenciasJogador = 0;
      Celula vazia = null;

      for (int col = 0; col < celulas[row].length; col++) {
        int linha = 0;
        int coluna = 0;
        
        if (by.equals("linhas")) {
          linha = row;
          coluna = col;
        } else if (by.equals("colunas")) {
          linha = col;
          coluna = row;
        }
        
        if (celulas[linha][coluna].getJogador() == jogadorModel) {
          ocorrenciasJogador++;
        } else if (celulas[linha][coluna].vazia()) {
          vazia = celulas[linha][coluna];
        }
      }
      
      if (ocorrenciasJogador == 2 && vazia != null) {
        this.qualFalta = vazia;
        return true;
      }
    }

    return false;
  }

  public boolean faltaUmDiagonalInversa(JogadorModel jogadorModel) {
    Celula L0C2 = celulas[0][2];
    Celula L1C1 = celulas[1][1];
    Celula L2C0 = celulas[2][0];
    JogadorModel jogadorL0C2 = L0C2.getJogador();
    JogadorModel jogadorL1C1 = L1C1.getJogador();
    JogadorModel jogadorL2C0 = L2C0.getJogador();

    if (jogadorModel == jogadorL0C2 && jogadorModel == jogadorL1C1 && jogadorL2C0 == null) {
      this.qualFalta = L2C0;
      return true;
    }

    if (jogadorModel == jogadorL0C2 && jogadorModel == jogadorL2C0 && jogadorL1C1 == null) {
      this.qualFalta = L1C1;
      return true;
    }

    if (jogadorModel == jogadorL1C1 && jogadorModel == jogadorL2C0 && jogadorL0C2 == null) {
      this.qualFalta = L0C2;
      return true;
    }

    return false;
  }

  public boolean faltaUmDiagonal(JogadorModel jogadorModel) {
    Celula L0C0 = celulas[0][0];
    Celula L1C1 = celulas[1][1];
    Celula L2C2 = celulas[2][2];
    JogadorModel jogadorL0C0 = L0C0.getJogador();
    JogadorModel jogadorL1C1 = L1C1.getJogador();
    JogadorModel jogadorL2C2 = L2C2.getJogador();

    if (jogadorModel == jogadorL0C0 && jogadorModel == jogadorL1C1 && jogadorL2C2 == null) {
      this.qualFalta = L2C2;
      return true;
    }

    if (jogadorModel == jogadorL0C0 && jogadorModel == jogadorL2C2 && jogadorL1C1 == null) {
      this.qualFalta = L1C1;
      return true;
    }

    if (jogadorModel == jogadorL1C1 && jogadorModel == jogadorL2C2 && jogadorL0C0 == null) {
      this.qualFalta = L0C0;
      return true;
    }

    return false;
  }

  public Celula getQualFalta() {
    return qualFalta;
  }

  public boolean endGame() {
    int jogadas = 0;

    for (int linha = 0; linha < celulas.length; linha++) {
      for (int coluna = 0; coluna < celulas[linha].length; coluna++) {
        if (celulas[linha][coluna].jogavel()) {
          jogadas++;
        }
      }
    }

    return jogadas == 0;
  }
  
  private JogadorModel jogadorRival() {
	  if (model.getJogador1() == model.getJogadorAtual()) {
		  return model.getJogador2();
	  } else {
		  return model.getJogador1();
	  }
  }
}
