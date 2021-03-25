/**
 * 
 */
package goban.process;
import goban.stones.*;
import gui.GameDisplay;

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
	private GameDisplay dashboard;
	
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
	
	public boolean isExist (Intersection intersection) {
		Intersection result= new Intersection(0,0);
		for(Stones stones : getStonesIntersection()) {
			if (stones.getPosition().equals(intersection)) {
				result = stones.getPosition();
			}
		}
		return result == intersection;
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
		List<Stones> eliminatedStones = new ArrayList<Stones>();
		for(Stones stones : getStonesIntersection()) {
			if (countLiberties(stones)==0) {			
				eliminatedStones.add(stones);
			}
		}	
		for(Stones stones : eliminatedStones) {
			remove(stones);
		}		
	}
	
	public int countLiberties(Stones stones) {
		Intersection left = new Intersection(stones.getPosition().getAbscisse()-GobanConfiguration.BLOCK_SIZE, stones.getPosition().getOrdonnee());
		Intersection right = new Intersection(stones.getPosition().getAbscisse()+GobanConfiguration.BLOCK_SIZE, stones.getPosition().getOrdonnee());
		Intersection up = new Intersection(stones.getPosition().getAbscisse(), stones.getPosition().getOrdonnee()-GobanConfiguration.BLOCK_SIZE);
		Intersection down = new Intersection(stones.getPosition().getAbscisse(), stones.getPosition().getOrdonnee()+GobanConfiguration.BLOCK_SIZE);
		int liberties=4;
		if(isOccupied(left) || goban.isOnBorder(stones.getPosition())) {
			liberties--;
		}
		if(isOccupied(right) || goban.isOnBorder(stones.getPosition())) {
			liberties--;
		}
		if(isOccupied(up) || goban.isOnBorder(stones.getPosition())) {
			liberties--;
		}
		if(isOccupied(down) || goban.isOnBorder(stones.getPosition())) {
			liberties--;
		}
		return liberties;
	}
	
	public boolean isOccupied(Intersection intersection) {
		Intersection find = new Intersection(0,0);
		for (Stones stones : getStonesIntersection()) {
			if (stones.getPosition().equals(intersection)) {
				find = stones.getPosition();
				System.out.println("Occupied = " + find);
			}
		}
		return find == intersection;
	}

	
	public boolean isEnemy(Color a, Color b) {
		return a != b;
	}
	
	public boolean isForbidden(Intersection intersection) {	
		
		int x = intersection.getAbscisse();
		int y = intersection.getOrdonnee();
		
		int xl = intersection.getAbscisse()-(GobanConfiguration.BLOCK_SIZE);
		int xr = intersection.getAbscisse()+(GobanConfiguration.BLOCK_SIZE);
		
		int yu = intersection.getOrdonnee()-(GobanConfiguration.BLOCK_SIZE);
		int yd = intersection.getOrdonnee()+(GobanConfiguration.BLOCK_SIZE);
		
		Intersection left = new Intersection(xl, y);
		Intersection right = new Intersection(xr, y);
		Intersection up = new Intersection(x, yu);
		Intersection down = new Intersection(x, yd);
		/*
		Stones l = searchStones(left);
		Stones r = searchStones(right);
		Stones u = searchStones(up);
		Stones d = searchStones(down);
		*/
		int liberties=4;
		
		if(/*(isExist(left) && */isOccupied(left) /*&& isEnemy(turnColor(),l.getColor()) /*|| left.getAbscisse()<0*/) {
			liberties--;
		}
		if(/*(isExist(right) &&*/ isOccupied(right) /*&& isEnemy(turnColor(),r.getColor()) /*|| right.getAbscisse()>16*/) {
			liberties--;
		}
		if(/*(isExist(up)&& */isOccupied(up) /*&& isEnemy(turnColor(),u.getColor()) /*|| up.getOrdonnee()<0*/) {
			liberties--;
		}
		if(/*(isExist(down)&& */isOccupied(down) /*&& isEnemy(turnColor(),d.getColor()) /*|| down.getOrdonnee()>16*/) {
			liberties--;
		}
		System.out.println("liberties = "+liberties);
		return liberties == 0;
	}
	
	public String toString () {
		String result = "List=";
		for(Stones stones : getStonesIntersection()) {
				result += "["+stones.getPosition().toString()+"]";			
		}
		return result;
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

	
