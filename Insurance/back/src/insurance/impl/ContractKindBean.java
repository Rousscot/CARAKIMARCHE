package insurance.impl;

import insurance.model.contract.ContractKind;
import insurance.remote.ContractKindRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ContractKindBean implements ContractKindRemote {
    @PersistenceContext(unitName = "Insurance-ejb")
    protected EntityManager persistance;

    public List<ContractKind> listContractKinds() {
        Query query = persistance.createNamedQuery("allContractKinds");
        List<ContractKind> contractKinds = new ArrayList<>();
        contractKinds.addAll((List<ContractKind>) query.getResultList());
        return contractKinds;
    }

    public void removeContractKind(Integer id) {
        List<ContractKind> contractKinds = listContractKinds();
        for (ContractKind contractKind : contractKinds) {
            if (contractKind.getId().equals(id)) {
                persistance.remove(contractKind);
            }
        }
    }

    public void addContractKind(String title, String description, Integer minAmount, String category) {
        ContractKind contractKind = new ContractKind(title, description, minAmount, category);
        persistance.persist(contractKind);
    }
}
