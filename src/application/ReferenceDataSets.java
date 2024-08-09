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
	private static Map<String, Boolean> OptimalCodons = new HashMap<String, Boolean>();
	
	
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
			double frequencyA = CodonFrequencyReference.get( codonA );
			
			for ( var v2 : CodonFrequencyReference.entrySet() ) {
				String codonB = v2.getKey();
				String aminoB = CodonToAminoacid.get(codonB);
				double frequencyB = v2.getValue();
				
				if( aminoB.equalsIgnoreCase( aminoA ) ) {
					if ( frequencyA > frequencyB ) {
						if ( AminoacidMostCodedByCodon.get(aminoB) == null ) {
							AminoacidMostCodedByCodon.put(aminoB, codonA);
							continue;
						}
						if ( CodonFrequencyReference.get( AminoacidMostCodedByCodon.get(aminoB) ) < frequencyA ) {
							AminoacidMostCodedByCodon.put(aminoA, codonA);
						}
					}
					else {
						if ( AminoacidMostCodedByCodon.get(aminoB) == null ) {
							AminoacidMostCodedByCodon.put(aminoB, codonB);
							continue;
						}
					}
				}
			}
		}
		
		System.out.println( AminoacidToCodon );
		System.out.println( AminoacidMostCodedByCodon );
		
	}
	
	public static double getCodonRSCU( String codon ) {
		double rscu;
		double denom = 0;
		double degeneracy = 0;
		String codedAminoacid = CodonToAminoacid.get(codon);
		double frequency = CodonFrequencyReference.get( codon );
		
		for ( var entry : CodonToAminoacid.entrySet() ) {
			String compCodon = entry.getKey();
			String compAmino = CodonToAminoacid.get(compCodon);
			
			if ( !compAmino.equalsIgnoreCase(codedAminoacid) ) {
				continue;
			}
			double compFrequency = CodonFrequencyReference.get( compCodon );
			++degeneracy;
			denom = denom + compFrequency;
		}
		
		denom = 1.0 / degeneracy * denom;
		rscu = frequency / denom;
		
		//System.out.println( rscu );
		
		return rscu;
	}
	
	public static double getGeneRSCU( String inputString ) {
		double rscu_gene;
		String codonString = biocodon.codonList( inputString );
		Vector<String> codonVector = biocodon.getCodonTriplets( codonString );
		Map<String, Boolean> codonCache = new HashMap<String, Boolean>();
		double L = codonVector.size();
		double rscu_ij_sum = 0;
		
		for ( int i=0; i<L; i++ ) {
			rscu_ij_sum = rscu_ij_sum + getCodonRSCU( codonVector.get(i) );
			codonCache.put( codonVector.get(i), true );
		}
		
		for ( var entry : CodonFrequencyReference.entrySet() ) {
			String codon = entry.getKey();
			if ( codonCache.get(codon) == null ) {
				rscu_ij_sum = rscu_ij_sum + 0.0001;
			}
		}
		
		rscu_gene = rscu_ij_sum / L;
	
		return rscu_gene;
	}
	
	public static double getCodonWeight( String codon ) {
		double weight;
		
		double fi = CodonFrequencyReference.get( codon );
		String aminocoded = CodonToAminoacid.get(codon);
		String optimalCodon = AminoacidMostCodedByCodon.get( aminocoded );
		double fj = CodonFrequencyReference.get( optimalCodon );
		
		//System.out.println( "fi: " + fi + " | fj: " + fj );
		
		weight = fi / fj;
		
		return weight;
	}
	
	public static double writeCAI( String inputString ) {
		String codonString = biocodon.codonList( inputString );
		Vector<String> codonVector = biocodon.getCodonTriplets( codonString );
		double CAI = -1;
		double L = codonVector.size();
		double OneDIVbyL = 1.0 / L;
		
		for ( int i=0; i<L; i++ ) {
			double wi = (double) Math.pow( getCodonWeight( codonVector.get(i) ), OneDIVbyL );
			System.out.println( getCodonWeight( codonVector.get(i) ) );
			
			if( CAI == -1) {
				CAI = wi;
			}
			else {
				CAI = CAI * wi;
			}
		}
		
		
		return CAI;
	}
	
	public static double getFOP( String inputString ) {
		double FOP;
		String codonString = biocodon.codonList( inputString );
		Vector<String> codonVector = biocodon.getCodonTripletsWithoutStop( codonString );
		double totalCodonsInSequence = codonVector.size();
		double optimalCodonsInSequence = 0;
		
		for ( int i=0; i<totalCodonsInSequence; i++ ) {
			String codon = codonVector.get(i);
			if( biocodon.isInitCodon(codon) ) {
				continue;
			}
			if ( OptimalCodons.get(codon) != null ) {
				optimalCodonsInSequence = optimalCodonsInSequence + 1;
			}
		}
		
		FOP = optimalCodonsInSequence / totalCodonsInSequence;
		
		return FOP;
	}
	
}
