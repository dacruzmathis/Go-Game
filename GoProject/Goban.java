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
	
	private int distance = (GobanConfiguration.BLOCK_SIZE * (GobanConfiguration.ABSCISSE_COUNT-1)) + GobanConfiguration.Abscisse_Start;
	
	private int abscisseCount;
	private int ordonneeCount;
	private int AbscisseStart = GobanConfiguration.Abscisse_Start;
	private int OrdonneeStart = GobanConfiguration.Ordonnee_Start;
	
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
	
	public boolean isOnLeftTop(Intersection position) {
		return position.getAbscisse() == GobanConfiguration.Abscisse_Start && position.getOrdonnee() == GobanConfiguration.Ordonnee_Start;
	}
	
	public boolean isOnRightTop(Intersection position) {
		int abscisse = position.getAbscisse();
		int ordonnee = position.getOrdonnee();
		return abscisse == distance  && ordonnee == GobanConfiguration.Ordonnee_Start;
	}
	
	public boolean isOnLeftBottom(Intersection position) {
		int x = position.getAbscisse();
		int y = position.getOrdonnee();
		return x == GobanConfiguration.Abscisse_Start &&  y == distance; 
	}
	
	public boolean isOnRightBottom(Intersection position) {
		int x = position.getAbscisse();
		int y = position.getOrdonnee();
		return x == distance && y == GobanConfiguration.Ordonnee_Start + distance;
	}
	
	public boolean isOnTopBorder(Intersection position) {
		int x = position.getAbscisse();
		int y = position.getOrdonnee();
		return GobanConfiguration.Abscisse_Start <= x && x <= distance  && y == GobanConfiguration.Ordonnee_Start;
	}
	
	public boolean isOnBottomBorder(Intersection position) {
		int x = position.getAbscisse();
		int y = position.getOrdonnee();
		return GobanConfiguration.Abscisse_Start <= x && x <= distance  && y == distance;
	}
	
	public boolean isOnLeftBorder(Intersection position) {
		int x = position.getAbscisse();
		int y = position.getOrdonnee();
		return GobanConfiguration.Abscisse_Start == x  && y >= GobanConfiguration.Ordonnee_Start && y <= distance;
	}
	
	public boolean isOnRightBorder(Intersection position) {
		int x = position.getAbscisse();
		int y = position.getOrdonnee();
		return x == distance && y >= GobanConfiguration.Ordonnee_Start && y <= distance;
	}
}