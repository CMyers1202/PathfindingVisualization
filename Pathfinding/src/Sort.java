import java.util.ArrayList;

/**
 * Sort - implements a bubble sort, inefficient but it gets the job done.
 * 
 * @author Chase Myers
 *
 */
public class Sort {

	private boolean lowToHigh;
	private boolean highToLow;

	/**
	 * constructor - defaults low to high.
	 */
	public Sort() {
		lowToHigh = true;
		highToLow = false;
	}

	/**
	 * bubbleSort - sorts the list.
	 * 
	 * @param nodes the nodes.
	 */
	public void bubbleSort(int[] nodes) {
		int keepGoing = -1;
		int temp;

		while (keepGoing != 0) {
			keepGoing = 0;

			if (lowToHigh) {
				for (int i = 0; i < nodes.length - 1; i++) {
					if (nodes[i] > nodes[i + 1]) {
						temp = nodes[i];
						nodes[i] = nodes[i + 1];
						nodes[i + 1] = temp;
						keepGoing = 1;
					}
				}
			} else if (highToLow) {
				for (int i = 0; i < nodes.length - 1; i++) {
					if (nodes[i] < nodes[i + 1]) {
						temp = nodes[i];
						nodes[i] = nodes[i + 1];
						nodes[i + 1] = temp;
						keepGoing = 1;
					}
				}
			}
		}
	}

	/**
	 * bubbleSort - the bubbleSort.
	 * 
	 * @param nodes the nodes provided.
	 */
	public void bubbleSort(ArrayList<Node> nodes) {
		int keepGoing = -1;
		Node temp;

		while (keepGoing != 0) {
			keepGoing = 0;

			if (lowToHigh) {
				for (int i = 0; i < nodes.size() - 1; i++) {
					if (nodes.get(i).getFScore() > nodes.get(i + 1).getFScore()) {
						temp = nodes.get(i);
						nodes.remove(i);
						nodes.add(i + 1, temp);
						keepGoing = 1;
					}
				}
			} else if (highToLow) {
				for (int i = 0; i < nodes.size() - 1; i++) {
					if (nodes.get(i).getFScore() < nodes.get(i + 1).getFScore()) {
						temp = nodes.get(i);
						nodes.remove(i);
						nodes.add(i + 1, temp);
						keepGoing = 1;
					}
				}
			}
		}
	}
}
