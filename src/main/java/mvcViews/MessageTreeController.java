package mvcViews;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import mvcModel.PageModel;

public class MessageTreeController {

	public MessageTreeController()
	{
		
	}

	TreeItem<PageModel> root;
	
    @FXML
    private TreeView<PageModel> MessageTreeView;

    public void setModel(TreeItem<PageModel> root)
    {
    	this.root = root;
    	MessageTreeView.setRoot(root);
    	
    }
   
}
