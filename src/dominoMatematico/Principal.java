package dominoMatematico;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * La clase Principal es el punto de entrada de la aplicacion de domino. Extiende la clase 
 * Application de JavaFX y carga la interfaz grafica definida en DominoView.fxml.
 */
public class Principal extends Application {
    
    /**
     * El metodo main que lanza la aplicacion.
     *
     * @param args los argumentos de la linea de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metodo de inicio de la aplicacion JavaFX. Carga el archivo FXML y configura la escena.
     *
     * @param stage el escenario principal de la aplicacion
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DominoView.fxml"));
            
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