package application;

import java.util.Map;


import java.util.HashMap;
import java.util.Vector;

/*
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
*/

public class biocodonencoder {
	static final Map<String , String> RNAcodonTable = new HashMap<String , String>();
	static final Map<String , String> DNAcodonTable = new HashMap<String , String>();
	
	static final Map<String, String> CodonTableToUse = new HashMap<String , String>();
	
	private static final String stopCode = "STOP";
	@SuppressWarnings("serial")
	static final Map<Integer, String> organismSelection = new HashMap<Integer, String>() {{
		put(1, "Human (Standard)");
		put(2, "Vertebrate mitochondrial");
		put(3, "Yeast mitochondrial");
		put(4, "Protozoan");
		put(5, "Invertebrate mitochondrial");
		put(6, "Ciliate");
		put(7, "Echinoderm/Flatworm");
		put(8, "Eplotid nuclear");
		put(9, "Bacterial/Plant Plastid/Archael");
		put(10, "Alternative yeast nuclear");
		put(11, "Alternative flatworm mitochondira");
		put(12, "Blepharisma nuclear");
		put(13, "Chlorophycean mitochondrial");
	}};
	
	private static int selectedOrganism = 1;
	public static void setOrganism( int organismNumber ) {
		selectedOrganism = organismNumber;
	}
	
	static final Map<Integer, Object> overwriteNAbyOrganism = new HashMap<Integer, Object>();
	
