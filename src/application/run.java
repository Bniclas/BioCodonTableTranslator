package application;

import java.io.FileWriter;

public class run {

	public static void main(String[] args) throws Exception {
		
		/*
		biocodonencoder.prepare(1);
		String[] rnaExample = new String[1];
		rnaExample[0] = "AUG,AUA,AUC,UAG,AUG,UAG,AUG,UUG,UUC,AUG,UUC,CCU,UAA";
		
		String result = biocodonencoder.encodeRNA( rnaExample );
		System.out.println( result );
		
		FileWriter myWriter = new FileWriter("save.txt");
		myWriter.write(result);
		myWriter.close();
		
		
		String[] dnaExample = new String[1];
		dnaExample[0] = "TTT;TTA;AAT;TAG;AAT;AGT;TGA";
		biocodonencoder.encodeDNA( dnaExample );
		*/
		
		ReferenceDataSets.getReferenceDataSet();
		menu menu = new menu();
	}

}
