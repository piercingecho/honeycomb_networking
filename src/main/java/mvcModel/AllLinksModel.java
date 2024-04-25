package mvcModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

public class AllLinksModel
{
	BorderPane mainview;
	PageModel currentPage;
	String pageId;
	
	AllLinksModel(BorderPane mainview, String pageId)
	{
		this.mainview = mainview;
		this.pageId = pageId;
		this.currentPage = StorageModel.pull(pageId);
	}
	
	
	public ObservableList<PageModel> getLinks(String link)
	{
		return FXCollections.observableList(StorageModel.getAllLinkedPagesModels(currentPage.getAssociatedPage(), link));
	}

}
