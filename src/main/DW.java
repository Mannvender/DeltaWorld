package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class DW extends JFrame implements ActionListener{
	public static final int BOARD_SIZE = 3;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	public DW(){
		super.setTitle("DWorld");
		super.setSize(600, 600);
		Font font = new Font("Comic Sans", 1, 145);
		super.setResizable(false);
		super.setVisible(true);
	}

}
