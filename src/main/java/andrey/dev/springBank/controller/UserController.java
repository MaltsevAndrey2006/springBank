package andrey.dev.springBank.controller;


import andrey.dev.springBank.model.User;
import andrey.dev.springBank.repositores.InMemoryUserRepository;
import andrey.dev.springBank.services.User.InMemoryUserServiceImpl;
import andrey.dev.springBank.services.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Users")
@AllArgsConstructor
public class UserController {
    private UserService inMemoryUserService;

    @PostMapping("createUser")
    public User createUser(@RequestBody User user) {
        return inMemoryUserService.createUser(user);
    }

    @GetMapping("{userId}")
    public User findUserById(@PathVariable int userId) {
        return inMemoryUserService.findUserById(userId);
    }

    @GetMapping
    public List<User> findAllUser() {
        return inMemoryUserService.findAllUser();
    }

    @DeleteMapping("delete_User/{userId}")
    public void deleteById(@PathVariable int userId) {
        inMemoryUserService.deleteUser(userId);
    }
}
