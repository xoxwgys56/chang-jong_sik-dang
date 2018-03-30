package chang_jong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Order extends JPanel {

	protected JPanel ListPanel, ButtonPanel;
	protected JList NameList, NumList, PriceList; // �ֹ���, ����, ����
	protected JLabel NameLabel, NumLabel, PriceLabel, TotalLabel;
	protected JButton CancelButton, ResetButton, CalButton;
	protected JTextField TotalText;
	private DefaultListModel listmodel;
	public Order() {
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		int wid=(res.width/4);				//�ֹ� �г� ����
		int hei=((res.height/10)*9);			//�ֹ� �г� ����
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setSize(wid, hei);
		//����Ʈ�� �� �ִ� �г�
		ListPanel = new JPanel();			
		ListPanel.setBounds(0,0, wid, (hei/5)*4);
		add(ListPanel);
		ListPanel.setLayout(null);
		
		//���ĸ��
		NameLabel = new JLabel("�ֹ���");			
		NameLabel.setBounds(0, 0, wid/2, 20);
		NameLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ListPanel.add(NameLabel);
		
		//������
		NumLabel = new JLabel("����");				
		NumLabel.setBounds(wid/2, 0, wid/4, 20);
		NumLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ListPanel.add(NumLabel);
		
		//���ݶ�
		PriceLabel = new JLabel("����");			
		PriceLabel.setBounds((wid/2)+(wid/4), 0, wid/4, 20);
		PriceLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ListPanel.add(PriceLabel);
		
		//���ĸ� ����Ʈ	
		NameList = new JList(new DefaultListModel());								
		NameList.setBounds(0, 20, wid/2, (hei/5)*4-20-hei/5/2);
		NameList.setBorder(new LineBorder(new Color(0, 0, 0)));
		ListPanel.add(NameList);
		
		//���� ����Ʈ
		NumList = new JList(new DefaultListModel());					
		NumList.setBounds(wid/2, 20, wid/4, (hei/5)*4-20-hei/5/2);
		NumList.setBorder(new LineBorder(new Color(0, 0, 0)));
		ListPanel.add(NumList);
		
		//���� ����Ʈ
		PriceList = new JList(new DefaultListModel());					
		PriceList.setBounds((wid/2)+(wid/4), 20, wid/4, (hei/5)*4-20-hei/5/2);
		PriceList.setBorder(new LineBorder(new Color(0, 0, 0)));
		ListPanel.add(PriceList);
		
		//���ݶ�
		TotalLabel = new JLabel("�� ����  : ");											
		TotalLabel.setBounds(0, (hei/5)*4-hei/5/2, wid/4, hei/5/2);
		ListPanel.add(TotalLabel);
		
		//�Ѱ��� �ؽ�Ʈ �ʵ�
		TotalText = new JTextField("0 ��");			
		TotalText.setBounds(wid/4, (hei/5)*4-hei/5/2, wid/4*3, hei/5/2);
		TotalText.setEditable(false);
		ListPanel.add(TotalText);																
		
		ButtonPanel = new JPanel();				//��ư �г�
		ButtonPanel.setBounds(0, (hei/5)*4, wid, hei/5);
		add(ButtonPanel);
		ButtonPanel.setLayout(null);
		
		//��� ��ư
		CancelButton = new JButton("���");
		CancelButton.setBounds(0, 0, wid/2, hei/5/2);
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectIndex = NameList.getSelectedIndices();

				// ����Ʈ �������� ���ų� ������ ���� �ʾ��� ��� ����
				if ( selectIndex == null) {
					return;
				}
				
				// ���ÿ� �ټ� ��Ұ� ������ �ݺ���
				// �ټ� ���� �� ���� �ε������� ���� ��� �迭���� ���ܰ� �߻��ϹǷ� ������ ���� ������
				for ( int i = selectIndex.length - 1; i >= 0 ; i-- ) {
					listCancel(listmodel,selectIndex[i]);
				}
					
				
			
			}
		});
		ButtonPanel.add(CancelButton);
		
		//�ʱ�ȭ ��ư
		ResetButton = new JButton("�ʱ�ȭ");
		ResetButton.setBounds(wid/2, 0, wid/2, hei/5/2);		
		ResetButton.addActionListener(new ActionListener() {
			// ����Ʈ ������ ��ü ����
			public void actionPerformed(ActionEvent e) {
				listReset(listmodel);
			}
		});
		ButtonPanel.add(ResetButton);
		
		//��� ��ư
		CalButton = new JButton("���");
		CalButton.setBounds(0, hei/5/2, wid, hei/5/2);		
		CalButton.addActionListener(new ActionListener() {
			// ����Ʈ ������ ��ü ����
			public void actionPerformed(ActionEvent e) {
				listmodel = (DefaultListModel) NumList.getModel();
				
				//����Ʈ�� ������� ���
				if ( listmodel.isEmpty() ) {
					JOptionPane.showMessageDialog(null, "����� �� �����ϴ�.\n�ٽ� Ȯ���� �ּ���");
					return;
				}
				
				// ��� �˾� �� ��ư Ŭ���� ó��
				if(JOptionPane.showConfirmDialog(null, "����Ͻðڽ��ϱ�?", "���Ȯ��", JOptionPane.YES_NO_OPTION) 
						== JOptionPane.YES_OPTION) {
					// DB�� �ֹ����� ���� �ڵ� ���� ���߿�
					
					// �Ϸ� �˾�â ���� ����Ʈ ��ü ����
					JOptionPane.showMessageDialog(null, "����� �Ϸ�Ǿ����ϴ�!");
					listReset(listmodel);
				}
				
			}
		});
		ButtonPanel.add(CalButton);

	}
	
	// �ʱ�ȭ ���� �޼ҵ�
	public void listReset(DefaultListModel listmodel) {
		listmodel = (DefaultListModel) NameList.getModel();
		listmodel.removeAllElements();
		listmodel = (DefaultListModel) NumList.getModel();
		listmodel.removeAllElements();
		listmodel = (DefaultListModel) PriceList.getModel();
		listmodel.removeAllElements();
		TotalText.setText("0 ��");
	}
	
	// ��� ���� �޼ҵ�
	public void listCancel(DefaultListModel listmodel, int index) {

		
		listmodel = (DefaultListModel) NameList.getModel();
		listmodel.remove(index);
		listmodel = (DefaultListModel) NumList.getModel();
		listmodel.remove(index);
		listmodel = (DefaultListModel) PriceList.getModel();
		//�Ѱ���
		String totalprice_s = TotalText.getText().split(" ")[0];
		int totalprice = Integer.parseInt(totalprice_s);
		int minusprice = Integer.parseInt((String)listmodel.get(index));
		totalprice -= minusprice;
		TotalText.setText(Integer.toString(totalprice) + " ��");

		listmodel.remove(index);
	}
}
