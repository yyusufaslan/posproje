package service;

import java.io.*;
import java.io.BufferedWriter;;
public class printText {


	public void kaydet(  String a  ,String b,String c,String d,String e  ) throws Exception{		
		
	File f = new File("rapor.txt");
	FileWriter fw = new FileWriter(f, true);
	BufferedWriter yaz = new BufferedWriter(fw);
	yaz.write("Ara Toplam Tutar :" +c+"�ndirim :"+e+"Kredi Karti :"+a+"Nakit :"+b+"Toplam Tutar :"+d+"\n");
	yaz.write("***********************F�� SONU*********************** \n");
	yaz.write("           ********************************** \n");
	yaz.flush();
	yaz.close();
	
	}	

}