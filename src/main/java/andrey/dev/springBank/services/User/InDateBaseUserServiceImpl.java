package andrey.dev.springBank.services.User;

import andrey.dev.springBank.model.User;
import andrey.dev.springBank.repositores.InDateBase.InDateBaseUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class InDateBaseUserServiceImpl implements UserService {
    private InDateBaseUserRepository inDateBaseUserRepository;

    @Override
    public User createUser(User user) {
        return inDateBaseUserRepository.createUser(user);
    }

    @Override
    public User findUserById(int UserId) {
        return inDateBaseUserRepository.findUserByUserId(UserId);
    }

    @Override
    public List<User> findAllUser() {
        return inDateBaseUserRepository.findAllUser();
    }

    @Override
    public void deleteUser(int userId) {
        inDateBaseUserRepository.deleteUserById(userId);
    }
}
