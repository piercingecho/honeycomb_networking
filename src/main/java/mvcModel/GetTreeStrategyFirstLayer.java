package mvcModel;

import honeycombData.Page;
import honeycombData.RoleNotAllowedException;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class GetTreeStrategyFirstLayer implements GetTreeStrategy
{

	@Override
	public TreeItem<PageModel> getTree(PageModel rootPage)
	{
		try
		{
	        TreeItem<PageModel> rootItem = new TreeItem<PageModel>(rootPage);
	        // add children: for each reply, we create a new subtree for its 
	        // message.
	        Page currentPage = rootPage.getAssociatedPage();
	        
	        for(PageModel replyPageModel : StorageModel.getAllLinkedPagesModels(currentPage, "reply"))
	        {
	        	TreeItem<PageModel> replySubtree = new TreeItem<PageModel> (replyPageModel);
	        	rootItem.getChildren().add(replySubtree);
	        }

	        return rootItem;
		}
		catch (RoleNotAllowedException e)
		{
			//return null;
			return new TreeItem<PageModel> (null);
		}
	}

}
