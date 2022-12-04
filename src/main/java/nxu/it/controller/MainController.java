package nxu.it.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import nxu.it.Admin;
import nxu.it.RequireAdmin;
import nxu.it.RequireLogin;
import nxu.it.SysUser;
import nxu.it.validator.LoginFormValidator;

import java.util.List;

import static nxu.it.RequireLogin.LOGIN_REDIRECT_URL_KEY;
import static nxu.it.RequireLogin.USER_SESSION_KEY;


@Path(value = "/",viewPath = "/")
public class MainController extends Controller {
    public void index() {
        renderHtml("<h2>It works!</h2>");
    }

    public void hello() {
//        String username=get("username");
//        String htmlTpl="<h2>你好!</h2>";
//        String html=String.format(htmlTpl,username);
//        renderHtml(html);
        renderQrCode("你好", 240, 240);
    }

    public void bye() {
        //renderHtml("<h2>再见！</h2>");
        renderQrCode("再见", 240, 240);
    }

    public void today() {
        String jsonStr = "{year: 2021, month: 4, day: 14, hour: 9, minute: 15, weekday: Wednesday}";
        renderJson(jsonStr);
    }

    public void register() {
        List<String> hometownList = List.of("北京", "天津", "上海", "重庆",
                "宁夏", "陕西", "甘肃", "青海", "内蒙", "四川", "河南", "河北");
        List<String> hobbies = List.of("唱", "跳", "Rap", "篮球");
        set("hometownList", hometownList);
        set("hobbies", hobbies);
        renderFreeMarker("register.ftl");
    }

    public void login() {
        renderFreeMarker("login.ftl");
    }

    public void logout(){
        removeSessionAttr(USER_SESSION_KEY);
        removeSessionAttr(LOGIN_REDIRECT_URL_KEY);
        redirect("/login");
    }

    public void doRegister() {
        String username = get("username");
        String password = get("password");
        Integer gender = getInt("gender");
        String hometown = get("hometown");
        String[] hobby = getParaValues("hobby");

        set("username", username);
        set("password", password);
        set("gender", gender);
        set("hometown", hometown);
        set("hobby", String.join(",", hobby));
        renderTemplate("register-result.html");
    }
    public void loginSuccess(){
        String username=get("username");
        String character=get("username");
        set("username",username);
        set("character",username);
        renderTemplate("login-result.html");
    }
@Before(LoginFormValidator.class)
    public void loginCheck() {
        String username = get("username");
        String password = get("password");
        if (username.equals("admin") && password.equals("123456") || password.equals("nxu")) {
            SysUser sysUser=new SysUser(username);
            setSessionAttr(USER_SESSION_KEY,sysUser);
            String redirectUrl=getSessionAttr(LOGIN_REDIRECT_URL_KEY);
            if (redirectUrl !=null && !redirectUrl.isBlank()){
                redirect(redirectUrl);
            }else {
                renderHtml("<h2 style=\"color:green;\">登录成功！</h2>");
            }
            //setCookie("username",username, 30 * 60, true);
        } else {
            renderHtml("<h2 style=\"color:red;\">登录失败</h2>");
        }
    }
public boolean checkUserLogin(){
        String username=getSessionAttr("username");
        if (username==null){
            redirect("/login");
            return false;
        }
        return true;
}


@Before(RequireLogin.class)
    public void main() {
        //String username = getCookie("username");
        SysUser sysUser = getSessionAttr(USER_SESSION_KEY);
        set("sysUser",sysUser);
        renderFreeMarker("main.ftl");
    }
    @Before(RequireLogin.class)
    public void test(){
        //String username=getSessionAttr("username");
//        if (username==null){
//            redirect("/login");
//        }else
            renderHtml("<h2 style=\"color:green;\">你好,欢迎再次访问！</h2>");
    }
    @Admin
    @Before(RequireLogin.class)
    public void admin(){
        renderHtml("<h2 style=\"color:blue;\">admin页面</h2>");
    }
}