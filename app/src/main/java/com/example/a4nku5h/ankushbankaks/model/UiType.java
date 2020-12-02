
package com.example.a4nku5h.ankushbankaks.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UiType {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("values")
    @Expose
    private List<Value> values = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

}
