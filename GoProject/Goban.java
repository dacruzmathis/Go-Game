/**
 * 
 */
package go;

/**
 * @author afatc
 *
 */
public class Goban {
	private Intersection[][] intersections;
	
	private int abscisseCount;
	private int ordonneeCount;
	
	public Goban(int abscisseCount , int ordonneeCount) {
		this.abscisseCount = abscisseCount;
		this.ordonneeCount = ordonneeCount;
		
		intersections = new Intersection[abscisseCount][ordonneeCount];
		
		for(int abscisseIndex = 0; abscisseIndex < abscisseCount; abscisseIndex++) {
			for (int ordonneeIndex = 0; ordonneeIndex < ordonneeCount; ordonneeIndex++) {
				intersections[abscisseIndex][ordonneeIndex] = new Intersection(abscisseIndex, ordonneeIndex);				
			}
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
	 public Goban(Intersection position) {
		 super();
	 }
}
