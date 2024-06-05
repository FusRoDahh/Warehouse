import models.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteTest {
    Cliente cliente = new Cliente("test","test");
    @Test
    public void aggiungiAlCarrelloTest(){
        Prodotto p = new Prodotto();
        cliente.aggiungiAlCarrello(p);
        assertFalse(cliente.getCarrello().isEmpty());
    }
    @Test
    public void RimuoviDalCarrelloTest(){
        Prodotto p = new Prodotto();
        cliente.aggiungiAlCarrello(p);
        cliente.rimuoviDalCarrello(p);
        assertTrue(cliente.getCarrello().isEmpty());
    }
    @Test
    public void FinalizzaAcquistoTest(){
        Prodotto p = new Prodotto();
        cliente.aggiungiAlCarrello(p);
        cliente.finalizzaAcquisto();
        assertTrue(cliente.getCarrello().isEmpty());
    }
    @Test
    public void CercaPerIdTest(){
        Prodotto p = new Prodotto("test", "test","test",1,1,1,1,"test");
        Prodotto p1 = new Prodotto("test1", "test1","test1",1,1,1,1,"test1");
        cliente.aggiungiAlCarrello(p);
        cliente.aggiungiAlCarrello(p1);
        assertEquals(cliente.cercaProdotto(p.getId()), p);
    }
}
