package mvcViews;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mvcModel.LoginNavigationModelInterface;
import mvcModel.SessionSingleton;
import javafx.event.ActionEvent;

public class LoginPageController {
	
	LoginNavigationModelInterface loginNavModel;
	
	public LoginPageController()
	{
		
	}
	
	public void setModel(LoginNavigationModelInterface model)
	{
		this.loginNavModel = model;
	}
	
	
    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordLabel;

    @FXML
    private TextField usernameLabel;
    
    @FXML
    private Label invalidLoginLabel;
    
    
    @FXML
    void onLoginButtonClick(ActionEvent event) {
    	String username = usernameLabel.getText();
    	String password = passwordLabel.getText();
    	
    	SessionSingleton currentSession = SessionSingleton.getInstance();
    	
    	usernameLabel.setText("");
    	passwordLabel.setText("");
    	if(currentSession.startSession(username, password))
    	{
    		invalidLoginLabel.setText("");

    		this.loginNavModel.showHomepage();
    	}
    	else
    	{
    		invalidLoginLabel.setText("Invalid login.");
    	}
    	
    }
    
}
