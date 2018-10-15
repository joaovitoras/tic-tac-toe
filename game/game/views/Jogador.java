package game.views;
public class Jogador {
  private String marker;
  private String type;

  public Jogador(String marker, boolean robot) {
    this.marker = marker;
  }

  public String getMarker() {
    return this.marker;
  }

  public String getType() {
    return this.type;
  }
}
