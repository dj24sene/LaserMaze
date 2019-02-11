
/**
 * Sets the pieces for the board if beginner difficulty is picked
 *
 * @author Brianna Davis, Zach Giannuzzi, Abdul Samad, Eric Sauer,
 * Daniel Senecal
 * @version 4/30/2018
 */
public class Card1
{
    // [row][column]  [down][over]
    protected int[][] board; // 0 = blank. 1 = redlaser. 2 = yellow.
    //3 = purpleRotatable. 4 = purple1. 5 = purple2 (target piece).
    protected int[][] positions; // 1 = down. 2 = right. 3 = up. 0 =
    //left.
    protected boolean[][] targets; //might not need this
    /**
     * Constructs an object for Card1 and sets the positions in
     * the arrays to what type of piece is located where
     * 
     */
    public Card1(){
        board = new int[5][5];
        positions = new int[5][5];
        targets = new boolean[5][5]; 
        reset();
    }
    
    //Accessor Methods
    /**
     * Allows access to the board variable
     * 
     * @return Returns the int matrix board
     */
    public int[][] getBoard(){
        return board;
    }
    /**
     * Allows access to the positions variable
     * 
     * @return Returns the int matrix positions
     */
    public int[][] getPositions(){
        return positions;
    }
    /**
     * Allows access to the target variable
     * 
     * @return Returns the int matrix target
     */
    public boolean[][] target(){
        return targets;
    }
    
    //Mutator Methods
    /**
     * Allows mutations to be made to the board variable
     * 
     * @param piece determines what type of piece will be set
     * @param row the row on board that will be set
     * @param col the column on the board that will be set 
     */
    public void setBoard(int piece, int row, int col){
        board[row][col] = piece;
    }
    /**
     * Allows mutations to be made to the positions variable
     * 
     * @param pos determines what direction the piece will be set to
     * @param row the row on board that will be set
     * @param col the column on the board that will be set 
     */
    public void setPositions(int pos, int row, int col){
        positions[row][col] = pos;
    }
    /**
     * Allows mutations to be made to the target variable
     * 
     * @param pos determines where the target piece will be set to
     * @param row the row on board that will be set
     * @param col the column on the board that will be set 
     */
    public void setTargets(boolean t, int row, int col){
        targets[row][col] = t;
    }
    
    //Resets Card1 to original orientation
    /**
     * Resets the board to the default positions
     */
    public void reset(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                board[i][j] = 0;
                positions[i][j] = 0;
                targets[i][j] = false;
            }
        }
        board[0][1] = 1;
        board[1][0] = 2;
        board[4][0] = 3;
        positions[0][1] = 1;
        positions[1][0] = 0;
        //starts at down but position can be changed
        positions[4][0] = 1; 
    }
    /**
     * Determines if the game has been solved or not
     * 
     * @return True if the positions of the pieces are correct and
     * the board has been solved. Otherwise it returns false
     */
    public boolean solvedGame(){
        if ((board[0][0] == 5) && (board[4][1] == 4)){
            if (positions[0][0] == 1 && positions[4][1]
            == 2 && positions[4][0] == 1){
                return true;
            }
        }
        return false;
    }
    
    /**
     *  Gets the row of a piece on the board.
     *  
     *  @param Piece is the type of piece to get the row of.
     *  @return The row that the piece is in
     */
    public int getRow(int piece){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (board[i][j] == piece){
                    return i;
                }
            }
        }
        return -1;
    }
    
    /**
     *  Gets the column of a piece on the board.
     *  
     *  @param Piece is the type of piece to get the row of.
     *  @return The column that the piece is in
     */
    public int getCol(int piece){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (board[i][j] == piece){
                    return j;
                }
            }
        }
        return -1;
    }
    /**
     *  Gets the direction of a piece on the board.
     *  
     *  @param Piece is the type of piece to get the direction of.
     *  @return The direction that the piece is facing
     */
    public int getPos(int piece){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (board[i][j] == piece){
                    return positions[i][j];
                }
            }
        }
        return -1;
    }
    /**
     *  Determines is the board already has that type of piece.
     *  
     *  @param Piece is the type of piece to check for.
     *  @return True if the board already has that piece.
     */
    public boolean alreadyHas(int piece){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (board[i][j] == piece){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Removes a piece from the board
     * 
     * @param Piece is the type of piece to remove from the board
     * @return Nothing
     */
    public void remove(int piece){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (board[i][j] == piece){
                    board[i][j] = 0;
                }
            }
        }
    }
}
