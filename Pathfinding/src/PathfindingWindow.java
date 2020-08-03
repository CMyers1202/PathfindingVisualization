import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * The window that will display the Path-finding algorithm.
 * 
 * @author Chase Myers
 *
 */
public class PathfindingWindow extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;

	JFrame window;

	private Node startNode;
	private Node endNode;

	private int size;

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

		addMouseListener(this);
		addMouseMotionListener(this);

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
		for (int i = 0; i < height; i += size) {
			for (int j = 0; j < width; j += size) {
				g.drawRect(j, i, size, size);
			}
		}

		// Draws the start node with a cyan color.
		if (startNode != null) {
			g.setColor(Color.BLUE);
			g.fillRect(startNode.getXCoordinate(), startNode.getYCoordinate(), size, size);
		}

		// Draws the end node with a red color.
		if (endNode != null) {
			g.setColor(Color.RED);
			g.fillRect(endNode.getXCoordinate(), endNode.getYCoordinate(), size, size);
		}

	}

	/**
	 * getStartNode - gets the start node.
	 * 
	 * @return Node the start node.
	 */
	public Node getStartNode() {
		return startNode;
	}

	/**
	 * getEndNode - gets the end node.
	 * 
	 * @return Node the end node.
	 */
	public Node getEndNode() {
		return endNode;
	}

	/**
	 * returns the size of the square node.
	 */
	public int getSquareSize() {
		return size;
	}

	/**
	 * sets the start node.
	 * 
	 * @param node the node to set start node as.
	 */
	public void setStartNode(Node node) {
		startNode = node;
	}

	/**
	 * sets the end node.
	 * 
	 * @param node the node to set end node as.
	 */
	public void setEndNode(Node node) {
		endNode = node;
	}

	/*****************************************************/
	/**************** LISTENER COMPONENTS ****************/
	/*****************************************************/

	@Override
	public void mouseClicked(MouseEvent e) {
		cursorLocation(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	/**
	 * finds where the cursor is to place the node.
	 * 
	 * @param e the mouse event.
	 */
	public void cursorLocation(MouseEvent e) {

		int xValue = e.getX() % getSquareSize();
		int yValue = e.getY() % getSquareSize();

		// If the left mouse is clicked.
		if (SwingUtilities.isLeftMouseButton(e)) {

			// if the start node is not on the grid set the start node.
			if (getStartNode() == null) {
				Node start = new Node(e.getX() - xValue, e.getY() - yValue);
				setStartNode(start);
			}

			// if the start node is on the grid and the end node is not set the end node.
			else if (getStartNode() != null && getEndNode() == null) {
				Node end = new Node(e.getX() - xValue, e.getY() - yValue);

				if (!Node.isEqual(end, getStartNode())) {
					setEndNode(end);
				}
			}
			// Start and End node is on the grid, create a wall.
			else {
				// Create here.
			}
			repaint();
		}

		// If the right mouse button is clicked.
		else if (SwingUtilities.isRightMouseButton(e)) {
			Node clicked = new Node(e.getX() - xValue, e.getY() - yValue);

			// Compares the current clicked location to start node.
			if (getStartNode() != null && Node.isEqual(clicked, getStartNode())) {
				setStartNode(null);
			}

			// Compares the current clicked location to end node.
			if (getEndNode() != null && Node.isEqual(clicked, getEndNode())) {
				setEndNode(null);
			}
			repaint();
		}
	}
}
