package chatserver;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class gui extends JPanel{
	JTextArea jtfchat = new JTextArea();
	
	gui(){
		setName("chat");
		setPreferredSize(new Dimension(710, 310));
		
		jtfchat.setText("k");
		jtfchat.setMinimumSize(new Dimension(700,300));
		jtfchat.setMaximumSize(new Dimension(700,300));
		jtfchat.setPreferredSize(new Dimension(700,300));
		jtfchat.setEditable(false);
		jtfchat.setLineWrap(true);
		
		add(jtfchat);
	}
	
	public void setText(){
		jtfchat.setText(server.inputchat);
	}
}
