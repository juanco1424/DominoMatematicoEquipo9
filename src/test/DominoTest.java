package test;


import dominoMatematico.Domino;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DominoTest {
    @Test
    public void testConstructorAndGetters() {
        // instaciamos la clase para tener variables
        Domino domino = new Domino(3, 6, false);
        // modificamos las variables con metodos set
        assertEquals(3, domino.getLeft());
        assertEquals(6, domino.getRight());
        assertFalse(domino.getInvertir());
    }

    @Test
    public void testSetters() {
        // instaciamos la clase para tener variables
        Domino domino = new Domino(3, 6, false);
        // modificamos las variables con metodos set
        domino.setLeft(4);
        domino.setRight(5);
        domino.setInvertir(true);
        // con los metodos assert comparamos los resultados
        assertEquals(4, domino.getLeft());
        assertEquals(5, domino.getRight());
        assertTrue(domino.getInvertir());
    }

    @Test
    public void testIsDouble() {
        // instaciamos la clase para tener variables
        Domino doubleDomino = new Domino(6, 6, false);
        Domino nonDoubleDomino = new Domino(3, 6, false);
        // con los metodos assert comparamos los resultados
        assertTrue(doubleDomino.isDouble());
        assertFalse(nonDoubleDomino.isDouble());
    }
}
