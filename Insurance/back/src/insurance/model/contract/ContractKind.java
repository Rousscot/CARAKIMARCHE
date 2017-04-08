package insurance.model.contract;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "allContractKinds", query = "SELECT c FROM ContractKind c")
public class ContractKind implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    protected String title;
    protected String description;
    protected Integer minAmount;
    protected String category;

    public ContractKind() {
    }

    public ContractKind(String title, String description, Integer minAmount, String category) {
        this.title = title;
        this.description = description;
        this.minAmount = minAmount;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
