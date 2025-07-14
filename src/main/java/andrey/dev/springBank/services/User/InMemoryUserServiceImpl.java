package andrey.dev.springBank.services.User;

import andrey.dev.springBank.model.User;
import andrey.dev.springBank.repositores.InMemoryUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryUserServiceImpl implements UserService {
    InMemoryUserRepository inMemoryUserRepository;

    @Override
    public User createUser(User user) {
        inMemoryUserRepository.addToUserList(user);
        return user;
    }

    @Override
    public User findUserById(int userId) {
        return inMemoryUserRepository.findAllUsers().stream().filter(e -> e.getUserId() == userId)
                .findFirst().orElse(null);
    }

    @Override
    public List<User> findAllUser() {
        return inMemoryUserRepository.findAllUsers();
    }

    @Override
    public void deleteUser(int userId) {
        inMemoryUserRepository.deleteUser(userId);
    }


}
