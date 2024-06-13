package test;

import dominoMatematico.Domino;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para la clase Domino.
 * Realiza pruebas unitarias de los métodos de la clase Domino utilizando JUnit 5.
 */
class DominoTest {

    /**
     * Prueba el constructor y los métodos getter de la clase Domino.
     * Verifica que el constructor inicializa correctamente los valores y que los métodos getter devuelven los valores correctos.
     */
    @Test
    public void testConstructorAndGetters() {
        // Instanciar la clase para tener variables
        Domino domino = new Domino(3, 6, false);
        // Verificar que los métodos getter devuelven los valores correctos
        assertEquals(3, domino.getLeft());
        assertEquals(6, domino.getRight());
        assertFalse(domino.getInvertir());
    }

    /**
     * Prueba los métodos setter de la clase Domino.
     * Verifica que los métodos setter modifican correctamente los valores de los atributos.
     */
    @Test
    public void testSetters() {
        // Instanciar la clase para tener variables
        Domino domino = new Domino(3, 6, false);
        // Modificar las variables con métodos setter
        domino.setLeft(4);
        domino.setRight(5);
        domino.setInvertir(true);
        // Verificar que los métodos getter devuelven los nuevos valores
        assertEquals(4, domino.getLeft());
        assertEquals(5, domino.getRight());
        assertTrue(domino.getInvertir());
    }

    /**
     * Prueba el método isDouble de la clase Domino.
     * Verifica que el método isDouble identifica correctamente si una ficha de dominó es doble.
     */
    @Test
    public void testIsDouble() {
        // Instanciar la clase para tener variables
        Domino doubleDomino = new Domino(6, 6, false);
        Domino nonDoubleDomino = new Domino(3, 6, false);
        // Verificar que el método isDouble identifica correctamente si una ficha de dominó es doble
        assertTrue(doubleDomino.isDouble());
        assertFalse(nonDoubleDomino.isDouble());
    }
}

