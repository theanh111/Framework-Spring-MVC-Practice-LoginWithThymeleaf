package controller;

import dao.UserDAO;
import model.Login;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {


    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home", "login", new Login());
        return modelAndView;
    }

    // Cach 1:
    @PostMapping("/login")
    public String login(Login login, RedirectAttributes redirect, Model model) {
        User user = UserDAO.checkLogin(login);
        if (user == null) {
            redirect.addFlashAttribute("message", "Wrong info! Please try again!");
            return "redirect:/";
        } else {
            model.addAttribute("user", user);
            return "user";
        }
    }

    // Cach 2:
//    @PostMapping("/login")
//    public ModelAndView login(@ModelAttribute("login") Login login) {
//        User user = UserDAO.checkLogin(login);
//        if(user == null) {
//            ModelAndView modelAndView = new ModelAndView("error");
//            return modelAndView;
//        } else {
//            ModelAndView modelAndView = new ModelAndView("user");
//            modelAndView.addObject("user", user);
//            return modelAndView;
//        }
//    }
}
