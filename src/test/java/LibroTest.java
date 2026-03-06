import com.cursojava.modelo.Libro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {

    @Test
    @DisplayName("Should default all fields to null/zero in no-args constructor")
    void defaultConstructorDefaults() {
        Libro l = new Libro();
        assertNull(l.getId());
        assertNull(l.getTitulo());
        assertNull(l.getAutor());
        assertEquals(0.0, l.getPrecio());
        assertEquals(0, l.getAnioPublicacion());
    }

    @Test
    @DisplayName("Should persist-friendly: id mutable and can be set after construction")
    void idIsMutableAfterConstruction() {
        Libro l = new Libro("Titulo", "Autor", 10.0, 1990);
        assertNull(l.getId());
        l.setId(100L);
        assertEquals(100L, l.getId());
    }

    @Test
    @DisplayName("Should allow updating title and author and reflect new values")
    void updateTitleAndAuthor() {
        Libro l = new Libro("T1", "A1", 5.0, 1980);
        l.setTitulo("T2");
        l.setAutor("A2");
        assertEquals("T2", l.getTitulo());
        assertEquals("A2", l.getAutor());
    }

    @Test
    @DisplayName("toString should include all set values even when some are null")
    void toStringIncludesNullables() {
        Libro l = new Libro();
        l.setId(7L);
        l.setPrecio(0.0);
        String s = l.toString();
        assertNotNull(s);
        assertTrue(s.contains("7"));
    }

    @Test
    @DisplayName("Should support chaining-like successive updates to multiple fields")
    void successiveUpdates() {
        Libro l = new Libro("A", "B", 1.0, 1900);
        l.setPrecio(2.5);
        l.setAnioPublicacion(2001);
        l.setTitulo("NuevoTitulo");
        l.setAutor("NuevoAutor");
        assertAll(
                () -> assertEquals(2.5, l.getPrecio()),
                () -> assertEquals(2001, l.getAnioPublicacion()),
                () -> assertEquals("NuevoTitulo", l.getTitulo()),
                () -> assertEquals("NuevoAutor", l.getAutor())
        );
    }


    @Test
    @DisplayName("Should construct with all fields (except id) and expose them via getters")
    void constructorAndGettersWork() {
        Libro l = new Libro("Titulo", "Autor", 19.99, 2020);
        assertNull(l.getId(), "id should start null before persistence");
        assertEquals("Titulo", l.getTitulo());
        assertEquals("Autor", l.getAutor());
        assertEquals(19.99, l.getPrecio());
        assertEquals(2020, l.getAnioPublicacion());
    }

    @Test
    @DisplayName("Should allow setting and getting all mutable fields including id")
    void settersWork() {
        Libro l = new Libro();
        l.setId(5L);
        l.setTitulo("Nuevo");
        l.setAutor("Alguien");
        l.setPrecio(12.5);
        l.setAnioPublicacion(1995);

        assertEquals(5L, l.getId());
        assertEquals("Nuevo", l.getTitulo());
        assertEquals("Alguien", l.getAutor());
        assertEquals(12.5, l.getPrecio());
        assertEquals(1995, l.getAnioPublicacion());
    }

    @Test
    @DisplayName("Should allow updating price and reflect new value")
    void updatePrice() {
        Libro l = new Libro("t", "a", 10.0, 2000);
        l.setPrecio(15.75);
        assertEquals(15.75, l.getPrecio());
    }

    @Test
    @DisplayName("Should allow updating publication year and reflect new value")
    void updateYear() {
        Libro l = new Libro("t", "a", 10.0, 2000);
        l.setAnioPublicacion(2024);
        assertEquals(2024, l.getAnioPublicacion());
    }

    @Test
    @DisplayName("toString should contain key fields for logging/printing")
    void toStringContainsFields() {
        Libro l = new Libro("MiTitulo", "MiAutor", 9.99, 2010);
        l.setId(42L);
        String s = l.toString();
        assertNotNull(s);
        assertTrue(s.contains("MiTitulo"));
        assertTrue(s.contains("MiAutor"));
        assertTrue(s.contains("9.99"));
        assertTrue(s.contains("42"));
    }
}
