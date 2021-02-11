/**
 * 
 */
package go;

import java.awt.Color;
import java.awt.Graphics;


/**
 * @author afatc
 *
 */
public class PaintGoban {
	public void paint(Goban goban , Graphics graphics) {
		int blockSize = GobanConfiguration.BLOCK_SIZE;
		Intersection[][] intersections = goban.getIntersections();
		
		for (int abscisseIndex = 0; abscisseIndex < goban.getAbscisseCount(); abscisseIndex++) {
			for (int ordonneeIndex = 0; ordonneeIndex < goban.getOrdonneeCount(); ordonneeIndex++) {
				Intersection intersection = intersections[abscisseIndex][ordonneeIndex];
				graphics.setColor(Color.YELLOW);
				graphics.fillRect(intersection.getAbscisse() * blockSize, intersection.getOrdonnee() * blockSize, blockSize, blockSize);
			}
		}
	}
		public void grill(Goban goban , Graphics g) {
		int blockSize = GobanConfiguration.BLOCK_SIZE;
		Intersection[][] intersections = goban.getIntersections();
		for (int abscisseIndex = 1; abscisseIndex < (goban.getAbscisseCount()); abscisseIndex++) {
			for (int ordonneeIndex = 1; ordonneeIndex < (goban.getOrdonneeCount()); ordonneeIndex++) {
				Intersection intersection = intersections[abscisseIndex][ordonneeIndex];
				g.setColor(Color.BLACK);
				g.drawLine(abscisseIndex*blockSize,ordonneeIndex*blockSize,abscisseIndex*blockSize,(goban.getOrdonneeCount()-1)*blockSize);
				g.drawLine(abscisseIndex*blockSize,ordonneeIndex*blockSize,(goban.getAbscisseCount()-1)*blockSize,ordonneeIndex*blockSize);
				
			}
		}
	}
	public void hoshi(Goban goban , Graphics g) {
		int blockSize = GobanConfiguration.BLOCK_SIZE;
		Intersection[][] intersections = goban.getIntersections();
		for (int abscisseIndex = 1; abscisseIndex < (goban.getAbscisseCount()); abscisseIndex++) {
			for (int ordonneeIndex = 1; ordonneeIndex < (goban.getOrdonneeCount()); ordonneeIndex++) {
				if (abscisseIndex == 4 && ordonneeIndex == 4 || abscisseIndex == 14 && ordonneeIndex == 14 || abscisseIndex == 4 && ordonneeIndex == 14 || abscisseIndex == 14 && ordonneeIndex == 4)  {
					g.setColor(Color.BLACK);
					g.fillOval((abscisseIndex)*blockSize, (ordonneeIndex)*blockSize, blockSize/2, blockSize/2);
				}
			}
		}	
	}	
}			
	