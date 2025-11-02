package sebas.lab.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
public class GestionBibliotecaTest {
    private GestionBiblioteca biblioteca;
    @BeforeEach
    void setUp() {
        biblioteca = new GestionBiblioteca();
    }

    // 1. calcularPrecioConDescuento()

    @Test
    void testPrecioSinDescuento() {
        double resultado = biblioteca.calcularPrecioConDescuento(100.0, 0.0);
        assertEquals(100.0, resultado);
    }

    @Test
    void testPrecioCon50PorcientoDescuento() {
        double resultado = biblioteca.calcularPrecioConDescuento(200.0, 50.0);
        assertEquals(100.0, resultado);
    }

    @Test
    void testExcepcionDescuentoMayorA100() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            biblioteca.calcularPrecioConDescuento(100.0, 150.0);
        });
        assertEquals("El porcentaje debe estar entre 0 y 100", ex.getMessage());
    }

    // 2. estaDisponible()

    @Test
    void testLibroNoDisponible() {
        assertFalse(biblioteca.estaDisponible("Cien años de soledad"));
    }

    @Test
    void testLibroDisponibleDespuesDeAgregarlo() {
        biblioteca.agregarLibro("El Principito");
        assertTrue(biblioteca.estaDisponible("El Principito"));
    }

    // 3. agregarLibro()

    @Test
    void testAgregarLibroExitosamente() {
        assertTrue(biblioteca.agregarLibro("Don Quijote"));
        assertEquals(1, biblioteca.getCantidadLibros());
    }

    @Test
    void testAgregarLibroDuplicado() {
        biblioteca.agregarLibro("1984");
        assertFalse(biblioteca.agregarLibro("1984"));
    }

    // 4. obtenerCategoriaLector()

    @Test
    void testCategoriaPrincipiante() {
        assertEquals("Principiante", biblioteca.obtenerCategoriaLector(0));
    }

    @Test
    void testCategoriaIntermedio() {
        assertEquals("Intermedio", biblioteca.obtenerCategoriaLector(5));
    }

    @Test
    void testExcepcionNumeroNegativo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            biblioteca.obtenerCategoriaLector(-3);
        });
        assertEquals("La cantidad no puede ser negativa", ex.getMessage());
    }

    // 5. obtenerLibrosDisponibles()
    @Test
    void testNuncaRetornaNull() {
        assertNotNull(biblioteca.obtenerLibrosDisponibles());
    }

    @Test
    void testContieneLibrosDespuesDeAgregarlos() {
        biblioteca.agregarLibro("Harry Potter");
        List<String> libros = biblioteca.obtenerLibrosDisponibles();
        assertTrue(libros.contains("Harry Potter"));
    }
}
