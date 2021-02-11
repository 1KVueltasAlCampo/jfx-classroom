package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	
	private ClassroomGUI cgui;
	private BorderPane bdp;

	public static void main(String[] args) {
		launch(args);
	}
	
	public Main() {
		bdp = new BorderPane();
		bdp.setPrefHeight(717);
		bdp.setPrefWidth(753);
		cgui = new ClassroomGUI(bdp);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogInScreen.fxml"));
		fxmlLoader.setController(cgui);
		Parent root = fxmlLoader.load();
		bdp.setCenter(root);
		Scene scene = new Scene(bdp);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Classroom");
		primaryStage.show();
		
		
		
	}
	

}
