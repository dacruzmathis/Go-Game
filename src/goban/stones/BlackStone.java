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

/*Creation of the BlackStone class with black color*/
public class BlackStone extends Stones{
	private Color color = Color.BLACK;
	
	public BlackStone(Intersection position) {
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
