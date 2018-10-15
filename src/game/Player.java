package game;
public class Player {
  private String marker;
  private String type;

  public Player(String marker, boolean robot) {
    this.marker = marker;
  }

  public String getMarker() {
    return this.marker;
  }

  public String getType() {
    return this.type;
  }
}
