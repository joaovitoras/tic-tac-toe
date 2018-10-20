package views;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import models.Jogador;
import models.TabuleiroModel;
import src.Celula;
import src.VerificadorResultado;
public class TabuleiroView extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  private JPanel board, sideBar, cellsPanel;
  private Celula L1C1, L1C2, L1C3;
  private Celula L2C1, L2C2, L2C3;
  private Celula L3C1, L3C2, L3C3;
  private Celula[][] celulas;
  private VerificadorResultado resultChecker;
  private TabuleiroModel model;
  private JLabel lblPlayer2;
  private JLabel lblPlayer1;
  private JLabel turn;
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
    
    turn = new JLabel("Vez do jogador");
    turn.setFont(new Font("Times", Font.PLAIN, 16));
    turn.setHorizontalAlignment(JLabel.CENTER);

    sideBar.add(turn);
    
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
      if (model.getJogadorAtual().isRobot()) {
          
      }
    }
  }

  public void checkAndUpdateGameState() {
    if (model.getCelulaAtual().isMarcable()) {
      model.getJogadorAtual().getMarker();
      model.getCelulaAtual().mark(model.getJogadorAtual());

      if (this.resultChecker.hasWon()) {
        paintCells(this.resultChecker.getWinnerCells());
        this.turn.setText("Vencedor");
        this.finishGame();
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

  public void paintCells(Celula[] cells) {
    for (int i = 0; i < cells.length; i++) {
      cells[i].pintar();
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
    board.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    // Adiciona o board no frame
    board.add(cellsPanel);
  }

  public void addJanelaListener(WindowAdapter adapter) {
    this.addWindowListener(adapter);
  }

  public void novoJogo() {
    resultChecker = new VerificadorResultado(model);
    lblPlayer1.setText(model.getJogador1().getMarker());
    lblPlayer2.setText(model.getJogador2().getMarker());
    atualizarLabels();
    pack();
    setVisible(true);
  }

  public void addNovoJogoListener(ActionListener listener) {
    novoJogo.addActionListener(listener); 
  }
  
  public void addMenuListener(ActionListener listener) {
    menu.addActionListener(listener);
    
  }
}
