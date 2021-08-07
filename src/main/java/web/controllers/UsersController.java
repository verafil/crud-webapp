package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.dto.UserDto;
import web.models.Role;
import web.models.User;
import web.service.UserServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UsersController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!!!");
        messages.add("I'm Spring MVC application");
        model.addAttribute("messages", messages);
        return "index";
    }

    @GetMapping("/admin")
    public String index(Model model) {
        //получим список всех пользователей БД
        model.addAttribute("usersList", userServiceImp.readAll());
        return "show";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(UserDto userDto) {
        userServiceImp.saveUser(userDto);
        return "redirect:/admin";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userServiceImp.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        User user = userServiceImp.readById(id);
        model.addAttribute("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(UserDto userDto) {
        userServiceImp.updateUser(userDto);
        return "redirect:/admin";
    }

    @GetMapping("/user/{id}")
    public String personalPageUser(@PathVariable("id") int id, Model model) {
        User user = userServiceImp.readById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user-personal";
    }
}
