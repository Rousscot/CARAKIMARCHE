package insurance.remote;

import insurance.model.contract.Contract;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ContractRemote {
    List<Contract> listContracts();

    void removeContract(Integer id);

    void addLifeContract(String title, String username, Integer amount, Integer kindid, String category, Integer capitalAmount, Integer minYears);

    void addHouseContract(String title, String username, Integer amount, Integer kindid, String category, Integer maxAmount, String address);

    void addCarContract(String title, String username, Integer amount, Integer kindid, String category, String model, String plate);
}
