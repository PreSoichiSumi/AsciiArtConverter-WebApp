import controllers.HomeController;
import org.junit.Test;

import java.awt.*;
import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by s-sumi on 2016/03/27.
 */
public class UtilTest {
    @Test
    public void isPictureTest() {
        HomeController hc = new HomeController();
        File jpg = new File("test/resources/test.jpg");
        assertTrue(jpg.exists());
        assertTrue(hc.isPicture(jpg));
    }

    @Test
    public void generateConfigManagerTest0() {
        Font f=new Font("a;slkdf",Font.PLAIN,9);
        assertTrue(f.getFontName().contains("Dialog"));
    }
    @Test
    public void generateConfigManagerTest00() {
        Font f=new Font("Dialog",Font.PLAIN,9);
        assertTrue(f.getFontName().contains("Dialog"));
    }
    @Test
    public void generateConfigManagerTest01() {
        Font f=new Font("Monospaced",Font.PLAIN,9);
        assertFalse(f.getFontName().contains("Dialog"));
    }
    @Test
    public void generateConfigManagerTest02() {
        Font f=new Font("SansSerif",Font.PLAIN,9);
        assertFalse(f.getFontName().contains("Dialog"));
    }
    @Test
    public void generateConfigManagerTest03() {
        Font f=new Font("Arial",Font.PLAIN,9);
        assertFalse(f.getFontName().contains("Dialog"));
    }
    @Test
    public void generateConfigManagerTest04() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            Font f = new Font("MS Gothic", Font.PLAIN, 9);
            assertFalse(f.getFontName().contains("Dialog"));
        }
    }
    @Test
    public void generateConfigManagerTest05() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            Font f = new Font("MS PGothic", Font.PLAIN, 9);
            assertFalse(f.getFontName().contains("Dialog"));
        }
    }

}
