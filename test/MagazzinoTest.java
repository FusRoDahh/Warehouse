import models.Magazzino;
import models.Prodotto;
import org.junit.Test;
import static org.junit.Assert.*;

public class MagazzinoTest {
    Magazzino magazzino = new Magazzino();
    @Test
    public void aggiungiAlMagazzinoTest (){
        Prodotto p = new Prodotto();
        magazzino.aggiungiProdotto(p);
        assertFalse(magazzino.getMagazzino().isEmpty());
    }
    @Test
    public void incrementaQuantitaTest(){
        Prodotto p = new Prodotto("test", "test","test",1,1,1,1,"test");
        Prodotto p1 = new Prodotto("test1", "test1","test1",1,1,1,1,"test1");
        Prodotto p2 = new Prodotto("test1", "test1","test1",1,1,1,1,"test1");
        magazzino.aggiungiProdotto(p);
        magazzino.aggiungiProdotto(p1);
        magazzino.aggiungiProdotto(p2);
        assertEquals("anche se sono stati inseriti 3 prodotti, la grandezza del magazzino deve essere 2",magazzino.getMagazzino().size(), 2);
        assertEquals("la quantità del primo prodotto deve essere pari a 1",magazzino.getMagazzino().get(0).getQuantita(), 1);
        assertEquals("la quantità del secondo prodotto deve essere pari a 2 (p1 e p2 sono uguali)",magazzino.getMagazzino().get(1).getQuantita(), 2);
    }
    @Test
    public void rimuoviDalMagazzinoTest(){
        Prodotto p = new Prodotto("test", "test","test",1,1,1,1,"test");
        magazzino.aggiungiProdotto(p);
        magazzino.rimuoviProdotto(p.getId());
        assertTrue(magazzino.getMagazzino().isEmpty());
    }
    @Test
    public void riduciQuantitaTest(){
        Prodotto p = new Prodotto("test", "test","test",1,1,1,1,"test");
        Prodotto p1 = new Prodotto("test", "test","test",1,1,1,1,"test");
        magazzino.aggiungiProdotto(p);
        magazzino.aggiungiProdotto(p1);
        magazzino.rimuoviProdotto(p.getId());
        assertFalse(magazzino.getMagazzino().isEmpty());
        assertEquals(magazzino.getMagazzino().get(0).getQuantita(),1);
    }
    @Test
    public void cercaPerIdTest(){
        Prodotto p = new Prodotto("test", "test","test",1,1,1,1,"test");
        Prodotto p1 = new Prodotto("test1", "test1","test1",1,1,1,1,"test1");
        magazzino.aggiungiProdotto(p);
        magazzino.aggiungiProdotto(p1);
        assertEquals(magazzino.cercaProdottoPerId(p.getId()), p);
    }
}