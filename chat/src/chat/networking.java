package chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class networking implements Runnable{
	
	int port = 61001;
	
	Socket s=null;
	
	PrintWriter writer = null;
	
	String buff=null;
	
	File reg = new File("log.txt");
	PrintWriter logWriter=null;
	
	public networking(){
		while(s==null){
			try {
				s = new Socket("localhost", port);
				System.out.println("conecçao criada");
				writer = new PrintWriter(s.getOutputStream());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			enviar();
			receber();
		}
	}//run
	
	public void enviar(){
		if(chat.sendmsg){
			System.out.println("Envio on");
			writer.println(chat.sendtext);
			writer.flush();
			System.out.println("sent text");
			chat.sendtext="";
			chat.sendmsg=false;
		}
	}
	
	public void receber(){
		System.out.println("Receber on");
		BufferedReader read=null;
		
		try {
			InputStreamReader serverinput = new InputStreamReader(s.getInputStream());
			read = new BufferedReader(serverinput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //envia a string para o socket
				
		
		try {
			buff = read.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(buff!=null){
			chat.log+="\n"+buff;

			try {
				logWriter = new PrintWriter(new FileWriter(reg,true)); //permite escritura para o ficheiro sem fazer overwrite
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			logWriter.println("\n"+buff);
			logWriter.flush();
			
			buff=null;
		}
	}
	
}//fim classe
