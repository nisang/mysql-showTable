package io.dandan.entity;

public class FieldRecord {
	private String field;
	private String type;
	private String length;
	private String isNull;
	private String note;
	/**
	 * 数字长度
	 */
	private String munericLength;
	/**
	 * 小数位数
	 */
	private String numericScale;

	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getNumericScale() {
		return numericScale;
	}
	public void setNumericScale(String numericScale) {
		this.numericScale = numericScale;
	}
	public String getMunericLength() {
		return munericLength;
	}
	public void setMunericLength(String munericLength) {
		this.munericLength = munericLength;
	}
	
}
