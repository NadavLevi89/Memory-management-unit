package com.hit.view;

import java.awt.event.ActionListener;
import com.hit.memoryunits.Page;

public class MMUView extends java.util.Observable implements View{
	private GUI gui;
	@Override
	public void start() {
		String[] p = {"0","0"};
		updateCounters(p);
		gui.getFrame().setVisible(true);
		}
	
	public void createP4Table(int numOfProcess) {
		for(int i=0;i<numOfProcess;i++) {
			gui.getP4DTM().addRow(new Object[] {"  process " + i});
		}
	}
	
	public GUI getGui() {
		return gui;
	}
	
	public MMUView() {
		gui = new GUI();
		gui.create();
	}
	
	public void addPlayListener(ActionListener listenerForPlayButton) {
		gui.getButtonPlay().addActionListener(listenerForPlayButton);
	}
	
	public void addPlayAllListener(ActionListener listenerForPlayAllButton) {
		gui.getButtonPlayAll().addActionListener(listenerForPlayAllButton);
	}


	public void updateCounters(String[] p) { /// part 2
		this.getGui().getPageFaultNumber().setText("    " + String.valueOf(p[0]));
		this.getGui().getPageReplacmentNumbe().setText("    "+ String.valueOf(p[1]));
	}
	
	
	
	public void resetTable() {				// reset table	part 1 and start
		for(int i = 0 ; i<this.gui.getp1Table().getColumnCount();i++) {
			this.gui.getp1Table().getColumnModel().getColumn(i).setHeaderValue(" ");
			for(int j = 0 ; j<5 ; j++) {
				this.gui.getp1Table().setValueAt("    0", j, i);
			}
		}
	}
	public void p1TableUpdate(Page<byte[]>[] p) {		/// part 1
		resetTable();
		for(int i = 0 ; i<p.length;i++) {
			this.gui.getp1Table().getColumnModel().getColumn((p[i].getPageId()).intValue()).setHeaderValue(p[i].getPageId().intValue());
			this.gui.getP1ScrollPane().repaint();
			for(int j = 0 ; j<5 ; j++)
				this.gui.getp1Table().setValueAt(" "+p[i].getContent()[j], j, p[i].getPageId().intValue());
		}
		//this.gui.getp1Table().repaint();
		
	}

}
