package chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;

public class chat extends JFrame{
	
	static String log=""; //texto recebido 97 caracteres por linha
	static String sendtext = ""; //texto escrito pelo user
	
	static boolean sendmsg=false;
	
	chat(Component c){
		setTitle("Chat");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		add(c);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
	
	public static void main(String args[]){
		gui g = new gui();
		
		g.setFocusable(true);
		
		chat c = new chat(g);
		
		networking n = new networking();
		Thread rede = new Thread(n);
		rede.start();
		
		while(true){
			g.getuserin();
		} //fim do while
		
	}

	
}
