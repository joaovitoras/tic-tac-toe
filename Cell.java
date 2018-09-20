import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cell extends JButton {
  private Font font = new Font("Avenir", Font.PLAIN, 40);
  private Dimension dimension = new Dimension(80, 80);
  private Player player;
  private int row, col;

  public Cell(int row, int col) {
    // Muda o tamanho da celula
    this.setPreferredSize(dimension);

    // Muda a fonte da celula (tipo, peso e tamanho)
    this.setFont(font);
    this.row = row;
    this.col = col;
  }

  public Player getPlayer() {
    return this.player;
  }

  public int getCol() {
    return this.col;
  }

  public int getRow() {
    return this.row;
  }

  public void paint(Player player) {
    this.setEnabled(false);
    this.player = player;
    this.setText(player.getMarker());
  }

  public boolean isMarcable() {
    return this.isEnabled();
  }
}
