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

public class PageLinksPersonController implements ShowItemInterface {

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
	

    @FXML
    private ListView<PageModel> allLinksListView;

    @FXML
    private Button editorLinkBtn;

    @FXML
    private Button employerLinkBtn;

    @FXML
    private Button followerLinkBtn;

    @FXML
    private Button followingLinkBtn;

    @FXML
    private Button friendLinkBtn;

    @FXML
    private Button mentorLinkBtn;

    @FXML
    private Button newsArticleLinkBtn;

    @FXML
    private Button projectLinkBtn;

    @FXML
    private Button viewerLinkBtn;

    @FXML
    void onEditorBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("editor"));
    }

    @FXML
    void onEmployerBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("employer"));

    }

    @FXML
    void onFollowerBtnClick(ActionEvent event) {

		allLinksListView.setItems(allLinksModel.getLinks("follower"));

    }

    @FXML
    void onFollowingBtnClick(ActionEvent event) {

		allLinksListView.setItems(allLinksModel.getLinks("following"));

    }

    @FXML
    void onFriendBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("friend"));

    }

    @FXML
    void onMentorBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("mentor"));

    }

    @FXML
    void onNewsArticleBtnClick(ActionEvent event) {
		allLinksListView.setItems(FXCollections.observableArrayList());

    }

    @FXML
    void onProjectBtnClick(ActionEvent event) {
		allLinksListView.setItems(FXCollections.observableArrayList());
    }

    @FXML
    void onViewerBtnClick(ActionEvent event) {
		allLinksListView.setItems(allLinksModel.getLinks("viewer"));

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

}
