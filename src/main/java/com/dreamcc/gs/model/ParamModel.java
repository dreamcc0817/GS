package com.dreamcc.gs.model;

public class ParamModel extends BaseModel {
	private Integer sysParamId;

	private String sysParamField;

	private String sysParamValue;

	private String sysParamText;

	public Integer getSysParamId() {
		return sysParamId;
	}

	public void setSysParamId(Integer sysParamId) {
		this.sysParamId = sysParamId;
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