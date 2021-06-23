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
	protected JList NameList, NumList, PriceList; // 주문명, 수량, 가격
	protected JLabel NameLabel, NumLabel, PriceLabel, TotalLabel,TableLabel;
	protected JButton CancelButton, ResetButton, CalButton;
	protected JTextField TotalText;
	private DefaultListModel listmodel, NameModel, NumModel, PriceModel;
	private Table table;
	private ArrayList<String> menuArray;
	private ArrayList<String> numArray;
	private ArrayList<String> payArray;

	public int tablenum;
	static int ctr =0; //순서
	public Order() {
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		int wid = (res.width / 4); // 주문 패널 넓이
		int hei = ((res.height / 10) * 9); // 주문 패널 높이
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setSize(wid, hei);
		// 리스트들 들어가 있는 패널
		ListPanel = new JPanel(); // 리스트들 들어가 있는 패널
		ListPanel.setBounds(0, 0, wid, (hei / 6) * 5);
		add(ListPanel);
		ListPanel.setLayout(null);

		TableLabel = new JLabel(tablenum + "번 테이블"); // 테이블번호라벨 03.30문식추가
		TableLabel.setBounds(0, 0, wid / 2, (hei / 6) / 2);
		setFontsize(TableLabel);
		ListPanel.add(TableLabel);

		NameLabel = new JLabel("주문명"); // 음식명라벨
		NameLabel.setBounds(0, (hei / 6) / 2, wid / 2, 20);
		NameLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ListPanel.add(NameLabel);

		NumLabel = new JLabel("수량"); // 수량라벨
		NumLabel.setBounds(wid / 2, (hei / 6) / 2, wid / 4, 20);
		NumLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ListPanel.add(NumLabel);

		PriceLabel = new JLabel("가격"); // 가격라벨
		PriceLabel.setBounds((wid / 2) + (wid / 4), (hei / 6) / 2, wid / 4, 20);
		PriceLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ListPanel.add(PriceLabel);

		NameList = new JList(new DefaultListModel()); // 음식명 리스트
		NameList.setBounds(0, (hei / 6) / 2 + 20, wid / 2, (hei / 6) * 4 - 20);
		NameList.setBorder(new LineBorder(new Color(0, 0, 0)));
		ListPanel.add(NameList);

		NumList = new JList(new DefaultListModel()); // 수량 리스트
		NumList.setBounds(wid / 2, (hei / 6) / 2 + 20, wid / 4, (hei / 6) * 4 - 20);
		NumList.setBorder(new LineBorder(new Color(0, 0, 0)));
		ListPanel.add(NumList);

		PriceList = new JList(new DefaultListModel()); // 가격 리스트
		PriceList.setBounds((wid / 2) + (wid / 4), (hei / 6) / 2 + 20, wid / 4, (hei / 6) * 4 - 20);
		PriceList.setBorder(new LineBorder(new Color(0, 0, 0)));
		ListPanel.add(PriceList);

		// 가격라벨
		TotalLabel = new JLabel("총 가격  : ");
		TotalLabel.setBounds(0, (hei / 6) * 5 - hei / 6 / 2, wid / 4, (hei / 6) / 2);
		setFontsize(TotalLabel);
		ListPanel.add(TotalLabel);

		// 총가격 텍스트 필드
		TotalText = new JTextField("0 원");
		TotalText.setBounds(wid / 4, (hei / 6) * 5 - hei / 6 / 2, wid / 4 * 3, (hei / 6) / 2);
		TotalText.setEditable(false);
		setFontsize(TotalText);
		ListPanel.add(TotalText);

		ButtonPanel = new JPanel(); // 버튼 패널
		ButtonPanel.setBounds(0, (hei / 6) * 5, wid, hei / 6);
		add(ButtonPanel);
		ButtonPanel.setLayout(null);

		// 취소 버튼
		CancelButton = new JButton("취소");
		CancelButton.setBounds(0, 0, wid / 2, hei / 6 / 2);
		setFontsize(CancelButton);
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectIndex = NameList.getSelectedIndices();

				// 리스트 아이템이 없거나 선택을 하지 않았을 경우 무시
				if (selectIndex == null) {
					return;
				}

				// 동시에 다수 취소가 가능한 반복문
				// 다수 지울 시 앞의 인덱스부터 지울 경우 배열범위 예외가 발생하므로 끝에서 부터 삭제함
				for (int i = selectIndex.length - 1; i >= 0; i--) {
					listCancel(listmodel, selectIndex[i]);
				}

			}
		});
		ButtonPanel.add(CancelButton);

		// 초기화 버튼
		ResetButton = new JButton("초기화");
		ResetButton.setBounds(wid / 2, 0, wid / 2, hei / 6 / 2);
		setFontsize(ResetButton);
		ResetButton.addActionListener(new ActionListener() {
			// 리스트 아이템 전체 제거
			public void actionPerformed(ActionEvent e) {
				listReset(listmodel);
			}
		});
		ButtonPanel.add(ResetButton);

		// 계산 버튼
		CalButton = new JButton("계산");
		CalButton.setBounds(0, hei / 6 / 2, wid, hei / 6 / 2);
		setFontsize(CalButton);
		CalButton.addActionListener(new ActionListener() {
			// 리스트 아이템 전체 제거
			public void actionPerformed(ActionEvent e) {
				listmodel = (DefaultListModel) NumList.getModel();
				
				// 리스트가 비어있을 경우
				if (listmodel.isEmpty()) {
					JOptionPane.showMessageDialog(null, "계산할 수 없습니다.\n다시 확인해 주세요");
					return;
				}

				// 계산 팝업 예 버튼 클릭시 처리
				if (JOptionPane.showConfirmDialog(null, "계산하시겠습니까?", "계산확인",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					// DB에 주문내역 저장 코드 구현 나중에
						//현재 날짜
		                  SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm", Locale.KOREA ); 
		                  Date currentTime = new Date ( ); 
		                  String dTime = formatter.format ( currentTime );
		                  String date = dTime; //날짜
		                  ctr ++; //주문 테이블 숫자 증가
		                  
		                 for (int k = 0 ; k < menuArray.size();k++ ) {
		                      String name = menuArray.get(k); //메뉴이름
		                      System.out.println(name);
		                      String count = numArray.get(k); //수량
		                      String price = payArray.get(k); //가격
		                       
		                      try {
		                         Connection con2 = makeConnection();
		                         Statement stmt = con2.createStatement();
		                         String s = "INSERT INTO poslog2 (p_date,p_order,p_menu,p_count,p_price) VALUES ";
		                         s += "('"+date+"','"+ctr+"','"+name+"','"+count+"','"+price+"')";
		                         System.out.println(s);
		                         
		                         int i = stmt.executeUpdate(s);
		                         if( i == 1) {
		                            System.out.println("레코드 추가 성공");
		                         }else {
		                            System.out.println("레코드 추가 실패");
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
		               
					// 완료 팝업창 띄우고 리스트 전체 삭제
					JOptionPane.showMessageDialog(null, "계산이 완료되었습니다!");
					listReset(listmodel);
				}

			}
		});
		ButtonPanel.add(CalButton);

	}
	// 디비 연동 | order만 돌렸을 땐 연동 가능
	   public static Connection makeConnection() {
	         String url = "jdbc:mysql://localhost:3306/changjong?useUnicode=true&characterEncoding=utf8";
	         String id = "root";
	         String password = "apmsetup";
	         Connection con = null;
	         
	         try {
	            Class.forName("com.mysql.jdbc.Driver");
	            System.out.println("드라이버 적재 성공");
	            con = DriverManager.getConnection(url,id,password);
	            System.out.println("데이터베이스 연결 성공");
	         }catch(ClassNotFoundException e) {
	            System.out.println("드라이버를 찾을 수 없습니다.");
	         }catch(SQLException e) {
	            System.out.println("연결에 실패하였습니다.");
	         }
	         
	         return con;
	  }
	public void setFontsize(JLabel sized) { // 폰트사이즈 바꾸는 메소드인데 핸들러에 넣으면 널포인트에러 떠서 일단은 여기에 구현함
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

	// 초기화 실행 메소드
	public void listReset(DefaultListModel listmodel) {
		// 리스트 전체 삭제
		listmodel = (DefaultListModel) NameList.getModel();
		listmodel.removeAllElements();
		listmodel = (DefaultListModel) NumList.getModel();
		listmodel.removeAllElements();
		listmodel = (DefaultListModel) PriceList.getModel();
		listmodel.removeAllElements();
		TotalText.setText("0 원");

		// 테이블 전체 삭제
		menuArray.clear();
		numArray.clear();
		payArray.clear();

	}

	// 초기화 실행 메소드
	public void listClear(DefaultListModel listmodel) {
		// 리스트 전체 삭제
		listmodel = (DefaultListModel) NameList.getModel();
		listmodel.removeAllElements();
		listmodel = (DefaultListModel) NumList.getModel();
		listmodel.removeAllElements();
		listmodel = (DefaultListModel) PriceList.getModel();
		listmodel.removeAllElements();
		TotalText.setText("0 원");

	}

	// 취소 실행 메소드
	public void listCancel(DefaultListModel listmodel, int index) {
		// 해당 이름 리스트 삭제
		listmodel = (DefaultListModel) NameList.getModel();
		listmodel.remove(index);
		// 해당 수량 리스트 삭제
		listmodel = (DefaultListModel) NumList.getModel();
		listmodel.remove(index);
		listmodel = (DefaultListModel) PriceList.getModel();
		// 가격
		/*
		 * String totalprice_s = TotalText.getText().split(" ")[0]; int totalprice =
		 * Integer.parseInt(totalprice_s); int minusprice =
		 * Integer.parseInt((String)listmodel.get(index)); totalprice -= minusprice;
		 * TotalText.setText(Integer.toString(totalprice) + " 원");
		 */
		listmodel.remove(index);

		sumPay(listmodel);

		// 테이블 해당 주문 내역 삭제
		menuArray.remove(index);
		numArray.remove(index);
		payArray.remove(index);

	}

	// 테이블 내용 주문패널 리스트에 저장
	public void loadList() {
		ArrayList<String> menuArray = table.getMenu();
		ArrayList<String> numArray = table.getNum();
		ArrayList<String> payArray = table.getPay();

		// 비어있을 경우 무시
		if (menuArray.isEmpty())
			return;

		// 각 리스트들 리스트 모델로 받기
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

	// 테이블을 설정하는 메소드
	public void setTable(Table table) {
		this.table = table;
		this.menuArray = table.getMenu();
		this.numArray = table.getNum();
		this.payArray = table.getPay();
	}

	// 리스트 가격 합해서 총가격 텍스트필드에 설정
	public void sumPay(DefaultListModel paymodel) {
		int sum = 0;
		for (int i = 0; i < paymodel.size(); i++) {
			sum += Integer.parseInt((String) paymodel.get(i));
		}
		// 총가격 텍스트필드 설정
		TotalText.setText(Integer.toString(sum) + " 원");
	}
}