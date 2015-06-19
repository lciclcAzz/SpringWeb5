package com.idc.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;

public class GennerateData {
//	private final static String path = "C://EGP3_CGD_0056_55//PreparedFac//PreparedFac//src//";
//	private final static String jcc_driver = "com.ibm.db2.jcc.DB2Driver";
//	private final static String jdbc_url = "jdbc:db2://10.11.3.104:50088/EGPDB";
	private final static String path = "D:\\GitHub\\workspace\\SpringWeb5\\SpringWeb5\\src\\";
	private final static String jcc_driver = "com.mysql.jdbc.Driver";
	private final static String jdbc_url = "jdbc:mysql://localhost:3306/db_merchant";	
	private final static String user = "root";
	private final static String password = "asdf";
	private final static String compShortName="idc";
	private final static String projShortName="spr";
	private final static String compUpperCase = compShortName.substring(0,1).toUpperCase()+compShortName.substring(1);
	private final static String projUpperCase = projShortName.substring(0,1).toUpperCase()+projShortName.substring(1);
	
	public static void main(String[] args) {
//		Gen From Table 
		new GennerateData().genDataTable("","BRAND_PRODUCT");
		
		
//		Gen From schema
//		new GennerateData().genDataSchema("EGPAGENCY");

	}
	
	private Connection getConnection() throws MissingResourceException, ClassNotFoundException, SQLException {
		Connection connection = null;
		Class.forName(jcc_driver);
		connection = DriverManager.getConnection(jdbc_url,user,password);
		connection.setAutoCommit(true);
		return connection;
	}
	
