package application;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;



public class ReferenceDataSets {
	
	private static Map<String, Double> CodonFrequencyReference = new HashMap<String, Double>();
	private static Map<String, String> CodonToAminoacid = new HashMap<String, String>();
	private static Map<String, String> AminoacidToCodon = new HashMap<String, String>();
	private static Map<String, String> AminoacidMostCodedByCodon = new HashMap<String, String>();
	
	
	public static void addCodonFrequency( String codon, double frequency ) {
		CodonFrequencyReference.put( codon, frequency );
	}
	
	public static void addCodonAminoacid( String codon, String aminoacid ) {
		CodonToAminoacid.put( codon, aminoacid );
	}
	
	public static void addAminoacidCodon( String aminoacid, String codon ) {
		AminoacidToCodon.put( aminoacid, codon );
	}
	
	public static void getReferenceDataSet() throws Exception {
		
		FileInputStream fileInput = new FileInputStream( "ressources\\\\referencedatasets\\\\e_coli_reference.csv" );
		Scanner fileReader = new Scanner( fileInput );
		
		int mode = 0;
		
		String codon = "";
		String aminoacid = "";
		double fraction = 0;
		double frequency = 0; 
		int number = 0;
		
		while( fileReader.hasNext() ) {
			String read = fileReader.next();
			
			if ( read == "\n" || read== "\0" ) {
				break;
			}
			String [] data = read.split(";");
			
			codon = data[0];
			aminoacid = biocodon.ShortToAmino( data[1] );
			fraction = Double.parseDouble(data[2]);
			frequency = Double.parseDouble(data[3]);
			number = Integer.parseInt(data[4]);
			
			addCodonFrequency( codon, frequency );
			addCodonAminoacid( codon, aminoacid );
			addAminoacidCodon( aminoacid, codon );
		}
		
		fileReader.close();
		
		for ( var v1 : AminoacidToCodon.entrySet() ) {
			String aminoA = v1.getKey();
			String codonA = v1.getValue();
			double value = -1;
			String optimalCodon = "";
			
			for ( var v2 : CodonFrequencyReference.entrySet() ) {
				String codonB = v2.getKey();
				double f = v2.getValue();
				
				if ( !CodonToAminoacid.get(codonA).equalsIgnoreCase( aminoA ) ) {
					continue;
				}
				
				if ( value == -1 ) {
					value = f;
				}
				else if ( value < f ) {
					optimalCodon = codonB;
				}
			}
			AminoacidMostCodedByCodon.put( aminoA, codon );
		}
		
	}
	
	public static double getCodonWeight( String codon ) {
		double weight;
		
		double fi = CodonFrequencyReference.get( codon );
		String aminocoded = CodonToAminoacid.get(codon);
		String optimalCodon = AminoacidMostCodedByCodon.get( aminocoded );
		double fj = CodonFrequencyReference.get( optimalCodon );
		
		weight = fi / fj;
		
		return weight;
	}
	
	public static double writeCAI( String inputString ) {
		String codonString = biocodon.codonList( inputString );
		Vector<String> codonVector = biocodon.getCodonTriplets( codonString );
		double CAI = 0;
		double L = codonVector.size();
		double OneDIVbyL = 1.0 / L;
		
		for ( int i=0; i<codonVector.size(); i++ ) {
			double wi = getCodonWeight( codonVector.get(i) );
			CAI = CAI + Math.log( wi );
		}
		

		System.out.println( OneDIVbyL );
		System.out.println( CAI );
		CAI = CAI * OneDIVbyL;
		System.out.println( CAI );
		CAI = (double) Math.exp( CAI ) - 1;
		
		System.out.println( CAI );
		
		return CAI;
	}
	
}
