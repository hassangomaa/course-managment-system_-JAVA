/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminFrames;

/**
 *
 * @author egypt
 */
public class CoursesInfo {
    String CoNa ;
    String Bran ;
    String Room ;
    String nosa ;
    String pric ;
    String mnth ;
    String strt ;
    String prnt ;
    String EndC ;
    String Inst ;
    CoursesInfo(){}
    /*=====================get=====================*/
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
    /*=====================set=====================*/
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
    
}
