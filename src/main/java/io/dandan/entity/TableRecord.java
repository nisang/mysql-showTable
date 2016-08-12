package io.dandan.entity;

import java.util.List;

public class TableRecord {
	
	private String tableName;
	private int sort;
	private List<FieldRecord> fields;
	private String tableComment;
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<FieldRecord> getFields() {
		return fields;
	}

	public void setFields(List<FieldRecord> fields) {
		this.fields = fields;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	
}
