package application;

import java.util.Map;
import java.util.HashMap;
import java.util.Vector;

public class biocodon {
	private static String nucleinAcid = "DNA"; // OR DNA
	private static final Map<String , String> codonTable = new HashMap<String , String>();
	private static final String stopCode = "*";
	public static String getStopCodeSign() {
		return stopCode;
	}
	@SuppressWarnings("serial")
	private static final Map<Integer, String> translationSelection = new HashMap<Integer, String>() {{
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
	private static final Map<String , String> stopCodonList = new HashMap<String , String>();
	private static final Map<String , String> initCodonList = new HashMap<String , String>();
	private static final Map<Integer, Object> overwriteNAbyOrganism = new HashMap<Integer, Object>();
	private static int selectedTable = 1;
	private static final Vector<String> Aminoacids = new Vector<String>() {{
		add("Ala");
		add("Asn");
		add("Cys");
		add("Gln");
		add("His");
		add("Leu");
		add("Met");
		add("Pro");
		add("Thr");
		add("Tyr");
		add("Arg");
		add("Asp");
		add("Glu");
		add("Gly");
		add("Ile");
		add("Lys");
		add("Phe");
		add("Ser");
		add("Trp");
		add("Val");
		add("Arg");
		add(getStopCodeSign());
	}};
	
	
	private static boolean isStopCodon( String triplet ) {
		if( stopCodonList.get(triplet) != null ) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isInitCodon( String triplet ) {
		if( initCodonList.get(triplet) != null ) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static String getNucleinAcid() {
		return nucleinAcid;
	}
	
	public static void setNucleinAcid( String acid ) {
		nucleinAcid = acid;
	}
	
	public static void clearStopCodons() {
		stopCodonList.clear();
	}
	
	public static void clearInitCodons() {
		initCodonList.clear();
	}
	
	public static void insertStopCodon( String triplet ) {
		stopCodonList.put(triplet, "*");
	}
	
	public static void insertInitCodon( String triplet ) {
		initCodonList.put(triplet, "INIT");
	}

	public static void settranslationTable( int tablenumber ) {
		selectedTable = tablenumber;
	}
	
	public static Map<Integer, String> getTranslationtables(){
		return translationSelection;
	}

	
	public static Map<String, String> getTriplettTable(){
		return codonTable; 
	}
	
	public static void initOrganismNA() {
		Map<String, String> overwrite = new HashMap<String , String> (){{
			put("AGA",getStopCodeSign());
			put("AGG",getStopCodeSign());
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
			put("#CA",getStopCodeSign());
			put("#AG","Leu");
		}};
		overwriteNAbyOrganism.put(22, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("##A",getStopCodeSign());
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
			put("#GA","Trp");
		}};
		overwriteNAbyOrganism.put(27, overwrite);
		
		overwrite = new HashMap<String , String> (){{
			put("#AA","Gln");
			put("#AG","Gln");
			put("#GA","Trp");
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
			put("#AA","Glu");
			put("#AG","Glu");
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
		if ( overwriteNAbyOrganism.get( selectedTable ) == null ) {
			return;
		}
		
		Map<String, String> overwriteData = (HashMap) overwriteNAbyOrganism.get( selectedTable );
		
		
		for ( var entry : overwriteData.entrySet() ) {
			String key = entry.getKey().replaceAll( "#", "T" );
			codonTable.put( key, entry.getValue() );
		}
	}
	
	public static void prepare( int selectedOrganism ) {
		settranslationTable(selectedOrganism);
		writeBasicRNAandDNA();
		initOrganismNA();
		overwriteOrganismNA();
	}

	public static void writeBasicRNAandDNA() {
		
		String baseS = "U";
		
		if ( getNucleinAcid() == "DNA" ) {
			baseS = "T";
		}
		/*
			A - Start
		*/
		codonTable.put("AGG", "Arg");
		codonTable.put("AGA", "Arg");
		codonTable.put("AGC", "Ser");
		codonTable.put("AG"+baseS, "Ser");
		
		codonTable.put("AAG", "Lys");
		codonTable.put("AAA", "Lys");
		codonTable.put("AAC", "Asn");
		codonTable.put("AA"+baseS, "Asn");
		
		codonTable.put("ACG", "Thr");
		codonTable.put("ACA", "Thr");
		codonTable.put("ACC", "Thr");
		codonTable.put("AC"+baseS, "Thr");
		
		codonTable.put("A"+baseS+"G", "Met");
		codonTable.put("A"+baseS+"A", "Ile");
		codonTable.put("A"+baseS+"C", "Ile");
		codonTable.put("A"+baseS+baseS, "Ile");
		
		/*
			C - Start
		*/
		codonTable.put("CGG", "Arg");
		codonTable.put("CGA", "Arg");
		codonTable.put("CGC", "Arg");
		codonTable.put("CG"+baseS, "Arg");
		
		codonTable.put("CAG", "Gln");
		codonTable.put("CAA", "Gln");
		codonTable.put("CAC", "His");
		codonTable.put("CA"+baseS, "His");
		
		codonTable.put("CCG", "Pro");
		codonTable.put("CCA", "Pro");
		codonTable.put("CCC", "Pro");
		codonTable.put("CC"+baseS, "Pro");
		
		codonTable.put("C"+baseS+"G", "Leu");
		codonTable.put("C"+baseS+"A", "Leu");
		codonTable.put("C"+baseS+"C", "Leu");
		codonTable.put("C"+baseS+baseS, "Leu");
		
		/*
			U - Start
		*/
		codonTable.put(baseS+"GG", "Trp");
		codonTable.put(baseS+"GA", getStopCodeSign());
		codonTable.put(baseS+"GC", "Cys");
		codonTable.put(baseS+"G"+baseS, "Cys");
		
		codonTable.put(baseS+"AG", getStopCodeSign());
		codonTable.put(baseS+"AA", getStopCodeSign());
		codonTable.put(baseS+"AC", "Tyr");
		codonTable.put(baseS+"A"+baseS, "Tyr");
		
		codonTable.put(baseS+"CG", "Ser");
		codonTable.put(baseS+"CA", "Ser");
		codonTable.put(baseS+"CC", "Ser");
		codonTable.put(baseS+"C"+baseS, "Ser");
		
		codonTable.put(baseS+baseS+"G", "Leu");
		codonTable.put(baseS+baseS+"A", "Leu");
		codonTable.put(baseS+baseS+"C", "Phe");
		codonTable.put(baseS+baseS+baseS+"", "Phe");	
		
		/*
			G - Start
		*/
		codonTable.put("GGG", "Gly");
		codonTable.put("GGA", "Gly");
		codonTable.put("GGC", "Gly");
		codonTable.put("GG"+baseS, "Gly");
		
		codonTable.put("GAG", "Glu");
		codonTable.put("GAA", "Glu");
		codonTable.put("GAC", "Asp");
		codonTable.put("GA"+baseS, "Asp");
		
		codonTable.put("GCG", "Ala");
		codonTable.put("GCA", "Ala");
		codonTable.put("GCC", "Ala");
		codonTable.put("GC"+baseS, "Ala");
		
		codonTable.put("G"+baseS+"G", "Val");
		codonTable.put("G"+baseS+"A", "Val");
		codonTable.put("G"+baseS+"C", "Val");
		codonTable.put("G"+baseS+baseS, "Val");	
		
	}
	
	public static String codonList( String arg ) {
		arg = arg.replaceAll("\n", "");
		arg = arg.replaceAll(",", "");
		arg = arg.replaceAll(";", "");
		arg = arg.replaceAll(" ", "");
		
		return arg;
	}
	
	public static Vector<String> getCodonTriplets( String codonString ){
		Vector<String> tripletVector = new Vector<String>();
		for ( int i=0; i<codonString.length(); i+=3 ) {
			tripletVector.add( codonString.substring( i, i+3 ) );
		}
		
		return tripletVector;
	}
	
	public static String nucleinToAmino( String[] args ) {
		int partCounter = 0;
		String codon = null;
		String prevCodon = null;
		String nextCodon = null;
		String decodedNucleinAcid = "";
		String codonString = codonList( args[0] );
		Vector<String> rnaParts = getCodonTriplets( codonString );
		
		if ( codonString.length() == 0 ) {
			System.out.println("Der angegebene Nucleinsäure Code ist fehlerhaft.");
			return decodedNucleinAcid;
		}
		
		if( codonString.length() % 3 != 0 ) {
			System.out.println("Der angegebene Nucleinsäure Code ist fehlerhaft.");
			return decodedNucleinAcid;
		}
		
		for ( int i=0; i<codonString.length(); i+=3 ) {
			rnaParts.add( codonString.substring( i, i+3 ) );
		}
		
		for ( int i=0; i<rnaParts.size(); i++ ) {
			String triplet = rnaParts.get(i);
			String nextTriplet = "";
			String prevTriplet = "";
			
			if( i+1 < rnaParts.size() ) {
				nextTriplet = rnaParts.get(i+1);
			}
			if( i-1 >= 0 ) {
				prevTriplet = rnaParts.get(i-1);
			}
			
			codon = codonTable.get( triplet );
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
				System.out.println("Der folgende Nucleinsäure-Part existiert nicht: " + rnaParts.get(i) );
				continue;
			}

			if (  isInitCodon( triplet ) && i != 0 ) {
				++partCounter;
				decodedNucleinAcid = decodedNucleinAcid + codon;
			}
			else if ( isStopCodon( triplet ) ) {
				++partCounter;	
				decodedNucleinAcid = decodedNucleinAcid + " " + getStopCodeSign() + " \n";
				/*
				decodedNucleinAcid = decodedNucleinAcid + "\n";
				decodedNucleinAcid = decodedNucleinAcid + "> ";
				*/
			}
			else if ( nextTriplet != null && isInitCodon( nextTriplet ) && !isStopCodon(nextTriplet) ) {
				decodedNucleinAcid = decodedNucleinAcid + codon + " (" + getStopCodeSign() + ") \n";
			}
			else {
				decodedNucleinAcid = decodedNucleinAcid + codon;
			}
			

			if ( i < rnaParts.size()-1 && nextTriplet != null && !isInitCodon( nextTriplet ) && nextTriplet != null && !isStopCodon(nextTriplet) ) {
				decodedNucleinAcid = decodedNucleinAcid + " ";
			}
		}
		
		return decodedNucleinAcid;
	}
	
	public static String decode( String[] args ) {
		return nucleinToAmino(args);
	}
	
	public static float getAmountOf( Character ofWhat, String nucleinString ) {
		float c = 0;
		float p = 0;
		for (int i=0; i<nucleinString.length(); i++) {
			Character compValue = (Character) nucleinString.charAt(i);
			if ( compValue.equals(ofWhat) ) {
				c++;
			}
		}
		p = c/nucleinString.length() * 100;
	
		
		return p;
	}
	
	public static float getAmountOfAbs( Character ofWhat, String nucleinString ) {
		float c = 0;
		for (int i=0; i<nucleinString.length(); i++) {
			Character compValue = (Character) nucleinString.charAt(i);
			if ( compValue.equals(ofWhat) ) {
				c++;
			}
		}
		
		return c;
	}
	
	public static String getAmountOfAminoacids( String aminoacidString ) {
		String res = "";
		
		return res;
	}
	
	public static int getSequenceLength( String nucleinString ) {
		String codonString = codonList( nucleinString );
		Vector<String> tripletVector = getCodonTriplets( codonString );
		
		int c = 0;
		
		for( int i=0; i<tripletVector.size(); i++ ) {
			if ( isStopCodon( tripletVector.get(i) ) ) {
				++c;
			}
		}
		
		//System.out.println( c );
		//System.out.println( codonString.length());
		
		return (codonString.length() - c*3);
	}
	
	public static int getL( String nucleinString ) {
		return ( getSequenceLength( nucleinString ) / 3 );
	}
	
	public static Map<String, Float> getCodonFrequency( Vector<String> triplets ){
		Map<String, Float> Xi = new HashMap<String, Float>();
		
		// Calculate the frequency of the codon
		for ( int i=0; i<triplets.size(); i++ ) {
			String codon = triplets.get(i);

			if ( Xi.get( codon ) == null ) {
				Xi.put( codon, (float) 1 );
			}
			else {
				float value = Xi.get( codon );
				++value;
				Xi.put( codon, value );
			}
		}
		
		return Xi;
	}
	
	public static Map<String, String> getXimaxCodon( Map<String, Float> XiValues ){
		Map<String, String> Ximax = new HashMap<String, String>();
		
		for ( String aminoacid : Aminoacids ) {
			String highestCodon = "";
			float highestValue = -1;
			for ( var entry : XiValues.entrySet() ) {
				String codon = entry.getKey();
				float value = entry.getValue();
						
				if ( highestValue == -1 ) {
					highestCodon = codon;
					highestValue = value;
				}
				else {
					if ( highestValue < value ) {
						highestCodon = codon;
						highestValue = value;
					}
				}
				
				Ximax.put( aminoacid, highestCodon );
			}
		}
		
		return Ximax;
	}
	
	public static Map<String, Float> getAmountSamecodingCodons( ){
		Map<String, Float> SameCodingAmounts = new HashMap<String, Float>();
		
		for( var entry : getTriplettTable().entrySet() ) {
			if ( SameCodingAmounts.get( entry.getValue() ) == null ) {
				SameCodingAmounts.put( entry.getValue(), (float) 1 );
			}
			else {
				float value = SameCodingAmounts.get( entry.getValue() );
				++value;
				SameCodingAmounts.put( entry.getValue(), value );
			}
		}
		
		return SameCodingAmounts;
	}
	

	public static float getCodonAdaptationIndex( String inputString ) {
		String codonString = codonList( inputString );
		Vector<String> tripletVector = getCodonTriplets( codonString );
		
		float CAI = 1;
		float L = (float) getL( codonString );
		
		Map<String, Float> RSCU_ij = new HashMap<String, Float>();
		Map<String, Float> Xi = getCodonFrequency( tripletVector );
		Map<String, String> Ximax = getXimaxCodon( Xi );
		Map<String, Float> AminoacidsSameCodons = getAmountSamecodingCodons( );
		Vector<Float> wi = new Vector<Float>();
		
		for ( var entry : Xi.entrySet() ) {
			String codon = entry.getKey();
			String codedAminoacid = codonTable.get(entry.getKey());
			float n_i = AminoacidsSameCodons.get(codedAminoacid);
			
			float rscu_ij = 0;
			
			for ( var codonData : getTriplettTable().entrySet() ) {
				if ( !codonData.getValue().equalsIgnoreCase(codedAminoacid) ) {
					continue;
				}
				if ( Xi.get( codonData.getKey() ) != null ) {
					rscu_ij = rscu_ij + Xi.get( codonData.getKey() );
				}
			}
			
			rscu_ij = 1/n_i * rscu_ij;
			
			RSCU_ij.put( entry.getKey(), rscu_ij );
		}
		//System.out.println( RSCU_ij );
		
		for ( var entry : RSCU_ij.entrySet() ) {
			float value = 0;
			float rscu_ij = entry.getValue();
			String codon = entry.getKey();
			
			String rscu_imax_amino = getTriplettTable().get( codon );
			String rscu_imax_codon = Ximax.get( rscu_imax_amino );
			float rscu_imax_value = RSCU_ij.get( rscu_imax_codon );
			
			/*
			System.out.println( codon );
			System.out.println( rscu_imax_codon );
			System.out.println( rscu_ij );
			System.out.println( rscu_imax_value );
			*/
			
			value = rscu_ij/rscu_imax_value;
			wi.add( value );
		}
		
		//System.out.println("wi Table");
		//System.out.println(wi);
		
		
		for ( int i=0; i<wi.size(); i++ ) {
			CAI = CAI + (float) Math.log( wi.get(i) );
		}
		CAI = 1/L * CAI;
		CAI = (float) Math.exp( CAI );
		
		return CAI;
	}
	
	public static float getRelativeCodonBiasStrength( String inputString ) {
		String codonString = codonList( inputString );
		Vector<Float> RCB_xyz = new Vector<Float>();
		Vector<String> codonVector = getCodonTriplets( codonString );
		Map<Character, Float> baseFrequency = new HashMap<Character, Float>();
		float RCBS = 1;
		float L = (float) getL( codonString );
		
		float f_t = biocodon.getAmountOfAbs( 'T', codonString );
		float f_a = biocodon.getAmountOfAbs( 'A', codonString );
		float f_u = biocodon.getAmountOfAbs( 'U', codonString );
		float f_g = biocodon.getAmountOfAbs( 'G', codonString );
		float f_c = biocodon.getAmountOfAbs( 'C', codonString );
		
		baseFrequency.put('T', f_t);
		baseFrequency.put('A', f_a);
		baseFrequency.put('U', f_u);
		baseFrequency.put('G', f_g);
		baseFrequency.put('C', f_c);
		
		Map<String, Float> Xi = getCodonFrequency( codonVector );
		
		for ( int i=0; i<L; i++ ) {
			String codon = codonVector.get(i);
			/*if ( RCB_xyz.get(codon) != null ) {
				continue;
			}*/
			
			Character x = codon.charAt(0);
			Character y = codon.charAt(1);
			Character z = codon.charAt(2);
			
			float val_x = baseFrequency.get(x);
			float val_y = baseFrequency.get(y);
			float val_z = baseFrequency.get(z);
			
			String xyz = "";
			xyz = xyz +  x.toString();
			xyz = xyz +  y.toString();
			xyz = xyz +  z.toString();
			float val_xyz = Xi.get( xyz );
			
			System.out.println( xyz );
			System.out.println( val_xyz );
			
			float d_xyz = ( val_xyz - val_x * val_y * val_z ) /  (val_x * val_y * val_z);
			RCB_xyz.add(d_xyz);
		}
		
		for ( int i=0; i<L; i++ ) {
			RCBS = 1 + RCBS * RCB_xyz.get(i);
		}
		RCBS = (float) Math.pow( RCBS, 1/L ) - 1;
		
		return RCBS;
	}
	
}
