package mvcViews;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import mvcModel.AllPagesModel;
import mvcModel.DirectoryTransitionModel;
import mvcModel.PersonModel;

public class AllPageTypesFlowController {
	PersonModel model;
	AllPagesModel allPagesModel;
	
    @FXML
    private Button companyPageTypeBtn;

    @FXML
    private Button jobPostingPageTypeBtn;

    @FXML
    private Button newsArticlePageTypeBtn;

    @FXML
    private Button personPageTypeBtn;

    @FXML
    private Button projectPageTypeBtn;

    @FXML
    private Button skillPageTypeBtn;
    
    @FXML
    private ListView<String> allPagesListView;

    @FXML
    void onCompanyPageClick(ActionEvent event) {
    	allPagesListView.setItems(allPagesModel.getCompanies());

    }

    @FXML
    void onJobPostingPageClick(ActionEvent event) {

    }

    @FXML
    void onNewsArticlePageClick(ActionEvent event) {

    }

    @FXML
    void onPersonPageClick(ActionEvent event) {
    	ObservableList<String> people = allPagesModel.getPeople();
    	allPagesListView.setItems(people);

    }

    @FXML
    void onProjectPageClick(ActionEvent event) {

    }

    @FXML
    void onSkillPageClick(ActionEvent event) {

    }

	public void setModel(PersonModel model, AllPagesModel allPagesModel)
	{
		this.model = model;
		this.allPagesModel = allPagesModel;
		allPagesListView.setItems(FXCollections.observableArrayList());
	}

}