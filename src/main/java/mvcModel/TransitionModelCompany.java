package mvcModel;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import mvcViews.PageLinksCompanyController;

public class TransitionModelCompany extends TransitionModelPage
{


	public TransitionModelCompany(BorderPane view, PageModel p)
	{
		super(view, p);
	}

	@Override
	public void showLinks()
	{
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(TransitionModelPerson.class
			        .getResource("../mvcViews/PageLinksCompanyView.fxml"));
			    try {
			      Node view = loader.load();
			      mainview.setCenter(view);
			      PageLinksCompanyController cont = loader.getController();
			      cont.setModel(new AllLinksModel(mainview, currentPage.getId().getValue()), mainview);
			    } catch (IOException e) {
			      e.printStackTrace();
			    }

	}

}
