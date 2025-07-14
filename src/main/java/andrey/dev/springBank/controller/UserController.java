package andrey.dev.springBank.controller;


import andrey.dev.springBank.model.User;
import andrey.dev.springBank.services.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Users")
@AllArgsConstructor
public class UserController {
    private UserService UserService;

    @PostMapping("createUser")
    public User createUser(@RequestBody User user) {
        return UserService.createUser(user);
    }

    @GetMapping("{userId}")
    public User findUserById(@PathVariable int userId) {
        return UserService.findUserById(userId);
    }

    @GetMapping
    public List<User> findAllUser() {
        return UserService.findAllUser();
    }

    @DeleteMapping("delete_User/{userId}")
    public void deleteById(@PathVariable int userId) {
        UserService.deleteUser(userId);
    }
}
