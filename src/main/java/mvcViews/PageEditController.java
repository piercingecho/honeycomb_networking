package mvcViews;

import javafx.event.ActionEvent;
import mvcModel.PageTransitionModel;
import mvcModel.PersonDemoModel;

public class PageEditController{
	PersonDemoModel model;
	PageTransitionModel transitionModel;
	public void setModel(PersonDemoModel newModel, PageTransitionModel transition) {
		model = newModel;
		transitionModel = transition;
	}
    public void onUpdateClick(ActionEvent event) {
    	transitionModel.showUneditable();
    }
    public void onCancelClick(ActionEvent event) {
    	transitionModel.showUneditable();
    }
}
