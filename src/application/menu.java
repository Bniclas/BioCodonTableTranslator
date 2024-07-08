package application;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

public class menu {
	private static JFrame menu;
	private static JMenuBar menuBar;
	private static JMenu mainMenu;
	private static JTextArea Console;
	private static JTextArea codeInput;
	private static final Object[] header = new Object[] { "TRIPLET", "AMINO ACID", "STOP", "INIT" };
	@SuppressWarnings("serial")
	private static DefaultTableModel model = new DefaultTableModel ( header, 0 );
	private static JTable triplettTable = new JTable( model ){
        @Override
        public Class getColumnClass(int columnIndex) {
            switch (columnIndex) {
	            case 0:
	                return String.class;
	            case 1:
	                return String.class;
	            case 2:
	                return Boolean.class;
	            default:
	                return Boolean.class;
            }
        }
	};
	private static JComboBox<String> chooseOrganism;
	private static int organismTranslationID = 1;
	
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
	    biocodonencoder.prepare( organismTranslationID );
	    createMenu();
	    refreshMenu();
	}
	
	public static void clearConsole() {
		Console.setText("");
	}
	
	public static void printConsole( String text ) {
		Console.append(text);
		Console.append("\n");
	}
	
	public static void refreshMenu( ) {
		biocodonencoder.prepare( organismTranslationID );
		model = new DefaultTableModel ( header, 0 );
		Map<String, String> dataMap = biocodonencoder.getTriplettTable();
		
		for (var entry : dataMap.entrySet()) {
		    model.addRow(new Object[] { entry.getKey(), entry.getValue(), false, false });
		}

		model.fireTableDataChanged();
		triplettTable.setModel(model);
		triplettTable.repaint();
		//menu.revalidate();
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
	    leftConstraint.gridwidth = 2;
	    leftMainPanel.setLayout(leftLayout);
		
		menu.add( leftMainPanel, mainConstraint );
		
		leftConstraint.gridx = 0;
		leftConstraint.gridy = 5;
		
		chooseOrganism = new JComboBox();
		for ( var entry : biocodonencoder.getOrganismList().entrySet()) {
			chooseOrganism.addItem( entry.getValue() );
		}
		chooseOrganism.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	for ( var entry: biocodonencoder.getOrganismList().entrySet() ) {
		    		if ( entry.getValue() == chooseOrganism.getSelectedItem() ) {
		    			organismTranslationID = entry.getKey();
		    		}
		    	}
		    	refreshMenu();
		    }
		});
		leftMainPanel.add(chooseOrganism, leftConstraint);
		
		leftConstraint.gridx = 0;
		leftConstraint.gridy = 20;
		leftConstraint.weightx = 0.5;
		leftConstraint.weighty = 1;
		leftMainPanel.add( triplettTable, leftConstraint );
		JScrollPane leftScrollPanel = new JScrollPane(triplettTable);
		leftMainPanel.add( leftScrollPanel, leftConstraint );
		
		leftConstraint.gridx = 0;
		leftConstraint.gridy = 25;
		JLabel infoLabel = new JLabel("Input your triplet code here:");
		leftMainPanel.add( infoLabel, leftConstraint );
		
		leftConstraint.gridwidth = 1;
		leftConstraint.gridx = 0;
		leftConstraint.gridy = 27;
		JRadioButton rnaAcid = new JRadioButton ("RNA", true);
		JRadioButton dnaAcid = new JRadioButton ("DNA", false);
		ButtonGroup group = new ButtonGroup();
		group.add( rnaAcid );
		group.add( dnaAcid );
		leftMainPanel.add( rnaAcid, leftConstraint );
		leftConstraint.gridx = 1;
		leftMainPanel.add( dnaAcid, leftConstraint );
		leftConstraint.gridwidth = 2;
		
		leftConstraint.gridx = 0;
		leftConstraint.gridy = 30;
		codeInput = new JTextArea();
		codeInput.setEditable( true );
		leftMainPanel.add( codeInput, leftConstraint );
		
		leftConstraint.gridx = 0;
		leftConstraint.gridy = 40;
		JButton runDecode = new JButton("Decode input");
		runDecode.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  clearConsole();
				  String[] inputData = new String[1];
				  inputData[0] = codeInput.getText();
				  String result = "";
				  if (rnaAcid.isSelected()) {
					  biocodonencoder.setNucleinAcid("RNA");
					  biocodonencoder.prepare( organismTranslationID );
					  result = biocodonencoder.decodeRNA( inputData );
				  }
				  else {
					  biocodonencoder.setNucleinAcid("DNA");
					  biocodonencoder.prepare( organismTranslationID );
					  result = biocodonencoder.decodeDNA( inputData );
				  }
				  printConsole( result );
			  } 
		});
		leftMainPanel.add( runDecode, leftConstraint );
		
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


