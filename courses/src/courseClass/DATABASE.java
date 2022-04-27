package courseClass;

import courseClass.*;

import com.mysql.jdbc.*;
import com.mysql.cj.*;

import java.sql.*;
import java.util.logging.*;
import javax.swing.*;


public class DATABASE {
    private Connection conn;
    private Statement statement1;
    private Statement statement2;
    private String sql1;
    private String sql2;
    private ResultSet rs;
    private PreparedStatement pst;
    
    //intialize Database
    public DATABASE(){}
    public DATABASE(String _userName,String _dbName)
    {
        startDatabase (_userName,_dbName);
    }
    
    public void startDatabase(String _userName,String _dbName)
    {
        try{    
//            String url = "jdbc:mysql://localhost:3306/";
            String url = "jdbc:mysql://localhost:3306/courses?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String dbName = _dbName;
          //  String driver = "com.mysql.jdbc.Driver";
            String driver = "com.mysql.cj.jdbc.Driver";
            String userName = _userName;
            String password = "";
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url+dbName, userName, password);
            System.out.println("Connection Successful!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Connection Not Successful!");
        } catch (SQLException ex) {
            System.out.println("Connection Not Successful!");
        }
    }
    
    public ResultSet resultSetOfPosts()
    {
        try {
            statement1 = (Statement) conn.createStatement();
            sql1 = "select * from posts";
            return rs = statement1.executeQuery(sql1);
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        return rs;
    }
    
    public ResultSet resultSetOfStudents()
    {
        try {
            statement1 = (Statement) conn.createStatement();
            sql1 = "select * from students_information";
            rs = statement1.executeQuery(sql1);
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        return rs;
    }
    
    public PreparedStatement ps()
    {
        try {
            statement2 = (Statement) conn.createStatement();
            sql2 = "INSERT INTO `posts`(subject,contetnt) VALUES (?,?)";
            return pst = conn.prepareStatement(sql2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    
    public int insertPost(PreparedStatement pst, String val1, String val2)
    {
        try{ 
            pst.setString(1, val1);
            pst.setString(2, val2);
            return pst.executeUpdate();
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        return 0;
    }
    
    public String getPostString(ResultSet rs, String ColName)
    {
        try {
            return rs.getString(ColName);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    
    public int getPostID(ResultSet rs)
    {
        try {
            return rs.getInt("ID");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return 0;
    }
    
    public String getStudentString(ResultSet rs, String ColName)
    {
        try {
            return rs.getString(ColName);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    
    public int getStudentGrades(ResultSet rs, String ColName)
    {
        try {
            return rs.getInt(ColName);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return 0;
    }
    
    public int getStudentID(ResultSet rs)
    {
        try {
            return rs.getInt("PHONE");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return 0;
    }
    
    public String getSubject(int id)
    {
        try {
            statement1 = (Statement) conn.createStatement();
            String sql = "select * from posts where ID = '"+id+"'";
            rs = statement1.executeQuery(sql);
            while(rs.next()){
                return rs.getString("subject");
            }
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        return null;
    }
    
    public String getcontent(int id)
    {
        try {
            statement1 = (Statement) conn.createStatement();
            String sql = "select * from posts where ID = '"+id+"'";
            ResultSet rs = statement1.executeQuery(sql);
            while(rs.next()){
                return rs.getString("contetnt");
            }
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        return null;
    }
    
    public void updatesubj(String value ,int id)
    {
        try {
            String sql = "update posts set subject = ? where ID = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, value);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex, "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    
    public void updatecont(String value ,int id)
    {
        try {
            String sql = "update posts set contetnt = ? where ID = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, value);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex, "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    
    public void updateGrades(int value ,String name)
    {
        try {
            String sql = "update students_information set GRADE = ? where FULL_NAME = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, value);
            pst.setString(2, name);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex, "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    
    public void updateGrades(int id)
    {
        try {
            String sql = "update students_information set GRADE = ? where PHONE = '"+id+"'";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "0");
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex, "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    
    public void deleted(int id)
    {
        try {
            statement1 = (Statement) conn.createStatement();
            sql1 = "DELETE FROM `posts` WHERE ID = '"+id+"'";
            int rs = statement1.executeUpdate(sql1);
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    public java.sql.Statement CreateConnection(String forName , String GConnection , String UserName ,String Pass) throws ClassNotFoundException, SQLException{
        java.sql.Connection c = null ;
        try {
            Class.forName(forName);
            c = DriverManager.getConnection(GConnection, UserName, Pass);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return c.createStatement();
    }
    
    public int InsertIntoParentCourse (java.sql.Statement stat , String Column1 , String Column2 , String Column3 ) throws SQLException{
        String sql = "insert into parent_course (ParentCourseName,ChildCourseName,IdParentCourse) values"
            + "('"+ Column1 +"',"
            +  "'"+ Column2 +"',"
            + "'"+Integer.parseInt(Column3)+"')" ;
        return stat.executeUpdate(sql) ;
    }
    
    public int SupdateParentCourse(java.sql.Statement stat ,int column , String NewValue , String id ) throws SQLException{
        String sql = "" ;
        if(column == 0){
            sql = "update parent_course set ParentCourseName = '"+ NewValue +"' where IdParentCourse = '"+id+"'";
        }
        else if(column == 1){
            sql = "update parent_course set IdParentCourse = '"+ NewValue +"' where IdParentCourse = '"+id+"'";
        }
        else if(column == 2){
            sql = "update parent_course set ChildCourseName = '"+ NewValue +"' where IdParentCourse = '"+id+"'";
        }
        return stat.executeUpdate(sql) ;
    }
    
    public int SupInsertIntoInstractor(java.sql.Statement stat , String FullName , String age ,
            String phn , String hphn , String Adress , String course) throws SQLException{
        
           return stat.executeUpdate("insert into instractor_information (FULL_NAME,AGE,PHONE,HOMEPHONE,ADDRESS,COURS)"
        + "values('"+ FullName +"',"
               + "'"+ age +"',"
               + "'"+ phn +"',"
               + "'"+ hphn +"',"
               + "'"+ Adress +"',"
               + "'"+ course +"')") ;
    }
    
    public static int InsertCondition( java.sql.Statement stat , int CurrentColumn , String FLNM , String AGE , String PHN , String HPHN , String ADDR , String CONM , JTextField NEWVALUETXT ) throws SQLException{
        String sql = "" ;
        if(CurrentColumn == 0){
            sql = "insert into instractor_information (FULL_NAME,AGE,PHONE,HOMEPHONE,ADDRESS,COURS)"
            +  "values('"+ NEWVALUETXT.getText() +"',"
                    + "'"+ AGE  +"',"
                    + "'"+ PHN  +"',"
                    + "'"+ HPHN +"',"
                    + "'"+ ADDR +"',"
                    + "'"+ CONM +"')" ;
        }
        else if(CurrentColumn == 1){
            sql = "insert into instractor_information (FULL_NAME,AGE,PHONE,HOMEPHONE,ADDRESS,COURS)"
            +  "values('"+ FLNM +"',"
                    + "'"+ NEWVALUETXT.getText()  +"',"
                    + "'"+ PHN  +"',"
                    + "'"+ HPHN +"',"
                    + "'"+ ADDR +"',"
                    + "'"+ CONM +"')" ;
        }
        else if(CurrentColumn == 2){
            sql = "insert into instractor_information (FULL_NAME,AGE,PHONE,HOMEPHONE,ADDRESS,COURS)"
            +  "values('"+ FLNM +"',"
                    + "'"+ AGE  +"',"
                    + "'"+ NEWVALUETXT.getText()  +"',"
                    + "'"+ HPHN +"',"
                    + "'"+ ADDR +"',"
                    + "'"+ CONM +"')" ;
        }
        else if(CurrentColumn == 3){
            sql = "insert into instractor_information (FULL_NAME,AGE,PHONE,HOMEPHONE,ADDRESS,COURS)"
            +  "values('"+ FLNM +"',"
                    + "'"+ AGE  +"',"
                    + "'"+ PHN  +"',"
                    + "'"+ NEWVALUETXT.getText() +"',"
                    + "'"+ ADDR +"',"
                    + "'"+ CONM +"')" ;
        }
        else if(CurrentColumn == 4){
            sql = "insert into instractor_information (FULL_NAME,AGE,PHONE,HOMEPHONE,ADDRESS,COURS)"
            +  "values('"+ FLNM +"',"
                    + "'"+ AGE  +"',"
                    + "'"+ PHN  +"',"
                    + "'"+ HPHN +"',"
                    + "'"+ NEWVALUETXT.getText() +"',"
                    + "'"+ CONM +"')" ;
        }
        else if(CurrentColumn == 5){
            sql = "insert into instractor_information (FULL_NAME,AGE,PHONE,HOMEPHONE,ADDRESS,COURS)"
            +  "values('"+ FLNM +"',"
                    + "'"+ AGE  +"',"
                    + "'"+ PHN  +"',"
                    + "'"+ HPHN +"',"
                    + "'"+ ADDR +"',"
                    + "'"+ NEWVALUETXT.getText() +"')" ;
        }
        return stat.executeUpdate(sql);
    }
    
    public int InsertCourse(java.sql.Statement stat , String CoNa, String Bran,String Room,String nosa,String pric,String Mnth,String strt,String prnt,String EndC,String Inst) throws SQLException{ 
        return stat.executeUpdate("insert into courses_information (CourseName,Branch,Room,AllowedStudent,Price,period,Start,Parent,End,Instractor)"
                + "values('"+  CoNa +"',"
                        + "'"+ Bran +"',"
                        + "'"+ Room +"',"
                        + "'"+ nosa +"',"
                        + "'"+ pric +"',"
                        + "'"+ Mnth +"',"
                        + "'"+ strt +"',"
                        + "'"+ prnt +"',"
                        + "'"+ EndC +"',"
                        + "'"+ Inst +"')") ;
    }
    
    public int InsertUser(java.sql.Statement stat ,String Username ,String password ,String Type,String Path) throws SQLException{
        return stat.executeUpdate("insert into register(UserName,Password,Type,Path)values"
                        + "('" +Username+ "','"+password+"','"+Type+"','"+Path+"')") ;
    }
    /*================================ForAll===========================================*/
    public int UpdateWhere(java.sql.Statement stat, String tableName , String Column , String NewValue , String where , String value) throws SQLException{
        String sqQl = "update "+tableName+" set "+Column+" = "+NewValue+" where " + where + " = " + value ;
        return stat.executeUpdate(sqQl);
    }
    
    public ResultSet SelectAll(String TableName , java.sql.Statement stat) throws SQLException{
    //Select all rows and columns from table name
        return stat.executeQuery("select * from " + TableName) ;
    }
    
    public int Count (String tableName , java.sql.Statement stat){
        int count = 0 ;
        try {
            //always result set have execute query 
            //,,,,,,,,Select/get the count of rows from + table name
            ResultSet rs = stat.executeQuery("select count(*) from " + tableName );
            
            //////////////////////////////////
            rs.next();//what for
            count = rs.getInt(1) ;//what for 
            //////////////////////////////////
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return count ;
    }
    
    public int deleteWhere(java.sql.Statement stat , String tableName , String Where , String Value) throws SQLException{
        return stat.executeUpdate("Delete from "+tableName+" where "+Where+" = '"+ Value +"'") ;
    }
    
    public int insertAll(java.sql.Statement st , String tableName , String[] dbColumnName , String[] dbValue) throws SQLException{
        String SQL = "insert into " + tableName +"( ";
        for(int i = 0 ; i < dbColumnName.length ; i++){
            if(i != dbColumnName.length-1){
                SQL += dbColumnName[i] + " , " ;
            }
            else{
                SQL += dbColumnName[i] + " )values( " ;
            }
        }
        for(int i = 0 ; i < dbValue.length ; i++){
            if(i != dbValue.length-1){
                SQL += "'"+ dbValue[i] +"' ," ;
            }
            else{
                SQL += "'"+ dbValue[i] +"' )" ;
            }
        }
        return st.executeUpdate(SQL);
    }
    public ResultSet SelectColumnWhere(Statement st , String TableName , String Column , String Where , String Value) throws SQLException{
        return st.executeQuery("select "+Column+" from "+TableName+" where "+Where+" = '" + Value + "'" );
    }
    public int UpdateMoreThanColumn(Statement stat , JTextField []jt) throws SQLException{
        String sql = "Update students_information set (ADDRESS,PHONE,AGE,FULL_NAME) =( '"+jt[0].getText()+ "'" 
                                                        + ",'" + jt[1].getText() + "'" 
                                                        + ",'" + jt[2].getText() + "'" 
                                                        + ",'" + jt[3].getText() + "')"
                    + "where HOMEPHONE ='"+ jt[4].getText() +"'" ;
            return stat.executeUpdate(sql);
    }
}
