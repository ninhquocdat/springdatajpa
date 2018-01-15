package vn.hcmute.web.logic.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Admin on 17/6/2017.
 */
@Controller
public class LoginController {
    private final Logger log = Logger.getLogger(LoginController.class);
    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping(value = "/admin.html", method = RequestMethod.GET)
    public ModelAndView adminHomePage() {
        ModelAndView mav = new ModelAndView("admin/home/admin");
        return mav;
    }

    @RequestMapping(value = "/user.html", method = RequestMethod.GET)
    public ModelAndView userHomePage() {
        ModelAndView mav = new ModelAndView("admin/home/user");
        return mav;
    }

    @RequestMapping(value = "/manager.html", method = RequestMethod.GET)
    public ModelAndView managerHomePage() {
        ModelAndView mav = new ModelAndView("admin/home/manager");
        return mav;
    }

    @RequestMapping(value="/access-denied.html", method = RequestMethod.GET)
    public String accessDenied() {
        return "redirect:/login.html?accessDenied";
    }

    @RequestMapping(value="/logout.html", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login.html";
    }
}
