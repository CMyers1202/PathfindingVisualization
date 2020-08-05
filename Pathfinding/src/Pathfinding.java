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

	private Sort sort;

	private int size;
	private int diagonalCost;
	private double kValue;

	private boolean isRunning;
	private boolean isComplete;
	private boolean noPathFound;
	private boolean isRunningPath;

	/**
	 * constructor - the constructor.
	 * 
	 * @param size the size of a node.
	 */
	public Pathfinding(int size) {

		this.size = size;
		kValue = Math.PI / 2;
		diagonalCost = (int) (Math.sqrt(2 * (Math.pow(size, 2))));

		sort = new Sort();

		isRunning = false;
		isComplete = false;
		isRunningPath = true;

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
		kValue = Math.PI / 2;
		diagonalCost = (int) (Math.sqrt(2 * (Math.pow(size, 2))));

		sort = new Sort();

		isRunning = false;
		isComplete = false;
		isRunningPath = true;

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
		kValue = Math.PI / 2;
		diagonalCost = (int) (Math.sqrt(2 * (Math.pow(size, 2))));

		sort = new Sort();

		isRunning = false;
		isComplete = false;
		isRunningPath = true;

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

		System.out.println("made it to start pathfinding");
		findPath(startNode);

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
	 * isDiagonal - returns boolean variable isRunningPath.
	 * 
	 * @return boolean isRunningPath.
	 */
	public boolean isDiagonal() {
		return isRunningPath;
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

	public Node getOpenNode(int xCoord, int yCoord) {
		for (int i = 0; i < openNodes.size(); i++) {
			if (openNodes.get(i).getXCoordinate() == xCoord && openNodes.get(i).getYCoordinate() == yCoord) {
				return openNodes.get(i);
			}
		}
		return null; // Specified node not found.
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

	/**
	 * calculateValues - calculates the g, f, and h score of a node.
	 * 
	 * @param xCoord  the x coordinate of a potential node.
	 * @param yCoord  the y coordinate of a potential node.
	 * @param current the potential node.
	 * @param parent  the parent of the potential node.
	 */
	public void calculateValues(int xCoord, int yCoord, Node current, Node parent) {
		if (xCoord < 0 || yCoord < 0 || xCoord > frame.getWidth() || yCoord > frame.getWidth()) {
			return; // this if statement checks if the coordinates are "out of bounds."
		}

		if (findBorderNode(xCoord, yCoord) != -1 || findClosedNode(xCoord, yCoord) != -1
				|| findOpenNode(xCoord, yCoord) != -1) {
			return; // Checks if the node is a border, closed, or open node, if so don't make it an
					// open node.
		}

		// create a node that will be placed into the open list.
		current = new Node(xCoord, yCoord);
		current.setParent(parent);

		// Calculating G score from parent -> current node.
		int xGScore = current.getXCoordinate() - parent.getXCoordinate();
		int yGScore = current.getYCoordinate() - parent.getYCoordinate();
		int gScore = parent.getGScore();

		if (xGScore != 0 && yGScore != 0) {
			gScore += diagonalCost;
		} else {
			gScore += size;
		}
		current.setGScore(gScore);

		// Calculating H score.
		int xHScore = Math.abs(endNode.getXCoordinate() - current.getXCoordinate());
		int yHScore = Math.abs(endNode.getYCoordinate() - current.getYCoordinate());
		int hScore = xHScore + yHScore;
		current.setHScore(hScore);

		// Calculating F Score.
		int fScore = gScore + hScore;
		current.setFScore(fScore);

		addOpenNode(current);
	}

	/**
	 * findPath - finds the a* algorithm path.
	 * 
	 * @param parent the parent node.
	 */
	public void findPath(Node parent) {
		Node open = null;

		if (isRunningPath) {

			// Adds nodes to open list.
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (i == 1 && j == 1) {
						continue;
					}
					int xCoord = (parent.getXCoordinate() - size) + (size * i);
					int yCoord = (parent.getYCoordinate() - size) + (size * j);

					// Check to see if there are walls blocking the path.
					int wallX = parent.getXCoordinate() + (xCoord - parent.getXCoordinate());
					int wallY = parent.getYCoordinate() + (yCoord - parent.getYCoordinate());

					// I would prefer if corners were not cut, therefore they are disabled.
					if (findBorderNode(wallX, parent.getYCoordinate()) != -1
							|| findBorderNode(parent.getXCoordinate(), wallY) != -1) {
						continue; // If node is found with the wall coord (x or y) and parent coord (x or y).
					}

					calculateValues(wallX, wallY, open, parent);
				}
			}
		} else {
			for (int i = 0; i < 4; i++) {
				// use cosine and sine to get circle of points around parent node.
				int xCoord = (int) Math.round(parent.getXCoordinate() + (-size * Math.cos(kValue * i)));
				int yCoord = (int) Math.round(parent.getYCoordinate() + (-size * Math.cos(kValue * i)));

				// calculate the values of the surrounding nodes and add to open list if
				// possible.
				calculateValues(xCoord, yCoord, open, parent);
			}
		}

		parent = lowestFCost();

		if (parent == null) {
			isRunning = false;
			noPathFound = true;

			frame.repaint();
			return;
		}

		if (Node.isEqual(parent, endNode)) {
			endNode.setParent(parent.getParent());

			connectPathNodes();
			isRunning = false;
			isComplete = true;
			frame.repaint();
			return;
		}

		// Remove and add parent node from open to closed list.
		removeOpenNode(parent);
		addClosedNode(parent);

		// Corrects for shortest path, checks all adjacent nodes and compares scores.
		if (isRunningPath) {
			// Adds nodes to open list.
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (i == 1 && j == 1) {
						continue;
					}

					int xCoord = (parent.getXCoordinate() - size) + (size * i);
					int yCoord = (parent.getYCoordinate() - size) + (size * j);
					Node check = getOpenNode(xCoord, yCoord);

					// if the node was found in open nodes calculate new values.
					if (check != null) {
						int newX = parent.getXCoordinate() - check.getXCoordinate();
						int newY = parent.getYCoordinate() - check.getYCoordinate();
						int newG = parent.getGScore();

						if (newX != 0 && newY != 0) {
							newG += diagonalCost;
						} else {
							newG += size;
						}

						// If new g is less than old g update the values.
						if (newG < check.getGScore()) {
							int findNode = findOpenNode(xCoord, yCoord);
							int newFScore = openNodes.get(findNode).getGScore() + openNodes.get(findNode).getHScore();

							openNodes.get(findNode).setParent(parent);
							openNodes.get(findNode).setGScore(newG);
							openNodes.get(findNode).setFScore(newFScore);
						}
					}

				}
			}

		}
		findPath(parent);
	}

	/**
	 * conncetPathNodes - connects the path nodes.
	 */
	private void connectPathNodes() {
		if (getPathNodeList().size() == 0) {
			Node nextNode = endNode.getParent();

			// runs through the nodes back to the start node.
			while (!Node.isEqual(nextNode, startNode)) {
				addPathNode(nextNode);

				for (int i = 0; i < getClosedNodeList().size(); i++) {
					Node current = getClosedNodeList().get(i);

					// If next node is = to current node go to next parent.
					if (Node.isEqual(current, nextNode)) {
						nextNode = current.getParent();
						break;
					}
				}
			}
			reverseList(getPathNodeList());
		}

	}

	/**
	 * reverseList - reverses the list in place.
	 * 
	 * @param toReverse the list to reverse.
	 */
	private void reverseList(ArrayList<Node> toReverse) {
		int next = toReverse.size() - 1;

		for (int i = 0; i < next; i++) {
			Node temp = toReverse.get(i);
			toReverse.remove(i);
			toReverse.add(i, toReverse.get(next - 1));
			toReverse.remove(next);
			toReverse.add(next, temp);
		}
	}

	/**
	 * finds the lowest f cost in the open nodes.
	 * 
	 * @return Node the lowest f cost node.
	 */
	private Node lowestFCost() {
		if (openNodes.size() > 0) {
			sort.bubbleSort(openNodes);
			return openNodes.get(0);
		}
		return null;
	}
}
