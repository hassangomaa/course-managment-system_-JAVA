/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseClass;

import adminFrames.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author egypt
 */
public class Student extends person{
    private static courseClass.DATABASE DB = new courseClass.DATABASE();
    private static Function f = new Function(true);
   static  java.sql.Statement stat;
                
                
    public Student(){try {
        this.stat = DB.CreateConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    public static void ReadAllStudent(JTable TABLEIinfo){
        try{
            java.sql.Statement stat = DB.CreateConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "") ;
            ResultSet rs = DB.SelectAll("students_information",stat);
            DefaultTableModel model = (DefaultTableModel) TABLEIinfo.getModel();
            while(rs.next()){
                Object obj[] = {
                    rs.getString("FULL_NAME") ,
                    rs.getString("AGE") ,
                    rs.getString("PHONE") ,
                    rs.getString("HOMEPHONE") ,
                    rs.getString("ADDRESS") ,
                    rs.getString("COURS") ,
                };
                model.addRow(obj);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void insertStudent(JComboBox COURSENAMETXT , JTable TABLEIinfo , JTextField ADDRESSTXT ,JTextField HOMEPHONETXT,JTextField PHONETXT,JTextField AGETXT,JTextField lNAMETXT,JTextField fNAMETXT,JTextField mNAMETXT){
        DefaultTableModel model = (DefaultTableModel) TABLEIinfo.getModel();
        if( ADDRESSTXT.getText().compareTo("") == 0    ||
            HOMEPHONETXT.getText().compareTo("") == 0  ||
            PHONETXT.getText().compareTo("") == 0      ||
            AGETXT.getText().compareTo("") == 0        ||
            lNAMETXT.getText().compareTo("") == 0      ||
            fNAMETXT.getText().compareTo("") == 0      ||
            mNAMETXT.getText().compareTo("") == 0 
        ){JOptionPane.showMessageDialog(null,"The faild cant be Empty");}
        else{
            try {
                String fnm = fNAMETXT.getText();
                String mnm = mNAMETXT.getText();
                String lnm = lNAMETXT.getText();
                String FullName = fnm + " " + mnm + " " + lnm ;
                String age = AGETXT.getText();
                String phn = PHONETXT.getText();
                String hphn = HOMEPHONETXT.getText();
                String Adress = ADDRESSTXT.getText();
                String course = (String) COURSENAMETXT.getSelectedItem();
                Object obj[] = {FullName , age , phn , hphn , Adress , course};
                
//java.sql.Statement st = stat ;
                String []dbColumnName = {"FULL_NAME","AGE","PHONE","HOMEPHONE","ADDRESS","COURS"} ;
                String []dbValue = { FullName , age , phn , hphn , Adress , course } ;
                if(DB.insertAll(stat, "students_information", dbColumnName, dbValue)==1){
                    model.addRow(obj);
                    JOptionPane.showMessageDialog(null, "Student is Inserted");
                }
                JTextField [] jt = {fNAMETXT , mNAMETXT , lNAMETXT ,
                    lNAMETXT , mNAMETXT , fNAMETXT , AGETXT, PHONETXT , HOMEPHONETXT,ADDRESSTXT };
                
                f.CLEAR(jt);
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void UpdateStudent(JTextField NEWVALUETXT , JTable TABLEIinfo ){
        
        if(NEWVALUETXT.getText().compareTo("") == 0){
            JOptionPane.showMessageDialog(null,"The faild cant be Empty");
        }
        else{
            try {
                int CurrentRow = TABLEIinfo.getSelectedRow();
                int CurrentColumn = TABLEIinfo.getSelectedColumn() , x = 0 ;
                DefaultTableModel model = (DefaultTableModel) TABLEIinfo.getModel();
                java.sql.Statement stat = DB.CreateConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "");
                if(CurrentColumn == 0){
                    x = DB.UpdateWhere(stat, "students_information", "FULL_NAME", NEWVALUETXT.getText(), "PHONE",TABLEIinfo.getValueAt(CurrentRow, 2)+"" );
                }
                else if(CurrentColumn == 1){
                    x = DB.UpdateWhere(stat, "students_information", "AGE", NEWVALUETXT.getText(), "FULL_NAME",TABLEIinfo.getValueAt(CurrentRow, 0)+"" );
                }
                else if(CurrentColumn == 2){
                    x = DB.UpdateWhere(stat, "students_information", "PHONE", NEWVALUETXT.getText(), "FULL_NAME",TABLEIinfo.getValueAt(CurrentRow, 0)+"" );
                }
                else if(CurrentColumn == 3){
                    x = DB.UpdateWhere(stat, "students_information", "HOMEPHONE", NEWVALUETXT.getText(), "FULL_NAME",TABLEIinfo.getValueAt(CurrentRow, 0)+"" );
                }
                else if(CurrentColumn == 4){
                    x = DB.UpdateWhere(stat, "students_information", "ADDRESS", NEWVALUETXT.getText(), "FULL_NAME",TABLEIinfo.getValueAt(CurrentRow, 0)+"" );
                }
                else if(CurrentColumn == 5){
                    x = DB.UpdateWhere(stat, "students_information", "COURS", NEWVALUETXT.getText(), "FULL_NAME",TABLEIinfo.getValueAt(CurrentRow, 0)+"" );
                }   if(x != 0){
                    model.setValueAt( NEWVALUETXT.getText()  , CurrentRow , CurrentColumn );
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void DeleteStudent(JTable TABLEIinfo){
        DefaultTableModel model = (DefaultTableModel) TABLEIinfo.getModel();
        int CurrentRow = -1 ;
        CurrentRow = TABLEIinfo.getSelectedRow();
        if(CurrentRow == -1){
            JOptionPane.showMessageDialog(null, "Choose one row to delete it");
        }
        else{
            try{
                java.sql.Statement stat = DB.CreateConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "") ;
                int SelectedRow = TABLEIinfo.getSelectedRow() ;
                DB.deleteWhere(stat, "students_information", "FULL_NAME", TABLEIinfo.getValueAt(CurrentRow, 0)+"");
                model.removeRow(CurrentRow);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    
    public static void retreve(JComboBox COURSENAMETXT , JTable TABLEIinfo){
        
        try{
            int count = DB.Count("students_information", stat) ;
            ResultSet rs = DB.SelectAll("students_information", stat);
            DefaultTableModel model = (DefaultTableModel) TABLEIinfo.getModel();
            while(rs.next()){
                Object []obj = {
                    rs.getString("FULL_NAME"),
                    rs.getString("AGE"),
                    rs.getString("PHONE"),
                    rs.getString("HOMEPHONE"),
                    rs.getString("ADDRESS"),
                    rs.getString("COURS") ,
                    rs.getString("GRADE")
                };
                model.addRow(obj);
            }
            rs = DB.SelectAll("courses_information", stat) ;
            while(rs.next()){
                COURSENAMETXT.addItem(rs.getString("CourseName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(adminFrames.Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void RetrevePersonalInformation(JTextField TXT1 , JTextField TXT2 , JTextField TXT3){
        try{
         
            int count = DB.Count("students_information", stat);
            ResultSet rs = DB.SelectAll("students_information", stat);
            String Path = Home_pages.Login_page.var ;
            int h = 0 ;
            while(rs.next()){
                if(Path.compareTo(rs.getString("HOMEPHONE")) == 0){
                    TXT1.setText("Student Name is " + " " + rs.getString("FULL_NAME"));
                    TXT2.setText("Course Name is " + " " + rs.getString("COURS"));
                    TXT3.setText("Grade is " + rs.getString("GRADE"));
                }
            }
        } catch (SQLException ex ) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public static void MakeSurvy(JRadioButton rb[] ){
        try {
            Statement st = stat;
            String Path = Home_pages.Login_page.var ;
            System.out.println(Path);
            String fsurvey = "";
            String ssurvey = "";
            String tsurvey = "";
            if(rb[0].isSelected()){
                fsurvey = "Strongly Agree";
            }
            else if (rb[1].isSelected()) {
                fsurvey = "Agree";
            }
            else if(rb[2].isSelected()){
                fsurvey = "Neutral";
            }
            if(rb[3].isSelected()){
                ssurvey = "Strongly Agree";
            }
            else if (rb[4].isSelected()) {
                ssurvey = "Agree";
            }
            else{
                ssurvey = "Neutral";
            }
            if(rb[5].isSelected()){
                tsurvey = "Strongly Agree";
            }
            else if (rb[6].isSelected()) {
                tsurvey = "Agree";
            }
            else if(rb[7].isSelected()){
                tsurvey = "Neutral";
            }
            String sql = "insert into survey(FirstQ,SecondQ,ThiredQ) values('"+fsurvey+"','"+ssurvey+"','"+tsurvey+"')";
            String Column [] = {"FirstQ","SecondQ","ThiredQ"};
            String Value  [] = {fsurvey , ssurvey , tsurvey };
            int result = DB.insertAll(st, "survey", Column, Value);
            if(result == 1){
                DB.UpdateWhere(st, "register", "survy", "DONE" , "Path", Path);
                JOptionPane.showMessageDialog(null, "Submit successfully", "success", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Try Again", "success", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void IfDone(JLabel LABTXT , JPanel PANEL , JPanel PANELLL ){
        try {
            Statement st = DB.CreateConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "");
            String Path = Home_pages.Login_page.var ;
            ResultSet rs = DB.SelectColumnWhere( st, "register", "survy", Path, Path);
            String vir = "";
            while(rs.next()){
                vir = rs.getString("survy");
                System.out.println(vir);
            }
            if(String.valueOf(vir).compareTo("DONE") == 0){
                PANEL.setVisible(false);
                LABTXT.setVisible(true);
                //this.setSize( 642 , 200 );
                PANELLL.setLocation(100,100);
            }
            else{
                LABTXT.setVisible(false);
                PANEL.setVisible(true);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public static void UpdateInformation(JTextField jt[]){
        try {
            //java.sql.Statement stat =  DB.CreateConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "");
            DB.UpdateMoreThanColumn(stat, jt);
            JOptionPane.showMessageDialog(null, "Information is Updated ");
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void RetreveInformation(JTextField jt[]){
        try{
            //java.sql.Statement stat =  DB.CreateConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "");
            int count = DB.Count("students_information", stat) ;
            ResultSet rs = DB.SelectAll("students_information", stat);
            String Path = Home_pages.Login_page.var ;
            while(rs.next()){
                if(Path.compareTo(rs.getString("HOMEPHONE")) == 0){
                    jt[0].setText(rs.getString("FULL_NAME")) ;
                    jt[1].setText(rs.getString("AGE")) ;
                    jt[2].setText(rs.getString("PHONE")) ;
                    jt[3].setText(rs.getString("HOMEPHONE")) ;
                    jt[4].setText(rs.getString("ADDRESS")) ;
                    jt[5].setText(rs.getString("COURS")) ;
                    jt[6].setText(rs.getString("GRADE")) ;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(adminFrames.Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
