package trab4;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class trab4gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=111,279
	 */
	private final JLabel label = new JLabel("New label");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trab4gui frame = new trab4gui();
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
	private trab4gui() {
		final JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo de Texto", "txt");
		fc.setFileFilter(filter);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("New label");
		contentPane.add(lblNewLabel, BorderLayout.SOUTH);
		
		JTree tree = new JTree();
		scrollPane.setRowHeaderView(tree);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setVisible(false);
		textArea.setText("");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu_1 = new JMenu("Arquivo");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("Abrir");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				int returnVal = fc.showOpenDialog(mnNewMenu_1);
				Scanner fileOutput = null;
				if(returnVal == JFileChooser.APPROVE_OPTION) {					
					try {
						textArea.setVisible(true);
						File file  = new File(fc.getSelectedFile().getAbsolutePath());
						fileOutput = new Scanner(file);
						if(file.isFile()) {
							while(fileOutput.hasNext()) {
								String line = fileOutput.nextLine() + "\n";
								textArea.append(line);
							}
						}
					
						
						lblNewLabel.setText(file.getAbsolutePath());
					} catch (FileNotFoundException e1) {	
						System.out.print("deu ruim");
					} finally {
						fileOutput.close();
					}
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Fechar");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				textArea.setVisible(false);
				fc.setSelectedFile(null);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Salvar");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fc.getSelectedFile() != null && fc.getSelectedFile().isFile()) {					
					try {
						FileWriter fw = new FileWriter(fc.getSelectedFile().getAbsolutePath());
						fw.write(textArea.getText());
						fw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Salvar Como");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textArea.getText() != "") {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File("."));
	
					if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						try {
							File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
							PrintWriter fileOut = new PrintWriter(file);
							fileOut.println(textArea.getText());
							fileOut.close();
						} 
						catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);

		setContentPane(contentPane);
		
		
	
	}

}
