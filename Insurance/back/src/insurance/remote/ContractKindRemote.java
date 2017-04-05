package insurance.remote;

import insurance.model.contract.ContractKind;
import insurance.model.utils.ContractKindEnum;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ContractKindRemote {
    List<ContractKind> listContractKinds();
    void removeContractKind(Integer id);
    void addContractKind(String title, String description, Integer minAmount, ContractKindEnum category);
}
