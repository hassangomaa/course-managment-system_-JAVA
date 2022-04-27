package InstructorPart;

import Home_pages.Home;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.*;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.swing.*;


public class HomePage {
    int scrollMoving = 0,
            intialScrollVal = 9,
            currID,
            index2 = 1
            ,currLID;
    public int index;
    public ResultSet rs;
    JLabel[] label = new JLabel[100];
    Label subjectLabel;
    Frame frame = new Frame("Home Page"),
            PostFrame;
    JPanel panel = new JPanel();
    Button sendBtn ,
            updateBtn;
    JButton postsbtn = new JButton();
    JScrollPane scroll;
    TextField subject;
    TextArea textpost,
            showMsg,
            textArea = new TextArea();
    courseClass.DATABASE db= new courseClass.DATABASE("root","courses");
    LabelMouseListener LabelPopMenu;
    JMenuItem edit,
            delete;
    PreparedStatement pst = db.ps();
    
    public HomePage(String y){
        if( y == "student" ){
             Updateview(y);
            showAllButtons();
            showScrollPane();
            showMainFrame();
            refresh(y);
        }
        else{
            showPostBtn();
            showAllButtons();
            showScrollPane();   
            showMainFrame();
            refresh("");
        }
    }
    
    public void refresh(String name)
    {
        scroll.setVisible(false);
        Updateview(name);
        scroll.setVisible(true);
    }
      
