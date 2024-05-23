package mvcViews;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import mvcModel.PageModel;
import mvcModel.TransitionModelPage;

public class ListCellPageController {

	ListCellPage pageCell;
	PageModel pageModel;
	BorderPane mainview;
			
    @FXML
    private Button pageListCellBtn;

    @FXML
    void onPageListCellClick(ActionEvent event) {
    	TransitionModelPage transition = (TransitionModelPage) pageModel.getTransitionModel(mainview);
    	transition.showLinkedPage();
    	
    }

    public void setModel(ListCellPage cell, BorderPane mainview)
    {
    	this.pageCell = cell;
    	this.pageModel = null;
    	this.mainview = mainview;
    }
    
    public void updateView(PageModel pageModel)
    {
    	if(pageModel ==null)
    	{
    		pageListCellBtn.setVisible(false);
    		this.pageModel = null;
    	}
    	else
    	{
    		pageListCellBtn.setVisible(true);
    		pageListCellBtn.setText(pageModel.getName().getValue());
    		this.pageModel = pageModel;
    	}
    }
}
