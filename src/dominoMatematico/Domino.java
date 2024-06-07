package dominoMatematico;

public class Domino {
    
    // Lado izquierdo de la ficha
    private int left;
    
    // Lado derecho de la ficha
    private int right;
    
    // Si la ficha esta invertida (Se útiliza para cuando la ficha debe invertirse, en sí el lado derecho pasaría a ser el 
    // lado izquierdo en este concepto, y el lado izquierdo pasaría a ser el lado derecho.
    private boolean invertir;

    public Domino(int left, int right, boolean invertir) {
        this.left = left;
        this.right = right;
        this.invertir = invertir;
    }
    
    // Obtenemos la ficha izquierda (Si o si la ficha izquierda, no invertida)
    public int getLeft() {
        return left;
    }
    
    // Obtenemos un bool para saber si la ficha esta invertida
    public boolean getInvertir() {
        return invertir;
    }
    
    // Modificamos la ficha izquierda
    public void setLeft(int newLeft) {
        left = newLeft;
    }
    
    // Obtenemos la ficha derecha
    public int getRight() {
        return right;
    }
    
    // Modificamos la ficha derecha
    public void setRight(int newRight) {
        right = newRight;
    }
        
    // Modificamos el bool de Invertir
    public void setInvertir(boolean newInvertir) {
        invertir = newInvertir;
    }
    
    // Método para verificar si las fichas son dobles (Ejemplo: Lado Izquierdo = 6 y Lado Derecho = 6)
    public boolean isDouble() {
        return left == right;
    }
}
