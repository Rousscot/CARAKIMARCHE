package insurance.remote;

import insurance.model.user.User;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface UserRemote {
    boolean login(String lastName, String password);
    List<User> listUsers();
    void addUser(String userName, String pswd, String firstName, String lastName);
    void removeUser(String userName);
}
