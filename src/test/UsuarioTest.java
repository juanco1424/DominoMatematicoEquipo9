package test;

import dominoMatematico.Domino;
import dominoMatematico.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    private List<Domino> fichasIniciales;
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        // Inicializar una lista de fichas de dominó
        fichasIniciales = new ArrayList<>();
        fichasIniciales.add(new Domino(1, 2, false));
        fichasIniciales.add(new Domino(3, 4, false));
        fichasIniciales.add(new Domino(5, 6, false));

        // Crear un nuevo usuario con la lista inicial de fichas
        usuario = new Usuario(fichasIniciales);
    }

    @Test
    public void testGetFichas() {
        // Verificar que el método getFichas devuelve la lista correcta de fichas
        List<Domino> fichas = usuario.getFichas();
        assertEquals(3, fichas.size());
        assertEquals(1, fichas.get(0).getLeft());
        assertEquals(2, fichas.get(0).getRight());
        assertEquals(3, fichas.get(1).getLeft());
        assertEquals(4, fichas.get(1).getRight());
        assertEquals(5, fichas.get(2).getLeft());
        assertEquals(6, fichas.get(2).getRight());
    }

    @Test
    public void testSetFichas() {
        // Crear una nueva lista de fichas
        List<Domino> nuevasFichas = new ArrayList<>();
        nuevasFichas.add(new Domino(6, 6, false));

        // Establecer las nuevas fichas en el usuario
        usuario.setFichas(nuevasFichas);

        // Verificar que el método getFichas devuelve la nueva lista de fichas
        List<Domino> fichas = usuario.getFichas();
        assertEquals(1, fichas.size());
        assertEquals(6, fichas.get(0).getLeft());
        assertEquals(6, fichas.get(0).getRight());
    }

    @Test
    public void testRemoveInIndex() {
        // Remover la ficha en el índice 1
        usuario.removeInIndex(1);

        // Verificar que la ficha fue removida
        List<Domino> fichas = usuario.getFichas();
        assertEquals(2, fichas.size());
        assertEquals(1, fichas.get(0).getLeft());
        assertEquals(2, fichas.get(0).getRight());
        assertEquals(5, fichas.get(1).getLeft());
        assertEquals(6, fichas.get(1).getRight());
    }

}