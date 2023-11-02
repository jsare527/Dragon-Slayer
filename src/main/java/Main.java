import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for the application. This class contains a main method which
 * launches the application.
 */

public class Main extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Startup code for the application. This loads the FXML and controller and
     * creates the window to display it.
     * @param primaryStage The primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        window = primaryStage;

        Scene scene = new Scene(root, 1200, 700);

        window.setTitle("Dragon's Lair Pull List");

        window.setScene(scene);
        window.setMinHeight(700);
        window.setMinWidth(1200);
        window.show();
    }
}
