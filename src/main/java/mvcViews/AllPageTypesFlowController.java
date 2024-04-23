package mvcViews;

import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import mvcModel.AllPagesModel;
import mvcModel.DirectoryTransitionModel;
import mvcModel.PageModel;
import mvcModel.PersonDemoModel;
import mvcModel.PageModelPerson;
import mvcModel.StorageModel;

public class AllPageTypesFlowController {
	PersonDemoModel model;
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
    private ListView<PageModel> allPagesListView;

//    allPagesListView.setCellFactory(param -> new ListCell<PageModel>() {
//        @Override
//        protected void updateItem(PageModel item, boolean empty) {
//            super.updateItem(item, empty);
//
//            if (empty || item == null || item.getName() == null) {
//                setText(null);
//            } else {
//                setText(item.getName());
//            }
//        }
//    });
    
    
    @FXML
    void onCompanyPageClick(ActionEvent event) {
    	allPagesListView.setItems(StorageModel.getAllCompanyModels());

    }

    @FXML
    void onJobPostingPageClick(ActionEvent event) {
    	allPagesListView.setItems(StorageModel.getAllJobModels());

    }


    @FXML
    void onPersonPageClick(ActionEvent event) {
    	allPagesListView.setItems(StorageModel.getAllPeopleModels());

    }

    @FXML
    void onSkillPageClick(ActionEvent event) {
    	allPagesListView.setItems(StorageModel.getAllSkillModels());

    }

	public void setModel(AllPagesModel allPagesModel)
	{
		this.model = model;
		this.allPagesModel = allPagesModel;
		allPagesListView.setItems(FXCollections.observableArrayList());
	}
	
    @FXML
    void onNewsArticlePageClick(ActionEvent event) {

    }
    
    @FXML
    void onProjectPageClick(ActionEvent event) {

    }
}