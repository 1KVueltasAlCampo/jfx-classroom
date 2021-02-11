package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ClassroomGUI {
		@FXML
		private VBox mainPanel;
		
		@FXML
	    private VBox logInPanel;
		
	 	@FXML
	    private TextField txtfLogInUsername;

	    @FXML
	    private TextField txtfLoginPassword;
	    @FXML
	    private TextField txtfSignUpUserName;

	    @FXML
	    private TextField txtfSignUpPassword;

	    @FXML
	    private TextField txtfProfilePhoto;

	    @FXML
	    private ToggleGroup gender;
	    
	    @FXML
	    private RadioButton rbMale;

	    @FXML
	    private RadioButton rbFemale;

	    @FXML
	    private RadioButton rbOther;

	    @FXML
	    private CheckBox cbSoftwareEngineering;

	    @FXML
	    private CheckBox cbTelematicEngineering;

	    @FXML
	    private CheckBox cbIndustrialEngineering;

	    @FXML
	    private DatePicker dpBirthday;

	    @FXML
	    private ChoiceBox<?> cbFavoriteBrowser;
	    
	    private BorderPane bdp = new BorderPane(); 
	    
	    public ClassroomGUI(BorderPane aux) {
	    	bdp = aux;
	    }
	    
	    public void initialize() {
	    	
	    }
	    @FXML
	    public void logInAction(ActionEvent event) {

	    }

	    @FXML
	    public void signUpAction(ActionEvent event) throws IOException {
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpScreen.fxml"));
	    	fxmlLoader.setController(this);
	    	Parent signUpPane = fxmlLoader.load();
	    	bdp.getChildren().clear();
	    	bdp.setCenter(signUpPane);
	    }

	    @FXML
	    public void browseAction(ActionEvent event) {

	    }

	    @FXML
	    public void createAccountAction(ActionEvent event) {

	    }

	    @FXML
	    public void signInAction(ActionEvent event) {

	    }
	    @FXML
	    public void logOutAction(ActionEvent event) {

	    }

	    
}
