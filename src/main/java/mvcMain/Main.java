package mvcMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mvcModel.LoginNavigationModel;
import mvcModel.LoginNavigationModelInterface;
import mvcViews.LoginPageController;


public class Main extends Application
{

  @Override
  public void start(Stage stage) throws Exception
  {        
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(Main.class.getResource("../views/BackgroundView.fxml"));
	  BorderPane view = loader.load();
	
    LoginPageController cont = loader.getController();
    if(cont == null)
    {
    	System.out.println("SHIT");
    }
    LoginNavigationModelInterface navModel = new LoginNavigationModel(view); 

    cont.setModel(navModel);
    
    navModel.showLogin();
    
 
    Scene s = new Scene(view);
    stage.setScene(s);
    stage.show();
   }

  
  public static void main(String [] args)
  {  
    launch(args);
  }
  
}
