import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The window that will display the Path-finding algorithm.
 * 
 * @author Chase Myers
 *
 */
public class PathfindingWindow extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JFrame window;
	Node startNode;
	Node endNode;
	PathfindingListener listener;
	
	int size;
	
	/**
	 * main - starts the program.
	 * 
	 * @param args command line args.
	 */
	public static void main(String[] args) {
		new PathfindingWindow();
	}
	
	/**
	 * PathfindingWindow - the constructor for the window.
	 * 
	 */
	public PathfindingWindow() {
		size = 16;
		
		window = new JFrame();
		window.setTitle("A* Pathfinding Visualization");
		window.setContentPane(this);
		window.getContentPane().setPreferredSize(new Dimension(800, 800));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		
		
		
	}
	
	/**
	 * paintComponent - paints the components on the graph.
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int height = getHeight();
		int width = getWidth();
	
		// draws the grid on the frame.
		g.setColor(Color.DARK_GRAY);
		for(int i = 0; i < height; i += size) {
			for(int j = 0; j < width; j += size) {
				g.drawRect(j, i, size, size);
			}
		}
		
		// draws the borders of the frame.
		g.setColor(Color.BLACK);
		// CREATE HERE.
		
		
	}

}
