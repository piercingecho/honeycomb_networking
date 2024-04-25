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

public class PageLinksJobPostingController implements ShowItemInterface{

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

	AllLinksModel allLinksModel;
	BorderPane mainview;

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
    private Button applicantLinkBtn;

    @FXML
    private Button contributorLinkBtn;

    @FXML
    private Button editorLinkBtn;

    @FXML
    private Button followerLinkBtn;

    @FXML
    private Button mentorLinkBtn;

    @FXML
    private Button qualificationLinkBtn;

    @FXML
    private Button viewerLinkBtn;

    @FXML
    void onApplicantBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("applicant"));

    }

    @FXML
    void onContributorBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("contributor"));

    }

    @FXML
    void onEditorBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("editor"));

    }

    @FXML
    void onFollowerBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("follower"));

    }

    @FXML
    void onMentorBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("mentor"));

    }

    @FXML
    void onQualificationBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("qualification"));

    }

    @FXML
    void onViewerBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("viewer"));

    }

}
