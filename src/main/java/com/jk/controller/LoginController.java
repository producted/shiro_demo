package com.jk.controller;

import com.jk.common.ResponseBo;
import com.jk.model.User;
import com.jk.service.UserService;
import com.jk.util.MD5Utils;
import com.jk.util.Message;
import com.jk.util.vcode.Captcha;
import com.jk.util.vcode.GifCaptcha;
import com.jk.util.vcode.ValidateCodeProperties;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static org.apache.shiro.util.ThreadContext.getSubject;

@Controller
public class LoginController {

    private static final String CODE_KEY = "_code";

    @Autowired
    private UserService userService;

    @RequestMapping("toMain")
    public String toMain(){
        return "/main";
    }



    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index";
    }

    /*@RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> loginFail(User user,String code, ModelMap mm){
        //获取到当前认证器所抛出的异常类的类名
        *//*String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        if (UnknownAccountException.class.getName().equals(exceptionClassName) || AuthenticationException.class.getName().equals(exceptionClassName)) {
            request.setAttribute("flag","账号不存在");
        }
        if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            request.setAttribute("flag","密码错误");
        }*//*

        if (user == null) {
            return Message.ok("登录失败");
        }
        String loginNumber = user.getLoginNumber();
        String password = user.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(loginNumber, password, false);
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        }catch (IncorrectCredentialsException e){
                return Message.ok("密码错误");
            }catch (AuthenticationException e){
                return Message.ok("登录失败");
        }
        return Message.ok("SUCCESS");
    }*/

    @PostMapping("/login")
    @ResponseBody
    public ResponseBo login(User user, String code, Boolean rememberMe,HttpServletRequest request) {
        if (StringUtils.isEmpty(code)) {
            return ResponseBo.warn("验证码不能为空！");
        }
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute(CODE_KEY);
        if (!code.equalsIgnoreCase(sessionCode)) {
            return ResponseBo.warn("验证码错误！");
        }

        String password = user.getPassword();
        // 密码 MD5 加密
        //password = MD5Utils.encrypt(user.getLoginNumber().toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginNumber(), password, rememberMe);
        try {
            Subject subject = getSubject();
            if (subject != null)
                subject.logout();
            subject.login(token);
            //this.userService.updateLoginTime(username);
            return ResponseBo.ok();
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseBo.error("认证失败！");
        }
    }

    @GetMapping(value = "gifCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            ValidateCodeProperties validateCodeProperties = new ValidateCodeProperties();
            Captcha captcha = new GifCaptcha(
                    validateCodeProperties.getWidth(),
                    validateCodeProperties.getHeight(),
                    validateCodeProperties.getLength());
            HttpSession session = request.getSession(true);
            captcha.out(response.getOutputStream());
            session.removeAttribute(CODE_KEY);
            session.setAttribute(CODE_KEY, captcha.text().toLowerCase());
        } catch (Exception e) {
            System.out.println("图形验证码生成失败,"+e);
        }
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
}
