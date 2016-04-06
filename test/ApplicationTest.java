import controllers.WebJarAssets;
import org.junit.Test;
import play.twirl.api.Content;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


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
}
