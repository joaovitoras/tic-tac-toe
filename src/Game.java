import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

public class Game extends JFrame implements ActionListener {
  private Board board;
  private JLabel lblJogoDaVelha;
  private JButton btnNovoJogo;
  private JButton btnSair;
  private JButton btnJogadores;
  private PlayerSettings playerSettings;
  private Player player1;
  private Player player2;

  public Game() {
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

    playerSettings = new PlayerSettings(this);

    this.board = new Board(this);
    this.board.setPlayer1(player1);
    this.board.setPlayer2(player2);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnNovoJogo) {
      this.board.setPlayer1(player1);
      this.board.setPlayer2(player2);
      this.board.init();
      this.start();
      this.setVisible(false);
    } else if (e.getSource() == btnJogadores) {
      System.out.println("asd");
      playerSettings.setVisible(true);
    } else if (e.getSource() == btnSair) {
      System.exit(0);
    }
  }

  public void setPlayer1(Player player) {
    this.player1 = player;
  }

  public void setPlayer2(Player player) {
    this.player2 = player;
  }

  public void start() {
    this.board.setVisible(true);
  }

  public static void main(String[] args) {
    new Game();
  }
}
