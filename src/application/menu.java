package application;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

public class menu {
	private static JFrame menu;
	private static JMenuBar menuBar;
	private static JMenu mainMenu;
	private static JTextArea Console;
	private static JTextArea codeInput;
	private static final Object[] header = new Object[] { "Triplet", "Amino Acid", "Stopcodon", "Initcodon" };
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
	    biocodon.prepare( organismTranslationID );
	    createMenu();
	    refreshMenu();
	}

	
	
	public static void showResults( String nucleinString, String results ) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth() * 0.7;
		double height = screenSize.getHeight() * 0.7;
		
		JDialog resultDialog = new JDialog();
		resultDialog.setTitle("Analyse results");
		resultDialog.setSize( (int)width, (int)height);
		resultDialog.setLocationRelativeTo(null);
	    GridBagLayout mainBagLayout = new GridBagLayout();
	    GridBagConstraints mainConstraint = new GridBagConstraints();
	    mainBagLayout.setConstraints(resultDialog,mainConstraint);
	    mainConstraint.fill = GridBagConstraints.BOTH;
	    resultDialog.setLayout(mainBagLayout);
	    
		mainConstraint.gridx = 0;
		mainConstraint.gridy = 0;
		mainConstraint.weightx = 1;
		mainConstraint.weighty = 0.8;
		mainConstraint.gridwidth = 1;
		
		JTextArea  resultsConsole = new JTextArea ();
		resultsConsole.setText( results );
		resultsConsole.setEditable( false );
		resultsConsole.setLineWrap(true);
		resultsConsole.setWrapStyleWord(true);
		JScrollPane leftScrollPanel = new JScrollPane(resultsConsole);
		resultDialog.add( leftScrollPanel, mainConstraint );
		
		mainConstraint.weighty = 0.05;
		
		mainConstraint.gridx = 0;
		mainConstraint.gridy = 8;
		
		JLabel nucleinStringLengthLabel = new JLabel("Size: " + biocodon.getNucleinLength( nucleinString ));
		resultDialog.add( nucleinStringLengthLabel, mainConstraint );
		
		mainConstraint.gridx = 0;
		mainConstraint.gridy = 10;
		
		JLabel guaninAmount = new JLabel("Guanin: " + biocodon.getAmountOf( 'G', nucleinString ));
		resultDialog.add( guaninAmount, mainConstraint );
		
		mainConstraint.gridy = 12;
		JLabel cytosinAmount = new JLabel("Cytosin: " + biocodon.getAmountOf( 'C', nucleinString ));
		resultDialog.add( cytosinAmount, mainConstraint );
		
		mainConstraint.gridy = 14;
		if ( biocodon.getNucleinAcid() == "DNA") {
			JLabel thyminAmount = new JLabel("Thymin: " + biocodon.getAmountOf( 'T', nucleinString ) );
			resultDialog.add( thyminAmount, mainConstraint );
		}
		else {
			JLabel uracilAmount = new JLabel("Uracil: " + biocodon.getAmountOf( 'U', nucleinString ));
			resultDialog.add( uracilAmount, mainConstraint );
		}
		
		mainConstraint.gridy = 16;
		JLabel adeninAmount = new JLabel("Adenin: " + biocodon.getAmountOf( 'A', nucleinString ));
		resultDialog.add( adeninAmount, mainConstraint );
		
		mainConstraint.gridx = 0;
		mainConstraint.gridy = 100;
		
		JButton saveButton = new JButton("Save Results");
		resultDialog.add( saveButton, mainConstraint );
	    
		resultDialog.setVisible( true );
	}
	
	public static void refreshMenu( ) {
		biocodon.prepare( organismTranslationID );
		model = new DefaultTableModel ( header, 0 );
		Map<String, String> dataMap = biocodon.getTriplettTable();
		
		for (var entry : dataMap.entrySet()) {
			boolean stopcodon = false;
			boolean initcodon = false;
			if ( entry.getValue() == "STOP" ) {
				stopcodon = true;
			}
			if ( entry.getValue() == "Met" ) {
				initcodon = true;
			}
			
			String triplet = entry.getKey();
			if ( biocodon.getNucleinAcid() == "DNA" ) {
				triplet = triplet.replaceAll("U","T");
			}
			else {
				triplet = triplet.replaceAll("T","U");
			}
		    model.addRow(new Object[] { triplet, entry.getValue(), stopcodon, initcodon });
		}

		model.fireTableDataChanged();
		triplettTable.setModel(model);
		triplettTable.repaint();
		//menu.revalidate();
	}
	
	public static void createMenu() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth() * 0.6;
		double height = screenSize.getHeight() * 0.6;
		
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
		for ( var entry : biocodon.getOrganismList().entrySet()) {
			chooseOrganism.addItem( entry.getValue() );
		}
		chooseOrganism.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	for ( var entry: biocodon.getOrganismList().entrySet() ) {
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
		leftConstraint.weighty = 0.7;
		leftMainPanel.add( triplettTable, leftConstraint );
		JScrollPane leftScrollPanel = new JScrollPane(triplettTable);
		leftMainPanel.add( leftScrollPanel, leftConstraint );
		
		
		leftConstraint.gridx = 0;
		leftConstraint.gridy = 28;
		leftConstraint.weighty = 0.05;
		JLabel infoLabel = new JLabel("Input from File: ");
		leftMainPanel.add( infoLabel, leftConstraint );
		leftConstraint.gridx = 1;
		leftConstraint.gridy = 28;
		leftConstraint.gridwidth = 3;
		JTextField fileChoose = new JTextField("");
		leftMainPanel.add( fileChoose, leftConstraint );
		
		leftConstraint.gridwidth = 1;
		leftConstraint.gridx = 0;
		leftConstraint.gridy = 27;
		JRadioButton rnaAcid = new JRadioButton ("RNA", true);
		rnaAcid.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	biocodon.setNucleinAcid("RNA");
		    	refreshMenu();
		    }
		});
		JRadioButton dnaAcid = new JRadioButton ("DNA", false);
		dnaAcid.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	biocodon.setNucleinAcid("DNA");
		    	refreshMenu();
		    }
		});
		ButtonGroup group = new ButtonGroup();
		group.add( rnaAcid );
		group.add( dnaAcid );
		leftMainPanel.add( rnaAcid, leftConstraint );
		leftConstraint.gridx = 1;
		leftMainPanel.add( dnaAcid, leftConstraint );
		leftConstraint.gridwidth = 2;
		
		leftConstraint.gridx = 0;
		leftConstraint.gridy = 30;
		infoLabel = new JLabel("Input from User: ");
		leftMainPanel.add( infoLabel, leftConstraint );
		
		leftConstraint.gridx = 1;
		leftConstraint.gridy = 30;
		codeInput = new JTextArea();
		codeInput.setEditable( true );
		codeInput.setLineWrap(true);
		codeInput.setWrapStyleWord(true);
		codeInput.setText( "AUG,AUA,AUC,UAG,AUG,UAG,AUG,UUG,UUC,AUG,UUC,CCU,UAA" );
		leftMainPanel.add( codeInput, leftConstraint );
		
		leftConstraint.gridx = 0;
		leftConstraint.gridy = 40;
		leftConstraint.weighty = 0.05;
		JButton runDecode = new JButton("Analyse");
		runDecode.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  biocodon.clearStopCodons();
				  biocodon.clearInitCodons();
				  
				  File dataFile = new File( fileChoose.getText() );
				  String[] inputData = new String[1];
				  inputData[0] = "";
				  
				  if ( dataFile.exists() ) {
					  try {
						  BufferedReader reader = new BufferedReader ( new FileReader( fileChoose.getText( )) );
						  String line;
						  while ( (line = reader.readLine() ) != null) {
					          inputData[0] = inputData[0] + line;
					      }
					      reader.close();
					  } 
					  catch (IOException e1) {
						  e1.printStackTrace();
					  }
				  }
				  else {
					  inputData[0] = codeInput.getText();
				  }

				  
				  for (int i=0; i<triplettTable.getRowCount(); i++ ) {
					  String triplet = (String) triplettTable.getValueAt(i, 0);
					  
					  if ( (boolean) triplettTable.getValueAt(i, 2) == true || triplet == "STOP" ) {
						  biocodon.insertStopCodon( triplet );
					  }
					  if ( (boolean) triplettTable.getValueAt(i, 3) == true ) {
						  biocodon.insertInitCodon( triplet );
					  }
				  }
				  
				  String result = "";
				  if (rnaAcid.isSelected()) {
					  biocodon.prepare( organismTranslationID );
					  result = biocodon.decode( inputData );
				  }
				  else {
					  biocodon.prepare( organismTranslationID );
					  result = biocodon.decode( inputData );
				  }
				  showResults( inputData[0], result );
			  } 
		});
		leftMainPanel.add( runDecode, leftConstraint );

		
		menu.setVisible( true );
	}
}


