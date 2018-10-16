import models.GameModel;
import views.GameView;
import controllers.GameController;
import controllers.TabuleiroController;

public class GameMVC {
  private TabuleiroMVC tabuleiroMVC;
  private GameView view;
  private GameController controller;

  public GameMVC() {
    GameModel model = new GameModel();
    view = new GameView(model);
    controller = new GameController(model, view); 
    view.setVisible(true);
  }
  
  public void setTabuleiroController(TabuleiroController tabuleiroController) {
    this.controller.setTabuleiroController(tabuleiroController);
  }

  public GameController getController() {
    return controller;
  }
}
