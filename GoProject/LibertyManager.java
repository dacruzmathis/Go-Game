/**
 * 
 */
package go;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author afatc
 *
 */
public class LibertyManager {
	private Goban goban;
	private int distance = (GobanConfiguration.BLOCK_SIZE * (GobanConfiguration.ABSCISSE_COUNT-1)) + GobanConfiguration.Abscisse_Start;

	
	public LibertyManager(Goban goban) {
		this.goban = goban;
	}
	
	public void addLiberty(Stones stone) {
		Intersection position = stone.getPosition();
		if(goban.isOnLeftTop(position)) {
			stone.setStoneLiberty(LeftTopLiberty(position));
		}
		else if (goban.isOnRightTop(position)) {
			stone.setStoneLiberty(RightTopLiberty(position));
		}
		else if (goban.isOnLeftBottom(position)) {
			stone.setStoneLiberty(LeftBottomLiberty(position));
		}
		else if (goban.isOnRightBottom(position)) {
			stone.setStoneLiberty(RightBottomLiberty(position));
		}
		else if (goban.isOnTopBorder(position)) {
			stone.setStoneLiberty(TopBorderLiberty(position));
		}
		else if (goban.isOnBottomBorder(position)) {
			stone.setStoneLiberty(BottomBorderLiberty(position));
		}
		else if (goban.isOnLeftBorder(position)) {
			stone.setStoneLiberty(LeftBorderLiberty(position));
		}
		else if (goban.isOnRightBorder(position)) {
			stone.setStoneLiberty(RightBorderLiberty(position));
		}
		else {
			stone.setStoneLiberty(OtherLiberty(position));
		}
	}
	
	public Liberty LeftTopLiberty(Intersection position) {
			Intersection right = new Intersection(position.getAbscisse()+GobanConfiguration.BLOCK_SIZE,position.getOrdonnee());
			Intersection down = new Intersection(position.getAbscisse(),position.getOrdonnee()+GobanConfiguration.BLOCK_SIZE);
			Liberty liberty = new Liberty();
			liberty.put(right);
			liberty.put(down);
			return liberty;
	}
	
	public Liberty RightTopLiberty(Intersection position) {
		Intersection left = new Intersection(distance,position.getOrdonnee());
		Intersection down = new Intersection(distance,position.getOrdonnee()+GobanConfiguration.BLOCK_SIZE);
		Liberty liberty = new Liberty();
		liberty.put(left);
		liberty.put(down);
		return liberty;
	}
	
	public Liberty LeftBottomLiberty(Intersection position) {
		Intersection up = new Intersection(position.getAbscisse(),position.getOrdonnee()-GobanConfiguration.BLOCK_SIZE);
		Intersection right = new Intersection(position.getAbscisse()+GobanConfiguration.BLOCK_SIZE,position.getOrdonnee());
		Liberty liberty = new Liberty();
		liberty.put(up);
		liberty.put(right);
		return liberty;
	}
	
	public Liberty RightBottomLiberty(Intersection position) {
		Intersection left = new Intersection(distance,position.getOrdonnee());
		Intersection up = new Intersection(distance,position.getOrdonnee()-GobanConfiguration.BLOCK_SIZE);
		Liberty liberty = new Liberty();
		liberty.put(left);
		liberty.put(up);
		return liberty;
	}
	
	public Liberty TopBorderLiberty(Intersection position) {
		Intersection left = new Intersection(position.getAbscisse()-GobanConfiguration.BLOCK_SIZE,position.getOrdonnee());
		Intersection right = new Intersection(position.getAbscisse()+GobanConfiguration.BLOCK_SIZE,position.getOrdonnee());
		Intersection down = new Intersection(position.getAbscisse(),position.getOrdonnee()+GobanConfiguration.BLOCK_SIZE);
		Liberty liberty = new Liberty();
		liberty.put(left);
		liberty.put(right);
		liberty.put(down);
		return liberty;
	}
	
