package insurance.model.user;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "allUsers", query = "SELECT u FROM User u")
public class User implements Serializable {

    @Id
    protected String userName;
    protected String pswd;
    protected String firstName;
    protected String lastName;

    public User(){}

    public User(String userName, String pswd, String firstName, String lastName) {
        this.userName = userName;
        this.pswd = pswd;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String password) {
        this.pswd = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
