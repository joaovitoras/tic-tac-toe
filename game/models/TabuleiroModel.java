package models;

import src.Celula;

public class TabuleiroModel {
  private Celula[][] celulas;
  private Celula celulaAtual;
  private Jogador jogador1;
  private Jogador jogador2;
  private Jogador jogadorAtual;
  
  public Jogador getJogador1() {
    return jogador1;
  }
  
  public Jogador getJogador2() {
    return jogador2;
  }
  
  public Jogador getJogadorAtual() {
    return jogadorAtual;
  }
  
  public Celula[][] getCelulas() {
    return celulas;
  }
  
  public Celula getCelulaAtual() {
    return celulaAtual;
  }
  
  public void inverterJogadores() {
    if (jogadorAtual == jogador1) {
      jogadorAtual = jogador2;
    } else {
      jogadorAtual = jogador1;
    }
  }

  public void setCelulas(Celula[][] celulas) {
    this.celulas = celulas;
  }
  
  public void setCelulaAtual(Celula celulaAtual) {
    this.celulaAtual = celulaAtual;
  }

  public void novoJogo(Jogador jogador1, Jogador jogador2) {
    limpar();
    this.jogador1 = jogador1;
    this.jogador2 = jogador2;
    this.jogadorAtual = this.jogador1; 
  }
  
  public void novoJogo() {
    limpar(); 
  }
  
  public void limpar() {
    this.jogadorAtual = this.jogador1;
    this.celulaAtual = null;
    
    for(int linha = 0; linha < this.celulas.length; linha++) {
      for(int coluna = 0; coluna < this.celulas.length; coluna++) {
        celulas[linha][coluna].limpar();
      }
    }
  }
}
