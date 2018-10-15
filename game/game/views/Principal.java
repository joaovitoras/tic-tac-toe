package game.views;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

public class Principal extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  private Tabuleiro tabuleiro;
  private JLabel lblJogoDaVelha;
  private JButton btnNovoJogo;
  private JButton btnSair;
  private JButton btnJogadores;
  private Configuracoes configuracoes;
  private Jogador player1;
  private Jogador player2;

  public Principal() {
    this.setBounds(new Rectangle(0, 0, 200, 280));
    this.getContentPane().setLayout(null);

    lblJogoDaVelha = new JLabel("Jogo da Velha");
    lblJogoDaVelha.setBounds(20, 10, 160, 52);
    lblJogoDaVelha.setHorizontalAlignment(SwingConstants.CENTER);
    lblJogoDaVelha.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
    this.getContentPane().add(lblJogoDaVelha);

    btnNovoJogo = new JButton("Novo Jogo");
    btnNovoJogo.setBounds(40, 70, 120, 46);
    btnNovoJogo.addActionListener(this);
    this.getContentPane().add(btnNovoJogo);

    btnSair = new JButton("Sair");
    btnSair.setBounds(41, 185, 119, 46);
    btnSair.addActionListener(this);
    this.getContentPane().add(btnSair);

    btnJogadores = new JButton("Jogadores");
    btnJogadores.setBounds(40, 127, 119, 46);
    btnJogadores.addActionListener(this);
    getContentPane().add(btnJogadores);

    this.player1 = new Jogador("✕", false);
    this.player2 = new Jogador("O", false);
    configuracoes = new Configuracoes(this);

    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnNovoJogo) {
      start();
    } else if (e.getSource() == btnJogadores) {
      configuracoes.setVisible(true);
    } else if (e.getSource() == btnSair) {
      System.exit(0);
    }
  }

  public void setPlayer1(Jogador jogador) {
    this.player1 = jogador;
  }

  public void setPlayer2(Jogador jogador) {
    this.player2 = jogador;
  }
  
  public Jogador getPlayer1() {
    return this.player1;
  }

  public Jogador getPlayer2() {
    return this.player2;
  }

  public void start() {
    this.tabuleiro = new Tabuleiro(this);
    this.tabuleiro.setPlayer1(this.player1);
    this.tabuleiro.setPlayer2(this.player2);
    this.tabuleiro.init();
    this.setVisible(false);
  }

  public static void main(String[] args) {
    new Principal();
  }
}
