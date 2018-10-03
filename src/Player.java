public class Player {
  private String marker;
  private String type;
  private String name;

  public Player(String marker, String type) {
    this.marker = marker;
    this.type = type;
    this.name = this.type + " " + this.marker;
  }
  public Player(String marker, String type, String name) {
    this.marker = marker;
    this.type = type;
    this.name = name;
  }

  public String getMarker() {
    return this.marker;
  }

  public String getType() {
    return this.type;
  }

  public String getName() {
    return this.name;
  }
}
