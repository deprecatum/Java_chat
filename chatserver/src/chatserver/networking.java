package chatserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class networking implements Runnable{
	
	ArrayList<Socket> peers = new ArrayList<Socket>(); //lista de conecçoes
	
	ArrayList<Thread> coms = new ArrayList<Thread>(); //threads das conecçoes
	
	Socket prevSoc = new Socket();
	
	int port = 61001;
	
	ServerSocket getsock=null;

	InputStreamReader serverinput = null;
	
	String buff=null;
	
	File log = new File("tabaibe.txt");
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("networking thread comecou");
		
		try {
			getsock = new ServerSocket(port);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Socket currentSoc=null;
		
		while(true){
			System.out.println("Handling com");
			
			int counter=0;
			
			System.out.println(counter);
			
			try {
				currentSoc = getsock.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(currentSoc!=prevSoc||prevSoc==null){
				peers.add(currentSoc);
				prevSoc=currentSoc;
				coms.add(new Thread(new socketHandle(currentSoc,counter)));
				coms.get(counter).start();
				counter++;
				currentSoc=null;
			}
			
			
		}
			
	}//fim run

	public class socketHandle implements Runnable{
		Socket soc=null;
		
		int socId;
		
		String buff=null;
		
		socketHandle(Socket s, int id){
			soc=s;
			socId=id;
			System.out.println(socId);
		}
		
		boolean on=true;

		@Override
		public void run() {
			System.out.println("Com on");
			// TODO Auto-generated method stub
			
			while(on){
				System.out.println("Loopin around");
				if(soc.isConnected()==true){
					System.out.println("soc is legit");
					receber();
				}
				else{
					on=false;
				}
			}
				
			System.out.println(socId+" off");
		}
		
		
		synchronized public void receber(){
			BufferedReader read = null;
			
			try {
				read= new BufferedReader(new InputStreamReader(soc.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				on=false;
			}
			
			
			try {
				buff=read.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				on=false;
			}
			
			if(buff!=null){
				System.out.println(buff);
				server.inputchat+="\n"+buff;
				relay();
				buff=null;
			}
			
			
		}
		
		synchronized public void relay(){
			PrintWriter writer = null;
			for(int i=0;i==peers.size();i++){
				if(i==socId){
					return;
				}
				else{
					try {
						writer = new PrintWriter(peers.get(i).getOutputStream());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						on=false;
					}
					writer.println(buff);
					writer.flush();
					writer=null;
				}
			}
		}
	}
	
	
} //fim classe