	private void genDataSchema(String schemaName) {
		List<Map<String, String>> list = new LinkedList<Map<String,String>>();
		Connection conn = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			// get colum
			rs = conn.getMetaData().getTables(conn.getCatalog(), schemaName, null, null);
		    while (rs.next()) {
		    	genDataTable(schemaName, rs.getString("TABLE_NAME"));
		    }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (!rs.isClosed()) rs.close();
				if (!conn.isClosed()) conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void genDataTable(String schemaName, String tableName) {
		System.out.println("Gen Table " + tableName.toUpperCase());
		List<Map<String, String>> list = new LinkedList<Map<String,String>>();
		Connection conn = null;
		ResultSet rs = null;
		
		try{
			conn = conDB.getConnection();
			// get colum
				rs = conn.getMetaData().getColumns(conn.getCatalog(), schemaName.toUpperCase(), tableName.toUpperCase(), null);
		    while (rs.next()) {
//		    	System.out.println(rs.getString("COLUMN_NAME") +":"+ rs.getString("DATA_TYPE")+":"+ rs.getString("TYPE_NAME"));
		    	Map<String, String> mapdata = new HashMap<String, String>();
		    	mapdata.put("COLUMN_NAME",rs.getString("COLUMN_NAME"));
		    	mapdata.put("DATA_TYPE",rs.getString("DATA_TYPE"));
		    	mapdata.put("TYPE_NAME",rs.getString("TYPE_NAME"));
		    	list.add(mapdata);
		    }
		    
			// get pk
//			rs = conn.getMetaData().getPrimaryKeys(conn.getCatalog(), schemaName.toUpperCase(), tableName.toUpperCase());
//			while(rs.next()){
//				System.out.println("PK:"+rs.getString("COLUMN_NAME"));
//				System.out.println("PK SEQ:"+rs.getString("KEY_SEQ"));
//			}
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (rs != null) rs.close();
				if (!conn.isClosed()) conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
//		factory();
		dto(schemaName,tableName, list);
		dao(schemaName, tableName, list);
	}
	
	private static void factory(){
		StringBuffer strb = new StringBuffer("package com."+compShortName+projShortName+".factory;\n");
		strb.append("import java.sql.Connection;\n");
		strb.append("import java.sql.SQLException;\n");
		strb.append("import java.util.List;\n");
		strb.append("\n");
		strb.append("public interface "+compUpperCase+"Factory {\n");
		strb.append("	\n");
		strb.append("	/**\n");
		strb.append("	 * @param Connection conn\n");
		strb.append("	 * @param Object dto\n");
		strb.append("	 * @return List<Object>\n");
		strb.append("	 * @throws SQLException\n");
		strb.append("	 * @throws Exception\n");
		strb.append("	 */\n");
		strb.append("	public List<Object> select(Connection conn, Object dto) throws SQLException,Exception;\n");
		strb.append("	\n");
		strb.append("	/**\n");
		strb.append("	 * @param Connection conn\n");
		strb.append("	 * @param Object dto\n");
		strb.append("	 * @param String orderby\n");
		strb.append("	 * @return List<Object>\n");
		strb.append("	 * @throws SQLException\n");
		strb.append("	 * @throws Exception\n");
		strb.append("	 */\n");
		strb.append("	public List<Object> select(Connection conn, Object dto, String orderby) throws SQLException,Exception;\n");
		strb.append("	\n");
		strb.append("	/**\n");
		strb.append("	 * @param Connection conn\n");
		strb.append("	 * @param String where\n");
		strb.append("	 * @param List<Object> value\n");
		strb.append("	 * @return List<Object>\n");
		strb.append("	 * @throws SQLException\n");
		strb.append("	 * @throws Exception\n");
		strb.append("	 */\n");
		strb.append("	public List<Object> selectByWhere(Connection conn, String where, List<Object> value) throws SQLException,Exception;\n");
		strb.append("	\n");
		strb.append("	/**\n");
		strb.append("	 * @param Connection conn\n");
		strb.append("	 * @param String column\n");
		strb.append("	 * @param String where\n");
		strb.append("	 * @param List<Object> value\n");
		strb.append("	 * @return List<Object>\n");
		strb.append("	 * @throws SQLException\n");
		strb.append("	 * @throws Exception\n");
		strb.append("	 */\n");
		strb.append("	public List<Object> selectByWhere(Connection conn, String column, String where, List<Object> value) throws SQLException,Exception;\n");
		strb.append("	\n");
		strb.append("	/**\n");
		strb.append("	 * @param Connection conn\n");
		strb.append("	 * @param Object dto\n");
		strb.append("	 * @return List<Object>\n");
		strb.append("	 * @throws SQLException\n");
		strb.append("	 * @throws Exception\n");
		strb.append("	 */\n");
		strb.append("	public List<Object> select(Object dto) throws SQLException,Exception;\n");
		strb.append("	\n");
		strb.append("	/**\n");
		strb.append("	 * @param Connection conn\n");
		strb.append("	 * @param Object dto\n");
		strb.append("	 * @param String orderby\n");
		strb.append("	 * @return List<Object>\n");
		strb.append("	 * @throws SQLException\n");
		strb.append("	 * @throws Exception\n");
		strb.append("	 */\n");
		strb.append("	public List<Object> select(Object dto, String orderby) throws SQLException,Exception;\n");
		strb.append("	\n");
		strb.append("	/**\n");
		strb.append("	 * @param Connection conn\n");
		strb.append("	 * @param String where\n");
		strb.append("	 * @param List<Object> value\n");
		strb.append("	 * @return List<Object>\n");
		strb.append("	 * @throws SQLException\n");
		strb.append("	 * @throws Exception\n");
		strb.append("	 */\n");
		strb.append("	public List<Object> selectByWhere(String where, List<Object> value) throws SQLException,Exception;\n");
		strb.append("	\n");
		strb.append("	/**\n");
		strb.append("	 * @param Connection conn\n");
		strb.append("	 * @param String column\n");
		strb.append("	 * @param String where\n");
		strb.append("	 * @param List<Object> value\n");
		strb.append("	 * @return List<Object>\n");
		strb.append("	 * @throws SQLException\n");
		strb.append("	 * @throws Exception\n");
		strb.append("	 */\n");
		strb.append("	public List<Object> selectByWhere(String column, String where, List<Object> value) throws SQLException,Exception;\n");
		strb.append("	/**\n");
		strb.append("	 * @param Connection conn\n");
		strb.append("	 * @param Object dto\n");
		strb.append("	 * @return int\n");
		strb.append("	 * @throws SQLException\n");
		strb.append("	 * @throws Exception\n");
		strb.append("	 */\n");
		strb.append("	public int insert(Connection conn, Object dto) throws SQLException,Exception;\n");
		strb.append("	\n");
		strb.append("	/**\n");
		strb.append("	 * @param Connection conn\n");
		strb.append("	 * @param Object dtoColum\n");
		strb.append("	 * @param Object dtoWhere\n");
		strb.append("	 * @return int\n");
		strb.append("	 * @throws SQLException\n");
		strb.append("	 * @throws Exception\n");
		strb.append("	 */\n");
		strb.append("	public int update(Connection conn, Object dtoColumn, Object dtoWhere) throws SQLException,Exception;\n");
		strb.append("	\n");
		strb.append("	/**\n");
		strb.append("	 * @param Connection conn\n");
		strb.append("	 * @param Object dtoWhere\n");
		strb.append("	 * @return int\n");
		strb.append("	 * @throws SQLException\n");
		strb.append("	 * @throws Exception\n");
		strb.append("	 */\n");
		strb.append("	public int delete(Connection conn, Object dtoWhere) throws SQLException,Exception;\n");
		strb.append("}\n");

		boolean success = writeFile(path+"com//"+compShortName+"//"+projShortName+"//factory", compUpperCase+"Factory.java", strb.toString());
		strb = null;
		System.out.println("success "+success);
	}
	
	private static void dto(String schemaName, String tableName, List<Map<String,String>> list){
		StringBuffer strb = new StringBuffer("package com."+compShortName+projShortName+".dto."+packageName(schemaName)+"."+packageName(tableName)+";\n");
		strb.append("\n");
		strb.append("import java.io.Serializable;\n");
		strb.append("\n");
		strb.append("public class "+className(tableName)+"Dto implements Serializable {\n");
		strb.append("	private static final long serialVersionUID = 1L;\n");
		strb.append("	\n");
		StringBuffer strb_valiable = new StringBuffer("");
		StringBuffer strb_method = new StringBuffer("");
		if(list != null && list.size() > 0){
			for(Map<String, String> mapdata : list){
				strb_valiable.append("	private "+variableType(Integer.parseInt(mapdata.get("DATA_TYPE")))+" "+variableName(mapdata.get("COLUMN_NAME"))+" = "+variableTypeValueDefualt(Integer.parseInt(mapdata.get("DATA_TYPE")))+";\n");
				strb_valiable.append("	public boolean _"+variableName(mapdata.get("COLUMN_NAME"))+" = false;\n");
				strb_valiable.append("	\n");
				
				strb_method.append("	/**\n");
				strb_method.append(" 	 * @return "+variableType(Integer.parseInt(mapdata.get("DATA_TYPE")))+"\n");
				strb_method.append("	 */\n");
				strb_method.append("	public "+variableType(Integer.parseInt(mapdata.get("DATA_TYPE")))+" get"+methodName(mapdata.get("COLUMN_NAME"))+"() {\n");
				strb_method.append("		return "+variableName(mapdata.get("COLUMN_NAME"))+";\n");
				strb_method.append("	}\n");
				strb_method.append("	\n");
				strb_method.append("	/**\n");
				strb_method.append("	 * @param "+variableName(mapdata.get("COLUMN_NAME"))+"\n");
				strb_method.append("	 */\n");
				strb_method.append("	public void set"+methodName(mapdata.get("COLUMN_NAME"))+"("+variableType(Integer.parseInt(mapdata.get("DATA_TYPE")))+" "+variableName(mapdata.get("COLUMN_NAME"))+") {\n");
				strb_method.append("		this."+variableName(mapdata.get("COLUMN_NAME"))+" = "+variableName(mapdata.get("COLUMN_NAME"))+";\n");
				strb_method.append("		this._"+variableName(mapdata.get("COLUMN_NAME"))+" = true;\n");
				strb_method.append("	}\n");
			}
		}
		
		strb.append(strb_valiable);
		strb.append(strb_method);
		
		strb.append("}\n");
		
		boolean success = writeFile(path+"com//"+compShortName+"//"+projShortName+"//dto//"+packageName(schemaName)+"//"+packageName(tableName), className(tableName)+"Dto.java", strb.toString());
		strb = null;
		System.out.println("success "+success);
	}
	
	private static void dao(String schemaName, String tableName, List<Map<String,String>> list){
		/********************************************************************************/
		StringBuffer strb_sel_column = new StringBuffer("");
		StringBuffer strb_where = new StringBuffer("");
		StringBuffer strb_pstm_col = new StringBuffer("");
		StringBuffer strb_pstm_whe = new StringBuffer("");
		StringBuffer strb_set_dto = new StringBuffer("");
		StringBuffer strb_ints = new StringBuffer("");
		StringBuffer strb_ints_val = new StringBuffer("");
		StringBuffer strb_update = new StringBuffer("");
		StringBuffer fieldColum = new StringBuffer();
		int i = 0;
		for(Map<String,String> mapdata: list){
			i++;
			strb_sel_column.append("		if(dtoColum._"+variableName(mapdata.get("COLUMN_NAME"))+"){\n");
			strb_sel_column.append("			if(chk_comma){\n");
			strb_sel_column.append("				strbSql.append(\", \");\n");
			strb_sel_column.append("			}\n");
			strb_sel_column.append("			strbSql.append(\""+mapdata.get("COLUMN_NAME").toUpperCase()+"\");\n");
			strb_sel_column.append("			chk_comma = true;\n");
			strb_sel_column.append("		}\n");
			strb_sel_column.append("		\n");
			
			strb_where.append("		if(dtoWhere._"+variableName(mapdata.get("COLUMN_NAME"))+"){\n");
			strb_where.append("			if(chk_and){\n");
			strb_where.append("				strbSql.append(\" AND \");\n");
			strb_where.append("			}\n");
			strb_where.append("			strbSql.append(\""+mapdata.get("COLUMN_NAME").toUpperCase()+" = ?\");\n");
			strb_where.append("			chk_and = true;\n");
			strb_where.append("		}\n");
			strb_where.append("		\n");
			
			strb_set_dto.append("				if(rsmd.getColumnName(i).toUpperCase().equals(\""+mapdata.get("COLUMN_NAME").toUpperCase()+"\")){\n");
			strb_set_dto.append("					dto.set"+methodName(mapdata.get("COLUMN_NAME"))+"(rs.get"+getDataType(Integer.parseInt(mapdata.get("DATA_TYPE")))+"(i));\n");
			strb_set_dto.append("				}\n");
			
			strb_ints.append("			if(chk_comma){\n");
			strb_ints.append("				strbSql.append(\", \");\n");
			strb_ints.append("			}\n");
			strb_ints.append("			strbSql.append(\"?\");\n");
			strb_ints.append("			chk_comma = true;\n");
			strb_ints.append("			\n");
			
			strb_ints_val.append("			pstmt.set"+getDataType(Integer.parseInt(mapdata.get("DATA_TYPE")))+"(index_p++, dto.get"+methodName(mapdata.get("COLUMN_NAME"))+"());\n");
			
			strb_pstm_col.append("			if(dtoColum._"+variableName(mapdata.get("COLUMN_NAME"))+"){\n");
			strb_pstm_col.append("				pstmt.set"+getDataType(Integer.parseInt(mapdata.get("DATA_TYPE")))+"(index_p++, dtoColum.get"+methodName(mapdata.get("COLUMN_NAME"))+"());\n");
			strb_pstm_col.append("			}\n");
			strb_pstm_col.append("			\n");
			
			strb_pstm_whe.append("			if(dtoWhere._"+variableName(mapdata.get("COLUMN_NAME"))+"){\n");
			strb_pstm_whe.append("				pstmt.set"+getDataType(Integer.parseInt(mapdata.get("DATA_TYPE")))+"(index_p++, dtoWhere.get"+methodName(mapdata.get("COLUMN_NAME"))+"());\n");
			strb_pstm_whe.append("			}\n");
			strb_pstm_whe.append("			\n");
			
			strb_update.append("			if(dtoColum._"+variableName(mapdata.get("COLUMN_NAME"))+"){\n");
			strb_update.append("				if(chk_comma){\n");
			strb_update.append("					strbSql.append(\", \");\n");
			strb_update.append("				}\n");
			strb_update.append("				strbSql.append(\""+mapdata.get("COLUMN_NAME").toUpperCase()+" = ?\");\n");
			strb_update.append("				chk_comma = true;\n");
			strb_update.append("			}\n");
			strb_update.append("			\n");
			
			if(i == list.size()){
				fieldColum.append(mapdata.get("COLUMN_NAME").toUpperCase());
			}else if(list.size()>1){
				fieldColum.append(mapdata.get("COLUMN_NAME").toUpperCase()+", ");
			}else{
				fieldColum.append(mapdata.get("COLUMN_NAME").toUpperCase());
			}
		}
		/********************************************************************************/
		
		StringBuffer strb = new StringBuffer("package com."+compShortName+projShortName+".dao."+packageName(schemaName)+"."+packageName(tableName)+";\n");
		strb.append("\n");
		strb.append("import java.io.Serializable;\n");
		strb.append("import java.sql.Connection;\n");
		strb.append("import java.sql.PreparedStatement;\n");
		strb.append("import java.sql.ResultSet;\n");
		strb.append("import java.sql.ResultSetMetaData;\n");
		strb.append("import java.sql.SQLException;\n");
		strb.append("import java.util.LinkedList;\n");
		strb.append("import java.util.List;\n");
		strb.append("\n");
		strb.append("import com."+compShortName+projShortName+".dto."+packageName(schemaName)+"."+packageName(tableName)+"."+className(tableName)+"Dto;\n");
		strb.append("import com."+compShortName+projShortName+".factory."+projShortName+"Factory;\n");
		strb.append("import com."+compShortName+projShortName+".resource."+projShortName+"DsConnection;\n");
		strb.append("import com."+compShortName+projShortName+".resource."+projShortName+"Schema;\n");
		strb.append("\n");
		strb.append("public class "+className(tableName)+"Dao implements "+projShortName+"Factory, Serializable {\n");
		strb.append("	private static final long serialVersionUID = 1L;\n");
		strb.append("	private final String schema = "+projShortName+"Schema.SCHEMA_"+schemaName.toUpperCase()+";\n");
		strb.append("	private final String table = \""+tableName.toUpperCase()+"\";\n");
		
		strb.append("	private List<Object> executeQuery(Connection conn, String column, String where, List<Object> value)\n");
		strb.append("			throws SQLException,Exception {\n");
		strb.append("		List<Object> list = null;\n");
		strb.append("		list = new LinkedList<Object>();\n");
		strb.append("		StringBuffer strbSql = new StringBuffer(\"SELECT \");\n");
		strb.append("		\n");
		strb.append("		if(column != null && !column.equals(\"\")){\n");
		strb.append("			strbSql.append(column);\n");
		strb.append("		}else{\n");
		strb.append("			strbSql.append(\""+fieldColum+" \");\n");
		strb.append("		}\n");
		strb.append("		\n");
		strb.append("		strbSql.append(\" FROM \");\n");
		strb.append("		strbSql.append(this.schema);\n");
		strb.append("		strbSql.append(\".\");\n");
		strb.append("		strbSql.append(this.table);\n");
		strb.append("		strbSql.append(\" \");\n");
		strb.append("		\n");
		strb.append("		if(where != null && !where.equals(\"\")){\n");
		strb.append("			strbSql.append(where);\n");
		strb.append("		}\n");
		strb.append("		\n");
		strb.append("		PreparedStatement pstmt = null;\n");
		strb.append("		ResultSet rs = null;\n");
		strb.append("		ResultSetMetaData rsmd = null;\n");
		strb.append("		\n");
		strb.append("		pstmt = conn.prepareStatement(strbSql.toString());\n");
		strb.append("		\n");
		strb.append("		int index_p = 1;\n");
		strb.append("		\n");
		strb.append("		if(value != null && value.size()>0){\n");
		strb.append("			for(Object obj : value){\n");
		strb.append("				pstmt.setObject(index_p++, obj);\n");
		strb.append("			}\n");
		strb.append("		}\n");
		strb.append("		\n");
		strb.append("		rs = pstmt.executeQuery();\n");
		strb.append("		rsmd = rs.getMetaData();\n");
		strb.append("		\n");
		strb.append("		while(rs.next()){\n");
		strb.append("			"+className(tableName)+"Dto dto = new "+className(tableName)+"Dto();\n");
		strb.append("			for(int i = 1;i <= rsmd.getColumnCount();i++){\n");
		
		strb.append(strb_set_dto);
		
		strb.append("			}\n");
		strb.append("			list.add(dto);\n");
		strb.append("		}\n");
		strb.append("		\n");
		strb.append("		strbSql = null;\n");
		strb.append("		\n");
		strb.append("		if (rs != null) rs.close();\n");
		strb.append("		if (pstmt != null) pstmt.close();\n");
		strb.append("		return list;\n");
		strb.append("	}\n");
		
		strb.append("	\n");
		strb.append("	/**\n");
		strb.append("	 * @param Connection conn\n");
		strb.append("	 * @param "+className(tableName)+"Dto dtoColum\n");
		strb.append("	 * @param "+className(tableName)+"Dto dtoWhere\n");
		strb.append("	 * @return List<Object>\n");
		strb.append("	 * @throws SQLException\n");
		strb.append("	 * @throws Exception\n");
		strb.append("	 */\n");
		strb.append("	private List<Object> executeQuery(Connection conn, "+className(tableName)+"Dto dtoColum, "+className(tableName)+"Dto dtoWhere, String orderby)\n");
		strb.append("			throws SQLException,Exception {\n");
		strb.append("		List<Object> list = null;\n");
		strb.append("		list = new LinkedList<Object>();\n");
		strb.append("		boolean chk_and = false;\n");
		strb.append("		boolean chk_comma = false;\n");
		strb.append("		StringBuffer strbSql = new StringBuffer(\"SELECT \");\n");
		strb.append("		\n");
		strb.append("		if(dtoColum != null){\n");
		
		strb.append(strb_sel_column);
		
		strb.append("		}\n");
		strb.append("		\n");
		strb.append("		if(!chk_comma){\n");
		strb.append("			strbSql.append(\""+fieldColum+" \");\n");
		strb.append("		}\n");
		strb.append("		\n");
		strb.append("		strbSql.append(\" FROM \");\n");
		strb.append("		strbSql.append(this.schema);\n");
		strb.append("		strbSql.append(\".\");\n");
		strb.append("		strbSql.append(this.table);\n");
		strb.append("		strbSql.append(\" WHERE \");\n");
		strb.append("		\n");
		strb.append("		if(dtoWhere != null){\n");
		
		strb.append(strb_where);
		
		strb.append("		}\n");
		strb.append("		\n");
		strb.append("		if(orderby != null){\n");
		strb.append("			strbSql.append(\" \");\n");
		strb.append("			strbSql.append(orderby);\n");
		strb.append("		}\n");
		strb.append("		\n");
		strb.append("		PreparedStatement pstmt = null;\n");
		strb.append("		ResultSet rs = null;\n");
		strb.append("		ResultSetMetaData rsmd = null;\n");
		strb.append("		\n");
		strb.append("		pstmt = conn.prepareStatement(strbSql.toString());\n");
		strb.append("		\n");
		strb.append("		int index_p = 1;\n");
		
		strb.append(strb_pstm_whe);
		
		strb.append("		rs = pstmt.executeQuery();\n");
		strb.append("		rsmd = rs.getMetaData();\n");
		strb.append("		\n");
		strb.append("		while(rs.next()){\n");
		strb.append("			"+className(tableName)+"Dto dto = new "+className(tableName)+"Dto();\n");
		strb.append("			for(int i = 1;i <= rsmd.getColumnCount();i++){\n");
		
		strb.append(strb_set_dto);
		
		strb.append("			}\n");
		strb.append("			list.add(dto);\n");
		strb.append("		}\n");
		strb.append("		\n");
		strb.append("		strbSql = null;\n");
		strb.append("		\n");
		strb.append("		if (rs != null) rs.close();\n");
		strb.append("		if (pstmt != null) pstmt.close();\n");
		strb.append("		return list;\n");
		strb.append("	}\n");
		
		strb.append("	\n");
		strb.append("	/**\n");
		strb.append("	 * @param Connection conn\n");
		strb.append("	 * @param "+className(tableName)+"Dto dto\n");
		strb.append("	 * @return int\n");
		strb.append("	 * @throws SQLException\n");
		strb.append("	 * @throws Exception\n");
		strb.append("	 */\n");
		strb.append("	private int executeInsert(Connection conn, "+className(tableName)+"Dto dto)\n");
		strb.append("			throws SQLException,Exception {\n");
		strb.append("		if(dto != null){\n");
		strb.append("			boolean chk_comma = false;\n");
		strb.append("			StringBuffer strbSql = new StringBuffer(\"INSERT INTO \");\n");
		strb.append("			strbSql.append(this.schema);\n");
		strb.append("			strbSql.append(\".\");\n");
		strb.append("			strbSql.append(this.table);\n");
		strb.append("			strbSql.append(\" ( "+fieldColum+" ) VALUES ( \");\n");
		strb.append("			\n");
		
		strb.append(strb_ints);
		
		strb.append("			strbSql.append(\" )\");\n");
		strb.append("			\n");
		strb.append("			PreparedStatement pstmt = null;\n");
		strb.append("			pstmt = conn.prepareStatement(strbSql.toString());\n");
		strb.append("			\n");
		strb.append("			int index_p = 1;\n");
		
		strb.append(strb_ints_val);
		
		strb.append("			\n");
		strb.append("			strbSql = null;\n");
		strb.append("			\n");
		strb.append("			return pstmt.executeUpdate();\n");
		strb.append("		}else{\n");
		strb.append("			return 0;\n");
		strb.append("		}\n");
		strb.append("	}\n");
		
		strb.append("	\n");
		strb.append("	/**\n");
		strb.append("	 * @param Connection conn\n");
		strb.append("	 * @param "+className(tableName)+"Dto dtoColum\n");
		strb.append("	 * @param "+className(tableName)+"Dto dtoWhere\n");
		strb.append("	 * @return int\n");
		strb.append("	 * @throws SQLException\n");
		strb.append("	 * @throws Exception\n");
		strb.append("	 */\n");
		strb.append("	private int executeUpdate(Connection conn, "+className(tableName)+"Dto dtoColum, "+className(tableName)+"Dto dtoWhere)\n");
		strb.append("			throws SQLException,Exception {\n");
		strb.append("		if(dtoColum != null && dtoWhere != null){\n");
		strb.append("			boolean chk_comma = false;\n");
		strb.append("			boolean chk_and = false;\n");
		strb.append("			StringBuffer strbSql = new StringBuffer(\"UPDATE \");\n");
		strb.append("			strbSql.append(this.schema);\n");
		strb.append("			strbSql.append(\".\");\n");
		strb.append("			strbSql.append(this.table);\n");
		strb.append("			strbSql.append(\" SET \");\n");
		strb.append("			\n");
		
		strb.append(strb_update);
		
		strb.append("			strbSql.append(\" WHERE \");\n");
		strb.append("			\n");
		
		strb.append(strb_where);
		
		strb.append("			PreparedStatement pstmt = null;\n");
		strb.append("			pstmt = conn.prepareStatement(strbSql.toString());\n");
		strb.append("			\n");
		strb.append("			int index_p = 1;\n");
		
		strb.append(strb_pstm_col);
		strb.append(strb_pstm_whe);

		strb.append("			\n");
		strb.append("			strbSql = null;\n");
		strb.append("			\n");
		strb.append("			return pstmt.executeUpdate();\n");
		strb.append("		}else{\n");
		strb.append("			return 0;\n");
		strb.append("		}\n");
		strb.append("	}\n");
		strb.append("	\n");
		strb.append("	/**\n");
		strb.append("	 * @param Connection conn\n");
		strb.append("	 * @param "+className(tableName)+"Dto dtoWhere\n");
		strb.append("	 * @return int\n");
		strb.append("	 * @throws SQLException\n");
		strb.append("	 * @throws Exception\n");
		strb.append("	 */\n");
		strb.append("	private int executeDelete (Connection conn, "+className(tableName)+"Dto dtoWhere)\n");
		strb.append("			throws SQLException,Exception {\n");
		strb.append("		if(dtoWhere != null){\n");
		strb.append("			boolean chk_and = false;\n");
		strb.append("			StringBuffer strbSql = new StringBuffer(\"DELETE FROM \");\n");
		strb.append("			strbSql.append(this.schema);\n");
		strb.append("			strbSql.append(\".\");\n");
		strb.append("			strbSql.append(this.table);\n");
		strb.append("			strbSql.append(\" WHERE \");\n");
		strb.append("			\n");
		
		strb.append(strb_where);
		
		strb.append("			PreparedStatement pstmt = null;\n");
		strb.append("			pstmt = conn.prepareStatement(strbSql.toString());\n");
		strb.append("			\n");
		strb.append("			int index_p = 1;\n");
		
		strb.append(strb_pstm_whe);
		
		strb.append("			\n");
		strb.append("			strbSql = null;\n");
		strb.append("			\n");
		strb.append("			return pstmt.executeUpdate();\n");
		strb.append("		}else{\n");
		strb.append("			return 0;\n");
		strb.append("		}\n");
		strb.append("	}\n");
		
		strb.append("	\n");
		strb.append("	/* (non-Javadoc)\n");
		strb.append("	 * @see com."+compShortName+projShortName+".factory."+projUpperCase+"Factory#select(java.sql.Connection, java.lang.Object)\n");
		strb.append("	 */\n");
		strb.append("	@Override\n");
		strb.append("	public List<Object> select(Connection conn, Object dto)\n");
		strb.append("			throws SQLException, Exception {\n");
		strb.append("		return executeQuery(conn, null, ("+className(tableName)+"Dto)dto, null);\n");
		strb.append("	}\n");
		
		strb.append("	\n");
		strb.append("	/* (non-Javadoc)\n");
		strb.append("	 * @see com."+compShortName+projShortName+".factory."+projUpperCase+"Factory#select(java.sql.Connection, java.lang.Object, java.lang.String)\n");
		strb.append("	 */\n");
		strb.append("	@Override\n");
		strb.append("	public List<Object> select(Connection conn, Object dto, String orderby)\n");
		strb.append("			throws SQLException, Exception {\n");
		strb.append("		return executeQuery(conn, null, ("+className(tableName)+"Dto)dto, orderby);\n");
		strb.append("	}\n");
		
		strb.append("	\n");
		strb.append("	/* (non-Javadoc)\n");
		strb.append("	 * @see com."+compShortName+projShortName+".factory."+projUpperCase+"Factory#selectByWhere(java.sql.Connection, java.lang.String, java.util.List)\n");
		strb.append("	 */\n");
		strb.append("	@Override\n");
		strb.append("	public List<Object> selectByWhere(Connection conn, String where,\n");
		strb.append("			List<Object> value) throws SQLException, Exception {\n");
		strb.append("		return executeQuery(conn, null, where, value);\n");
		strb.append("	}\n");
		
		strb.append("	\n");
		strb.append("	/* (non-Javadoc)\n");
		strb.append("	 * @see com."+compShortName+projShortName+".factory."+projUpperCase+"Factory#selectByWhere(java.sql.Connection, java.lang.String, java.lang.String, java.util.List)\n");
		strb.append("	 */\n");
		strb.append("	@Override\n");
		strb.append("	public List<Object> selectByWhere(Connection conn, String column,\n");
		strb.append("			String where, List<Object> value) throws SQLException, Exception {\n");
		strb.append("		return executeQuery(conn, column, where, value);\n");
		strb.append("	}\n");
		
		strb.append("	\n");
		strb.append("	/* (non-Javadoc)\n");
		strb.append("	 * @see com."+compShortName+projShortName+".factory."+projUpperCase+"Factory#select(java.lang.Object)\n");
		strb.append("	 */\n");
		strb.append("	@Override\n");
		strb.append("	public List<Object> select(Object dto)\n");
		strb.append("			throws SQLException, Exception {\n");
		strb.append("		List<Object> list = null;\n");
		strb.append("		Connection conn = null;\n");
		strb.append("		conn = "+projUpperCase+"DsConnection.getConnection();\n");
		strb.append("		list = executeQuery(conn, null, ("+className(tableName)+"Dto)dto, null);\n");
		strb.append("		if (conn != null) conn.commit();\n");
		strb.append("		if (conn != null) conn.close();\n");
		strb.append("		return list;\n");
		strb.append("	}\n");
		
		strb.append("	\n");
		strb.append("	/* (non-Javadoc)\n");
		strb.append(" 	* @see com."+compShortName+projShortName+".factory."+projUpperCase+"Factory#select(java.lang.Object, java.lang.String)\n");
		strb.append(" 	*/\n");
		strb.append("	@Override\n");
		strb.append("	public List<Object> select(Object dto, String orderby)\n");
		strb.append("			throws SQLException, Exception {\n");
		strb.append("		List<Object> list = null;\n");
		strb.append("		Connection conn = null;\n");
		strb.append("		conn = "+projUpperCase+"DsConnection.getConnection();\n");
		strb.append("		list = executeQuery(conn, null, ("+className(tableName)+"Dto)dto, orderby);\n");
		strb.append("		if (conn != null) conn.commit();\n");
		strb.append("		if (conn != null) conn.close();\n");
		strb.append("		return list;\n");
		strb.append("	}\n");
		
		strb.append("	\n");
		strb.append("	/* (non-Javadoc)\n");
		strb.append("	 * @see com."+compShortName+projShortName+".factory."+projUpperCase+"Factory#selectByWhere(java.lang.String, java.util.List)\n");
		strb.append("	 */\n");
		strb.append("	@Override\n");
		strb.append("	public List<Object> selectByWhere(String where,\n");
		strb.append("			List<Object> value) throws SQLException, Exception {\n");
		strb.append("		List<Object> list = null;\n");
		strb.append("		Connection conn = null;\n");
		strb.append("		conn = "+projUpperCase+"DsConnection.getConnection();\n");
		strb.append("		list = executeQuery(conn, null, where, value);\n");
		strb.append("		if (conn != null) conn.commit();\n");
		strb.append("		if (conn != null) conn.close();\n");
		strb.append("		return list;\n");
		strb.append("	}\n");
		
		strb.append("	\n");
		strb.append("	/* (non-Javadoc)\n");
		strb.append("	 * @see com."+compShortName+projShortName+".factory."+projUpperCase+"Factory#selectByWhere(java.lang.String, java.lang.String, java.util.List)\n");
		strb.append("	 */\n");
		strb.append("	@Override\n");
		strb.append("	public List<Object> selectByWhere(String column,\n");
		strb.append("			String where, List<Object> value) throws SQLException, Exception {\n");
		strb.append("		List<Object> list = null;\n");
		strb.append("		Connection conn = null;\n");
		strb.append("		conn = "+projUpperCase+"DsConnection.getConnection();\n");
		strb.append("		list = executeQuery(conn, column, where, value);\n");
		strb.append("		if (conn != null) conn.commit();\n");
		strb.append("		if (conn != null) conn.close();\n");
		strb.append("		return list;\n");
		strb.append("	}\n");
		
		strb.append("	\n");
		strb.append("	/* (non-Javadoc)\n");
		strb.append("	 * @see com."+compShortName+projShortName+".factory."+projUpperCase+"Factory#insert(java.sql.Connection, java.lang.Object)\n");
		strb.append("	 */\n");
		strb.append("	@Override\n");
		strb.append("	public int insert(Connection conn, Object dto) \n");
		strb.append("			throws SQLException,Exception{\n");
		strb.append("		return executeInsert(conn, ("+className(tableName)+"Dto)dto);\n");
		strb.append("	}\n");
		
		strb.append("	\n");
		strb.append("	/* (non-Javadoc)\n");
		strb.append("	 * @see com."+compShortName+projShortName+".factory."+projUpperCase+"Factory#updateByWhere(java.sql.Connection, java.lang.Object, java.lang.Object)\n");
		strb.append("	 */\n");
		strb.append("	@Override\n");
		strb.append("	public int update(Connection conn, Object dtoColum, Object dtoWhere) \n");
		strb.append("			throws SQLException,Exception{\n");
		strb.append("		return executeUpdate(conn, ("+className(tableName)+"Dto)dtoColum, ("+className(tableName)+"Dto)dtoWhere);\n");
		strb.append("	}\n");
		
		strb.append("	\n");
		strb.append("	/* (non-Javadoc)\n");
		strb.append("	 * @see com."+compShortName+projShortName+".factory."+projUpperCase+"Factory#deleteByWhere(java.sql.Connection, java.lang.Object)\n");
		strb.append("	 */\n");
		strb.append("	@Override\n");
		strb.append("	public int delete(Connection conn, Object dtoWhere) \n");
		strb.append("			throws SQLException,Exception{\n");
		strb.append("		return executeDelete(conn, ("+className(tableName)+"Dto)dtoWhere);\n");
		strb.append("	}\n");
		strb.append("}\n");
		
		boolean success = writeFile(path+"com//"+compShortName+"//"+projShortName+"//dao//"+packageName(schemaName)+"//"+packageName(tableName), className(tableName)+"Dao.java", strb.toString());
		strb = null;
		strb_sel_column = null;
		strb_where = null;
		strb_pstm_col = null;
		strb_pstm_whe = null;
		strb_set_dto = null;
		strb_ints = null;
		strb_ints_val = null;
		strb_update = null;
		fieldColum = null;
		System.out.println("success "+success);
	}
	
    private static String packageName(String tableName) {
        return tableName.replace("_", "").toLowerCase();
    }
    
    private static String className(String tableName) {
    	String[] a_tableName = tableName.toLowerCase().split("_");
    	tableName = "";
    	for(int i=0;i<a_tableName.length;i++){
    		if(a_tableName[i].length()>0){
    			tableName += a_tableName[i].substring(0,1).toUpperCase()+a_tableName[i].substring(1);
    		}
    	}
        return tableName;
    }
    
    private static String methodName(String columnName) {
    	String[] a_columnName = columnName.toLowerCase().split("_");
    	columnName = "";
    	for(int i=0;i<a_columnName.length;i++){
    		if(a_columnName[i].length()>0){
    			columnName += a_columnName[i].substring(0,1).toUpperCase()+a_columnName[i].substring(1);
    		}
    	}
        return columnName;
    }
    
    private static String variableName(String columnName) {
    	String[] a_columnName = columnName.toLowerCase().split("_");
    	columnName = "";
    	for(int i=0;i<a_columnName.length;i++){
    		if(a_columnName[i].length()>0){
	    		if(i==0){
	    			columnName = a_columnName[i];
	    		}else{
	    			columnName += a_columnName[i].substring(0,1).toUpperCase()+a_columnName[i].substring(1);
	    		}
    		}
    	}
        return columnName;
    }
    
    private static String variableType(int dbDataColumnType) {
    	String dataType = "String";
    	switch (dbDataColumnType) {
    	 case Types.VARCHAR	: dataType = "String"; break;
    	 case Types.CHAR : dataType = "String"; break;
    	 case Types.LONGVARCHAR :	dataType = "String"; break;
    	 case Types.BIT	: dataType = "boolean"; break;
    	 case Types.DECIMAL	: dataType = "java.math.BigDecimal"; break;
    	 case Types.NUMERIC	: dataType = "java.math.BigDecimal"; break;
    	 case Types.TINYINT	: dataType = "byte"; break;
    	 case Types.SMALLINT	: dataType = "short"; break;
    	 case Types.INTEGER	: dataType = "int"; break;
    	 case Types.BIGINT	: dataType = "long"; break;
    	 case Types.REAL	: dataType = "float"; break;
    	 case Types.FLOAT	: dataType = "float"; break;
    	 case Types.DOUBLE	: dataType = "double"; break;
    	 case Types.VARBINARY	: dataType = "byte[ ]"; break;
    	 case Types.BINARY	: dataType = "byte[ ]"; break;
    	 case Types.DATE	: dataType = "java.sql.Date"; break;
    	 case Types.TIME	: dataType = "java.sql.Time"; break;
    	 case Types.TIMESTAMP	: dataType = "java.sql.Timestamp"; break;
    	 case Types.CLOB	: dataType = "java.sql.Clob"; break;
    	 case Types.BLOB	: dataType = "java.sql.Blob"; break;
    	 case Types.ARRAY	: dataType = "java.sql.Array"; break;
    	 case Types.REF	: dataType = "java.sql.Ref"; break;
    	 case Types.STRUCT	: dataType = "java.sql.Struct"; break;
        }
        return dataType;
    }
    
    private static String variableTypeValueDefualt(int dbDataColumnType) {
    	String dataType = "null";
    	switch (dbDataColumnType) {
    	 case Types.VARCHAR	: dataType = "null"; break;
    	 case Types.CHAR : dataType = "null"; break;
    	 case Types.LONGVARCHAR :	dataType = "0"; break;
    	 case Types.BIT	: dataType = "false"; break;
    	 case Types.DECIMAL	: dataType = "null"; break;
    	 case Types.NUMERIC	: dataType = "null"; break;
    	 case Types.TINYINT	: dataType = "null"; break;
    	 case Types.SMALLINT	: dataType = "0"; break;
    	 case Types.INTEGER	: dataType = "0"; break;
    	 case Types.BIGINT	: dataType = "0"; break;
    	 case Types.REAL	: dataType = "0.00"; break;
    	 case Types.FLOAT	: dataType = "0.00"; break;
    	 case Types.DOUBLE	: dataType = "0.00"; break;
    	 case Types.VARBINARY	: dataType = "null"; break;
    	 case Types.BINARY	: dataType = "null"; break;
    	 case Types.DATE	: dataType = "null"; break;
    	 case Types.TIME	: dataType = "null"; break;
    	 case Types.TIMESTAMP	: dataType = "null"; break;
    	 case Types.CLOB	: dataType = "null"; break;
    	 case Types.BLOB	: dataType = "null"; break;
    	 case Types.ARRAY	: dataType = "null"; break;
    	 case Types.REF	: dataType = "null"; break;
    	 case Types.STRUCT	: dataType = "null"; break;
        }
        return dataType;
    }
    
    private static String getDataType(int dbDataColumnType) {
    	String dataType = "Object";
    	switch (dbDataColumnType) {
			case Types.VARCHAR	: dataType = "String"; break;
			case Types.CHAR : dataType = "String"; break;
			case Types.LONGVARCHAR :	dataType = "String"; break;
			case Types.BIT	: dataType = "Boolean"; break;
			case Types.DECIMAL	: dataType = "BigDecimal"; break;
			case Types.NUMERIC	: dataType = "BigDecimal"; break;
			case Types.TINYINT	: dataType = "Byte"; break;
			case Types.SMALLINT	: dataType = "Short"; break;
			case Types.INTEGER	: dataType = "Int"; break;
			case Types.BIGINT	: dataType = "Long"; break;
			case Types.REAL	: dataType = "Float"; break;
			case Types.FLOAT	: dataType = "Float"; break;
			case Types.DOUBLE	: dataType = "Double"; break;
			case Types.VARBINARY	: dataType = "Byte"; break;
			case Types.BINARY	: dataType = "Byte"; break;
			case Types.DATE	: dataType = "Date"; break;
			case Types.TIME	: dataType = "Time"; break;
			case Types.TIMESTAMP	: dataType = "Timestamp"; break;
			case Types.CLOB	: dataType = "Clob"; break;
			case Types.BLOB	: dataType = "Blob"; break;
			case Types.ARRAY	: dataType = "Array"; break;
			case Types.REF	: dataType = "Ref"; break;
			case Types.STRUCT	: dataType = "Object"; break;
	    }
        return dataType;
    }
    
	private static boolean writeFile(String path,String fileName,String data){
		String pathfile = "";
		boolean flag = false;
		if((!path.equals(null) || !path.equals("")) && (!fileName.equals(null) ||!fileName.equals(""))){
			pathfile = mkDirReturnPath(path)+fileName;
			System.out.println("Write File "+pathfile);
			try {
				PrintWriter printWriter = new PrintWriter(new FileWriter(pathfile, false), true);
				printWriter.close();
				FileOutputStream fileOutputStream = null;
				OutputStreamWriter outputStreamWriter = null;
				BufferedWriter buffWriter = null;
				   File file = new File(pathfile);
				   if (file.isFile()){
					   fileOutputStream = new FileOutputStream(file);
					   outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
					   buffWriter = new BufferedWriter(outputStreamWriter);
					   buffWriter.write(data);
					   buffWriter.close();
					   outputStreamWriter.close();
					   fileOutputStream.close();
					   flag = true;
				   }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	private static String mkDirReturnPath(String path) {
		try {
			File filePath = new File(path);
			if (filePath.exists()) {
				path = filePath.toString() + File.separator;
			} else {
				filePath.mkdirs();
				path = filePath.toString() + File.separator;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
}
