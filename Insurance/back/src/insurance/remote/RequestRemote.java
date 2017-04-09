package insurance.remote;

import insurance.model.contract.Request;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RequestRemote {
    List<Request> listRequestsForContracts(List<Integer> contractIds, String requestType);

    void requestRemovalForContract(Integer contractId);

    void addRequest(Integer contractId, String requestType, Boolean insuredValidation);

    Request getRequestFromContractId(Integer contractId);

    void cancelRequestedContract(Integer contractId);

    void validRequestedContract(Integer contractId);
}
