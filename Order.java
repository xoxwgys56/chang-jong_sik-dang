package chang_jong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Order extends JPanel {

	public Order() {
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		int wid=(res.width/4);				//�ֹ� �г� ����
		int hei=((res.height/10)*9);			//�ֹ� �г� ����
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setSize(wid, hei);
		JPanel ListPanel = new JPanel();			//����Ʈ�� �� �ִ� �г�
		ListPanel.setBounds(0,0, wid, (hei/5)*4);
		add(ListPanel);
		ListPanel.setLayout(null);
		
		JLabel NameLabel = new JLabel("�ֹ���");			//���ĸ��
		NameLabel.setBounds(0, 0, wid/2, 20);
		NameLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ListPanel.add(NameLabel);

		JLabel NumLabel = new JLabel("����");				//������
		NumLabel.setBounds(wid/2, 0, wid/4, 20);
		NumLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ListPanel.add(NumLabel);
		
		JLabel PriceLabel = new JLabel("����");			//���ݶ�
		PriceLabel.setBounds((wid/2)+(wid/4), 0, wid/4, 20);
		PriceLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ListPanel.add(PriceLabel);
		
		JList NameList = new JList();					//���ĸ� ����Ʈ							//������� ------------------------------------------------------
		NameList.setBounds(0, 20, wid/2, (hei/5)*4-20-hei/5/2);
		NameList.setBorder(new LineBorder(new Color(0, 0, 0)));
		ListPanel.add(NameList);
		
		JList NumList = new JList();					//���� ����Ʈ
		NumList.setBounds(wid/2, 20, wid/4, (hei/5)*4-20-hei/5/2);
		NumList.setBorder(new LineBorder(new Color(0, 0, 0)));
		ListPanel.add(NumList);
		
		JList PriceList = new JList();					//���� ����Ʈ
		PriceList.setBounds((wid/2)+(wid/4), 20, wid/4, (hei/5)*4-20-hei/5/2);
		PriceList.setBorder(new LineBorder(new Color(0, 0, 0)));
		ListPanel.add(PriceList);
		
		JLabel TotalLabel = new JLabel("�� ����  : ");			//���ݶ�								
		TotalLabel.setBounds(0, (hei/5)*4-hei/5/2, wid/4, hei/5/2);
		ListPanel.add(TotalLabel);
		
		JTextField TotalText = new JTextField();			//���� �ؽ�Ʈ �ʵ�
		TotalText.setBounds(wid/4, (hei/5)*4-hei/5/2, wid/4*3, hei/5/2);
		TotalText.setEditable(false);
		ListPanel.add(TotalText);																//��������� ������ ����---------------------------------------------
		
		JPanel ButtonPanel = new JPanel();				//��ư �г�
		ButtonPanel.setBounds(0, (hei/5)*4, wid, hei/5);
		add(ButtonPanel);
		ButtonPanel.setLayout(null);
		
		JButton CancelButton = new JButton("���");
		CancelButton.setBounds(0, 0, wid/2, hei/5/2);
		ButtonPanel.add(CancelButton);
		
		JButton ResetButton = new JButton("�ʱ�ȭ");
		ResetButton.setBounds(wid/2, 0, wid/2, hei/5/2);
		ButtonPanel.add(ResetButton);
		
		JButton CalButton = new JButton("���");
		CalButton.setBounds(0, hei/5/2, wid, hei/5/2);
		ButtonPanel.add(CalButton);

	}
	
}
