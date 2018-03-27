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
		int wid=(res.width/4);				//주문 패널 넓이
		int hei=((res.height/10)*9);			//주문 패널 높이
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setSize(wid, hei);
		JPanel ListPanel = new JPanel();			//리스트들 들어가 있는 패널
		ListPanel.setBounds(0,0, wid, (hei/5)*4);
		add(ListPanel);
		ListPanel.setLayout(null);
		
		JLabel NameLabel = new JLabel("주문명");			//음식명라벨
		NameLabel.setBounds(0, 0, wid/2, 20);
		NameLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ListPanel.add(NameLabel);

		JLabel NumLabel = new JLabel("수량");				//수량라벨
		NumLabel.setBounds(wid/2, 0, wid/4, 20);
		NumLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ListPanel.add(NumLabel);
		
		JLabel PriceLabel = new JLabel("가격");			//가격라벨
		PriceLabel.setBounds((wid/2)+(wid/4), 0, wid/4, 20);
		PriceLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		ListPanel.add(PriceLabel);
		
		JList NameList = new JList();					//음식명 리스트							//여기부터 ------------------------------------------------------
		NameList.setBounds(0, 20, wid/2, (hei/5)*4-20-hei/5/2);
		NameList.setBorder(new LineBorder(new Color(0, 0, 0)));
		ListPanel.add(NameList);
		
		JList NumList = new JList();					//수량 리스트
		NumList.setBounds(wid/2, 20, wid/4, (hei/5)*4-20-hei/5/2);
		NumList.setBorder(new LineBorder(new Color(0, 0, 0)));
		ListPanel.add(NumList);
		
		JList PriceList = new JList();					//가격 리스트
		PriceList.setBounds((wid/2)+(wid/4), 20, wid/4, (hei/5)*4-20-hei/5/2);
		PriceList.setBorder(new LineBorder(new Color(0, 0, 0)));
		ListPanel.add(PriceList);
		
		JLabel TotalLabel = new JLabel("총 가격  : ");			//가격라벨								
		TotalLabel.setBounds(0, (hei/5)*4-hei/5/2, wid/4, hei/5/2);
		ListPanel.add(TotalLabel);
		
		JTextField TotalText = new JTextField();			//가격 텍스트 필드
		TotalText.setBounds(wid/4, (hei/5)*4-hei/5/2, wid/4*3, hei/5/2);
		TotalText.setEditable(false);
		ListPanel.add(TotalText);																//여기까지가 수정한 내용---------------------------------------------
		
		JPanel ButtonPanel = new JPanel();				//버튼 패널
		ButtonPanel.setBounds(0, (hei/5)*4, wid, hei/5);
		add(ButtonPanel);
		ButtonPanel.setLayout(null);
		
		JButton CancelButton = new JButton("취소");
		CancelButton.setBounds(0, 0, wid/2, hei/5/2);
		ButtonPanel.add(CancelButton);
		
		JButton ResetButton = new JButton("초기화");
		ResetButton.setBounds(wid/2, 0, wid/2, hei/5/2);
		ButtonPanel.add(ResetButton);
		
		JButton CalButton = new JButton("계산");
		CalButton.setBounds(0, hei/5/2, wid, hei/5/2);
		ButtonPanel.add(CalButton);

	}
	
}
