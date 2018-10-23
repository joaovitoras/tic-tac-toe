package models;

import src.AI;
import src.Verificador;
import views.TabuleiroView;

public class Jogador {
  private String marker;
  private boolean robot;
  private AI ai;

  public Jogador(String marker, boolean robot) {
    this.marker = marker;
    this.robot = robot;
  }

  public String getMarker() {
    return this.marker;
  }

  public boolean isRobot() {
    return this.robot;
  }

  public void jogar() {
    ai.jogar();
  }

  public void iniciarAI(TabuleiroView view, Verificador verificador) {
     ai = new AI(view, verificador);
  }
}
