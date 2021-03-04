/**
 * 
 */
package goban.map;

import config.GobanConfiguration;

/**
 * @author afatc
 *
 */
public class Goban {
	private Intersection[][] intersections;
	
	private int abscisseCount;
	private int ordonneeCount;
	private int AbscisseStart = 100;
	private int OrdonneeStart = 100;
	
	/*Creation of the table to put stones on the intersections of the goban*/
	public Goban(int abscisseCount , int ordonneeCount) {
		this.abscisseCount = abscisseCount;
		this.ordonneeCount = ordonneeCount;
		
		intersections = new Intersection[abscisseCount][ordonneeCount];
		
		for(int abscisseIndex = 0; abscisseIndex < abscisseCount; abscisseIndex++) {
			for (int ordonneeIndex = 0; ordonneeIndex < ordonneeCount; ordonneeIndex++) {
				intersections[abscisseIndex][ordonneeIndex] = new Intersection(AbscisseStart,OrdonneeStart);	
				OrdonneeStart += GobanConfiguration.BLOCK_SIZE;
			}
			OrdonneeStart = 100;
			AbscisseStart += GobanConfiguration.BLOCK_SIZE;
		}
	}

	public Intersection[][] getIntersections() {
		return intersections;
	}

	public int getAbscisseCount() {
		return abscisseCount;
	}

	public int getOrdonneeCount() {
		return ordonneeCount;
	}
	
	public Intersection getIntersection(int abscisse , int ordonnee) {
		return intersections[abscisse][ordonnee];
	}
}