package mvcModel;

import javafx.scene.control.TreeItem;

public interface GetTreeStrategy
{
	TreeItem<PageModel> getTree(PageModel rootPage);
}
