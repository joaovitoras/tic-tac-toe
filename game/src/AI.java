package src;

import views.TabuleiroView;

public class AI {
  private Verificador verificador;
  private int jogadas;
  private TabuleiroView view;
  private boolean comecouCanto;
  private boolean comecouCentro;
  private boolean comecouBorda;
  private Celula L1C1;
  private Celula L2C2;
  private Celula L2C1;
  private Celula L0C1;

  public AI(TabuleiroView view, Verificador verificador) {
    this.verificador = verificador;
    this.jogadas = 0;
    this.view = view;
    L0C1 = view.getCelulas()[0][1];
    L1C1 = view.getCelulas()[1][1];
    L2C2 = view.getCelulas()[2][2];
    L2C1 = view.getCelulas()[2][1];
  }
  
  public void jogar() {
    if (jogadas == 0) {
      identificarEstrategia();
    }    
    
    jogadas++;
    if (verificador.faltaUm()) {
      verificador.getQualFalta().doClick();
    } else {
      if (comecouCanto) {
        estrategiaMeioEBordas();
      } else if (comecouCentro) {
        estrategiaBordaEBloqueio();
      } else if (comecouBorda) {
        estrategiaCantos();
      } else {
        estrategiaMeioEBordas();
      }
    }
  }

  private void estrategiaCantos() {
    if (L1C1.jogavel()) {
      L1C1.doClick();
    } else {
      boolean fechouLinha = false;
      
      if (L0C1.getJogador() == L2C1.getJogador()) {
        fechouLinha = true;
      } else if (L0C1.getJogador() == L2C1.getJogador()) {
        fechouLinha = true;
      }
      
      if (fechouLinha) {
        for (int i = 0; i < this.view.getCantos().length; i ++) {
          if (this.view.getCantos()[i].jogavel()) {
            this.view.getCantos()[i].doClick();
            break;
          }
        }   
      } else {
        jogarEmCelulaVazia();
      }
    }
  }

  private void estrategiaBordaEBloqueio() {
    if (L2C2.jogavel()) {
      L2C2.doClick();
    } else {
      jogarEmCelulaVazia();
    }
    
  }

  private void estrategiaMeioEBordas() {
    if (L1C1.jogavel()) {
      L1C1.doClick();
    } else {  
      for (int i = 0; i < this.view.getBordas().length; i ++) {
        if (this.view.getBordas()[i].jogavel()) {
          this.view.getBordas()[i].doClick();
          break;
        }
      }        
    }
  }

  private void identificarEstrategia() {
    for (int i = 0; i < this.view.getCantos().length; i ++) {
      if (!this.view.getCantos()[i].jogavel()) {
        comecouCanto = true;
        break;
      }
    }
    
    for (int i = 0; i < this.view.getBordas().length; i ++) {
      if (!this.view.getBordas()[i].jogavel()) {
        comecouBorda = true;
        break;
      }
    }
    
    if (!L1C1.jogavel()) {
      comecouCentro = true;
    }
  }
  
  private void jogarEmCelulaVazia() {
    Celula vai = null;
    
    for (int linha = 0; linha < this.view.getCelulas().length; linha ++) {
      for (int coluna = 0; coluna < this.view.getCelulas().length; coluna ++) {
        if (this.view.getCelulas()[linha][coluna].jogavel()) {
          vai = this.view.getCelulas()[linha][coluna];
          break;
        }
      }
    }
     
    if (vai != null) {
      vai.doClick();
    }
  }
}
