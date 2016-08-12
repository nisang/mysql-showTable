/**
 * 
 */
package io.dandan.entity;

import java.io.Serializable;

/**
 * 数据库表结构情况BO
 * 
 * @author Hongten
 * @mail hongtenzone@foxmail.com
 * @create 2013-8-3
 * 
 * @editor nisang
 * @editdate 2016-08-08
 * 新增 tableComment 字段信息
 */
public class TableInfo implements Serializable {
	private static final long serialVersionUID = 6450523501528806316L;
	/**
	 * 数据库表中对应的字段名称
	 */
	private String field;
	/**
	 * 数据库表中对应字段的类型
	 */
	private String type;
	/**
	 * 数据库表中字段是否为空：YES/NO
	 */
	private String isNullable;
	/**
	 * 是否为主键：KEY，不是，则为空，null
	 */
	private String key;
	/**
	 * 字段的默认
	 */
	private String isDefault;
	/**
	 * 额外的属性，如：auto_increment
	 */
	private String extra;
	/**
	 * 小数位数
	 */
	private String numericScale;
	/**
	 * 数字长度
	 */
	private String munericLength;

	/**
	 * 字符长度
	 */
	private String characterLength;
	/**
	 * 备注
	 */
	private String memo;
	
	/** 表的注释 */
	private String tableComment;

	/**
	 * 重写toStirng方法 主要是为了控制台输出
	 */
	public String toString() {
		return " " + field + "   " + type + "    " + isNullable + "        " + key + "        " + isDefault + "      " + extra + "            "+ memo+"    " + tableComment;
	}

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

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
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

	public String getCharacterLength() {
		return characterLength;
	}

	public void setCharacterLength(String characterLength) {
		this.characterLength = characterLength;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

}
