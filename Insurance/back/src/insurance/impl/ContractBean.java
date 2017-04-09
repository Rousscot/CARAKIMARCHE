package insurance.impl;

import insurance.model.contract.Contract;
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
        Query query = persistance.createNamedQuery("allActiveContracts");
        List<Contract> contracts = new ArrayList<>();
        contracts.addAll((List<Contract>) query.getResultList());
        return contracts;
    }

    public List<Contract> listContractsForCategory(String category) {
        Query query = persistance.createNamedQuery("allContractsForCategory");
        query.setParameter("category", category);
        List<Contract> contracts = new ArrayList<>();
        contracts.addAll((List<Contract>) query.getResultList());
        return contracts;
    }

    public List<Contract> listContractsRequestedForCategory(String category) {
        Query query = persistance.createNamedQuery("allContractsRequestedForCategory");
        query.setParameter("category", category);
        List<Contract> contracts = new ArrayList<>();
        contracts.addAll((List<Contract>) query.getResultList());
        return contracts;
    }

    public List<Contract> listContractsForUserForCategory(String userName, String category) {
        Query query = persistance.createNamedQuery("allContractsForUserForCategory");
        query.setParameter("username", userName);
        query.setParameter("category", category);
        List<Contract> contracts = new ArrayList<>();
        contracts.addAll((List<Contract>) query.getResultList());
        return contracts;
    }

    public List<Contract> listContractsRequestedForUserForCategory(String userName, String category) {
        Query query = persistance.createNamedQuery("allContractsRequestedForUserAndCategory");
        query.setParameter("userName", userName);
        query.setParameter("category", category);
        List<Contract> contracts = new ArrayList<>();
        contracts.addAll((List<Contract>) query.getResultList());
        return contracts;
    }

    public void removeContract(Integer id) {
        Contract contract = persistance.find(Contract.class, id);
        persistance.remove(contract);
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


    @Override
    public Integer addSubscriptionLifeContract(String title, String username, Integer amount, Integer kindid, String category, Integer capitalAmount, Integer minYears) {
        Contract contract = new Contract(title, username, amount, kindid, category, capitalAmount, minYears, false);
        persistance.persist(contract);
        persistance.flush();
        return contract.getId();
    }

    @Override
    public Integer addSubscriptionHouseContract(String title, String username, Integer amount, Integer kindid, String category, Integer maxAmount, String address) {
        Contract contract = new Contract(title, username, amount, kindid, category, maxAmount, address, false);
        persistance.persist(contract);
        persistance.flush();
        return contract.getId();
    }

    @Override
    public Integer addSubscriptionCarContract(String title, String username, Integer amount, Integer kindid, String category, String model, String plate) {
        Contract contract = new Contract(title, username, amount, kindid, category, model, plate, false);
        persistance.persist(contract);
        persistance.flush();
        return contract.getId();
    }

    public void activeContract(Integer contractId){
        Query query = persistance.createNamedQuery("activeContract");
        query.setParameter("contractId", contractId);
        query.executeUpdate();
    }

    public void desactiveContract(Integer contractId){
        Query query = persistance.createNamedQuery("desactiveContract");
        query.setParameter("contractId", contractId);
        query.executeUpdate();
    }

}
