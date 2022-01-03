public class SudokuDemo {

	public static void main(String[] args) {
		int[][] board = {
				{0, 0, 6, 0, 1, 0, 0, 7, 8},
				{0, 2, 0, 4, 7, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 8, 4, 0, 0},
				{0, 1, 7, 0, 0, 9, 0, 0, 0},
				{0, 0, 4, 0, 0, 0, 3, 0, 0},
				{0, 0, 0, 8, 0, 0, 7, 5, 0},
				{0, 0, 9, 2, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 8, 5, 0, 4, 0},
				{3, 7, 0, 0, 9, 0, 2, 0, 0}
		};

		Sudoku s = new Sudoku(board);
		System.out.println("The table:\n");
		System.out.println(s);
		if (s.solve()) {
			System.out.println("Puzzle solved!\n");
			System.out.println(s);
		} else
			System.out.println("The table is not solvable. Try another.");
	}
}
