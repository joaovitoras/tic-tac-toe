import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JFrame implements ActionListener {
  private JPanel board, scorePanel, cellsPanel;
  private Cell R1C1, R1C2, R1C3;
  private Cell R2C1, R2C2, R2C3;
  private Cell R3C1, R3C2, R3C3;
  private Cell[][] cells;
  private Cell currentCell;
  private Player currentPlayer, player1, player2;
  private int state;
  private static final int PLAYING = 1;
  private static final int PLAYED = 2;
  private static final int INVALID_MOVE = 3;
  private static final int WON = 4;
  private JLabel label1;

  public Board() {
    setTitle("Johngo da Velha");
    board = new JPanel();
    board.setLayout(new  BorderLayout());

    // Termina a aplicação após fechar a janela
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  public void initCells() {
    cellsPanel = new JPanel();
    cellsPanel.setLayout(new GridLayout(3, 3));

    R1C1 = new Cell(1, 1);
    R1C2 = new Cell(1, 2);
    R1C3 = new Cell(1, 3);
    R2C1 = new Cell(2, 1);
    R2C2 = new Cell(2, 2);
    R2C3 = new Cell(2, 3);
    R3C1 = new Cell(3, 1);
    R3C2 = new Cell(3, 2);
    R3C3 = new Cell(3, 3);

    cells = new Cell[][] {
      { R1C1, R1C2, R1C3 },
      { R2C1, R2C2, R2C3 },
      { R3C1, R3C2, R3C3 }
    };

    // Adiciona todas as celulas no board
    for (int row = 0; row < cells.length; row++) {
      for (int col = 0; col < cells[row].length; col++) {
        cells[row][col].addActionListener(this);
        cellsPanel.add(cells[row][col]);
      }
    }

    // Adiciona o board no frame
    board.add(cellsPanel, BorderLayout.PAGE_START);
  }

  public void initScore() {
    scorePanel = new JPanel();
    scorePanel.setLayout(new GridLayout(1, 2));

    label1 = new JLabel(player1.getName() + " playing");

    label1.setHorizontalAlignment(JLabel.CENTER);
    label1.setVerticalAlignment(JLabel.CENTER);

    scorePanel.add(label1);

    board.add(scorePanel, BorderLayout.PAGE_END);
  }

  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();

    if (obj instanceof Cell) {
      this.currentCell = (Cell) obj;
      checkAndUpdateGameState();
    }
  }

  public void checkAndUpdateGameState() {
    checkMove();

    switch (this.state) {
      case Board.PLAYED:
        this.togglePlayer();
        this.state = Board.PLAYING;
        break;
      case Board.INVALID_MOVE:
        label1.setText("Jogada Invalida");
        break;
      case Board.WON:
        this.label1.setText(this.currentPlayer.getName() +  "won");
        this.finishGame();
        break;
      default:
        break;
    }
  }

  public void checkMove() {
    if (this.currentCell.isMarcable()) {
      this.currentCell.paint(this.currentPlayer);
      this.state = Board.PLAYED;
    } else {
      this.state = Board.INVALID_MOVE;
    }

    if (this.hasWon()) {
      this.state = Board.WON;
    }
  }

  public boolean hasWon() {
    return (
      wonBy("rows") || wonBy("cols")
    );
  }

  public boolean wonByCols() {
    for (int col = 0; col < cells.length; col++) {
      int occurrences = 0;

      for (int row = 0; row < cells[col].length; row++) {
        if (cells[row][col].getPlayer() == this.currentPlayer) {
          occurrences++;
        }
      }

      if (occurrences == 3) {
        paintCells(new Cell[] {cells[0][col], cells[1][col], cells[2][col]});
        return true;
      }
    }

    return false;
  }

  public boolean wonBy(String by) {
    for (int row = 0; row < cells.length; row++) {
      int occurrences = 0;

      for (int col = 0; col < cells[row].length; col++) {
        if (by.equals("rows")) {
          if (cells[row][col].getPlayer() == this.currentPlayer) {
            occurrences ++;
          }
        } else if (by.equals("cols")) {
          if (cells[col][row].getPlayer() == this.currentPlayer) {
            occurrences ++;
          }
        }


      }

      if (occurrences == 3) {
        if (by.equals("rows")) {
          paintCells(cells[row]);
        } else if (by.equals("cols")) {
          paintCells(new Cell[] { cells[0][row], cells[1][row], cells[2][row]});
        }
        return true;
      }
    }

    return false;
  }


  public void finishGame() {
    for (int row = 0; row < cells.length; row++) {
      for (int col = 0; col < cells[row].length; col++) {
        cells[row][col].setEnabled(false);
      }
    }
  }

  public void paintCells(Cell[] cells) {
    for (int i = 0; i < cells.length; i++) {
      cells[i].setBackground(Color.GREEN);
    }
  }

  public void togglePlayer() {
    if (this.currentPlayer == player1) {
      this.currentPlayer = player2;
    } else {
      this.currentPlayer = player1;
    }
  }

  public void setPlayer1(Player player) {
    this.player1 = player;
    this.currentPlayer = player;
  }

  public void setPlayer2(Player player) {
    this.player2 = player;
  }

  public void makeVisible() {
    this.state = Board.PLAYING;

    add(board);

    // Ajusta automaticamente o tamanho da janela, alternativa ao setSize()
    pack();

    // Centraliza a janela
    setLocationRelativeTo(null);

    // torna o board visivel
    this.setVisible(true);
  }
}

