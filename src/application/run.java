package application;

public class run {

	public static void main(String[] args) {
		
		String[] rnaExample = new String[1];
		rnaExample[0] = "AUG,AUA,AUC,UAG,AUG,UAG";
		biocodonencoder.encodeRNA( rnaExample );
		
		String[] dnaExample = new String[1];
		dnaExample[0] = "TTTTTAAATTAGAATAGTTGA";
		biocodonencoder.encodeDNA( dnaExample );
	}

}
