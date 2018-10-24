package models;

public class ConfiguracaoModel {
  private int jogador1Id;
  private int jogador2Id;
  
  public ConfiguracaoModel(int jogador1Id, int jogador2Id) {
    this.jogador1Id = jogador1Id;
    this.jogador2Id = jogador2Id;
  }
  
  public JogadorModel getJogador1() {
    return new JogadorModel(this.jogador1Id).get();
  }

  public JogadorModel getJogador2() {
    return new JogadorModel(this.jogador2Id).get();
  }
}
