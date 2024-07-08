package application;

import java.util.Map;


import java.util.HashMap;
import java.util.Vector;

public class biocodonencoder {
	static final Map<String , String> codonTable = new HashMap<String , String>();
	
	private static final String stopCode = "STOP";
	@SuppressWarnings("serial")
	static final Map<Integer, String> organismSelection = new HashMap<Integer, String>() {{
		put(1, "Standard");
		put(2, "Vertebrate mitochondrial");
		put(3, "Yeast mitochondrial");
		put(4, "Protozoan");
		put(5, "Invertebrate mitochondrial");
		put(6, "Ciliate");
		put(9, "Echinoderm/Flatworm");
		put(10, "Eplotid nuclear");
		put(11, "Bacterial/Plant Plastid/Archael");
		put(12, "Alternative yeast nuclear");
		put(13, "Alternative flatworm mitochondira");
		put(14, "Blepharisma nuclear");
		put(15, "Chlorophycean mitochondrial");
		put(16, "Chlorophycean mitochondrial");
		put(21, "Trematode mitochondrial");
		put(22, "Scenedesmus obliquus mitochondiral");
		put(23, "Thraustochytrium mitochondrial");
		put(24, "Pterobranchia mitochondiral");
		put(25, "Candidate division SR1 and Gracilibacteria");
		put(26, "Pachysolen tannophilus nuclear");
		put(27, "Karyorelict nuclear");
		put(28, "Condylostoma nuclear");
		put(29, "Mesodinium nuclear");
		put(30, "Peritrich nuclear");
		put(31, "Blastocrithidia nuclear");
		put(33, "Cephalodscidae mitochondrial code");
	}};
	
	private static int selectedOrganism = 1;
	public static void setOrganism( int organismNumber ) {
		selectedOrganism = organismNumber;
	}
	
	public static Map<Integer, String> getOrganismList(){
		return organismSelection;
	}
	
	static final Map<Integer, Object> overwriteNAbyOrganism = new HashMap<Integer, Object>();
	
	public static Map<String, String> getTriplettTable(){
		return codonTable; 
	}
	
	public static void initOrganismNA() {
		Map<String, String> overwrite = new HashMap<String , String> (){{
			put("AGA",stopCode);
			put("AGG",stopCode);
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
		
		overwrite = new HashMap<String , String> (){{
			put("AAA","Asn");
			put("AGA","Ser");
			put("AGG","Ser");
			put("#GA","Trp");
		}};
		overwriteNAbyOrganism.put(9, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("#GA","Cys");
		}};
		overwriteNAbyOrganism.put(10, overwrite);
		
		overwrite = new HashMap<String , String> (){{

		}};
		overwriteNAbyOrganism.put(11, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("C#G","Ser");
		}};
		overwriteNAbyOrganism.put(12, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("AGA","Gly");
			put("AGG","Gly");
			put("A#A","Met");
			put("#GA","Trp");
		}};
		overwriteNAbyOrganism.put(13, overwrite);
	
