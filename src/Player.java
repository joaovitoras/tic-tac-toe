public class Player {
  private String marker;
  private String type;

  public Player(String marker, String type, boolean robot) {
    this.marker = marker;
    this.type = type;
  }

  public String getMarker() {
    return this.marker;
  }

  public String getType() {
    return this.type;
  }
}
