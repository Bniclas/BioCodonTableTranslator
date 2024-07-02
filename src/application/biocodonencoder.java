package application;

import java.util.HashMap;
import java.util.Vector;

public class biocodonencoder {
	
	public static final HashMap<String, String> RNAcodonTable = new HashMap<String, String>();
	public static final HashMap<String, String> inverseRNAcodonTable = new HashMap<String, String>();
	
	public static void reset() {
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
		RNAcodonTable.put("UGA", "STOP");
		RNAcodonTable.put("UGC", "Cys");
		RNAcodonTable.put("UGU", "Cys");
		
		RNAcodonTable.put("UAG", "STOP");
		RNAcodonTable.put("UAA", "STOP");
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
	}
	
	public static void encodeRNA( String[] args ) {
		int partCounter = 1;
		Vector<String> rnaParts = new Vector<String>();
		String codonGet = "";
		String encodedRNA = "";
		encodedRNA = encodedRNA + "[" + partCounter + "]> ";
		
		if ( args[0] == null || args[0].length() % 3 != 0 ) {
			System.out.println("Der angegebene RNA Code ist fehlerhaft.");
			return;
		}
		
		for ( int i=0; i<args[0].length(); i+=3 ) {
			rnaParts.add( args[0].substring( i, i+3 ) );
		}
		
		for ( int i=0; i<rnaParts.size(); i++ ) {
			codonGet = RNAcodonTable.get( rnaParts.get(i) );
			if ( codonGet == null ) {
				System.out.println("Der folgende RNA-Part existiert nicht: " + rnaParts.get(i) );
				continue;
			}
			encodedRNA = encodedRNA + codonGet;
			
			if ( codonGet == "STOP" ) {
				partCounter++;
				encodedRNA = encodedRNA + "\n";
				encodedRNA = encodedRNA + "[" + partCounter + "]> ";
				continue;
			}
			
			if ( ( i != 0 && RNAcodonTable.get( rnaParts.get(i-1) ) == "STOP" && codonGet != "Met" ) || ( i == 0 && codonGet != "Met") ) {
				System.out.println("Fehler im RNA-Code: Abschnitt " + partCounter + " beginnt nicht mit Methionine.");
			}

			if ( i != rnaParts.size()-1 ) {
				encodedRNA = encodedRNA + "-";
			}
		}
		
		
		System.out.println( encodedRNA );
	}
	
	public static void encodeInverseRNA( String[] args ) {
		int partCounter = 1;
		Vector<String> rnaParts = new Vector<String>();
		String codonGet = "";
		String encodedRNA = "";
		encodedRNA = encodedRNA + "[" + partCounter + "]> ";
		
		if ( args[0] == null || args[0].length() % 3 != 0 ) {
			System.out.println("Der angegebene RNA Code ist fehlerhaft.");
			return;
		}
		
		for ( int i=0; i<args[0].length(); i+=3 ) {
			rnaParts.add( args[0].substring( i, i+3 ) );
		}
		
		for ( int i=0; i<rnaParts.size(); i++ ) {
			codonGet = inverseRNAcodonTable.get( rnaParts.get(i) );
			if ( codonGet == null ) {
				System.out.println("Der folgende RNA-Part existiert nicht: " + rnaParts.get(i) );
				continue;
			}
			encodedRNA = encodedRNA + codonGet;
			
			if ( codonGet == "STOP" ) {
				partCounter++;
				encodedRNA = encodedRNA + "\n";
				encodedRNA = encodedRNA + "[" + partCounter + "]> ";
				continue;
			}
			
			if ( ( i != 0 && inverseRNAcodonTable.get( rnaParts.get(i-1) ) == "STOP" && codonGet != "Met" ) || ( i == 0 && codonGet != "Met") ) {
				System.out.println("Fehler im RNA-Code: Abschnitt " + partCounter + " beginnt nicht mit Methionine.");
			}

			if ( i != rnaParts.size()-1 ) {
				encodedRNA = encodedRNA + "-";
			}
		}
		
		
		System.out.println( encodedRNA );
	}
}
