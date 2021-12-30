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

/*Creation of the WhiteStone class with white color*/
public class WhiteStone extends Stones{
	private Color color = Color.WHITE;
	
	public WhiteStone(Intersection position) {
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
