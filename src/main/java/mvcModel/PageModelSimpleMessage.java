package mvcModel;

import honeycombData.Company;
import honeycombData.Page;
import honeycombData.SimpleMessage;
import honeycombData.Storage;
import javafx.scene.layout.BorderPane;

public class PageModelSimpleMessage extends PageModel
{

	public PageModelSimpleMessage(Page page)
	{
		super(page);
		// TODO Auto-generated constructor stub
	}
	
	public PageModelSimpleMessage(String id)
	{
		this((SimpleMessage) Storage.pull(id));
	}

	@Override
	public SimpleMessage getAssociatedPage()
	{
		return (SimpleMessage) this.associatedPage;
	}

	@Override
	public TransitionModel getTransitionModel(BorderPane mainview)
	{
		//TODO create something else for this lol
		return new TransitionModelSimpleMessage(mainview, (PageModel) this);
		
	}
	
	@Override
	public String toString()
	{
		return this.description.getValue();
	}

}
