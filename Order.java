package chang_jong;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Order extends JPanel {
	
	protected JPanel panel, panel_1;
	protected JList list, list_1, list_2; // 주문명, 수량, 가격
	protected JLabel lblNewLabel, lblNewLabel_1, lblNewLabel_2;
	protected JButton btnNewButton, btnNewButton_1, btnNewButton_2;
	public Order() {
		setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 420, 390);
		add(panel);
		panel.setLayout(null);
		
		list = new JList(new DefaultListModel());
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(12, 29, 220, 351);
		panel.add(list);
		
		list_1 = new JList(new DefaultListModel());
		list_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		list_1.setBounds(233, 29, 63, 351);
		panel.add(list_1);
		
		list_2 = new JList(new DefaultListModel());
		list_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		list_2.setBounds(297, 29, 111, 351);
		panel.add(list_2);
		
		lblNewLabel = new JLabel("\uC8FC\uBB38\uBA85");
		lblNewLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel.setLabelFor(list);
		lblNewLabel.setBounds(12, 14, 220, 15);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("\uC218\uB7C9");
		lblNewLabel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_1.setBounds(233, 14, 63, 15);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("\uAC00\uACA9");
		lblNewLabel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_2.setBounds(297, 14, 111, 15);
		panel.add(lblNewLabel_2);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 390, 420, 105);
		add(panel_1);
		panel_1.setLayout(null);
		
		btnNewButton = new JButton("\uCDE8\uC18C");
		btnNewButton.setBounds(12, 10, 201, 39);
		panel_1.add(btnNewButton);
		
		btnNewButton_1 = new JButton("\uCD08\uAE30\uD654");
		btnNewButton_1.setBounds(225, 10, 183, 39);
		panel_1.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("\uACC4\uC0B0");
		btnNewButton_2.setBounds(12, 56, 396, 39);
		panel_1.add(btnNewButton_2);

	}
	
}