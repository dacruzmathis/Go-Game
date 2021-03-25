package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import config.GobanConfiguration;
import goban.map.Goban;
import goban.map.Intersection;
import goban.process.GameBuilder;
import goban.process.StonesManager;
import goban.stones.BlackStone;
import goban.stones.RedStone;
import goban.stones.Stones;
import goban.stones.WhiteStone;


public class GameFrame extends JFrame{
	
private static final long serialVersionUID = 1L;
	
	private Goban goban;
	 
	private StonesManager manager;
	
	private GameDisplay dashboard;
	
	private final static Dimension preferredSize = new Dimension(GobanConfiguration.WINDOW_WIDTH, GobanConfiguration.WINDOW_HEIGHT);
	

	private static final Font TURN_FONT = new Font(Font.DIALOG, Font.BOLD, 20);
	
	private static final Color TURN_RED = Color.RED;
	private static final Color TURN_WHITE = Color.WHITE;
	private static final Color TURN_BLACK = Color.BLACK;
	
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
	

	private JPanel tourPanel;
	private JPanel menuPanel;
	private JPanel gamePanel;
	
	private int passcount = 0;

	private EndFrame end;
	
	public GameFrame() {
		init();
	}
	
	private void init() {
		
		setTitle("Jeu de Go");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		goban = GameBuilder.buildGoban();
		manager = GameBuilder.InitStones(goban);
		dashboard = new GameDisplay(goban, manager);
		
		MouseControls mouseControls = new MouseControls();
		dashboard.addMouseListener(mouseControls);
		
		dashboard.setPreferredSize(preferredSize);
		
		tourPanel = new JPanel();
		tourPanel.setLayout(new FlowLayout(FlowLayout.LEFT));		
		tourLabel.setFont(TURN_FONT);
		colorLabel.setFont(TURN_FONT);
		colorLabel.setForeground(TURN_BLACK);
		
		tourPanel.add(tourLabel);
		tourPanel.add(colorLabel);
		tourPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(tourPanel,BorderLayout.WEST);	
							
		
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));		
		helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuPanel.add(helpButton);
		menuPanel.add(Box.createVerticalStrut(50));
		megapionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuPanel.add(megapionButton);
		menuPanel.add(Box.createVerticalStrut(50));
		passButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuPanel.add(passButton);
		menuPanel.add(Box.createVerticalStrut(50));
		resignButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuPanel.add(resignButton);
				
		
		gamePanel = new JPanel();
		gamePanel.setLayout(new FlowLayout());
		gamePanel.add(scoreLabel);
		gamePanel.add(dashboard);
		gamePanel.add(menuPanel);
		
		contentPane.add(gamePanel,BorderLayout.CENTER);
	

				
		passButton.addActionListener(new PassAction(this));
		resignButton.addActionListener(new ResignAction(this));
		
					
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
	}

	private class PassAction implements ActionListener {
		//Window to be closed.
		private JFrame window;
	
		public PassAction(JFrame window) {
					this.window = window;
		}

	
	public void actionPerformed(ActionEvent e) {
		GobanConfiguration.TURN++;
		if(GobanConfiguration.TURN%3==0) {
			updateBLACKColorMessage("BLACK");
			}
		if(GobanConfiguration.TURN%3==1) {
			updateWHITEColorMessage("WHITE");
		}
		if(GobanConfiguration.TURN%3==2) {
			updateREDColorMessage(" RED ");
		}
		
		passcount++;
		if(passcount == 3) {
			window.dispose();
			end = new EndFrame();
			}
		}
	}




	private class ResignAction implements ActionListener {
		//Window to be closed.
		private JFrame window;
	
		public ResignAction(JFrame window) {
			this.window = window;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			window.dispose();			
			end = new EndFrame();			
			}
		
	}

	private void updateWHITEColorMessage(String message) {
		colorLabel.setForeground(TURN_WHITE);
		colorLabel.setText(message);
	}

	private void updateREDColorMessage(String message) {
		colorLabel.setForeground(TURN_RED);
		colorLabel.setText(message);
	}

	private void updateBLACKColorMessage(String message) {
		colorLabel.setForeground(TURN_BLACK);
		colorLabel.setText(message);
	}
	
	private class MouseControls implements MouseListener {
		/*This is for alternate colors of the stones when we click with the mouse*/
		public void mouseClicked(MouseEvent arg0) {
			int x = arg0.getX()+(GobanConfiguration.BLOCK_SIZE/2);
			int y = arg0.getY()+(GobanConfiguration.BLOCK_SIZE/2);
			
			int xl = x-(GobanConfiguration.BLOCK_SIZE);
			int xr = x+(GobanConfiguration.BLOCK_SIZE);
			
			int yu = y-(GobanConfiguration.BLOCK_SIZE);
			int yd = y+(GobanConfiguration.BLOCK_SIZE);
			
			Intersection stonePosition = dashboard.getStonePosition(x,y);
			System.out.println(stonePosition);

			if(!manager.isOccupied(stonePosition)) {
				int liberties=4;
				if(!goban.isOnLeftBorder(stonePosition)) {
					Intersection left = dashboard.getStonePosition(xl, y);
					Stones l = manager.searchStones(left);
					if((manager.isExist(left) && manager.isOccupied(left)) && manager.isEnemy(manager.turnColor(),l.getColor())) {
						liberties--;
					}
				}
				if(!goban.isOnRightBorder(stonePosition)) {
					Intersection right = dashboard.getStonePosition(xr, y);
					Stones r = manager.searchStones(right); 
					if((manager.isExist(right) && manager.isOccupied(right)) && manager.isEnemy(manager.turnColor(),r.getColor())) {
						liberties--;
					}
				}
				if(!goban.isOnTopBorder(stonePosition)) {
					Intersection up = dashboard.getStonePosition(x, yu);
					Stones u = manager.searchStones(up);
					if((manager.isExist(up)&& manager.isOccupied(up)) && manager.isEnemy(manager.turnColor(),u.getColor())) {
						liberties--;
					}
				}
				if(!goban.isOnBottomBorder(stonePosition)) {
					Intersection down = dashboard.getStonePosition(x, yd);
					Stones d = manager.searchStones(down);
					if((manager.isExist(down)&& manager.isOccupied(down)) && manager.isEnemy(manager.turnColor(),d.getColor())) {
						liberties--;
					}
				}
			
				if(goban.isOnBottom(stonePosition)) {
					liberties = liberties-2;
				}
				else if(goban.isOnBorder(stonePosition)) {
					liberties--;
				}
				System.out.println("liberties = "+liberties);
				if(liberties != 0) {
					if(GobanConfiguration.TURN%3==0) {				
						Stones blackstones = new BlackStone(stonePosition); 
						manager.putStones(blackstones);
						updateWHITEColorMessage("WHITE");
					}
					else if(GobanConfiguration.TURN%3==1) {
						Stones whitestones = new WhiteStone(stonePosition);
						manager.putStones(whitestones);
						updateREDColorMessage(" RED ");
					}
					else {
		
						Stones redstones = new RedStone(stonePosition);
						manager.putStones(redstones);
						updateBLACKColorMessage("BLACK");
					}
					System.out.println(manager.toString());
					//manager.isCaptured();
					passcount=0;
					GobanConfiguration.TURN++;
					dashboard.repaint();
				}
			}
		}
		

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
		
		}
	}
}
