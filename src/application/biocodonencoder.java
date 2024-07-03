package application;

import java.util.Map;
import java.util.HashMap;
import java.util.Vector;

public class biocodonencoder {
	
	
	public static final String stopCode = "STOP";
	@SuppressWarnings("serial")
	static final Map<String , String> RNAcodonTable = new HashMap<String , String>() {{
		/*
			A - Start
		*/
		put("AGG", "Arg");
		put("AGA", "Arg");
		put("AGC", "Ser");
		put("AGU", "Ser");
		
		put("AAG", "Lys");
		put("AAA", "Lys");
		put("AAC", "Asn");
		put("AAU", "Asn");
		
		put("ACG", "Thr");
		put("ACA", "Thr");
		put("ACC", "Thr");
		put("ACU", "Thr");
		
		put("AUG", "Met");
		put("AUA", "Ile");
		put("AUC", "Ile");
		put("AUU", "Ile");
		
		/*
			C - Start
		*/
		put("CGG", "Arg");
		put("CGA", "Arg");
		put("CGC", "Arg");
		put("CGU", "Arg");
		
		put("CAG", "Gln");
		put("CAA", "Gln");
		put("CAC", "His");
		put("CAU", "His");
		
		put("CCG", "Pro");
		put("CCA", "Pro");
		put("CCC", "Pro");
		put("CCU", "Pro");
		
		put("CUG", "Leu");
		put("CUA", "Leu");
		put("CUC", "Leu");
		put("CUU", "Leu");
		
		/*
			U - Start
		*/
		put("UGG", "Trp");
		put("UGA", stopCode);
		put("UGC", "Cys");
		put("UGU", "Cys");
		
		put("UAG", stopCode);
		put("UAA", stopCode);
		put("UAC", "Tyr");
		put("UAU", "Tyr");
		
		put("UCG", "Ser");
		put("UCA", "Ser");
		put("UCC", "Ser");
		put("UCU", "Ser");
		
		put("UUG", "Leu");
		put("UUA", "Leu");
		put("UUC", "Phe");
		put("UUU", "Phe");	
		
		/*
			G - Start
		*/
		put("GGG", "Gly");
		put("GGA", "Gly");
		put("GGC", "Gly");
		put("GGU", "Gly");
		
		put("GAG", "Glu");
		put("GAA", "Glu");
		put("GAC", "Asp");
		put("GAU", "Asp");
		
		put("GCG", "Ala");
		put("GCA", "Ala");
		put("GCC", "Ala");
		put("GCU", "Ala");
		
		put("GUG", "Val");
		put("GUA", "Val");
		put("GUC", "Val");
		put("GUU", "Val");	
	}};
	
	static final Map<String , String> RNAcodonTableInverse = new HashMap<String , String>() {{
		
	}};
	
	static final Map<String , String> DNAcodonTable = new HashMap<String , String>() {{
		/*
			A - Start
		*/
		put("AGG", "Arg");
		put("AGA", "Arg");
		put("AGC", "Ser");
		put("AGT", "Ser");
		
		put("AAG", "Lys");
		put("AAA", "Lys");
		put("AAC", "Asn");
		put("AAT", "Asn");
		
		put("ACG", "Thr");
		put("ACA", "Thr");
		put("ACC", "Thr");
		put("ACT", "Thr");
		
		put("ATG", "Met");
		put("ATA", "Ile");
		put("ATC", "Ile");
		put("ATT", "Ile");
		
		/*
			C - Start
		*/
		put("CGG", "Arg");
		put("CGA", "Arg");
		put("CGC", "Arg");
		put("CGT", "Arg");
		
		put("CAG", "Gln");
		put("CAA", "Gln");
		put("CAC", "His");
		put("CAT", "His");
		
		put("CCG", "Pro");
		put("CCA", "Pro");
		put("CCC", "Pro");
		put("CCT", "Pro");
		
		put("CTG", "Leu");
		put("CTA", "Leu");
		put("CTC", "Leu");
		put("CTT", "Leu");
		
		/*
			T - Start
		*/
		put("TGG", "Trp");
		put("TGA", stopCode);
		put("TGC", "Cys");
		put("TGT", "Cys");
		
		put("TAG", stopCode);
		put("TAA", stopCode);
		put("TAC", "Tyr");
		put("TAT", "Tyr");
		
		put("TCG", "Ser");
		put("TCA", "Ser");
		put("TCC", "Ser");
		put("TCT", "Ser");
		
		put("TTG", "Leu");
		put("TTA", "Leu");
		put("TTC", "Phe");
		put("TTT", "Phe");	
		
		/*
			G - Start
		*/
		put("GGG", "Gly");
		put("GGA", "Gly");
		put("GGC", "Gly");
		put("GGT", "Gly");
		
		put("GAG", "Glu");
		put("GAA", "Glu");
		put("GAC", "Asp");
		put("GAT", "Asp");
		
		put("GCG", "Ala");
		put("GCA", "Ala");
		put("GCC", "Ala");
		put("GCT", "Ala");
		
		put("GTG", "Val");
		put("GTA", "Val");
		put("GTC", "Val");
		put("GTT", "Val");	
	}};
	
	static final Map<String , String> DNAcodonTableInverse = new HashMap<String , String>() {{
		
	}};
	
	static final Map<String , String> stopCodonTranslation = new HashMap<String , String>() {{
		put("TAG", "Amber");
		put("TAA", "Ochre");
		put("TGA", "Opal");
		
		put("UAG", "Amber");
		put("UAA", "Ochre");
		put("UGA", "Opal");
	}};

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
	
}
