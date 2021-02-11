/**
 * 
 */
package go;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author afatc
 *
 */
public class GameDisplay extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Goban goban;
	private PaintGoban paintGoban = new PaintGoban();
	
	public GameDisplay(Goban goban ) {
		this.goban = goban;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		paintGoban.paint(goban, g);
		paintGoban.grill(goban, g);
		paintGoban.hoshi(goban, g);
	}
}
