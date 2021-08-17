package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.dto.UserDto;
import web.exception.DataInfoHandler;
import web.models.User;
import web.service.UserServiceImp;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {


    private final UserServiceImp userServiceImp;

    @Autowired
    public UserRestController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> index() {
        final List<User> users = userServiceImp.readAll();
        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> personalPageUser(@PathVariable("id") int id) {
        final User user = userServiceImp.readById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        userServiceImp.saveUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<DataInfoHandler> deleteUser(@PathVariable("id") int id) {
        userServiceImp.delete(id);
        return new ResponseEntity<>(new DataInfoHandler("User was deleted"), HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody UserDto user) {
        userServiceImp.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

