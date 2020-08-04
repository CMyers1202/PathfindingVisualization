import java.awt.Frame;
import java.util.ArrayList;

/**
 * Pathfinding - class that implements the A* Pathfinding algorithm.
 * 
 * @author Chase Myers
 *
 */
public class Pathfinding {

	private PathfindingWindow frame;

	private Node startNode;
	private Node endNode;

	private ArrayList<Node> borderNodes;
	private ArrayList<Node> openNodes;
	private ArrayList<Node> closedNodes;
	private ArrayList<Node> pathNodes;

	private int size;

	boolean isRunning;
	boolean isComplete;

	/**
	 * constructor - the constructor.
	 * 
	 * @param size the size of a node.
	 */
	public Pathfinding(int size) {

		this.size = size;

		isRunning = false;
		isComplete = false;

		borderNodes = new ArrayList<Node>();
		openNodes = new ArrayList<Node>();
		closedNodes = new ArrayList<Node>();
		pathNodes = new ArrayList<Node>();

	}

	/**
	 * constructor - an overloaded constructor passed the frame.
	 * 
	 * @param size  the size of a node.
	 * @param frame the frame.
	 */
	public Pathfinding(int size, PathfindingWindow frame) {

		this.size = size;
		this.frame = frame;

		isRunning = false;
		isComplete = false;

		borderNodes = new ArrayList<Node>();
		openNodes = new ArrayList<Node>();
		closedNodes = new ArrayList<Node>();
		pathNodes = new ArrayList<Node>();

	}

	/**
	 * constructor - an overloaded constructor passed frame, start, and end nodes.
	 * 
	 * @param size      the size of a node.
	 * @param frame     the frame.
	 * @param startNode the start node.
	 * @param endNode   the end node.
	 */
	public Pathfinding(int size, PathfindingWindow frame, Node startNode, Node endNode) {

		this.size = size;
		this.frame = frame;
		this.startNode = startNode;
		this.endNode = endNode;

		isRunning = false;
		isComplete = false;

		borderNodes = new ArrayList<Node>();
		openNodes = new ArrayList<Node>();
		closedNodes = new ArrayList<Node>();
		pathNodes = new ArrayList<Node>();
	}

	/**
	 * start - starts the pathfinding.
	 * 
	 * @param beginning the start node.
	 * @param end       the end node.
	 */
	public void start(Node beginning, Node end) {

		// Algorithm is running and nodes are being set up.
		isRunning = true;

		startNode = beginning;
		startNode.setGScore(0);
		endNode = end;

		addClosedNode(startNode);

		// Create method for pathfinding here.

		isComplete = true;

	}

	/**
	 * isRunning - returns boolean variable isRunning.
	 * 
	 * @return boolean isRunning.
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * isComplete - returns boolean variable isComplete.
	 * 
	 * @return boolean isComplete.
	 */
	public boolean isComplete() {
		return isComplete;
	}

	/**
	 * getBorderNodeList - returns list of nodes.
	 * 
	 * @return ArrayList<Node> the nodes.
	 */
	public ArrayList<Node> getBorderNodeList() {
		return borderNodes;
	}

	/**
	 * getOpenNodeList - returns list of nodes.
	 * 
	 * @return ArrayList<Node> the nodes.
	 */
	public ArrayList<Node> getOpenNodeList() {
		return openNodes;
	}

	/**
	 * getClosedNodeList - returns list of nodes.
	 * 
	 * @return ArrayList<Node> the nodes.
	 */
	public ArrayList<Node> getClosedNodeList() {
		return closedNodes;
	}

	/**
	 * getPathNodeList - returns list of nodes.
	 * 
	 * @return ArrayList<Node> the nodes.
	 */
	public ArrayList<Node> getPathNodeList() {
		return pathNodes;
	}

	/**
	 * addBorderNode - adds a node to the border if it is not there.
	 * 
	 * @param node the node.
	 */
	public void addBorderNode(Node node) {
		if (borderNodes.size() == 0) {
			borderNodes.add(node);
		} else if (!borderContainsNode(node)) {
			borderNodes.add(node);
		}
	}

	/**
	 * addOpenNode - adds a node to the open if it is not there.
	 * 
	 * @param node the node.
	 */
	public void addOpenNode(Node node) {
		if (openNodes.size() == 0) {
			openNodes.add(node);
		} else if (!openContainsNode(node)) {
			openNodes.add(node);
		}
	}

