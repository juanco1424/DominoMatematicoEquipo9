package dominoMatematico;

import java.util.List;

/**
 * Clase que representa a un usuario en el juego de domino.
 * Cada usuario tiene una lista de fichas de domino.
 */
public class Usuario {
    /** Lista de fichas de domino del usuario. */
    private List<Domino> fichas;

    /**
     * Constructor que inicializa un usuario con la lista de fichas dada.
     *
     * @param fichas La lista de fichas de domino del usuario.
     */
    public Usuario(List<Domino> fichas) {
        this.fichas = fichas;
    }

    /**
     * Metodo para obtener la lista de fichas de domino del usuario.
     *
     * @return La lista de fichas de domino del usuario.
     */
    public List<Domino> getFichas() {
        return fichas;
    }

    /**
     * Metodo para establecer una nueva lista de fichas de domino para el usuario.
     *
     * @param newFichas La nueva lista de fichas de domino.
     */
    public void setFichas(List<Domino> newFichas) {
        fichas = newFichas;
    }

    /**
     * Metodo para eliminar una ficha de domino en un indice específico de la lista.
     *
     * @param index El indice de la ficha de domino que se va a eliminar.
     */
    public void removeInIndex(int index) {
        fichas.remove(index);
    }
}
