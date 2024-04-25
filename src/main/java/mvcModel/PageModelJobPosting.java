package mvcModel;

import honeycombData.Page;
import javafx.scene.layout.BorderPane;

public class PageModelJobPosting extends PageModel
{

	public PageModelJobPosting(Page page)
	{
		super(page);
		// TODO Auto-generated constructor stub
	}
	
	public PageModelJobPosting(String id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}


	@Override
	public TransitionModel getTransitionModel(BorderPane mainview)
	{
		return new TransitionModelJobPosting(mainview, (PageModel) this);
		
	}

}