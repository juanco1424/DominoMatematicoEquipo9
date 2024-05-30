package prueba;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import java.util.ResourceBundle;
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
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author JD-APPS
 */
public class PruebaViewController implements Initializable {

    @FXML
    private ListView<Domino> listFichas;

    private List<Image> dominoImages;
    
    private double posX_TuTurno = 373;
    private double posY_TuTurno = 14;
    
    private double posX_TurnoIA = 339;
    private double posY_TurnoIA = 14;



    @FXML
    private Button btnPasar;

    private Domino ultIzquierda;
    private Domino ultDerecha;

    private List<Domino> fichasTablaDomino;
    
    // Proximamente implementar inteligencia básica de IA para jugar contra ella.
    private List<Domino> iaFichas;
    
    @FXML
    private ListView<Domino> tablaDomino;
    @FXML
    private Label turneLabel;
    @FXML
    private Label fichasIALabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dominoImages = new ArrayList<>();
        fichasTablaDomino = new ArrayList<>();

        loadImages();
        populatePlayerHand();
        setupPlayerHandListView();
        setupListaFichasTabla();
    }

    private void loadImages() {
        dominoImages = new ArrayList<>();

        try {
            for (int i = 0; i <= 6; i++) {
                dominoImages.add(new Image(getClass().getResource("/domino_" + i + ".png").toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error loading images.");
        }

    }
    
    private void selectTile(Domino domino) {
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
            
            listFichas.getItems().remove(domino);
        } else {
            if(domino.isDouble()) {
                // Ficha cualquiera (Vertical)
                if(ultIzquierda.getLeft() == domino.getLeft() && !ultIzquierda.getInvertir()) {
                    // Ultimo a la izquierda pero es la parte izquierda (invertir)
                    
                    domino.setInvertir(true);
                    addFichaTabla(domino , true);
                    ultIzquierda = domino;
                    
                } else if(ultIzquierda.getLeft() == domino.getRight() && !ultIzquierda.getInvertir()) {
                    // Ultimo a la izquierda y es la parte derecha (no se necesita invertir)
                    
                    addFichaTabla(domino , true);
                    ultIzquierda = domino;
                    
                // Parte invertida
                } else if(ultIzquierda.getRight() == domino.getLeft()) {
                    // Esta es por si esta invertida la ficha para el lado derecho
                    
                    // Ya como esta invertida, la ficha derecha contaría como ficha izquierda, y continuando con el mismo concepto
                    // Se debe invertir
                    domino.setInvertir(true);
                    //Con este añadido, se añadirá la ficha de Domino para la izquierda.
                    addFichaTabla(domino , true); //Sin necesidad de organizar orden
                    ultIzquierda = domino;
                    
                } else if(ultIzquierda.getRight() == domino.getRight()) {
                    // Esta es por si esta invertida la ficha para el lado derecho
                    
                    // Ya como esta invertida, la ficha derecha contaría como ficha izquierda, y continuando con el mismo concepto
                    // En esta caso, no se debe invertir
                    
                    //Con este añadido, se añadirá la ficha de Domino para la izquierda.
                    addFichaTabla(domino , true); //Sin necesidad de organizar orden
                    ultIzquierda = domino;
                    
                } else if(ultDerecha.getRight() == domino.getLeft() && !ultDerecha.getInvertir()) {
                    // Ultimo a la derecha y es la parte izquierda (no se necesita invertir)
                    
                    //Con este añadido, se añadirá la ficha de Domino para la derecha.
                    addFichaTabla(domino , false); //Sin necesidad de organizar orden
                    ultDerecha = domino;
                    
                } else if(ultDerecha.getRight() == domino.getRight() && !ultDerecha.getInvertir()) {
                    // Ultimo a la derecha pero es la parte derecha (invetir)
                    
                    domino.setInvertir(true);
                    //Con este añadido, se añadirá la ficha de Domino para la derecha.
                    addFichaTabla(domino , false); //Ahora se organizará el orden correcto de la ficha
                    ultDerecha = domino;
                    
                // Parte invertida
                } else if(ultDerecha.getLeft() == domino.getLeft()) {
                    // Esta es por si esta invertida la ficha para el lado izquierdo
                    
                    // Ya como esta invertida, la ficha izquierdo contaría como ficha derecha, y continuando con el mismo concepto
                    // En esta caso, no se debe invertir
                    
                    //Con este añadido, se añadirá la ficha de Domino para la izquierda.
                    addFichaTabla(domino , false); //Sin necesidad de organizar orden
                    ultDerecha = domino;
                } else if(ultDerecha.getLeft() == domino.getRight()) {
                    // Esta es por si esta invertida la ficha para el lado izquierdo
                    
                    // Ya como esta invertida, la ficha izquierdo contaría como ficha derecha, y continuando con el mismo concepto
                    // En esta caso, si se debe invertir
                    domino.setInvertir(true);
                    
                    //Con este añadido, se añadirá la ficha de Domino para la izquierda.
                    addFichaTabla(domino , false); //Sin necesidad de organizar orden
                    ultDerecha = domino;
                } else {
                    showAlert("Esta ficha no puede ser usada");
                    return;
                }
                
                
                listFichas.getItems().remove(domino);
                
            } else {
                // Ficha cualquiera (Horizontal)
                if(ultIzquierda.getLeft() == domino.getLeft() && !ultIzquierda.getInvertir()) {
                    // Ultimo a la izquierda pero es la parte izquierda (invertir)
                    
                    domino.setInvertir(true);
                    //Con este añadido, se añadirá la ficha de Domino para la izquierda.
                    addFichaTabla(domino , true); //Ahora se organizará el orden correcto de la ficha
                    ultIzquierda = domino;
                    
                } else if(ultIzquierda.getLeft() == domino.getRight() && !ultIzquierda.getInvertir()) {
                    // Ultimo a la izquierda y es la parte derecha (no se necesita invertir)
                    
                    //Con este añadido, se añadirá la ficha de Domino para la izquierda.
                    addFichaTabla(domino , true); //Sin necesidad de organizar orden
                    ultIzquierda = domino;
                   
                // Parte para fichas invertidas
                } else if(ultIzquierda.getRight() == domino.getLeft()) {
                    // Esta es por si esta invertida la ficha para el lado derecho
                    
                    // Ya como esta invertida, la ficha derecha contaría como ficha izquierda, y continuando con el mismo concepto
                    // Se debe invertir
                    domino.setInvertir(true);
                    //Con este añadido, se añadirá la ficha de Domino para la izquierda.
                    addFichaTabla(domino , true); //Sin necesidad de organizar orden
                    ultIzquierda = domino;
                    
                } else if(ultIzquierda.getRight() == domino.getRight()) {
                    // Esta es por si esta invertida la ficha para el lado derecho
                    
                    // Ya como esta invertida, la ficha derecha contaría como ficha izquierda, y continuando con el mismo concepto
                    // En esta caso, no se debe invertir
                    
                    //Con este añadido, se añadirá la ficha de Domino para la izquierda.
                    addFichaTabla(domino , true); //Sin necesidad de organizar orden
                    ultIzquierda = domino;
                    
                    
                } else if(ultDerecha.getRight() == domino.getLeft() && !ultDerecha.getInvertir()) {
                    // Ultimo a la derecha y es la parte izquierda (no se necesita invertir)
                    
                    //Con este añadido, se añadirá la ficha de Domino para la derecha.
                    addFichaTabla(domino , false); //Sin necesidad de organizar orden
                    ultDerecha = domino;

                    
                } else if(ultDerecha.getRight() == domino.getRight() && !ultDerecha.getInvertir()) {
                    // Ultimo a la derecha pero es la parte derecha (invetir)
                    
                    domino.setInvertir(true);
                    //Con este añadido, se añadirá la ficha de Domino para la derecha.
                    addFichaTabla(domino , false); //Ahora se organizará el orden correcto de la ficha
                    
                    ultDerecha = domino;
                    
                    
                // Parte para fichas invertidas
                } else if(ultDerecha.getLeft() == domino.getLeft()) {
                    // Esta es por si esta invertida la ficha para el lado izquierdo
                    
                    // Ya como esta invertida, la ficha izquierdo contaría como ficha derecha, y continuando con el mismo concepto
                    // En esta caso, no se debe invertir
                    
                    //Con este añadido, se añadirá la ficha de Domino para la izquierda.
                    addFichaTabla(domino , false); //Sin necesidad de organizar orden
                    ultDerecha = domino;
                    
                } else if(ultDerecha.getLeft() == domino.getRight()) {
                    // Esta es por si esta invertida la ficha para el lado izquierdo
                    
                    // Ya como esta invertida, la ficha izquierdo contaría como ficha derecha, y continuando con el mismo concepto
                    // En esta caso, si se debe invertir
                    domino.setInvertir(true);
                    
                    //Con este añadido, se añadirá la ficha de Domino para la izquierda.
                    addFichaTabla(domino , false); //Sin necesidad de organizar orden
                    ultDerecha = domino;
                } else {
                    showAlert("Esta ficha no puede ser usada");
                    return;
                }
                
                listFichas.getItems().remove(domino);
            }
            
            // Más acciones fuera del If...
            
        }
                
    }
    
    private void addFichaTabla(Domino domino , boolean index0) {
        if(index0) {
            fichasTablaDomino.add(0 , domino);
            tablaDomino.getItems().add(0, domino);
        } else {
            fichasTablaDomino.add(domino);
            tablaDomino.getItems().add(domino);
        }
    }

    private void populatePlayerHand() {
        List<Domino> playerTiles = new ArrayList<>();
        // Ejemplo: Añadir algunas fichas al azar al ListView
        playerTiles.add(new Domino(0, 1 , false));
        playerTiles.add(new Domino(0, 2 , false));
        playerTiles.add(new Domino(2, 2 , false));
        playerTiles.add(new Domino(1, 4 , false));
        playerTiles.add(new Domino(3, 4 , false));
        playerTiles.add(new Domino(5, 1 , false));
        playerTiles.add(new Domino(6, 6 , false));
        playerTiles.add(new Domino(6, 2 , false));
        playerTiles.add(new Domino(0, 0 , false));

        

        listFichas.getItems().addAll(playerTiles);
    }

    private void setupPlayerHandListView() {
        listFichas.setCellFactory(new Callback<ListView<Domino>, ListCell<Domino>>() {
            @Override
            public ListCell<Domino> call(ListView<Domino> listView) {
                return new ListCell<Domino>() {
                    @Override
                    protected void updateItem(Domino domino, boolean empty) {
                        super.updateItem(domino, empty);
                        if (domino != null && !empty) {
                            HBox hBox = new HBox();
                            ImageView topImage = new ImageView(dominoImages.get(domino.left));
                            ImageView bottomImage = new ImageView(dominoImages.get(domino.right));
                            hBox.getChildren().addAll(topImage, bottomImage);
                            setGraphic(hBox);
                            
                            hBox.setOnMouseClicked((t) -> selectTile(domino));
                            //listFichas.setOnMouseClicked((t) -> selectTile(domino));
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
    }
    
    private void setupListaFichasTabla() {
        tablaDomino.setEditable(false);
        
        tablaDomino.setCellFactory(new Callback<ListView<Domino>, ListCell<Domino>>() {
            @Override
            public ListCell<Domino> call(ListView<Domino> listView) {
                return new ListCell<Domino>() {
                    @Override
                    protected void updateItem(Domino domino, boolean empty) {
                        super.updateItem(domino, empty);
                        if (domino != null && !empty) {
                            HBox hBox = new HBox();
                            VBox vBox = new VBox();
                            
                            if(domino.isDouble()) {
                                // Vertical
                                ImageView topImage = new ImageView(dominoImages.get(domino.invertir ? domino.right : domino.left));
                                ImageView bottomImage = new ImageView(dominoImages.get(domino.invertir ? domino.left : domino.right));
                                vBox.getChildren().addAll(topImage, bottomImage);
                                setGraphic(vBox);
                            } else {
                                // Horizontal
                                ImageView topImage = new ImageView(dominoImages.get(domino.invertir ? domino.right : domino.left));
                                ImageView bottomImage = new ImageView(dominoImages.get(domino.invertir ? domino.left : domino.right));
                                hBox.getChildren().addAll(topImage, bottomImage);
                                setGraphic(hBox);
                            }
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Precacuión");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    private void pasar(ActionEvent event) {
        System.out.println("PASASTE!");
    }

    static class Domino {

        private int left;
        private int right;
        
        private boolean invertir;

        public Domino(int left, int right, boolean invertir) {
            this.left = left;
            this.right = right;
            this.invertir = invertir;
        }
        
        public int getLeft() {
            return left;
        }
        
        public boolean getInvertir() {
            return invertir;
        }
        
        public void setLeft(int newLeft) {
            left = newLeft;
        }
        
        public int getRight() {
            return right;
        }
        
        public void setRight(int newRight) {
            right = newRight;
        }
        
        public void setInvertir(boolean newInvertir) {
            invertir = newInvertir;
        }
        
        public boolean isDouble() {
            return left == right;
        }

    }
}
