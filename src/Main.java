import java.util.Scanner;

public class Main {

    public static final Scanner sc = new Scanner(System.in);
    public static char[][] grid = new char[3][3];
    public static int move = 0;
    public static boolean flag = true;
    public static void main(String[] args) {
        initializationGrid();
        play();
    }

    public static void initializationGrid(){
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                grid[i][j] = ' ';
            }
        }
    }

    public static void check(){
        boolean xWins = false;
        boolean oWins = false;

        //Перевірка по діагоналі
        for (int i = 0; i < grid.length; i++) {
            if(grid[i][0] == 'X' && grid[i][1] == 'X' && grid[i][2] == 'X') xWins = true;
            if(grid[i][0] == 'O' && grid[i][1] == 'O' && grid[i][2] == 'O') oWins = true;
        }

        //Перевірка по вертикалі
        for(int j = 0; j < grid.length; j++) {
            if (grid[0][j] == 'X' && grid[1][j] == 'X' && grid[2][j] == 'X') xWins = true;
            if (grid[0][j] == 'O' && grid[1][j] == 'O' && grid[2][j] == 'O') oWins = true;
        }

        // Перевірка по головній діагоналі
        if(grid[0][0] == 'X' && grid[1][1] == 'X' && grid[2][2] == 'X') {
            xWins = true;
        } else if (grid[0][0] == 'O' && grid[1][1] == 'O' && grid[2][2] == 'O') {
            oWins = true;
        }

        // Перевірка по побічній діагоналі
        if(grid[0][2] == 'X' && grid[1][1] == 'X' && grid[2][0] == 'X'){
            xWins = true;
        } else if (grid[0][2] == 'O' && grid[1][1] == 'O' && grid[2][0] == 'O') {
            oWins = true;
        }

        if(oWins && xWins || Math.abs(countOccurrences('X') - countOccurrences('O')) > 1) {
            System.out.println("Impossible");
            flag = false;
        } else if (xWins) {
            System.out.println("X wins");
            flag = false;
        } else if (oWins) {
            System.out.println("O wins");
            flag = false;
        } else if (countOccurrences(' ') == 0) {
            System.out.println("Draw");
            flag = false;
        }
    }

    public static int countOccurrences(char symbol){
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == symbol) count++;
            }
        }
        return count;
    }
    public static void showGrid(){
        System.out.println("---------");
        for(int i = 0; i < grid.length; i++) {
            System.out.print("| ");
            for(int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]+ " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    public static void userInput(){
        while(true){
            if (sc.hasNextInt()) {
                int one = sc.nextInt() - 1;
                if (sc.hasNextInt()) {
                    int two = sc.nextInt() - 1;
                    if (one < grid.length  && one >= 0 && two < grid.length && two >= 0) {
                        if (grid[one][two] == ' ') {
                            if (move % 2 == 0){
                                grid[one][two] = 'X';
                            }else{
                                grid[one][two] = 'O';
                            }
                            move++;
                            return;
                        } else {
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                    } else {
                        System.out.println("Coordinates should be from 1 to 3!");
                    }
                } else {
                    System.out.println("You should enter numbers!");
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }

    }

    public static void play() {
        showGrid();
        while (flag){
            userInput();
            showGrid();
            check();
        }
    }

}
