package application;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
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
	private static JComboBox<String> chooseTranslationTable;
	private static JComboBox<String> chooseOrganismReference;
	private static int tableTranslationID = 1;
	
	public menu() {
	    try {
	        for ( LookAndFeelInfo info : UIManager.getInstalledLookAndFeels() ) {
	        	//System.out.println( info.getName() );
	            if ("Windows".equalsIgnoreCase(info.getName())) {
	                UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    } 
	    catch ( Exception e) {
	      
	    }
	    biocodon.prepare( tableTranslationID );
	    createMenu();
	    refreshMenu();
	}

	
	
	public static void showResults( String nucleinString, String results ) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth() * 0.8;
		double height = screenSize.getHeight() * 0.8;
		
		JDialog resultDialog = new JDialog();
		resultDialog.setTitle("Analyse results");
		Image icon = Toolkit.getDefaultToolkit().getImage("ressources/dna_logo_01.png");    
		resultDialog.setIconImage(icon);    
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
		mainConstraint.weighty = 0.1;
		mainConstraint.gridwidth = 1;
		mainConstraint.insets = new Insets( 5, 5, 5, 5 );
		mainConstraint.ipadx = 5;
		mainConstraint.ipady = 5;
		
		JLabel AminoAcidsInfo = new JLabel("Amino Acids");
		AminoAcidsInfo.setFont(new Font("Calibri", Font.BOLD, 20));
		resultDialog.add( AminoAcidsInfo, mainConstraint );
		
		mainConstraint.gridy = 6;
		mainConstraint.weighty = 0.8;
		JTextArea  resultsConsole = new JTextArea ();
		resultsConsole.setText( results );
		resultsConsole.setEditable( false );
		resultsConsole.setLineWrap(false);
		resultsConsole.setWrapStyleWord(true);
		JScrollPane leftScrollPanel = new JScrollPane(resultsConsole);
		resultDialog.add( leftScrollPanel, mainConstraint );

		
		mainConstraint.weighty = 0.1;
		
		mainConstraint.gridx = 0;
		mainConstraint.gridy = 8;
		
		biocodon.initBaseFrequency( nucleinString );
		//biocodon.getRCAGene( nucleinString );
		double CodonAdaptionIndex = ReferenceDataSets.writeCAI( nucleinString );
		double gcContent = biocodon.getGCContent( nucleinString );
		double gc1Content = biocodon.getGCContentPos( nucleinString, 0 );
		double gc2Content = biocodon.getGCContentPos( nucleinString, 1 );
		double gc3Content = biocodon.getGCContentPos( nucleinString, 2 );
		//double DCBS = biocodon.getDirectionalCodonBiasScore( nucleinString );
		
		double thyminPerc = biocodon.getAmountOf( 'T', nucleinString );
		double adeninPerc = biocodon.getAmountOf( 'A', nucleinString );
		double uracilPerc = biocodon.getAmountOf( 'U', nucleinString );
		double guaninPerc = biocodon.getAmountOf( 'G', nucleinString );
		double cytosinPerc = biocodon.getAmountOf( 'C', nucleinString );
		
		double purinPerc = adeninPerc + guaninPerc;
		double pyrimidinPerc = cytosinPerc + uracilPerc + thyminPerc;
		
		//ReferenceDataSets.getCodonRSCU( "ATT" );
		//double RSCUGene = ReferenceDataSets.getGeneRSCU( nucleinString );
		//double FOP = ReferenceDataSets.getFOP( nucleinString );
		
		
		mainConstraint.gridx = 0;
		mainConstraint.gridy = 34;
		JLabel generalInfo = new JLabel("General Analysis Data");
		generalInfo.setFont(new Font("Calibri", Font.BOLD, 20));
		resultDialog.add( generalInfo, mainConstraint );
		
		mainConstraint.gridx = 0;
		mainConstraint.gridy = 40;
		Object[] header;
		
		if ( biocodon.getNucleinAcid() == "DNA" ) {
			header = new Object[] { "Sequence Length", "Adenin", "Thymin", "Guanin", "Cytosin", "Pyrimidinbases", "Purinbases" };
		}
		else {
			header = new Object[] { "Sequence Length", "Adenin", "Uracil", "Guanin", "Cytosin", "Pyrimidinbases", "Purinbases" };
		}
		DefaultTableModel model = new DefaultTableModel ( header, 0 );
		
		mainConstraint.weighty = 0.15;
		JTable generalAnalyseData = new JTable( model );
		model.addRow(new Object[] { 
				biocodon.getSequenceLength( nucleinString ) + " bases",
				String.format("%.2f", adeninPerc ) + " %", 
				String.format("%.2f", thyminPerc ) + " %", 
				String.format("%.2f", guaninPerc ) + " %", 
				String.format("%.2f", cytosinPerc ) + " %", 
				String.format("%.2f", pyrimidinPerc ) + " %", 
				String.format("%.2f", purinPerc ) + " %", 
			}
		);
		resultDialog.add(new JScrollPane(generalAnalyseData), mainConstraint );
		
		/*
		mainConstraint.weighty = 0.1;
		mainConstraint.gridy = 50;
		JLabel codonfrequencyData = new JLabel("Indices based on codon frequency in a reference set of genes");
		codonfrequencyData.setFont(new Font("Calibri", Font.BOLD, 20));
		resultDialog.add( codonfrequencyData, mainConstraint );
		
		mainConstraint.gridy = 52;
		header = new Object[] { "Index", "Description", "Range", "Value" };
		model = new DefaultTableModel ( header, 0 );
		mainConstraint.weighty = 0.45;
		JTable frequencyDataTable = new JTable( model );
		model.addRow(new Object[] { 
				"FOP",
				"Frequency of Optimal Codons",
				"0~1",
				String.format("%.2f", FOP )
			}
		);
		model.addRow(new Object[] { 
				"DCBS",
				"Directional Codon Bias Score",
				"≥1",
				String.format("%.2f", DCBS )
			}
		);
		model.addRow(new Object[] { 
				"CAI",
				"Codon Adaptation Index",
				"0-1",
				String.format("%.2f", CodonAdaptionIndex )
			}
		);
		model.addRow(new Object[] { 
				"RSCU",
				"Relative Synonymous Codon Use",
				"-1~1",
				String.format("%.2f", RSCUGene )
			}
		);
		resultDialog.add(new JScrollPane(frequencyDataTable), mainConstraint );
		*/
		
		mainConstraint.gridy = 80;
		mainConstraint.weighty = 0.1;
		JLabel complexPatternsLabel = new JLabel("Indices based on complex patterns of codon usage");
		complexPatternsLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		resultDialog.add( complexPatternsLabel, mainConstraint );
		
		mainConstraint.gridy = 82;
		header = new Object[] { "Index", "Description", "Range", "Value" };
		model = new DefaultTableModel ( header, 0 );
		mainConstraint.weighty = 0.45;
		JTable complexPatternsDataTable = new JTable( model );
		model.addRow(new Object[] { 
				"GC",
				"GC Content",
				"0~1",
				String.format("%.2f", gcContent )
			}
		);
		model.addRow(new Object[] { 
				"GC1",
				"GC 1 Content",
				"0~1",
				String.format("%.2f", gc1Content )
			}
		);
		model.addRow(new Object[] { 
				"GC2",
				"GC 2 Content",
				"0~1",
				String.format("%.2f", gc2Content )
			}
		);
		model.addRow(new Object[] { 
				"GC3",
				"GC 3 Content",
				"0~1",
				String.format("%.2f", gc3Content )
			}
		);
		resultDialog.add(new JScrollPane(complexPatternsDataTable), mainConstraint );
		
		mainConstraint.gridx = 0;
		mainConstraint.gridy = 100;
		
		mainConstraint.weighty = 0.05;
		/*
		JButton saveButton = new JButton("Save Results");
		resultDialog.add( saveButton, mainConstraint );
		*/
	    
		resultDialog.setVisible( true );
	}
	
	public static void refreshMenu( ) {
		biocodon.prepare( tableTranslationID );
		model = new DefaultTableModel ( header, 0 );
		Map<String, String> dataMap = biocodon.getTriplettTable();
		
		for (var entry : dataMap.entrySet()) {
			boolean stopcodon = false;
			boolean initcodon = false;
			if ( entry.getValue() == biocodon.getStopCodeSign() ) {
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
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		menu = new JFrame("Biocodon");
		menu.setSize( (int)width, (int)height);
		menu.setLocationRelativeTo(null);
		Image icon = Toolkit.getDefaultToolkit().getImage("ressources/dna_logo_01.png");    
		menu.setIconImage(icon);    
		
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
	    leftConstraint.gridwidth = 1;
		leftConstraint.insets = new Insets( 5, 5, 5, 5 );
		leftConstraint.ipadx = 5;
		leftConstraint.ipady = 5;
	    leftMainPanel.setLayout(leftLayout);
		menu.add( leftMainPanel, mainConstraint );
		
		leftConstraint.gridx = 0;
		leftConstraint.gridy = 2;
		leftConstraint.weighty = 0.1;
		JLabel translationLabel = new JLabel("Select Translation Table");
		translationLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		leftMainPanel.add( translationLabel, leftConstraint );
		
		/*
		leftConstraint.gridx = 1;
		JLabel organismLabel = new JLabel("Select Organism Reference Set");
		organismLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		leftMainPanel.add( organismLabel, leftConstraint );
		*/
		
		leftConstraint.gridx = 0;
		leftConstraint.gridy = 10;
		chooseTranslationTable = new JComboBox();
		for ( var entry : biocodon.getTranslationtables().entrySet()) {
			chooseTranslationTable.addItem( entry.getValue() );
		}
		chooseTranslationTable.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	for ( var entry: biocodon.getTranslationtables().entrySet() ) {
		    		if ( entry.getValue() == chooseTranslationTable.getSelectedItem() ) {
		    			tableTranslationID = entry.getKey();
		    		}
		    	}
		    	refreshMenu();
		    }
		});
		leftMainPanel.add(chooseTranslationTable, leftConstraint);
	
		/*
		leftConstraint.gridx = 1;
		chooseOrganismReference = new JComboBox();
		leftMainPanel.add(chooseOrganismReference, leftConstraint);
		*/
		
		leftConstraint.gridwidth = 2;
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
		
		JRadioButton dnaAcid = new JRadioButton ("DNA", true);
		dnaAcid.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	biocodon.setNucleinAcid("DNA");
		    	refreshMenu();
		    }
		});
		
		JRadioButton rnaAcid = new JRadioButton ("RNA", false);
		rnaAcid.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	biocodon.setNucleinAcid("RNA");
		    	refreshMenu();
		    }
		});
		
		ButtonGroup group = new ButtonGroup();
		group.add( rnaAcid );
		group.add( dnaAcid );
		leftMainPanel.add( dnaAcid, leftConstraint );
		leftConstraint.gridx = 1;
		leftMainPanel.add( rnaAcid, leftConstraint );
		leftConstraint.gridwidth = 2;
		
		leftConstraint.gridx = 0;
		leftConstraint.gridy = 30;
		infoLabel = new JLabel("Input from User: ");
		leftMainPanel.add( infoLabel, leftConstraint );
		
		leftConstraint.weighty = 0.4;
		leftConstraint.gridx = 1;
		leftConstraint.gridy = 30;
		codeInput = new JTextArea();
		codeInput.setEditable( true );
		codeInput.setLineWrap( false );
		codeInput.setWrapStyleWord( true );
		codeInput.setText( "ATGGCTATCGACGAAAACAAACAGAAAGCGTTGGCGGCAGCACTGGGCCAGATTGAGAAACAATTTGGTAAAGGCTCCATCATGCGCCTGGGTGAAGACCGTTCCATGGATGTGGAAACCATCTCTACCGGTTCGCTTTCACTGGATATCGCGCTTGGGGCAGGTGGTCTGCCGATGGGCCGTATCGTCGAAATCTACGGACCGGAATCTTCCGGTAAAACCACGCTGACGCTGCAGGTGATCGCCGCAGCGCAGCGTGAAGGTAAAACCTGTGCGTTTATCGATGCTGAACACGCGCTGGACCCAATCTACGCACGTAAACTGGGCGTCGATATCGACAACCTGCTGTGCTCCCAGCCGGACACCGGCGAGCAGGCACTGGAAATCTGTGACGCCCTGGCGCGTTCTGGCGCAGTAGACGTTATCGTCGTTGACTCCGTGGCGGCACTGACGCCGAAAGCGGAAATCGAAGGCGAAATCGGCGACTCTCACATGGGCCTTGCGGCACGTATGATGAGCCAGGCGATGCGTAAGCTGGCGGGTAACCTGAAGCAGTCCAACACGCTGCTGATCTTCATCAACCAGATCCGTATGAAAATTGGTGTGATGTTCGGTAACCCGGAAACCACTACCGGTGGTAACGCGCTGAAATTCTACGCCTCTGTTCGTCTCGACATCCGTCGTATCGGCGCGGTGAAAGAGGGCGAAAACGTGGTGGGTAGCGAAACCCGCGTGAAAGTGGTGAAGAACAAAATCGCTGCGCCGTTTAAACAGGCTGAATTCCAGATCCTCTACGGCGAAGGTATCAACTTCTACGGCGAACTGGTTGACCTGGGCGTAAAAGAGAAGCTGATCGAGAAAGCAGGCGCGTGGTACAGCTACAAAGGTGAGAAGATCGGTCAGGGTAAAGCGAATGCGACTGCCTGGCTGAAAGATAACCCGGAAACCGCGAAAGAGATCGAGAAGAAAGTACGTGAGTTGCTGCTGAGCAACCCGAACTCAACGCCGGATTTCTCTGTAGATGATAGCGAAGGCGTAGCAGAAACTAACGAAGATTTTTAA" );
		leftMainPanel.add(new JScrollPane(codeInput), leftConstraint );
		
		leftConstraint.weighty = 0.05;
		
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
					  
					  if ( (boolean) triplettTable.getValueAt(i, 2) == true || triplet == biocodon.getStopCodeSign() ) {
						  biocodon.insertStopCodon( triplet );
					  }
					  if ( (boolean) triplettTable.getValueAt(i, 3) == true ) {
						  biocodon.insertInitCodon( triplet );
					  }
				  }
				  
				  String result = "";
				  if (rnaAcid.isSelected()) {
					  biocodon.prepare( tableTranslationID );
					  result = biocodon.decode( inputData );
				  }
				  else {
					  biocodon.prepare( tableTranslationID );
					  result = biocodon.decode( inputData );
				  }
				  showResults( inputData[0], result );
			  } 
		});
		leftMainPanel.add( runDecode, leftConstraint );

		menu.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		menu.setVisible( true );
	}
}


