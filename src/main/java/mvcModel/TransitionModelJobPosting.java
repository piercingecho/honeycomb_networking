package mvcModel;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import mvcViews.PageLinksJobPostingController;

public class TransitionModelJobPosting extends TransitionModelPage
{


	public TransitionModelJobPosting(BorderPane view, PageModel p)
	{
		super(view, p);
	}

	@Override
	public void showLinks()
	{
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(TransitionModelPerson.class
			        .getResource("../mvcViews/PageLinksJobPostingView.fxml"));
			    try {
			      Node view = loader.load();
			      mainview.setCenter(view);
			      PageLinksJobPostingController cont = loader.getController();
			      cont.setModel(new AllLinksModel(mainview, currentPage.getId().getValue()), mainview);
			    } catch (IOException e) {
			      e.printStackTrace();
			    }

	}

}