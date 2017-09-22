package com.dreamcc.gs.bean;

public class Param extends BaseBean{
    private Integer id;

    private String sysParamField;

    private String sysParamValue;

    private String sysParamText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSysParamField() {
        return sysParamField;
    }

    public void setSysParamField(String sysParamField) {
        this.sysParamField = sysParamField == null ? null : sysParamField.trim();
    }

    public String getSysParamValue() {
        return sysParamValue;
    }

    public void setSysParamValue(String sysParamValue) {
        this.sysParamValue = sysParamValue == null ? null : sysParamValue.trim();
    }

    public String getSysParamText() {
        return sysParamText;
    }

    public void setSysParamText(String sysParamText) {
        this.sysParamText = sysParamText == null ? null : sysParamText.trim();
    }
}