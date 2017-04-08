package insurance.remote;

import insurance.model.contract.ContractKind;
import insurance.model.utils.ContractKindEnum;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ContractKindRemote {
    List<ContractKind> listContractKinds();
    ContractKind getContractKindFromId(Integer id);
    void removeContractKind(Integer id);
    void addContractKind(String title, String description, Integer minAmount, String category);
}
