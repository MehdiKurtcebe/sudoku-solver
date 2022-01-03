public class Sudoku {

	private final int GRID_SIZE = 9;

	private int[][] board;

	public Sudoku(int[][] board) {
		this.board = board;
	}

	public int[][] getBoard() {
		return board;
	}

	// Returns false if given board is in invalid format and does not set.
	// Otherwise, returns true and sets the value.
	public boolean setBoard(int[][] board) {
		if (board.length != GRID_SIZE) return false;
		for (int row = 0; row < GRID_SIZE; row++) {
			if (board[row].length != GRID_SIZE) return false;
			for (int col = 0; col < GRID_SIZE; col++) {
				if (board[row][col] < 0 || board[row][col] > GRID_SIZE) return false;
			}
		}
		this.board = board;
		return true;
	}

	// Solves the board and returns true.
	// Returns false if the board is not solvable.
	public boolean solve() {
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				if (board[row][col] == 0) {
					for (int num = 1; num <= GRID_SIZE; num++) {
						if (isValidPlacement(num, row, col)) {
							board[row][col] = num;
							if (solve()) return true;
							else board[row][col] = 0;
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	// Sets the given point to given point and returns true.
	// Returns false if given parameters are invalid and does not set the number.
	public boolean setPoint(int number, int row, int col) {
		if (isValidPoint(row) && isValidPoint(col) && (isValidNumber(number) || number == 0)) {
			board[row][col] = number;
			return true;
		}
		return false;
	}

	// Returns true if it is valid to put given number to given point, false otherwise.
	public boolean isValidPlacement(int number, int row, int col) {
		return !isInRow(number, row) && !isInCol(number, col) && !isInBox(number, row, col);
	}

	// Returns true if given number exists in given row, false otherwise.
	public boolean isInRow(int number, int row) {
		if (!isValidPoint(row) || !isValidNumber(number))
			return false;
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[row][i] == number) return true;
		}
		return false;
	}

	// Returns true if given number exists in given column, false otherwise.
	public boolean isInCol(int number, int col) {
		if (!isValidPoint(col) || !isValidNumber(number))
			return false;
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[i][col] == number) return true;
		}
		return false;
	}

	// Returns true if given number exists in the box that given point belongs, false otherwise.
	public boolean isInBox(int number, int row, int col) {
		if (!isValidNumber(number) || !isValidPoint(row) || !isValidPoint(col))
			return false;

		int localBoxRow = row - (row % 3);
		int localBoxCol = col - (col % 3);
		for (int i = localBoxRow; i < localBoxRow + 3; i++) {
			for (int j = localBoxCol; j < localBoxCol + 3; j++) {
				if (board[i][j] == number) return true;
			}
		}
		return false;
	}

	private boolean isValidPoint(int rowOrCol) {
		return rowOrCol >= 0 && rowOrCol < GRID_SIZE;
	}

	private boolean isValidNumber(int number) {
		return number > 0 && number <= GRID_SIZE;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int row = 0; row < GRID_SIZE; row++) {
			if (row % 3 == 0)
				s.append("-------------------------\n");
			for (int col = 0; col < GRID_SIZE; col++) {
				if (col % 3 == 0)
					s.append("| ");
				s.append(board[row][col] != 0 ? board[row][col] : " ").append(" ");
			}
			s.append("|\n");
		}
		s.append("-------------------------\n");
		return s.toString();
	}
}
