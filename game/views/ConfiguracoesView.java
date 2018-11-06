package views;
import javax.swing.*;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

import models.JogadorModel;

public class ConfiguracoesView extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  private JTextField player1;
  private JTextField player2;
  private GameView gameView;
  private JButton btnP1Human;
  private JButton btnP1Robot;
  private JButton btnP2Human;
  private JButton btnP2Robot;
  private JButton btnSalvar;

  public ConfiguracoesView(GameView gameView) {
    getContentPane().setFocusTraversalKeysEnabled(false);
    this.setBounds(new Rectangle(0, 0, 210, 240));
    getContentPane().setLayout(null);

    player1 = new JTextField();
    player1.setFocusTraversalKeysEnabled(false);
    player1.setHorizontalAlignment(SwingConstants.CENTER);
    player1.setText(gameView.getPlayer1().getMarca());
    player1.setFont(new Font("Avenir", Font.PLAIN, 40));
    player1.setBounds(20, 40, 80, 80);
    getContentPane().add(player1);
    player1.setColumns(10);

    player2 = new JTextField();
    player2.setFocusTraversalKeysEnabled(false);
    player2.setHorizontalAlignment(SwingConstants.CENTER);
    player2.setText(gameView.getPlayer2().getMarca());
    player2.setFont(new Font("Avenir", Font.PLAIN, 40));
    player2.setColumns(10);
    player2.setBounds(110, 40, 80, 80);
    getContentPane().add(player2);

    JLabel lblPlayer = new JLabel("Player 1");
    lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
    lblPlayer.setBounds(20, 20, 78, 16);
    getContentPane().add(lblPlayer);

    JLabel lblPlayer_1 = new JLabel("Player 2");
    lblPlayer_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblPlayer_1.setBounds(110, 20, 79, 16);
    getContentPane().add(lblPlayer_1);

    ImageIcon robot = new ImageIcon(ConfiguracoesView.class.getResource("/images/robot.png"));
    robot = new ImageIcon(robot.getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT));
    ImageIcon human = new ImageIcon(ConfiguracoesView.class.getResource("/images/person.png"));
    human = new ImageIcon(human.getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT));

    btnP1Human = new JButton("");
    btnP1Human.setBounds(20, 120, 40, 40);
    btnP1Human.setIcon(human);
    btnP1Human.setBorderPainted(gameView.getPlayer1().humano());
    btnP1Human.addActionListener(this);
    getContentPane().add(btnP1Human);

    btnP1Robot = new JButton("");
    btnP1Robot.setBounds(60, 120, 40, 40);
    btnP1Robot.setBorderPainted(false);
    btnP1Robot.setIcon(robot);
    btnP1Robot.setBorderPainted(gameView.getPlayer1().robo());
    btnP1Robot.addActionListener(this);
    getContentPane().add(btnP1Robot);

    btnP2Human = new JButton("");
    btnP2Human.setEnabled(true);
    btnP2Human.setBounds(110, 120, 40, 40);
    btnP2Human.setIcon(human);
    btnP2Human.setBorderPainted(gameView.getPlayer2().humano());
    btnP2Human.addActionListener(this);
    getContentPane().add(btnP2Human);

    btnP2Robot = new JButton("");
    btnP2Robot.setBounds(150, 120, 40, 40);
    btnP2Robot.setBorderPainted(false);
    btnP2Robot.setIcon(robot);
    btnP2Robot.setBorderPainted(gameView.getPlayer2().robo());
    btnP2Robot.addActionListener(this);
    getContentPane().add(btnP2Robot);

    btnSalvar = new JButton("Salvar");
    btnSalvar.setBounds(20, 172, 170, 29);
    btnSalvar.addActionListener(this);
    getContentPane().add(btnSalvar);

    this.gameView = gameView;
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnP1Human) {
      togglePlayerButtonBorder(btnP1Human, btnP1Robot, false);
    } else if (e.getSource() == btnP1Robot) {
      togglePlayerButtonBorder(btnP1Human, btnP1Robot, true);
    } else if (e.getSource() == btnP2Human) {
      togglePlayerButtonBorder(btnP2Human, btnP2Robot, false);
    } else if (e.getSource() == btnP2Robot) {
      togglePlayerButtonBorder(btnP2Human, btnP2Robot, true);
    } else if (e.getSource() == btnSalvar) {
      salvar();
      dispose();
    }
  }

  public void togglePlayerButtonBorder(JButton human, JButton bot, boolean isBotSelected) {
    human.setBorderPainted(!isBotSelected);
    bot.setBorderPainted(isBotSelected);
  }

  private void salvar() {
    JogadorModel j1 = gameView.getPlayer1();
    j1.setMarca(player1.getText());
    j1.setRobo(btnP1Robot.isBorderPainted());
    j1.save();

    JogadorModel j2 = gameView.getPlayer2();
    j2.setMarca(player2.getText());
    j2.setRobo(btnP2Robot.isBorderPainted());
    j2.save();
  }

}
