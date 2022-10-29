package org.worldline.assignment.perfectnumbers.response;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;
@XmlRootElement(name = "NumberResponseMapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class NumberResponseMapper implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean operationSuccess;
    private boolean isPerfectNumber;
    private List<Integer> perfectNumberList;
    private Response response;

    private String message;

    public boolean isOperationSuccess() {
        return operationSuccess;
    }

    public void setOperationSuccess(boolean isOperationSuccess) {
        this.operationSuccess = isOperationSuccess;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public void setPerfectNumber(boolean perfectNumber) {
        isPerfectNumber = perfectNumber;
    }

    public boolean isPerfectNumber() {
        return isPerfectNumber;
    }

    public List<Integer> getPerfectNumberList() {
        return perfectNumberList;
    }

    public void setPerfectNumberList(List<Integer> perfectNumberList) {
        this.perfectNumberList = perfectNumberList;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
