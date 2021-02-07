/**
 * 
 */
package go;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;


/**
 * @author afatc
 *
 */
public class MainGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private Goban goban;
	
	private GameDisplay dashboard;
	
	private final static Dimension preferredSize = new Dimension(GobanConfiguration.WINDOW_WIDTH, GobanConfiguration.WINDOW_HEIGHT);
	
	public MainGUI(String title) {
		super(title);
		init();
	}
	
	private void init() {
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		goban = GameBuilder.buildGoban();
		dashboard = new GameDisplay(goban);
		
		dashboard.setPreferredSize(preferredSize);
		contentPane.add(dashboard,BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setPreferredSize(preferredSize);
		setResizable(false);
	}
}
