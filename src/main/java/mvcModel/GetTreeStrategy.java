package mvcModel;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public interface GetTreeStrategy
{
	TreeItem<PageModel> getTree(PageModel rootPage);
}
