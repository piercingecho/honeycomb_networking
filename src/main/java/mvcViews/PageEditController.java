package mvcViews;


import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import mvcModel.PageModel;
import mvcModel.StorageModel;
import mvcModel.TransitionModelPage;

public class PageEditController{
	String pageId;
	PageModel model;

	TransitionModelPage transitionModel;
	
	@FXML
	Button cancelBtn;
	
	@FXML
	Button addLinkBtn;
	
	@FXML
	Button updateBtn;
	
    @FXML
    TextField nameField;
	
    @FXML
    TextArea descriptionField;

	public void setModel(String pageId, TransitionModelPage transition) {
		this.pageId = pageId;
		this.model = StorageModel.pull(pageId);
		transitionModel = transition;
		
    	Bindings.bindBidirectional(nameField.textProperty(), model.getName());
    	Bindings.bindBidirectional(descriptionField.textProperty(), model.getDescription());

	}
    public void onUpdateClick(ActionEvent event) {
    	model.updatePageInStorage();
    	
    	transitionModel.showCanEdit();
    }
    public void onCancelClick(ActionEvent event) {
    	transitionModel.showCanEdit();
    }
    
    public void onAddLinkClick(ActionEvent event) {
    	
    }
}
