package andrey.dev.springBank.repositores.InMemory;

import andrey.dev.springBank.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryUserRepository {

    private List<User> userList = new ArrayList<>();

    public void addToUserList(User user) {
        userList.add(user);
    }

    public void deleteFromUserList(User user) {
        userList.remove(user);
    }

    public List<User> findAllUsers() {
        return userList;
    }

    public void deleteUser(int userId) {
        User user = userList.stream()
                .filter(e -> e.getUserId() == userId).findFirst().orElse(null);

        if (user != null) {
            userList.remove(user);
        }
    }
    
}
