package chang_jong;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MenuPanel extends JPanel {
	
	protected JList menulist,paylist;
	private String[] menu = {"���ܹ�","�Ұ����","���������","�������̹��","��������",
			 "���","���繬��","��ġ����","���ڹ�ȸ��ħ","��������","�κ�����","����","����","�����","�����"};
	private String[] pay = {"12000", "10000", "9000", "9000", "7000", "6000", "30000", "30000", "25000", 
			"25000", "15000", "4000", "4000", "2000", "1000"};
	private JScrollPane scrollPane, scrollPane2;
	
	private Order orderpanel;
	private JList ordername, ordernum, orderpay; // �ֹ���, ����, ����
	private DefaultListModel mlistmodel, plistmodel, namemodel, nummodel, paymodel;


	MenuPanel(Order order) {
		menulist = new JList(menu);
		paylist = new JList(pay);
	
		menulist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menulist.setVisibleRowCount(20);

		// �ֹ��г� ��ü ����
		orderpanel = order;
		ordername = orderpanel.list;
		ordernum = orderpanel.list_1;
		orderpay = orderpanel.list_2;
		
		menulist.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        // ó�� ����Ŭ�� �� �ֹ� �߰�, ���� �ѹ� Ŭ�������� ���ϸ޴� �߰� ����
		        if (evt.getClickCount() >= 2) {
		        	// ����Ʈ ������ �ޱ�
		        	namemodel = (DefaultListModel) ordername.getModel();
		        	nummodel  = (DefaultListModel) ordernum.getModel();
		        	paymodel  = (DefaultListModel) orderpay.getModel();
		        	
		        	// Ŭ���� ������ �̸�, ���� ����
		        	String menuname = (String)list.getSelectedValue();
		        	String menupay = pay[list.getSelectedIndex()]; // �̸��� �ش��ϴ� �ε��� ����
		        	
		        	// �޴� �ߺ� �˻�
		        	int i;
		        	for ( i = 0; i < namemodel.getSize() ; i++ ) {
		        		
		        		// ���� �޴��� �ִٸ�
		        		if ( namemodel.get(i).equals(menuname)) {
		        			
		        			System.out.println("�����޴��߰�");
		        			String selectnum = (String)nummodel.get(i); // ���ڿ� ����
		        			int selectnumi = Integer.parseInt(selectnum) + 1; // ���ڿ� ������ ��ȯ�� 1����
		        			
		        			// ����Ʈ ����
		        			nummodel.remove(i);
		        			nummodel.insertElementAt(Integer.toString(selectnumi), i); 
		        			
		        			String selectpay = (String)paymodel.get(i); // ���� ���ڿ� ����
		        			// ���� ���� + (��������/����  == 1�κ�) �� ����
		        			int selectpayi = Integer.parseInt(selectpay) + (Integer.parseInt(selectpay)/Integer.parseInt(selectnum));
		        			
		        			// ����Ʈ ����
		        			paymodel.remove(i);
		        			paymodel.insertElementAt(Integer.toString(selectpayi), i);
		        			
		        			return; // ���� �Ϸ�
		        		}
		        	}
		        	// �ֹ����� �ش� �޴��� ���ٸ�
		        	namemodel.insertElementAt(menuname, i);
		        	nummodel.insertElementAt("1", i);
		        	paymodel.insertElementAt(menupay, i);
 		        	ordername.setModel(namemodel);
		        	ordernum.setModel(nummodel);
		        	orderpay.setModel(paymodel);
		        } 
		    }
		});
		paylist.setEnabled(false);
		paylist.setVisibleRowCount(20);
		
		scrollPane = new JScrollPane(menulist);
		scrollPane2= new JScrollPane(paylist);
		
		this.setLayout(new GridLayout(1,0,0,0));
		add(scrollPane);
		add(scrollPane2);
	}
	
	
}
