package mvcViews;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import mvcMain.Main;
import mvcModel.PersonModel;
import mvcModel.PersonTransitionModel;

public class ListCellPage extends ListCell<PageModel>
{
	PageModelController cont;
	Node node;
	
	public ListCellPerson(ListView<PersonModel> view)
	{
		FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(Main.class.getResource("../mvcViews/ListCellPerson.fxml"));

	    try
	    {
	    	node = loader.load();
	    	cont = loader.getController();
	    	cont.setModel(this);
	    	this.setGraphic(node);
	    }
	}

}
