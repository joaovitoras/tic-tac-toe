import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JFrame implements ActionListener {
  private JPanel board, scorePanel, cellsPanel;
  private Cell R1C1, R1C2, R1C3;
  private Cell R2C1, R2C2, R2C3;
  private Cell R3C1, R3C2, R3C3;
  private Cell[] cells;
  private Cell[][] cellsToWin;
  private Cell[] row1, row2, row3;
  private Cell[] col1, col2, col3;
  private Cell[] diagonal, reverseDiagonal;
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

    row1 = new Cell[] { R1C1, R1C2, R1C3 };
    row2 = new Cell[] { R2C1, R2C2, R2C3 };
    row3 = new Cell[] { R3C1, R3C2, R3C3 };
    col1 = new Cell[] { R1C1, R2C1, R3C1 };
    col2 = new Cell[] { R1C2, R2C2, R3C2 };
    col3 = new Cell[] { R1C3, R2C3, R3C3 };
    diagonal = new Cell[] { R1C1, R2C2, R3C3 };
    reverseDiagonal = new Cell[] { R1C3, R2C2, R3C1 };
    cellsToWin = new Cell[][] {row1, row2, row3, col1, col2, col3, diagonal, reverseDiagonal};
    cells = new Cell[] { R1C1, R1C2, R1C3, R2C1, R2C2, R2C3, R3C1, R3C2, R3C3 };

    // Adiciona todas as celulas no board
    for (int i = 0; i < cells.length; i++) {
      cells[i].addActionListener(this);
      cellsPanel.add(cells[i]);
    }

    // Adiciona o board no frame
    board.add(cellsPanel, BorderLayout.PAGE_START);
  }

  public void initScore() {
    scorePanel = new JPanel();
    scorePanel.setLayout(new GridLayout(1, 2));

    label1 = new JLabel(player1.getName());
    JLabel label2 = new JLabel(player2.getName());

    label1.setHorizontalAlignment(JLabel.CENTER);
    label1.setVerticalAlignment(JLabel.CENTER);
    label2.setHorizontalAlignment(JLabel.CENTER);
    label2.setVerticalAlignment(JLabel.CENTER);

    scorePanel.add(label1);
    scorePanel.add(label2);

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
    boolean won = false;

    for (int i = 0; i < this.cellsToWin.length; i++) {
      if (hasWinBy(cellsToWin[i])) {
        won = true;
        paintCells(cellsToWin[i]);
      }
    }

    return won;
  }

  public boolean hasWinBy(Cell[] cells) {
    int count = 0;

    for (int i = 0; i < cells.length; i++) {
      Player player = cells[i].getPlayer();

      if (player != null && player == this.currentPlayer) {
        count++;
      } else {
        break;
      }
    }

    if (count == 3) {
      return true;
    }

    return false;
  }

  public void finishGame() {
    for (int i = 0; i < cells.length; i++) {
      cells[i].setEnabled(false);
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
  }

  public void setPlayer2(Player player) {
    this.player2 = player;
  }

  public void makePlayer1Current() {
    this.currentPlayer = this.player1;
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

