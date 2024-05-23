package mvcModel;

import honeycombData.Page;
import javafx.scene.layout.BorderPane;

public class PageModelSkill extends PageModel
{

	public PageModelSkill(Page page)
	{
		super(page);
		// TODO Auto-generated constructor stub
	}
	
	public PageModelSkill(String id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}


	@Override
	public TransitionModel getTransitionModel(BorderPane mainview)
	{
		return new TransitionModelSkill(mainview, (PageModel) this);

		
	}

}