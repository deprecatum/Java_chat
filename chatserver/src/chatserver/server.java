package chatserver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;

public class server extends JFrame implements Runnable{
	server(Component c){
		setTitle("Server");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		add(c);	
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
	
	static String inputchat="";
	
	public static void main(String args[]){
		gui g = new gui();
		server s = new server(g);		
		
		Thread semilha = new Thread(new networking());
		semilha.start();
		
		g.setFocusable(true);

		while(true){
			g.setText();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
