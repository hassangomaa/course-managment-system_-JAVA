/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home_pages;

import courseClass.*;


import adminFrames.*;
import adminFrames.NewUSer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author egypt
 */
public class RejesterClass {
    private String username ;
    private String Password ;
    private String Type ;
    private String FilePath ;
    private String Phone ;
    //cons database for this class
    private static DATABASE DB = new DATABASE();
    private static Function f = new Function(true);
    private static  String DBurl ="jdbc:mysql://localhost:3306/courses?zeroDateTimeBehavior=CONVERT_TO_NULL"; 
    private static String DBdriver ="com.mysql.cj.jdbc.Driver"; 
   static  Login_page sign = new Login_page();

    //the constructor 
    public RejesterClass()
    {
        
    }
    
    
    void setUserName(String Value){
        username = Value ; 
    }
    void setPassword(String Value){
        Password = Value ; 
    }
    void SetType(String Value){
        Type = Value ; 
    }
    void setPath(String Value){
        FilePath = Value ;
    }
    void SetPhone(String Value){
        Phone = Value ;
    }
    /*======================Get=====================*/
    String GetUserName(){
        return username ;
    }
    String GetPassword(){
        return Password;
    }
    String GetType(){
        return Type ; 
    }
    String GetPath(){
        return FilePath ;
    }
    String GetPhone(){
        return Phone ;
    }
    public static String virifiction(JFrame frm1, JTextField txtFiledUsername, JTextField txtFiledPassword) throws ClassNotFoundException{
        
        String x = "" ;
        int counter = 0 ;
        if(txtFiledUsername.getText().compareTo("") == 0 || txtFiledPassword.getText().compareTo("") == 0){
           // JOptionPane.showMessageDialog(null, "This Faild Can't Be Empty");
            

        }
        else{
            try {
                java.sql.Statement stat = DB.CreateConnection(DBdriver ,DBurl , "root", "");
               ///////////////////////////////
               //count used for getting rows count ,by sql language 
               ///////////////////////////////////
                
               
               ///////////////////what for go to func count .........
               int count = DB.Count("register", stat) ;
               
               
                ResultSet rs = DB.SelectAll("register", stat);
               
                //Array of objects for this class 
                RejesterClass rj[] = new RejesterClass[count] ;
                //initialize those objects 
                for(int i = 0 ; i < count ; i++)
                {
                    rj[i] = new RejesterClass();//create //initail
                }
                int w = 0 ;
               //set the value in them as where as Database Table 
                while(rs.next())//while u have more in table DataBase 
                {
                    rj[w].setUserName(rs.getString("UserName"));
                    rj[w].setPassword(rs.getString("Password"));
                    rj[w].SetType(rs.getString("Type"));
                    rj[w].setPath(rs.getString("Path"));
                    if(w!=count){w++ ;}
                }
                
                
                String UserName = txtFiledUsername.getText() ;
                String password = txtFiledPassword.getText() ;
                    //Check Verification
                for(int i = 0 ; i < count ;i++){
                    if(//if#1
                        UserName.compareTo(rj[i].GetUserName()) == 0 &&
                        password.compareTo(rj[i].GetPassword()) == 0 &&
                        rj[i].GetType().compareTo("Admin") == 0
                      )
                    {//do this if#1
                        counter = 3 ;
                        adminFrames.adminFrame1 frm = new adminFrames.adminFrame1();
                        f.Start(frm , frm1);
                    }
                    else if(
                        UserName.compareTo(rj[i].GetUserName()) == 0 &&
                        password.compareTo(rj[i].GetPassword()) == 0 &&
                        rj[i].GetType().compareTo("Student") == 0
                    ){
                        x = rj[i].GetPath() ;
                        counter = 3 ;
                        studentClass.studentWelcome frm = new studentClass.studentWelcome();
                        f.Start(frm , frm1);
                    }
                    else if(
                        UserName.compareTo(rj[i].GetUserName()) == 0 &&
                        password.compareTo(rj[i].GetPassword()) == 0 &&
                        rj[i].GetType().compareTo("Instructor") == 0
                    ){  
                        counter = 3 ;
                        InstructorPart.MainPage1 frm = new InstructorPart.MainPage1(frm1);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(Login_page.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(counter == 0){
                //Action if passwd WRONG !
             // JOptionPane.showMessageDialog(null, "UserName or Password is Wrong");
                sign.SignInfo.setText("Wrong Passwd ");
                //SignInfo.setText("Login Info...");
            }
        }
        return x ;
    }//end verification func
    
    public static void insertUSER
 ( JTable tableReg ,JTextField UserNameTXT , JTextField PasswordTXT ,JComboBox TypeBox, JTextField PhoneTXT )
         throws ClassNotFoundException, SQLException{
        DATABASE DB = new DATABASE();
        if(UserNameTXT.getText().compareTo("") == 0 ||PasswordTXT.getText().compareTo("") == 0 ){
            JOptionPane.showMessageDialog(null, "This faild can't be empty");
        }
        else{
            String Username = UserNameTXT.getText();
            String password = PasswordTXT.getText();
            String Type     = TypeBox.getSelectedItem().toString();
            String Path     = PhoneTXT.getText() ;
            java.sql.Statement stat =  DB.CreateConnection(DBdriver ,DBurl , "root", "");
            
            try {
                DefaultTableModel model = (DefaultTableModel) tableReg.getModel();
                if(DB.InsertUser(stat, Username, password, Type, Path) == 1){
                    //Create some array of rows
                    Object obj[] = {Username ,password ,Type ,Path};
                   //then  insert them in the table module
                   model.addRow(obj);
                }
                //save all inputs in textfield 
                JTextField jt[] = {UserNameTXT , PasswordTXT ,PhoneTXT};
               //clear them
                f.CLEAR(jt);
            }
            catch (SQLException ex) 
            {
                if(String.valueOf(ex).contains("Duplicate entry"))
                {
                    JOptionPane.showMessageDialog(null, "UserName is alreay Exist");
                }
            } catch (Exception ex)
            {
                Logger.getLogger(NewUSer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void UpdateUser(JTable tableReg , JTextField NewValue){
        DefaultTableModel model = (DefaultTableModel) tableReg.getModel();
        int currentRow = tableReg.getSelectedRow() , x ;
        int currentColumn = tableReg.getSelectedColumn();
        if(NewValue.getText().compareTo("") == 0){
            JOptionPane.showMessageDialog(null, "This Faild Can't Be Empty");
        }
        else{
            try {
                java.sql.Statement st =DB.CreateConnection(DBdriver ,DBurl , "root", "");
                DB.deleteWhere(st, "register" , "UserName", model.getValueAt(currentRow, 0)+"") ;
                String NAME = (String) model.getValueAt(currentRow, 0);
                String PASS = (String) model.getValueAt(currentRow, 1);
                String Type = (String) model.getValueAt(currentRow, 2);
                String Path = (String) model.getValueAt(currentRow, 3);
                String SQL = "";
                String []dbColumnName = {"UserName" , "Password" , "Type" , "Path"};
                if(currentColumn == 0){
                    String []dbValue = {NewValue.getText() , PASS , Type , Path};
                    x = DB.insertAll(st, NAME, dbColumnName, dbValue);
                }
                else if(currentColumn == 1){
                    String []dbValue = {NAME ,NewValue.getText(),Type,Path};
                    x = DB.insertAll(st, NAME, dbColumnName, dbValue);
                }
                else if(currentColumn == 2){
                    String []dbValue = {NAME ,PASS,NewValue.getText(),Path};
                    x = DB.insertAll(st, NAME, dbColumnName, dbValue);
                }
                else{
                    String []dbValue = {NAME ,PASS,Type,NewValue.getText()};
                    x = DB.insertAll(st, NAME, dbColumnName, dbValue);
                }
                if(x == 1){
                    model.setValueAt(NewValue.getText(), currentRow , currentColumn );
                    JTextField jt[] = {NewValue} ;
                    f.CLEAR(jt);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NewUSer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RejesterClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void retreve(JTable tableReg) throws ClassNotFoundException{
        try {

java.sql.Statement stat = DB.CreateConnection(DBdriver ,DBurl , "root", "");
ResultSet rs = DB.SelectAll("register", stat) ;
            int i = 0 ;
            DefaultTableModel model = (DefaultTableModel) tableReg.getModel();
            while(rs.next()){
                Object obj [] = {rs.getString("UserName") ,
                    rs.getString("Password") ,
                    rs.getString("Type") ,
                    rs.getString("Path")
                };
                model.addRow(obj);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public static void DeleteUser(JTable tableReg) throws ClassNotFoundException, SQLException{
        

java.sql.Statement st =DB.CreateConnection(DBdriver ,DBurl , "root", "");
DefaultTableModel model = (DefaultTableModel) tableReg.getModel();
int CurRow    = tableReg.getSelectedRow() ;
if(DB.deleteWhere(st, "register", "UserName", tableReg.getValueAt(CurRow, 0)+"") == 1){
    model.removeRow(CurRow);
        }
    }
}
