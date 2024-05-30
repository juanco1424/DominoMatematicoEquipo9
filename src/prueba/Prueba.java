package prueba;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Prueba extends Application {
    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PruebaView.fxml"));
            //loader.setController(new PruebaViewController());
            
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
        
            stage.setScene(scene);
            stage.setTitle("Domino Game");
            stage.show();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
