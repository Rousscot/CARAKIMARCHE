package insurance.model.contract;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "allContracts", query = "SELECT c FROM Contract c"),
        @NamedQuery(name = "allContractsForUser", query = "SELECT c FROM Contract c WHERE c.username = :username"),
        @NamedQuery(name = "allContractsForUserForCategory", query = "SELECT c FROM Contract c WHERE c.username = :username AND c.category = :category"),
        @NamedQuery(name = "allContractsForCategory", query = "SELECT c FROM Contract c WHERE c.category = :category")})
public class Contract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    protected String title;
    protected String username;
    protected Integer amount;
    protected Integer kindId;
    protected String category;
    protected Integer maxAmount;
    protected String address;
    protected String model;
    protected String plate;
    protected Integer capitalAmount;
    protected Integer minYears;

    public Contract() {
    }

    public Contract(String title, String username, Integer amount, Integer kindid, String category) {
        this.title = title;
        this.username = username;
        this.amount = amount;
        this.kindId = kindid;
        this.category = category;
    }

    public Contract(String title, String username, Integer amount, Integer kindid, String category, Integer maxAmount, String address) {
        this(title, username, amount, kindid, category);
        this.maxAmount = maxAmount;
        this.address = address;
    }

    public Contract(String title, String username, Integer amount, Integer kindid, String category, String model, String plate) {
        this(title, username, amount, kindid, category);
        this.model = model;
        this.plate = plate;
    }

    public Contract(String title, String username, Integer amount, Integer kindid, String category, Integer capitalAmount, Integer minYears) {
        this(title, username, amount, kindid, category);
        this.capitalAmount = capitalAmount;
        this.minYears = minYears;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Integer getCapitalAmount() {
        return capitalAmount;
    }

    public void setCapitalAmount(Integer capitalAmount) {
        this.capitalAmount = capitalAmount;
    }

    public Integer getMinYears() {
        return minYears;
    }

    public void setMinYears(Integer minYears) {
        this.minYears = minYears;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getKindId() {
        return kindId;
    }

    public void setKindId(Integer kindId) {
        this.kindId = kindId;
    }
}
