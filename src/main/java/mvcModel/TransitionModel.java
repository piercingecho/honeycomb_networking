package mvcModel;

import javafx.scene.layout.BorderPane;

public abstract class TransitionModel {
	BorderPane mainview;
	String currentlyViewedId;
	
	public TransitionModel(BorderPane view, PageModel currentPage) {
		mainview = view;
		currentlyViewedId = currentPage.getId().getValue();
	}
	
	public abstract void showEditable();
	public abstract void showCanEdit();
	public abstract void showFollowed();
	public abstract void showNoEdit();
	public abstract void showLinks();
}
