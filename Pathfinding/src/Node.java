/**
 * Node class - This a class that will create a node object (Node) that will be
 * used to denote walls, start node, end node, open nodes, and closed nodes.
 * 
 * @author Chase Myers
 *
 */
public class Node {

	private int xCoord;
	private int yCoord;
	private int gScore;
	private int hScore;
	private int fScore;

	private Node parent;

	/**
	 * Constructor - constructs a node.
	 * 
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 */
	public Node(int x, int y) {
		this.xCoord = x;
		this.yCoord = y;
	}

	/**
	 * setCoordinates - sets the coordinates for the Node.
	 * 
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 */
	public void setCoordinates(int x, int y) {
		this.xCoord = x;
		this.yCoord = y;
	}

	/**
	 * setGScore - sets the g score of the Node.
	 * 
	 * @param g the g score.
	 */
	public void setGScore(int g) {
		this.gScore = g;
	}

	/**
	 * setHScore - sets the h score of the Node.
	 * 
	 * @param h the h score.
	 */
	public void setHScore(int h) {
		this.hScore = h;
	}

	/**
	 * setFScore - sets the f score of the Node.
	 * 
	 * @param f the f score.
	 */
	public void setFScore(int f) {
		this.fScore = f;
	}

	/**
	 * setParent - sets the parent node of the current Node.
	 * 
	 * @param parent the parent Node.
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * getXCoordinate - retrieves the x coordinate of the Node.
	 * 
	 * @return int the x coordinate value.
	 */
	public int getXCoordinate() {
		return xCoord;
	}

	/**
	 * getYCoordinate - retrieves the y coordinate of the Node.
	 * 
	 * @return int the y coordinate value.
	 */
	public int getYCoordinate() {
		return yCoord;
	}

	/**
	 * getGScore - retrieves the g score of the Node.
	 * 
	 * @return int the value of the g score.
	 */
	public int getGScore() {
		return gScore;
	}

	/**
	 * getHScore - retrieves the h score of the Node.
	 * 
	 * @return int the value of the h score.
	 */
	public int getHScore() {
		return hScore;
	}

	/**
	 * getFScore - retrieves the f score of the Node.
	 * 
	 * @return int the value of the f score.
	 */
	public int getFScore() {
		return fScore;
	}

	/**
	 * getNode - gets this Node.
	 * 
	 * @return Node this node.
	 */
	public Node getNode() {
		return this;
	}

	/**
	 * getParent - gets the parent Node of this Node.
	 * 
	 * @return Node the parent node.
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * isEqual - looks and checks if this Node is equal to another Node.
	 * 
	 * @param current the current Node.
	 * @param other   the other Node.
	 * 
	 * @return boolean if the Nodes are equal.
	 */
	public static boolean isEqual(Node current, Node other) {
		if (current.getXCoordinate() == other.getXCoordinate() && current.getYCoordinate() == other.getYCoordinate()) {
			return true;
		}
		return false;
	}
}
