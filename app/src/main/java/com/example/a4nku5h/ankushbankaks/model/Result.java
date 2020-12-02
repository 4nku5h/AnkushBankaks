
package com.example.a4nku5h.ankushbankaks.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("number_of_fields")
    @Expose
    private Integer numberOfFields;
    @SerializedName("screen_title")
    @Expose
    private String screenTitle;
    @SerializedName("operator_name")
    @Expose
    private String operatorName;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("fields")
    @Expose
    private List<Field> fields = null;

    public Integer getNumberOfFields() {
        return numberOfFields;
    }

    public void setNumberOfFields(Integer numberOfFields) {
        this.numberOfFields = numberOfFields;
    }

    public String getScreenTitle() {
        return screenTitle;
    }

    public void setScreenTitle(String screenTitle) {
        this.screenTitle = screenTitle;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

}
