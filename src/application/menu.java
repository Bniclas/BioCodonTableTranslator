package application;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class menu {
	private static JFrame menu;
	private static JMenuBar menuBar;
	private static JMenu mainMenu;
	private static JTextArea Console;
	
	public menu() {
	    try {
	        for ( LookAndFeelInfo info : UIManager.getInstalledLookAndFeels() ) {
	        	//System.out.println( info.getName() );
	            if ("Nimbus".equalsIgnoreCase(info.getName())) {
	                UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    } 
	    catch ( Exception e) {
	      
	    }
	    
	    createMenu();
	}
	
	public static void createMenu() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth() * 0.7;
		double height = screenSize.getHeight() * 0.7;
		
		menu = new JFrame("Biocodon");
		menu.setSize( (int)width, (int)height);
		menu.setLocationRelativeTo(null);
	    GridBagLayout mainBagLayout = new GridBagLayout();
	    GridBagConstraints mainConstraint = new GridBagConstraints();
	    mainBagLayout.setConstraints(menu,mainConstraint);
	    mainConstraint.fill = GridBagConstraints.BOTH;
	    menu.setLayout(mainBagLayout);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		menu.setJMenuBar( menuBar );
		mainMenu = new JMenu("Mainmenu");
		menuBar.add(mainMenu);
		
		mainConstraint.gridx = 0;
		mainConstraint.gridy = 0;
		mainConstraint.weightx = 0.5;
		mainConstraint.weighty = 1;
		mainConstraint.gridwidth = 1;
		
		JPanel leftMainPanel = new JPanel();
	    GridBagLayout leftLayout = new GridBagLayout();
	    GridBagConstraints leftConstraint = new GridBagConstraints();
	    leftLayout.setConstraints(menu,leftConstraint);
	    leftConstraint.fill = GridBagConstraints.BOTH;
	    leftMainPanel.setLayout(leftLayout);
		menu.add( leftMainPanel, mainConstraint );
		
		mainConstraint.gridx = 1;
		mainConstraint.gridy = 0;
		JPanel rightMainPanel = new JPanel();
	    GridBagLayout rightLayout = new GridBagLayout();
	    GridBagConstraints rightConstraint = new GridBagConstraints();
	    rightLayout.setConstraints(menu,rightConstraint);
	    rightConstraint.fill = GridBagConstraints.BOTH;
	    rightMainPanel.setLayout(rightLayout);
		menu.add( rightMainPanel, mainConstraint );
		
		Console = new JTextArea();
		Console.setEditable( false );
		rightConstraint.gridx = 0;
		rightConstraint.gridy = 0;
		rightConstraint.weightx = 1;
		rightConstraint.weighty = 1;
		JScrollPane rightScrollPanel = new JScrollPane(Console); 
		rightMainPanel.add( rightScrollPanel, rightConstraint );
		
		menu.setVisible( true );
	}
}
