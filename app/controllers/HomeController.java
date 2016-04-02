package controllers;

import aacj.*;
import aacj.util.AAUtil;
import akka.stream.impl.fusing.GraphStages;
import javax.inject.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.Play;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Http.*;
import play.*;

import play.http.HttpErrorHandler;
import play.mvc.*;

import play.twirl.api.Content;
import scala.util.parsing.json.JSON;
import util.ConvertData;
import util.ConvertionUtil;
import views.html.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    @Inject
    WebJarAssets webJarAssets;
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render("this is top page",webJarAssets));
    }

    /**
     * ファイルはフォームデータの一部として送られてこない．
     * フォームのデータに添付されて送られてくる．
     * http://stackoverflow.com/questions/9452375/how-to-get-the-upload-file-with-other-inputs-in-play2#9587052
     * @return
     */
    public Result aaConvert(){
        MultipartFormData.FilePart picture=request().body().asMultipartFormData().getFile("picture");

        if(picture!=null){
            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            File file = (File)picture.getFile();

            try {
                String aa = ConvertionUtil.aaConvertion(file);
                ObjectNode result= Json.newObject();
                result.put("aa",aa);
                return ok(result);
            }catch (IOException e) {
                return badRequest("invalid request");
            }
        }
        return badRequest("picture is null");
    }

    public boolean isPicture(File file){
        String ext=file.getName().substring(file.getName().lastIndexOf(".")+1);
        if(file.isFile() &&(
                ext.equals("png") || ext.equals("jpeg") ||ext.equals("jpg")) )
            return true;
        return false;
    }

}
