
/**
 * Sets up the game if intermediate difficulty is picked
 *
 * @author Brianna Davis, Zach Giannuzzi, Abdul Samad, 
 * Eric Sauer, Daniel Senecal
 * @version 4/30/2018
 */
public class Card2
{
    // [row][column]  [down][over]
    protected int[][] board; // 0 = blank. 1 = redlaser. 2 = yellow.
    //3 = purpleTarget. 4 = purpleRotatable. 
    // 5 = purple1. 6 = purple2. 7 = purple3.
    protected int[][] positions; // 0 = down. 1 = right. 2 = up. 3 =
    //left.
    protected int[][] xCoords;
    protected int[][] yCoords;
    /**
     * Constructs an object for Card1 and sets the positions in 
     * the arrays to what type of piece is located where
     */
    public Card2(){
        board = new int[5][5];
        positions = new int[5][5];
        xCoords = new int[5][5];
        yCoords = new int[5][5];
        setXCoords();
        setYCoords();
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
    
    //Resets Card1 to original orientation
    /**
     * Resets the board to the default positions
     */
    public void reset(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                board[i][j] = 0;
                positions[i][j] = 0;
                //targets[i][j] = false;
            }
        }
        board[0][4] = 1;
        board[4][3] = 2;
        board[2][0] = 3; //purple with target
        board[4][0] = 4;
        positions[0][4] = 0;
        positions[4][3] = 1;
        //starts at down but position can be changed
        positions[2][0] = 1; 
        //starts at down but position can be changed
        positions[4][0] = 1; 
    }
    
    /**
     * Determines if the game has been solved or not
     * 
     * @return True if the positions of the pieces are 
     * correct and the board has been solved. Otherwise it 
     * returns false
     */
    public boolean solvedGame(){
        if (board[0][0] != 0 && board[2][4] != 0 && board[4][4] != 
        0){
            if (positions[0][0] == 0 && positions[2][0] == 1 && 
            positions[4][0] == 1 && positions[2][4] == 3 &&
            positions[4][4] == 2){
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
    
    /**
     * Sets the x coordinates for all the pieces.
     */
    public void setXCoords(){
        xCoords[0][0] = 171;
        xCoords[1][0] = 171;
        xCoords[2][0] = 171;
        xCoords[3][0] = 171;
        xCoords[4][0] = 171;
        xCoords[0][1] = 318;
        xCoords[1][1] = 318;
        xCoords[2][1] = 318;
        xCoords[3][1] = 318;
        xCoords[4][1] = 318;
        xCoords[0][2] = 468;
        xCoords[1][2] = 468;
        xCoords[2][2] = 468;
        xCoords[3][2] = 468;
        xCoords[4][2] = 468;
        xCoords[0][3] = 615;
        xCoords[1][3] = 615;
        xCoords[2][3] = 615;
        xCoords[3][3] = 615;
        xCoords[4][3] = 615;
        xCoords[0][4] = 767;
        xCoords[1][4] = 767;
        xCoords[2][4] = 767;
        xCoords[3][4] = 767;
        xCoords[4][4] = 767;
    }
    
    /**
     * Sets the y coordinates for all the pieces.
     */
    public void setYCoords(){
        xCoords[0][0] = 150;
        xCoords[0][1] = 150;
        xCoords[0][2] = 150;
        xCoords[0][3] = 150;
        xCoords[0][4] = 150;
        xCoords[1][0] = 300;
        xCoords[1][1] = 300;
        xCoords[1][2] = 300;
        xCoords[1][3] = 300;
        xCoords[1][4] = 300;
        xCoords[2][0] = 448;
        xCoords[2][1] = 448;
        xCoords[2][2] = 448;
        xCoords[2][3] = 448;
        xCoords[2][4] = 448;
        xCoords[3][0] = 592;
        xCoords[3][1] = 592;
        xCoords[3][2] = 592;
        xCoords[3][3] = 592;
        xCoords[3][4] = 592;
        xCoords[4][0] = 741;
        xCoords[4][1] = 741;
        xCoords[4][2] = 741;
        xCoords[4][3] = 741;
        xCoords[4][4] = 741;
    }
}
