package controllers;

import aacj.*;
import aacj.util.AAUtil;
import play.*;
import play.mvc.*;

import util.ConvertionUtil;
import views.html.*;

import java.io.IOException;
import java.util.Base64;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render("You are already ready."));
    }
    public Result tst(){
        return ok("hello");
    }
    public Result getAA(String base64){
        String tmp;
        try {
            tmp = ConvertionUtil.aaConvertion(base64);
        }catch (IOException e){
            return badRequest("an error occured while reading image");
        }
        return ok(tmp);
    }
}
