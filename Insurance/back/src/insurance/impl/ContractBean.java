package insurance.impl;

import insurance.model.contract.Contract;
import insurance.model.contract.ContractKind;
import insurance.remote.ContractRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ContractBean implements ContractRemote {
    @PersistenceContext(unitName = "Insurance-ejb")
    protected EntityManager persistance;

    public List<Contract> listContracts() {
        Query query = persistance.createNamedQuery("allContracts");
        List<Contract> contractKinds = new ArrayList<>();
        contractKinds.addAll((List<Contract>) query.getResultList());
        return contractKinds;
    }

    public void removeContract(Integer id) {
        List<Contract> contracts = listContracts();
        for (Contract contract : contracts) {
            if (contract.getId().equals(id)) {
                persistance.remove(contract);
            }
        }
    }

    @Override
    public void addLifeContract(String title, String username, Integer amount, Integer kindid, String category, Integer capitalAmount, Integer minYears) {
        Contract contract = new Contract(title, username, amount, kindid, category, capitalAmount, minYears);
        persistance.persist(contract);
    }

    @Override
    public void addHouseContract(String title, String username, Integer amount, Integer kindid, String category, Integer maxAmount, String address) {
        Contract contract = new Contract(title, username, amount, kindid, category, maxAmount, address);
        persistance.persist(contract);
    }

    @Override
    public void addCarContract(String title, String username, Integer amount, Integer kindid, String category, String model, String plate) {
        Contract contract = new Contract(title, username, amount, kindid, category, model, plate);
        persistance.persist(contract);
    }

}