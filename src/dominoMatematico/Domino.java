package dominoMatematico;

/**
 * Clase que representa una ficha de domino.
 * Cada ficha tiene dos lados y puede estar invertida.
 */
public class Domino {

    /**
     * Lado izquierdo de la ficha.
     */
    private int left;

    /**
     * Lado derecho de la ficha.
     */
    private int right;

    /**
     * Indica si la ficha está invertida.
     * Cuando está invertida, el lado derecho pasa a ser el lado izquierdo y viceversa.
     */
    private boolean invertir;

    /**
     * Constructor que inicializa una ficha de domino con los valores dados.
     *
     * @param left     El valor del lado izquierdo de la ficha.
     * @param right    El valor del lado derecho de la ficha.
     * @param invertir Indica si la ficha está invertida.
     */
    public Domino(int left, int right, boolean invertir) {
        this.left = left;
        this.right = right;
        this.invertir = invertir;
    }

    /**
     * Obtiene el valor del lado izquierdo de la ficha.
     *
     * @return El valor del lado izquierdo de la ficha.
     */
    public int getLeft() {
        return left;
    }

    /**
     * Obtiene el valor del lado derecho de la ficha.
     *
     * @return El valor del lado derecho de la ficha.
     */
    public int getRight() {
        return right;
    }

    /**
     * Verifica si la ficha está invertida.
     *
     * @return true si la ficha está invertida, false en caso contrario.
     */
    public boolean getInvertir() {
        return invertir;
    }

    /**
     * Establece un nuevo valor para el lado izquierdo de la ficha.
     *
     * @param newLeft El nuevo valor para el lado izquierdo de la ficha.
     */
    public void setLeft(int newLeft) {
        left = newLeft;
    }

    /**
     * Establece un nuevo valor para el lado derecho de la ficha.
     *
     * @param newRight El nuevo valor para el lado derecho de la ficha.
     */
    public void setRight(int newRight) {
        right = newRight;
    }

    /**
     * Establece un nuevo valor para la inversión de la ficha.
     *
     * @param newInvertir El nuevo valor booleano que indica si la ficha esta invertida.
     */
    public void setInvertir(boolean newInvertir) {
        invertir = newInvertir;
    }

    /**
     * Verifica si la ficha es doble.
     * Una ficha es doble si ambos lados tienen el mismo valor.
     *
     * @return true si la ficha es doble, false en caso contrario.
     */
    public boolean isDouble() {
        return left == right;
    }
}
