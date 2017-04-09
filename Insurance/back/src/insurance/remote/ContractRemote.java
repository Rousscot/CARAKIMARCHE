package insurance.remote;

import insurance.model.contract.Contract;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ContractRemote {
    List<Contract> listContracts();

    List<Contract> listContractsForCategory(String category);

    List<Contract> listContractsForUserForCategory(String userName, String category);

    List<Contract> listContractsRequestedForUserForCategory(String userName, String category);

    void removeContract(Integer id);

    void addLifeContract(String title, String username, Integer amount, Integer kindid, String category, Integer capitalAmount, Integer minYears);

    void addHouseContract(String title, String username, Integer amount, Integer kindid, String category, Integer maxAmount, String address);

    void addCarContract(String title, String username, Integer amount, Integer kindid, String category, String model, String plate);

    Integer addSubscriptionLifeContract(String title, String username, Integer amount, Integer kindid, String category, Integer capitalAmount, Integer minYears);

    Integer addSubscriptionHouseContract(String title, String username, Integer amount, Integer kindid, String category, Integer maxAmount, String address);

    Integer addSubscriptionCarContract(String title, String username, Integer amount, Integer kindid, String category, String model, String plate);
}
