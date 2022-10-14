package nxu.it.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

import java.util.List;


@Path(value = "/",viewPath = "/")
public class MainController extends Controller {
    public void index() {
        renderHtml("<h2>It works!</h2>");
    }

    public void hello(){
//        String username=get("username");
//        String htmlTpl="<h2>你好!</h2>";
//        String html=String.format(htmlTpl,username);
//        renderHtml(html);
          renderQrCode("你好",240,240);
    }
    public void bye(){
        //renderHtml("<h2>再见！</h2>");
        renderQrCode("再见",240,240);
    }
    public void today(){
        String jsonStr="{year: 2021, month: 4, day: 14, hour: 9, minute: 15, weekday: Wednesday}";
        renderJson(jsonStr);
    }
    public void register(){
        List<String> hometownList=List.of("北京","天津","上海","重庆",
                "宁夏","陕西","甘肃","青海","内蒙","四川","河南","河北");
        List<String> hobbies=List.of("唱","跳","Rap","篮球");
        set("hometownList",hometownList);
        set("hobbies",hobbies);
        renderFreeMarker("register.ftl");
    }
    public void login(){
        renderFreeMarker("login.ftl");
    }
    public void doRegister(){
        renderHtml("注册成功！");
    }
    public void loginCheck(){
        renderHtml("登录成功！");
    }
}
