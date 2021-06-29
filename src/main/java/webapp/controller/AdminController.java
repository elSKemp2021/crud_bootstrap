package webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webapp.model.User;
import webapp.service.RoleService;
import webapp.service.UserService;
import javax.transaction.Transactional;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
@Transactional
public class AdminController {

    private  UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("activeUser", userService.getUserByUsername(principal.getName()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/admin";
    }

    @PostMapping("/adduser")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "roles_string") String[] roles) {

        if (roles != null) {
            user.setRoles(roleService.getRoleByName(roles));
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user,
                           @RequestParam(name = "roles_string", required = false) String[] roles) {
        System.out.println("ЗАЙТИ");
        if (roles != null) {
            user.setRoles(roleService.getRoleByName(roles));
        }
        userService.editUser(user);
        System.out.println("Выйти");
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(userService.getUser(id));
        return "redirect:/admin";
    }
}