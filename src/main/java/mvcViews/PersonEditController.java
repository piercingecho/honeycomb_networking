package mvcViews;

import javafx.beans.binding.Bindings;
//import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mvcModel.PageModelPerson;
import mvcModel.StorageModel;
import mvcModel.TransitionModelPerson;

public class PersonEditController {
	String personId;
	PageModelPerson model;
	TransitionModelPerson transition;
	
	
    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField idField;

    @FXML
    private ChoiceBox<?> linksChoices;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField pronounsField;
    
    @FXML
    void onAddLinkClick(ActionEvent event) {

    }
    public void setModel(String id, TransitionModelPerson transitionModel)
    {
    	personId = id;
    	model = (PageModelPerson) StorageModel.pull(id);
    	transition = transitionModel;
    	System.out.println("Person ID:");
    	System.out.println(personId);
    	Bindings.bindBidirectional(nameField.textProperty(), model.getName());
    	Bindings.bindBidirectional(pronounsField.textProperty(), model.getPronouns());
    	Bindings.bindBidirectional(emailField.textProperty(), model.getEmail());
    	Bindings.bindBidirectional(phoneField.textProperty(), model.getPhone());
    	Bindings.bindBidirectional(descriptionField.textProperty(), model.getDescription());

    	
    }
    
    @FXML
    public void onUpdateClick(ActionEvent event) {
    	//update variables
    	model.updatePageInStorage();
    	
    	transition.showCanEdit();
    }
    
    public void onCancelClick(ActionEvent event) {
    	transition.showCanEdit();
    }
}
