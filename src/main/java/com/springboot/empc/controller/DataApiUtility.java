package com.springboot.empc.controller;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataApiUtility {
	private static final Logger log = LoggerFactory.getLogger(DataApiUtility.class);
			//    private static final SimpleDateFormat formatYearMonth = new SimpleDateFormat("yyyy-MM");        
//    private static final SimpleDateFormat formatFullDate = new SimpleDateFormat("yyyy-MM-dd");
//    private static final SimpleDateFormat formatFullDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat formatFullDateTimeMs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static final SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 
//    private static final SimpleDateFormat formatHHmm = new SimpleDateFormat("HH:mm");
	public static long getEpochMSTime(String dateString, String format){
	    SimpleDateFormat df = new SimpleDateFormat(format);
	    Date date;
		try {
			date = df.parse(dateString);
		    return date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch blockF
			e.printStackTrace();
		}
		return -1;
	}
	
	public static long getEpochMsTimeFromDateTimeMs(String dateString){
	    Date date;
		try {
			date = formatFullDateTimeMs.parse(dateString);
		    return date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
    public static String getLastMonthFirstDate() {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        System.out.println("Last Month = "+ cal.getTime());
        return format.format(cal.getTime()) + "-01";
    }

	public static String getCurrentMonthFirstDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");        
        Calendar cal = Calendar.getInstance();
        System.out.println("Current Month = "+ cal.getTime());
        return format.format(cal.getTime()) + "-01";
    }

	public static String getPrevDate(int days) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days*(-1));
        System.out.println("Older by " + days + " days date = "+ cal.getTime());
        return format.format(cal.getTime());
    }

	public static String getPrevDateTimeTZFormat(int days, int hourOffset) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
        Calendar cal = Calendar.getInstance();
        //cal.add(Calendar.DATE, days*(-1));
        cal.add(Calendar.HOUR, (days*24 + hourOffset)*(-1));
        String output = format.format(cal.getTime());
        output = output.substring(0,10) + "T" + output.substring(11) + "Z";
        System.out.println("Older by " + days + " days with hour offset " + hourOffset + " == "+ output);
        return output;
    }
	
	public static String getTodayDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");        
        Calendar cal = Calendar.getInstance();
        //System.out.println("Today's date = "+ cal.getTime());
        return format.format(cal.getTime());
    }
	
	public static String getFullDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return format.format(cal.getTime());
    }

	public static int getCurrentHour() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.HOUR_OF_DAY);
    }
	public static String getHHMM() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        return format.format(cal.getTime());
    }
	
	public static String getNextDate(String curDate){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
		try {
			date = format.parse(curDate);
	        cal.setTime(date);
	        cal.add(Calendar.DATE, 1);
	        //System.out.println("curDate = " + curDate + ", new Date = " + format.format(cal.getTime()));
	        return format.format(cal.getTime());	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getPrevDate(String curDate){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
		try {
			date = format.parse(curDate);
	        cal.setTime(date);
	        cal.add(Calendar.DATE, -1);
	        //System.out.println("curDate = " + curDate + ", new Date = " + format.format(cal.getTime()));
	        return format.format(cal.getTime());	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static long ipToLong(String ipAddress) {
		String[] ipAddressInArray = ipAddress.trim().split("\\.");
		long result = 0;
		for (int i = 0; i < ipAddressInArray.length; i++) {
			int power = 3 - i;
			int ip = Integer.parseInt(ipAddressInArray[i]);
			result += ip * Math.pow(256, power);
		}
		//result += (Integer.parseInt(ipAddressInArray[i]) % 256 * Math.pow(256, power));
		return result;
	}

	public static String toHexString(String inputStr) {
		if(inputStr == null || inputStr.length() <= 0) return inputStr;
		byte[] ba = inputStr.getBytes(Charset.forName("UTF-8"));
	    StringBuilder str = new StringBuilder();
	    for(int i = 0; i < ba.length; i++)
	        str.append(String.format("%02x", ba[i]));
	    return str.toString();
	}

	public static String fromHexString(String hex) {		
		if(hex == null || hex.length() <= 0) return hex;
	    StringBuffer str = new StringBuffer();
	    for (int i = 0; i < hex.length(); i+=2) {
	        str.append((char) Integer.parseInt(hex.substring(i, i + 2), 16));
	    }
	    return str.toString();
	}
	public static int toInt(String number) {		
		int value=0;
		try{
			value=Integer.parseInt(number);
		}catch(Exception e){
			value=0;
		}
	    return value;
	}
	
	
}