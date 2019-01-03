package com.example.rizwanaslam.snote.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteUtils {

     public static  String dateFromLong(long time){
         DateFormat format=new SimpleDateFormat("EEE.dd MM YYYY 'at' hh:mm:aaa",Locale.US);
         return format.format(new Date(time));
     }
}
