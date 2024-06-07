package dominoMatematico;

import java.util.List;

public class Usuario {
    private List<Domino> fichas;
    
    public Usuario(List<Domino> fichas) {
        this.fichas = fichas;
    }
    
    // Este método se utilizará para obtener las fichas
    public List<Domino> getFichas() {
        return fichas;
    }
    
    // Este método se utilizará para modificar todas las fichas
    public void setFichas(List<Domino> newFichas) {
        fichas = newFichas;
    }
    
    // Este método se utilizará para remover en cierto Index
    public void removeInIndex(int index) {
        fichas.remove(index);
    }
}