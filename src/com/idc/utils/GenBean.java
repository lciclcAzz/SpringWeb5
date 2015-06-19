package com.idc.utils;

import com.idc.utils.conDB;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class GenBean {
    private Connection con;

    private String schemaName;

    private String tableName;

    private String fileName;

    private String getter;

    private String setter;

    private String declaration;

    private String position;

    private String addValue;

    private String insertColumn;

    private String insertValue;

    private String updateColumn;

    private String packageName;

    private String className;

    private FileWriter out;

    private String[] colPrimary;

    public GenBean() {
        con = conDB.getConnection();
        getter = "";
        setter = "";
        position = "";
        declaration = "";
        addValue = "";
        insertValue = "";
        insertColumn = "";
        updateColumn = "";
        packageName = "";
        className = "";
    }

    public static void main(String[] args){
            ArrayList alist = new ArrayList();
            /*           
            while ((buffer = brin.readLine()) != null) {
                GenBean genbean = new GenBean();
                //genbean.schemaName = "RTN";
                genbean.packageName = "util";
                genbean.tableName = buffer;
                buffer = brin.readLine();
                genbean.className = buffer;
                buffer = brin.readLine();
                StringTokenizer stk = new StringTokenizer(buffer, "#");
                String column = "";
                alist.clear();
                while (stk.hasMoreTokens()) {
                    column = stk.nextToken();
                    alist.add(column);
                }
                strPrimaryKey = new String[15];
                alist.toArray(strPrimaryKey);
                System.out.println("Table " + genbean.tableName);
                System.out.println("Class " + genbean.className);
                for (int i = 0;
                     i < strPrimaryKey.length && strPrimaryKey[i] != null; i++)
                    System.out
                    .println("Primary Key " + (i + 1) + " " + strPrimaryKey[i]);
                genbean.colPrimary = strPrimaryKey;
                genbean.fileName =
                    "E:/" +
                    genbean.className + "Bean.java";
                genbean.performTask();
            }
           
        } catch (Exception e) {
            System.out.println("Exception = " + e);
 
            e.printStackTrace();*/
        //String strPrimaryKey[] = {"RTNCTL_NO"};
        //String strPrimaryKey[] = {"RTNCTL_NO", "SEQ_NO"};
        String strPrimaryKey[] = {"product_id","type_product_id","brand_product_id"};
        GenBean genbean = new GenBean();
        //genbean.schemaName = "RDB";
        genbean.packageName = "spr";
        genbean.tableName = "product";
        genbean.className = "Product";
        genbean.colPrimary = strPrimaryKey;
        //genbean.fileName = "D:/ED-0078-48/EDINTRAAPI/EDINTRAAPI/src/com/pccth/rtn/IntPs51DetBean.java";
        genbean.fileName = "D:/GitHub/workspace/SpringWeb5/SpringWeb5/src/com/idc/spr/"+genbean.className + "Bean.java";
        genbean.performTask();
        System.out.println(" End Class ");
    }

    private void performTask() {
        String sqlWehre = "", sCondition = "";
        //String sql = "select * from " + schemaName + "." + tableName;
        String sql = "select * from " + tableName;
        System.out.println(sql);
        Statement stm = null;
        ResultSet rs = null;
        ResultSetMetaData rsm = null;

        try {
            out = new FileWriter(fileName);
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            rsm = rs.getMetaData();
            int noOfColumn = rsm.getColumnCount();

            for (int count = 1; count <= noOfColumn; count++) {
                getColumnInfo(rsm, count);
            }
            insertColumn += ")";
            insertValue += " + \")";

            out.write("package com.idc." + packageName + ";\n\n");
            out.write("import java.util.*;\n");
            out.write("import java.sql.*;\n");
            out.write("import com.idc.utils.*;\n");

            out.write("public class " + className + "Bean {\n");
            out.write("\tprivate String primaryKeyWhere = \"\";\n");
            out.write(declaration + "\n");
            out.write(position + "\n");
            out.write("\tprivate ArrayList " + tableName + "List = new ArrayList();\n\n");
            out.write("\tpublic " + className + "Bean () {\n");
            out.write("\t}\n\n");
/*
            out.write("\tprivate boolean chkPrimaryKey () throws Exception {\n");
            out.write("\t\t//TODO : Check your Primary Key.\n");
            out.write("\t\tif (aaa == null || aaa.compareTo(\"\") ==0)\n");
            out.write("\t\t\t throw new Exception(\"Invalid Primary Key for Table " + tableName + ".\");\n");
            out.write("\t\t//TODO : Create where cause with primary key.\n");
            out.write("\t\tprimaryKeyWhere = \"Where aaa = '\" + aaa + \"' \";\n");
            out.write("\t\treturn true;\n");
            out.write("\t}\n\n");
*/
            out
            .write("\tprivate boolean chkPrimaryKey () throws Exception {\n");
            out.write("\t\t//TODO : Check your Primary Key.\n");
            //out.write("\t\tif (");
            for (int r = 0; r < this.colPrimary.length; r++) {
                //out.write("\tthis."+chgUnderScoreToSpaceToFirstUp(this.colPrimary[r].toLowerCase())+" == null || this."+chgUnderScoreToSpaceToFirstUp(this.colPrimary[r].toLowerCase())+".compareTo(\"\") ==0");
                if (r == 0) {
                    sCondition =
                        "\t\tif (this." + chgUnderScoreToSpaceToFirstUp(this.colPrimary[r].toLowerCase()) +
                        " == null || this." +
                        chgUnderScoreToSpaceToFirstUp(this.colPrimary[r].toLowerCase()) +
                        ".compareTo(\"\") ==0";
                    sqlWehre =
                        "\t\tprimaryKeyWhere = \"Where " + this.colPrimary[r] +
                        " = \" + this." +
                        chgUnderScoreToSpaceToFirstUp(this.colPrimary[r].toLowerCase());
                } else if (this.colPrimary[r] != null) {
                    sCondition +=
                        ("" + "||this." + chgUnderScoreToSpaceToFirstUp(this.colPrimary[r].toLowerCase()) +
                                   " == null || this." +
                                   chgUnderScoreToSpaceToFirstUp(this.colPrimary[r].toLowerCase()) +
                                   ".compareTo(\"\") ==0");
                    sqlWehre +=
                        "+ \" And " + this.colPrimary[r] + " = \" + this." +
                        chgUnderScoreToSpaceToFirstUp(this.colPrimary[r].toLowerCase()) +
                        " +\" \" ";
                }
            }
            out.write(sCondition + ")\n");
            //out.write("\t\t)\n");
            out
            .write("\t\t\t throw new Exception(\"Invalid Primary Key for Table " +
                      tableName + ".\");\n");
            out.write("\t\t//TODO : Create where cause with primary key.\n");
            out.write(sqlWehre + ";\n");
            out.write("\t\treturn true;\n");
            out.write("\t}\n\n");

            out
            .write("\tprivate void addToArrayList(ResultSet rs) throws Exception {\n");
            out
            .write("\t\tString row[] = new String[" + noOfColumn + "];\n\n");
            out.write(addValue);
            out.write("\t\t" + tableName + "List.add(row);\n");
            out.write("\t}\n\n");

            out.write("\tpublic int getSize() {\n");
            out.write("\t\treturn " + tableName + "List.size();\n");
            out.write("\t}\n\n");

            out
            .write("\tpublic void set" + tableName + "List (ArrayList " + tableName +
                      "List) {\n");
            out
            .write("\t\tthis." + tableName + "List = " + tableName + "List;\n");
            out.write("\t}\n\n");

            out
            .write("\tpublic int selectByPrimaryKey () throws SQLException, Exception {\n");
            out.write("\t\tConnection con = null;\n");
            out.write("\t\ttry {\n");
            out.write("\t\t\tcon = conDB.getConnection();\n");
            out.write("\t\t\tselectByPrimaryKey(con);\n");
            out.write("\t\t} catch (SQLException sqle) {\n");
            out
            .write("\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                      "Bean.selectByPrimaryKey() : \" + sqle);\n");
            out.write("\t\t\tsqle.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow sqle;\n");
            out.write("\t\t} catch (Exception e) {\n");
            out
            .write("\t\t\tSystem.out.println(\"Exception Error in " + tableName +
                      "Bean.selectByPrimaryKey() : \" + e);\n");
            out.write("\t\t\te.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow e;\n");
            out.write("\t\t} finally {\n");
            out.write("\t\t\tif (con != null) {\n");
            out.write("\t\t\t\ttry {\n");
            out.write("\t\t\t\t\tcon.commit();\n");            
            out.write("\t\t\t\t\tcon.close();\n");
            out.write("\t\t\t\t} catch (Exception e) { ;}\n");
            out.write("\t\t\t}\n");
            out.write("\t\t}\n");
            out.write("\t\treturn this.getSize();\n");
            out.write("\t}\n\n");

            out
            .write("\tpublic int selectByPrimaryKey (Connection con) throws SQLException, Exception {\n");
            out.write("\t\ttry {\n");
            out.write("\t\t\tif (chkPrimaryKey()) {\n");
            out.write("\t\t\t\tselectWhere(con, primaryKeyWhere);\n");
            out.write("\t\t\t}\n");
            out.write("\t\t} catch (SQLException sqle) {\n");
            out
            .write("\t\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                      "Bean.selectByPrimaryKey(Connection) : \" + sqle);\n");
            out.write("\t\t\t\tsqle.printStackTrace(System.out);\n");
            out.write("\t\t\t\tthrow sqle;\n");
            out.write("\t\t} catch (Exception e) {\n");
            out
            .write("\t\t\tSystem.out.println(\"Exception Error in " + tableName +
                      "Bean.selectByPrimaryKey(Connection) : \" + e);\n");
            out.write("\t\t\te.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow e;\n");
            out.write("\t\t}\n");
            out.write("\t\treturn this.getSize();\n");
            out.write("\t}\n\n");

            out
            .write("\tpublic int selectWhere (String where) throws SQLException, Exception {\n");
            out.write("\t\tConnection con = null;\n");
            out.write("\t\ttry {\n");
            out.write("\t\t\tcon = conDB.getConnection();\n");
            out.write("\t\t\tselectWhere(con, where);\n");
            out.write("\t\t} catch (SQLException sqle) {\n");
            out
            .write("\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                      "Bean.selectWhere(String) : \" + sqle);\n");
            out.write("\t\t\tsqle.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow sqle;\n");
            out.write("\t\t} catch (Exception e) {\n");
            out
            .write("\t\t\tSystem.out.println(\"Exception Error in " + tableName +
                      "Bean.selectWhere(String) : \" + e);\n");
            out.write("\t\t\te.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow e;\n");
            out.write("\t\t} finally {\n");
            out.write("\t\t\tif (con != null) {\n");
            out.write("\t\t\t\ttry {\n");
            out.write("\t\t\t\t\tcon.commit();\n");            
            out.write("\t\t\t\t\tcon.close();\n");
            out.write("\t\t\t\t} catch (Exception e) { ;}\n");
            out.write("\t\t\t}\n");
            out.write("\t\t}\n");
            out.write("\t\treturn this.getSize();\n");
            out.write("\t}\n\n");

            out
            .write("\tpublic int selectWhere (Connection con, String where) throws SQLException, Exception {\n");
            out.write("\t\tStatement stmt = null;\n");
            out.write("\t\tResultSet rs   = null;\n");
            out
            .write("\t\tString sql = \"Select * from \" " +
                      " + \"." + tableName + " \" + where;\n\n");
            out.write("\t\ttry {\n");
            out.write("\t\t\tif (" + tableName + "List.isEmpty() == false)\n");
            out.write("\t\t\t\t" + tableName + "List.clear();\n");
            out.write("\t\t\tstmt = con.createStatement();\n");
            out.write("\t\t\trs = stmt.executeQuery(sql);\n");
            out.write("\t\t\twhile (rs.next()) {\n");
            out.write("\t\t\t\taddToArrayList(rs);\n");
            out.write("\t\t\t}\n");
            out.write("\t\t\trs.close();\n");
            out.write("\t\t\tstmt.close();\n");
            out.write("\t\t} catch (SQLException sqle) {\n");
            out
            .write("\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                      "Bean.selectWhere(Connection, String) : \" + sqle);\n");
            out.write("\t\t\tsqle.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow sqle;\n");
            out.write("\t\t} catch (Exception e) {\n");
            out
            .write("\t\t\tSystem.out.println(\"Exception Error in " + tableName +
                      "Bean.selectWhere(Connection, String) : \" + e);\n");
            out.write("\t\t\te.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow e;\n");
            out.write("\t\t}\n");
            out.write("\t\treturn this.getSize();\n");
            out.write("\t}\n\n");

            out
            .write("\tpublic int insert () throws SQLException, Exception {\n");
            out.write("\t\tConnection con = null;\n");
            out.write("\t\ttry {\n");
            out.write("\t\t\tcon = conDB.getConnection();\n");
            out.write("\t\t\treturn insert(con);\n");
            out.write("\t\t} catch (SQLException sqle) {\n");
            out
            .write("\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                      "Bean.insert() : \" + sqle);\n");
            out.write("\t\t\tsqle.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow sqle;\n");
            out.write("\t\t} catch (Exception e) {\n");
            out
            .write("\t\t\tSystem.out.println(\"Exception Error in " + tableName +
                      "Bean.insert() : \" + e);\n");
            out.write("\t\t\te.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow e;\n");
            out.write("\t\t} finally {\n");
            out.write("\t\t\tif (con != null) {\n");
            out.write("\t\t\t\ttry {\n");
            out.write("\t\t\t\t\tcon.commit();\n");                        
            out.write("\t\t\t\t\tcon.close();\n");
            out.write("\t\t\t\t} catch (Exception e) { ;}\n");
            out.write("\t\t}\n");
            out.write("\t\t}\n");
            out.write("\t}\n\n");

            out
            .write("\tpublic int insert (Connection con) throws SQLException, Exception {\n");
            out.write("\t\tStatement stmt = null;\n");
            out.write("\t\tint rowsInsert = 0;\n");
            out
            .write("\t\tString sql = \"Insert into \" "+
                      " + \"." + tableName + " " + insertColumn +
                      " values \"" + insertValue + "\";\n");
            out.write("\t\ttry {\n");
            out.write("\t\t\tstmt = con.createStatement();\n");
            out.write("\t\t\trowsInsert = stmt.executeUpdate(sql);\n");
            out.write("\t\t\tstmt.close();\n");
            out.write("\t\t} catch (SQLException sqle) {\n");
            out
            .write("\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                      "Bean.insert(Connection, String) : \" + sqle);\n");
            out.write("\t\t\tsqle.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow sqle;\n");
            out.write("\t\t}\n");
            out.write("\t\t\treturn rowsInsert;\n");
            out.write("\t}\n\n");

            out
            .write("\tpublic int deleteByPrimaryKey () throws SQLException, Exception {\n");
            out.write("\t\tConnection con = null;\n");
            out.write("\t\ttry {\n");
            out.write("\t\t\tcon = conDB.getConnection();\n");
            out.write("\t\t\treturn deleteByPrimaryKey(con);\n");
            out.write("\t\t} catch (SQLException sqle) {\n");
            out
            .write("\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                      "Bean.deleteByPrimaryKey() : \" + sqle);\n");
            out.write("\t\t\tsqle.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow sqle;\n");
            out.write("\t\t} catch (Exception e) {\n");
            out
            .write("\t\t\tSystem.out.println(\"Exception Error in " + tableName +
                      "Bean.deleteByPrimaryKey() : \" + e);\n");
            out.write("\t\t\te.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow e;\n");
            out.write("\t\t} finally {\n");
            out.write("\t\t\tif (con != null) {\n");
            out.write("\t\t\t\ttry {\n");
            out.write("\t\t\t\t\tcon.commit();\n");                        
            out.write("\t\t\t\t\tcon.close();\n");
            out.write("\t\t\t\t} catch (Exception e) { ;}\n");
            out.write("\t\t\t}\n");
            out.write("\t\t}\n");
            out.write("\t}\n\n");

            out
            .write("\tpublic int deleteByPrimaryKey (Connection con) throws SQLException, Exception {\n");
            out.write("\t\tStatement stmt = null;\n");
            out.write("\t\tint rowsDelete = 0;\n");
            out
            .write("\t\tString sql = \"Delete from \" " +
                      " + \"." + tableName + " \";\n");
            out.write("\t\ttry {\n");
            out.write("\t\t\tif (chkPrimaryKey()) {\n");
            out.write("\t\t\t\tstmt = con.createStatement();\n");
            out
            .write("\t\t\t\trowsDelete = stmt.executeUpdate(sql + primaryKeyWhere);\n");
            out.write("\t\t\t\tstmt.close();\n");
            out.write("\t\t\t}\n");
            out.write("\t\t} catch (SQLException sqle) {\n");
            out
            .write("\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                      "Bean.deleteByPrimaryKey(Connection) : \" + sqle);\n");
            out.write("\t\t\tsqle.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow sqle;\n");
            out.write("\t\t} catch (Exception e) {\n");
            out
            .write("\t\t\tSystem.out.println(\"Exception Error in " + tableName +
                      "Bean.deleteByPrimaryKey(Connection) : \" + e);\n");
            out.write("\t\t\te.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow e;\n");
            out.write("\t\t}\n");
            out.write("\t\treturn rowsDelete;\n");
            out.write("\t}\n\n");
            
            out
            .write("\tpublic int deleteWhere (Connection con, String where) throws SQLException, Exception {\n");
            out.write("\t\tStatement stmt = null;\n");
            out.write("\t\tint rowsDelete = 0;\n");
            out
            .write("\t\tString sql = \"Delete from \" " +
                      " + \"." + tableName + " \";\n");
            out.write("\t\ttry {\n");
            //out.write("\t\t\tif (chkPrimaryKey()) {\n");
            out.write("\t\t\t\tstmt = con.createStatement();\n");
            out
            .write("\t\t\t\trowsDelete = stmt.executeUpdate(sql + where);\n");
            out.write("\t\t\t\tstmt.close();\n");
            //out.write("\t\t\t}\n");
            out.write("\t\t} catch (SQLException sqle) {\n");
            out
            .write("\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                      "Bean.deleteWhere(Connection) : \" + sqle);\n");
            out.write("\t\t\tsqle.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow sqle;\n");
            out.write("\t\t} catch (Exception e) {\n");
            out
            .write("\t\t\tSystem.out.println(\"Exception Error in " + tableName +
                      "Bean.deleteWhere(Connection) : \" + e);\n");
            out.write("\t\t\te.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow e;\n");
            out.write("\t\t}\n");
            out.write("\t\treturn rowsDelete;\n");
            out.write("\t}\n\n");

            out
            .write("\tpublic int updateByPrimaryKey () throws SQLException, Exception {\n");
            out.write("\t\tConnection con = null;\n");
            out.write("\t\ttry {\n");
            out.write("\t\t\tcon = conDB.getConnection();\n");
            out.write("\t\t\treturn updateByPrimaryKey(con);\n");
            out.write("\t\t} catch (SQLException sqle) {\n");
            out
            .write("\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                      "Bean.updateByPrimaryKey() : \" + sqle);\n");
            out.write("\t\t\tsqle.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow sqle;\n");
            out.write("\t\t} catch (Exception e) {\n");
            out
            .write("\t\t\tSystem.out.println(\"Exception Error in " + tableName +
                      "Bean.updateByPrimaryKey() : \" + e);\n");
            out.write("\t\t\te.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow e;\n");
            out.write("\t\t} finally {\n");
            out.write("\t\t\tif (con != null) {\n");
            out.write("\t\t\t\ttry {\n");
            out.write("\t\t\t\t\tcon.commit();\n");                        
            out.write("\t\t\t\t\tcon.close();\n");
            out.write("\t\t\t\t} catch (Exception e) { ;}\n");
            out.write("\t\t\t}\n");
            out.write("\t\t}\n");
            out.write("\t}\n\n");

            out
            .write("\tpublic int updateByPrimaryKey (Connection con) throws SQLException, Exception {\n");
            out.write("\t\tString colUpdate = \"" + updateColumn + ";\n");
            out.write("\t\ttry {\n");
            out.write("\t\t\tif (chkPrimaryKey()) {\n");
            out
            .write("\t\t\t\treturn updateByColumn(con, colUpdate, primaryKeyWhere);\n");
            out.write("\t\t\t} else {\n");
            out.write("\t\t\t\treturn 0;\n");
            out.write("\t\t\t}\n");
            out.write("\t\t} catch (SQLException sqle) {\n");
            out
            .write("\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                      "Bean.updateByPrimaryKey(Connection) : \" + sqle);\n");
            out.write("\t\t\tsqle.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow sqle;\n");
            out.write("\t\t} catch (Exception e) {\n");
            out
            .write("\t\t\tSystem.out.println(\"Exception Error in " + tableName +
                      "Bean.updateByPrimaryKey(Connection) : \" + e);\n");
            out.write("\t\t\te.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow e;\n");
            out.write("\t\t}\n");
            out.write("\t}\n\n");

            out
            .write("\tpublic int updateByColumn (String colUpdate, String where) throws SQLException, Exception {\n");
            out.write("\t\tConnection con = null;\n");
            out.write("\t\ttry {\n");
            out.write("\t\t\tcon = conDB.getConnection();\n");
            out.write("\t\t\treturn updateByColumn(con, colUpdate, where);\n");
            out.write("\t\t} catch (SQLException sqle) {\n");
            out
            .write("\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                      "Bean.updateByColumn(String, String) : \" + sqle);\n");
            out.write("\t\t\tsqle.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow sqle;\n");
            out.write("\t\t} catch (Exception e) {\n");
            out
            .write("\t\t\tSystem.out.println(\"Exception Error in " + tableName +
                      "Bean.updateByColumn(String, String) : \" + e);\n");
            out.write("\t\t\te.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow e;\n");
            out.write("\t\t} finally {\n");
            out.write("\t\t\tif (con != null) {\n");
            out.write("\t\t\t\ttry {\n");
            out.write("\t\t\t\t\tcon.commit();\n");                        
            out.write("\t\t\t\t\tcon.close();\n");
            out.write("\t\t\t\t} catch (Exception e) { ;}\n");
            out.write("\t\t\t}\n");
            out.write("\t\t}\n");
            out.write("\t}\n\n");

            out
            .write("\tpublic int updateByColumn (Connection con, String colUpdate, String where) throws SQLException, Exception {\n");
            out.write("\t\tStatement stmt = null;\n");
            out.write("\t\tint rowsUpdate = 0;\n");
            out
            .write("\t\tString sql = \"Update \" " +
                      " + \"." + tableName +
                      " set \" + colUpdate + \" \" + where;\n");
            out.write("\t\ttry {\n");
            out.write("\t\t\tstmt = con.createStatement();\n");
            out.write("\t\t\trowsUpdate = stmt.executeUpdate(sql);\n");
            out.write("\t\t\tstmt.close();\n");
            out.write("\t\t} catch (SQLException sqle) {\n");
            out
            .write("\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                      "Bean.updateByColumn(Connection, String, String) : \" + sqle);\n");
            out.write("\t\t\tsqle.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow sqle;\n");
            out.write("\t\t} catch (Exception e) {\n");
            out
            .write("\t\t\tSystem.out.println(\"Exception Error in " + tableName +
                      "Bean.updateByColumn(Connection, String, String) : \" + e);\n");
            out.write("\t\t\te.printStackTrace(System.out);\n");
            out.write("\t\t\tthrow e;\n");
            out.write("\t\t}\n");
            out.write("\t\treturn rowsUpdate;\n");
            out.write("\t}\n\n");
//          New Update
          out.write("\t/**\n");
          out.write("\t* get record between row 1 to row n from database \n");
          out.write("\t * return ArraList\n");
          out.write("\t * */\n");
          out.write("\tpublic ArrayList executeQuerybetweenRec(String where,String beginrec , String endrec , boolean autoCommit) throws SQLException, Exception {\n");
          out.write("\t\tConnection con = null;\n");
          out.write("\t\tString strorder = \"\";\n");
          out.write("\t\ttry {\n");
          out.write("\t\t\tcon = conDB.getConnection();\n");
          out.write("\t\t\t executeQuerybetweenRec(con,where,beginrec ,endrec,strorder,autoCommit);\n");
          out.write("\t\t} catch (SQLException sqle) {\n");
          out.write("\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                    "Bean.selectWhere(String) : \" + sqle);\n");
          out.write("\t\t\tsqle.printStackTrace(System.out);\n");
          out.write("\t\t\tthrow sqle;\n");
          out.write("\t\t} catch (Exception e) {\n");
          out.write("\t\t\tSystem.out.println(\"Exception Error in " + tableName +
                    "Bean.selectWhere(String) : \" + e);\n");
          out.write("\t\t\te.printStackTrace(System.out);\n");
          out.write("\t\t\tthrow e;\n");
          out.write("\t\t} finally {\n");
          out.write("\t\t\tif (con != null) {\n");
          out.write("\t\t\t\ttry {\n");
          out.write("\t\t\t\t\tcon.close();\n");
          out.write("\t\t\t\t} catch (Exception e) { ;}\n");
          out.write("\t\t\t}\n");
          out.write("\t\t}\n");
          out.write("\t\treturn this."+tableName + "List;\n");
          out.write("\t}\n\n");
          
          out.write("\t/**\n");
          out.write("\t* get record between row 1 to row n from database \n");
          out.write("\t * return ArraList\n");
          out.write("\t * */\n");
          out.write("\tpublic ArrayList executeQuerybetweenRec(String where,String beginrec , String endrec , String strorder , boolean autoCommit) throws SQLException, Exception {\n");
          out.write("\t\tConnection con = null;\n");
          out.write("\t\ttry {\n");
          out.write("\t\t\tcon = conDB.getConnection();\n");
          out.write("\t\t\t executeQuerybetweenRec(con,where,beginrec ,endrec,strorder,autoCommit);\n");
          out.write("\t\t} catch (SQLException sqle) {\n");
          out.write("\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                    "Bean.selectWhere(String) : \" + sqle);\n");
          out.write("\t\t\tsqle.printStackTrace(System.out);\n");
          out.write("\t\t\tthrow sqle;\n");
          out.write("\t\t} catch (Exception e) {\n");
          out.write("\t\t\tSystem.out.println(\"Exception Error in " + tableName +
                    "Bean.selectWhere(String) : \" + e);\n");
          out.write("\t\t\te.printStackTrace(System.out);\n");
          out.write("\t\t\tthrow e;\n");
          out.write("\t\t} finally {\n");
          out.write("\t\t\tif (con != null) {\n");
          out.write("\t\t\t\ttry {\n");
          out.write("\t\t\t\t\tcon.close();\n");
          out.write("\t\t\t\t} catch (Exception e) { ;}\n");
          out.write("\t\t\t}\n");
          out.write("\t\t}\n");
          out.write("\t\treturn this."+tableName + "List;\n");
          out.write("\t}\n\n");
          
          out.write("\t/**\n");
          out.write("\t* get record between row 1 to row n from database \n");
          out.write("\t * return ArraList\n");
          out.write("\t * */\n");
          out.write("\tpublic ArrayList executeQuerybetweenRec (Connection con,String where , String beginrec , String endrec , String strorder ,boolean autoCommit) throws SQLException, Exception {\n");
          out.write("\t\tStatement stmt = null;\n");
          out.write("\t\tResultSet rs   = null;\n");
//          out.write("\t\tString sql = \"Select * from (select c.*,ROW_NUMBER() OVER(\"+strorder+\") as RN from \"+conDB.schemaEGPMERCHANT+\"." + tableName + " as c \"+where+\") as x where RN between \"+ beginrec + \" and \"+ endrec;\n\n");
          out.write("\t\tString sql = \"Select * from (select c.*,ROW_NUMBER() OVER(\"+strorder+\") as RN from \" + tableName + \" as c \"+where+\") as x where RN between \"+ beginrec + \" and \"+ endrec;\n\n");         
          out.write("\t\ttry {\n");
          out.write("\t\t\tif (" + tableName + "List.isEmpty() == false)\n");
          out.write("\t\t\t\t" + tableName + "List.clear();\n");
          out.write("\t\t\tif (" + tableName + "List.isEmpty() == false)\n");
          out.write("\t\t\t\t" + tableName + "List.clear();\n");
          out.write("\t\t\tstmt = con.createStatement();\n");
          out.write("\t\t\trs = stmt.executeQuery(sql);\n");
          out.write("\t\t\twhile (rs.next()) {\n");
          out.write("\t\t\t\taddToArrayList(rs);\n");
          out.write("\t\t\t}\n");
			out.write("\t\t\trs.close();\n");
          out.write("\t\t\tstmt.close();\n");
          out.write("\t\t\tif(autoCommit) {\n");
          out.write("\t\t\t\tcon.commit();\n");
          out.write("\t\t\t}\n");
          out.write("\t\t} catch (SQLException sqle) {\n");
          out.write("\t\t\trs.close();\n");
          out.write("\t\t\tstmt.close();\n");
          out.write("\t\t\tif(autoCommit) {\n");
          out.write("\t\t\t\tcon.rollback();\n");
          out.write("\t\t\t}\n");
			out.write("\t\t\tSystem.out.println(\"SQLException SQL : \" + sql);\n");
          out.write("\t\t\tSystem.out.println(\"SQLException Error in " + tableName +
                    "EBean.selectWhere(Connection, String) : \" + sqle);\n");
          out.write("\t\t\tsqle.printStackTrace(System.out);\n");
          out.write("\t\t\tthrow sqle;\n");
          out.write("\t\t} catch (Exception e) {\n");
          out.write("\t\t\trs.close();\n");
          out.write("\t\t\tstmt.close();\n");
          out.write("\t\t\tif(autoCommit) {\n");
          out.write("\t\t\t\tcon.rollback();\n");
          out.write("\t\t\t}\n");
          out.write("\t\t\tSystem.out.println(\"Exception Error in " + tableName +
                    "EBean.selectWhere(Connection, String) : \" + e);\n");
          out.write("\t\t\te.printStackTrace(System.out);\n");
          out.write("\t\t\tthrow e;\n");
          out.write("\t\t}\n");
			out.write("\t\treturn this."+tableName + "List;\n");
          out.write("\t}\n\n");
         
          out.write("\t/**\n");
          out.write("\t* select count or max from... where... \n");
          out.write("\t * return ArraList\n");
          out.write("\t * */\n");
  		out.write("\tpublic ArrayList executeQueryAdvSql(String sql) throws SQLException, Exception {\n");
  		out.write("\t\tConnection con = null;\n");
  		out.write("\t\tArrayList list = new ArrayList();\n");
  		out.write("\t\ttry {\n");
  		out.write("\t\t\t\tcon = conDB.getConnection();\n");
  		out.write("\t\t\t\tlist = executeQueryAdvSql(con, sql);\n");
  		out.write("\t\t	} catch (Exception e) {\n");
  		out.write("\t\t\t\tSystem.out.println(\"Exception Error in executeQueryAdvSql.executeQuerymainAdv(String) : \" + e);\n");
  		out.write("\t\t\t\te.printStackTrace(System.out);\n");
  		out.write("\t\t\t\tthrow e;\n");
  		out.write("\t\t	} finally {\n");
  		out.write("\t\t	\tif (con != null) {\n");
  		out.write("\t\t	\ttry {\n");
  		out.write("\t\t	\t\tcon.close();\n");
  		out.write("\t\t	\t} catch (Exception e) { ;}\n");
  		out.write("\t\t	\t}\n");
  		out.write("\t\t	}\n");
  		out.write("\t\t	return list;\n");
  		out.write("\t}\n\n");
      	
  		out.write("\t/**\n");
          out.write("\t* select count or max from... where... \n");
          out.write("\t * return ArraList\n");
          out.write("\t * */\n");
  		out.write("\tpublic ArrayList executeQueryAdvSql(Connection con , String sql){	\n");
  		out.write("\t\t	Statement stmt = null;\n");
  		out.write("\t\t	ArrayList list = new ArrayList();\n");
  		out.write("\t\t	ResultSet rs = null;\n");
  		out.write("\t\t	ResultSetMetaData rsmd = null;\n");
  		out.write("\t\t	try{\n");
  		out.write("\t\t	\tstmt = con.createStatement();\n");
  		out.write("\t\t	\trs = stmt.executeQuery(sql);\n");
  		out.write("\t\t	\trsmd = rs.getMetaData();\n");
  		out.write("\t\t	\tint colCount = rsmd.getColumnCount();\n");
  		out.write("\t\t	\tString[] colNames = new String[colCount];\n");
  		out.write("\t\t	\tString toCap = null;\n");
  		out.write("\t\t	\tfor (int j = 1; j <= colCount; j++){\n");
  		out.write("\t\t\t\ttoCap = rsmd.getColumnName(j);\n");
  		out.write("\t\t\t\tcolNames[j-1] = toCap.toUpperCase();\n");
  		out.write("\t\t	\t}\n");
  		out.write("\t\t	\tint rowNum = 0;\n");
  		out.write("\t\t	\twhile(rs.next()){\n");
  		out.write("\t\t\t\t//Represent Row Object\n");
  		out.write("\t\t\t\tMap map = new HashMap();\n");
  		out.write("\t\t\t\tfor(int i = 1;i<= colCount;i++){\n");
  		out.write("\t\t\t\t\tObject obj = rs.getObject(i);\n");
  		out.write("\t\t\t\t\tif(obj != null){\n");
  		out.write("\t\t\t\t\t\tif (obj instanceof Date) {\n");
  		out.write("\t\t\t\t\t\tobj = Tools.chkNull(rs.getString(i));\n");
  	    out.write("\t\t\t\t\t}\n");
  	    out.write("\t\t\t\t\tmap.put(colNames[i-1],obj);\n");
  	    out.write("\t\t\t\t\t}else{\n");
  	    out.write("\t\t\t\t\tmap.put(colNames[i-1],\"\");\n");
  	    out.write("\t\t\t\t\t}\n");
  	    out.write("\t\t\t\t}\n");
  	    out.write("\t\t\t\tlist.add(rowNum,map);\n");
  	    out.write("\t\t\t\t//Represent Table Object\n");
  	    out.write("\t\t\t\trowNum++;\n");
  		out.write("\t\t	\t}//End Records\n");
  		out.write("\t\t	}catch(Exception e){\n");
  		out.write("\t\t	\tSystem.out.println(\"Exception Error in executeQueryAdvSql.executeQueryAdv(String) : \" + e);\n");
  		out.write("\t\t	}finally{\n");
  		out.write("\t\t	\ttry{\n");
  		out.write("\t\t	\t\tif (rs != null) rs.close();\n");
  		out.write("\t\t	\t\tif (stmt != null) stmt.close();\n");
  		out.write("\t\t	\t}catch(Exception ex){}\n");
  		out.write("\t\t	}\n");
  		out.write("\t\t	return list;\n");
  		out.write("\t}\n");
  		
  		out.write("\t/**\n");
  		out.write("\t * Ex =  select cusid , cusname , cursurname from x.a1 where offcode='xxxx'\n");
  		out.write("\t * return ArraList\n");
  		out.write("\t * */\n");
  		out.write("\tpublic ArrayList executeQuerymainAdv(String table ,String where) throws SQLException, Exception {\n");
  		out.write("\t\tConnection con = null;\n");
  		out.write("\t\tArrayList list = new ArrayList();\n");
  		out.write("\t\ttry {\n");
  		out.write("\t\t\t\tcon = conDB.getConnection();\n");
  		out.write("\t\t\t\tlist = executeQuerymainAdv(con,table, where);\n");
  		out.write("\t\t	} catch (Exception e) {\n");
  		out.write("\t\t\t\tSystem.out.println(\"Exception Error in executeQueryAdvSql.executeQuerymainAdv(String) : \" + e);\n");
  		out.write("\t\t\t\te.printStackTrace(System.out);\n");
  		out.write("\t\t\t\tthrow e;\n");
  		out.write("\t\t	} finally {\n");
  		out.write("\t\t	\tif (con != null) {\n");
  		out.write("\t\t	\ttry {\n");
  		out.write("\t\t	\t\tcon.close();\n");
  		out.write("\t\t	\t} catch (Exception e) { ;}\n");
  		out.write("\t\t	\t}\n");
  		out.write("\t\t	}\n");
  		out.write("\t\t	return list;\n");
  		out.write("\t}\n\n");
  		
  		out.write("\t/**\n");
  		out.write("\t * Ex =  select cusid , cusname , cursurname from x.a1 where offcode='xxxx'\n");
  		out.write("\t * return ArraList\n");
  		out.write("\t * */\n");
  		out.write("\tpublic ArrayList executeQuerymainAdv(Connection con , String table ,String where){	\n");
  		out.write("\t\t	Statement stmt = null;\n");
  		out.write("\t\t	ArrayList list = new ArrayList();\n");
  		out.write("\t\t	ResultSet rs = null;\n");
  		out.write("\t\t	ResultSetMetaData rsmd = null;\n");
  		out.write("\t\t	try{\n");
  		out.write("\t\t	\tstmt = con.createStatement();\n");
  		out.write("\t\t	\tString sql = \"select * from \"+table+\" \"+where ;\n");
  		out.write("\t\t	\trs = stmt.executeQuery(sql);\n");
  		out.write("\t\t	\trsmd = rs.getMetaData();\n");
  		out.write("\t\t	\tint colCount = rsmd.getColumnCount();\n");
  		out.write("\t\t	\tString[] colNames = new String[colCount];\n");
  		out.write("\t\t	\tString toCap = null;\n");
  		out.write("\t\t	\tfor (int j = 1; j <= colCount; j++){\n");
  		out.write("\t\t\t\ttoCap = rsmd.getColumnName(j);\n");
  		out.write("\t\t\t\tcolNames[j-1] = toCap.toUpperCase();\n");
  		out.write("\t\t	\t}\n");
  		out.write("\t\t	\tint rowNum = 0;\n");
  		out.write("\t\t	\twhile(rs.next()){\n");
  		out.write("\t\t\t\t//Represent Row Object\n");
  		out.write("\t\t\t\tMap map = new HashMap();\n");
  		out.write("\t\t\t\tfor(int i = 1;i<= colCount;i++){\n");
  		out.write("\t\t\t\t\tObject obj = rs.getObject(i);\n");
  		out.write("\t\t\t\t\tif(obj != null){\n");
  		out.write("\t\t\t\t\t\tif (obj instanceof Date) {\n");
  		out.write("\t\t\t\t\t\tobj = Tools.chkNull(rs.getString(i));\n");
  	    out.write("\t\t\t\t\t}\n");
  	    out.write("\t\t\t\t\tmap.put(colNames[i-1],obj);\n");
  	    out.write("\t\t\t\t\t}else{\n");
  	    out.write("\t\t\t\t\tmap.put(colNames[i-1],\"\");\n");
  	    out.write("\t\t\t\t\t}\n");
  	    out.write("\t\t\t\t}\n");
  	    out.write("\t\t\t\tlist.add(rowNum,map);\n");
  	    out.write("\t\t\t\t//Represent Table Object\n");
  	    out.write("\t\t\t\trowNum++;\n");
  		out.write("\t\t	\t}//End Records\n");
  		out.write("\t\t	}catch(Exception e){\n");
  		out.write("\t\t	\tSystem.out.println(\"Exception Error in executeQueryAdvSql.executeQuerymainAdv(String) : \" + e);\n");
  		out.write("\t\t	}finally{\n");
  		out.write("\t\t	\ttry{\n");
  		out.write("\t\t	\t\tif (rs != null) rs.close();\n");
  		out.write("\t\t	\t\tif (stmt != null) stmt.close();\n");
  		out.write("\t\t	\t}catch(Exception ex){}\n");
  		out.write("\t\t	}\n");
  		out.write("\t\t	return list;\n");
  		out.write("\t}\n");
//         End Update
            out.write(getter);
            out.write(setter);

            out.write("}\n");
            out.flush();
            out.close();
        } catch (SQLException sqlE) {
            System.out.println("SQLException Error : " + sqlE);
            sqlE.printStackTrace(System.out);
        } catch (Exception e) {
            System.out.println("Exception Error : " + e);
            e.printStackTrace(System.out);
        } finally {
            try {
                if (con != null) {
                   // con.commit();
                    con.close();
                }
            } catch (Exception sqlE) {
                System.out.println("Close Connection Error : " + sqlE);
            }
        }
    }

    private void getColumnInfo(ResultSetMetaData rsmd,
                               int index) throws Exception {
        String columnName = rsmd.getColumnName(index).toLowerCase();
        String columnNameNonUnderScore =
            chgUnderScoreToSpaceToFirstUp(columnName);
        String dataType = null;
        String defaultValue = null;
        String getter1 = null;
        String getter2 = null;

        try {
            switch (rsmd.getColumnType(index)) {
            case Types.TINYINT :
            case Types.SMALLINT :
            case Types.INTEGER :
                dataType = "int";
                defaultValue = "0";
                break;

            case Types.FLOAT :
            case Types.REAL :
            case Types.DOUBLE :
            case Types.NUMERIC :
            case Types.DECIMAL :
                if (rsmd.getPrecision(index) == 38) {
                    dataType = "int";
                    defaultValue = "0";
                } else {
                    dataType = "double";
                    defaultValue = "0.0";
                }
                break;

            case Types.CHAR :
            case Types.VARCHAR :
            case Types.DATE :
            case Types.TIME :
            case Types.TIMESTAMP :
                dataType = "String";
                defaultValue = "\"\"";
                break;

            default :
                break;
            }

            getter1 =
                "\tpublic " + dataType + "[] get" + chgFirstCharToUpperCase(columnNameNonUnderScore) +
                "() {\n" + "\t\tint records = getSize();\n" + "\t\t" +
                dataType + " col[] = new " + dataType + "[records];\n\n" +
                "\t\tfor (int at=0; at<records; at++) {\n" +
                "\t\t\tString row[] = (String[])" + tableName +
                "List.get(at);\n";
            getter2 =
                "\tpublic " + dataType + " get" + chgFirstCharToUpperCase(columnNameNonUnderScore) +
                "At(int at) {\n" +
                "\t\tif ((at >= getSize()) || (getSize() == 0))\n" +
                "\t\t\treturn " + defaultValue + ";\n" +
                "\t\tString row[] = (String[])" + tableName +
                "List.get(at);\n";

            if (insertValue.compareTo("") == 0)
                insertValue = " + \"(";
            else
                insertValue += " + \", ";

            if (updateColumn.compareTo("") == 0)
                //                updateColumn = columnName + " = \" + this." + columnNameNonUnderScore;
                updateColumn = columnName + " = ";
            else
                //                updateColumn += " + \", " + columnName + " = \" + this." + columnNameNonUnderScore;
                updateColumn += " + \", " + columnName + " = ";

            switch (rsmd.getColumnType(index)) {
            case Types.TINYINT :
            case Types.SMALLINT :
            case Types.INTEGER :
                getter1 +=
                    "\t\t\tcol[at] = (new Integer(row[" + columnNameNonUnderScore +
                    "Pos])).intValue();\n\t\t}\n\t\treturn col;\n\t}\n";
                getter2 +=
                    "\t\treturn (new Integer(row[" + columnNameNonUnderScore +
                    "Pos])).intValue();\n\t}\n";
                insertValue += "\" + this." + columnNameNonUnderScore;
                updateColumn += "\" + this." + columnNameNonUnderScore;
                setter +=
                    "\tpublic void set" + chgFirstCharToUpperCase(columnNameNonUnderScore) +
                    "(" + dataType + " " + columnNameNonUnderScore + ") {\n" +
                    "\t\t\tthis." + columnNameNonUnderScore + " = " +
                    columnNameNonUnderScore + ";\n\t}\n\n";
                break;

            case Types.FLOAT :
            case Types.REAL :
            case Types.DOUBLE :
            case Types.NUMERIC :
            case Types.DECIMAL :
                if (rsmd.getPrecision(index) == 38) {
                    getter1 +=
                        "\t\t\tcol[at] = (new Integer(Tools.chkNullToZero(row[" + columnNameNonUnderScore +
                        "Pos]))).intValue();\n\t\t}\n\t\treturn col;\n\t}\n";
                    getter2 +=
                        "\t\treturn (new Integer(Tools.chkNullToZero(row[" + columnNameNonUnderScore +
                        "Pos]))).intValue();\n\t}\n";
                    insertValue += "\" + this." + columnNameNonUnderScore;
                    updateColumn += "\" + this." + columnNameNonUnderScore;
                } else {
                    getter1 +=
                        "\t\t\tcol[at] = (new Double(Tools.chkNullToZero(row[" + columnNameNonUnderScore +
                        "Pos]))).doubleValue();\n\t\t}\n\t\treturn col;\n\t}\n";
                    getter2 +=
                        "\t\treturn (new Double(Tools.chkNullToZero(row[" + columnNameNonUnderScore +
                        "Pos]))).doubleValue();\n\t}\n";
                    insertValue += "\" + this." + columnNameNonUnderScore;
                    updateColumn += "\" + this." + columnNameNonUnderScore;
                }
                setter +=
                    "\tpublic void set" + chgFirstCharToUpperCase(columnNameNonUnderScore) +
                    "(" + dataType + " " + columnNameNonUnderScore + ") {\n" +
                    "\t\t\tthis." + columnNameNonUnderScore + " = " +
                    columnNameNonUnderScore + ";\n\t}\n\n";
                break;

            case Types.DATE :
            case Types.TIME :
            case Types.TIMESTAMP :
                getter1 +=
                    "\t\t\tcol[at] = row[" + columnNameNonUnderScore + "Pos];\n\t\t}\n\t\treturn col;\n\t}\n";
                getter2 +=
                    "\t\treturn row[" + columnNameNonUnderScore + "Pos];\n\t}\n";
                insertValue +=
                    "\" + Tools.dateToDb(this." + columnNameNonUnderScore +
                    ")";
                updateColumn +=
                    "\" + Tools.dateToDb(this." + columnNameNonUnderScore +
                    ")";
                setter +=
                    "\tpublic void set" + chgFirstCharToUpperCase(columnNameNonUnderScore) +
                    "(" + dataType + " " + columnNameNonUnderScore + ") {\n" +
                    "\t\tif (" + columnNameNonUnderScore + " == null) {\n" +
                    "\t\t\tthis." + columnNameNonUnderScore + " = " +
                    columnNameNonUnderScore + ";\n" + "\t\t} else {\n" +
                    "\t\t\tthis." + columnNameNonUnderScore + " = \"'\" + " +
                    columnNameNonUnderScore + " + \"'\";\n" + "\t\t}\n" +
                    "\t}\n\n";
                break;

            default :
                getter1 +=
                    "\t\t\tcol[at] = row[" + columnNameNonUnderScore + "Pos];\n\t\t}\n\t\treturn col;\n\t}\n";
                getter2 +=
                    "\t\treturn row[" + columnNameNonUnderScore + "Pos];\n\t}\n";
                insertValue += "\" + this." + columnNameNonUnderScore;
                updateColumn += "\" + this." + columnNameNonUnderScore;
                setter +=
                    "\tpublic void set" + chgFirstCharToUpperCase(columnNameNonUnderScore) +
                    "(" + dataType + " " + columnNameNonUnderScore + ") {\n" +
                    "\t\tif (" + columnNameNonUnderScore + " == null) {\n" +
                    "\t\t\tthis." + columnNameNonUnderScore + " = " +
                    columnNameNonUnderScore + ";\n" + "\t\t} else {\n" +
                    "\t\t\tthis." + columnNameNonUnderScore + " = \"'\" + " +
                    columnNameNonUnderScore + " + \"'\";\n" + "\t\t}\n" +
                    "\t}\n\n";
                break;
            }

            if (insertColumn.compareTo("") == 0)
                insertColumn = "(" + columnName;
            else
                insertColumn += ", " + columnName;

            addValue +=
                "\t\trow[" + columnNameNonUnderScore + "Pos] = Tools.chkNull(rs.getString(\"" +
                columnName + "\"));\n";

            declaration +=
                "\tprivate " + dataType + " " + columnNameNonUnderScore +
                ";\n";
            position +=
                "\tprivate int " + columnNameNonUnderScore + "Pos = " + (index -
                                                                                 1) +
                ";\n";
            getter += getter1 + getter2;
        } catch (Exception e) {
            System.out
            .println("Error in DataTranfer.getValue: " + e.getMessage());
            throw (e);
        }
    }

    private String chgUnderScoreToSpaceToFirstUp(String sValue) {
        String sTmpValue = "", sTmpValue1 = "";
        int iCount = 0;
        StringTokenizer sTValue = new StringTokenizer(sValue, "_");
        iCount = sTValue.countTokens();
        for (int i = 0; i < iCount; i++) {
            sTmpValue1 = sTValue.nextToken();
            if (i == 0) {
                sTmpValue = sTmpValue1;
            } else {
                if (sTmpValue1.length() == 1) {
                    sTmpValue += sTmpValue1.toUpperCase();
                } else {
                    sTmpValue +=
                        (sTmpValue1.substring(0, 1).toUpperCase() + sTmpValue1
                                  .substring(1));
                }
            }
        }
        if (sTmpValue.compareTo("") == 0) {
            sTmpValue = sValue;
        }
        return sTmpValue;
    }

    private String chgFirstCharToUpperCase(String sValue) {
        String sTmpValue = "";
        if (sValue != null) {
            sTmpValue =
                sValue.substring(0, 1).toUpperCase() + sValue.substring(1);
        }
        return sTmpValue;
    }

/**
    private String chgStrToUpperCase(String sValue, int iIndex) {
        String sTmpValue = "";
        if (sValue != null) {
            if (iIndex <= sValue.length()) {
                sTmpValue =
                    sValue.substring(0, iIndex).toUpperCase() + sValue.substring(1);
            }
        }
        return sTmpValue;
    }
*/
}
