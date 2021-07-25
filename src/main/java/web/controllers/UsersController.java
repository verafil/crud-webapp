package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import web.dao.UserDaoImp;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDaoImp userDaoImp;

    @Autowired
    public UsersController(UserDaoImp userDaoImp) {
        this.userDaoImp = userDaoImp;
    }

    @GetMapping()
    public String index(ModelMap model) {
        //получим список всех пользователей БД
        model.addAttribute(userDaoImp.readAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String showUserByID(@PathVariable("id") int id, ModelMap model) {
        //получим пользователя по переданному id
        model.addAttribute("usersList", userDaoImp.readById(id));
        return "users/showUserByID";
    }
}
