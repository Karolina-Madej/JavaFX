
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{



        ResourceBundle bundlee = ResourceBundle.getBundle("messages");

        Parent root = FXMLLoader.load(getClass().getResource("Demo.fxml"),bundlee);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Demo.fxml"));

        loader.setResources(bundlee);
        primaryStage.setTitle(bundlee.getString("title"));
        //primaryStage.setTitle("Problem plecakowy");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);


    }
}
