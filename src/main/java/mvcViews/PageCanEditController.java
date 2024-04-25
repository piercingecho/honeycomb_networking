package mvcViews;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mvcModel.TransitionModelPage;
import mvcModel.PageModel;
import mvcModel.StorageModel;

public class PageCanEditController
{
	String personId;
	PageModel model;
	TransitionModelPage transition;
	
	public void setModel(String id, TransitionModelPage transitionModel)
	{
		personId = id;
		model = StorageModel.pull(id);
		transition = transitionModel;
		System.out.println(model.getName());
		System.out.println(model.getDescription());
    	Bindings.bindBidirectional(nameLabel.textProperty(), model.getName());
    	Bindings.bindBidirectional(descriptionLabel.textProperty(), model.getDescription());

	}
    @FXML
    private Label descriptionLabel;

    @FXML
    private Label nameLabel;
    
    @FXML
    private Button editBtn;
    
    @FXML
    private Button linksBtn;
    
    @FXML
    public void onEditClick(ActionEvent event)
    {
    	transition.showEditable();
    }
    @FXML
    public void onFollowClick(ActionEvent event)
    {
    	transition.showFollowed();
    }
    @FXML
    public void onUnfollowClick(ActionEvent event)
    {
    	transition.showCanEdit();
    }
    @FXML
    public void onLinksClick(ActionEvent event)
    {
    	transition.showLinks();
    }
    
}
