package insurance.model.user;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "allRoles", query = "SELECT r.roleName FROM Role AS r")
public class Role implements Serializable{

    @Id
    protected String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
