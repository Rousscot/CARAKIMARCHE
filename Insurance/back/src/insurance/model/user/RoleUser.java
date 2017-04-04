package insurance.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class RoleUser implements Serializable{
    @Id
    protected String userName;
    @Id
    protected String roleName;

    public RoleUser() {
    }

    public RoleUser(String userName, String roleName) {
        this.userName = userName;
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
