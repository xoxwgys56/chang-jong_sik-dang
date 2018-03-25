package Changzong;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Order extends JPanel {

	/**
	 * Create the panel.
	 */
	public Order() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 420, 390);
		add(panel);
		panel.setLayout(null);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(12, 29, 220, 351);
		panel.add(list);
		
		JList list_1 = new JList();
		list_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		list_1.setBounds(233, 29, 63, 351);
		panel.add(list_1);
		
		JList list_2 = new JList();
		list_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		list_2.setBounds(297, 29, 111, 351);
		panel.add(list_2);
		
		JLabel lblNewLabel = new JLabel("\uC8FC\uBB38\uBA85");
		lblNewLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel.setLabelFor(list);
		lblNewLabel.setBounds(12, 14, 220, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC218\uB7C9");
		lblNewLabel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_1.setBounds(233, 14, 63, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uAC00\uACA9");
		lblNewLabel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_2.setBounds(297, 14, 111, 15);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 390, 420, 105);
		add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("\uCDE8\uC18C");
		btnNewButton.setBounds(12, 10, 201, 39);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uCD08\uAE30\uD654");
		btnNewButton_1.setBounds(225, 10, 183, 39);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\uACC4\uC0B0");
		btnNewButton_2.setBounds(12, 56, 396, 39);
		panel_1.add(btnNewButton_2);

	}
	
}