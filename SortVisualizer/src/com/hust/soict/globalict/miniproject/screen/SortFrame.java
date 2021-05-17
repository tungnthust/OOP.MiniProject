package com.hust.soict.globalict.miniproject.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.hust.soict.globalict.miniproject.sort_visualizer.BubbleSortVisualizer;
import com.hust.soict.globalict.miniproject.sort_visualizer.InsertionSortVisualizer;
import com.hust.soict.globalict.miniproject.sort_visualizer.QuickSortVisualizer;
import com.hust.soict.globalict.miniproject.sort_visualizer.SortVisualizer;


public class SortFrame extends JFrame {
	
	private Integer[] array;
	private int capacity;

	private SortCanvas sortCanvas;
	private SortVisualizer sortVisualizer;
	
	private JPanel contentPane;
	private BufferStrategy bs;
	private Thread threadSort;
	private Graphics g;
	private JButton btnPlay;
	/**
	 * Create the frame.
	 */
	public SortFrame(int array[], String sortAlgorithm) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1024, 768);
		setLocationRelativeTo(null);		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(249, 249, 249));
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(30, 30, 953, 630);
		
		JButton btnNextStep = new JButton("Next Step");
		btnNextStep.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				sortVisualizer.setContinue(true);
			}
		});
		
		btnNextStep.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNextStep.setBounds(370, 680, 127, 34);
		contentPane.add(btnNextStep);
		
		btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnPlay.getText().equals("Play")) {
					btnPlay.setText("Pause");
					sortVisualizer.setContinous(true);
					return;
				}
				if (btnPlay.getText().equals("Pause")) {
					btnPlay.setText("Play");
					sortVisualizer.setContinous(false);
					return;
				}
			}
		});
		
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPlay.setBounds(200, 680, 127, 34);
		contentPane.add(btnPlay);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				threadSort = new Thread() {
					@Override
					public void run() {
						sortVisualizer.sort();					
					}
				};
				threadSort.start();
			}
		});
		
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnStart.setBounds(30, 680, 127, 34);
		contentPane.add(btnStart);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				threadSort.interrupt();
				SortFrame.this.dispose();
			}
		});
		
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBounds(855, 680, 127, 34);
		contentPane.add(btnBack);
		
		
		sortCanvas = new SortCanvas(this);
		sortCanvas.setFocusable(false);
		sortCanvas.setSize(800, 600);
		setMinimumSize(new Dimension(1024, 768));
		pack();
		panel.add(sortCanvas);
		
		contentPane.add(panel);
		if (sortAlgorithm.equals("Bubble Sort")) {
			
		}
		switch (sortAlgorithm) {
		case "Bubble Sort": {
			sortVisualizer = new BubbleSortVisualizer(sortCanvas, this, array);			
			break;
		}
		case "Quick Sort": {
			sortVisualizer = new QuickSortVisualizer(sortCanvas, this, array);			
			break;
		}
		case "Insertion Sort": {
			sortVisualizer = new InsertionSortVisualizer(sortCanvas, this, array);			
			break;
		}
		default:
			break;
		}
		setVisible(true);
	
	}
	public void setBtnPlayText(String btnPlayStr) {
		this.btnPlay.setText(btnPlayStr);
	}
	
	public void onDrawArray()
	{
		if (sortVisualizer != null)
			sortVisualizer.drawArray();
	}
	
	public BufferStrategy getBufferStrategy()
	{
		BufferStrategy bs = sortCanvas.getBufferStrategy();
		if (bs == null)
		{
			sortCanvas.createBufferStrategy(2);
			bs = sortCanvas.getBufferStrategy();
		}

		return bs;
	}
}
