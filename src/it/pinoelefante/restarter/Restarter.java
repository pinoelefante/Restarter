package it.pinoelefante.restarter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class Restarter {
	public static void main(String[] args) {
		if(args.length==1){
			String proc=args[0];
			if(exists(proc)){
				int numero_avvii=1;
				while(true){
					writeStart(numero_avvii);
					System.out.println("Start: "+numero_avvii);
					try {
						Process p=Runtime.getRuntime().exec(cmdJar(proc));
						p.waitFor();
						numero_avvii++;
					} 
					catch (IOException e) {
						e.printStackTrace();
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			else {
				System.out.println("The file does not exists");
			}
		}
		else {
			System.out.println("Usage: Restarter <path_exe>");
		}
	}
	private static boolean exists(String s){
		File f=new File(s);
		return f.exists();
	}
	private static void writeStart(int n_start){
		try {
			FileWriter fw=new FileWriter("restarter.log", true);
			Date now=new Date();
			fw.write(DateFormat.getInstance().format(now)+" - start number: "+n_start+"\n");
			fw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static String[] cmdJar(String p){
		if(p.toLowerCase().endsWith(".jar")){
			String[] cmd={"java","-jar",p};
			return cmd;
		}
		else {
			String[] cmd={p};
			return cmd;
		}
	}
}
