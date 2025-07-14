package andrey.dev.springBank.services.User;


import andrey.dev.springBank.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User findUserById(int UserId);

    List<User> findAllUser();

    void deleteUser(int userId);
}
