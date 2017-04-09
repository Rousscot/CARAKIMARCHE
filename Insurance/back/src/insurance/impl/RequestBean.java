package insurance.impl;

import insurance.model.contract.Request;
import insurance.remote.RequestRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class RequestBean implements RequestRemote {
    @PersistenceContext(unitName = "Insurance-ejb")
    protected EntityManager persistance;

    public List<Request> listRequestsForContracts(List<Integer> contractIds, String requestType) {
        Query query = persistance.createNamedQuery("allRemoveRequestsForType");
        query.setParameter("contractIds", contractIds);
        query.setParameter("requestType", contractIds);
        return (List<Request>) query.getResultList();
    }

    public void requestRemovalForContract(Integer contractId) {
        Query query = persistance.createNamedQuery("removalRequestForContractId");
        query.setParameter("contractId", contractId);
        query.executeUpdate();
    }

    public void addRequest(Integer contractId, String requestType, Boolean insuredValidation) {
        Request request = new Request(contractId, requestType, insuredValidation);
        persistance.persist(request);
    }

    public Request getRequestFromContractId(Integer contractId) {
        Query query = persistance.createNamedQuery("getRequestForContractId");
        query.setParameter("contractId", contractId);
        try{
            return (Request)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
