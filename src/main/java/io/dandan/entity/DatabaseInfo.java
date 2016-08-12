package io.dandan.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 数据库配置
 * @author Hongten
 * @mail hongtenzone@foxmail.com
 * @create 2013-8-3
 * 
 * SELECT
  (@rowNO := @rowNo+1) AS rowno,
	col.column_name AS field,
	col.data_type AS type,
	col.column_comment AS memo,
	col.numeric_precision AS munericLength,
	col.numeric_scale AS numericScale,
	col.is_nullable AS isNullable,
	CASE
WHEN extra = 'auto_increment' THEN
	1
ELSE
	0
END AS extra,
 col.column_default AS isDefault,
 col.character_maximum_length AS characterLength,
 tab.table_comment AS tableComment
FROM
	Information_schema. COLUMNS col
LEFT JOIN Information_schema. TABLES tab ON col.TABLE_NAME = tab.TABLE_NAME,(select @rowNO :=0) b
WHERE
	col.TABLE_NAME in
 *  * 修改 nisang
 * @edit 2016-08-13
 */
public class DatabaseInfo implements Serializable {
	private static final long serialVersionUID = 171777003280248377L;
	private final String SELECT_SQL_FIELD = " col.column_name as field,";
	private final String SELECT_SQL_TYPE = " col.data_type as type,";
	private final String SELECT_SQL_MEMO = " col.column_comment as memo,";
	private final String SELECT_SQL_MUNERIC_LENGTH = " col.numeric_precision as munericLength,";
	private final String SELECT_SQL_NUMERIC_SCALE = " col.numeric_scale as numericScale, ";
	private final String SELECT_SQL_ISNULLABLE = " col.is_nullable as isNullable,";
	private final String SELECT_SQL_EXTRA = " CASE WHEN extra = 'auto_increment' THEN 1 ELSE 0 END as extra,";
	private final String SELECT_SQL_ISDEFAULT = " col.column_default as isDefault,";
	private final String SELECT_SQL_CHARACTER_LENGTH = " col.character_maximum_length  AS characterLength, ";
	private final String SELECT_SQL_TABLE_COMMENT = " tab.table_comment as tableComment ";
	/**
	 * 查询表结构sql
	 */
	private String selectSQL = "SELECT " + SELECT_SQL_FIELD + SELECT_SQL_TYPE + SELECT_SQL_MEMO + SELECT_SQL_MUNERIC_LENGTH + SELECT_SQL_NUMERIC_SCALE + SELECT_SQL_ISNULLABLE + SELECT_SQL_EXTRA + SELECT_SQL_ISDEFAULT + SELECT_SQL_CHARACTER_LENGTH + SELECT_SQL_TABLE_COMMENT +" FROM Information_schema.columns col "+
	 " left join Information_schema.tables tab on col.TABLE_NAME=tab.TABLE_NAME,(select @rowNO :=0) b"+
	 " WHERE  col.TABLE_NAME = ";//WHERE  col.TABLE_NAME =
	
	
	/**
	 * 驱动名称
	 */
	private String driver;
	/**
	 * 数据库名
	 */
	private String dbName;
	/**
	 * 数据库密码
	 */
	private String passwrod;
	/**
	 * 数据库用户名
	 */
	private String userName;
	/**
	 * 访问数据库的url
	 */
	private String url;
	/**
	 * 端口�?
	 */
	private String port;
	/**
	 * ip地址
	 */
	private String ip;
	/**
	 * 数据类型：mysql,oracle等等
	 */
	private String dbType;

	/**
	 * 根据sql:show tables;查询出的数据库表名称
	 */
	private List<String> tables;
	/**
	 * 数据库表名称
	 */
	private String tableName;
	/**
	 * sql语句
	 */
	private String sql;

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getPasswrod() {
		return passwrod;
	}

	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getTables() {
		return tables;
	}

	public void setTables(List<String> tables) {
		this.tables = tables;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSelectSQL() {
		return selectSQL;
	}

	public void setSelectSQL(String selectSQL) {
		this.selectSQL = selectSQL;
	}

}
