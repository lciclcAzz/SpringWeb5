package com.idc.spr;

import java.util.*;
import java.util.Date;
import java.sql.*;

import com.idc.utils.*;
public class ProductBean {
	private String primaryKeyWhere = "";
	private String productId;
	private String productName;
	private String typeProductId;
	private String brandProductId;
	private double priceCost;
	private double priceSale;
	private int productItem;
	private String productDateStart;
	private String productDateExpire;
	private String productDesc;
	private String deleteFlag;

	private int productIdPos = 0;
	private int productNamePos = 1;
	private int typeProductIdPos = 2;
	private int brandProductIdPos = 3;
	private int priceCostPos = 4;
	private int priceSalePos = 5;
	private int productItemPos = 6;
	private int productDateStartPos = 7;
	private int productDateExpirePos = 8;
	private int productDescPos = 9;
	private int deleteFlagPos = 10;

	private ArrayList productList = new ArrayList();

	public ProductBean () {
	}

	private boolean chkPrimaryKey () throws Exception {
		//TODO : Check your Primary Key.
		if (this.productId == null || this.productId.compareTo("") ==0||this.typeProductId == null || this.typeProductId.compareTo("") ==0||this.brandProductId == null || this.brandProductId.compareTo("") ==0)
			 throw new Exception("Invalid Primary Key for Table product.");
		//TODO : Create where cause with primary key.
		primaryKeyWhere = "Where product_id = " + this.productId+ " And type_product_id = " + this.typeProductId +" " + " And brand_product_id = " + this.brandProductId +" " ;
		return true;
	}

	private void addToArrayList(ResultSet rs) throws Exception {
		String row[] = new String[11];

		row[productIdPos] = Tools.chkNull(rs.getString("product_id"));
		row[productNamePos] = Tools.chkNull(rs.getString("product_name"));
		row[typeProductIdPos] = Tools.chkNull(rs.getString("type_product_id"));
		row[brandProductIdPos] = Tools.chkNull(rs.getString("brand_product_id"));
		row[priceCostPos] = Tools.chkNull(rs.getString("price_cost"));
		row[priceSalePos] = Tools.chkNull(rs.getString("price_sale"));
		row[productItemPos] = Tools.chkNull(rs.getString("product_item"));
		row[productDateStartPos] = Tools.chkNull(rs.getString("product_date_start"));
		row[productDateExpirePos] = Tools.chkNull(rs.getString("product_date_expire"));
		row[productDescPos] = Tools.chkNull(rs.getString("product_desc"));
		row[deleteFlagPos] = Tools.chkNull(rs.getString("delete_flag"));
		productList.add(row);
	}

	public int getSize() {
		return productList.size();
	}

	public void setproductList (ArrayList productList) {
		this.productList = productList;
	}

	public int selectByPrimaryKey () throws SQLException, Exception {
		Connection con = null;
		try {
			con = conDB.getConnection();
			selectByPrimaryKey(con);
		} catch (SQLException sqle) {
			System.out.println("SQLException Error in productBean.selectByPrimaryKey() : " + sqle);
			sqle.printStackTrace(System.out);
			throw sqle;
		} catch (Exception e) {
			System.out.println("Exception Error in productBean.selectByPrimaryKey() : " + e);
			e.printStackTrace(System.out);
			throw e;
		} finally {
			if (con != null) {
				try {
					con.commit();
					con.close();
				} catch (Exception e) { ;}
			}
		}
		return this.getSize();
	}

	public int selectByPrimaryKey (Connection con) throws SQLException, Exception {
		try {
			if (chkPrimaryKey()) {
				selectWhere(con, primaryKeyWhere);
			}
		} catch (SQLException sqle) {
				System.out.println("SQLException Error in productBean.selectByPrimaryKey(Connection) : " + sqle);
				sqle.printStackTrace(System.out);
				throw sqle;
		} catch (Exception e) {
			System.out.println("Exception Error in productBean.selectByPrimaryKey(Connection) : " + e);
			e.printStackTrace(System.out);
			throw e;
		}
		return this.getSize();
	}

	public int selectWhere (String where) throws SQLException, Exception {
		Connection con = null;
		try {
			con = conDB.getConnection();
			selectWhere(con, where);
		} catch (SQLException sqle) {
			System.out.println("SQLException Error in productBean.selectWhere(String) : " + sqle);
			sqle.printStackTrace(System.out);
			throw sqle;
		} catch (Exception e) {
			System.out.println("Exception Error in productBean.selectWhere(String) : " + e);
			e.printStackTrace(System.out);
			throw e;
		} finally {
			if (con != null) {
				try {
					con.commit();
					con.close();
				} catch (Exception e) { ;}
			}
		}
		return this.getSize();
	}

