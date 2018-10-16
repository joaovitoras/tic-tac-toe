package views;
import javax.swing.*;
import auxiliar.Celula;
import auxiliar.VerificadorResultado;

import java.awt.*;
import java.awt.event.*;
import models.TabuleiroModel;
public class TabuleiroView extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  private JPanel board, scorePanel, cellsPanel;
  private Celula L1C1, L1C2, L1C3;
  private Celula L2C1, L2C2, L2C3;
  private Celula L3C1, L3C2, L3C3;
  private Celula[][] celulas;
  private JLabel statusLabel;
  private VerificadorResultado resultChecker;
  private TabuleiroModel model;

  public TabuleiroView(TabuleiroModel model) {
    this.model = model;
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
  }

  class FecharJanelaListener extends WindowAdapter  {
    public void windowClosing(WindowEvent e) {
       System.out.println("veio");
    }
  }

  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();

    if (obj instanceof Celula) {
      model.setCelulaAtual((Celula) obj);
      checkAndUpdateGameState();
    }
  }

  public void checkAndUpdateGameState() {
    if (model.getCelulaAtual().isMarcable()) {
      model.getJogadorAtual().getMarker();
      model.getCelulaAtual().mark(model.getJogadorAtual());

      if (this.resultChecker.hasWon()) {
        paintCells(this.resultChecker.getWinnerCells());
        this.statusLabel.setText(model.getJogadorAtual().getMarker() +  "ganhou");
        this.finishGame();
      } else {
        model.inverterJogadores();
        statusLabel.setText(model.getJogadorAtual().getMarker() + " Playing");
      }
    }
  }

  public void finishGame() {
    for (int row = 0; row < celulas.length; row++) {
      for (int col = 0; col < celulas[row].length; col++) {
        celulas[row][col].setEnabled(false);
      }
    }
  }

  public void paintCells(Celula[] cells) {
    for (int i = 0; i < cells.length; i++) {
      cells[i].setBackground(Color.GREEN);
    }
  }

  public Celula[][] getCells() {
    return this.celulas;
  }

  public Jogador getJogadorAtual() {
    return model.getJogadorAtual();
  }

  public void initCells() {
    cellsPanel = new JPanel();
    cellsPanel.setLayout(new GridLayout(3, 3));

    L1C1 = new Celula(1, 1);
    L1C2 = new Celula(1, 2);
    L1C3 = new Celula(1, 3);
    L2C1 = new Celula(2, 1);
    L2C2 = new Celula(2, 2);
    L2C3 = new Celula(2, 3);
    L3C1 = new Celula(3, 1);
    L3C2 = new Celula(3, 2);
    L3C3 = new Celula(3, 3);

    celulas = new Celula[][] { { L1C1, L1C2, L1C3 }, { L2C1, L2C2, L2C3 }, { L3C1, L3C2, L3C3 } };

    L1C1.addActionListener(this);
    L1C2.addActionListener(this);
    L1C3.addActionListener(this);
    L2C1.addActionListener(this);
    L2C2.addActionListener(this);
    L2C3.addActionListener(this);
    L3C1.addActionListener(this);
    L3C2.addActionListener(this);
    L3C3.addActionListener(this);

    cellsPanel.add(L1C1);
    cellsPanel.add(L1C2);
    cellsPanel.add(L1C3);
    cellsPanel.add(L2C1);
    cellsPanel.add(L2C2);
    cellsPanel.add(L2C3);
    cellsPanel.add(L3C1);
    cellsPanel.add(L3C2);
    cellsPanel.add(L3C3);

    // Adiciona as celular ao model
    model.setCelulas(celulas);
    // Adiciona o board no frame
    board.add(cellsPanel, BorderLayout.PAGE_START);
  }

  public void configurarTabuleiro() {
    resultChecker = new VerificadorResultado(model);
    scorePanel = new JPanel();
    scorePanel.setLayout(new GridLayout(1, 2));
    statusLabel = new JLabel(model.getJogador1().getMarker() + " Playing");
    statusLabel.setHorizontalAlignment(JLabel.CENTER);
    statusLabel.setVerticalAlignment(JLabel.CENTER);
    scorePanel.add(statusLabel);
    board.add(scorePanel, BorderLayout.PAGE_END);
    setVisible(true);
  }

  public void addJanelaListener(WindowAdapter adapter) {
    this.addWindowListener(adapter);
  }
}
