package mvcViews;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import mvcModel.AllLinksModel;
import mvcModel.AllPagesModel;
import mvcModel.LoginNavigationModel;
import mvcModel.PersonModel;

public class PersonLinksTypesFlowController {
	PersonModel model; // this will be the page we're looking at
	AllLinksModel allLinksModel;

	
	public void setModel(PersonModel model, AllLinksModel allLinks)
	{
		this.model = model;
		this.allLinksModel = allLinks;
		allLinksListView.setItems(FXCollections.observableArrayList());
	}

    @FXML
    private ListView<String> allLinksListView;

    @FXML
    private Button companyPageTypeBtn;

    @FXML
    private Button jobPostingPageTypeBtn;

    @FXML
    private Button newsArticlePageTypeBtn;

    @FXML
    private Button personPageTypeBtn;

    @FXML
    private Button personPageTypeBtn1;

    @FXML
    private Button personPageTypeBtn2;

    @FXML
    private Button personPageTypeBtn3;

    @FXML
    private Button projectPageTypeBtn;

    @FXML
    private Button skillPageTypeBtn;

    @FXML
    void onEmployerButtonClick(ActionEvent event) {
    	ObservableList<String> employers = this.allLinksModel.getLinks("employer");

    	allLinksListView.setItems(employers);


    }

    @FXML
    void onFollowerButtonClick(ActionEvent event) {

    }

    @FXML
    void onFollowingButtonClick(ActionEvent event) {

    }

    @FXML
    void onFriendButtonClick(ActionEvent event) {
    	ObservableList<String> friends = this.allLinksModel.getLinks("friend");

    	allLinksListView.setItems(friends);

    }

    @FXML
    void onMentorButtonClick(ActionEvent event) {

    }

    @FXML
    void onNewsArticleButtonClick(ActionEvent event) {

    }

    @FXML
    void onProjectButtonClick(ActionEvent event) {

    }

    @FXML
    void onViewerButtonClick(ActionEvent event) {

    }
    
    //TODO delete this, for demo only
    @FXML
    void onClickAmazon(ActionEvent event)
    {
    	this.allLinksModel.showAmazon();
    }
    

}