    public void showUpdatePostFrame(int index)
    {
        PostFrame = new Frame("Posts");
        PostFrame.setLayout(null);
        PostFrame.setSize(375, 250);
        PostFrame.setVisible(true);
        PostFrame.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e) {PostFrame.dispose();}});
        PostFrame.setLocationRelativeTo(frame);
        subjectLabel = new Label("Subject");
        subjectLabel.setBounds(10,30,50,20);
        subject = new TextField();
        textpost = new TextArea(5,5);
        subject.setBounds(80, 30,280,20);
        textpost.setBounds(8, 60,360,150);
        subject.setText(label[index].getText());
        textpost.setText(db.getcontent(currLID));
    }
    
    public void addToUpdatePostFrame()
    {
        PostFrame.add(updateBtn);
        PostFrame.add(subjectLabel);
        PostFrame.add(subject);
        PostFrame.add(textpost);
    }
    
    public void showMainFrame()
    {
        frame.setVisible(true);
        frame.setLocation(362,117);
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setBackground(Color.white);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e) 
        {frame.dispose();}});
        showMsg = new TextArea(5,5);
        showMsg.setEditable(false);
        showMsg.setBounds(255,100,320,330);
        addToMainFrame();
    }
    
    public void addToMainFrame()
    {
        frame.add(postsbtn);
        frame.add(scroll);
        frame.add(showMsg);
    }
  
    public void showPostBtn(){
        URL url = HomePage.class.getResource("pt.png");

        postsbtn = new JButton(new ImageIcon(url));
        postsbtn.setBackground(Color.white);
        postsbtn.setBorder(null);
        postsbtn.setBounds(10,50,60,40);
        postsbtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionPostBtn();
            } 
        });
        //new WindowAdapter(){public void windowClosing(WindowEvent e) {PostFrame.dispose();}};
    }
    
    public void actionPostBtn(){
        showPostFrame();
        addToPostFrame();
    }
    
    public void showPostFrame()
    {
        PostFrame = new Frame("Posts");
        PostFrame.setLayout(null);
        PostFrame.setSize(375, 250);
        PostFrame.setVisible(true);
        PostFrame.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e) {PostFrame.dispose();}});
        PostFrame.setLocationRelativeTo(frame);
        subjectLabel = new Label("Subject");
        subjectLabel.setBounds(10,30,50,20);
        subject = new TextField();
        textpost = new TextArea(5,5);
        subject.setBounds(80, 30,280,20);
        textpost.setBounds(8, 60,360,150);
    }
    
    public void addToPostFrame()
    {
        PostFrame.add(sendBtn);
        PostFrame.add(subjectLabel);
        PostFrame.add(subject);
        PostFrame.add(textpost);
    }
    
    public void showSendBtn()
    {
        sendBtn = new Button("Done");
        sendBtn.setSize(60,30);
        sendBtn.setLocation(300,210);
        sendBtn.setBackground(Color.green);
        sendBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {            
                scroll.setVisible(false);
                sendBtn(evt,index2);
                createPanel();  
                scroll.setVisible(true);
            } 
            });
    }
    
    public void showUpdateBtn()
    {
        updateBtn = new Button("Update");
        updateBtn.setSize(60,30);
        updateBtn.setLocation(300,210);
        updateBtn.setBackground(Color.green);
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {            
                scroll.setVisible(false);
                db.updatesubj(subject.getText(), currLID);
                db.updatecont(textpost.getText(), currLID);
                Updateview("");
                scroll.setVisible(true);
            } 
            });
    }
    
    public void showAllButtons()
    {
        
        showSendBtn();
        showUpdateBtn();
    }

    public void sendBtn(java.awt.event.ActionEvent evt,int d) 
    {
    label[d]  = new JLabel(subject.getText()); 
    label[d].setFont(new Font("Serif", Font.BOLD, 11));
    label[d].setSize(380, 50);
    label[d].setBackground(Color.lightGray);
    panel.add(label[d]);
    String txt = textpost.getText();
    db.insertPost(pst,subject.getText(), txt);
    Updateview("");
    }
    
    public void Updateview(String name){
        panel.removeAll();
        index = 1;
        rs = db.resultSetOfPosts();
        try {
            while(rs.next()){
                label[index]  = new JLabel(rs.getString("subject"));
                label[index].setFont(new Font("Serif", Font.BOLD, 18));
                label[index].setOpaque(true);
                label[index].setBackground(Color.lightGray);
                currID = db.getPostID(rs);
                LabelsPopMenu(index,name);
                createPanel();
                label[index].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        showMsg.setText( "\nSubject : " + db.getSubject(currLID) +"\nDate : " + new java.util.Date() + "\n" + "=====================" +  "\n\n" +db.getcontent(currLID));
                        showMsg.setFont(new Font("Serif", Font.BOLD, 18));
                    }
                       
                });
                panel.add(label[index]);
                index++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JPanel createPanel()
    {
        panel.setLayout(new GridLayout(100,1, 0, 1)); 
        panel.setBackground(Color.white);
        return panel;
    }
    
    public void showScrollPane()
    {
        scroll = new JScrollPane(createPanel());
        scroll.setSize(240,490);
        scroll.setLocation(8,100);
        scroll.setBackground(Color.white);
    }
    
    public void LabelsPopMenu(int index,String name){
            JPopupMenu popupMenu  = new JPopupMenu();
            LabelPopMenu = new LabelMouseListener(label[index],currID);
            edit = new JMenuItem("Edit");
            delete = new JMenuItem("Delete");
            label[index].setComponentPopupMenu(popupMenu);
            label[index].addMouseListener(LabelPopMenu);
            edit.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(name != "student"){
                        showUpdatePostFrame(index);
                        addToUpdatePostFrame();
                    }
                } 
            });
            delete.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(name != "student"){
                        db.deleted(currLID);
                        scroll.setVisible(false);
                        Updateview("");
                        scroll.setVisible(true);
                    }
                } 
            });
            popupMenu.add(edit);
            popupMenu.add(delete);
        /*else{
            popupMenu.removeAll();
        }*/
    }
    
    public class LabelMouseListener extends MouseAdapter {
        private int pos;
        private JLabel label;
        
        public LabelMouseListener(JLabel label,int pos) {
            this.label = label;
            this.pos = pos;
        }
        
        public void mousePressed(MouseEvent event) {
        // selects the row at which point the mouse is clicked
        currLID = pos;
        }
    }

}
