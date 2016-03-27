package controllers;

import aacj.*;
import aacj.util.AAUtil;
import akka.stream.impl.fusing.GraphStages;
import javax.inject.*;
import play.Play;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Http.*;
import play.*;

import play.http.HttpErrorHandler;
import play.mvc.*;

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
    FormFactory formfactory;
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        Form<ConvertData> f=formfactory.form(ConvertData.class).bindFromRequest();
        return ok(index.render("this is top page","",f));
    }

    /**
     * ファイルはフォームデータの一部として送られてこない．
     * フォームのデータに添付されて送られてくる．
     * http://stackoverflow.com/questions/9452375/how-to-get-the-upload-file-with-other-inputs-in-play2#9587052
     * @return
     */
    public Result send(){
        Form<ConvertData> f=formfactory.form(ConvertData.class).bindFromRequest();
        MultipartFormData body=request().body().asMultipartFormData();
        MultipartFormData.FilePart picture=body.getFile("picture");
        if(picture!=null){
            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            File file = (File)picture.getFile();
            file.renameTo(new File(
                    Play.application().path().getPath()+"/public/images/"
                    ,fileName)); //TODO should use DI
            return ok(index.render("file uploaded",fileName,f));
        }
        else{
            return badRequest(index.render("please upload a picture","",f));
        }
        /*Form<ConvertData> f=formfactory.form(ConvertData.class).bindFromRequest();
        ConvertData data=f.get();
        if(data.getPicture()!=null && isPicture(data.getPicture())){
            File file=data.getPicture();
            file.renameTo(new File(
                    Play.application().path().getPath()+"/public/images/"
                    ,file.getName()));
            ok(index.render("your file is successfully uploaded.",
                    file.getName(),
                    f.fill(new ConvertData())));
        }
        return badRequest("please upload a picture");*/
    }
    public  Result tst(){
        return ok("hello");
    }
    @BodyParser.Of(Text10Kb.class)
    public Result getAA(){
        String method = request().method();
        if ("GET".equals(method)) {
            return badRequest();
        }
        Map<String, String[]> form = request().body().asFormUrlEncoded();
        String base64 = form.get("base64")[0];
        String tmp;
        try {
            tmp = ConvertionUtil.aaConvertion(base64);
        }catch (IOException e){
            return badRequest("an error occured while reading image");
        }
        return ok(tmp);
    }
    public class Text10Kb extends BodyParser.FormUrlEncoded {
        @Inject
        public Text10Kb(HttpErrorHandler errorHandler) {
            super(1 * 1024 *1024, errorHandler);
        }
    }
    public boolean isPicture(File file){
        String ext=file.getName().substring(file.getName().lastIndexOf(".")+1);
        if(file.isFile() &&(
                ext.equals("png") || ext.equals("jpeg") ||ext.equals("jpg")) )
            return true;
        return false;
    }

}
