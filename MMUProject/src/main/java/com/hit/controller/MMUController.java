package com.hit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import com.hit.driver.CLI;
import com.hit.memoryunits.Page;
import com.hit.model.MMUModel;
import com.hit.view.MMUView;

public class MMUController extends java.lang.Object implements Controller, java.util.Observer{
	private MMUModel model;
	private MMUView view;
	
	public MMUController(MMUModel model,MMUView view) {
		this.model = model;
		this.view = view;
		
		view.addPlayListener(new PlayListener());
		view.addPlayAllListener(new PlayAllListener());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof CLI) {
			model.setConfiguration((String[]) arg);
			model.start();
			view.createP4Table(model.getNumOfProcesses());
			view.start();
			
		}
		else if(o instanceof MMUModel) {
			view.p1TableUpdate((Page<byte[]>[])arg);
			view.updateCounters(model.convortCounter());
		}
	}
	
	class PlayListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int[] selectedRows=view.getGui().getp4Table().getSelectedRows();
			view.getGui().getp4Table().clearSelection();
			model.updateProcesses(selectedRows);
			}
		}
	
	class PlayAllListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			view.getGui().getp4Table().clearSelection();
			model.runProcesses(model.getProcesses());
		}
	}
	
}