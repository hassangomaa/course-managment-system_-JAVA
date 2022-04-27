package adminFrames;


import courseClass.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author egypt
 */
public class time {
    int Year ;
    int Day ;
    int Month ;
    int EYear ;
    int EDay ;
    int EMonth ;
    public time(int year , int month , int day  , int Eyear , int Emonth , int Eday ){
        Year = year ;
        Day = day ;
        Month = month ;
        EYear = Eyear ;
        EDay = Eday ;
        EMonth = Emonth ;
    }
    public time(int year , int month , int day , String Edate){
        //  yyyy - mm - hh
        //  0123 4 56 7 89
        //  yyyy - mm - h
        //  0123 4 56 7 8
        //  yyyy - m - hh
        //  0123 4 5 6 78
        String change = "" ;
        Year = year ;
        Day = day ;
        Month = month ;
        char slash1 = Edate.charAt(4);
        char slash2 = Edate.charAt(7);
        char slash3 = Edate.charAt(6);
        if(Edate.length() > 10 ){}
        if(slash1 == '-' && slash2 == '-' && Edate.length() == 10 ){
            String s1 = String.valueOf(Edate.charAt(0)).concat(String.valueOf(Edate.charAt(1)));
            String s2 = String.valueOf(Edate.charAt(2)).concat(String.valueOf(Edate.charAt(3)));
            change = s1.concat(s2);
            EYear = Integer.parseInt( change ) ;
            String change2 = String.valueOf(Edate.charAt(8)).concat(String.valueOf(Edate.charAt(9))) ;
            EDay = Integer.parseInt( change2 ) ;
            String change3 = String.valueOf(Edate.charAt(5)).concat(String.valueOf(Edate.charAt(6))) ;
            EMonth = Integer.parseInt( change3 );
        }
        else if(slash1 == '-' && slash2 == '-' && Edate.length() == 9 ){
            String s1 = String.valueOf(Edate.charAt(0)).concat(String.valueOf(Edate.charAt(1)));
            String s2 = String.valueOf(Edate.charAt(2)).concat(String.valueOf(Edate.charAt(3)));
            change = s1.concat(s2);
            EYear = Integer.parseInt( change ) ;
            String change2 = String.valueOf(Edate.charAt(7)).concat(String.valueOf(Edate.charAt(8))) ;
            EDay = Integer.parseInt( change2 ) ;
            String change3 = String.valueOf(Edate.charAt(5)) ;
            EMonth = Integer.parseInt( change3 );
        }
        else if(slash1 == '-' && slash3 == '-' && Edate.length() == 9 ){
            String s1 = String.valueOf(Edate.charAt(0)).concat(String.valueOf(Edate.charAt(1)));
            String s2 = String.valueOf(Edate.charAt(2)).concat(String.valueOf(Edate.charAt(3)));
            change = s1.concat(s2);
            EYear = Integer.parseInt( change ) ;
            String change2 = String.valueOf(Edate.charAt(8));
            EDay = Integer.parseInt( change2 ) ;
            String change3 = String.valueOf(Edate.charAt(5)).concat(String.valueOf(Edate.charAt(6))) ;
            EMonth = Integer.parseInt( change3 );
        }
    }
    public time(String DaTa1 , String Edate){
        char slassh1 = DaTa1.charAt(4);
        char slassh2 = DaTa1.charAt(7);
        char slassh3 = DaTa1.charAt(6);
        if(slassh1 == '-' && slassh2 == '-' && DaTa1.length() == 10 ){
            String s1 = String.valueOf(DaTa1.charAt(0)).concat(String.valueOf(DaTa1.charAt(1)));
            String s2 = String.valueOf(DaTa1.charAt(2)).concat(String.valueOf(DaTa1.charAt(3)));
            String change = s1.concat(s2);
            Year = Integer.parseInt( change ) ;
            String change2 = String.valueOf(DaTa1.charAt(8)).concat(String.valueOf(DaTa1.charAt(9))) ;
            Day = Integer.parseInt( change2 ) ;
            String change3 = String.valueOf(DaTa1.charAt(5)).concat(String.valueOf(DaTa1.charAt(6))) ;
            Month = Integer.parseInt( change3 );
        }
        else if(slassh1 == '-' && slassh2 == '-' && Edate.length() == 9 ){
            String s1 = String.valueOf(DaTa1.charAt(0)).concat(String.valueOf(DaTa1.charAt(1)));
            String s2 = String.valueOf(DaTa1.charAt(2)).concat(String.valueOf(DaTa1.charAt(3)));
            String change = s1.concat(s2);
            Year = Integer.parseInt( change ) ;
            String change2 = String.valueOf(DaTa1.charAt(7)).concat(String.valueOf(DaTa1.charAt(8))) ;
            Day = Integer.parseInt( change2 ) ;
            String change3 = String.valueOf(DaTa1.charAt(5)) ;
            Month = Integer.parseInt( change3 );
        }
        else if(slassh1 == '-' && slassh3 == '-' && DaTa1.length() == 9 ){
            String s1 = String.valueOf(DaTa1.charAt(0)).concat(String.valueOf(DaTa1.charAt(1)));
            String s2 = String.valueOf(DaTa1.charAt(2)).concat(String.valueOf(DaTa1.charAt(3)));
            String change = s1.concat(s2);
            Year = Integer.parseInt( change ) ;
            String change2 = String.valueOf(DaTa1.charAt(7)).concat(String.valueOf(DaTa1.charAt(8))) ;
            Day = Integer.parseInt( change2 ) ;
            String change3 = String.valueOf(DaTa1.charAt(8));
            System.out.println(change3);
            Month = Integer.parseInt( change3 );
        }
        else if(slassh1 == '-' && slassh3 == '-' && DaTa1.length() == 8 ){
            String s1 = String.valueOf(DaTa1.charAt(0)).concat(String.valueOf(DaTa1.charAt(1)));
            String s2 = String.valueOf(DaTa1.charAt(2)).concat(String.valueOf(DaTa1.charAt(3)));
            String change = s1.concat(s2);
            Year = Integer.parseInt( change ) ;
            String change2 = String.valueOf(DaTa1.charAt(7));
            Day = Integer.parseInt( change2 ) ;
            String change3 = String.valueOf(DaTa1.charAt(5));
            Month = Integer.parseInt( change3 );
        }
        /*===============================*/
        char slash1 = Edate.charAt(4);
        char slash2 = Edate.charAt(7);
        char slash3 = Edate.charAt(6);
        if(slash1 == '-' && slash2 == '-' && Edate.length() == 10 ){
            String s1 = String.valueOf(Edate.charAt(0)).concat(String.valueOf(Edate.charAt(1)));
            String s2 = String.valueOf(Edate.charAt(2)).concat(String.valueOf(Edate.charAt(3)));
            String change = s1.concat(s2);
            EYear = Integer.parseInt( change ) ;
            String change2 = String.valueOf(Edate.charAt(8)).concat(String.valueOf(Edate.charAt(9))) ;
            EDay = Integer.parseInt( change2 ) ;
            String change3 = String.valueOf(Edate.charAt(5)).concat(String.valueOf(Edate.charAt(6))) ;
            EMonth = Integer.parseInt( change3 );
        }
        else if(slash1 == '-' && slash2 == '-' && Edate.length() == 9 ){
            String s1 = String.valueOf(Edate.charAt(0)).concat(String.valueOf(Edate.charAt(1)));
            String s2 = String.valueOf(Edate.charAt(2)).concat(String.valueOf(Edate.charAt(3)));
            String change = s1.concat(s2);
            EYear = Integer.parseInt( change ) ;
            String change2 = String.valueOf(Edate.charAt(7)).concat(String.valueOf(Edate.charAt(8))) ;
            EDay = Integer.parseInt( change2 ) ;
            String change3 = String.valueOf(Edate.charAt(5)) ;
            EMonth = Integer.parseInt( change3 );
        }//4 6
        else if(slash1 == '-' && slash3 == '-' && Edate.length() == 9 ){
            String s1 = String.valueOf(Edate.charAt(0)).concat(String.valueOf(Edate.charAt(1)));
            String s2 = String.valueOf(Edate.charAt(2)).concat(String.valueOf(Edate.charAt(3)));
            String change = s1.concat(s2);
            EYear = Integer.parseInt( change ) ;
            String change2 = String.valueOf(Edate.charAt(7)).concat(String.valueOf(Edate.charAt(8)));
            EDay = Integer.parseInt( change2 ) ;
            String change3 = String.valueOf(Edate.charAt(8)) ;
            System.out.println(change3);
            EMonth = Integer.parseInt( change3 );
        }
        else if(slash1 == '-' && slash3 == '-' && Edate.length() == 8 ){
            String s1 = String.valueOf(Edate.charAt(0)).concat(String.valueOf(Edate.charAt(1)));
            String s2 = String.valueOf(Edate.charAt(2)).concat(String.valueOf(Edate.charAt(3)));
            String change = s1.concat(s2);
            EYear = Integer.parseInt( change ) ;
            String change2 = String.valueOf(Edate.charAt(7));
            EDay = Integer.parseInt( change2 ) ;
            String change3 = String.valueOf(Edate.charAt(5)) ;
            EMonth = Integer.parseInt( change3 );
        }
    }
    int compareTime() throws ParseException{
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdformat.parse( Year + "-" + Month + "-" + Day );
        Date d2 = sdformat.parse( EYear + "-" + EMonth + "-" + EDay );
        System.out.println("The date 1 is: " + sdformat.format(d1));
        System.out.println("The date 2 is: " + sdformat.format(d2));
        if(d1.compareTo(d2) > 0) {
           return 1 ;
        } else if(d1.compareTo(d2) < 0) {
           return -1 ;
        } else if(d1.compareTo(d2) == 0) {
           return 0 ;
        }
        else{
            return 10 ;
        }
    }
    public int period(){
        if(Year == EYear){
            return EMonth - Month ;
        }
        else{
            return 12 - Month + EMonth ;
        }
    }
    String test(){
        return EYear + " - " + EMonth + " - " + EDay ;
    }
}
