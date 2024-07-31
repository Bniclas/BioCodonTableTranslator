package application;

import java.util.Map;
import java.math.BigDecimal;
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
	public static final Vector<String> Aminoacids = new Vector<String>() {{
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
	
	private static final Map<String, String> AminoshortToAminoacid = new HashMap<String, String>() {{
		put( "*", getStopCodeSign() );
		put( "A", "Ala" );
		put( "C", "Cys" );
		put( "D", "Asp" );
		put( "E", "Glu" );
		put( "F", "Phe" );
		put( "G", "Gly" );
		put( "H", "His" );
		put( "I", "Ile" );
		put( "K", "Lys" );
		put( "L", "Leu" );
		put( "M", "Met" );
		put( "N", "Asn" );
		put( "P", "Pro" );
		put( "Q", "Gln" );
		put( "R", "Arg" );
		put( "S", "Ser" );
		put( "T", "Thr" );
		put( "V", "Val" );
		put( "W", "Trp" );
		put( "Y", "Tyr" );
	}};
	private static Map<String, Double> baseFrequency = new HashMap<String, Double>();
	
	public static double getBaseFrequencyAtPos( Character Base, int Position ) {
		return baseFrequency.get( Integer.toString(Position) + Base );
	}
	
	public static void initBaseFrequency( String inputString ) {
		String codonString = codonList( inputString );
		Vector<String> codonVector = getCodonTriplets( codonString );
		
		double f_a_1 = biocodon.getAmountOfAbsAtPos( 'A', codonVector, 0 );
		double f_a_2 = biocodon.getAmountOfAbsAtPos( 'A', codonVector, 1 );
		double f_a_3 = biocodon.getAmountOfAbsAtPos( 'A', codonVector, 2 );
		
		double f_g_1 = biocodon.getAmountOfAbsAtPos( 'G', codonVector, 0 );
		double f_g_2 = biocodon.getAmountOfAbsAtPos( 'G', codonVector, 1 );
		double f_g_3 = biocodon.getAmountOfAbsAtPos( 'G', codonVector, 2 );
		
		double f_c_1 = biocodon.getAmountOfAbsAtPos( 'C', codonVector, 0 );
		double f_c_2 = biocodon.getAmountOfAbsAtPos( 'C', codonVector, 1 );
		double f_c_3 = biocodon.getAmountOfAbsAtPos( 'C', codonVector, 2 );
		
		double f_u_1 = biocodon.getAmountOfAbsAtPos( 'U', codonVector, 0 );
		double f_u_2 = biocodon.getAmountOfAbsAtPos( 'U', codonVector, 1 );
		double f_u_3 = biocodon.getAmountOfAbsAtPos( 'U', codonVector, 2 );
		
		double f_t_1 = biocodon.getAmountOfAbsAtPos( 'T', codonVector, 0 );
		double f_t_2 = biocodon.getAmountOfAbsAtPos( 'T', codonVector, 1 );
		double f_t_3 = biocodon.getAmountOfAbsAtPos( 'T', codonVector, 2 );
		
		
		baseFrequency.put("1A", f_a_1);
		baseFrequency.put("2A", f_a_2);
		baseFrequency.put("3A", f_a_3);
		
		baseFrequency.put("1G", f_g_1);
		baseFrequency.put("2G", f_g_2);
		baseFrequency.put("3G", f_g_3);
		
		baseFrequency.put("1C", f_c_1);
		baseFrequency.put("2C", f_c_2);
		baseFrequency.put("3C", f_c_3);
		
		baseFrequency.put("1U", f_u_1);
		baseFrequency.put("2U", f_u_2);
		baseFrequency.put("3U", f_u_3);
		
		baseFrequency.put("1T", f_t_1);
		baseFrequency.put("2T", f_t_2);
		baseFrequency.put("3T", f_t_3);
	}
	
	public static String ShortToAmino( String aminoshort ) {
		return AminoshortToAminoacid.get( aminoshort );
	}
	
	public static boolean isStopCodon( String triplet ) {
		if( stopCodonList.get(triplet) != null ) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isInitCodon( String triplet ) {
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
		Vector<String> codonVector = new Vector<String>();
		for ( int i=0; i<codonString.length(); i+=3 ) {
			codonVector.add( codonString.substring( i, i+3 ) );
		}
		
		return codonVector;
	}
	
	public static Vector<String> getCodonTripletsWithoutStop( String codonString ){
		Vector<String> codonVector = new Vector<String>();
		for ( int i=0; i<codonString.length(); i+=3 ) {
			String codon = codonString.substring( i, i+3 );
			if ( isStopCodon(codon) ) {
				continue;
			}
			codonVector.add( codonString.substring( i, i+3 ) );
		}
		
		return codonVector;
	}
	
	public static Vector<String> getCodonTripletsOnce( String codonString ){
		Map<String, Boolean> cache = new HashMap<String, Boolean>();
		Vector<String> codonVector = new Vector<String>();
		for ( int i=0; i<codonString.length(); i+=3 ) {
			String codon = codonString.substring( i, i+3 );
			if ( cache.get( codon ) != null ) {
				continue;
			}
			codonVector.add( codon );
			cache.put(codon, true);
		}
		
		return codonVector;
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
	
	public static double getAmountOf( Character ofWhat, String nucleinString ) {
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
	
	public static double getAmountOfAbs( Character ofWhat, String nucleinString ) {
		float c = 0;
		for (int i=0; i<nucleinString.length(); i++) {
			Character compValue = (Character) nucleinString.charAt(i);
			if ( compValue.equals(ofWhat) ) {
				c++;
			}
		}
		
		return c;
	}
	
	public static double getAmountOfAbsAtPos( Character ofWhat, Vector<String> tripletVector, int Position ) {
		double c = 0;
		for (int i=0; i<tripletVector.size(); i++) {
			String codon = tripletVector.get(i);
			Character compValue = (Character) codon.charAt( Position );
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
	
	public static float getL( String nucleinString ) {
		return ( getSequenceLength( nucleinString ) / 3 );
	}
	
	public static Map<String, Double> getCodonFrequency( Vector<String> codonVector ){
		Map<String, Double> Xi = new HashMap<String, Double>();
		
		// Calculate the frequency of the codon
		for ( int i=0; i<codonVector.size(); i++ ) {
			String codon = codonVector.get(i);

			if ( Xi.get( codon ) == null ) {
				Xi.put( codon, 1.0 );
			}
			else {
				double value = Xi.get( codon );
				++value;
				Xi.put( codon, value );
			}
		}
		
		return Xi;
	}
	
	public static Map<String, Double> getAminoacidFrequency( Vector<String> codonVector ){
		Map<String, Double> Xi = new HashMap<String, Double>();
		
		// Calculate the frequency of the codon
		for ( int i=0; i<codonVector.size(); i++ ) {
			String codon = codonVector.get(i);
			String aminoacid = codonTable.get(codon);

			if ( Xi.get( aminoacid ) == null ) {
				Xi.put( aminoacid, 1.0 );
			}
			else {
				double value = Xi.get( aminoacid );
				++value;
				Xi.put( aminoacid, value );
			}
		}
		
		return Xi;
	}
	
	public static Map<String, String> getXimaxCodon( Map<String, Double> XiValues ){
		Map<String, String> Ximax = new HashMap<String, String>();
		
		for ( String aminoacid : Aminoacids ) {
			String highestCodon = "";
			double highestValue = -1;
			for ( var entry : XiValues.entrySet() ) {
				String codon = entry.getKey();
				double value = entry.getValue();
						
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
	
	public static Map<String, Double> getAmountSamecodingCodons( ){
		Map<String, Double> SameCodingAmounts = new HashMap<String, Double>();
		
		for( var entry : getTriplettTable().entrySet() ) {
			if ( SameCodingAmounts.get( entry.getValue() ) == null ) {
				SameCodingAmounts.put( entry.getValue(), 1.0 );
			}
			else {
				double value = SameCodingAmounts.get( entry.getValue() );
				++value;
				SameCodingAmounts.put( entry.getValue(), value );
			}
		}
		
		return SameCodingAmounts;
	}
	
	public static double getGCContent( String inputString ) {
		String codonString = codonList( inputString );
		double GCContent = 1;
		double thymin = getAmountOfAbs( 'T', codonString );
		double adenin = getAmountOfAbs( 'A', codonString );
		double uracil = getAmountOfAbs( 'U', codonString );
		double guanin = getAmountOfAbs( 'G', codonString );
		double cytosin = getAmountOfAbs( 'C', codonString );
		
		double thym_uracil = uracil + thymin;
		
		GCContent = ( guanin + cytosin ) / ( adenin + thym_uracil + guanin + cytosin );
		
		return GCContent;
	}
	
	public static double getGCContentPos( String inputString, int pos ) {
		String codonString = codonList( inputString );
		Vector<String> tripletVector = getCodonTriplets( codonString );
		
		double GCXContent = 1;
		double thymin = getAmountOfAbsAtPos( 'T', tripletVector, pos );
		double adenin = getAmountOfAbsAtPos( 'A', tripletVector, pos  );
		double uracil = getAmountOfAbsAtPos( 'U', tripletVector, pos );
		double guanin = getAmountOfAbsAtPos( 'G', tripletVector, pos );
		double cytosin = getAmountOfAbsAtPos( 'C', tripletVector, pos );
		
		double thym_uracil = uracil + thymin;
		
		GCXContent = ( guanin + cytosin ) / ( adenin + thym_uracil + guanin + cytosin );
		
		return GCXContent;
	}
	
	public static double getDirectionalCodonBiasScore( String inputString  ) {
		String codonString = codonList( inputString );
		Vector<String> codonVector = getCodonTriplets( codonString );
		double DCBS = 0;
		
		Map<String, Double> f_xyz_frequency = getCodonFrequency( codonVector );
		Vector<Double> D_XYZ_Values = new Vector<Double>();
		
		
		
		// Calculating the d_xyz values
		for ( int i=0; i<codonVector.size(); i++ ) {
			String codon = codonVector.get(i);

			double d_xyz;
			double value_a;
			double value_b;
			
			Character x = codon.charAt(0);
			Character y = codon.charAt(1);
			Character z = codon.charAt(2);
			
			double f_x_1 = getBaseFrequencyAtPos( x, 1 );
			double f_y_2 = getBaseFrequencyAtPos( y, 2 );
			double f_z_3 = getBaseFrequencyAtPos( z, 3 );
		
			double f_xyz = f_xyz_frequency.get( codon );
			
			/*
			System.out.println( f_xyz );
			System.out.println( f_x_1 );
			System.out.println( f_y_2 );
			System.out.println( f_z_3 );
			*/
			
			value_a = f_xyz / ( f_x_1 * f_y_2 * f_z_3 );
			value_b = ( f_x_1 * f_y_2 * f_z_3 ) / f_xyz;
			
			if ( value_a < value_b ) {
				d_xyz = value_a;
			}
			else {
				d_xyz = value_b;
			}
			
			D_XYZ_Values.add( d_xyz );
		}
		
		for ( int i=0; i<D_XYZ_Values.size(); i++ ) {
			double dxyz = D_XYZ_Values.get(i);
			DCBS = DCBS + dxyz;
		}
		
		DCBS = (double) ( DCBS / (double) codonVector.size() );
		
		//System.out.println( codonVector.size() );
		//System.out.println( DCBS );
		
		return DCBS;
	}
	
	public static double getRCACodon( String codon ) {
		double RCA_k = 0;
		
		Character x = codon.charAt(0);
		Character y = codon.charAt(1);
		Character z = codon.charAt(2);
		
		double fx = getBaseFrequencyAtPos( x, 1 );
		double fy = getBaseFrequencyAtPos( y, 2 );
		double fz = getBaseFrequencyAtPos( z, 3 );
		
		double fxyz = 1;
		
		RCA_k = fxyz / fx * fy * fz;


		return RCA_k;
	}
	
	public static double getRCAGene( String inputString ) {
		double RCA = 0;
		
		String codonString = codonList( inputString );
		Vector<String> codonVector = getCodonTriplets( codonString );
		
		for ( int i=0; i<codonVector.size(); i++ ) {
			RCA = RCA * getRCACodon( codonVector.get(i) );
		}
			
		System.out.println( RCA );
		return RCA;
	}
	
}
