package models;
public class Jogador {
  private String marker;
  private boolean robot;

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
}
