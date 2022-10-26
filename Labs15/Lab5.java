package Labs15;

import java.util.Scanner;

public class Lab5 {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        char[][] board = new char[][] {{'_','_','_'}, {'_','_','_'}, {'_','_','_'}};

        int currentPlayer = 1;

        boolean endGame = false;

        while(!endGame){

            int row;
            int column;
            boolean validTurn = false;

            while(!validTurn){
                System.out.println("Which row would you like to place your piece?");
                System.out.println("0, 1, 2");
                row = sc.nextInt();

                System.out.println("Which column would you like to place your piece?");
                System.out.println("0, 1, 2");
                column = sc.nextInt();

                if(validMove(board, row, column)){
                    if(currentPlayer == 1){
                        board[row][column] = 'X';
                    } else {
                        board[row][column] = 'O';
                    }
                    validTurn = true;
                } else {
                    System.out.println("Spot Taken! Try Again!");
                    validTurn = false;
                }
            }
            
            if(tieTest(board)){
                System.out.println("IT'S A TIE!");
                break;
            }
            
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }

            if(winCondition(board, currentPlayer)){
                endGame = true;
            } else {
                currentPlayer = changePlayer(currentPlayer);

                System.out.println("Player " + currentPlayer + " turn");
            }
        }

        sc.close();
    }

    public static boolean winCondition(char[][] x, int currentPlayer){
        for(int i = 0; i < 3; i++) {
            if(x[i][0] != '_' && x[i][0] == x[i][1] && x[i][1] == x[i][2]) {
                System.out.println("Player " + currentPlayer + " is the winner!");
                return true;
            }
        }

        for(int i = 0; i < 3; i++) {
            if(x[0][i] != '_' && x[0][i] == x[1][i] && x[1][i] == x[2][i]) {
                System.out.println("Player " + currentPlayer + " is the winner!");
                return true;
            }
        }

        if(x[0][0] != '_' && x[0][0] == x[1][1] && x[1][1] == x[2][2]) {
            System.out.println("Player " + currentPlayer + " is the winner!");
            return true;
        }
        if(x[2][0] != '_' && x[2][0] == x[1][1] && x[1][1] ==  x[0][2]) {
            System.out.println("Player " + currentPlayer + " is the winner!");
            return true;
        }

        return false;
    }

    public static boolean validMove(char[][] x, int row, int column){
        if(x[row][column] == '_'){
            return true;
        } else {
            return false;
        }
    }

    public static boolean tieTest(char[][] x){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(x[i][j] == '_'){
                    return false;
                }
            }
        }
        return true;
    }

    public static int changePlayer(int x){
        return (x == 1) ? 2 : 1;
    }

}