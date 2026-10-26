package utiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriceUtils {

	public static double calculatePrice(String text) {
		return Double.parseDouble(text.replaceAll("[^0-9.]", ""));

	}
	
	public static double calculatePrice(List<Double> prices ) {
		
		double sum=0;
		for(double price:prices) {
			sum+=0;
		}
		return sum;
	}
	
	public static boolean isProductPricesInAscendingOrder(List<Double> priceList) {
		List<Double> sortedPrices = new ArrayList<>(priceList);
		Collections.sort(sortedPrices);
		
		return priceList.equals(sortedPrices);
		
	}
	
	public static boolean isProductPricesInDescendingOrder(List<Double> priceList) {
		List<Double> sortedPrices = new ArrayList<>(priceList);
		Collections.sort(sortedPrices, Collections.reverseOrder());
		
		return priceList.equals(sortedPrices);
	}
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
