package mvcViews;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import mvcModel.GetTreeStrategyFullTree;
import mvcModel.PageModel;
import mvcModel.PageModelPerson;
import mvcModel.PageModelSimpleMessage;
import mvcModel.TransitionModelPerson;
import mvcModel.TransitionModelSimpleMessage;

public class SimpleMessageCanEditController {

	TransitionModelSimpleMessage transitionModel;
	PageModelSimpleMessage model;
	String messageId;
	
    @FXML
    private Label nameLabel;
    
    @FXML
    private TreeView<PageModel> MessageTreeView;

    
    
    public void setModel(String id, TransitionModelSimpleMessage newTransitionModel)
    {
    	transitionModel = newTransitionModel;
    	messageId = id;
    	
    	model = new PageModelSimpleMessage(id);
    	
    	// create the full tree here
		TreeItem<PageModel> treeRoot = model.getTree(new GetTreeStrategyFullTree());
    	MessageTreeView.setRoot(treeRoot);
		
    	Bindings.bindBidirectional(nameLabel.textProperty(), model.getName());
    	
    }
    
    @FXML
    public void onEditClick(ActionEvent event) 
    {
    	transitionModel.showEditable();
    }
    @FXML
    public void onFollowClick(ActionEvent event)
    {
    }
    @FXML
    public void onLinksClick(ActionEvent event)
    {
    	transitionModel.showLinks();
    }
    
}
