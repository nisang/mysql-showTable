package io.dandan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.dandan.entity.DataElementConfig;
import io.dandan.entity.DatabaseInfo;
import io.dandan.entity.FieldRecord;
import io.dandan.entity.TableInfo;
import io.dandan.entity.TableRecord;

/**
 * DE数据插入工具
 * 
 * @author Hongten
 * @mail hongtenzone@foxmail.com
 * @create 2013-8-3
 */
public class DataElementConfigTool {
	/**
	 * 初始化数据库链接的相关
	 * 
	 * @param dataBaseBO
	 *            数据库配置
	 */
	public DataElementConfigTool(DatabaseInfo dataBaseBO) {
		dataBaseBO.setIp(dataBaseBO.getIp() == null ? "127.0.0.1" : dataBaseBO.getIp());
		dataBaseBO.setPort(dataBaseBO.getPort() == null ? "3306" : dataBaseBO.getPort());
		dataBaseBO.setUrl("jdbc:mysql://" + dataBaseBO.getIp() + ":" + dataBaseBO.getPort() + "/" + dataBaseBO.getDbName()+"?useUnicode=true&characterEncoding=utf-8&useSSL=false");
	}
	
	

	public static List<TableRecord> dao(){
		DatabaseInfo dataBaseBO = new DatabaseInfo();
		dataBaseBO.setDriver("com.mysql.jdbc.Driver");
		dataBaseBO.setDbName("chesource");
		dataBaseBO.setUserName("root");
		dataBaseBO.setPasswrod("nidandan");
		dataBaseBO.setSql("show tables");
		//获得表信息
		
		DataElementConfigTool tool = new DataElementConfigTool(dataBaseBO);
		List<String> tables = tool.showTables(dataBaseBO);
		List<TableRecord> records = new ArrayList<TableRecord>();
		String tablesql = "( ";
		for(int i =0 ; i<tables.size();i++){
			if(i == tables.size()-1){
				tablesql +="'"+tables.get(i)+"'";
			}else{
				tablesql +="'"+tables.get(i)+"'"+",";
			}
		}
		tablesql +=" )";
		for(int i =0; i<tables.size();i++){
			String table = tables.get(i);
			TableRecord tab = new TableRecord();
			tab.setTableName(table);
			tab.setSort(i);
			dataBaseBO.setTableName("'"+table+"'");
			dataBaseBO.setSql(dataBaseBO.getSelectSQL() + dataBaseBO.getTableName());
			
			tool = new DataElementConfigTool(dataBaseBO);
			List<TableInfo> list = tool.getTableDescBOList(dataBaseBO);
			
			List<FieldRecord> temp = new ArrayList<FieldRecord>();
			if (list != null) {
				for (TableInfo bo : list) {
					FieldRecord fie = new FieldRecord();
					fie.setField(bo.getField());
					fie.setLength(bo.getCharacterLength());
					fie.setIsNull(bo.getIsNullable());
					fie.setNote(bo.getMemo());
					fie.setType(bo.getType());
					fie.setMunericLength(bo.getMunericLength());
					fie.setNumericScale(bo.getNumericScale());
					tab.setTableComment(bo.getTableComment());
					temp.add(fie);
				}
			}
			tab.setFields(temp);
			records.add(tab);
		}
		return records;
	}

