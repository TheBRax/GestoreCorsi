package it.brax.gestorecorsi;
	
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.brax.gestorecorsi.model.Model;
import it.brax.gestorecorsi.model.StudentByPeriod;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GestoreCorsi.fxml"));
			BorderPane root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			GestoreCorsiController controller = loader.getController();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Gestore Corsi");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
//		Model model = new Model();
//		try {
//			List<StudentByPeriod> studentByPeriod = new LinkedList<StudentByPeriod>();
//			studentByPeriod = model.getStudentByPeriod("1");
//			for(StudentByPeriod s : studentByPeriod) {
//				System.out.println(s.toString());
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
