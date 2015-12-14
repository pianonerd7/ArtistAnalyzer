
public class Node {

	private int row;
	private int column;
	
	public Node (int firstIndex, int secondIndex) {
		this.row = firstIndex;
		this.column = secondIndex;
	}
	
	public int getFirstIndex() {
		return row;
	}
	
	public int getSecondIndex() {
		return column;
	}
	
	@Override
	public String toString() {
		return "(firstIndex: " + row + ", secondIndex: " + column + ")";
	}
}
