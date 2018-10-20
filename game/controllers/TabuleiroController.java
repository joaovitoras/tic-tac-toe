package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import models.Jogador;
import models.TabuleiroModel;
import views.TabuleiroView;

public class TabuleiroController {
  private TabuleiroModel model;
  private TabuleiroView view;
  private GameController gameController;

  public TabuleiroController(TabuleiroModel model, TabuleiroView view) {
    this.model = model;
    this.view = view;

    view.addJanelaListener(new JanelaListener());
    view.addNovoJogoListener(new NovoJogoListener());
    view.addMenuListener(new MenuListener());
  }

  public void startGame(Jogador jogador1, Jogador jogador2) {
    model.novoJogo(jogador1, jogador2);
    view.novoJogo();
  }

  public class JanelaListener extends WindowAdapter  {
    public void windowClosing(WindowEvent e) {
      view.dispose();
    }
  }
  
  public class NovoJogoListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      model.novoJogo();
      view.novoJogo();
    }
  }
  
  public class MenuListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
    }
  }

  public void setJanelaListener(GameController controller) {
    view.addJanelaListener(controller.new JanelaListener());
  }
}
