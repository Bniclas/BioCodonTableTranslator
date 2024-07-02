package application;

import java.util.HashMap;
import java.util.Vector;

public class biocodonencoder {
	
	public static final HashMap<String, String> codonTable = new HashMap<String, String>();
	
	public static void reset() {
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
		codonTable.put("UGA", "STOP");
		codonTable.put("UGC", "Cys");
		codonTable.put("UGU", "Cys");
		
		codonTable.put("UAG", "STOP");
		codonTable.put("UAA", "STOP");
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
	}
	
	public static void encodeRNA( String[] args ) {
		int partCounter = 1;
		Vector<String> rnaParts = new Vector<String>();
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
			if ( codonTable.get( rnaParts.get(i) ) == null ) {
				System.out.println("Der folgende RNA-Part existiert nicht: " + rnaParts.get(i) );
				continue;
			}
			encodedRNA = encodedRNA + codonTable.get( rnaParts.get(i) );
			
			if ( codonTable.get( rnaParts.get(i) ) == "STOP" ) {
				partCounter++;
				encodedRNA = encodedRNA + "\n";
				encodedRNA = encodedRNA + "[" + partCounter + "]> ";
				continue;
			}
			
			if ( i != rnaParts.size()-1 ) {
				encodedRNA = encodedRNA + "-";
			}
		}
		
		
		System.out.println( encodedRNA );
	}
}
