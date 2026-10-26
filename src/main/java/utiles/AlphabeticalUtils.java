package utiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlphabeticalUtils {
	
	public static boolean isInAlphabeticSequence(List<String> namesList) {
		List<String> productsName = new ArrayList<>(namesList);
		Collections.sort(productsName);
		return namesList.equals(productsName);
	}
	
	public static boolean isInAlphabeticReverseSequence(List<String> namesList) {
		List<String> productsName = new ArrayList<>(namesList);
		Collections.sort(productsName, Collections.reverseOrder());
		
		return namesList.equals(productsName);
	}

}