	/**
	 * addClosedNode - adds a node to the closed if it is not there.
	 * 
	 * @param node the node.
	 */
	public void addClosedNode(Node node) {
		if (closedNodes.size() == 0) {
			closedNodes.add(node);
		} else if (!closedContainsNode(node)) {
			closedNodes.add(node);
		}
	}

	/**
	 * addPathNode - adds a node to the path.
	 * 
	 * @param node the node.
	 */
	public void addPathNode(Node node) {
		if (pathNodes.size() == 0) {
			pathNodes.add(node);
		} else {
			pathNodes.add(node);
		}
	}

	/**
	 * removeBorderNode - removes the node from the borderNode list.
	 * 
	 * @param index the indexed node to remove.
	 */
	public void removeBorderNode(int index) {
		borderNodes.remove(index);
	}

	/**
	 * removeOpenNode - removes the node from the openNode list.
	 * 
	 * @param index the indexed node to remove.
	 */
	public void removeOpenNode(int index) {
		openNodes.remove(index);
	}

	/**
	 * removeOpenNode - removes the specified node from the openNode list.
	 * 
	 * @param node the node to be removed.
	 */
	public void removeOpenNode(Node node) {
		for (int i = 0; i < openNodes.size(); i++) {
			if (Node.isEqual(node, openNodes.get(i))) {
				openNodes.remove(i);
			}
		}
	}

	/**
	 * removeClosedNode - removes the closed node from the closedNode list.
	 * 
	 * @param index the indexed node to remove.
	 */
	public void removeClosedNode(int index) {
		closedNodes.remove(index);
	}

	/**
	 * removePath - removes the node from the pathNodes list.
	 * 
	 * @param index the indexed node to remove.
	 */
	public void removePath(int index) {
		pathNodes.remove(index);
	}

	/**
	 * borderContainsNode - checks to see if a node is already in the list.
	 * 
	 * @param node the node.
	 * @return boolean if the node was in the list.
	 */
	public boolean borderContainsNode(Node node) {

		for (int i = 0; i < borderNodes.size(); i++) {
			if (Node.isEqual(node, borderNodes.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * openContainsNode - checks to see if a node is already in the list.
	 * 
	 * @param node the node.
	 * @return boolean if the node was in the list.
	 */
	public boolean openContainsNode(Node node) {

		for (int i = 0; i < openNodes.size(); i++) {
			if (Node.isEqual(node, openNodes.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * closedCotainsNode - checks to see if a node is already in the list.
	 * 
	 * @param node the node.
	 * @return boolean if the node was in the list.
	 */
	public boolean closedContainsNode(Node node) {

		for (int i = 0; i < closedNodes.size(); i++) {
			if (Node.isEqual(node, closedNodes.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * searches for a node with the specified coordinates in the border list.
	 * 
	 * @param xCoord the x coordinate.
	 * @param yCoord the y coordinate.
	 * @return int the index.
	 */
	public int findBorderNode(int xCoord, int yCoord) {
		int index = -1;

		for (int i = 0; i < borderNodes.size(); i++) {
			if (borderNodes.get(i).getXCoordinate() == xCoord && borderNodes.get(i).getYCoordinate() == yCoord) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * searches for a node with the specified coordinates in the open list.
	 * 
	 * @param xCoord the x coordinate.
	 * @param yCoord the y coordinate.
	 * @return int the index.
	 */
	public int findOpenNode(int xCoord, int yCoord) {
		int index = -1;

		for (int i = 0; i < openNodes.size(); i++) {
			if (openNodes.get(i).getXCoordinate() == xCoord && openNodes.get(i).getYCoordinate() == yCoord) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * searches for a node with the specified coordinates in the closed list.
	 * 
	 * @param xCoord the x coordinate.
	 * @param yCoord the y coordinate.
	 * @return int the index.
	 */
	public int findClosedNode(int xCoord, int yCoord) {
		int index = -1;

		for (int i = 0; i < closedNodes.size(); i++) {
			if (closedNodes.get(i).getXCoordinate() == xCoord && closedNodes.get(i).getYCoordinate() == yCoord) {
				index = i;
				break;
			}
		}
		return index;
	}
}
