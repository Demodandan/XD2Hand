package site.luoyu.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.luoyu.model.User;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Computer user xd
 * Created by 张洋 on 2017/2/21.
 */
@Controller
@RequestMapping("/learn")
public class ForLearn {

    private static Logger logger = LogManager.getLogger(ForLearn.class);

    @RequestMapping()
    public String getTestView(Model model) {
        logger.info("记录日志，看日志器是否能够拦截");
        model.addAttribute("User", new User());
        return "test/testForm";
    }

    // TODO: 2017/2/21 Valid 还没有试验完
    @RequestMapping(value = "testForm", produces = "text/plain")
    @ResponseBody
    public String testForm(@Valid User user, Errors errors, HttpServletResponse response) {
        if (errors.hasErrors()) return "test/testForm";
        return "用户名：" + user.getName() + " 密码:" + user.getPasswd();
//        return "用户名：" + user.getName() + " 密码:" + user.getPasswd();
    }

    @RequestMapping("/testJson")
    @ResponseBody
    public String testJson(@RequestBody User user) {
        System.out.println(user.getName() + " " + user.getPasswd());
//        todo Jquary当返回格式不是json就调用error
        return "success";
    }
}
