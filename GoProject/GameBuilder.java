/**
 * 
 */
package go;

/**
 * @author afatc
 *
 */
public class GameBuilder {
	
	public static Goban buildGoban() {
		return new Goban(GobanConfiguration.ABSCISSE_COUNT, GobanConfiguration.ORDONNEE_COUNT);
	}
	
}
