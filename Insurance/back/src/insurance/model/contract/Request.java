package insurance.model.contract;

import javax.persistence.*;
import java.io.Serializable;

//TODO foreign key sur le contractID

@Entity
@NamedQueries({@NamedQuery(name="allRemoveRequestsForType", query = "SELECT r FROM Request r WHERE r.contractId IN ( :contractIds) AND r.requestType = :requestType"),
        @NamedQuery(name="removalRequestForContractId", query = "UPDATE Request r SET r.requestType = \"removal\" WHERE r.contractId = :contractId"),
        @NamedQuery(name = "getRequestForContractId", query = "SELECT r FROM Request r WHERE r.contractId = :contractId")})
public class Request implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    protected Integer contractId;
    protected Boolean insuredValidation;
    protected Boolean brokerAcceptance;
    // remove or subscribe
    protected String requestType;

    public Request(Integer contractId, Boolean insuredValidation, Boolean brokerAcceptance, String requestType) {
        this.contractId = contractId;
        this.insuredValidation = insuredValidation;
        this.brokerAcceptance = brokerAcceptance;
        this.requestType = requestType;
    }

    public Request(Integer contractId, String requestType, Boolean insuredValidation) {
        this.contractId = contractId;
        this.requestType = requestType;
        this.insuredValidation = insuredValidation;
    }

    public Request() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Boolean getInsuredValidation() {
        return insuredValidation;
    }

    public void setInsuredValidation(Boolean insuredValidation) {
        this.insuredValidation = insuredValidation;
    }

    public Boolean getBrokerAcceptance() {
        return brokerAcceptance;
    }

    public void setBrokerAcceptance(Boolean brokerAcceptance) {
        this.brokerAcceptance = brokerAcceptance;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
