package mvcModel;

import javafx.scene.layout.BorderPane;

public abstract class TransitionModel {
	BorderPane mainview;
	PersonModel model;
	public TransitionModel(BorderPane view, PersonModel newModel) {
		mainview = view;
		model = newModel;
	}
	public abstract void showEditable();
	public abstract void showUneditable();
	public abstract void showFollowed();
	public abstract void showFollowedNoEdit();
	public abstract void showNoEdit();
}
