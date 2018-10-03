import javax.swing.*;

public class Game {
  private Board board;

  public Game() {
    this.board = new Board();
  }

  public void choosePlayers() {
    Player player1 = new Player("✕", playerDiloag(1));
    Player player2 = new Player("O", playerDiloag(2));

    this.board.setPlayer1(player1);
    this.board.setPlayer2(player2);
  }

  private String playerDiloag(int number) {
    Object[] options = { "Human", "Invensible Bot" };

    int result = JOptionPane.showOptionDialog(
      null, "Selecione o tipo do player " + number,
      "Player",
      JOptionPane.DEFAULT_OPTION,
      JOptionPane.QUESTION_MESSAGE,
      null,
      options,
      options[0]
    );

    if (result == 1) {
      return "bot";
    } else {
      return "human";
    }
  }

  public void start() {
    this.board.setVisible(true);
  }

  public static void main(String args[]) {
    Game game = new Game();
    game.choosePlayers();
    game.board.init();
    game.start();
  }
}
