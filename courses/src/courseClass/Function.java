/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseClass;

import courseClass.*;
import adminFrames.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author egypt
 */
public class Function{
    
    private static boolean Visible ;
   //the deafult constructor
    public Function() {
        
    }
    //the second constructor
    public Function(boolean st) {
        Visible = st ;
    }
    
    public void Start(JFrame frm2 , JFrame frm1){
        frm2.setVisible(Visible);
        frm2.setLocationRelativeTo(null);
        frm1.dispose();
    }
    
    //deleting all content in text filed 
    //any textFieild must be like arr of strings
    public void CLEAR(JTextField[] jt ){
        for(int i = 0 ; i < jt.length ; i++){
            jt[i].setText("");
        }
    }
}