	public int selectWhere (Connection con, String where) throws SQLException, Exception {
		Statement stmt = null;
		ResultSet rs   = null;
		String sql = "Select * from "  + ".product " + where;

		try {
			if (productList.isEmpty() == false)
				productList.clear();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				addToArrayList(rs);
			}
			rs.close();
			stmt.close();
		} catch (SQLException sqle) {
			System.out.println("SQLException Error in productBean.selectWhere(Connection, String) : " + sqle);
			sqle.printStackTrace(System.out);
			throw sqle;
		} catch (Exception e) {
			System.out.println("Exception Error in productBean.selectWhere(Connection, String) : " + e);
			e.printStackTrace(System.out);
			throw e;
		}
		return this.getSize();
	}

	public int insert () throws SQLException, Exception {
		Connection con = null;
		try {
			con = conDB.getConnection();
			return insert(con);
		} catch (SQLException sqle) {
			System.out.println("SQLException Error in productBean.insert() : " + sqle);
			sqle.printStackTrace(System.out);
			throw sqle;
		} catch (Exception e) {
			System.out.println("Exception Error in productBean.insert() : " + e);
			e.printStackTrace(System.out);
			throw e;
		} finally {
			if (con != null) {
				try {
					con.commit();
					con.close();
				} catch (Exception e) { ;}
		}
		}
	}

	public int insert (Connection con) throws SQLException, Exception {
		Statement stmt = null;
		int rowsInsert = 0;
		String sql = "Insert into "  + ".product (product_id, product_name, type_product_id, brand_product_id, price_cost, price_sale, product_item, product_date_start, product_date_expire, product_desc, delete_flag) values " + "(" + this.productId + ", " + this.productName + ", " + this.typeProductId + ", " + this.brandProductId + ", " + this.priceCost + ", " + this.priceSale + ", " + this.productItem + ", " + Tools.dateToDb(this.productDateStart) + ", " + Tools.dateToDb(this.productDateExpire) + ", " + this.productDesc + ", " + this.deleteFlag + ")";
		try {
			stmt = con.createStatement();
			rowsInsert = stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException sqle) {
			System.out.println("SQLException Error in productBean.insert(Connection, String) : " + sqle);
			sqle.printStackTrace(System.out);
			throw sqle;
		}
			return rowsInsert;
	}

	public int deleteByPrimaryKey () throws SQLException, Exception {
		Connection con = null;
		try {
			con = conDB.getConnection();
			return deleteByPrimaryKey(con);
		} catch (SQLException sqle) {
			System.out.println("SQLException Error in productBean.deleteByPrimaryKey() : " + sqle);
			sqle.printStackTrace(System.out);
			throw sqle;
		} catch (Exception e) {
			System.out.println("Exception Error in productBean.deleteByPrimaryKey() : " + e);
			e.printStackTrace(System.out);
			throw e;
		} finally {
			if (con != null) {
				try {
					con.commit();
					con.close();
				} catch (Exception e) { ;}
			}
		}
	}

	public int deleteByPrimaryKey (Connection con) throws SQLException, Exception {
		Statement stmt = null;
		int rowsDelete = 0;
		String sql = "Delete from "  + ".product ";
		try {
			if (chkPrimaryKey()) {
				stmt = con.createStatement();
				rowsDelete = stmt.executeUpdate(sql + primaryKeyWhere);
				stmt.close();
			}
		} catch (SQLException sqle) {
			System.out.println("SQLException Error in productBean.deleteByPrimaryKey(Connection) : " + sqle);
			sqle.printStackTrace(System.out);
			throw sqle;
		} catch (Exception e) {
			System.out.println("Exception Error in productBean.deleteByPrimaryKey(Connection) : " + e);
			e.printStackTrace(System.out);
			throw e;
		}
		return rowsDelete;
	}

	public int deleteWhere (Connection con, String where) throws SQLException, Exception {
		Statement stmt = null;
		int rowsDelete = 0;
		String sql = "Delete from "  + ".product ";
		try {
				stmt = con.createStatement();
				rowsDelete = stmt.executeUpdate(sql + where);
				stmt.close();
		} catch (SQLException sqle) {
			System.out.println("SQLException Error in productBean.deleteWhere(Connection) : " + sqle);
			sqle.printStackTrace(System.out);
			throw sqle;
		} catch (Exception e) {
			System.out.println("Exception Error in productBean.deleteWhere(Connection) : " + e);
			e.printStackTrace(System.out);
			throw e;
		}
		return rowsDelete;
	}

	public int updateByPrimaryKey () throws SQLException, Exception {
		Connection con = null;
		try {
			con = conDB.getConnection();
			return updateByPrimaryKey(con);
		} catch (SQLException sqle) {
			System.out.println("SQLException Error in productBean.updateByPrimaryKey() : " + sqle);
			sqle.printStackTrace(System.out);
			throw sqle;
		} catch (Exception e) {
			System.out.println("Exception Error in productBean.updateByPrimaryKey() : " + e);
			e.printStackTrace(System.out);
			throw e;
		} finally {
			if (con != null) {
				try {
					con.commit();
					con.close();
				} catch (Exception e) { ;}
			}
		}
	}

	public int updateByPrimaryKey (Connection con) throws SQLException, Exception {
		String colUpdate = "product_id = " + this.productId + ", product_name = " + this.productName + ", type_product_id = " + this.typeProductId + ", brand_product_id = " + this.brandProductId + ", price_cost = " + this.priceCost + ", price_sale = " + this.priceSale + ", product_item = " + this.productItem + ", product_date_start = " + Tools.dateToDb(this.productDateStart) + ", product_date_expire = " + Tools.dateToDb(this.productDateExpire) + ", product_desc = " + this.productDesc + ", delete_flag = " + this.deleteFlag;
		try {
			if (chkPrimaryKey()) {
				return updateByColumn(con, colUpdate, primaryKeyWhere);
			} else {
				return 0;
			}
		} catch (SQLException sqle) {
			System.out.println("SQLException Error in productBean.updateByPrimaryKey(Connection) : " + sqle);
			sqle.printStackTrace(System.out);
			throw sqle;
		} catch (Exception e) {
			System.out.println("Exception Error in productBean.updateByPrimaryKey(Connection) : " + e);
			e.printStackTrace(System.out);
			throw e;
		}
	}

	public int updateByColumn (String colUpdate, String where) throws SQLException, Exception {
		Connection con = null;
		try {
			con = conDB.getConnection();
			return updateByColumn(con, colUpdate, where);
		} catch (SQLException sqle) {
			System.out.println("SQLException Error in productBean.updateByColumn(String, String) : " + sqle);
			sqle.printStackTrace(System.out);
			throw sqle;
		} catch (Exception e) {
			System.out.println("Exception Error in productBean.updateByColumn(String, String) : " + e);
			e.printStackTrace(System.out);
			throw e;
		} finally {
			if (con != null) {
				try {
					con.commit();
					con.close();
				} catch (Exception e) { ;}
			}
		}
	}

	public int updateByColumn (Connection con, String colUpdate, String where) throws SQLException, Exception {
		Statement stmt = null;
		int rowsUpdate = 0;
		String sql = "Update "  + ".product set " + colUpdate + " " + where;
		try {
			stmt = con.createStatement();
			rowsUpdate = stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException sqle) {
			System.out.println("SQLException Error in productBean.updateByColumn(Connection, String, String) : " + sqle);
			sqle.printStackTrace(System.out);
			throw sqle;
		} catch (Exception e) {
			System.out.println("Exception Error in productBean.updateByColumn(Connection, String, String) : " + e);
			e.printStackTrace(System.out);
			throw e;
		}
		return rowsUpdate;
	}

	/**
	* get record between row 1 to row n from database 
	 * return ArraList
	 * */
	public ArrayList executeQuerybetweenRec(String where,String beginrec , String endrec , boolean autoCommit) throws SQLException, Exception {
		Connection con = null;
		String strorder = "";
		try {
			con = conDB.getConnection();
			 executeQuerybetweenRec(con,where,beginrec ,endrec,strorder,autoCommit);
		} catch (SQLException sqle) {
			System.out.println("SQLException Error in productBean.selectWhere(String) : " + sqle);
			sqle.printStackTrace(System.out);
			throw sqle;
		} catch (Exception e) {
			System.out.println("Exception Error in productBean.selectWhere(String) : " + e);
			e.printStackTrace(System.out);
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) { ;}
			}
		}
		return this.productList;
	}

	/**
	* get record between row 1 to row n from database 
	 * return ArraList
	 * */
	public ArrayList executeQuerybetweenRec(String where,String beginrec , String endrec , String strorder , boolean autoCommit) throws SQLException, Exception {
		Connection con = null;
		try {
			con = conDB.getConnection();
			 executeQuerybetweenRec(con,where,beginrec ,endrec,strorder,autoCommit);
		} catch (SQLException sqle) {
			System.out.println("SQLException Error in productBean.selectWhere(String) : " + sqle);
			sqle.printStackTrace(System.out);
			throw sqle;
		} catch (Exception e) {
			System.out.println("Exception Error in productBean.selectWhere(String) : " + e);
			e.printStackTrace(System.out);
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) { ;}
			}
		}
		return this.productList;
	}

	/**
	* get record between row 1 to row n from database 
	 * return ArraList
	 * */
	public ArrayList executeQuerybetweenRec (Connection con,String where , String beginrec , String endrec , String strorder ,boolean autoCommit) throws SQLException, Exception {
		Statement stmt = null;
		ResultSet rs   = null;
		String sql = "Select * from (select c.*,ROW_NUMBER() OVER("+strorder+") as RN from product as c "+where+") as x where RN between "+ beginrec + " and "+ endrec;

		try {
			if (productList.isEmpty() == false)
				productList.clear();
			if (productList.isEmpty() == false)
				productList.clear();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				addToArrayList(rs);
			}
			rs.close();
			stmt.close();
			if(autoCommit) {
				con.commit();
			}
		} catch (SQLException sqle) {
			rs.close();
			stmt.close();
			if(autoCommit) {
				con.rollback();
			}
			System.out.println("SQLException SQL : " + sql);
			System.out.println("SQLException Error in productEBean.selectWhere(Connection, String) : " + sqle);
			sqle.printStackTrace(System.out);
			throw sqle;
		} catch (Exception e) {
			rs.close();
			stmt.close();
			if(autoCommit) {
				con.rollback();
			}
			System.out.println("Exception Error in productEBean.selectWhere(Connection, String) : " + e);
			e.printStackTrace(System.out);
			throw e;
		}
		return this.productList;
	}

	/**
	* select count or max from... where... 
	 * return ArraList
	 * */
	public ArrayList executeQueryAdvSql(String sql) throws SQLException, Exception {
		Connection con = null;
		ArrayList list = new ArrayList();
		try {
				con = conDB.getConnection();
				list = executeQueryAdvSql(con, sql);
			} catch (Exception e) {
				System.out.println("Exception Error in executeQueryAdvSql.executeQuerymainAdv(String) : " + e);
				e.printStackTrace(System.out);
				throw e;
			} finally {
				if (con != null) {
				try {
					con.close();
				} catch (Exception e) { ;}
				}
			}
			return list;
	}

	/**
	* select count or max from... where... 
	 * return ArraList
	 * */
	public ArrayList executeQueryAdvSql(Connection con , String sql){	
			Statement stmt = null;
			ArrayList list = new ArrayList();
			ResultSet rs = null;
			ResultSetMetaData rsmd = null;
			try{
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				rsmd = rs.getMetaData();
				int colCount = rsmd.getColumnCount();
				String[] colNames = new String[colCount];
				String toCap = null;
				for (int j = 1; j <= colCount; j++){
				toCap = rsmd.getColumnName(j);
				colNames[j-1] = toCap.toUpperCase();
				}
				int rowNum = 0;
				while(rs.next()){
				//Represent Row Object
				Map map = new HashMap();
				for(int i = 1;i<= colCount;i++){
					Object obj = rs.getObject(i);
					if(obj != null){
						if (obj instanceof Date) {
						obj = Tools.chkNull(rs.getString(i));
					}
					map.put(colNames[i-1],obj);
					}else{
					map.put(colNames[i-1],"");
					}
				}
				list.add(rowNum,map);
				//Represent Table Object
				rowNum++;
				}//End Records
			}catch(Exception e){
				System.out.println("Exception Error in executeQueryAdvSql.executeQueryAdv(String) : " + e);
			}finally{
				try{
					if (rs != null) rs.close();
					if (stmt != null) stmt.close();
				}catch(Exception ex){}
			}
			return list;
	}
	/**
	 * Ex =  select cusid , cusname , cursurname from x.a1 where offcode='xxxx'
	 * return ArraList
	 * */
	public ArrayList executeQuerymainAdv(String table ,String where) throws SQLException, Exception {
		Connection con = null;
		ArrayList list = new ArrayList();
		try {
				con = conDB.getConnection();
				list = executeQuerymainAdv(con,table, where);
			} catch (Exception e) {
				System.out.println("Exception Error in executeQueryAdvSql.executeQuerymainAdv(String) : " + e);
				e.printStackTrace(System.out);
				throw e;
			} finally {
				if (con != null) {
				try {
					con.close();
				} catch (Exception e) { ;}
				}
			}
			return list;
	}

	/**
	 * Ex =  select cusid , cusname , cursurname from x.a1 where offcode='xxxx'
	 * return ArraList
	 * */
	public ArrayList executeQuerymainAdv(Connection con , String table ,String where){	
			Statement stmt = null;
			ArrayList list = new ArrayList();
			ResultSet rs = null;
			ResultSetMetaData rsmd = null;
			try{
				stmt = con.createStatement();
				String sql = "select * from "+table+" "+where ;
				rs = stmt.executeQuery(sql);
				rsmd = rs.getMetaData();
				int colCount = rsmd.getColumnCount();
				String[] colNames = new String[colCount];
				String toCap = null;
				for (int j = 1; j <= colCount; j++){
				toCap = rsmd.getColumnName(j);
				colNames[j-1] = toCap.toUpperCase();
				}
				int rowNum = 0;
				while(rs.next()){
				//Represent Row Object
				Map map = new HashMap();
				for(int i = 1;i<= colCount;i++){
					Object obj = rs.getObject(i);
					if(obj != null){
						if (obj instanceof Date) {
						obj = Tools.chkNull(rs.getString(i));
					}
					map.put(colNames[i-1],obj);
					}else{
					map.put(colNames[i-1],"");
					}
				}
				list.add(rowNum,map);
				//Represent Table Object
				rowNum++;
				}//End Records
			}catch(Exception e){
				System.out.println("Exception Error in executeQueryAdvSql.executeQuerymainAdv(String) : " + e);
			}finally{
				try{
					if (rs != null) rs.close();
					if (stmt != null) stmt.close();
				}catch(Exception ex){}
			}
			return list;
	}
	public String[] getProductId() {
		int records = getSize();
		String col[] = new String[records];

		for (int at=0; at<records; at++) {
			String row[] = (String[])productList.get(at);
			col[at] = row[productIdPos];
		}
		return col;
	}
	public String getProductIdAt(int at) {
		if ((at >= getSize()) || (getSize() == 0))
			return "";
		String row[] = (String[])productList.get(at);
		return row[productIdPos];
	}
	public String[] getProductName() {
		int records = getSize();
		String col[] = new String[records];

		for (int at=0; at<records; at++) {
			String row[] = (String[])productList.get(at);
			col[at] = row[productNamePos];
		}
		return col;
	}
	public String getProductNameAt(int at) {
		if ((at >= getSize()) || (getSize() == 0))
			return "";
		String row[] = (String[])productList.get(at);
		return row[productNamePos];
	}
	public int[] getTypeProductId() {
		int records = getSize();
		int col[] = new int[records];

		for (int at=0; at<records; at++) {
			String row[] = (String[])productList.get(at);
			col[at] = (new Integer(row[typeProductIdPos])).intValue();
		}
		return col;
	}
	public int getTypeProductIdAt(int at) {
		if ((at >= getSize()) || (getSize() == 0))
			return 0;
		String row[] = (String[])productList.get(at);
		return (new Integer(row[typeProductIdPos])).intValue();
	}
	public int[] getBrandProductId() {
		int records = getSize();
		int col[] = new int[records];

		for (int at=0; at<records; at++) {
			String row[] = (String[])productList.get(at);
			col[at] = (new Integer(row[brandProductIdPos])).intValue();
		}
		return col;
	}
	public int getBrandProductIdAt(int at) {
		if ((at >= getSize()) || (getSize() == 0))
			return 0;
		String row[] = (String[])productList.get(at);
		return (new Integer(row[brandProductIdPos])).intValue();
	}
	public double[] getPriceCost() {
		int records = getSize();
		double col[] = new double[records];

		for (int at=0; at<records; at++) {
			String row[] = (String[])productList.get(at);
			col[at] = (new Double(Tools.chkNullToZero(row[priceCostPos]))).doubleValue();
		}
		return col;
	}
	public double getPriceCostAt(int at) {
		if ((at >= getSize()) || (getSize() == 0))
			return 0.0;
		String row[] = (String[])productList.get(at);
		return (new Double(Tools.chkNullToZero(row[priceCostPos]))).doubleValue();
	}
	public double[] getPriceSale() {
		int records = getSize();
		double col[] = new double[records];

		for (int at=0; at<records; at++) {
			String row[] = (String[])productList.get(at);
			col[at] = (new Double(Tools.chkNullToZero(row[priceSalePos]))).doubleValue();
		}
		return col;
	}
	public double getPriceSaleAt(int at) {
		if ((at >= getSize()) || (getSize() == 0))
			return 0.0;
		String row[] = (String[])productList.get(at);
		return (new Double(Tools.chkNullToZero(row[priceSalePos]))).doubleValue();
	}
	public int[] getProductItem() {
		int records = getSize();
		int col[] = new int[records];

		for (int at=0; at<records; at++) {
			String row[] = (String[])productList.get(at);
			col[at] = (new Integer(row[productItemPos])).intValue();
		}
		return col;
	}
	public int getProductItemAt(int at) {
		if ((at >= getSize()) || (getSize() == 0))
			return 0;
		String row[] = (String[])productList.get(at);
		return (new Integer(row[productItemPos])).intValue();
	}
	public String[] getProductDateStart() {
		int records = getSize();
		String col[] = new String[records];

		for (int at=0; at<records; at++) {
			String row[] = (String[])productList.get(at);
			col[at] = row[productDateStartPos];
		}
		return col;
	}
	public String getProductDateStartAt(int at) {
		if ((at >= getSize()) || (getSize() == 0))
			return "";
		String row[] = (String[])productList.get(at);
		return row[productDateStartPos];
	}
	public String[] getProductDateExpire() {
		int records = getSize();
		String col[] = new String[records];

		for (int at=0; at<records; at++) {
			String row[] = (String[])productList.get(at);
			col[at] = row[productDateExpirePos];
		}
		return col;
	}
	public String getProductDateExpireAt(int at) {
		if ((at >= getSize()) || (getSize() == 0))
			return "";
		String row[] = (String[])productList.get(at);
		return row[productDateExpirePos];
	}
	public String[] getProductDesc() {
		int records = getSize();
		String col[] = new String[records];

		for (int at=0; at<records; at++) {
			String row[] = (String[])productList.get(at);
			col[at] = row[productDescPos];
		}
		return col;
	}
	public String getProductDescAt(int at) {
		if ((at >= getSize()) || (getSize() == 0))
			return "";
		String row[] = (String[])productList.get(at);
		return row[productDescPos];
	}
	public String[] getDeleteFlag() {
		int records = getSize();
		String col[] = new String[records];

		for (int at=0; at<records; at++) {
			String row[] = (String[])productList.get(at);
			col[at] = row[deleteFlagPos];
		}
		return col;
	}
	public String getDeleteFlagAt(int at) {
		if ((at >= getSize()) || (getSize() == 0))
			return "";
		String row[] = (String[])productList.get(at);
		return row[deleteFlagPos];
	}
	public void setProductId(String productId) {
		if (productId == null) {
			this.productId = productId;
		} else {
			this.productId = "'" + productId + "'";
		}
	}

	public void setProductName(String productName) {
		if (productName == null) {
			this.productName = productName;
		} else {
			this.productName = "'" + productName + "'";
		}
	}

	public void setTypeProductId(String typeProductId) {
			this.typeProductId = typeProductId;
	}

	public void setBrandProductId(String brandProductId) {
			this.brandProductId = brandProductId;
	}

	public void setPriceCost(double priceCost) {
			this.priceCost = priceCost;
	}

	public void setPriceSale(double priceSale) {
			this.priceSale = priceSale;
	}

	public void setProductItem(int productItem) {
			this.productItem = productItem;
	}

	public void setProductDateStart(String productDateStart) {
		if (productDateStart == null) {
			this.productDateStart = productDateStart;
		} else {
			this.productDateStart = "'" + productDateStart + "'";
		}
	}

	public void setProductDateExpire(String productDateExpire) {
		if (productDateExpire == null) {
			this.productDateExpire = productDateExpire;
		} else {
			this.productDateExpire = "'" + productDateExpire + "'";
		}
	}

	public void setProductDesc(String productDesc) {
		if (productDesc == null) {
			this.productDesc = productDesc;
		} else {
			this.productDesc = "'" + productDesc + "'";
		}
	}

	public void setDeleteFlag(String deleteFlag) {
		if (deleteFlag == null) {
			this.deleteFlag = deleteFlag;
		} else {
			this.deleteFlag = "'" + deleteFlag + "'";
		}
	}

}
