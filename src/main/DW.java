package main;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pilot.bed;
import pilot.door;
import pilot.doorScrew;
import pilot.key;
import pilot.lock;
import pilot.movable;
import pilot.screwDriver;

public class DW extends JFrame implements ActionListener {
	public static final int BOARD_SIZE = 3;
	public boolean playerInside = true;
	String input;

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "name of all objects goes here", "Message", JOptionPane.ERROR_MESSAGE);
		input = JOptionPane.showInputDialog("which object to interact with?");

		makemove(input);
		status gs = this.getStatus();
		if (gs != status.incomplete) {

			int choice = JOptionPane.showConfirmDialog(this, "level complete ,wanna Restart ?");
			if (choice == JOptionPane.YES_OPTION) {

			} else {
				super.dispose();
			}
		}
	}

	private status getStatus() {
		if (!playerInside) {
			return status.complete;
		} else {
			return status.incomplete;
		}
	}

	private void makemove(String input) {
		

	}

	public static enum status {
		incomplete, complete
	}

	public DW() {
		super.setTitle("DWorld");
		super.setSize(600, 600);
		Font font = new Font("Comic Sans", 1, 145);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setResizable(false);
		Button b = new Button("Click me");
		super.add(b);
		b.addActionListener(this);

		// initializing all objects around
		movable fixed = new movable(false);
		bed bed1 = new bed("bed1", fixed);
		bed bed2 = new bed("bed1", fixed);
		lock locked = new lock(true);
		doorScrew visible = new doorScrew(true);
		door doorFront = new door("doorFront", locked, visible);
		key cupBoard = new key("cupBoardKey", false, false);
		screwDriver sD = new screwDriver("screwDriver", false, false);
		super.setVisible(true);

	}

}
