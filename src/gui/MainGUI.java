/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;





/**
 * @author afatc
 *
 */
public class MainGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private static final Font MENU_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 50);

	
	
	protected JButton vsButton = new JButton("Mode 3 adversaires réels");
	protected JButton botButton = new JButton("Mode 2 adversaires réels, 1 bot");
	protected JButton exitButton = new JButton("Exit");
	protected JButton helpButton = new JButton("Help");
	protected JButton megapionButton = new JButton("Megapion");
	protected JButton passButton = new JButton("Pass");
	protected JButton resignButton = new JButton("Resign");
	protected JButton menuButton = new JButton("Menu");
	
	protected JLabel goLabel = new JLabel("Jeu de GO");
	protected JLabel tourLabel = new JLabel("Au tour de: ");
	protected JLabel colorLabel = new JLabel("BLACK");
	protected JLabel scoreLabel = new JLabel("Nombre de pierres capturées :");
	protected JLabel endLabel = new JLabel("Fin de Partie");
	
	private JPanel menutextPanel;
	private JPanel menubuttonPanel;

	private GameFrame gameFrame;
	
	public MainGUI(String title) {
		super(title);
		initMenu();
	}
	
	//Menu
	private void initMenu() {
		
		Container contentPaneMenu = getContentPane();
		contentPaneMenu.setLayout(new BorderLayout());		
		
		menutextPanel = new JPanel();
		menutextPanel.setLayout(new FlowLayout(FlowLayout.CENTER));		
		goLabel.setFont(MENU_FONT);
		menutextPanel.add(goLabel);
		
		contentPaneMenu.add(goLabel,BorderLayout.NORTH);
		
		menubuttonPanel = new JPanel();
		menubuttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		menubuttonPanel.setLayout(new BoxLayout(menubuttonPanel, BoxLayout.Y_AXIS));				
		vsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		menubuttonPanel.add(vsButton);
		menubuttonPanel.add(Box.createVerticalStrut(20));
		botButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		menubuttonPanel.add(botButton);
		menubuttonPanel.add(Box.createVerticalStrut(40));
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		menubuttonPanel.add(exitButton);
		
		contentPaneMenu.add(menubuttonPanel,BorderLayout.CENTER);
		
		exitButton.addActionListener(new ExitAction(this));
		vsButton.addActionListener(new VSAction(this));
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
		
	}
	

	private class ExitAction implements ActionListener {
		//Window to be closed.
		private JFrame window;

		public ExitAction(JFrame window) {
			this.window = window;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			window.dispose();
			
		}

	}
	
	private class VSAction implements ActionListener {
		//Window to be closed.
		private JFrame window;

		public VSAction(JFrame window) {
			this.window = window;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			window.dispose();			
			gameFrame = new GameFrame();
		}
		
	}
}