	public Liberty BottomBorderLiberty(Intersection position) {
		Intersection left = new Intersection(position.getAbscisse()-GobanConfiguration.BLOCK_SIZE,position.getOrdonnee());
		Intersection right = new Intersection(position.getAbscisse()+GobanConfiguration.BLOCK_SIZE,position.getOrdonnee());
		Intersection up = new Intersection(position.getAbscisse(),position.getOrdonnee()-GobanConfiguration.BLOCK_SIZE);
		Liberty liberty = new Liberty();
		liberty.put(left);
		liberty.put(right);
		liberty.put(up);
		return liberty;
	}
	
	public Liberty LeftBorderLiberty(Intersection position) {
		Intersection right = new Intersection(position.getAbscisse()+GobanConfiguration.BLOCK_SIZE,position.getOrdonnee());
		Intersection up = new Intersection(position.getAbscisse(),position.getOrdonnee()-GobanConfiguration.BLOCK_SIZE);
		Intersection down = new Intersection(position.getAbscisse(),position.getOrdonnee()+GobanConfiguration.BLOCK_SIZE);
		Liberty liberty = new Liberty();
		liberty.put(right);
		liberty.put(up);
		liberty.put(down);
		return liberty;
	}
	
	public Liberty RightBorderLiberty(Intersection position) {
		Intersection left = new Intersection(position.getAbscisse()-GobanConfiguration.BLOCK_SIZE,position.getOrdonnee());
		Intersection up = new Intersection(position.getAbscisse(),position.getOrdonnee()-GobanConfiguration.BLOCK_SIZE);
		Intersection down = new Intersection(position.getAbscisse(),position.getOrdonnee()+GobanConfiguration.BLOCK_SIZE);
		Liberty liberty = new Liberty();
		liberty.put(left);
		liberty.put(up);
		liberty.put(down);
		return liberty;
	}
	
	public Liberty OtherLiberty(Intersection position) {
		Intersection left = new Intersection(position.getAbscisse()-GobanConfiguration.BLOCK_SIZE,position.getOrdonnee());
		Intersection up = new Intersection(position.getAbscisse(),position.getOrdonnee()-GobanConfiguration.BLOCK_SIZE);
		Intersection down = new Intersection(position.getAbscisse(),position.getOrdonnee()+GobanConfiguration.BLOCK_SIZE);
		Intersection right = new Intersection(position.getAbscisse()+GobanConfiguration.BLOCK_SIZE,position.getOrdonnee());
		Liberty liberty = new Liberty();
		liberty.put(left);
		liberty.put(up);
		liberty.put(down);
		liberty.put(right);
		return liberty;
	}
	
	public boolean RefreshLiberty(Stones stone , List<Stones> Stonespresent) {
		boolean verify = false;
		List<Intersection> present = new ArrayList<Intersection>();
		List<Intersection> liberty = stone.getStoneLiberty().getLiberty();		
		Iterator<Intersection> iterator = liberty.iterator();
		Iterator<Stones> iteration = Stonespresent.iterator();
		while(iterator.hasNext()) {
			Intersection position = iterator.next();
			while(iteration.hasNext()) {
				Stones stones = iteration.next();
				if(stones.getPosition().getAbscisse() == (position.getAbscisse())) {
					if(stones.getPosition().getOrdonnee() == (position.getOrdonnee())) {
					present.add(position);
					}
				}
			}
		}
		if(liberty.size()==present.size()) {
			verify = true;
		}
		return verify;
	}
	
	public boolean verifyColor(Stones stone , List<Stones> Stonespresent) {
		boolean same = true;
		List<Stones> LibertyStones = new ArrayList<Stones>();
		List<Intersection> liberty = stone.getStoneLiberty().getLiberty();
		Iterator<Intersection> iterator = liberty.iterator();
		Iterator<Stones> iteration = Stonespresent.iterator();
		while(iterator.hasNext()) {
			Intersection position = iterator.next();
			while(iteration.hasNext()) {
				Stones stones = iteration.next();
				if(stones.getPosition().equals(position)) {
					LibertyStones.add(stones);
				}
			}
		}
		Stones s = LibertyStones.get(0);
		Color color = s.getColor();
		Iterator<Stones> iterator2 = LibertyStones.iterator();
		while(iterator2.hasNext() && same == true) {
			Stones Stones = iterator2.next();
			if(Stones.getColor().equals(color)) {
				same = true;
			}
			else {
				same = false;
			}
		}
		return same;
	}
}