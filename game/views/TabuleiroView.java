package views;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import models.TabuleiroModel;
import src.Celula;
import src.Verificador;
public class TabuleiroView extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  private JPanel board, sideBar, cellsPanel;
  private Celula L0C0, L0C1, L0C2;
  private Celula L1C0, L1C1, L1C2;
  private Celula L2C0, L2C1, L2C2;
  private Celula[][] celulas;
  private Celula[] bordas;
  private Celula[] cantos;
  private Verificador verificador;
  private TabuleiroModel model;
  private JLabel lblPlayer2;
  private JLabel lblPlayer1;
  private JLabel labelTurno;
  private JButton novoJogo;
  private JButton menu;
  private Font font = new Font("Avenir", Font.PLAIN, 30);

  public TabuleiroView(TabuleiroModel model) {
    this.model = model;
    setTitle("Johngo da Velha");
    board = new JPanel();
    this.initCells();
    getContentPane().setLayout(new FlowLayout());

    sideBar = new JPanel();
    sideBar.setLayout(new GridLayout(4, 1, 10, 10));

    JPanel turnPanel = new JPanel();

    labelTurno = new JLabel();
    labelTurno.setFont(new Font("Times", Font.PLAIN, 16));
    labelTurno.setHorizontalAlignment(JLabel.CENTER);

    sideBar.add(labelTurno);

    lblPlayer1 = new JLabel();
    lblPlayer1.setFont(font);
    turnPanel.add(lblPlayer1);

    lblPlayer2 = new JLabel();
    lblPlayer2.setFont(font);
    lblPlayer2.setVisible(false);
    turnPanel.add(lblPlayer2);

    sideBar.add(turnPanel);

    novoJogo = new JButton("Novo Jogo");
    novoJogo.setMargin(new Insets(-2, -2, -2, -2));
    sideBar.add(novoJogo);


    menu = new JButton("Voltar ao Menu");
    menu.setMargin(new Insets(-2, -2, -2, -2));
    sideBar.add(menu);


    board.add(sideBar);

    // Adiciona o board ao frame
    getContentPane().add(board);
    // Ajusta automaticamente o tamanho da janela, alternativa ao setSize()
    pack();
    // Centraliza a janela
    setLocationRelativeTo(null);
  }

  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();

    if (obj instanceof Celula) {
      model.setCelulaAtual((Celula) obj);
      checkAndUpdateGameState();

      if (model.getJogadorAtual().robo()) {
        model.getJogadorAtual().jogar();
      }
    }
  }
  
  public void novoJogo() {
    verificador = new Verificador(model);
    lblPlayer1.setText(model.getJogador1().getMarca());
    lblPlayer2.setText(model.getJogador2().getMarca());
    labelTurno.setText("Vez do jogador");
    
    if (model.getJogador2().robo()) {
      model.getJogador2().iniciarAI(this, verificador);
    }
    if (model.getJogador1().robo()) {
      model.getJogador1().iniciarAI(this, verificador);
      model.getJogador1().jogar();
    }
    
    atualizarLabels();
    pack();
    setVisible(true);
  }

  public void checkAndUpdateGameState() {
    if (model.getCelulaAtual().jogavel()) {
      model.getJogadorAtual().getMarca();
      model.getCelulaAtual().marcar(model.getJogadorAtual());

      if (this.verificador.hasWon()) {
        paintCells(this.verificador.getWinnerCells(), Color.GREEN);
        this.labelTurno.setText("Vencedor");
        this.finishGame();
      } else if (this.verificador.endGame()) {
        paintCells(this.celulas, Color.ORANGE);
        labelTurno.setText("Empate");
      } else {
      
        model.inverterJogadores();
        atualizarLabels();
      }
    }
  }

    public void atualizarLabels() {
    boolean isPlayer1 = model.getJogador1() == model.getJogadorAtual();

    lblPlayer1.setVisible(isPlayer1);
    lblPlayer2.setVisible(!isPlayer1);
  }

  public void finishGame() {
    for (int row = 0; row < celulas.length; row++) {
      for (int col = 0; col < celulas[row].length; col++) {
        celulas[row][col].setEnabled(false);
      }
    }

    novoJogo.setVisible(true);
  }

  public void paintCells(Celula[] cells, Color color) {
    for (int i = 0; i < cells.length; i++) {
      cells[i].pintar(color);
    }
  }
  
  public void paintCells(Celula[][] cells, Color color) {
    for (int row = 0; row < celulas.length; row++) {
      for (int col = 0; col < celulas[row].length; col++) {
        celulas[row][col].pintar(color);
      }
    }
  }

  public Celula[][] getCelulas() {
    return this.celulas;
  }
  
  public Celula[] getCantos() {
    return this.cantos;
  }
  
  public Celula[] getBordas() {
    return this.bordas;
  }

  public void initCells() {
    cellsPanel = new JPanel();
    GridLayout gl_cellsPanel = new GridLayout(3, 3);
    cellsPanel.setLayout(gl_cellsPanel);

    L0C0 = new Celula(0, 0);
    L0C1 = new Celula(0, 1);
    L0C2 = new Celula(0, 2);
    L1C0 = new Celula(1, 0);
    L1C1 = new Celula(1, 1);
    L1C2 = new Celula(1, 2);
    L2C0 = new Celula(2, 0);
    L2C1 = new Celula(2, 1);
    L2C2 = new Celula(2, 2);

    celulas = new Celula[][] { { L0C0, L0C1, L0C2 }, { L1C0, L1C1, L1C2 }, { L2C0, L2C1, L2C2 } };
    bordas = new Celula[] { L0C1, L1C0, L1C2, L2C1 };
    cantos = new Celula[] { L0C0, L0C2, L2C0, L2C2};
    L0C0.addActionListener(this);
    L0C1.addActionListener(this);
    L0C2.addActionListener(this);
    L1C0.addActionListener(this);
    L1C1.addActionListener(this);
    L1C2.addActionListener(this);
    L2C0.addActionListener(this);
    L2C1.addActionListener(this);
    L2C2.addActionListener(this);

    cellsPanel.add(L0C0);
    cellsPanel.add(L0C1);
    cellsPanel.add(L0C2);
    cellsPanel.add(L1C0);
    cellsPanel.add(L1C1);
    cellsPanel.add(L1C2);
    cellsPanel.add(L2C0);
    cellsPanel.add(L2C1);
    cellsPanel.add(L2C2);

    // Adiciona as celular ao model
    model.setCelulas(celulas);
    board.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    // Adiciona o board no frame
    board.add(cellsPanel);
  }

  public void addJanelaListener(WindowAdapter adapter) {
    this.addWindowListener(adapter);
  }


  public void addNovoJogoListener(ActionListener listener) {
    novoJogo.addActionListener(listener);
  }

  public void addMenuListener(ActionListener listener) {
    menu.addActionListener(listener);

  }
}
