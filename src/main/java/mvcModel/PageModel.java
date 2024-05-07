package mvcModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import honeycombData.Page;
import honeycombData.Person;
import honeycombData.Storage;


public abstract class PageModel
{
	Page associatedPage;
	StringProperty id;
	StringProperty name;
	StringProperty description;
	ObservableList<String> externalLinks;
	HashMap<String, ObservableList<String>> internalLinks;
	
	public PageModel(Page page)
	{
		this.associatedPage = page;
		
		this.id = new SimpleStringProperty();
		this.name = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
		this.externalLinks = FXCollections.observableArrayList();
		this.internalLinks = new HashMap<String, ObservableList<String>>();;
		
		// we return a null object if storage couldn't pull a page.
		if(this.associatedPage != null)
		{
			this.id.setValue(page.getId());
			this.name.setValue(associatedPage.getName());
			this.description.setValue(associatedPage.getDescription());
			
			this.initializeExternalLinks();	
			
			this.initializeInternalLinks();

		}
	}
	public PageModel(String id)
	{
		// storage.pull(id) creates a page. This calls its own constructor.
		this(Storage.pull(id));
	}

	abstract public TransitionModel getTransitionModel(BorderPane mainview);
	
	@Override
	public String toString()
	{
		return this.getName().getValue();
	}
	
	
	
	public Page getAssociatedPage()
	{
		return this.associatedPage;
	}
	
	private void initializeExternalLinks()
	{
		ArrayList<String> pageExternalLinks = associatedPage.getExternalLinks();
		for(int i=0; i<pageExternalLinks.size(); i++)
		{
			this.externalLinks.add(pageExternalLinks.get(i));
		}
	}
	
	private void initializeInternalLinks()
	{		
		//get all the links the page could be
		for(String linkType : this.associatedPage.rolesHas())
		{
			
		    ArrayList<String> currLinks = this.associatedPage.getInternalLinks(linkType);
		    
		    
		    // add the page's internal data to the PageModel
			ObservableList<String> newLinksList = FXCollections.observableArrayList();
			
			for(String link : currLinks)
			{
				newLinksList.add(link);
			}
			
			
			this.internalLinks.put(linkType, newLinksList);


		}
	}
	
	public boolean canBeEditedByUser()
	{
		String userId = SessionSingleton.getInstance().getUserId();
		Person user = (Person) Storage.pull(userId);
		return user.canEdit(this.associatedPage);
	}
	
	public boolean canBeViewedByUser()
	{
		String userId = SessionSingleton.getInstance().getUserId();
		Person user = (Person) Storage.pull(userId);
		return user.canView(this.associatedPage);
	}

	
	public boolean updatePageInStorage()
	{
		this.associatedPage.setName(this.name.getValue());
		this.associatedPage.setDescription(this.description.getValue());
		
		//setting external links
		ArrayList<String> updatedExternalLinks = new ArrayList<>();
		for(int i=0; i<this.externalLinks.size(); i++)
		{
			updatedExternalLinks.add(this.externalLinks.get(i));
		}

		this.associatedPage.setExternalLinks(updatedExternalLinks);
		
		//setting internal links
		
		HashMap<String, ArrayList<String>> updatedInternalLinks = new HashMap<>();
		
		for(String linkType : this.internalLinks.keySet())
		{
			ObservableList<String> modelInternalLink = this.internalLinks.get(linkType);
			
			ArrayList<String> updatedLinkContents = new ArrayList<>();
			for(int i=0; i<modelInternalLink.size(); i++)
			{
				updatedLinkContents.add(modelInternalLink.get(i));
			}
				
				
			updatedInternalLinks.put(linkType, updatedLinkContents);
		}
		
		this.associatedPage.setInternalLinks(updatedInternalLinks);


		return Storage.update(this.associatedPage);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * @return the id
	 */
	public StringProperty getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(StringProperty id)
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public StringProperty getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(StringProperty name)
	{
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public StringProperty getDescription()
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(StringProperty description)
	{
		this.description = description;
	}

	/**
	 * @return the externalLinks
	 */
	public ObservableList<String> getExternalLinks()
	{
		return externalLinks;
	}

	/**
	 * @param externalLinks the externalLinks to set
	 */
	public void setExternalLinks(ObservableList<String> externalLinks)
	{
		this.externalLinks = externalLinks;
	}

	/**
	 * @return the internalLinks
	 */
	public HashMap<String, ObservableList<String>> getInternalLinks()
	{
		return internalLinks;
	}

	/**
	 * @param internalLinks the internalLinks to set
	 */
	public void setInternalLinks(HashMap<String, ObservableList<String>> internalLinks)
	{
		this.internalLinks = internalLinks;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageModel other = (PageModel) obj;
		return Objects.equals(associatedPage, other.associatedPage);
	}
	
	public TreeItem<PageModel> getTree(GetTreeStrategy strat)
	{
		return strat.getTree(this);
	}
}
