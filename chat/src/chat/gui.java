package chat;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class gui extends JPanel implements ActionListener, KeyListener{
	
	
	
	JTextField jtfusername = new JTextField(11);
	
	
	String userin = "";
	String username = "Tabaibe";
	
	JTextArea jtfuserin = new JTextArea();
	
	gui(){
		setName("gui");
		setPreferredSize(new Dimension(800, 400));
		
		
		//right side code start
		JButton send = new JButton("Send");
		send.setActionCommand("send");
		send.addActionListener(this);
		send.setMaximumSize(new Dimension(90, 100));
		send.setPreferredSize(new Dimension(90, 100));
		
		
		jtfusername.setMaximumSize(new Dimension(100, 50));
		jtfusername.setPreferredSize(new Dimension(100, 50));
		jtfusername.setText(username);
		
		Container conb = new Container(); //container buttons
		send.setAlignmentX(conb.CENTER_ALIGNMENT);
		conb.setMinimumSize(new Dimension(90, 100));
		conb.setPreferredSize(new Dimension(90, 100));
		conb.setLayout(new BoxLayout(conb, BoxLayout.Y_AXIS));
		conb.add(jtfusername);
		conb.add(send);
		//right side code finish
		
		
		//left side code start
		JTextArea jtfchat = new JTextArea(chat.log); //display das mensagens
		jtfchat.setMaximumSize(new Dimension(700, 300));
		jtfchat.setPreferredSize(new Dimension(700, 300));
		jtfchat.setEditable(false);
		jtfchat.setLineWrap(true);
		
		
		jtfuserin.setMaximumSize(new Dimension(700, 90));
		jtfuserin.setPreferredSize(new Dimension(700, 90));
		jtfuserin.setLineWrap(true);
		
		JScrollPane scroll = new JScrollPane(jtfchat);
		scroll.setHorizontalScrollBarPolicy(scroll.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(scroll.VERTICAL_SCROLLBAR_ALWAYS);
		
		Container con = new Container(); //container jtextfield
		scroll.setAlignmentX(con.CENTER_ALIGNMENT);
		jtfuserin.setAlignmentX(con.CENTER_ALIGNMENT);
		con.setMaximumSize(new Dimension(700,400));
		con.setPreferredSize(new Dimension(700,400));
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS)); //http://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
		con.add(scroll);
		con.add(jtfuserin);
		//left side code finish
		
		
		//joining sides in gui
		add(con, BorderLayout.WEST); //add container textareas
		add(conb, BorderLayout.EAST); //add container buttons
	}

	
	
	//Gui Buttons action methods start
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="send"){
			sendtext();
			//funçao enviar text
		}
	}
	//Gui Buttons action methods finish
	
	
	
	//Keyboard button methods
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			sendtext();
			//funçao enviar text
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	//Keyboard button methods
	
	
	
	public void sendtext(){
		if(jtfusername.getText()!=null){
			chat.sendtext=jtfusername.getText()+": "+userin;
			chat.log+="\n+"+chat.sendtext;
			chat.sendmsg=true;
		}
	}
	
	
	public void getuserin(){
		if(username.length()>=12){
			username=username.substring(1, 12);
			jtfusername.setText(username); //permite manipulaçao do texto no textfield do username
		}
		else{
			username = jtfusername.getText();
		}
		if(userin.length()>=499){
			userin=userin.substring(1, 499);
			jtfuserin.setText(userin); //permite manipulaçao do texto no textarea da introduçao de texto
		}
		else{
			userin = jtfuserin.getText();
		}
		
	}
	
}

