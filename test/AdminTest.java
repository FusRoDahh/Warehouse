import models.*;
import org.junit.Test;
import static org.junit.Assert.*;
public class AdminTest {
    Admin admin = new Admin();
    @Test
    public void addKeyTest(){
        String key = "test";
        admin.addKey(key);
        assertFalse(admin.getKeys().isEmpty());
        assertEquals(admin.getKeys().get(0),"test");
    }
    @Test
    public void addExistingKeyTest(){
        String key = "test";
        String key1 = "test";
        admin.addKey(key);
        admin.addKey(key1);
        assertEquals(admin.getKeys().size(), 1);
        assertEquals(admin.getKeys().get(0),"test");
    }
    @Test
    public void removeKeyTest(){
        String key = "test";
        admin.addKey(key);
        assertFalse(admin.getKeys().isEmpty());
        assertEquals(admin.getKeys().get(0),"test");
    }
}