		overwrite = new HashMap<String , String> (){{
			put("AAA","Asn");
			put("AGA","Ser");
			put("AGG","Ser");
			put("#AA","Tyr");
			put("#GA","Trp");
		}};
		overwriteNAbyOrganism.put(14, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("#AG","Gln");
		}};
		overwriteNAbyOrganism.put(15, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("#AG","Leu");
		}};
		overwriteNAbyOrganism.put(16, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("#GA","Trp");
			put("A#A","Met");
			put("AGA","Ser");
			put("AGG","Ser");
			put("AAA","Asn");
		}};
		overwriteNAbyOrganism.put(21, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("#CA",stopCode);
			put("#AG","Leu");
		}};
		overwriteNAbyOrganism.put(22, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("##A",stopCode);
		}};
		overwriteNAbyOrganism.put(23, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("AGA","Ser");
			put("AGG","Lys");
			put("#GA","Trp");
		}};
		overwriteNAbyOrganism.put(24, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("#GA","Gly");
		}};
		overwriteNAbyOrganism.put(25, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("C#G","Ala");
		}};
		overwriteNAbyOrganism.put(26, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("#AA","Ser");
			put("#AG","Lys");
			put("#GA","Trp/"+stopCode);
		}};
		overwriteNAbyOrganism.put(27, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("#AA","Gln/"+stopCode);
			put("#AG","Gln/"+stopCode);
			put("#GA","Trp/"+stopCode);
		}};
		overwriteNAbyOrganism.put(28, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("#AA","Tyr");
			put("#AG","Tyr");
		}};
		overwriteNAbyOrganism.put(29, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("#AA","Glu");
			put("#AG","Glu");
		}};
		overwriteNAbyOrganism.put(30, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("#AA","Glu/"+stopCode);
			put("#AG","Glu/"+stopCode);
			put("#GA","Trp");
		}};
		overwriteNAbyOrganism.put(31, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("AGA","Ser");
			put("AGG","Lys");
			put("#AA","Tyr");
			put("#GA","Trp");
		}};
		overwriteNAbyOrganism.put(33, overwrite);
	}
	
	public static void overwriteOrganismNA() {
		if ( overwriteNAbyOrganism.get( selectedOrganism ) == null ) {
			return;
		}
		
		Map<String, String> overwriteData = (HashMap) overwriteNAbyOrganism.get( selectedOrganism );
		
		
		for ( var entry : overwriteData.entrySet() ) {
			String key = entry.getKey().replaceAll( "#", "T" );
			codonTable.put( key, entry.getValue() );
			
			//System.out.println( key+ entry.getValue() );
		}
		
	}

	public static void writeBasicRNAandDNA() {
		/*
			A - Start
		*/
		codonTable.put("AGG", "Arg");
		codonTable.put("AGA", "Arg");
		codonTable.put("AGC", "Ser");
		codonTable.put("AGU", "Ser");
		
		codonTable.put("AAG", "Lys");
		codonTable.put("AAA", "Lys");
		codonTable.put("AAC", "Asn");
		codonTable.put("AAU", "Asn");
		
		codonTable.put("ACG", "Thr");
		codonTable.put("ACA", "Thr");
		codonTable.put("ACC", "Thr");
		codonTable.put("ACU", "Thr");
		
		codonTable.put("AUG", "Met");
		codonTable.put("AUA", "Ile");
		codonTable.put("AUC", "Ile");
		codonTable.put("AUU", "Ile");
		
		/*
			C - Start
		*/
		codonTable.put("CGG", "Arg");
		codonTable.put("CGA", "Arg");
		codonTable.put("CGC", "Arg");
		codonTable.put("CGU", "Arg");
		
		codonTable.put("CAG", "Gln");
		codonTable.put("CAA", "Gln");
		codonTable.put("CAC", "His");
		codonTable.put("CAU", "His");
		
		codonTable.put("CCG", "Pro");
		codonTable.put("CCA", "Pro");
		codonTable.put("CCC", "Pro");
		codonTable.put("CCU", "Pro");
		
		codonTable.put("CUG", "Leu");
		codonTable.put("CUA", "Leu");
		codonTable.put("CUC", "Leu");
		codonTable.put("CUU", "Leu");
		
		/*
			U - Start
		*/
		codonTable.put("UGG", "Trp");
		codonTable.put("UGA", stopCode);
		codonTable.put("UGC", "Cys");
		codonTable.put("UGU", "Cys");
		
		codonTable.put("UAG", stopCode);
		codonTable.put("UAA", stopCode);
		codonTable.put("UAC", "Tyr");
		codonTable.put("UAU", "Tyr");
		
		codonTable.put("UCG", "Ser");
		codonTable.put("UCA", "Ser");
		codonTable.put("UCC", "Ser");
		codonTable.put("UCU", "Ser");
		
		codonTable.put("UUG", "Leu");
		codonTable.put("UUA", "Leu");
		codonTable.put("UUC", "Phe");
		codonTable.put("UUU", "Phe");	
		
		/*
			G - Start
		*/
		codonTable.put("GGG", "Gly");
		codonTable.put("GGA", "Gly");
		codonTable.put("GGC", "Gly");
		codonTable.put("GGU", "Gly");
		
		codonTable.put("GAG", "Glu");
		codonTable.put("GAA", "Glu");
		codonTable.put("GAC", "Asp");
		codonTable.put("GAU", "Asp");
		
		codonTable.put("GCG", "Ala");
		codonTable.put("GCA", "Ala");
		codonTable.put("GCC", "Ala");
		codonTable.put("GCU", "Ala");
		
		codonTable.put("GUG", "Val");
		codonTable.put("GUA", "Val");
		codonTable.put("GUC", "Val");
		codonTable.put("GUU", "Val");	
		
		/*
		for (var entry : codonTable.entrySet()) {
			String key = entry.getKey().replaceAll( "U", "T" );
			DNAcodonTable.put( key, entry.getValue() );
		}
		*/
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
			codon = codonTable.get( rnaParts.get(i) );
			if ( i != 0 ) {
				prevCodon = codonTable.get( rnaParts.get(i-1) );
			}
			else {
				prevCodon = null;
			}
			
			if ( i != rnaParts.size() - 1) {
				nextCodon = codonTable.get( rnaParts.get(i+1) );
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

			/*
			if ( ( i != 0 && prevCodon == stopCode && codon != "Met" ) || ( i == 0 && codon != "Met") ) {
				System.out.println("Fehler im RNA-Code: Abschnitt " + partCounter + " beginnt nicht mit Methionine.");
			}
			
			if ( ( i != 0 && prevCodon != stopCode && codon == "Met" ) ) {
				System.out.println("Fehler im RNA-Code: Abschnitt " + (partCounter-1) + " endete nicht mit einem STOP-Code.");
			}

			if ( i == rnaParts.size() && codon != stopCode ) {
				System.out.println("Fehler im RNA-Code: Abschnitt " + (partCounter) + " endete nicht mit einem STOP-Code.");
			}
			*/
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
	
	
}
