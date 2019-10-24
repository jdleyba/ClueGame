package clueGame;
//Will Hu, Josh Leyba
public class BoardCell {
	int row;
	int column;
	String initial;
	
	public BoardCell(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public boolean isDoorway() {
		if(this.initial.length() == 2) {
			return true;
		}
		return false;
	}

	public DoorDirection getDoorDirection() {
		
		if(this.initial.length() != 2) {
			return null;
		}		
		else {
			if(this.initial.charAt(1) == 'U') {
				return DoorDirection.UP;
			}
			if(this.initial.charAt(1) == 'R') {
					return DoorDirection.RIGHT;
			}
			if(this.initial.charAt(1) == 'L') {
					return DoorDirection.LEFT;
			}
			if(this.initial.charAt(1) == 'D') {
					return DoorDirection.DOWN;
			}
		}
		return null;
		
	}

	public Object getInitial() {
		return this.initial;
	}
}

