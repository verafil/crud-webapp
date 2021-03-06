package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.models.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!!!");
        messages.add("I'm Spring MVC application");
        model.addAttribute("messages", messages);
        return "index";
    }

    @GetMapping("/users")
    public String index(Model model) {
        //получим список всех пользователей БД
        model.addAttribute("usersList", userService.readAll());
        return "show";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.create(user);
        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        User user = userService.readById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String deleteUser(User user) {
        userService.update(user);
        return "redirect:/users";
    }
}
