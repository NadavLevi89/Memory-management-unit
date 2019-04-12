package com.hit.view;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame mainFrame;
	private JPanel p1,p2,p3,p4;
	private JButton bPlay,bPlayAll;
	private JLabel pageFault,pageReplacment;
	private JLabel pFNumber,pRNumber;
	private JTable p1Table,p4Table;
	private JScrollPane p1ScrollPane,p4ScrollPane;
	private DefaultTableModel p4DTM;
	private Object columnNames[] = { " "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," " };
	private Object rowData[][] = { { " 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0" },
								   { " 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0" },
								   { " 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0" },
								   { " 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0" },
								   { " 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0"," 0" } };
	
	public JFrame getFrame() {
		return mainFrame;
	}
	public JTable getp1Table() {
		return p1Table;
	}
	public JTable getp4Table() {
		return p4Table;
	}
	public JButton getButtonPlay() {
		return bPlay;
	}
	public JButton getButtonPlayAll() {
		return bPlayAll;
	}
	public JLabel getPageFaultNumber() {
		return pFNumber;
	}
	public JLabel getPageReplacmentNumbe() {
		return pRNumber;
	}
	public JScrollPane getP1ScrollPane() {
		return p1ScrollPane;
	}
	public JScrollPane getP4ScrollPane() {
		return p4ScrollPane;
	}
	public DefaultTableModel getP4DTM() {
		return p4DTM;
	}
	
	public GUI()
	{
		create();
	}
	public void create() {
		setMainFrame();
		setPanel1();
		setPanel2();
		setPanel3();
		setPanel4();
		//mainFrame.setVisible(true);
	}
	
	
	
	
	void setMainFrame() {
		mainFrame = new JFrame("lol");
		mainFrame.setSize(1200, 600);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
	}
	
	
	void setPanel1() {
		p1 = new JPanel();
		p1.setBounds(0, 0, 805, 200);
		//p1.setBackground(Color.yellow);
		p1Table = new JTable(rowData,columnNames);
		p1Table.setRowHeight(33);
		p1ScrollPane = new JScrollPane(this.p1Table);
		p1ScrollPane.setBounds(5, 5, 790, 190);
		p1ScrollPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		p1Table.setRowSelectionAllowed(false);
		mainFrame.add(p1ScrollPane);
		mainFrame.add(p1);
	}

	void setPanel2() {
		p2 = new JPanel();
		p2.setBounds(805, 0, 395, 200);
		//p2.setBackground(Color.blue);
		p2.setLayout(new GridLayout(0,1,800,200));
		pageFault = new JLabel();
		pageFault.setText(" Numbers of pagefaults");
		pageFault.setBounds(825, 30, 250, 40);
		pageFault.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		pageFault.setFont(pageFault.getFont().deriveFont(17.0f));
		pageReplacment= new JLabel();
		pageReplacment.setText(" Numbers of pageReplacment");
		pageReplacment.setBounds(825, 100, 250, 40);
		pageReplacment.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		pageReplacment.setFont(pageReplacment.getFont().deriveFont(17.0f));
		pFNumber = new JLabel();
		pFNumber.setText(String.valueOf(0));
		pFNumber.setBounds(1100, 30, 70, 40);
		pFNumber.setFont(pFNumber.getFont().deriveFont(17.0f));
		pFNumber.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		pRNumber = new JLabel();
		pRNumber.setText(String.valueOf(0));
		pRNumber.setBounds(1100, 100, 70, 40);
		pRNumber.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		pRNumber.setFont(pRNumber.getFont().deriveFont(17.0f));
		mainFrame.add(pFNumber);
		mainFrame.add(pRNumber);
		mainFrame.add(pageFault);
		mainFrame.add(pageReplacment);
		mainFrame.add(p2);
	}
	
	void setPanel3() {
		p3 = new JPanel();
		p3.setBounds(0, 200, 805, 400);
		//p3.setBackground(Color.green);
		bPlay = new JButton();
		bPlay.setText("PLAY");
		bPlay.setBounds(300, 350, 100, 50);
		bPlay.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		bPlay.setFont(bPlay.getFont().deriveFont(17.0f));
		bPlayAll = new JButton();
		bPlayAll.setText("PLAY ALL");
		bPlayAll.setBounds(455, 350, 150, 50);
		bPlayAll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		bPlayAll.setFont(bPlayAll.getFont().deriveFont(17.0f));
		mainFrame.add(bPlay);
		mainFrame.add(bPlayAll);
		mainFrame.add(p3);
		
	}
	
	void setPanel4() {
		p4 = new JPanel();
		p4.setBounds(805, 200, 395, 400);
		//p4.setBackground(Color.orange);
		p4DTM = new DefaultTableModel();
		p4Table = new JTable(p4DTM);
		p4Table.setRowHeight(30);
		p4DTM.addColumn("Processes");
		p4ScrollPane= new JScrollPane(this.p4Table);
		p4ScrollPane.setBounds(900, 230, 100, 300);	
		p4ScrollPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		p4Table.setRowSelectionAllowed(true);
		mainFrame.add(p4ScrollPane);
		mainFrame.add(p4);
		
	}
	
}

