/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseClass;

/**
 *
 * @author egypt
 */
public class person {
    private static String FUllName ;
    private String Phone ;
    private String HomePhone ;
    private String Address ;
    private String CourseName ;
    private String Age ;
    
    public static String GetFName(){
        return FUllName;
    }
    
    String GetPhone(){
        return Phone;
    }
    
    String GetHomePhone(){
        return HomePhone;
    }
    
    String GetAddress(){
        return Address;
    }
    
    String GetCourseName(){
        return CourseName;
    }
    
    String GetAge(){
        return Age;
    }
    /*============================set================================*/
    void SetPhone(String PhoneUS ){
        Phone = PhoneUS;
    }
    
    void SetHomePhone(String HomePhoneUS){
        HomePhone = HomePhoneUS;
    }
    
    void SetAddress(String AddressUS){
        Address = AddressUS;
    }
    
    void SetCourseName(String CourseNameUS){
        CourseName = CourseNameUS;
    }
    
    void SetAge(String AgeUS){
        Age = AgeUS;
    }
    
    public static void SetFName(String FNameUS){
        FUllName = FNameUS ;
    }
}
