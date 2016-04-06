import aacj.config.CharManager;
import aacj.config.ConfigManager;
import aacj.model.PixelTable;
import controllers.WebJarAssets;
import org.junit.Test;
import play.twirl.api.Content;
import util.ConvertionUtil;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static org.junit.Assert.*;


/**
 *
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 *
 */
public class ApplicationTest {
    @Inject
    WebJarAssets webJarAssets;


    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertEquals(2, a);
    }

    @Test
    public void renderTemplate() {
        Content html = views.html.index.render("Your new application is ready.", webJarAssets);
        assertEquals("text/html", html.contentType());
        assertTrue(html.body().contains("Your new application is ready."));
    }
    @Test
    public void regexTest(){
        String str="abababaaaaababbaba";
        str=str.replaceAll("a|b","");
        assertTrue(Objects.equals(str,""));
    }

    @Test
    public void convertTest(){
        BufferedImage bi;
        try {
            bi = ImageIO.read(this.getClass()
                    .getResource("resources/whiteImage.png"));

            ConfigManager cm = ConvertionUtil.generateConfigManager();
            CharManager charm=new CharManager(cm);
            PixelTable lineImg=ConvertionUtil.img2LineImg(bi,cm);
            String aa= ConvertionUtil.lineImg2AA(lineImg.data,lineImg.width,
                    lineImg.height,charm,cm)[0];

            assertTrue(Objects.equals(
                    aa.replaceAll(" |　|\r|\n","") , "" ));

        }catch(Exception e){
            fail();
        }
    }

}
