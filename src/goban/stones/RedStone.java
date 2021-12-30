/**
 * 
 */
package goban.stones;

import java.awt.Color;

import goban.map.Intersection;

/**
 * @author afatc
 *
 */

/*Creation of the RedStone class with red color*/
public class RedStone extends Stones{
	private Color color = Color.RED;
	
	public RedStone(Intersection position) {
		super(position);
		this.setColor(color);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
