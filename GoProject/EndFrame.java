package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class EndFrame extends JFrame{
	
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
	
	
	private JPanel endtextPanel;
	private JPanel endbuttonPanel;
	
	private MainGUI menuFrame;
	
	public EndFrame() {
		endgame();
	}
	
	private void endgame() {
		
		setTitle("End Game");
		Container contentPaneEnd = getContentPane();
		contentPaneEnd.setLayout(new BorderLayout());
		
		endtextPanel = new JPanel();
		endtextPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		endLabel.setFont(MENU_FONT);
		endtextPanel.add(endLabel);
		
		contentPaneEnd.add(endLabel,BorderLayout.NORTH);
		
		endbuttonPanel = new JPanel();
		endbuttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		endbuttonPanel.add(menuButton);
		endbuttonPanel.add(exitButton);
		
		contentPaneEnd.add(endbuttonPanel,BorderLayout.CENTER);
		
		menuButton.addActionListener(new MenuAction(this));
		exitButton.addActionListener(new ExitAction(this));
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
		
	}

	private class MenuAction implements ActionListener {
		//Window to be closed.
		private JFrame window;
	
		public MenuAction(JFrame window) {
			this.window = window;
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			window.dispose();		
			menuFrame = new MainGUI("Menu");
		}
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
	
}
