
public class Principal {

  public static void main(String[] args) {
    GameMVC gameMVC = new GameMVC();
    TabuleiroMVC tabuleiroMVC= new TabuleiroMVC();
    
    gameMVC.setTabuleiroController(tabuleiroMVC.getController());
    tabuleiroMVC.setGameController(gameMVC.getController());
  }
}
