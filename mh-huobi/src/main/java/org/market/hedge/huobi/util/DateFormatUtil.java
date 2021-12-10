package org.market.hedge.huobi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtil {

    public static String format(Date date){
        DateFormat df1dd = new SimpleDateFormat("dd", Locale.ENGLISH);
        DateFormat df1MMM = new SimpleDateFormat("MMM", Locale.ENGLISH);
        DateFormat df1yyyy = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        String yyyy=df1yyyy.format(date);
        return df1dd.format(date)+df1MMM.format(date).toUpperCase()+yyyy.substring(yyyy.length()-2);
    }

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());					//放入Date类型数据

        System.out.println(calendar.get(Calendar.YEAR));				//获取年份
        System.out.println(calendar.get(Calendar.MONTH));					//获取月份
        System.out.println(calendar.get(Calendar.DATE));
    }

}
