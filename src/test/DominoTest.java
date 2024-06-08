package test;


import dominoMatematico.Domino;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DominoTest {
    @Test
    public void testConstructorAndGetters() {
        Domino domino = new Domino(3, 6, false);
        assertEquals(3, domino.getLeft());
        assertEquals(6, domino.getRight());
        assertFalse(domino.getInvertir());
    }

    @Test
    public void testSetters() {
        Domino domino = new Domino(3, 6, false);
        domino.setLeft(4);
        domino.setRight(5);
        domino.setInvertir(true);
        assertEquals(4, domino.getLeft());
        assertEquals(5, domino.getRight());
        assertTrue(domino.getInvertir());
    }

    @Test
    public void testIsDouble() {
        Domino doubleDomino = new Domino(6, 6, false);
        Domino nonDoubleDomino = new Domino(3, 6, false);
        assertTrue(doubleDomino.isDouble());
        assertFalse(nonDoubleDomino.isDouble());
    }
}