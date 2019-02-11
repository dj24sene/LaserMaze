import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import javax.sound.sampled.*;
import java.nio.file.*;
import java.io.*;
////////////////////////////////////////////////////////////////////////////////
/**
 * Sets up the game if intermediate difficulty is picked
 *
 * @author Brianna Davis, Zach Giannuzzi, Abdul Samad, Eric Sauer,
 * Daniel Senecal
 * @version 4/30/2018
 */
public class LaserMaze2 extends JPanel implements MouseListener,
MouseMotionListener
{
    private Image boardPic1, boardPic2, purplePic, purpleRotatable,
    redPicLeft, redPicDown;
    private Image yellowHorizontal, purpleTarget, youWon, 
    instructions, info;
    private Image purpleQuestionMovable1, purpleQuestionMovable2;
    private Image purpleQuestionMovable3, purpleQuestionMovable4, 
    purpleQuestionMovable5;
    private int clickedPurple1, clickedPurple2, clickedPurple3, 
    clickedPurple4, clickedPurpleTarget;
    protected boolean beginner;
    protected int p1x, p1y, p1xOffset, p1yOffset;
    protected int p2x, p2y, p2xOffset, p2yOffset;
    protected int p3x, p3y, p3xOffset, p3yOffset;
    protected static final int SIZE = 125;
    protected boolean draggingP1, draggingP2, draggingP3;
    protected boolean wasDraggedP1, wasDraggedP2, wasDraggedP3;
    protected boolean wasRightClickedP1, wasRightClickedP2,
    wasRightClickedP3;
    protected boolean laserRunning = false;
    //start and end points for laser line
    protected int r1x, r1y, r2x, r2y, r1w, r1h; 
    protected Card2 card;
    protected boolean isDone, pressedRed, correct, enabled;
    protected int[][] xCoords,yCoords;
    protected int needsInfo;
    /**
     * If the board selected is the intermediate difficulty, a 
     * LaserMaze2 object is constructed.
     * 
     * @param isBeginner sets intermediate to true to determine
     * the level difficulty
     */
    public LaserMaze2(boolean isBeginner){
        beginner = isBeginner;
        isDone = false;
        enabled = true;
        needsInfo = 0;
        correct = false;
        xCoords = new int[5][5];
        yCoords = new int[5][5];
        pressedRed = false;
        card = new Card2();
        clickedPurpleTarget = 0;
        clickedPurple1 = 0;
        clickedPurple2 = 0;
        clickedPurple3 = 0;
        clickedPurple4 = 0;
        card.setXCoords();
        card.setYCoords();
        wasDraggedP1 = false;
        wasDraggedP2 = false;
        wasDraggedP3 = false;
        wasRightClickedP1 = false;
        wasRightClickedP2 = false;
        wasRightClickedP3 = false;
        p3x = 941;
        p3y = 302;
        r1x = 321;
        r1y = 218;
        r1w = 0;
        r1h = 0;
        initBoard();
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    /**
     * Determines which type of tile the mouse has clicked on and
     * with which mouse button. If the mouse has clicked on the 
     * laser or fire button, it will draw the laser. if the mouse
     * has clicked on the coordinates of a movable tile it will 
     * display a new rotated tile image.
     * 
     * @param MousEvent e gets information on the mouseEvent
     */
    public void mouseClicked(MouseEvent e) {
        if (enabled){
            //if left clicked
            if (e.getButton() == MouseEvent.BUTTON1){ 
                //If clicked on non-movable purple piece
                if (e.getX() >= 1063 && e.getX() <= 1100 && e.getY()
                >= 714 && e.getY() <= 745){ //needs instructions
                    needsInfo++;
                }
                else if (e.getX() >= 112 && e.getX() <= 236 &&
                e.getY() >= 682 && e.getY() <= 806){
                    //increment variable to use for mod 4
                    clickedPurple1++;
                    ImageIcon purple = new 
                    ImageIcon("purpleRotatableDown.png");
                    purpleRotatable = purple.getImage();
                    if (clickedPurple1 % 4 == 1){
                        purple = new 
                        ImageIcon("purpleRotatableDown.png");
                        purpleRotatable = purple.getImage();
                    }
                    else if (clickedPurple1 % 4 == 2){
                        purple = new 
                        ImageIcon("purpleRotatableRight.png");
                        purpleRotatable = purple.getImage();
                    }
                    else if (clickedPurple1 % 4 == 3){
                        purple = new 
                        ImageIcon("purpleRotatableUp.png");
                        purpleRotatable = purple.getImage();
                    }
                    else if (clickedPurple1 % 4 == 0){
                        purple = new 
                        ImageIcon("purpleRotatableLeft.png");
                        purpleRotatable = purple.getImage();
                    }
                }
                //If clicked on Purple Rotatable Target
                else if (e.getX() >= 101 && e.getX() <= 237
                && e.getY() >= 379 && e.getY() <= 518){
                    clickedPurpleTarget++;
                    ImageIcon purple = new 
            ImageIcon("purpleTargetRotatableDown.png");
                    purpleTarget = purple.getImage();
                    if (clickedPurpleTarget % 4 == 1){
                        purple = new 
            ImageIcon("purpleTargetRotatableDown.png");
                        purpleTarget = purple.getImage();
                    }
                    else if (clickedPurpleTarget % 4 == 2){
                        purple = new 
            ImageIcon("purpleTargetRotatableRight.png");
                        purpleTarget = purple.getImage();
                    }
                    else if (clickedPurpleTarget % 4 == 3){
                        purple = new 
            ImageIcon("purpleTargetRotatableUp.png");
                        purpleTarget = purple.getImage();
                    }
                    else if (clickedPurpleTarget % 4 == 0){
                        purple = new 
            ImageIcon("purpleTargetRotatableLeft.png");
                        purpleTarget = purple.getImage();
                    }
                }
                //If clicked on first movable purple piece
                else if (e.getX() >= p1x && e.getX() <= p1x + SIZE &&
                e.getY() >= p1y && e.getY() <= p1y + SIZE){
                    clickedPurple2++;
                    ImageIcon purple = new 
                    ImageIcon("purpleRotatableDown.png");
                    if (clickedPurple2 % 4 == 1){
                        purple = new 
                        ImageIcon("purpleRotatableDown.png");
                    }
                    else if (clickedPurple2 % 4 == 2){
                        purple = new 
                        ImageIcon("purpleRotatableRight.png");
                    }
                    else if (clickedPurple2 % 4 == 3){
                        purple = new 
                        ImageIcon("purpleRotatableUp.png");
                    }
                    else if (clickedPurple2 % 4 == 0){
                        purple = new 
                        ImageIcon("purpleRotatableLeft.png");
                    }
                    purpleQuestionMovable1 = purple.getImage();
                }
                //If clicked on second movable purple piece
                else if (e.getX() >= p2x && e.getX() <= p2x + SIZE &&
                e.getY() >= p2y && e.getY() <= p2y + SIZE){
                    clickedPurple3++;
                    ImageIcon purple = new 
                    ImageIcon("purpleRotatableDown.png");
                    if (clickedPurple3 % 4 == 1){
                        purple = new 
                        ImageIcon("purpleRotatableDown.png");
                    }
                    else if (clickedPurple3 % 4 == 2){
                        purple = new 
                        ImageIcon("purpleRotatableRight.png");
                    }
                    else if (clickedPurple3 % 4 == 3){
                        purple = new 
                        ImageIcon("purpleRotatableUp.png");
                    }
                    else if (clickedPurple3 % 4 == 0){
                        purple = new 
                        ImageIcon("purpleRotatableLeft.png");
                    }
                    purpleQuestionMovable2 = purple.getImage();
                }
                //If clicked on third movable purple piece
                else if (e.getX() >= p3x && e.getX() <= p3x + SIZE &&
                e.getY() >= p3y && e.getY() <= p3y + SIZE){
                    clickedPurple4++;
                    ImageIcon purple = new 
                    ImageIcon("purpleRotatableDown.png");
                    if (clickedPurple4 % 4 == 1){
                        purple = new 
                        ImageIcon("purpleRotatableDown.png");
                    }
                    else if (clickedPurple4 % 4 == 2){
                        purple = new 
                        ImageIcon("purpleRotatableRight.png");
                    }
                    else if (clickedPurple4 % 4 == 3){
                        purple = new 
                        ImageIcon("purpleRotatableUp.png");
                    }
                    else if (clickedPurple4 % 4 == 0){
                        purple = new 
                        ImageIcon("purpleRotatableLeft.png");
                    }
                    purpleQuestionMovable3 = purple.getImage();
                }
            }
            //if right clicked
            else if (e.getButton() == MouseEvent.BUTTON3){ 
                //1st purple
                if (e.getX() >= p1x && e.getX() <= p1x + SIZE &&
                e.getY() >= p1y && e.getY() <= p1y + SIZE){ 
                    if (wasRightClickedP2 == true){
                        wasRightClickedP2 = false;
                    }
                    wasRightClickedP1 = true;
                }
                //2nd purple
                else if (e.getX() >= p2x && e.getX() <= p2x + SIZE &&
                e.getY() >= p2y && e.getY() <= p2y + SIZE){ 
                    if (wasRightClickedP1 == true){
                        wasRightClickedP1 = false;
                    }
                    wasRightClickedP2 = true;
                }
                //3rd purple
                else if (e.getX() >= p3x && e.getX() <= p3x + SIZE &&
                e.getY() >= p3y && e.getY() <= p3y + SIZE){ 
                    if (wasRightClickedP1 == true){
                        wasRightClickedP1 = false;
                    }
                    wasRightClickedP3 = true;
                }
                else { //Clicked outside of all purple pieces
                    if (wasRightClickedP1 == true){
                        wasDraggedP1 = true;
                        wasRightClickedP1 = false;
                        mouseReleased(e);
                    }
                    else if (wasRightClickedP2 == true){
                        wasDraggedP2 = true;
                        wasRightClickedP2 = false;
                        mouseReleased(e);
                    }
                    else if (wasRightClickedP3 == true){
                        wasDraggedP3 = true;
                        wasRightClickedP3 = false;
                        mouseReleased(e);
                    }
                }
            }
        }
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    /** 
     * Determines if the mouse was pressed in the bounds of
     * different objects and sets dragging coordinates.
     * 
     * @param e mouse event information
     */
    public void mousePressed(MouseEvent e) {
        if (enabled){
            //p1
            draggingP1 = (e.getX() >= p1x && e.getX() <= p1x + SIZE
            && e.getY() >= p1y && e.getY() <= p1y + SIZE);
            p1xOffset = e.getX() - p1x;
            p1yOffset = e.getY() - p1y;

            //p2
            draggingP2 = (e.getX() >= p2x && e.getX() <= p2x + SIZE
            && e.getY() >= p2y && e.getY() <= p2y + SIZE);
            p2xOffset = e.getX() - p2x;
            p2yOffset = e.getY() - p2y;

            //p3
            draggingP3 = (e.getX() >= p3x && e.getX() <= p3x + SIZE 
            && e.getY() >= p3y && e.getY() <= p3y + SIZE);
            p3xOffset = e.getX() - p3x;
            p3yOffset = e.getY() - p3y;

            if ((e.getX() >= 697 && e.getX() <= 835 && e.getY() >= 86
            && e.getY() <= 220) || 
            e.getX() >= 941 && e.getX() <= 1069 && e.getY() >= 748 &&
            e.getY() <= 875){
                pressedRed = true;
                try {
                    AudioInputStream audioInputStream = 
                    AudioSystem.getAudioInputStream(
 new File("Laser2.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                }
                catch(Exception ex){
                    System.err.println("Error");
                    ex.printStackTrace();
                }
                repaint();
            }
        }
    }

    /**
     * Determines if where the mouse was released is a legal move
     * and will repaint based on which tile it was released on
     * and will snap the piece into place.
     * 
     * @param MousEvent e gives information about the mouse event
     */
    public void mouseReleased(MouseEvent e) {
        if (enabled){
            //Need to make sure that dragged object is close to a 
            //spot and then is put into that spot.
            int px = 0;
            int py = 0;
            int row = 0;
            int col = 0;
            pressedRed = false;
            if (e.getX() >= 107 && e.getX() <= 240 && e.getY() >= 82
            && e.getY() <= 222){
                px = 109;
                py = 88;
            }
            else if (e.getX() >= 248 && e.getX() <= 394 && e.getY()
            >= 82 && e.getY() <= 221 ){
                px =  259;
                py =  90;
                col = 1;
            }
            else if (e.getX() >= 403 && e.getX() <= 538 && e.getY()
            >= 83 && e.getY() <= 221){
                px = 408;
                py = 91;
                col = 2;
            }
            else if (e.getX() >= 547 && e.getX() <= 689 && e.getY()
            >= 83 && e.getY() <= 221){ // end row 1
                px = 556;
                py = 90;
                col = 3;
            }
            else if (e.getX() >= 107 && e.getX() <= 240 && e.getY()
            >= 230 && e.getY() <= 372){ 
                px = 112;
                py = 240;
                row = 1;
                col = 0;
            }
            else if (e.getX() >= 251 && e.getX() <= 392 && e.getY()
            >= 230 && e.getY() <= 372){
                px = 261;
                py = 238;
                row = 1;
                col = 1;
            }
            else if (e.getX() >= 403 && e.getX() <= 538 && e.getY()
            >= 230 && e.getY() <= 372){
                px = 407;
                py = 239;
                row = 1;
                col = 2;
            }
            else if (e.getX() >= 547 && e.getX() <= 689 && e.getY()
            >= 230 && e.getY() <= 372){
                px = 556;
                py = 238;
                row = 1;
                col = 3;
            }
            else if (e.getX() >= 691 && e.getX() <= 837 && e.getY()
            >= 230 && e.getY() <= 372){ //end row 2
                px = 702;
                py = 238;
                row = 1;
                col = 4;
            }
            else if (e.getX() >= 251 && e.getX() <= 392 && e.getY()
            >= 376 && e.getY() <= 520){
                px = 260;
                py = 385;
                row = 2;
                col = 1;
            }
            else if (e.getX() >= 403 && e.getX() <= 538 && e.getY()
            >= 376 && e.getY() <= 520){
                px = 407;
                py = 385;
                row = 2;
                col = 2;
            }
            else if (e.getX() >= 547 && e.getX() <= 689 && e.getY()
            >= 376 && e.getY() <= 520){
                px = 556;
                py = 385;
                row = 2;
                col = 3;
            }
            else if (e.getX() >= 691 && e.getX() <= 837 && e.getY()
            >= 376 && e.getY() <= 520){ //end row 3
                px = 703;
                py = 385;
                row = 2;
                col = 4;
            }
            else if (e.getX() >= 107 && e.getX() <= 240 && e.getY()
            >= 525 && e.getY() <= 666){
                px = 112;
                py = 535;
                row = 3;
                col = 0;
            }
            else if (e.getX() >= 251 && e.getX() <= 392 && e.getY()
            >= 525 && e.getY() <= 666){
                px = 260;
                py = 535;
                row = 3;
                col = 1;
            }
            else if (e.getX() >= 403 && e.getX() <= 538 && e.getY()
            >= 525 && e.getY() <= 666){
                px = 407;
                py = 535;
                row = 3;
                col = 2;
            }
            else if (e.getX() >= 547 && e.getX() <= 689 && e.getY()
            >= 525 && e.getY() <= 666){
                px = 556;
                py = 535;
                row = 3;
                col = 3;
            }
            else if (e.getX() >= 691 && e.getX() <= 837 && e.getY()
            >= 525 && e.getY() <= 666){ //end row 4
                px = 703;
                py = 535;
                row = 3;
                col = 4;
            }
            else if (e.getX() >= 251 && e.getX() <= 392 && e.getY()
            >= 672 && e.getY() <= 815){
                px = 260;
                py = 681;
                row = 4;
                col = 1;
            }
            else if (e.getX() >= 403 && e.getX() <= 538 && e.getY()
            >= 672 && e.getY() <= 815){
                px = 407;
                py = 681;
                row = 4;
                col = 2;
            }
            else if (e.getX() >= 691 && e.getX() <= 837 && e.getY()
            >= 672 && e.getY() <= 815){ //end row 5
                px = 703;
                py = 681;
                row = 4;
                col = 4;
            }

            if (wasDraggedP1){
                wasDraggedP1 = false;
                p1x = px;
                p1y = py;

                if (px == 0){
                    p1x = 941;
                    p1y = 562;
                }
                else if ((px == p3x && py == p3y) || (px == p2x && py
                == p2y)){
                    p1x = 941;
                    p1y = 562;
                }
                else {
                    if (card.alreadyHas(5)){
                        card.remove(5);
                    }
                    card.board[row][col] = 5;
                }
            }
            else if (wasDraggedP2){
                wasDraggedP2 = false;
                p2x = px;
                p2y = py;
                if (px == 0){
                    p2x = 941;
                    p2y = 432;
                }
                else if ((px == p1x && py == p1y) || (px == p3x && py
                == p3y)){
                    p2x = 941;
                    p2y = 432;
                }
                else {
                    if (card.alreadyHas(6)){
                        card.remove(6);
                    }
                    card.board[row][col] = 6;
                }
            }
            else if (wasDraggedP3){
                wasDraggedP3 = false;
                p3x = px;
                p3y = py;
                if (px == 0){
                    p3x = 941;
                    p3y = 302;
                }
                else if ((px == p1x && py == p1y) || (px == p2x && py
                == p2y)){
                    p3x = 941;
                    p3y = 302;
                }
                else {
                    if (card.alreadyHas(7)){
                        card.remove(7);
                    }
                    card.board[row][col] = 7;
                }
            }
            repaint();
        }
    }
    
    /**
     * Repaints every time the mouse drags a piece around the
     * board before placeing it.
     * 
     * @param MousEvent e gives information on the mouse event
     */
    public void mouseDragged(MouseEvent e) {
        if (enabled){

            if (draggingP1) {
                p1x = e.getX() - p1xOffset;
                p1y = e.getY() - p1yOffset;
                wasDraggedP1 = true;
            }
            else if (draggingP2){
                p2x = e.getX() - p2xOffset;
                p2y = e.getY() - p2yOffset;
                wasDraggedP2 = true;
            }
            else if (draggingP3){
                p3x = e.getX() - p3xOffset;
                p3y = e.getY() - p3yOffset;
                wasDraggedP3 = true;
            }
            repaint();
        }
    }

    /**
     * Repaints if the mouse is moved before the game ends.
     * 
     * @param e MouseEvent that triggers action
     */
    public void mouseMoved(MouseEvent e) {
        if (enabled){
            repaint();
        }
    }

    /**
     * Loads the board and sets the dimensions for board2
     */
    private void initBoard(){
        loadImage();

        p1x = 941;
        p1y = 562;
        p2x = 941;
        p2y = 432;

        int w = boardPic1.getWidth(this);
        int h = boardPic1.getHeight(this);
        setPreferredSize(new Dimension(w,h));
    }

    /**
     * Creates all of the images needed for the board throughout 
     * the game.
     */
    public void loadImage(){
        ImageIcon b1 = new ImageIcon("board1.png");
        boardPic1 = b1.getImage();

        ImageIcon b2 = new ImageIcon("board2.png");
        boardPic2 = b2.getImage();

        ImageIcon icon2 = new ImageIcon("purpleTarget.jpg");
        purplePic = icon2.getImage();

        ImageIcon p1 = new ImageIcon("purpleRotatable.png");
        purpleRotatable = null;

        ImageIcon red1 = new ImageIcon("redLaserLeft.jpg");
        redPicLeft = red1.getImage();

        ImageIcon red2 = new ImageIcon("redLaserDown.jpg");
        redPicDown = red2.getImage();

        ImageIcon yellowH = new ImageIcon("yellowHorizontal.jpg");
        yellowHorizontal = yellowH.getImage();

        ImageIcon target = new 
        ImageIcon("purpleTargetRotatableDown.png");
        purpleTarget = null;

        ImageIcon won = new ImageIcon("youWon.png");
        youWon = won.getImage();

        ImageIcon questions = 
        new ImageIcon("smallQuestionIcon.png");
        instructions = questions.getImage();

        ImageIcon information = new ImageIcon("GameRules.jpg");
        info = information.getImage();

        ImageIcon purpleSpin = new 
        ImageIcon("purpleQuestionMovable.png");
        purpleQuestionMovable1 = purpleSpin.getImage();
        purpleQuestionMovable2 = purpleSpin.getImage();
        purpleQuestionMovable3 = purpleSpin.getImage();
        purpleQuestionMovable4 = purpleSpin.getImage();
        purpleQuestionMovable5 = purpleSpin.getImage();
    }

    /**
     * Updates positions matrix in the card class.
     */
    public void updatePositions(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (card.board[i][j] == 3){ //purple target
                    card.positions[i][j] = clickedPurpleTarget % 4;
                }
                else if (card.board[i][j] == 4){ //purple rotatable
                    card.positions[i][j] = clickedPurple1 % 4;
                }
                else if (card.board[i][j] == 5){ //purple1
                    card.positions[i][j] = clickedPurple2 % 4;
                }
                else if (card.board[i][j] == 6){ //purple2
                    card.positions[i][j] = clickedPurple3 % 4;
                }
                else if (card.board[i][j] == 7){ //purple3
                    card.positions[i][j] = clickedPurple4 % 4;
                }
            }
        }
    }
    /**
     * Paints the board and paints the laser and how it will react 
     * to the different surfaces of the blocks it hits.
     * 
     * @param Graphics g 
     */
    @Override
    public void paintComponent(Graphics g){
        if (enabled){
            super.paintComponent(g);
            if (beginner == true){
                g.drawImage(boardPic1,0,0,null);
                g.drawImage(purpleQuestionMovable1,p1x,p1y,null);
                g.drawImage(purpleQuestionMovable2,p2x,p2y,null);
                g.drawImage(purpleRotatable,112,682,null);
                g.setColor(Color.RED);
            }
            else {
                g.drawImage(boardPic2,0,0,null);
                g.drawImage(purpleQuestionMovable1,p1x,p1y,null);
                g.drawImage(purpleQuestionMovable2,p2x,p2y,null);
                g.drawImage(purpleQuestionMovable3,p3x,p3y,null);
                g.drawImage(purpleRotatable,112,682,null);
                g.drawImage(purpleTarget,109,389,null);
                g.drawImage(instructions,1068,720,null);
                
                //Creates Label for instructions
                Font courierBold10 = new Font("Courier", Font.BOLD,
                13);
                g.setFont(courierBold10);
                g.drawString(" OPEN/CLOSE", 1020, 700);
                g.drawString("INSTRUCTIONS", 1020, 715);
                
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(5));
                g2.setColor(Color.RED);
                isDone = false;
                if (needsInfo % 2 == 1){
                    g.drawImage(info,250,83,null);
                }

                if (pressedRed && isDone == false){
                    updatePositions();
                    int firstPiece = getFirstPiece(1,0);
                    int[] coords1 = getCoords(1, firstPiece, 0);
                    g.drawLine(700,151,coords1[0],coords1[1]);
                    int dir = getNewLaserDirection(0,firstPiece);
                    if (isDone == false && dir != -1){
                        updatePositions();
                        int direction = getNewLaserDirection(0,
                        firstPiece);
                        int nextPiece = getFirstPiece(firstPiece,
                        direction);
                        int[] coords2 = getCoords(firstPiece,
                        nextPiece, direction);
    g.drawLine(coords1[0],coords1[1],coords2[0],coords2[1]);
                        int direction1 = 
             getNewLaserDirection(direction, nextPiece);

                        if (isDone == false && nextPiece != -1 && 
                        direction1 != -1){ 
                            updatePositions();
                            int nextPiece1 = 
                            getFirstPiece(nextPiece,direction1);
                            int[] coords3 = 
                            getCoords(nextPiece, nextPiece1, direction1);
              g.drawLine(coords2[0],coords2[1],coords3[0],coords3[1]);
                            int direction2 = 
                            getNewLaserDirection(direction1, nextPiece1);
                            if (isDone == false && nextPiece1 != -1 
                            && direction2 != -1){
                                updatePositions();
                                int nextPiece2 = 
                                getFirstPiece(nextPiece1,direction2);
                                int[] coords4 = 
                                getCoords(nextPiece1, nextPiece2, direction2);
                   g.drawLine(coords3[0],coords3[1],coords4[0],coords4[1]);
                                int direction3 = 
                                getNewLaserDirection(direction2, nextPiece2);

                                if (isDone == false && nextPiece2 != -1 
                                && direction3 != -1){
                                    updatePositions();
                                    int nextPiece3 = 
                                    getFirstPiece(nextPiece2,direction3);
                                    int[] coords5 = 
                             getCoords(nextPiece2, nextPiece3, direction3);
                       g.drawLine(coords4[0],coords4[1],coords5[0],coords5[1]);
                                    int direction4 = 
                             getNewLaserDirection(direction3, nextPiece3);
                                    if (isDone == false && nextPiece3 != 
                                    -1 && direction4 != -1){
                                        updatePositions();
                                        int nextPiece4 = 
                                        getFirstPiece(nextPiece3,direction4);
                                        int[] coords6 = 
                       getCoords(nextPiece3, nextPiece4, direction4);
                      g.drawLine(coords5[0],coords5[1],coords6[0],coords6[1]);
                                        int direction5 = 
                      getNewLaserDirection(direction4, nextPiece4);
                                        if (isDone == false && nextPiece4 != 
                                        -1 && direction5 != -1){
                                            updatePositions();
                                            int nextPiece5 = 
                                   getFirstPiece(nextPiece4,direction5);

                                            int[] coords7 = 
                              getCoords(nextPiece4, nextPiece5, direction5);
                              g.drawLine(coords6[0],coords6[1],coords7[0],coords7[1]);
                                            int direction6 = 
                              getNewLaserDirection(direction5, nextPiece5);
                                            if (card.solvedGame()){
                                             g.drawImage(youWon,400,150,null);
                                   EventQueue.invokeLater(new Runnable() {

                                                        protected String answer;
                                                        @Override
                                                        public void run() {
                                                            enabled = false;
                                                            Driver b1 = 
                                                            new Driver(1);
                                                            Driver b2 = 
                                                            new Driver(2);
                                                            Object[] options = 
                                       { "Beginner","Intermediate", "Quit"};
                                                            int result =
                                  JOptionPane.showOptionDialog(null,
                         "Select a level","Laser Maze", 
                         JOptionPane.YES_NO_CANCEL_OPTION,
                         JOptionPane.QUESTION_MESSAGE, null, options, null);

                                                            while (correct == 
                                                            false){
                                                                if (result == 
                                                    JOptionPane.YES_OPTION){
                                                                    correct = 
                                                                    true;
                                                          b1.setVisible(true);
                                                                }
                                                                else if (result 
                                                  == JOptionPane.NO_OPTION){
                                                                    correct = 
                                                                    true;
                                                    b2.setVisible(true);
                                                                }
                                                                else if (result 
                                                 == JOptionPane.CANCEL_OPTION){
                                                                    correct =
                                                                    true;
                                                           System.exit(0);
                                                                }
                                                                else {
                                                                    result = 
  JOptionPane.showOptionDialog(null,"Select a level","Laser Maze", 
                               JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options, null);
                                                                    correct = 
                                                                    false; 
                                                            //set exit on close

                                                                }
                                                            }
                                                        }
                                                    });
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                repaint();
            }
        }
    }

    /**
     * Determines the first or next piece to be "hit" by the laser
     * or if the laser does not hit a piece and hits the wall
     * 
     * @param laserStartPiece The piece the laser is projected from
     * @param laserDirection The direction the laser is traveling
     * @return The corresponding int piece value the laser will hit
     * or -1 if no object is hit
     */
    public int getFirstPiece(int laserStartPiece, int laserDirection){
        int row = card.getRow(laserStartPiece);
        int col = card.getCol(laserStartPiece);
        if (laserDirection == 1){ //laser is going down
            for (int i = row + 1; i < 5; i++){
                if (card.board[i][col] != 0){
                    return card.board[i][col];
                }
            }
        }
        else if (laserDirection == 2){ //laser is going to the right
            for (int i = col + 1; i < 5; i++){
                if (card.board[row][i] != 0){
                    return card.board[row][i];
                }
            }
        }
        else if (laserDirection == 3){ //laser is going up
            for (int i = row - 1; i >= 0; i--){
                if (card.board[i][col] != 0){
                    return card.board[i][col];
                }
            }
        }
        else if (laserDirection == 0){ //laser is going to the left
            for (int i = col - 1; i >= 0; i--){
                if (card.board[row][i] != 0){
                    return card.board[row][i];
                }
            }
        }
        isDone = true;
        return -1; //indicates that there was no piece in path of laser
    }

    /**
     * Finds the new direction the laser should be traveling given
     * the inputed original laser direction and the piece it is 
     * hitting resulting in a potential change in direction.
     * 
     * @param origDirection The orgininal direction the laser was 
     * traveling
     * @param piece The piece that the laser reflects off of
     * @return The corresponding new int direction of the laser
     */
    public int getNewLaserDirection(int origDirection, int piece){
        int pos = card.getPos(piece);
        //System.out.println("Position of piece, should be 1: " + pos);
        if (piece == 2){ //is the piece is yellow
            if (origDirection == 1){
                return -1;
            }
            else {
                return origDirection;
            }
        }
        else if (piece == 1) {
            return -1;
        }
        else {
            if (origDirection == 1){ //Laser first goes down
                if (pos == 1){ //laser now goes to the right
                    return 2;
                }
                else if (pos == 2) { //laser now goes to the left
                    return 0;
                }
                else { //laser stops
                    isDone = true;
                    return -1;
                }
            }

            else if (origDirection == 2){ //Laser first goes to the right
                if (pos == 2){ //laser now goes up
                    return 3;
                }
                else if (pos == 3) { //laser now goes down
                    return 1;
                }
                else { //laser stops
                    isDone = true;
                    return -1;
                }
            }
            else if (origDirection == 3){ //Laser first goes up
                if (pos == 3){ //laser now goes to the left
                    return 0;
                }
                else if (pos == 0) { //laser now goes to the right
                    return 2;
                }
                else { //laser stops
                    isDone = true;
                    return -1;
                }
            }
            else { //Laser first goes to the left
                if (pos == 1){ //laser now goes up
                    return 3;
                }
                else if (pos == 0) { //laser now goes down
                    return 1;
                }
                else { //laser stops
                    isDone = true;
                    return -1;
                }
            }
        }

    }

    /**
     * Finds the x and y coordinates for drawing the laser.
     * 
     * @param firstPiece The first piece the laser is reflected off of
     * @param endPiece The second piece where the laser segment ends
     * @return An integer array containing the x and y coordinates for
     * drawing a laser segment
     */
    public int[] getCoords(int firstPiece, int endPiece, int laserDirection){
        int[] coords = new int[2];
        int piecePos = card.getPos(endPiece);
        int row = card.getRow(endPiece);
        int col = card.getCol(endPiece);
        int startRow = card.getRow(firstPiece);
        int startCol = card.getCol(firstPiece);
        setXCoords();
        setYCoords();
        if (endPiece == -1){ //if there is no piece and laser hits wall
            isDone = true;
            if (laserDirection == 1){
                coords[0] = (xCoords[startRow][startCol]);
                coords[1] = (828);
            }
            else if(laserDirection == 2){
                coords[0] = (851);
                coords[1] = (yCoords[startRow][startCol]);
            }
            else if (laserDirection == 3){
                coords[0] = (xCoords[startRow][startCol]);
                coords[1] = (66);
            }
            else {
                coords[0] = (88);
                coords[1] = (yCoords[startRow][startCol]);
            }
        }
        else if (endPiece == 2){ //if the endPiece is yellow
            if (laserDirection == 1){
                coords[0] = (xCoords[startRow][startCol]);
                coords[1] = (678);
            }
            else if (laserDirection == 2 || laserDirection == 0){
                coords[0] = (xCoords[4][3]);
                coords[1] = (yCoords[4][3]);
            }
            else {
                // means there was an issue with previous code, should never
                //this if statement
                coords[0] = (-1); 
                coords[1] = (-1);
            }
        }

        else { //if the endPiece is purpleTarget, purpleRotatable, purple1, 
            //purple2, or purple3
            if (laserDirection == 1){ 
                //hits side
                if (card.getPos(endPiece) == 3 || card.getPos(endPiece) == 0){ 
                    coords[0] = (xCoords[row][col]);
                    coords[1] = (yCoords[row][col] - 59);
                }
                else {
                    coords[0] = (xCoords[row][col]);
                    coords[1] = (yCoords[row][col]);
                }
            }
            else if (laserDirection == 2){
                if (card.getPos(endPiece) == 1 || card.getPos(endPiece) == 0){
                    coords[0] = (xCoords[row][col] - 59);
                    coords[1] = (yCoords[row][col]);
                }
                else {
                    coords[0] = (xCoords[row][col]);
                    coords[1] = (yCoords[row][col]);
                }
            }
            else if (laserDirection == 3){
                if (card.getPos(endPiece) == 1 || card.getPos(endPiece) == 2){
                    coords[0] = (xCoords[row][col]);
                    coords[1] = (yCoords[row][col] + 59);
                }
                else {
                    coords[0] = (xCoords[row][col]);
                    coords[1] = (yCoords[row][col]);
                }
            }
            else if (laserDirection == 0) { //Left
                if (card.getPos(endPiece) == 2 || card.getPos(endPiece) == 3){
                    coords[0] = (xCoords[row][col] + 59);
                    coords[1] = (yCoords[row][col]);
                }
                else {
                    coords[0] = (xCoords[row][col]);
                    coords[1] = (yCoords[row][col]);
                }
            }
            if (laserDirection == -1){
                coords[0] = xCoords[row][col];
                coords[1] = yCoords[row][col];
                isDone = true;
            }
        }
        return coords;
    }

    /**
     * Constructs the integer matrix of x coordinates corresponding
     * with laser maze's board matrix.
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
     * Constructs the integer matrix of y coordinates corresponding
     * with laser maze's board matrix.
     */
    public void setYCoords(){
        yCoords[0][0] = 150;
        yCoords[0][1] = 150;
        yCoords[0][2] = 150;
        yCoords[0][3] = 150;
        yCoords[0][4] = 150;
        yCoords[1][0] = 300;
        yCoords[1][1] = 300;
        yCoords[1][2] = 300;
        yCoords[1][3] = 300;
        yCoords[1][4] = 300;
        yCoords[2][0] = 448;
        yCoords[2][1] = 448;
        yCoords[2][2] = 448;
        yCoords[2][3] = 448;
        yCoords[2][4] = 448;
        yCoords[3][0] = 592;
        yCoords[3][1] = 592;
        yCoords[3][2] = 592;
        yCoords[3][3] = 592;
        yCoords[3][4] = 592;
        yCoords[4][0] = 741;
        yCoords[4][1] = 741;
        yCoords[4][2] = 741;
        yCoords[4][3] = 741;
        yCoords[4][4] = 741;
    }
}
