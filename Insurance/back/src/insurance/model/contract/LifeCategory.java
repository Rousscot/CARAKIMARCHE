package insurance.model.contract;

import javax.persistence.Entity;

@Entity
public class LifeCategory extends AbstractContractCategory {
    protected Long cost;
    protected Integer maxDuration;

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }
}
