package ui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Classroom;
import model.UserAccount;

public class ClassroomGUI {
	
		ObservableList<String> favoriteBrowserList = FXCollections.observableArrayList("Select an option","Chrome","Edge","Mozilla","Opera");
	
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
	    private ChoiceBox<String> chobFavoriteBrowser;
	    
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
	    
	    @FXML
	    private Label lbUsername;

	    @FXML
	    private ImageView ivProfilePhoto;
	    
	    private BorderPane bdp = new BorderPane(); 
	    
	    private Classroom cr;
	    
	    
	    public ClassroomGUI(BorderPane aux) {
	    	bdp = aux;
	    	cr = new Classroom();
	    	chobFavoriteBrowser = new ChoiceBox<String>();
	  
	    	
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
	    @FXML
	    public void initialize() {
	    	chobFavoriteBrowser.setValue("Select an option");
	    	chobFavoriteBrowser.setItems(favoriteBrowserList);
	    	
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
		 
		    	lbUsername.setText(txtfLogInUsername.getText());
		    	
		    	String string = cr.getTheProfilePhoto(cr.findAnUser(txtfLogInUsername.getText()));
		    	InputStream stream = new FileInputStream(string);
		    	Image image = new Image(stream);
		    	
		    	
		    	ivProfilePhoto.setImage(image);
		    	
		    	
	    	}
	    	else {
	    		txtfLogInUsername.setText("");
	    		txtfLoginPassword.setText("");
	    		doAnAlert("Log In Incorrect","The Username and the password given are incorrect","Error");
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
	    public void createAccountAction(ActionEvent event) throws IOException {
			String vUsername = txtfSignUpUserName.getText();
			String vPassword = txtfSignUpPassword.getText();
			String vProfilePhoto = txtfProfilePhoto.getText();
			String vGender = whatGender();
			String vCareer = howManyCareers();
			String vBirthday = "";
			try {
				vBirthday= dpBirthday.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}catch(NullPointerException np) {
				vBirthday  = "";
			}
			String vBrowser = chobFavoriteBrowser.getValue();
			if(verifyInformation(vUsername, vPassword, vProfilePhoto, vGender, vCareer, vBirthday, vBrowser)) {
				UserAccount student = new UserAccount(vUsername, vPassword, vProfilePhoto, vGender, vCareer, vBirthday, vBrowser);
				cr.addUsers(student);
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogInScreen.fxml"));
		    	fxmlLoader.setController(this);
		    	Parent signUpPane = fxmlLoader.load();
		    	bdp.getChildren().clear();
		    	bdp.setCenter(signUpPane);
			}
			else {
				doAnAlert("Missing information","Please enter valid data to everything that is requested in the form","Error");
			}
	    }
		
		public boolean verifyInformation(String vUsername,String vPassword,String vProfilePhoto,String vGender,String vCareer,String vBirthday, String vBrowser ) {
			if(!vUsername.equals("")&&!vPassword.equals("")&&!vProfilePhoto.equals("")&&!vGender.equals("")&&!vCareer.equals("")&&!vBirthday.equals("")&&!vBrowser.equals("Select an option")) {
				File image = new File(vProfilePhoto);
				if(image.exists()) {
					return true;
				}
			}
			return false;
		}
		public String whatGender() {
			String wGender = "";
			if(rbMale.isSelected()) {
				wGender = "Male";
			}
			else if(rbFemale.isSelected()) {
				wGender = "Female";
			}
			else if(rbOther.isSelected()) {
				wGender="Other";
			}
			return wGender;
		}
		
		public String howManyCareers() {
			String hCareers = "";
			if(cbSoftwareEngineering.isSelected()) {
				hCareers+="\nSoftware Engineering ";
			}
			if(cbTelematicEngineering.isSelected()) {
				hCareers+="\nTelematic Engineering ";
			}
			if(cbIndustrialEngineering.isSelected()) {
				hCareers+="\nIndustrial Engineering";
			}
			return hCareers;
		}

	    @FXML
	    public void signInAction(ActionEvent event) throws IOException {
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogInScreen.fxml"));
	    	fxmlLoader.setController(this);
	    	Parent signUpPane = fxmlLoader.load();
	    	bdp.getChildren().clear();
	    	bdp.setCenter(signUpPane);
	    }
	    @FXML
	    public void logOutAction(ActionEvent event) throws IOException {
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogInScreen.fxml"));
	    	fxmlLoader.setController(this);
	    	Parent signUpPane = fxmlLoader.load();
	    	bdp.getChildren().clear();
	    	bdp.setCenter(signUpPane);
	    }

	    
}
