package models;

import auxiliar.Celula;

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
      System.out.println("jogador2");
      jogadorAtual = jogador2;
    } else {
      System.out.println("jogador1");
      jogadorAtual = jogador1;
    }
  }

  public void setPlayer1(Jogador jogador) {
    this.jogador1 = jogador;
    this.jogadorAtual = this.jogador1;
  }
  
  public void setPlayer2(Jogador jogador) {
    this.jogador2 = jogador;
  }
  
  public void setCelulas(Celula[][] celulas) {
    this.celulas = celulas;
  }
  
  public void setCelulaAtual(Celula celulaAtual) {
    this.celulaAtual = celulaAtual;
  }
}
