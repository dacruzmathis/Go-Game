/**
 * 
 */
package goban.process;
import goban.stones.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import config.GobanConfiguration;
import goban.map.Goban;
import goban.map.Intersection;



/**
 * @author afatc
 *
 */
public class StonesManager {
	private Goban goban;	
	private List<Stones> StonesIntersection = new ArrayList<Stones>();
	
	public StonesManager(Goban goban) {
		this.goban = goban;
	}
	
	public void putStones (Stones stones) {	
		StonesIntersection.add(stones);
	}
	
	public Stones searchStones (Intersection intersection) {
		Stones result = null;
		for(Stones stones : getStonesIntersection()) {
			if (stones.getPosition().equals(intersection)) {
				result = stones;
			}
		}
		return result;
	}
	
	public void remove (Stones stones) {
		StonesIntersection.remove(stones);
	}

	public List<Stones> getStonesIntersection() {
		return StonesIntersection;
	}

	public void setStonesIntersection(List<Stones> stonesIntersection) {
		StonesIntersection = stonesIntersection;
	}
	
	public void isCaptured() {
		for(Stones stones : getStonesIntersection()) {
			if (countLiberties(stones)==0) {
				remove(stones);
			}
		}	
	}
	
	public int countLiberties(Stones stones) {
		Intersection left = new Intersection(stones.getPosition().getAbscisse()-GobanConfiguration.BLOCK_SIZE, stones.getPosition().getOrdonnee());
		Intersection right = new Intersection(stones.getPosition().getAbscisse()+GobanConfiguration.BLOCK_SIZE, stones.getPosition().getOrdonnee());
		Intersection up = new Intersection(stones.getPosition().getAbscisse(), stones.getPosition().getOrdonnee()-GobanConfiguration.BLOCK_SIZE);
		Intersection down = new Intersection(stones.getPosition().getAbscisse(), stones.getPosition().getOrdonnee()+GobanConfiguration.BLOCK_SIZE);
		int liberties=4;
		if(isOccupied(left) /*|| left.getAbscisse()<0*/) {
			liberties--;
		}
		if(isOccupied(right) /*|| right.getAbscisse()>16*/) {
			liberties--;
		}
		if(isOccupied(up) /*|| up.getOrdonnee()<0*/) {
			liberties--;
		}
		if(isOccupied(down) /*|| down.getOrdonnee()>16*/) {
			liberties--;
		}
		return liberties;
	}
	
	public boolean isOccupied(Intersection intersection) {
		Intersection find = new Intersection(0,0);
		for (Stones stones : getStonesIntersection()) {
			if (stones.getPosition().equals(intersection)) {
				find = stones.getPosition();
			}
		}
		return find == intersection;
	}
	
	public boolean isEnemy(Color a, Color b) {
		return a != b;
	}
	
	public boolean isForbidden(Intersection intersection) {
		
		Intersection left = new Intersection(intersection.getAbscisse()-GobanConfiguration.BLOCK_SIZE, intersection.getOrdonnee());
		Intersection right = new Intersection(intersection.getAbscisse()+GobanConfiguration.BLOCK_SIZE, intersection.getOrdonnee());
		Intersection up = new Intersection(intersection.getAbscisse(), intersection.getOrdonnee()-GobanConfiguration.BLOCK_SIZE);
		Intersection down = new Intersection(intersection.getAbscisse(), intersection.getOrdonnee()+GobanConfiguration.BLOCK_SIZE);
		
		Stones l = searchStones(left);
		Stones r = searchStones(right);
		Stones u = searchStones(up);
		Stones d = searchStones(down);
		
		int liberties=4;
		
		if((isOccupied(left) && isEnemy(turnColor(),l.getColor())) || left.getAbscisse()<0) {
			liberties--;
		}
		if((isOccupied(right) && isEnemy(turnColor(),r.getColor())) || right.getAbscisse()>16) {
			liberties--;
		}
		if((isOccupied(up) && isEnemy(turnColor(),u.getColor())) || up.getOrdonnee()<0) {
			liberties--;
		}
		if((isOccupied(down) && isEnemy(turnColor(),d.getColor())) || down.getOrdonnee()>16) {
			liberties--;
		}
		return liberties == 0;
	}
	
	public Color turnColor() {
		Color color;
		if(GobanConfiguration.TURN%3==0) {
			color = Color.BLACK;
		}
		else if(GobanConfiguration.TURN%3==1) {
			color = Color.WHITE;
		}
		else {
			color = Color.RED;
		}
		return color;
	}
	
	public void MegaStonePower(Intersection intersection) {
		Intersection left = new Intersection(intersection.getAbscisse()-1, intersection.getOrdonnee());
		Intersection right = new Intersection(intersection.getAbscisse()+1, intersection.getOrdonnee());
		Intersection up = new Intersection(intersection.getAbscisse(), intersection.getOrdonnee()-1);
		Intersection down = new Intersection(intersection.getAbscisse(), intersection.getOrdonnee()+1);
		
		Stones l = searchStones(left);
		Stones r = searchStones(right);
		Stones u = searchStones(up);
		Stones d = searchStones(down);
		
		if(isOccupied(left) && isEnemy(turnColor(),l.getColor())) {
			remove(l);
		}
		if((isOccupied(right) && isEnemy(turnColor(),r.getColor()))) {
			remove(r);
		}
		if((isOccupied(up) && isEnemy(turnColor(),u.getColor()))) {
			remove(u);
		}
		if((isOccupied(down) && isEnemy(turnColor(),d.getColor()))) {
			remove(d);
		}
	}
}

	
