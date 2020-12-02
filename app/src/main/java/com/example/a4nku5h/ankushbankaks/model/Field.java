
package com.example.a4nku5h.ankushbankaks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Field {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("placeholder")
    @Expose
    private String placeholder;

    @SerializedName("regex")
    @Expose
    private String regex;

    @SerializedName("type")
    @Expose
    private Type type;

    @SerializedName("is_mandatory")
    @Expose
    private String isMandatory;

    @SerializedName("hint_text")
    @Expose
    private String hintText;

    @SerializedName("ui_type")
    @Expose
    private UiType uiType;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPlaceholder(){
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(String isMandatory) {
        this.isMandatory = isMandatory;
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    public UiType getUiType() {
        return uiType;
    }

    public void setUiType(UiType uiType) {
        this.uiType = uiType;
    }

}
