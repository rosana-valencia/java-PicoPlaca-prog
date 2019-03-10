/*
Using object-oriented, tested code write a "pico y placa" predictor. The inputs should be a 
license plate number (the full number, not the last digit), a date (as a String), 
and a time, and the program will return whether or not that car can be on the road.
Pico y Placa Quito Ecuador
En los primeros días del mes de enero del 2010, se anunció la aplicación de esta medida en 
la ciudad de Quito en Ecuador debido a la congestión que se presenta en la ciudad.6 Esta 
medida empezó a funcionar en marzo de 2010, donde por un día a la semana los autos no pueden 
circular según el último número de la placa, en dos turnos durante las horas pico 
entre las 7:00 y las 9:30 en la mañana y entre las 16:00 y las 19:30 en la tarde y noche, 
el calendario de aplicación de la medida es el siguiente:
	Dia    		No Placa
	Lunes		1 y 2
	Martes      3 y 4
	Miercoles   5 y 6 
	Jueves      7 y 8
	Viernes     9 y 0
	
*/

import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;  
import java.util.Scanner;
		

public class MyProg {
	
    public static String getDay (int day, int month, int year) {
			LocalDate ld = LocalDate.of(year,month,day);
			return ld.getDayOfWeek().name();
	}
	
	public static boolean allowedOnRoad (String inputPlate, String day) {
			
		switch (day) {
			case "MONDAY": if (inputPlate.endsWith("1") || inputPlate.endsWith("2")) {
							return false;
			}
			break;
			case "TUESDAY": if (inputPlate.endsWith("3") || inputPlate.endsWith("4")) {
							return false;
			}
			break;
			case "WEDNESDAY": if (inputPlate.endsWith("5") || inputPlate.endsWith("6")) {
							return false;
			}
			break;
			case "THURSDAY": if (inputPlate.endsWith("7") || inputPlate.endsWith("8")) {
							return false;
			}
			break;
			case "FRIDAY": if (inputPlate.endsWith("9") || inputPlate.endsWith("0")) {
							return false;
			}
			break;	
		}
		return true;
	}
			
	public static String getTime (String inputTime) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("H:mm");

		String allow = null;
        String amS = "06:59";
        String amE = "09:31";

        String pmS = "15:59";
		String pmE = "19:31";

        LocalTime InTime = LocalTime.parse(inputTime, format);
        LocalTime amTime1 = LocalTime.parse(amS, format);
        LocalTime amTime2 = LocalTime.parse(amE, format);
		LocalTime pmTime1 = LocalTime.parse(pmS, format);
        LocalTime pmTime2 = LocalTime.parse(pmE, format);

        if ( InTime.isAfter(amTime1) && InTime.isBefore(amTime2) ) {
	          allow = "No";		
        } else if ( InTime.isAfter(pmTime1) && InTime.isBefore(pmTime2) ) {
			    allow = "No";
			} else	{
                allow = "Yes";
			}
      
        return allow; 
	}	
		
   public static void main(String[] args){
	  Scanner in = new Scanner(System.in);
	  DateTimeFormatter format1 = DateTimeFormatter.ofPattern("d/M/yyyy");
           
		   
      System.out.print("Enter the plate number:  ");
	  String plateNum = in.next();

	  System.out.print("Enter date as dd/mm/yyy:  ");
	  String dateIn = in.next();
	  
	  System.out.print("Enter Time as hh:mm:  ");
	  String timeIn = in.next();
	  
	  LocalDate dateNew = LocalDate.parse(dateIn,format1);   
	  int d = dateNew.getDayOfMonth();
      int m = dateNew.getMonthValue() ;
      int y	= dateNew.getYear() ;

	  String finalday = getDay(d,m,y);

	  String allowedTime = getTime(timeIn);
	  
	  boolean retVal;
	  if (allowedTime == "Yes") {
		  System.out.println("This car can be on the road");
	  }
	  
	  if (allowedTime == "No") {
		  retVal = allowedOnRoad(plateNum,finalday);
		  if (retVal) {
		  System.out.println("This car can be on the road");
		  } else { 
			System.out.println("This car can not be on the road");
		  }
	  }
    }
	  
   }


