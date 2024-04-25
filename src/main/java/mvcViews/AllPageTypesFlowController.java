package mvcViews;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import mvcModel.AllPagesModel;
import mvcModel.PageModel;
import mvcModel.StorageModel;

public class AllPageTypesFlowController implements ShowItemInterface{
	
	BorderPane mainview;
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
    private Button listCellPageBtn;
    
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
    
    
    private ShowItemInterface getShowItemController()
    {
    	return this;
    }

	public void setModel(AllPagesModel allPagesModel, BorderPane mainview)
	{
		this.allPagesModel = allPagesModel;
		this.mainview = mainview;
		
    	allPagesListView.setCellFactory(new Callback<ListView<PageModel>, ListCell<PageModel>>()
		  {

			@Override
			public ListCell<PageModel> call(ListView<PageModel> listViewPages)
			{
				return new ListCellPage(listViewPages, getShowItemController(), mainview);
			}
//            @Override
//            protected void updateItem(PageModel page, boolean empty) {
//                super.updateItem(page, empty);
//                if (empty || page == null) {
//                    setText(null);
//                } else {
//                    setText(page.getName());
//                }
		  });

		allPagesListView.setItems(FXCollections.observableArrayList());
	}
	
    @FXML
    void onNewsArticlePageClick(ActionEvent event) {

    }
    
    @FXML
    void onProjectPageClick(ActionEvent event) {

    }

	@Override
	public void showItem(PageModel page)
	{
		listCellPageBtn.setText(page.toString());
	}
}