	public static void initOrganismNA() {
		Map<String, String> overwrite = new HashMap<String , String> (){{
			put("AGA","STOP");
			put("AGG","STOP");
			put("A#A","Met");
			put("#GA","Trp");
		}};
		overwriteNAbyOrganism.put(2, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("A#A","Met");
			put("C##","Thr");
			put("C#C","Thr");
			put("C#A","Thr");
			put("C#G","Thr");
			put("#GA","Trp");
			put("CGA",null);
			put("CGC",null);
		}};
		overwriteNAbyOrganism.put(3, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("#G#","Met");
		}};
		overwriteNAbyOrganism.put(4, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("AGA","Ser");
			put("AGG","Ser");
			put("A#A","Met");
			put("#GA","Trp");
		}};
		overwriteNAbyOrganism.put(5, overwrite);
	
		overwrite = new HashMap<String , String> (){{
			put("#AA","Gln");
			put("#AG","Gln");
		}};
		overwriteNAbyOrganism.put(6, overwrite);
		

	}
	
	public static void overwriteOrganismNA() {
		if ( overwriteNAbyOrganism.get( selectedOrganism ) == null ) {
			return;
		}
		
		Map<String, String> overwriteData = (HashMap) overwriteNAbyOrganism.get( selectedOrganism );
		
		
		for ( var entry : overwriteData.entrySet() ) {
			String key = entry.getKey().replaceAll( "#", "T" );
			DNAcodonTable.put( key, entry.getValue() );
			
			key = entry.getKey().replaceAll( "#", "U" );
			RNAcodonTable.put( key, entry.getValue() );
		}
		
	}

	public static void writeBasicRNAandDNA() {
		/*
			A - Start
		*/
		RNAcodonTable.put("AGG", "Arg");
		RNAcodonTable.put("AGA", "Arg");
		RNAcodonTable.put("AGC", "Ser");
		RNAcodonTable.put("AGU", "Ser");
		
		RNAcodonTable.put("AAG", "Lys");
		RNAcodonTable.put("AAA", "Lys");
		RNAcodonTable.put("AAC", "Asn");
		RNAcodonTable.put("AAU", "Asn");
		
		RNAcodonTable.put("ACG", "Thr");
		RNAcodonTable.put("ACA", "Thr");
		RNAcodonTable.put("ACC", "Thr");
		RNAcodonTable.put("ACU", "Thr");
		
		RNAcodonTable.put("AUG", "Met");
		RNAcodonTable.put("AUA", "Ile");
		RNAcodonTable.put("AUC", "Ile");
		RNAcodonTable.put("AUU", "Ile");
		
		/*
			C - Start
		*/
		RNAcodonTable.put("CGG", "Arg");
		RNAcodonTable.put("CGA", "Arg");
		RNAcodonTable.put("CGC", "Arg");
		RNAcodonTable.put("CGU", "Arg");
		
		RNAcodonTable.put("CAG", "Gln");
		RNAcodonTable.put("CAA", "Gln");
		RNAcodonTable.put("CAC", "His");
		RNAcodonTable.put("CAU", "His");
		
		RNAcodonTable.put("CCG", "Pro");
		RNAcodonTable.put("CCA", "Pro");
		RNAcodonTable.put("CCC", "Pro");
		RNAcodonTable.put("CCU", "Pro");
		
		RNAcodonTable.put("CUG", "Leu");
		RNAcodonTable.put("CUA", "Leu");
		RNAcodonTable.put("CUC", "Leu");
		RNAcodonTable.put("CUU", "Leu");
		
		/*
			U - Start
		*/
		RNAcodonTable.put("UGG", "Trp");
		RNAcodonTable.put("UGA", stopCode);
		RNAcodonTable.put("UGC", "Cys");
		RNAcodonTable.put("UGU", "Cys");
		
		RNAcodonTable.put("UAG", stopCode);
		RNAcodonTable.put("UAA", stopCode);
		RNAcodonTable.put("UAC", "Tyr");
		RNAcodonTable.put("UAU", "Tyr");
		
		RNAcodonTable.put("UCG", "Ser");
		RNAcodonTable.put("UCA", "Ser");
		RNAcodonTable.put("UCC", "Ser");
		RNAcodonTable.put("UCU", "Ser");
		
		RNAcodonTable.put("UUG", "Leu");
		RNAcodonTable.put("UUA", "Leu");
		RNAcodonTable.put("UUC", "Phe");
		RNAcodonTable.put("UUU", "Phe");	
		
		/*
			G - Start
		*/
		RNAcodonTable.put("GGG", "Gly");
		RNAcodonTable.put("GGA", "Gly");
		RNAcodonTable.put("GGC", "Gly");
		RNAcodonTable.put("GGU", "Gly");
		
		RNAcodonTable.put("GAG", "Glu");
		RNAcodonTable.put("GAA", "Glu");
		RNAcodonTable.put("GAC", "Asp");
		RNAcodonTable.put("GAU", "Asp");
		
		RNAcodonTable.put("GCG", "Ala");
		RNAcodonTable.put("GCA", "Ala");
		RNAcodonTable.put("GCC", "Ala");
		RNAcodonTable.put("GCU", "Ala");
		
		RNAcodonTable.put("GUG", "Val");
		RNAcodonTable.put("GUA", "Val");
		RNAcodonTable.put("GUC", "Val");
		RNAcodonTable.put("GUU", "Val");	
		
		for (var entry : RNAcodonTable.entrySet()) {
			String key = entry.getKey().replaceAll( "U", "T" );
			DNAcodonTable.put( key, entry.getValue() );
		}
	}
	
	static final Map<String , String> stopCodonTranslation = new HashMap<String , String>() {{
		put("TAG", "Amber");
		put("TAA", "Ochre");
		put("TGA", "Opal");
		
		put("UAG", "Amber");
		put("UAA", "Ochre");
		put("UGA", "Opal");
	}};

	public static void prepare( int selectedOrganism ) {
		setOrganism(selectedOrganism);
		writeBasicRNAandDNA();
		initOrganismNA();
		overwriteOrganismNA();
	}
	
	public static String nucleinToAmino( String[] args, boolean isRNA ) {
		HashMap<String, String> mapToUse = new HashMap<>();
		if ( isRNA == true ) {
			mapToUse.putAll( RNAcodonTable );  
		}
		else {
			mapToUse.putAll( DNAcodonTable );  
		}

		int partCounter = 0;
		Vector<String> rnaParts = new Vector<String>();
		String codon = null;
		String prevCodon = null;
		String nextCodon = null;
		String encodedRNA = "";
		encodedRNA = encodedRNA + "> ";
		
		if ( args.length == 0 ) {
			System.out.println("Der angegebene RNA Code ist fehlerhaft.");
			return encodedRNA;
		}
		
		args[0] = args[0].replaceAll(",", "");
		args[0] = args[0].replaceAll(";", "");
		args[0] = args[0].replaceAll(" ", "");
		
		if( args[0].length() % 3 != 0 ) {
			System.out.println("Der angegebene RNA Code ist fehlerhaft.");
			return encodedRNA;
		}
		
		for ( int i=0; i<args[0].length(); i+=3 ) {
			rnaParts.add( args[0].substring( i, i+3 ) );
		}
		
		for ( int i=0; i<rnaParts.size(); i++ ) {
			codon = mapToUse.get( rnaParts.get(i) );
			if ( i != 0 ) {
				prevCodon = mapToUse.get( rnaParts.get(i-1) );
			}
			else {
				prevCodon = null;
			}
			
			if ( i != rnaParts.size() - 1) {
				nextCodon = mapToUse.get( rnaParts.get(i+1) );
			}
			else {
				nextCodon = null;
			}

			if ( codon == null ) {
				System.out.println("Der folgende RNA-Part existiert nicht: " + rnaParts.get(i) );
				continue;
			}
			if ( codon == "Met" && i != 0 ) {
				++partCounter;
				encodedRNA = encodedRNA + "\n";
				encodedRNA = encodedRNA + "> ";
				encodedRNA = encodedRNA + codon;
			}
			else if ( codon == stopCode ) {
				++partCounter;
				encodedRNA = encodedRNA + stopCodonTranslation.get( rnaParts.get(i) );			}
			else {
				encodedRNA = encodedRNA + codon;
			}
			

			if ( i != rnaParts.size()-1 && codon != stopCode && nextCodon != "Met" ) {
				encodedRNA = encodedRNA + "-";
			}

			
			if ( ( i != 0 && prevCodon == stopCode && codon != "Met" ) || ( i == 0 && codon != "Met") ) {
				System.out.println("Fehler im RNA-Code: Abschnitt " + partCounter + " beginnt nicht mit Methionine.");
			}
			
			if ( ( i != 0 && prevCodon != stopCode && codon == "Met" ) ) {
				System.out.println("Fehler im RNA-Code: Abschnitt " + (partCounter-1) + " endete nicht mit einem STOP-Code.");
			}

			if ( i == rnaParts.size() && codon != stopCode ) {
				System.out.println("Fehler im RNA-Code: Abschnitt " + (partCounter) + " endete nicht mit einem STOP-Code.");
			}
		}
		
		return encodedRNA;
	}
	
	public static String encodeRNA( String[] args ) {
		System.out.println(">>>>>>>>>> RNA Analyse wird gestartet:\n\n");
		return nucleinToAmino(args, true);
	}
	
	
	public static String encodeDNA( String[] args ) {
		System.out.println(">>>>>>>>>> DNA Analyse wird gestartet:\n\n");
		return nucleinToAmino(args, false);
	}
	
	/*
	@Test
	void testRNAencode(){
		biocodonencoder.prepare(1);
		String[] rnaExample = new String[1];
		rnaExample[0] = "AUG,AUA,AUC,UAG";
		
	    assertEquals("> Met-Ile-Ile-Amber", biocodonencoder.encodeRNA( rnaExample ) );
	}*/
	
}
