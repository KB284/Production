/****************************************************************************************************************
 *  Author:KFarah
 *  File:Main.java
 *  Date:9/19/2020
 *  Purpose:Executes the window application.
 ****************************************************************************************************************/


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class for the program.
 *
 * @author Khubaib Farah
 */
public class Main extends Application {

  /**
   * Main method of the program that starts the application.
   *
   * @param args the application would be launched from here.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Method for the Java FX program.
   *
   * @param primaryStage This is where the scenes of the program will take place
   * @throws Exception possible exception
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

    Scene scene = new Scene(root, 600, 600);

    primaryStage.setTitle("Production");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}

