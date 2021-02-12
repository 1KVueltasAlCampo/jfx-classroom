package ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Classroom;
import model.UserAccount;

public class ClassroomGUI {
		private Desktop desktop = Desktop.getDesktop();
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
	    
	    @FXML
	    private TableView<UserAccount> tvUserAccountList;

	    @FXML
	    private TableColumn<UserAccount, String> tcUsername;

	    @FXML
	    private TableColumn<UserAccount, String> tcGender;

	    @FXML
	    private TableColumn<UserAccount, String> tcCareer;

	    @FXML
	    private TableColumn<UserAccount, String> tcBirthday;

	    @FXML
	    private TableColumn<UserAccount, String> tcBrowser;
	    
	    private BorderPane bdp = new BorderPane(); 
	    
	    private Classroom cr;
	    
	    public ClassroomGUI(BorderPane aux) {
	    	bdp = aux;
	    	cr = new Classroom();
	    	
	    }
	    
	    private void initializeTableView() {
	    	ObservableList<UserAccount> observableList;
	    	observableList = FXCollections.observableArrayList(cr.getUsers());
	    	
			tvUserAccountList.setItems(observableList);
			tcUsername.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("username")); 
			tcGender.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("gender"));
			tcCareer.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("career"));
			tcBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("birthday"));
			tcBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("browser"));
			
	    }
	    
	    public void initialize() {
	    	
	    }
	    @FXML
	    public void logInAction(ActionEvent event) throws IOException {
	    	if(cr.logInVerification(txtfLogInUsername.getText(), txtfLoginPassword.getText())) {
	    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListScreen.fxml"));
				fxmlLoader.setController(this);
				Parent logInPane = fxmlLoader.load();
		    	
				bdp.getChildren().clear();
		    	bdp.setCenter(logInPane);
		    	initializeTableView();	
	    	}
	    	else {
	    		txtfLogInUsername.setText("");
	    		txtfLoginPassword.setText("");
	    		doAnAlert("Log In Incorrect","The Username and the password given are incorrect","Error");
	    		/*
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    	    alert.setTitle("Log In Incorrect");
	    	    alert.setContentText("The Username and the password given are incorrect");
	    	    alert.setHeaderText("Error");
	    	    alert.showAndWait();
	    	    */
	    	}
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
	    public void browseAction(ActionEvent event) throws IOException {
	    	
	    	String ubication = txtfProfilePhoto.getText();
	    	File photo = new File(ubication);
	    	if(!photo.exists()) {
	    		FileChooser fileChooser = new FileChooser();
		    	fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif"));
		    	fileChooser.setTitle("Open Resource File");
		    	Window stage = null;
		    	if(!ubication.equals("")) {
		    		fileChooser.setInitialDirectory(photo);
		    	}
		    	
		    	try {
		    		File information = fileChooser.showOpenDialog(stage);
		    		if (information != null) {
		                openFile(information);
		                ubication = information.getAbsolutePath();
		    			txtfProfilePhoto.setText(ubication);
		            }
					else {
						doAnAlert("Invalid profile Photo","You have not entered a valid profile photo","Error");
					}
		    	} catch(IllegalArgumentException ex) {
		    		doAnAlert("Invalid profile Photo","You have not entered a valid profile photo","Error");
		    	    fileChooser.setInitialDirectory(null);
		    	}
	    	}
	    	else {
	    		openFile(photo);
                ubication = photo.getAbsolutePath();
    			txtfProfilePhoto.setText(ubication);
	    	}
	    	
	  
	    }
	   
	    private void doAnAlert(String title,String content,String header) {
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle(title);
    	    alert.setContentText(content);
    	    alert.setHeaderText(header);
    	    alert.showAndWait();
	    }
	    
	    private void openFile(File file) {
	        try {
	            desktop.open(file);
	        } catch (IOException ex) {
	            Logger.getLogger(ClassroomGUI.class.getName()).log(
	                Level.SEVERE, null, ex
	            );
	        }
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
