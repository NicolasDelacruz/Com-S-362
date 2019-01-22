// Java program to fill a subarray of given array 
import java.util.Arrays; 

// Java program to read values
import java.util.Scanner;

public class TicTacToe {

    /*
    * The grid variable is to store the grid information of the current game    
    */
    public char[][] grid;

    /*
    * The turn variable is to indicate the current players turn of the game
    */
    public int current_turn;

    /*
    * The game_over variable is to inidicate if the game is over
    */
    public boolean game_over;

    public int x_move;

    public int y_move;


    public TicTacToe(){
        grid = new char[3][3];
        current_turn = 1;
        game_over = false;
        x_move = 0;
        y_move = 0;
    }

    public void check_win(){
        if(check_diagonal_win(get_player_symb())|| check_horizontal_win(get_player_symb()) || check_vertical_win(get_player_symb())){
            game_over = true;
        }

        game_over = false;
    }

    public boolean check_diagonal_win(char given_player){
        int counter = 0;

        for(int i = 0; i < grid.length; ++i){
            if(grid[i][i] == given_player){
                counter++;
            }
        }
        if(counter >= 3){
            return true;
        }

        return false;
    }

    public boolean check_horizontal_win(char given_player){
        int counter = 0;

        for(int i = 0; i < grid.length; ++i){
            for(int j = 0; j < grid[i].length; ++j){
                if(grid[i][j] == given_player){
                    counter++;
                }
            }
            if(counter >= 3){
                return true;
            }
            counter = 0;
        }

        return false;
    }

    public boolean check_vertical_win(char given_player){
        int counter = 0;

        for(int i = 0; i < grid.length; ++i){
            for(int j = 0; j < grid[i].length; ++j){
                if(grid[j][i] == given_player){
                    counter++;
                }
            }
            if(counter >= 3){
                return true;
            }
            counter = 0;
        }
        return false;
    }

    public void clear_screen() {
        System.out.print("\033[H\033[2J");
    }

    public void display_end_game_message() {
        clear_screen();

        if(get_current_turn() == 2) {
            System.out.println("Player 1 won!");
        }
        else {
            System.out.println("Player 2 won!");
        }
    }

    public void display_grid() {
        for(int i = 0; i < grid.length; ++i){
            for(int j = 0; j < grid[i].length; ++j){
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    public void init_grid(){
        Arrays.fill(grid, '*');
        display_grid();
    }

    public void invalid_move_display() {
        System.out.println("Invalid move entered, try again.");
    }

    public void read_user_move() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
    
        System.out.println("Enter x-coordinate: ");
        x_move = reader.nextInt(); // Scans the next token of the input as an int.
    
        System.out.println("Enter y-coordinate: ");
        y_move = reader.nextInt(); // Scans the next token of the input as an int.

        //once finished
        reader.close();
    }

    public void refresh_display(){
        clear_screen();
        display_grid();
    }

    public int get_current_turn(){
        return current_turn;
    }

    public boolean get_game_over() {
        return game_over;
    }

    public void set_current_turn(int new_turn) {
        current_turn = new_turn;
    }

    public void set_move(){
        read_user_move();

        while(!valid_move(x_move, y_move)){
            invalid_move_display();
            read_user_move();
        }

        if(get_current_turn() == 1){
            grid[y_move][x_move] = 'X';
            set_current_turn(2);
        }
        else{
            grid[y_move][x_move] = 'O';
            set_current_turn(1);
        }

        check_win();
    }

    public boolean valid_move(int given_x, int given_y){
        return true;
    }

    public char get_player_symb(){
        if(get_current_turn() == 1){
            return 'X';
        }
        else{
            return 'O';
        }
    }

    public void play_game(){

        init_grid();

        while(!get_game_over()){
            set_move();
            refresh_display();
        }

        display_end_game_message();
    }

    public static void main() {
        TicTacToe t = new TicTacToe();
        t.play_game();
    }
}