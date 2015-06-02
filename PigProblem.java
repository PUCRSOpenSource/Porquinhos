
public class PigProblem {
	
	public static final int VAGO = 0;
	public static final int PORCO = 1;
	public static final int GALINHA = 2;

	private int[][] board;
	private int l;
	private int p;
	private int g;
	private int answer;
	
	
	public PigProblem(int p, int g, int l) {
		this.p = p;
		this.g = g;
		this.l = l;
		this.board = new int[l][l];
		this.answer = 0;
	}
	
	public int getAnswer() {
		solve(p,g);
		return answer;
	}
	
	public void solve(int p, int g) {
		if(p == 0 && g == 0) {
			printaMatriz();
			answer++;
			return;
		}
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < l; j++) {
				if(podePorco(i, j, p)) {
					board[i][j] = PORCO;
					solve(p-1,g);
					board[i][j] = VAGO;
				} else if(podeGalinha(i, j, g)) {
					board[i][j] = GALINHA;
					solve(p,g-1);
					board[i][j] = VAGO;
				}
			}
		}
	}
	
	private boolean podePorco(int i, int j, int p) {
		if(p == 0) 
			return false;
		if(board[i][j] != VAGO) 
			return false;
		return true;
	}
		
	private boolean podeGalinha(int i, int j, int g) {
		if(g == 0) 
			return false;
		if(board[i][j] != VAGO) 
			return false;
		for (int n = 0; n < l; n++) {
			if(board[i][n] == PORCO || board[n][j] == PORCO) {
				return false;
			}
		}
		return true;
	}
	
		
	public void printaMatriz() {
		for(int i = 0; i < l; i++) {
			for(int j = 0; j < l; j++) {
				System.out.print(" " + board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	public static void main(String[] args) {
		PigProblem prob = new PigProblem(2,2,3);
		System.out.println(prob.getAnswer());

	}
	

}
