import java.awt.GridLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MenuPanel extends JPanel implements ListSelectionListener {
	
	JList menulist,paylist;
	String[] menu = {"돌솥밥","불고기백반","간장게장백반","생선구이백반","된장찌개백반",
					 "백반","도루묵찜","김치전골","가자미회무침","제육볶음","두부전골","소주","맥주","음료수","공기밥"};
	String[] pay = {"12000", "10000", "9000", "9000", "7000", "6000", "30000", "30000", "25000", 
					"25000", "15000", "4000", "4000", "2000", "1000"};
	JScrollPane scrollPane, scrollPane2;
	
	MenuPanel() {
	/*	Vector<String> vector = new Vector();
		for ( int i = 0; i < menu.length ; i++) {
			vector.add(menu[i] + "\t\t\t" + pay[i]);
		}*/

		menulist = new JList(menu);
		paylist = new JList(pay);
		menulist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menulist.setVisibleRowCount(20);
		paylist.setEnabled(false);
		paylist.setVisibleRowCount(20);
		
		scrollPane = new JScrollPane(menulist);
		scrollPane2= new JScrollPane(paylist);
		
		this.setLayout(new GridLayout(1,0,0,0));
		add(scrollPane);
		add(scrollPane2);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (menulist.getSelectedIndex() == -1) { //getSelectedIndex() : 선택한 항목 인덱스 값 반환
			//선택이 되지 않은 경우
		} else { //선택되지 않은 경우 –1을 반환한다.
			//선택이 된 경우
		}
	}
}
