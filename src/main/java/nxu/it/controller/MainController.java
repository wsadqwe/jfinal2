package nxu.it.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;


@Path(value = "/")
public class MainController extends Controller {
    public void index() {
        renderHtml("<h2>It works!</h2>");
    }

    public void hello(){
        String username=get("username");
        String htmlTpl="<h2>你好!</h2>";
        String html=String.format(htmlTpl,username);
        renderHtml(html);
    }
    public void bye(){renderHtml("<h2>再见！</h2>");}
}
