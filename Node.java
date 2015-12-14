
public class Node implements Comparable<Node> {

	private int row;
	private int column;
	
	public Node (int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	@Override
	public int compareTo(Node node) {
		
		if (node.getRow() == this.getRow()) {
			//We know that if two artists appear in the same row, they must have different column 
			return node.getColumn() - this.getColumn();
		}
		return node.getRow() - this.getRow();
	}
	
	@Override
	public String toString() {
		return "(firstIndex: " + row + ", secondIndex: " + column + ")";
	}

}
