package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  private JPanel board, scorePanel, cellsPanel;
  private Cell R1C1, R1C2, R1C3;
  private Cell R2C1, R2C2, R2C3;
  private Cell R3C1, R3C2, R3C3;
  private Cell[][] cells;
  private Cell currentCell;
  private Player currentPlayer, player1, player2;
  private JLabel statusLabel;
  private BoardCheck resultChecker;
  
  public Board(Game game) {
    setTitle("Johngo da Velha");
    board = new JPanel();
    board.setLayout(new BorderLayout());
    this.initCells();

    // Adiciona o board ao frame
    getContentPane().add(board);
    // Ajusta automaticamente o tamanho da janela, alternativa ao setSize()
    pack();
    // Centraliza a janela
    setLocationRelativeTo(null);
    // Abre o menu inicial após fechar este frame
    this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
          game.setVisible(true);
          dispose();
        }
    });
  }

  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();

    if (obj instanceof Cell) {
      this.currentCell = (Cell) obj;
      checkAndUpdateGameState();
    }
  }

  public void checkAndUpdateGameState() {
    if (this.currentCell.isMarcable()) {
      this.currentPlayer.getMarker();
      this.currentCell.mark(this.currentPlayer);

      if (this.resultChecker.hasWon()) {
        paintCells(this.resultChecker.getWinnerCells());
        this.statusLabel.setText(this.currentPlayer.getMarker() +  "ganhou");
        this.finishGame();
      } else {
        this.togglePlayer();
        statusLabel.setText(this.currentPlayer.getMarker() + " Playing");
      }
    }
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

  public Cell[][] getCells() {
    return this.cells;
  }

  public Player getCurrentPlayer() {
    return this.currentPlayer;
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

    cells = new Cell[][] { { R1C1, R1C2, R1C3 }, { R2C1, R2C2, R2C3 }, { R3C1, R3C2, R3C3 } };

    R1C1.addActionListener(this);
    R1C2.addActionListener(this);
    R1C3.addActionListener(this);
    R2C1.addActionListener(this);
    R2C2.addActionListener(this);
    R2C3.addActionListener(this);
    R3C1.addActionListener(this);
    R3C2.addActionListener(this);
    R3C3.addActionListener(this);

    cellsPanel.add(R1C1);
    cellsPanel.add(R1C2);
    cellsPanel.add(R1C3);
    cellsPanel.add(R2C1);
    cellsPanel.add(R2C2);
    cellsPanel.add(R2C3);
    cellsPanel.add(R3C1);
    cellsPanel.add(R3C2);
    cellsPanel.add(R3C3);

    // Adiciona o board no frame
    board.add(cellsPanel, BorderLayout.PAGE_START);
  }

  public void init() {
    resultChecker = new BoardCheck(this);
    scorePanel = new JPanel();
    scorePanel.setLayout(new GridLayout(1, 2));
    statusLabel = new JLabel(player1.getMarker() + " Playing");
    statusLabel.setHorizontalAlignment(JLabel.CENTER);
    statusLabel.setVerticalAlignment(JLabel.CENTER);
    scorePanel.add(statusLabel);
    board.add(scorePanel, BorderLayout.PAGE_END);
    setVisible(true);
  }
}
