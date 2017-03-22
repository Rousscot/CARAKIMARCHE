package insurance.remote;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface UserRemote {

    boolean login(String lastName, String password);
    List<String> listUsers();

}
