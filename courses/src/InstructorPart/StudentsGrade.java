/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructorPart;


import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel; 
import java.awt.event.*;

public class StudentsGrade {
    Frame frame = new Frame("Main Page");
    DefaultTableModel dtl = new DefaultTableModel();
    courseClass.DATABASE db = new courseClass.DATABASE("root","courses");
    ResultSet rs = db.resultSetOfStudents();
    JTable table = new JTable();
    JScrollPane pane = new JScrollPane(table);
    Button btn = new Button(),btnUpdate,btnRefresh,btnReset,btnBack;
    Label label = new Label();
    TextField textfield = new TextField();
    TextField textName,textGrade;
    PreparedStatement pst = db.ps() ;
    public StudentsGrade(){
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
        showTable();
        addtoMainFrame();
    }
    
    public  void showTable(){
        pane.setBounds(26,70,550,183);
        pane.setBorder(null);
        table.getTableHeader().setBackground(Color.GRAY);
        frame.add(pane);
        createTable();
    }
    
    public void createTable() {
        try {
            table.setModel(dtl);
            //"Name","Course","Start","End","Grade"
            dtl.addColumn("Name");
            dtl.addColumn("Course");
            dtl.addColumn("Age");
            dtl.addColumn("Address");
            dtl.addColumn("Grade");
            table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table1MouseClicked();
            }
        });
            while(rs.next())
                dtl.addRow(new Object[] {rs.getString("FULL_NAME"),rs.getString("COURS"),rs.getString("AGE"),rs.getString("ADDRESS"),rs.getString("GRADE")});
        } catch (SQLException ex) {
            Logger.getLogger(StudentsGrade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createComps(){
        btnUpdate = btnProp("Update",25,260);
        btnRefresh = btnProp("Refresh",105,260);
        btnReset = btnProp("Reset",185,260);
        btnBack = btnProp("Back",25,530);
        textName = textProp(435, 370,130);
        textGrade = textProp(435, 400,90);
        textName.setEditable(false);
    }
    
    public void addtoMainFrame(){
        createComps();
        frame.add(btnUpdate);
        frame.add(btnRefresh);
        frame.add(btnReset);
        frame.add(btnBack);
        frame.add(labelProp("Name",355,360));
        frame.add(labelProp("Grade",355,390));
        frame.add(textName);
        frame.add(textGrade);
        btnActions();
        
    }
    
    public void btnActions(){
        btnUpdate.addActionListener(new ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            updateMouseClicked();
            refreshMouseClicked();
            } 
            });
        btnRefresh.addActionListener(new ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshMouseClicked();
            } 
            });
        btnReset.addActionListener(new ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetMouseClicked();
                refreshMouseClicked();
            } 
            });
        btnBack.addActionListener(new ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            } 
            });
        
        
    }
    
    public Button btnProp(String name,int x, int y){
        btn = new Button(name);
        btn.setBackground(Color.white);
        btn.setBounds(x,y,60,40);
        return btn;
    }
    
    public Label labelProp(String name,int x, int y){
        label = new Label();
        label.setText(name);
        label.setBounds(x,y,60,40);
        label.setFont(new Font("Tahoma",Font.PLAIN,18));
        return label;
    }
    
    public TextField textProp(int x, int y,int width){
        textfield = new TextField();
        textfield.setBounds(x,y,width,20);
        return textfield;
    }
    
    private void Table1MouseClicked() {
        int i= table.getSelectedRow();
        textGrade.setText(dtl.getValueAt(i, 4).toString());
        textName.setText(dtl.getValueAt(i, 0).toString());
    }
    
    private void updateMouseClicked() {
        int i = Integer.parseInt(textGrade.getText());
        db.updateGrades(i, textName.getText());
    }
    
    private void refreshMouseClicked(){
        int i = 0;
        rs = db.resultSetOfStudents();
        table.setVisible(false);
        try {
            while(rs.next()){
                dtl.setValueAt(rs.getString("GRADE"), i, 4);
                dtl.setValueAt(rs.getString("ADDRESS"), i, 3);
                dtl.setValueAt(rs.getString("AGE"), i, 2);
                dtl.setValueAt(rs.getString("COURS"), i, 1);
                dtl.setValueAt(rs.getString("FULL_NAME"), i, 0);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsGrade.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setVisible(true);
    }
    
    private void resetMouseClicked(){
        try {
            rs = db.resultSetOfStudents();
            String SQL = "update students_information set GRADE = "+0+" ";
            pst.executeUpdate(SQL);
            table.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(StudentsGrade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
