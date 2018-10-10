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
  private static final int INVALID_MOVE = 3;
  private static final int WON = 4;
  private JLabel statusLabel;
  private BoardCheck resultChecker;
  private Game game;

  public Board(Game game) {
    setTitle("Johngo da Velha");
    this.game = game;
    board = new JPanel();
    board.setLayout(new BorderLayout());
  }

  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();

    if (obj instanceof Cell) {
      this.currentCell = (Cell) obj;
      checkAndUpdateGameState();
    }
  }

  public void checkAndUpdateGameState() {
    checkMoveAndUpdateState();
    switch (this.state) {
      case Board.PLAYING:
      statusLabel.setText(this.currentPlayer.getMarker() + " Playing");
        break;
      case Board.INVALID_MOVE:
        statusLabel.setText("Jogada Invalida");
        break;
      case Board.WON:
        paintCells(this.resultChecker.getWinnerCells());
        this.statusLabel.setText(this.currentPlayer.getMarker() +  "ganhou");
        this.finishGame();
        break;
      default:
        break;
    }
  }

  public void checkMoveAndUpdateState() {
    if (this.currentCell.isMarcable()) {
      this.currentCell.mark(this.currentPlayer);

      if (this.resultChecker.hasWon()) {
        this.state = Board.WON;
      } else {
        this.togglePlayer();
        this.state = Board.PLAYING;
      }

    } else {
      this.state = Board.INVALID_MOVE;
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

    statusLabel = new JLabel(player1.getMarker() + " Playing");

    statusLabel.setHorizontalAlignment(JLabel.CENTER);
    statusLabel.setVerticalAlignment(JLabel.CENTER);

    scorePanel.add(statusLabel);

    board.add(scorePanel, BorderLayout.PAGE_END);
  }

  public void initChecker() {
    this.resultChecker = new BoardCheck(this);
  }

  public void drawBoard() {
    // Adiciona o board ao frame
    add(board);
    // Ajusta automaticamente o tamanho da janela, alternativa ao setSize()
    pack();
    // Centraliza a janela
    setLocationRelativeTo(null);
    // Termina a aplicação após fechar a janela
    this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
          game.setVisible(true); 
          dispose();
        }
    });
  }

  public void init() {
    this.initCells();
    this.initScore();
    this.initChecker();
    this.drawBoard();
    this.state = Board.PLAYING;
  }
}

