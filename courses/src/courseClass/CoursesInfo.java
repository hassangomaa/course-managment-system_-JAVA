/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseClass;


import courseClass.*;
import adminFrames.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author egypt
 */
public class CoursesInfo {
    private String CoNa ;
    private String Bran ;
    private String Room ;
    private String nosa ;
    private String pric ;
    private String mnth ;
    private String strt ;
    private String prnt ;
    private String EndC ;
    private String Inst ;
    private static DATABASE DB = new DATABASE();
    private static Function f = new Function(true);
    
    public CoursesInfo(){}
    /*=====================set=====================*/
    void SetCourseName(String Value){CoNa = Value ;}
    
    void SetBrunch(String Value){Bran = Value ;}
    
    void SetRoom(String Value){Room = Value ;}
    
    void SetnumberOfStudentAllowd(String Value){nosa = Value ;}
    
    void Setprice(String Value){pric = Value ;}
    
    void Setmonth(String Value){mnth = Value ;}
    
    void Setstart(String Value){strt = Value ;}
    
    void Setparent(String Value){prnt = Value ;}
    
    void SetEndC(String Value){EndC = Value ;}
    
    void SetInstractor(String Value){Inst = Value ;}
    /*=====================get=====================*/
    String GetCourseName(){return CoNa ;}
    
    String GetBrunch(){return Bran ;}
    
    String GetRoom(){return Room ;}
    
    String GetnumberOfStudentAllowd(){return nosa ;}
    
    String Getprice(){return pric ;}
    
    String Getmonth(){return mnth ;}
    
    String Getstart(){return strt ;}
    
    String Getparent(){return prnt ;}
    
    String GetEndC(){return EndC ;}
    
