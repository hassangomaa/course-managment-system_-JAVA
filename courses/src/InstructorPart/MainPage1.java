package InstructorPart;

import com.mysql.jdbc.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.scene.control.ProgressBar;
import javax.swing.*;

public class MainPage1 {
    Frame frame = new Frame("Main Page");
    TextArea showMsg;
    JLabel label = new JLabel(),label2 = 
     new JLabel("Statistics of Top 10 Students"),
      label4,label3 = new JLabel(new ImageIcon(MainPage1.class.getResource("writing.png")) );
    JPanel panel = new JPanel();
    JProgressBar[] progBar = new JProgressBar[100];
    JLabel[] label1 = new JLabel[100];
    Button btn = new Button("Posts"),gradebtn = new Button("Grades");
    courseClass.DATABASE db = new courseClass.DATABASE("root","courses");
    ResultSet rs = db.resultSetOfStudents();
    
    public MainPage1(){
        showMainFrame();
    }
    
    public MainPage1(JFrame frm){
        frm.dispose();
        showMainFrame();
    }
    
    public void showMainFrame(){   
        frame.setVisible(true);
        frame.setLocation(362,117);
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setBackground(Color.white);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e) {frame.dispose();}});
        frame.add(label2);
        label2.setFont(new Font("Tahoma",Font.BOLD,20));
        label2.setBounds(160,220,300,80);
        showPanel();
        showUserIcon();
        postBtn();
        Btn();
    }
    
    public void showPanel(){
        frame.add(panel);
        panel.setBounds(10,280,580,280);
        panel.setLayout(null);
        panel.setBackground(Color.white);
        addToPanel();
    }
    public void showUserIcon(){
        label4 = new JLabel("user : " + Home_pages.Login_page.txtFiledUsername.getText());
        frame.add(label3);
        label3.setBounds(390,30,180,80);
        frame.add(label4);
        label4.setBounds(420,80,180,80);
    }
    
    public void postBtn(){
        frame.add(btn);
        btn.setBounds(10,50,60,20);
        btn.setBackground(Color.white);
        btn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {            
                HomePage hp = new HomePage("Instructor");
            } 
        });
    }
    
    public void Btn(){
        frame.add(gradebtn);
        gradebtn.setBounds(90,50,60,20);
        gradebtn.setBackground(Color.white);
        gradebtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {            
                StudentsGrade hp = new StudentsGrade();
                frame.dispose();
            } 
            });
    }
    
    public void addToPanel(){
        try {
            int x = -15;
            for(int i = 0; i < 10 && rs.next(); i++)
            {
                //initialize 10 JProgressBar
                //of start 0 minimum to 100 maxmum 
                progBar[i] = new JProgressBar(0,100);
                panel.add(progBar[i]);
                progBar[i].setBounds(270,i+=30,300,13);
                progressBarVal(i,rs.getInt("GRADE"));
                //intialize 10 labels that take input from rs.next (DataBase)
                label1[i] = new JLabel(rs.getString("FULL_NAME"));
               //add this label in panel 
                panel.add(label1[i]);
                //set Bounds for this label
                label1[i].setBounds(5,i,260,13);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void progressBarVal(int i,int val){
        //int[] arr = new int[8];
        int x = val;
        progBar[i].setValue(x);
        if( x >= 0 && x <= 30) progBar[i].setForeground(Color.red);
        else if( x > 30 && x <= 60) progBar[i].setForeground(Color.orange);
        else if( x > 60 && x <= 100) progBar[i].setForeground(Color.green);
        progBar[i].setBackground(Color.white);
    }

}
