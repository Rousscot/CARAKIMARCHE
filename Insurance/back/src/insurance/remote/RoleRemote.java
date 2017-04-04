package insurance.remote;

import insurance.model.user.Role;
import insurance.model.user.User;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RoleRemote {
    void addUserRole(String userName, String roleName);
    List<Role> listRoles();
}
