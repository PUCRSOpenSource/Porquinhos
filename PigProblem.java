//Daniel Amarante e Diego Jornada

import java.util.Scanner;

public class PigProblem {
	
	public static final int VAGO = 0;
	public static final int PORCO = 1;
	public static final int GALINHA = 2;

	private int[][] board;
	private int l;
	private int porcos;
	private int galinhas;
	
	public PigProblem(int l, int p, int g) {
		this.porcos = p;
		this.galinhas = g;
		this.l = l;
		this.board = new int[l][l];
	}
	
	public int getAnswer() {
		return solve(porcos,galinhas,0);
	}
	
	public int solve(int p, int g, int pos) {
		if(p == 0 && g == 0) {
			// printaMatriz();
			return 1;
		}
        if (p == 0 && g == galinhas) {
        	pos = 0;
        }
        int ans = 0;
        for(int n = pos; n < (l * l); n++) {
        	int i = n / l;
        	int j = n % l;
        	if(podePorco(i, j, p)) {
        		int newpos = (i * l + j) + 1;
				board[i][j] = PORCO;
				ans += solve(p-1,g, newpos);
				board[i][j] = VAGO;
			} else if(podeGalinha(i, j, g)) {
				int newpos = (i * l + j) + 1;
				board[i][j] = GALINHA;
				ans += solve(p,g-1,newpos);
				board[i][j] = VAGO;
			}

        }
        return ans;
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
		for(int n = i, m = j; n >= 0 && m >= 0; n--, m--) {
			if(board[n][m] == PORCO)
				return false; 
		}
		for(int n = i, m = j; n < l && m >= 0; n++, m--) {
			if(board[n][m] == PORCO)
				return false; 
		}
		for(int n = i, m = j; n >= 0 && m < l; n--, m++) {
			if(board[n][m] == PORCO)
				return false; 
		}
		for(int n = i, m = j; n < l && m < l; n++, m++) {
			if(board[n][m] == PORCO)
				return false; 
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
        Scanner scan = new Scanner(System.in);
        while(true) {
        	String oi = scan.nextLine();
        	String[] numbersString = oi.split(" ");
        	int[] numbers = new int[numbersString.length];
        	for (int i = 0; i < numbersString.length; i++) {
        	   	numbers[i] = Integer.parseInt(numbersString[i]);
        	}
        	PigProblem prob = new PigProblem(numbers[0],numbers[1],numbers[2]);
			System.out.println(prob.getAnswer());

        }

	}
	

}
