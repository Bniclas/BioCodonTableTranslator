package application;

import java.util.Map;
import java.util.HashMap;
import java.util.Vector;

public class biocodonencoder {
	
	
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
		put("UGA", "STOP");
		put("UGC", "Cys");
		put("UGU", "Cys");
		
		put("UAG", "STOP");
		put("UAA", "STOP");
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
	

	
	public static void encodeRNA( String[] args ) {
		int partCounter = 0;
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
			codonGet = RNAcodonTable.get( rnaParts.get(i) );
			if ( codonGet == null ) {
				System.out.println("Der folgende RNA-Part existiert nicht: " + rnaParts.get(i) );
				continue;
			}
			if ( codonGet == "Met" ) {
				++partCounter;
				encodedRNA = encodedRNA + "\n";
				encodedRNA = encodedRNA + "[" + partCounter + "]> ";
				encodedRNA = encodedRNA + codonGet + "-";
				continue;
			}
			
			encodedRNA = encodedRNA + codonGet;
			if ( i != rnaParts.size()-1 ) {
				encodedRNA = encodedRNA + "-";
			}

			
			if ( codonGet == "STOP" ) {
				++partCounter;
				encodedRNA = encodedRNA + "\n";
				encodedRNA = encodedRNA + "[" + partCounter + "]> ";
				continue;
			}
			
			
			if ( ( i != 0 && RNAcodonTable.get( rnaParts.get(i-1) ) == "STOP" && codonGet != "Met" ) || ( i == 0 && codonGet != "Met") ) {
				System.out.println("Fehler im RNA-Code: Abschnitt " + partCounter + " beginnt nicht mit Methionine.");
			}
			
			if ( ( i != 0 && RNAcodonTable.get( rnaParts.get(i-1) ) != "STOP" && codonGet == "Met" ) ) {
				System.out.println("Fehler im RNA-Code: Abschnitt " + (partCounter) + " endete nicht mit einem STOP-Code.");
			}

	
		}
		
		System.out.println( encodedRNA );
		
	}
	

}
