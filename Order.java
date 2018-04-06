package chang_jong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import chang_jong.entites.Table;

public class Order extends JPanel {
	protected JPanel ListPanel, ButtonPanel;
	protected JList NameList, NumList, PriceList; // �ֹ���, ����, ����
	protected JLabel NameLabel, NumLabel, PriceLabel, TotalLabel,TableLabel;
	protected JButton CancelButton, ResetButton, CalButton;
	protected JTextField TotalText;
	private DefaultListModel listmodel, NameModel, NumModel, PriceModel;
	private Table table;
	private ArrayList<String> menuArray;
	private ArrayList<String> numArray;
	private ArrayList<String> payArray;

	public int tablenum;
	static int ctr =0; //����
	public Order() {
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		int wid = (res.width / 4); // �ֹ� �г� ����
		int hei = ((res.height / 10) * 9); // �ֹ� �г� ����
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setSize(wid, hei);
		// ����Ʈ�� �� �ִ� �г�
		ListPanel = new JPanel(); // ����Ʈ�� �� �ִ� �г�
		ListPanel.setBounds(0, 0, wid, (hei / 6) * 5);
		add(ListPanel);
		ListPanel.setLayout(null);

		TableLabel = new JLabel(tablenum + "�� ���̺�"); // ���̺��ȣ�� 03.30�����߰�
		TableLabel.setBounds(0, 0, wid / 2, (hei / 6) / 2);
		setFontsize(TableLabel);
		ListPanel.add(TableLabel);

		NameLabel = new JLabel("�ֹ���"); // ���ĸ��
		NameLabel.setBounds(0, (hei / 6) / 2, wid / 2, 20);
		NameLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ListPanel.add(NameLabel);

		NumLabel = new JLabel("����"); // ������
		NumLabel.setBounds(wid / 2, (hei / 6) / 2, wid / 4, 20);
		NumLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ListPanel.add(NumLabel);

		PriceLabel = new JLabel("����"); // ���ݶ�
		PriceLabel.setBounds((wid / 2) + (wid / 4), (hei / 6) / 2, wid / 4, 20);
		PriceLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ListPanel.add(PriceLabel);

		NameList = new JList(new DefaultListModel()); // ���ĸ� ����Ʈ
		NameList.setBounds(0, (hei / 6) / 2 + 20, wid / 2, (hei / 6) * 4 - 20);
		NameList.setBorder(new LineBorder(new Color(0, 0, 0)));
		ListPanel.add(NameList);

		NumList = new JList(new DefaultListModel()); // ���� ����Ʈ
		NumList.setBounds(wid / 2, (hei / 6) / 2 + 20, wid / 4, (hei / 6) * 4 - 20);
		NumList.setBorder(new LineBorder(new Color(0, 0, 0)));
		ListPanel.add(NumList);

		PriceList = new JList(new DefaultListModel()); // ���� ����Ʈ
		PriceList.setBounds((wid / 2) + (wid / 4), (hei / 6) / 2 + 20, wid / 4, (hei / 6) * 4 - 20);
		PriceList.setBorder(new LineBorder(new Color(0, 0, 0)));
		ListPanel.add(PriceList);

		// ���ݶ�
		TotalLabel = new JLabel("�� ����  : ");
		TotalLabel.setBounds(0, (hei / 6) * 5 - hei / 6 / 2, wid / 4, (hei / 6) / 2);
		setFontsize(TotalLabel);
		ListPanel.add(TotalLabel);

		// �Ѱ��� �ؽ�Ʈ �ʵ�
		TotalText = new JTextField("0 ��");
		TotalText.setBounds(wid / 4, (hei / 6) * 5 - hei / 6 / 2, wid / 4 * 3, (hei / 6) / 2);
		TotalText.setEditable(false);
		setFontsize(TotalText);
		ListPanel.add(TotalText);

		ButtonPanel = new JPanel(); // ��ư �г�
		ButtonPanel.setBounds(0, (hei / 6) * 5, wid, hei / 6);
		add(ButtonPanel);
		ButtonPanel.setLayout(null);

		// ��� ��ư
		CancelButton = new JButton("���");
		CancelButton.setBounds(0, 0, wid / 2, hei / 6 / 2);
		setFontsize(CancelButton);
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectIndex = NameList.getSelectedIndices();

				// ����Ʈ �������� ���ų� ������ ���� �ʾ��� ��� ����
				if (selectIndex == null) {
					return;
				}

				// ���ÿ� �ټ� ��Ұ� ������ �ݺ���
				// �ټ� ���� �� ���� �ε������� ���� ��� �迭���� ���ܰ� �߻��ϹǷ� ������ ���� ������
				for (int i = selectIndex.length - 1; i >= 0; i--) {
					listCancel(listmodel, selectIndex[i]);
				}

			}
		});
		ButtonPanel.add(CancelButton);

		// �ʱ�ȭ ��ư
		ResetButton = new JButton("�ʱ�ȭ");
		ResetButton.setBounds(wid / 2, 0, wid / 2, hei / 6 / 2);
		setFontsize(ResetButton);
		ResetButton.addActionListener(new ActionListener() {
			// ����Ʈ ������ ��ü ����
			public void actionPerformed(ActionEvent e) {
				listReset(listmodel);
			}
		});
		ButtonPanel.add(ResetButton);

		// ��� ��ư
		CalButton = new JButton("���");
		CalButton.setBounds(0, hei / 6 / 2, wid, hei / 6 / 2);
		setFontsize(CalButton);
		CalButton.addActionListener(new ActionListener() {
			// ����Ʈ ������ ��ü ����
			public void actionPerformed(ActionEvent e) {
				listmodel = (DefaultListModel) NumList.getModel();
				
				// ����Ʈ�� ������� ���
				if (listmodel.isEmpty()) {
					JOptionPane.showMessageDialog(null, "����� �� �����ϴ�.\n�ٽ� Ȯ���� �ּ���");
					return;
				}

				// ��� �˾� �� ��ư Ŭ���� ó��
				if (JOptionPane.showConfirmDialog(null, "����Ͻðڽ��ϱ�?", "���Ȯ��",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					// DB�� �ֹ����� ���� �ڵ� ���� ���߿�
						//���� ��¥
		                  SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm", Locale.KOREA ); 
		                  Date currentTime = new Date ( ); 
		                  String dTime = formatter.format ( currentTime );
		                  String date = dTime; //��¥
		                  ctr ++; //�ֹ� ���̺� ���� ����
		                  
		                 for (int k = 0 ; k < menuArray.size();k++ ) {
		                      String name = menuArray.get(k); //�޴��̸�
		                      System.out.println(name);
		                      String count = numArray.get(k); //����
		                      String price = payArray.get(k); //����
		                       
		                      try {
		                         Connection con2 = makeConnection();
		                         Statement stmt = con2.createStatement();
		                         String s = "INSERT INTO poslog2 (p_date,p_order,p_menu,p_count,p_price) VALUES ";
		                         s += "('"+date+"','"+ctr+"','"+name+"','"+count+"','"+price+"')";
		                         System.out.println(s);
		                         
		                         int i = stmt.executeUpdate(s);
		                         if( i == 1) {
		                            System.out.println("���ڵ� �߰� ����");
		                         }else {
		                            System.out.println("���ڵ� �߰� ����");
		                         }
		                         
		                         ResultSet rs = stmt.executeQuery("select * from poslog2");
				                 while(rs.next()) {
				                	 System.out.println(rs.getString(1) + " " + rs.getString(2) + " " +
				                			 rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) );
				                 }
		                      }catch(SQLException e1) {
		                         System.err.println("Caught Exception: "+ e1.getMessage());
		                         System.exit(0);
		                      }
		                      
		                         
		                
		                   }
		               
					// �Ϸ� �˾�â ���� ����Ʈ ��ü ����
					JOptionPane.showMessageDialog(null, "����� �Ϸ�Ǿ����ϴ�!");
					listReset(listmodel);
				}

			}
		});
		ButtonPanel.add(CalButton);

	}
	// ��� ���� | order�� ������ �� ���� ����
	   public static Connection makeConnection() {
	         String url = "jdbc:mysql://localhost:3306/changjong?useUnicode=true&characterEncoding=utf8";
	         String id = "root";
	         String password = "apmsetup";
	         Connection con = null;
	         
	         try {
	            Class.forName("com.mysql.jdbc.Driver");
	            System.out.println("����̹� ���� ����");
	            con = DriverManager.getConnection(url,id,password);
	            System.out.println("�����ͺ��̽� ���� ����");
	         }catch(ClassNotFoundException e) {
	            System.out.println("����̹��� ã�� �� �����ϴ�.");
	         }catch(SQLException e) {
	            System.out.println("���ῡ �����Ͽ����ϴ�.");
	         }
	         
	         return con;
	  }
	public void setFontsize(JLabel sized) { // ��Ʈ������ �ٲٴ� �޼ҵ��ε� �ڵ鷯�� ������ ������Ʈ���� ���� �ϴ��� ���⿡ ������
		int i = 0;
		while (true) {
			Font before = sized.getFont();
			Font font = new Font(before.getName(), before.getStyle(), i);
			sized.setFont(font);
			if (sized.getPreferredSize().getWidth() > sized.getWidth()
					|| sized.getPreferredSize().getHeight() > sized.getHeight()) {
				font = new Font(before.getName(), before.getStyle(), i - 1);
				sized.setFont(font);
				break;
			}
			i++;
		}
	}

	public void setFontsize(JButton sized) {
		int i = 0;
		while (true) {
			Font before = sized.getFont();
			Font font = new Font(before.getName(), before.getStyle(), i);
			sized.setFont(font);
			if (sized.getPreferredSize().getWidth() > sized.getWidth()
					|| sized.getPreferredSize().getHeight() > sized.getHeight()) {
				font = new Font(before.getName(), before.getStyle(), i - 1);
				sized.setFont(font);
				break;
			}
			i++;
		}
	}
	
	public void setFontsize(JTextField sized) {
		int i = 0;
		while (true) {
			Font before = sized.getFont();
			Font font = new Font(before.getName(), before.getStyle(), i);
			sized.setFont(font);
			if (sized.getPreferredSize().getWidth() > sized.getWidth()
					|| sized.getPreferredSize().getHeight() > sized.getHeight()) {
				font = new Font(before.getName(), before.getStyle(), i - 1);
				sized.setFont(font);
				break;
			}
			i++;
		}
	}

	// �ʱ�ȭ ���� �޼ҵ�
	public void listReset(DefaultListModel listmodel) {
		// ����Ʈ ��ü ����
		listmodel = (DefaultListModel) NameList.getModel();
		listmodel.removeAllElements();
		listmodel = (DefaultListModel) NumList.getModel();
		listmodel.removeAllElements();
		listmodel = (DefaultListModel) PriceList.getModel();
		listmodel.removeAllElements();
		TotalText.setText("0 ��");

		// ���̺� ��ü ����
		menuArray.clear();
		numArray.clear();
		payArray.clear();

	}

	// �ʱ�ȭ ���� �޼ҵ�
	public void listClear(DefaultListModel listmodel) {
		// ����Ʈ ��ü ����
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
		// �ش� �̸� ����Ʈ ����
		listmodel = (DefaultListModel) NameList.getModel();
		listmodel.remove(index);
		// �ش� ���� ����Ʈ ����
		listmodel = (DefaultListModel) NumList.getModel();
		listmodel.remove(index);
		listmodel = (DefaultListModel) PriceList.getModel();
		// ����
		/*
		 * String totalprice_s = TotalText.getText().split(" ")[0]; int totalprice =
		 * Integer.parseInt(totalprice_s); int minusprice =
		 * Integer.parseInt((String)listmodel.get(index)); totalprice -= minusprice;
		 * TotalText.setText(Integer.toString(totalprice) + " ��");
		 */
		listmodel.remove(index);

		sumPay(listmodel);

		// ���̺� �ش� �ֹ� ���� ����
		menuArray.remove(index);
		numArray.remove(index);
		payArray.remove(index);

	}

	// ���̺� ���� �ֹ��г� ����Ʈ�� ����
	public void loadList() {
		ArrayList<String> menuArray = table.getMenu();
		ArrayList<String> numArray = table.getNum();
		ArrayList<String> payArray = table.getPay();

		// ������� ��� ����
		if (menuArray.isEmpty())
			return;

		// �� ����Ʈ�� ����Ʈ �𵨷� �ޱ�
		NameModel = (DefaultListModel) NameList.getModel();
		NumModel = (DefaultListModel) NumList.getModel();
		PriceModel = (DefaultListModel) PriceList.getModel();

		for (int i = 0; i < menuArray.size(); i++) {
			NameModel.insertElementAt(menuArray.get(i), i);
			NumModel.insertElementAt(numArray.get(i), i);
			PriceModel.insertElementAt(payArray.get(i), i);
		}

		NameList.setModel(NameModel);
		NumList.setModel(NumModel);
		PriceList.setModel(PriceModel);

		sumPay(PriceModel);
	}

	// ���̺��� �����ϴ� �޼ҵ�
	public void setTable(Table table) {
		this.table = table;
		this.menuArray = table.getMenu();
		this.numArray = table.getNum();
		this.payArray = table.getPay();
	}

	// ����Ʈ ���� ���ؼ� �Ѱ��� �ؽ�Ʈ�ʵ忡 ����
	public void sumPay(DefaultListModel paymodel) {
		int sum = 0;
		for (int i = 0; i < paymodel.size(); i++) {
			sum += Integer.parseInt((String) paymodel.get(i));
		}
		// �Ѱ��� �ؽ�Ʈ�ʵ� ����
		TotalText.setText(Integer.toString(sum) + " ��");
	}
}