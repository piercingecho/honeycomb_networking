package mvcModel;

import honeycombData.Company;
import honeycombData.Page;
import honeycombData.Storage;
import javafx.scene.layout.BorderPane;

public class PageModelCompany extends PageModel
{

	public PageModelCompany(Page page)
	{
		super(page);
		// TODO Auto-generated constructor stub
	}
	
	public PageModelCompany(String id)
	{
		this((Company) Storage.pull(id));
	}

	@Override
	public Company getAssociatedPage()
	{
		return (Company) this.associatedPage;
	}

	@Override
	public TransitionModel getTransitionModel(BorderPane mainview)
	{
		return new TransitionModelCompany(mainview, (PageModel) this);
		
	}

}
