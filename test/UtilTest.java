import controllers.HomeController;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.validation.constraints.AssertTrue;
import java.io.File;

/**
 * Created by s-sumi on 2016/03/27.
 */
public class UtilTest {
    @Test
    public void isPictureTest() {
        HomeController hc=new HomeController();
        File jpg=new File("test.jpg");
        File png=new File("test.png");
        File jpeg=new File("test.jpeg");
        File hoge=new File("test.hoge");
        assertTrue(hc.isPicture(jpg));
        assertTrue(hc.isPicture(png));
        assertTrue(hc.isPicture(jpeg));
        assertFalse(hc.isPicture(hoge));
    }

}
