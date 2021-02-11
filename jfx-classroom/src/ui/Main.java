package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private ClassroomGUI cgui;

	public static void main(String[] args) {
		launch(args);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane bdp = new BorderPane();
		bdp.setPrefHeight(717);
		bdp.setPrefWidth(753);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogInScreen.fxml"));
		cgui = new ClassroomGUI(bdp);
		fxmlLoader.setController(cgui);
		Parent root = fxmlLoader.load();
		bdp.setCenter(root);
		Scene scene = new Scene(bdp);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Classroom");
		primaryStage.show();
		
		
		
	}
	

}
