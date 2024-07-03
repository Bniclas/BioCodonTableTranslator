package application;

import java.util.Map;


import java.util.HashMap;
import java.util.Vector;

public class biocodonencoder {
	static final Map<String , String> RNAcodonTable = new HashMap<String , String>();
	static final Map<String , String> DNAcodonTable = new HashMap<String , String>();
	
	static final Map<String, String> CodonTableToUse = new HashMap<String , String>();
	
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
	
	static final Map<Integer, Object> overwriteNAbyOrganism = new HashMap<Integer, Object>();
	
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
