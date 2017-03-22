package insurance.model.contract;

import javax.persistence.Entity;

@Entity
public class AutoCategory extends AbstractContractCategory {
    protected String model;
    protected String plateNumber;
    protected String mainDriver;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getMainDriver() {
        return mainDriver;
    }

    public void setMainDriver(String mainDriver) {
        this.mainDriver = mainDriver;
    }
}