    String GetInstractor(){return Inst ;}
    /*=================================================*/
    public static void ShowAllCourses(JTable TableStart ,JLabel txtnumberOfStudent ,JLabel txtnumberOfCourses) throws ClassNotFoundException{
        try{
            DefaultTableModel model1 = (DefaultTableModel) TableStart.getModel();
            Statement stat = DB.CreateConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "") ;
            int count  = DB.Count("courses_information" , stat) ;
            
            String numberStu = "The Number of All Student at all Courses is : " + DB.Count("students_information", stat);
            txtnumberOfStudent.setText(numberStu);
            String numbercou = "The number of All Avilible courses is : " + count ;
            txtnumberOfCourses.setText(numbercou);
            
            ResultSet rs = DB.SelectAll("courses_information", stat);
            CoursesInfo CI[] = new CoursesInfo[count];
            for(int i = 0 ; i < count ; i++){
                CI[i] = new CoursesInfo() ;
            }
            int h = 0 ;
            while(rs.next()){
                CI[h].SetCourseName(rs.getString("CourseName")) ;
                CI[h].SetBrunch(rs.getString("Branch"));
                CI[h].SetRoom(rs.getString("Room")) ;
                CI[h].SetnumberOfStudentAllowd(rs.getString("AllowedStudent"));
                CI[h].Setprice(rs.getString("Price"));
                CI[h].Setmonth(rs.getString("period"));
                CI[h].Setstart(rs.getString("Start"));
                CI[h].Setparent(rs.getString("Parent"));
                CI[h].SetEndC(rs.getString("End"));
                CI[h].SetInstractor(rs.getString("Instractor"));
                h++;
            }
            for(int i = 0 ; i < count ; i++){
                String Start = CI[i].Getstart();
                String End   = CI[i].GetEndC() ;
                Date date = new Date();
                java.text.SimpleDateFormat myF = new java.text.SimpleDateFormat("yyyy-MM-dd");
                String curTime = myF.format(date) ;
                Object obj[] = {
                        CI[i].GetCourseName(),
                        CI[i].Getstart(),
                        CI[i].GetEndC(),
                        CI[i].GetnumberOfStudentAllowd(),
                        CI[i].GetInstractor() ,
                    } ;
                    model1.addRow(obj);
                if(Start.compareTo(curTime) > 0){
            ///starting (almost
            model1.setValueAt("Starting soon", i ,5);
                }
                if(End.compareTo(curTime) < 0){
                 //Ending 
                    model1.setValueAt("Finishing soon", i ,5);
                }
        }
        } catch (SQLException ex) {
            
        }
    }
    
    public static void ShowAllParentCourse(JTable tables){
        try {
            DefaultTableModel model = (DefaultTableModel) tables.getModel();
            Statement stat = DB.CreateConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "") ;
            ResultSet rs = DB.SelectAll("parent_course", stat);
            while(rs.next()){
                Object obj[] = {
                    rs.getString("ParentCourseName"),
                    rs.getString("IdParentCourse")  ,
                    rs.getString("ChildCourseName")
                };
                model.addRow(obj);
            }
        } catch (Exception ex) {
           // Logger.getLogger(Parent_course.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void EditOnParentCourse(JTable tables , JTextField Nval ) throws ClassNotFoundException , SQLException{
        DefaultTableModel model = (DefaultTableModel) tables.getModel();
        int CurSelRow = tables.getSelectedRow() ;
        int CurSelCol = tables.getSelectedColumn() ;
        String id  = (String) model.getValueAt(CurSelRow, 1);
        String NewValue = Nval.getText() ;
        Statement stat = DB.CreateConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "") ;
        String sql = "";
        if(DB.SupdateParentCourse( stat , CurSelCol , NewValue , id ) == 1){
            tables.setValueAt(NewValue, CurSelRow, CurSelCol);
            JOptionPane.showMessageDialog(null,"Information has Updated Successfully");
        }
    }
    
    public static void AddPatrenCourse(JTable tables , JTextField TXT1 , JTextField TXT2 , JTextField TXT3)
            throws SQLException , ClassNotFoundException {
        DefaultTableModel model = (DefaultTableModel) tables.getModel();
        if( TXT1.getText().compareTo("") == 0 || TXT2.getText().compareTo("")== 0 ||  TXT3.getText().compareTo("") == 0 ){
            JOptionPane.showMessageDialog(null, "Empty Faild");
        }
        Statement stat =  DB.CreateConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "") ;
        if(DB.InsertIntoParentCourse(stat ,TXT1.getText(), TXT3.getText(), TXT2.getText()) == 1){
            Object obj[] = {TXT1.getText() , TXT2.getText() , TXT3.getText()};
            model.addRow(obj);
            JTextField array[] = {TXT1 , TXT2 ,TXT3};
            f.CLEAR(array);
            JOptionPane.showMessageDialog(null, "Done");
        }
        else{
            JOptionPane.showMessageDialog(null, "Problem");
        }
    }
    
    public static void RemovePreRequsite(JTable tables) throws ClassNotFoundException , SQLException , Exception{
          DefaultTableModel model = (DefaultTableModel) tables.getModel();
            int CurSelRow = tables.getSelectedRow() ;
            int CurSelCol = tables.getSelectedColumn() ;
            Statement stat;
            // after execute will return 1
        try {
          stat = DB.CreateConnection("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "") ;
            if(DB.deleteWhere(stat, "parent_course",
                    "IdParentCourse", (String) tables.getValueAt(CurSelRow, 1)) == 1 )
            {
                model.removeRow(CurSelRow);
                JOptionPane.showMessageDialog(null, "Done");
            }
            else{
                JOptionPane.showMessageDialog(null, "Problem");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursesInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoursesInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void DeleteCourse(JTable TABLEcourse) throws ClassNotFoundException{
        DefaultTableModel model = (DefaultTableModel) TABLEcourse.getModel();
        int CurrentRow = model.getRowCount();
        int CurrentColumn = model.getColumnCount();
        int LENG = model.getRowCount() ;
        CurrentRow = -1 ;
        CurrentRow = TABLEcourse.getSelectedRow();
        if(CurrentRow == -1){
            JOptionPane.showMessageDialog(null, "Choose one row to delete it");
        }
        else{
            try {
                java.sql.Statement st = DB.CreateConnection("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "");

                ResultSet rs =  DB.SelectAll("courses_information", st);
                String VALUE = (String) TABLEcourse.getValueAt(CurrentRow, 0);
                DB.deleteWhere(st,"courses_information" , "CourseName", VALUE);
                model.removeRow(CurrentRow);
            } catch (SQLException ex) {
                Logger.getLogger(CoursesInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void UpdateCourse(JTable TABLEcourse , JTextField NEWVALUETXT ) throws ClassNotFoundException {
        DefaultTableModel model = (DefaultTableModel) TABLEcourse.getModel();
        int CurrentRow = TABLEcourse.getSelectedRow();
        int CurrentColumn = TABLEcourse.getSelectedColumn();
        if(NEWVALUETXT.getText().compareTo("") == 0){
            JOptionPane.showMessageDialog(null,"The faild cant be Empty");
        }
        else{
            try {
                java.sql.Statement st = DB.CreateConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "");
                int count = DB.Count("courses_information", st);
                if(CurrentColumn == 0){
                    JOptionPane.showMessageDialog(null, "you cant edit this faild");
                }
                else if(CurrentColumn == 1){
                    DB.UpdateWhere(st, "courses_information", "Branch" , NEWVALUETXT.getText() , "CourseName" , TABLEcourse.getValueAt(CurrentRow, 0)+"");
                }
                else if(CurrentColumn == 2){
                    DB.UpdateWhere(st, "courses_information", "Room" , NEWVALUETXT.getText() , "CourseName" , TABLEcourse.getValueAt(CurrentRow, 0)+"");
                }
                else if(CurrentColumn == 3){
                    DB.UpdateWhere(st, "courses_information", "AllowedStudent" , NEWVALUETXT.getText() , "CourseName" , TABLEcourse.getValueAt(CurrentRow, 0)+"");
                }
                else if(CurrentColumn == 4){
                    DB.UpdateWhere(st, "courses_information", "Price" , NEWVALUETXT.getText() , "CourseName" , TABLEcourse.getValueAt(CurrentRow, 0)+"");
                }
                else if(CurrentColumn == 5){
                    DB.UpdateWhere(st, "courses_information", "period" , NEWVALUETXT.getText() , "CourseName" , TABLEcourse.getValueAt(CurrentRow, 0)+"");
                }
                else if(CurrentColumn == 6){
                    DB.UpdateWhere(st, "courses_information", "Start" , NEWVALUETXT.getText() , "CourseName" , TABLEcourse.getValueAt(CurrentRow, 0)+"");
                }
                else if(CurrentColumn == 7){
                    DB.UpdateWhere(st, "courses_information", "Parent" , NEWVALUETXT.getText() , "CourseName" , TABLEcourse.getValueAt(CurrentRow, 0)+"");
                }
                else if(CurrentColumn == 8){
                    DB.UpdateWhere(st, "courses_information", "End" , NEWVALUETXT.getText() , "CourseName" , TABLEcourse.getValueAt(CurrentRow, 0)+"");
                }
                else{
                    DB.UpdateWhere(st, "courses_information", "Instractor" , NEWVALUETXT.getText() , "CourseName" , TABLEcourse.getValueAt(CurrentRow, 0)+"");
                }
                if(CurrentColumn != 0){
                    model.setValueAt(NEWVALUETXT.getText(), CurrentRow , CurrentColumn );
                    NEWVALUETXT.setText("");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CoursesInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void insertCourse(JTable TABLEcourse,JComboBox INSTRACTORSELECT,JTextField COURSENAMETXT, JTextField BRANCHTXT, JTextField ROOMTXT, JTextField numStuAlTXT ,
                JTextField PRICETXT , JTextField STARTDATETXTMonth , JTextField STARTDATETXTDay , JTextField STARTDATETXTYaer,
                JTextField EndDATETXTMonth , JTextField EndDATETXTDay , JTextField EndDATETXTYaer ,JTextField PARENTCOURSETXT
                ){
            if(
                COURSENAMETXT.getText().compareTo("") == 0 ||
                BRANCHTXT.getText().compareTo("") == 0     ||
                ROOMTXT.getText().compareTo("") == 0       ||
                numStuAlTXT.getText().compareTo("") == 0   ||
                PRICETXT.getText().compareTo("") == 0      ||
                !(STARTDATETXTMonth.getText().compareTo("") != 0  && STARTDATETXTMonth.getText().length() <=2 && Integer.parseInt(STARTDATETXTMonth.getText()) <= 12 && Integer.parseInt(STARTDATETXTMonth.getText()) != 0 )||
                !(STARTDATETXTDay.getText().compareTo("")   != 0  && STARTDATETXTDay.getText  ().length() <=2 && Integer.parseInt(STARTDATETXTDay.getText  ()) <= 30 && Integer.parseInt(STARTDATETXTDay.getText())   != 0 )||
                !(STARTDATETXTYaer.getText().compareTo("")  != 0  && STARTDATETXTYaer.getText ().length() ==4 && Integer.parseInt(STARTDATETXTYaer.getText ()) <= 2100 && Integer.parseInt(STARTDATETXTYaer.getText()) > 2000 )||
                (EndDATETXTMonth.getText().compareTo("") == 0  && EndDATETXTMonth.getText().length() <=2 && Integer.parseInt(EndDATETXTMonth.getText()) <= 12 && Integer.parseInt(EndDATETXTMonth.getText()) != 0 )||
                (EndDATETXTDay.getText().compareTo("")   == 0  && EndDATETXTDay.getText  ().length() <=2 && Integer.parseInt(EndDATETXTDay.getText  ()) <= 30 && Integer.parseInt(EndDATETXTDay.getText())   != 0 )||
                (EndDATETXTYaer.getText().compareTo("")  == 0  && EndDATETXTYaer.getText ().length() ==4 && Integer.parseInt(EndDATETXTYaer.getText ()) <= 2100 && Integer.parseInt(EndDATETXTYaer.getText()) > 2000 )||
                PARENTCOURSETXT.getText().compareTo("") == 0 
            ){
                JOptionPane.showMessageDialog(null, "This Faild Can't Be Empty");
            }
            else{
                try {
                    String CoNa = COURSENAMETXT.getText();
                    String Bran = BRANCHTXT.getText();
                    String Room = ROOMTXT.getText();
                    String nosa = numStuAlTXT.getText();
                    String pric = PRICETXT.getText();
                    String strt = STARTDATETXTYaer.getText()+"-"+STARTDATETXTMonth.getText()+"-"+STARTDATETXTDay.getText();
                    String prnt = PARENTCOURSETXT.getText();
                    String EndC = EndDATETXTYaer.getText()+"-"+EndDATETXTMonth.getText()+"-"+EndDATETXTDay.getText();
                    adminFrames.time t = new adminFrames.time(strt , EndC);
                    String Mnth = "";
                    try {
                        Mnth = String.valueOf(t.period());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    String Inst = (String)INSTRACTORSELECT.getSelectedItem();
                    DefaultTableModel model = (DefaultTableModel) TABLEcourse.getModel();
                    Object obj[] = {
                        CoNa,Bran,Room,
                        nosa,pric,Mnth,
                        strt,prnt,EndC,
                        Inst } ;
                    model.addRow(obj);
                    JTextField[] jt={COURSENAMETXT, BRANCHTXT, ROOMTXT,
                        numStuAlTXT, PRICETXT, STARTDATETXTYaer , STARTDATETXTMonth ,
                        STARTDATETXTDay , EndDATETXTYaer , EndDATETXTDay ,
                        EndDATETXTMonth , PARENTCOURSETXT};
                    f.CLEAR(jt);
                    
                    java.sql.Statement stat = DB.CreateConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "");
                    
                    if(DB.InsertCourse(stat, CoNa, Bran, Room, nosa, pric, Mnth, strt, prnt, EndC, Inst) == 1){
                        JOptionPane.showMessageDialog(null, "Course Information Is Inserted");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Course Information Isn't Inserted");
                    }
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CoursesInfo.class.getName()).log(Level.SEVERE,null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CoursesInfo.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
            }
        }
    
    public static void retreve(JTable TABLEcourse , JComboBox INSTRACTORSELECT){
        try {
            java.sql.Statement st = DB.CreateConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "");
            ResultSet rs = DB.SelectAll("courses_information", st);
            int i = 0 ;
            DefaultTableModel model = (DefaultTableModel) TABLEcourse.getModel();
            while(rs.next()){
                Object obj [] = {
                    rs.getString("CourseName"),
                    rs.getString("Branch"),
                    rs.getString("Room"),
                    rs.getString("AllowedStudent"),
                    rs.getString("Price"),
                    rs.getString("period"),
                    rs.getString("Start"),
                    rs.getString("Parent"),
                    rs.getString("End"),
                    rs.getString("Instractor"),
                };
                model.addRow(obj);
                rs =  DB.SelectAll("instractor_information", st);
                while(rs.next()){
                    INSTRACTORSELECT.addItem(rs.getString("FULL_NAME"));
                }
            }
        } catch ( Exception ex) {
            Logger.getLogger(NewUSer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void reteveT(JTable table){
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        try {
            Statement st = DB.CreateConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/courses", "root", "") ;
            String sql = "select * from courses_information";
            
            ResultSet rs = DB.SelectAll("courses_information", st);
            while(rs.next()){
                Object obj[] = {
                        rs.getString("CourseName") ,
                            rs.getString("Branch") ,
                              rs.getString("Room") ,
                    rs.getString("AllowedStudent") ,
                             rs.getString("Price") ,
                            rs.getString("period") ,
                             rs.getString("Start") ,
                            rs.getString("Parent") ,
                               rs.getString("End") ,
                        rs.getString("Instractor")
                };
                dtm.addRow(obj);
            }
        } catch ( Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
