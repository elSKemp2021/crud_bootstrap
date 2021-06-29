package webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webapp.service.UserService;
import javax.transaction.Transactional;
import java.security.Principal;

@Controller
@RequestMapping("/user")
@Transactional
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUser(Model model,
                          Principal principal) {
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        return "user/user";
    }
}