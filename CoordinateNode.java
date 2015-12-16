
public class CoordinateNode implements Comparable<CoordinateNode> {

	private int row;
	private int column;
	
	public CoordinateNode (int row, int column) {
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
	public int compareTo(CoordinateNode node) {
		
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
