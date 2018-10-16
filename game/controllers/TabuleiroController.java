package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import models.TabuleiroModel;
import views.Jogador;
import views.TabuleiroView;

public class TabuleiroController {
  private TabuleiroModel model;
  private TabuleiroView view;
  private GameController gameController;

  public TabuleiroController(TabuleiroModel model, TabuleiroView view) {
    this.model = model;
    this.view = view;

    view.addJanelaListener(new JanelaListener());
  }

  public void startGame(Jogador jogador1, Jogador jogador2) {
    model.setPlayer1(jogador1);
    model.setPlayer2(jogador2);
    view.configurarTabuleiro();
    view.setVisible(true);
  }

  public class JanelaListener extends WindowAdapter  {
    public void windowClosing(WindowEvent e) {
      view.dispose();
    }
  }

  public void setJanelaListener(GameController controller) {
    view.addJanelaListener(controller.new JanelaListener());
  }
}
