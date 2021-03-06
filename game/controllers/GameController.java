package controllers;

import views.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import models.ConfiguracaoModel;
import models.GameModel;

public class GameController {
  private GameModel model;
  private GameView view;
  private TabuleiroController tabuleiroController;
  private ConfiguracaoModel config;

  public GameController(GameModel model, GameView view) {
    this.model = model;
    this.view = view;
    this.config = model.getConfig();

    view.addNovoJogoListener(new NovoJogoListener());
  }

  public void setTabuleiroController(TabuleiroController tabuleiroController) {
    this.tabuleiroController = tabuleiroController;
  }

  class NovoJogoListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      tabuleiroController.startGame(config.getJogador1(), config.getJogador2());
      view.setVisible(false);
    }
  }

  public class JanelaListener extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
       view.setVisible(true);
    }
  }
}
