package mvcViews;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import mvcModel.AllLinksModel;
import mvcModel.PageModel;

public class PageLinksCompanyController implements ShowItemInterface {

	AllLinksModel allLinksModel;
	BorderPane mainview;
    @FXML
    private Button listCellPageBtn;

    private ShowItemInterface getShowItemController()
    {
    	return this;
    }
	@Override
	public void showItem(PageModel page)
	{
		listCellPageBtn.setText(page.toString());
	}
	
	public void setModel(AllLinksModel allLinksModel, BorderPane mainview)
	{
		this.allLinksModel = allLinksModel;
		this.mainview = mainview;
		
    	allLinksListView.setCellFactory(new Callback<ListView<PageModel>, ListCell<PageModel>>()
		  {

			@Override
			public ListCell<PageModel> call(ListView<PageModel> listViewPages)
			{
				return new ListCellPage(listViewPages, getShowItemController(), mainview);
			}
		  });

		allLinksListView.setItems(FXCollections.observableArrayList());

	}


    @FXML
    private ListView<PageModel> allLinksListView;

    @FXML
    private Button editorLinkButton;

    @FXML
    private Button employeeLinkBtn;

    @FXML
    private Button followerLinkBtn;

    @FXML
    private Button jobPostingLinkBtn;

    @FXML
    private Button mentorLinkBtn;

    @FXML
    private Button viewerLinkBtn;

    @FXML
    void onEditorBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("editor"));

    }

    @FXML
    void onEmployeeBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("employee"));

    }

    @FXML
    void onFollowerBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("follower"));

    }

    @FXML
    void onJobPostingBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("job_posting"));

    }

    @FXML
    void onMentorBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("mentor"));

    }

    @FXML
    void onViewerBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("viewer"));

    }

}
