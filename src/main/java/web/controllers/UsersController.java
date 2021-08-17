package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.dto.UserDto;
import web.models.User;
import web.service.UserServiceImp;

@Controller
public class UsersController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("usersList", userServiceImp.readAll());
        return "admin-panel";
    }

//    @PostMapping("/user-create")
//    public String createUser(UserDto userDto) {
//        userServiceImp.saveUser(userDto);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/user-delete/{id}")
//    public String deleteUser(@PathVariable("id") int id) {
//        userServiceImp.delete(id);
//        return "redirect:/admin";
//    }
//
//    @PostMapping("/user-update")
//    public String updateUser(UserDto userDto) {
//        userServiceImp.updateUser(userDto);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/user/{id}")
//    public String personalPageUser(@PathVariable("id") int id, Model model) {
//        User user = userServiceImp.readById(id);
//        model.addAttribute("user", user);
//        model.addAttribute("roles", user.getRoles());
//        return "user-personal";
//    }
}
