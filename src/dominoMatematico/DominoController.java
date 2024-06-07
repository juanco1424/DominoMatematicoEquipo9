package dominoMatematico;

import java.net.URL;
import java.util.*;

import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DominoController implements Initializable {

    @FXML
    private ListView<Domino> listFichas;

    private List<Image> dominoImages;

    // Bool el cual indicará cuando es el turno del jugador y cuando será el turno de la IA
    private final boolean turnoJugador = true;

    @FXML
    private Button btnPasar;

    // Esta Ficha es de referencia, se utiliza para saber cuál es la última ficha puesta al lado Izquierdo.
    private Domino ultIzquierda;
    
    // Esta Ficha es de referencia, se utiliza para saber cuál es la última ficha puesta al lado Derecho.
    private Domino ultDerecha;
    
    // La Lista de las Fichas puestas en el Tablero de Dominó.
    private List<Domino> fichasTablaDomino;
    
    // Este usuario es el Jugador, aquí se encuentran las fichas del Jugador.
    private Usuario jugador;
    
    // Este usuario es la IA, aquí se encuentran sus fichas.
    private Usuario ia;
    
    // Lista de todas las fichas disponibles en juego
    List<Domino> allFichas;
    
    // ListView de la Tabla de Dominó.
    @FXML
    private ListView<Domino> tablaDomino;
    
    // Label el cual se utilizará para ilustrar en pantalla de quién es el turno.
    @FXML
    private Label turneLabel;
    
    // Label el cual servirá de indicador para que el Jugador sepa cuantas fichas tiene la IA.
    @FXML
    private Label fichasIALabel;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dominoImages = new ArrayList<>();
        fichasTablaDomino = new ArrayList<>();
        
        jugador = new Usuario(new ArrayList<>());
        ia = new Usuario(new ArrayList<>());
        
        // Métodos necesarios...
        
        // Cargar Imágenes
        loadImages();
        
        // Sortear Fichas y Repartir
        sortFichas();
        
        // Ajustar el ListView del Jugador
        setupPlayerHandListView();
        
        // Ajustador el ListView del Tablero
        setupListaFichasTabla();
    }

    private void loadImages() {
        dominoImages = new ArrayList<>();

        try {
            for (int i = 0; i <= 6; i++) {
                dominoImages.add(new Image(Objects.requireNonNull(getClass().getResource("/domino_" + i + ".png")).toString()));
            }
        } catch (Exception e) {
            showAlert("Error loading images.");
        }
    }
    
    private void selectTile(Domino domino) {
        if(!turnoJugador) {
            showAlert("Aún no es tu TURNO.");
            return;
        }
        
        // La primera ficha (Vertical)
        if(fichasTablaDomino.isEmpty()) {
            if(domino.isDouble()) {
                // Cumple la regla de la primera ficha
                addFichaTabla(domino , false);
                
                ultIzquierda = domino;
                ultDerecha = domino;
            } else {
                showAlert("La primera Ficha debe ser un doble.");
                return;
            }
            
            jugador.getFichas().remove(domino);
            
            // Función para cambiar de Jugador a IA y viceversa.
            //turnoJugador = !turnoJugador;

        } else {
            if(domino.isDouble()) {
                // Ficha cualquiera (Vertical)
                if(ultIzquierda.getLeft() == domino.getLeft() && !ultIzquierda.getInvertir()) {
                    // Último a la izquierda, pero es la parte izquierda (invertir)

                    domino.setInvertir(true);
                    addFichaTabla(domino , true);
                    ultIzquierda = domino;

                } else if(ultIzquierda.getLeft() == domino.getRight() && !ultIzquierda.getInvertir()) {
                    // Último a la izquierda y es la parte derecha (no se necesita invertir)

                    addFichaTabla(domino , true);
                    ultIzquierda = domino;

                // Parte invertida
                } else if(ultIzquierda.getRight() == domino.getLeft() && ultIzquierda.getInvertir()) {
                    // Esta es por si está invertida la ficha para el lado derecho

                    // Ya como está invertida, la ficha derecha contaría como ficha izquierda, y continuando con el mismo concepto
                    // Se debe invertir
                    domino.setInvertir(true);
                    //Con este añadido, se añadirá la ficha de Dominó para la izquierda.
                    addFichaTabla(domino , true); //Sin necesidad de organizar orden
                    ultIzquierda = domino;

                } else if(ultIzquierda.getRight() == domino.getRight() && ultIzquierda.getInvertir()) {
                    // Esta es por si está invertida la ficha para el lado derecho

                    // Ya como está invertida, la ficha derecha contaría como ficha izquierda, y continuando con el mismo concepto
                    // En está caso, no se debe invertir

                    //Con este añadido, se añadirá la ficha de Dominó para la izquierda.
                    addFichaTabla(domino , true); //Sin necesidad de organizar orden
                    ultIzquierda = domino;

                } else if(ultDerecha.getRight() == domino.getLeft() && !ultDerecha.getInvertir()) {
                    // Último a la derecha y es la parte izquierda (no se necesita invertir)

                    //Con este añadido, se añadirá la ficha de Dominó para la derecha.
                    addFichaTabla(domino , false); //Sin necesidad de organizar orden
                    ultDerecha = domino;

                } else if(ultDerecha.getRight() == domino.getRight() && !ultDerecha.getInvertir()) {
                    // Último a la derecha, pero es la parte derecha (invertir)

                    domino.setInvertir(true);
                    //Con este añadido, se añadirá la ficha de Dominó para la derecha.
                    addFichaTabla(domino , false); //Ahora se organizará el orden correcto de la ficha
                    ultDerecha = domino;

                // Parte invertida
                } else if(ultDerecha.getLeft() == domino.getLeft() && ultDerecha.getInvertir()) {
                    // Esta es por si está invertida la ficha para el lado izquierdo

                    // Ya como está invertida, la ficha izquierda contaría como ficha derecha, y continuando con el mismo concepto
                    // En está caso, no se debe invertir

                    //Con este añadido, se añadirá la ficha de Dominó para la izquierda.
                    addFichaTabla(domino , false); //Sin necesidad de organizar orden
                    ultDerecha = domino;
                } else if(ultDerecha.getLeft() == domino.getRight() && ultDerecha.getInvertir()) {
                    // Esta es por si está invertida la ficha para el lado izquierdo

                    // Ya como está invertida, la ficha izquierda contaría como ficha derecha, y continuando con el mismo concepto
                    // En está caso, si se debe invertir
                    domino.setInvertir(true);

                    //Con este añadido, se añadirá la ficha de Dominó para la izquierda.
                    addFichaTabla(domino , false); //Sin necesidad de organizar orden
                    ultDerecha = domino;
                } else {
                    showAlert("Esta ficha no puede ser usada");
                    return;
                }

                jugador.getFichas().remove(domino);

                // Función para cambiar de Jugador a IA y viceversa.
                //turnoJugador = !turnoJugador;

            } else {
                // Ficha cualquiera (Horizontal)
                if(ultIzquierda.getLeft() == domino.getLeft() && !ultIzquierda.getInvertir()) {
                    // Último a la izquierda, pero es la parte izquierda (invertir)

                    domino.setInvertir(true);
                    //Con este añadido, se añadirá la ficha de Dominó para la izquierda.
                    addFichaTabla(domino , true); //Ahora se organizará el orden correcto de la ficha
                    ultIzquierda = domino;

                } else if(ultIzquierda.getLeft() == domino.getRight() && !ultIzquierda.getInvertir()) {
                    // Último a la izquierda y es la parte derecha (no se necesita invertir)

                    //Con este añadido, se añadirá la ficha de Dominó para la izquierda.
                    addFichaTabla(domino , true); //Sin necesidad de organizar orden
                    ultIzquierda = domino;

                // Parte para fichas invertidas
                } else if(ultIzquierda.getRight() == domino.getLeft() && ultIzquierda.getInvertir()) {
                    // Esta es por si está invertida la ficha para el lado derecho

                    // Ya como está invertida, la ficha derecha contaría como ficha izquierda, y continuando con el mismo concepto
                    // Se debe invertir
                    domino.setInvertir(true);
                    //Con este añadido, se añadirá la ficha de Dominó para la izquierda.
                    addFichaTabla(domino , true); //Sin necesidad de organizar orden
                    ultIzquierda = domino;

                } else if(ultIzquierda.getRight() == domino.getRight() && ultIzquierda.getInvertir()) {
                    // Esta es por si está invertida la ficha para el lado derecho

                    // Ya como está invertida, la ficha derecha contaría como ficha izquierda, y continuando con el mismo concepto
                    // En está caso, no se debe invertir

                    //Con este añadido, se añadirá la ficha de Dominó para la izquierda.
                    addFichaTabla(domino , true); //Sin necesidad de organizar orden
                    ultIzquierda = domino;


                } else if(ultDerecha.getRight() == domino.getLeft() && !ultDerecha.getInvertir()) {
                    // Último a la derecha y es la parte izquierda (no se necesita invertir)

                    //Con este añadido, se añadirá la ficha de Dominó para la derecha.
                    addFichaTabla(domino , false); //Sin necesidad de organizar orden
                    ultDerecha = domino;


                } else if(ultDerecha.getRight() == domino.getRight() && !ultDerecha.getInvertir()) {
                    // Último a la derecha, pero es la parte derecha (invertir)

                    domino.setInvertir(true);
                    //Con este añadido, se añadirá la ficha de Dominó para la derecha.
                    addFichaTabla(domino , false); //Ahora se organizará el orden correcto de la ficha

                    ultDerecha = domino;


                // Parte para fichas invertidas
                } else if(ultDerecha.getLeft() == domino.getLeft() && ultDerecha.getInvertir()) {
                    // Esta es por si está invertida la ficha para el lado izquierdo

                    // Ya como está invertida, la ficha izquierda contaría como ficha derecha, y continuando con el mismo concepto
                    // En está caso, no se debe invertir

                    //Con este añadido, se añadirá la ficha de Dominó para la izquierda.
                    addFichaTabla(domino , false); //Sin necesidad de organizar orden
                    ultDerecha = domino;

                } else if(ultDerecha.getLeft() == domino.getRight() && ultDerecha.getInvertir()) {
                    // Esta es por si está invertida la ficha para el lado izquierdo

                    // Ya como está invertida, la ficha izquierda contaría como ficha derecha, y continuando con el mismo concepto
                    // En está caso, si se debe invertir
                    domino.setInvertir(true);

                    //Con este añadido, se añadirá la ficha de Dominó para la izquierda.
                    addFichaTabla(domino , false); //Sin necesidad de organizar orden
                    ultDerecha = domino;
                } else {
                    showAlert("Esta ficha no puede ser usada");
                    return;
                }

                jugador.getFichas().remove(domino);

                // Función para cambiar de Jugador a IA y viceversa.
                //turnoJugador = !turnoJugador;
            }
        }
        
        notifyChanges();
    }
    
    private void notifyChanges() {
        // Fichas Jugador
        listFichas.getItems().setAll(jugador.getFichas());
        
        // Fichas Tabla
        tablaDomino.getItems().setAll(fichasTablaDomino);
        
        // De quien es el Turno (Label)
        if(turnoJugador) {
            turneLabel.setText("TU TURNO");
        } else {
            turneLabel.setText("TURNO DE LA IA");
        }
        
        // Fichas de la IA
        fichasIALabel.setText("Fichas IA: " + ia.getFichas().size());
        
        
        // Visualizador de Último Izquierda y Último Derecha
        /* if(ultIzquierda != null) {
        //    System.out.println("Último Izquierda:\n Izquierda: " + ultIzquierda.getLeft() + "\n Derecha: " + ultIzquierda.getRight() + "\n Esta Invertida: " + ultIzquierda.getInvertir());
        // }

        // if(ultDerecha != null) {
        //    System.out.println("\n\nÚltimo Derecha:\n Izquierda: " + ultDerecha.getLeft() + "\n Derecha: " + ultDerecha.getRight() + "\n Esta Invertida: " + ultDerecha.getInvertir());
        // } */

    }
        
    private void addFichaTabla(Domino domino , boolean index0) {
        if(index0) {
            fichasTablaDomino.add(0 , domino);
        } else {
            fichasTablaDomino.add(domino);
        }
    }

    private void sortFichas() {
        // Lista de todas las fichas
        allFichas = new ArrayList<>();
        
        // Añadiremos todas las fichas disponibles en orden para después sortearlas.
        allFichas.add(new Domino(0 , 0 , false));
        allFichas.add(new Domino(0 , 1 , false));
        allFichas.add(new Domino(0 , 2 , false));
        allFichas.add(new Domino(0 , 3 , false));
        allFichas.add(new Domino(0 , 4 , false));
        allFichas.add(new Domino(0 , 5 , false));
        allFichas.add(new Domino(0 , 6 , false));
        
        allFichas.add(new Domino(1 , 1 , false));
        allFichas.add(new Domino(1 , 2 , false));
        allFichas.add(new Domino(1 , 3 , false));
        allFichas.add(new Domino(1 , 4 , false));
        allFichas.add(new Domino(1 , 5 , false));
        allFichas.add(new Domino(1 , 6 , false));
        
        allFichas.add(new Domino(2 , 2 , false));
        allFichas.add(new Domino(2 , 3 , false));
        allFichas.add(new Domino(2 , 4 , false));
        allFichas.add(new Domino(2 , 5 , false));
        allFichas.add(new Domino(2 , 6 , false));
        
        allFichas.add(new Domino(3 , 3 , false));
        allFichas.add(new Domino(3 , 4 , false));
        allFichas.add(new Domino(3 , 5 , false));
        allFichas.add(new Domino(3 , 6 , false));
        
        allFichas.add(new Domino(4 , 4 , false));
        allFichas.add(new Domino(4 , 5 , false));
        allFichas.add(new Domino(4 , 6 , false));
        
        allFichas.add(new Domino(5 , 5 , false));
        allFichas.add(new Domino(5 , 6 , false));
        
        allFichas.add(new Domino(6 , 6 , false));

        // Valor Random de cuantas veces se revolverán las fichas
        int randomsCycles = new Random().nextInt(15);
        
        // Revolver fichas varias veces dependiendo el Valor Random de 'randomsCycles'
        for(int i = 0; i < randomsCycles; i++) {
            Collections.shuffle(allFichas);
        }
        
        // Este while se utilizará para sortear en 8 fichas al jugador y a la IA
        while (jugador.getFichas().size() < 8 || ia.getFichas().size() < 8) {
            //TODO: El randomGet siempre dara 0, por que la necesidad de pedir un numero random quede cero?
            // Valor Random para saber quien toma la siguiente ficha
            int randomGet = new Random().nextInt(1);
            
            // Si es 0 (Jugador) y sus fichas no superan las 8 permitidas de inicio se le da esa ficha
            if(randomGet == 0 && jugador.getFichas().size() < 8) {
                int index = new Random().nextInt(allFichas.size());
                
                jugador.getFichas().add(allFichas.get(index));
                allFichas.remove(index);
                
            // Si, por el contrario, es 1 (IA) y sus fichas no superan las 8 permitidas de inicio se le da esa ficha
            } else if(ia.getFichas().size() < 8) {
                int index = new Random().nextInt(allFichas.size());

                ia.getFichas().add(allFichas.get(index));
                allFichas.remove(index);
            }
        }
        
        // System.out.println("IA: " + ia.getFichas().size() + "\n\n Jugador: " + jugador.getFichas().size() + "\n\n Fichas Totales: " + allFichas.size());
        
        notifyChanges();
    }

    private void setupPlayerHandListView() {
        listFichas.setCellFactory((ListView<Domino> listView) -> new ListCell<>() {
            @Override
            protected void updateItem(Domino domino, boolean empty) {
                super.updateItem(domino, empty);
                if (domino != null && !empty) {
                    HBox hBox = new HBox();
                    ImageView topImage = new ImageView(dominoImages.get(domino.getLeft()));
                    ImageView bottomImage = new ImageView(dominoImages.get(domino.getRight()));
                    hBox.getChildren().addAll(topImage, bottomImage);
                    setGraphic(hBox);

                    hBox.setOnMouseClicked((t) -> selectTile(domino));
                } else {
                    setGraphic(null);
                }
            }
        });
    }
    
    private void setupListaFichasTabla() {
        tablaDomino.setEditable(false);
        
        tablaDomino.setCellFactory((ListView<Domino> listView) -> new ListCell<>() {
            @Override
            protected void updateItem(Domino domino, boolean empty) {
                super.updateItem(domino, empty);
                if (domino != null && !empty) {
                    HBox hBox = new HBox();
                    VBox vBox = new VBox();

                    if (domino.isDouble()) {
                        // Vertical
                        ImageView topImage = new ImageView(dominoImages.get(domino.getInvertir() ? domino.getRight() : domino.getLeft()));
                        ImageView bottomImage = new ImageView(dominoImages.get(domino.getInvertir() ? domino.getLeft() : domino.getRight()));
                        vBox.getChildren().addAll(topImage, bottomImage);
                        setGraphic(vBox);
                    } else {
                        // Horizontal
                        ImageView topImage = new ImageView(dominoImages.get(domino.getInvertir() ? domino.getRight() : domino.getLeft()));
                        ImageView bottomImage = new ImageView(dominoImages.get(domino.getInvertir() ? domino.getLeft() : domino.getRight()));
                        hBox.getChildren().addAll(topImage, bottomImage);
                        setGraphic(hBox);
                    }
                } else {
                    setGraphic(null);
                }
            }
        });
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Precaución");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    private void pasar(/*ActionEvent event*/) {
        System.out.println("PASASTE!");
        
        int index = new Random().nextInt(allFichas.size());
        
        jugador.getFichas().add(allFichas.get(index));
        allFichas.remove(index);
        
        notifyChanges();
    }
}
