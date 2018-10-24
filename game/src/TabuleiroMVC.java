package src;
import models.TabuleiroModel;
import views.TabuleiroView;

import controllers.GameController;
import controllers.TabuleiroController;

public class TabuleiroMVC {
  private TabuleiroModel model;
  private TabuleiroView view;
  private TabuleiroController controller;
  
  public TabuleiroMVC() {
    model = new TabuleiroModel();
    view = new TabuleiroView(model);
    controller = new TabuleiroController(model, view); 
  }

  public TabuleiroController getController() {
    return controller;
  }

  public void setGameController(GameController gameController) {
    controller.setJanelaListener(gameController);
  }
}