	/**
	 * 数据库表结构情况
	 * 
	 * @param dataBaseBO
	 *            数据库配置信
	 * @return 查询的数据表的字段
	 */
	public List<TableInfo> getTableDescBOList(DatabaseInfo dataBaseBO) {
		List<TableInfo> list = new ArrayList<TableInfo>();
		TableInfo tableDescBO = null;
		try {
			Class.forName(dataBaseBO.getDriver());
			Connection conn = DriverManager.getConnection(dataBaseBO.getUrl(), dataBaseBO.getUserName(), dataBaseBO.getPasswrod());
			PreparedStatement ps = conn.prepareStatement(dataBaseBO.getSql());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tableDescBO = new TableInfo();
				tableDescBO.setField(rs.getString(1));
				tableDescBO.setType(rs.getString(2));
				tableDescBO.setMemo(rs.getString(3));
				tableDescBO.setMunericLength(rs.getString(4));
				tableDescBO.setNumericScale(rs.getString(5));
				tableDescBO.setIsNullable(rs.getString(6));
				tableDescBO.setExtra(rs.getString(7));
				tableDescBO.setIsDefault(rs.getString(8));
				tableDescBO.setCharacterLength(rs.getString(9));
				//获得表的comment信息
				tableDescBO.setTableComment(rs.getString(10));
				list.add(tableDescBO);
			}
			close(rs, ps, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<String> showTables(DatabaseInfo dataBaseBO) {
		List<String> tables = new ArrayList<String>();
		try {
			Class.forName(dataBaseBO.getDriver());
			Connection conn = DriverManager.getConnection(dataBaseBO.getUrl(), dataBaseBO.getUserName(), dataBaseBO.getPasswrod());
			PreparedStatement ps = conn.prepareStatement(dataBaseBO.getSql());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				//System.out.println(rs.getString(1).toString());
				tables.add(rs.getString(1).toString());
			}
			close(rs, ps, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tables;
	}

	/**
	 * 执行向数据库:<code>data_element_config</code>中插入数
	 * 
	 * @param dataBaseBO
	 *            数据库配置信
	 * @param decBo
	 *            data_element_config这张表的BO
	 * @return 返回:<code>-1</code>, 表示插入数据失败，否则成
	 */
	public int insertIntoDECTable(DatabaseInfo dataBaseBO, DataElementConfig decBo) {
		int result = -1;
		if (decBo != null) {
			String sql = decBo.getInsertIntoSQL() + decBo.getDeName() + "," + decBo.getDeGroup() + "," + decBo.getMemo() + "," + decBo.getDataType() + "," + decBo.getValueCheck() + "," + decBo.getYxBj() + ")";
			try {
				Class.forName(dataBaseBO.getDriver());
				Connection conn = DriverManager.getConnection(dataBaseBO.getUrl(), dataBaseBO.getUserName(), dataBaseBO.getPasswrod());
				PreparedStatement ps = conn.prepareStatement(sql);
				result = ps.executeUpdate();
				close(null, ps, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 去除括号，如"int(11)",去除括号了以后，为："int"
	 * 
	 * @param oldType
	 * @return
	 */
	public static String getType(String oldType) {
		if (oldType != null && !oldType.equals("")) {
			return oldType.substring(0, oldType.indexOf("("));
		}
		return null;
	}

	/**
	 * 对数据库表描述进行封装成DataElementConfigBO对象
	 * 
	 * @param tableDescBO
	 *            数据库表的描
	 * @param group
	 *            字段的分组名称，在表<code>data_element_config</code>中对应的
	 *            <code>de_group</code>字段
	 * @return dataElementConfig对象的一个实
	 */
	public DataElementConfig getDataElementConfigBO(TableInfo tableDescBO, String group) {
		DataElementConfig bo = null;
		if (tableDescBO != null) {
			bo = new DataElementConfig();
			bo.setDeName("'" + tableDescBO.getField() + "'");
			bo.setDeGroup("'" + group + "'");
			bo.setValueCheck("'true'");
			bo.setYxBj("'1'");
			bo.setMemo("'" + tableDescBO.getMemo() + "'");
			bo.setDataType(1);
		}
		return bo;
	}

	/**
	 * 关闭数据库的相关链接
	 * 
	 * @param rs
	 *            记录
	 * @param ps
	 *            声明
	 * @param conn
	 *            链接对象
	 */
	public void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		// 关闭记录
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 关闭声明
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 关闭链接对象
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		// 设置数据库链接
		dao();
	}
}
