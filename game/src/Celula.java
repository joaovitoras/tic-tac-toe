package src;
import javax.swing.*;

import models.Jogador;

import java.awt.*;

public class Celula extends JButton {
  private static final long serialVersionUID = 1L;
  private Font font = new Font("Avenir", Font.PLAIN, 40);
  private Dimension dimension = new Dimension(80, 80);
  private Jogador jogador;
  public int row, col;

  public Celula(int row, int col) {
    // Muda o tamanho da celula
    this.setPreferredSize(dimension);
    this.setOpaque(true);
    // Muda a fonte da celula (tipo, peso e tamanho)
    this.setFont(font);
    this.row = row;
    this.col = col;
  }

  public Jogador getJogador() {
    return this.jogador;
  }

  public int getCol() {
    return this.col;
  }

  public int getRow() {
    return this.row;
  }

  public void marcar(Jogador jogador) {
    this.setEnabled(false);
    this.jogador = jogador;
    this.setText(jogador.getMarker());
  }

  public boolean jogavel() {
    return this.isEnabled();
  }
  
  public boolean vazia() {
    return this.jogador == null;
  }
  
  public void limpar() {
    setBackground(new Color(238, 238, 238));
    this.setEnabled(true);
    this.jogador = null;
    this.setText(null);
  }

  public void pintar(Color color) {
    setBackground(color);
  }
}
