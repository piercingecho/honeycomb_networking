package mvcViews;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import mvcModel.PageModel;

public class ListCellPage extends ListCell<PageModel>
{

	ShowItemInterface showController;
	ListCellPageController listCellPageController;
	Node node;
	BorderPane mainview;
	
	public ListCellPage(ListView<PageModel> list,ShowItemInterface cont, BorderPane mainview)//, PageModel page)
	{
		this.mainview = mainview;
		showController = cont;
		
		FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(ListCellPage.class
	        .getResource("ListCellPage.fxml"));
	    try {
	      node = loader.load(); //store for later
	      
	      listCellPageController = loader.getController(); //store for later
	      listCellPageController.setModel(this, mainview);//, page);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    
	    setGraphic(node);
		
		
		
		
		
		
	}

//
//	@Override
//	protected void updateItem(PageModel pageModel, boolean empty)
//	{
//		if(!empty)
//		{
//			itemController.updateView(pageModel);
//		}
//		else
//		{
//			itemController.updateView(null);
//		}
//		super.updateItem(pageModel, empty);
//	}

    @Override
    public void updateItem(PageModel pageModel, boolean empty) {
        super.updateItem(pageModel, empty);
    	if (empty || pageModel == null) {
        	listCellPageController.updateView(null);
        } else {
        	listCellPageController.updateView(pageModel);
        }
        
    }
	
	public void showItem()
	{
		showController.showItem(getItem());
	}
	

	
}