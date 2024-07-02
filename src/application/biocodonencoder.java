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
		
	}};
	
	static final Map<String , String> DNAcodonTableInverse = new HashMap<String , String>() {{
		
	}};

	
	@SuppressWarnings("unchecked")
	public static void encode( String[] args, boolean isRNA, boolean inverse ) {
		HashMap<String, String> mapToUse = new HashMap<>();
		if ( isRNA == true ) {
			if ( inverse == false ) {
				mapToUse.putAll( RNAcodonTable );  
			}
			else {
				mapToUse.putAll( RNAcodonTableInverse );  
			}
		}
		else {
			if ( inverse == false ) {
				mapToUse.putAll( DNAcodonTable );  
			}
			else {
				mapToUse.putAll( DNAcodonTableInverse );  
			}
		}

		int partCounter = 1;
		Vector<String> rnaParts = new Vector<String>();
		String codonGet = "";
		String encodedRNA = "";
		encodedRNA = encodedRNA + "[" + partCounter + "]> ";
		
		if ( args.length == 0 ) {
			System.out.println("Der angegebene RNA Code ist fehlerhaft.");
			return;
		}
		else if( args[0].length() % 3 != 0 ) {
			System.out.println("Der angegebene RNA Code ist fehlerhaft.");
			return;
		}
		
		for ( int i=0; i<args[0].length(); i+=3 ) {
			rnaParts.add( args[0].substring( i, i+3 ) );
		}
		
		for ( int i=0; i<rnaParts.size(); i++ ) {
			codonGet = mapToUse.get( rnaParts.get(i) );
			if ( codonGet == null ) {
				System.out.println("Der folgende RNA-Part existiert nicht: " + rnaParts.get(i) );
				continue;
			}
			if ( codonGet == "Met" ) {
				++partCounter;
				encodedRNA = encodedRNA + "\n";
				encodedRNA = encodedRNA + "[" + partCounter + "]> ";
			}
			else if ( codonGet == stopCode ) {
				++partCounter;
				encodedRNA = encodedRNA + "\n";
				encodedRNA = encodedRNA + "[" + partCounter + "]> ";
			}
			
			encodedRNA = encodedRNA + codonGet;
			
			if ( i != rnaParts.size()-1 && codonGet != stopCode ) {
				encodedRNA = encodedRNA + "-";
			}

			
			if ( ( i != 0 && mapToUse.get( rnaParts.get(i-1) ) == stopCode && codonGet != "Met" ) || ( i == 0 && codonGet != "Met") ) {
				System.out.println("Fehler im RNA-Code: Abschnitt " + partCounter + " beginnt nicht mit Methionine.");
			}
			
			if ( ( i != 0 && mapToUse.get( rnaParts.get(i-1) ) != stopCode && codonGet == "Met" ) ) {
				System.out.println("Fehler im RNA-Code: Abschnitt " + (partCounter-1) + " endete nicht mit einem STOP-Code.");
			}

			if ( i == rnaParts.size() && codonGet != stopCode ) {
				System.out.println("Fehler im RNA-Code: Abschnitt " + (partCounter) + " endete nicht mit einem STOP-Code.");
			}
		}
		
		System.out.println( encodedRNA );
	}
	
	public static void encodeRNA( String[] args ) {
		System.out.println(">>>>>>>>>> RNA Analyse wird gestartet:\n\n");
		encode(args, true, false);
	}
	
	public static void encodeRNAInverse( String[] args ) {
		System.out.println(">>>>>>>>>> RNA Inverse Analyse wird gestartet:\n\n");
		encode(args, true, true);
	}
	
	public static void encodeDNA( String[] args ) {
		System.out.println(">>>>>>>>>> DNA Analyse wird gestartet:\n\n");
		encode(args, false, false);
	}
	
	public static void encodeDNAInverse( String[] args ) {
		System.out.println(">>>>>>>>>> DNA Inverse Analyse wird gestartet:\n\n");
		encode(args, false, true);
	}
}
