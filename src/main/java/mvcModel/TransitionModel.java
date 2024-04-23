package mvcModel;

import javafx.scene.layout.BorderPane;

public abstract class TransitionModel {
	BorderPane mainview;
	PersonDemoModel model;
	String currentlyViewedId;
	public TransitionModel(BorderPane view, String id) {
		mainview = view;
		currentlyViewedId = id;
	}
	public abstract void showEditable();
	public abstract void showCanEdit();
	public abstract void showFollowed();
	public abstract void showNoEdit();
	public abstract void showLinks();
}
