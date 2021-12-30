/**
 * 
 */
package goban.process;

import config.GobanConfiguration;
import goban.map.Goban;

/**
 * @author afatc
 *
 */
/*This is for set the goban height and set the stones*/
public class GameBuilder {
	
	public static Goban buildGoban() {
		return new Goban(GobanConfiguration.ABSCISSE_COUNT, GobanConfiguration.ORDONNEE_COUNT);
	}
	
	public static StonesManager InitStones(Goban goban) {
		StonesManager manager = new StonesManager(goban);
		return manager;
	}
}
