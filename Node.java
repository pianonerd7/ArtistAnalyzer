
public class Node {

	private int firstIndex;
	private int secondIndex;
	
	public Node (int firstIndex, int secondIndex) {
		this.firstIndex = firstIndex;
		this.secondIndex = secondIndex;
	}
	
	public int getFirstIndex() {
		return firstIndex;
	}
	
	public int getSecondIndex() {
		return secondIndex;
	}
	
	@Override
	public String toString() {
		return "firstIndex: " + firstIndex + "secondIndex: " + secondIndex;
	}
}
