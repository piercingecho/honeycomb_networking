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
	public abstract void showUneditable();
	public abstract void showFollowed();
	public abstract void showFollowedNoEdit();
	public abstract void showNoEdit();
}
