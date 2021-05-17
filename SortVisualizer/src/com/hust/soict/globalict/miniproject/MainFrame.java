package com.hust.soict.globalict.miniproject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldArraySize;
	private JTextField txtFieldArrayValues;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private boolean isRandom = true;
	private int array[];
	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 446);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Sort Visualizer");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Help");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Usage");
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel inputArrayPanel = new JPanel();
		inputArrayPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		inputArrayPanel.setBounds(73, 53, 653, 204);
		contentPane.add(inputArrayPanel);
		inputArrayPanel.setLayout(null);
		
		JLabel lblArraySize = new JLabel("Array Size:");
		lblArraySize.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblArraySize.setBounds(36, 41, 119, 28);
		inputArrayPanel.add(lblArraySize);
		
		JLabel lblArrayValues = new JLabel("Array Values:");
		lblArrayValues.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblArrayValues.setBounds(36, 133, 119, 28);
		lblArrayValues.setVisible(false);
		inputArrayPanel.add(lblArrayValues);
		
		txtFieldArraySize = new JTextField();
		txtFieldArraySize.setText("Maximum 20");
		txtFieldArraySize.setHorizontalAlignment(SwingConstants.LEFT);
		txtFieldArraySize.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFieldArraySize.setBounds(185, 41, 150, 32);
		txtFieldArraySize.setFocusable(false);
		
		txtFieldArraySize.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				txtFieldArraySize.setFocusable(true);	
			}
		});
		txtFieldArraySize.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtFieldArraySize.getText().trim().equals("Maximum 20"))
					txtFieldArraySize.setText("");
				else return;
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (txtFieldArraySize.getText().trim().equals(""))
					txtFieldArraySize.setText("Maximum 20");
				else return;
			}
		});
		
		inputArrayPanel.add(txtFieldArraySize);
		txtFieldArraySize.setColumns(10);
		
		txtFieldArrayValues = new JTextField();
		txtFieldArrayValues.setToolTipText("");
		txtFieldArrayValues.setText("Type values using comma seperated");
		txtFieldArrayValues.setHorizontalAlignment(SwingConstants.LEFT);
		txtFieldArrayValues.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFieldArrayValues.setColumns(10);
		txtFieldArrayValues.setBounds(185, 133, 429, 32);
		txtFieldArrayValues.setVisible(false);
		
		txtFieldArrayValues.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtFieldArrayValues.getText().trim().equals("Type values using comma seperated"))
					txtFieldArrayValues.setText("");
				else return;
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (txtFieldArrayValues.getText().trim().equals(""))
					txtFieldArrayValues.setText("Type values using comma seperated");
				else return;
			}
		});
		
		inputArrayPanel.add(txtFieldArrayValues);
		
		JLabel lblOption = new JLabel("Generate value:");
		lblOption.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOption.setBounds(36, 89, 139, 28);
		inputArrayPanel.add(lblOption);
		
		JRadioButton rdbtnRandom = new JRadioButton("Random");
		rdbtnRandom.setSelected(true);
		
		buttonGroup.add(rdbtnRandom);
		rdbtnRandom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnRandom.setBounds(185, 89, 103, 32);
		inputArrayPanel.add(rdbtnRandom);
		
		JRadioButton rdbtnType = new JRadioButton("Type");
		buttonGroup.add(rdbtnType);
		rdbtnType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnType.setBounds(290, 89, 103, 32);
		
		rdbtnRandom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isRandom = true;
				lblArrayValues.setVisible(false);
				txtFieldArrayValues.setVisible(false);
				
			}
		});
		
		rdbtnType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isRandom = false;
				lblArrayValues.setVisible(true);
				txtFieldArrayValues.setVisible(true);
				
			}
		});
		
		inputArrayPanel.add(rdbtnType);

		
		JLabel lblNewLabel = new JLabel("Input");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(73, 21, 64, 28);
		contentPane.add(lblNewLabel);
		
		JButton btnBubbleSort = new JButton("Bubble Sort");
		btnBubbleSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = handleInput(txtFieldArraySize.getText().trim(), txtFieldArrayValues.getText().trim());
				if (check == true) {
					new SortFrame(array, "Bubble Sort");
				}
				
			}
		});
		btnBubbleSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBubbleSort.setBounds(73, 267, 150, 34);
		contentPane.add(btnBubbleSort);
		
		JButton btnInsertionSort = new JButton("Insertion Sort");
		btnInsertionSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnInsertionSort.setBounds(576, 267, 150, 34);
		btnInsertionSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = handleInput(txtFieldArraySize.getText().trim(), txtFieldArrayValues.getText().trim());
				if (check == true) {
					new SortFrame(array, "Insertion Sort");
				}
				
			}
		});
		contentPane.add(btnInsertionSort);
		
		JButton btnQuickSort = new JButton("Quick Sort");
		btnQuickSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnQuickSort.setBounds(328, 267, 150, 34);
		btnQuickSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = handleInput(txtFieldArraySize.getText().trim(), txtFieldArrayValues.getText().trim());
				if (check == true) {
					new SortFrame(array, "Quick Sort");
				}
				
			}
		});
		contentPane.add(btnQuickSort);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.dispose();
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(576, 339, 150, 34);
		contentPane.add(btnExit);
	}
	
	public boolean handleInput(String strSize, String strValues) {
		int arraySize;
		if (strSize.equals("") || strSize.equals("Maximum 20")) {
			new Dialog("Please enter size of array.");
			return false;
		}
		
		try {
			arraySize = Integer.parseInt(strSize);
		} catch (Exception e2) {
			new Dialog("Array size must be an integer number.");
			return false;
		}
		
		if (arraySize > 20) {
			new Dialog("Array size should be at most 20.");
			return false;
		}
		array = new int[arraySize];
		Random rand = new Random();
		if (isRandom) {
			for (int i = 0; i < arraySize; i++) {
				array[i] = rand.nextInt(25) + 1;
			}
			return true;
		}
		
		if (strValues == null) return false;
		if (strValues.equals("") || strValues.equals("Type values using comma seperated")) {
			new Dialog("Please enter array values.");
			return false;
		}
		
		String[] strArrayValues = strValues.split("[-,.: ]+");
		if (strArrayValues.length != arraySize) {
			new Dialog("Number of input values does not match array size.");
			return false;
		}
		int temp;
		for (int i = 0; i < strArrayValues.length; i++) {
			try {
				temp = Integer.parseInt(strArrayValues[i]);
			} catch (Exception e2) {
				new Dialog("Value must be an integer.");
				return false;
			}
			array[i] = temp;
		}
		return true;
	}
}
