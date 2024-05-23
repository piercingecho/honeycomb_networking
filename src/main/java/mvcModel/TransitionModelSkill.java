package mvcModel;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import mvcViews.PageLinksSkillController;

public class TransitionModelSkill extends TransitionModelPage
{


	public TransitionModelSkill(BorderPane view, PageModel p)
	{
		super(view, p);
	}

	@Override
	public void showLinks()
	{
		FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(TransitionModelPerson.class
			        .getResource("../mvcViews/PageLinksSkillView.fxml"));
		 try {
			      Node view = loader.load();
			      mainview.setCenter(view);
			      PageLinksSkillController cont = loader.getController();
			      cont.setModel(new AllLinksModel(mainview, currentPage.getId().getValue()), mainview);

			    } catch (IOException e) {
			      e.printStackTrace();
			    }
	}









}