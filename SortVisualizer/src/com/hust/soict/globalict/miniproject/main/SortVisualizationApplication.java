package com.hust.soict.globalict.miniproject.main;

import java.awt.EventQueue;

import com.hust.soict.globalict.miniproject.screen.MainFrame;

public class SortVisualizationApplication {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
