import models.*;
import org.junit.Test;
import static org.junit.Assert.*;
public class UtentiRegistratiTest {
    UtentiRegistrati ut = new UtentiRegistrati();
    @Test
    public void aggiungiAiClientiTest(){
        Cliente c = new Cliente("test","test");
        ut.aggiungiCliente(c);
        assertFalse(ut.getClientiRegistrati().isEmpty());
    }
    @Test
    public void aggiungiAiMagazzinieriTest(){
        Magazziniere m = new Magazziniere("test","test");
        ut.aggiungiMagazziniere(m);
        assertFalse(ut.getMagazzinieriRegistrati().isEmpty());
    }
    @Test
    public void rimuoviDaiClientiTest(){
        Cliente c = new Cliente("test","test");
        ut.aggiungiCliente(c);
        ut.eliminaCliente(c);
        assertTrue(ut.getClientiRegistrati().isEmpty());
    }
    @Test
    public void rimuoviDaiMagazzinieriTest(){
        Magazziniere m = new Magazziniere("test","test");
        ut.aggiungiMagazziniere(m);
        ut.eliminaMagazziniere(m);
        assertTrue(ut.getMagazzinieriRegistrati().isEmpty());
    }
}
