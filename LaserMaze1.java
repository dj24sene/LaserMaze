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
/**
 * Sets up the game if beginner difficulty is picked
 *
 * @author Brianna Davis, Zach Giannuzzi, Abdul Samad,
 * Eric Sauer, Daniel Senecal
 * @version 4/30/2018
 */
public class LaserMaze1 extends JPanel implements MouseListener, 
MouseMotionListener
{
    private Image boardPic1, boardPic2, youWon, info, 
    instructions;
    private Image purplePic;
    private Image purpleRotatable;
    private Image redPicLeft;
    private Image redPicDown;
    private Image yellowHorizontal;
    private Image purpleQuestionMovable1, purpleQuestionMovable2;
    private Image purpleQuestionMovable3, purpleQuestionMovable4, 
    purpleQuestionMovable5;
    private int clickedPurple1, clickedPurple2, clickedPurple3, 
    needsInfo;
    protected boolean beginner;
    protected int p1x, p1y, p1xOffset, p1yOffset;
    protected int p2x, p2y, p2xOffset, p2yOffset;
    protected static final int SIZE = 125;
    protected boolean draggingP1, draggingP2;
    protected boolean wasDraggedP1, wasDraggedP2;
    protected boolean wasRightClickedP1, wasRightClickedP2, 
    wasClickedRed;
    protected boolean laserRunning = false;
    //start and end points for laser line
    protected int r1x, r1y, r2x, r2y, r1w, r1h; 
    //variables for Card1 class
    protected Card1 card;
    protected int laserRow, laserCol;
    protected boolean laserContinues, laserDirection, pressedRed,
    correct, enabled;
    /**
     * If the board selected is the beginner difficulty, 
     * a LaserMaze1 object is constructed.
     * 
     * @Param isBeginner sets beginner to true to determine
     * the level difficulty
     */
    public LaserMaze1(boolean isBeginner){
        beginner = isBeginner;
        enabled = true;
        correct = false;
        clickedPurple1 = 0;
        needsInfo = 0;
        wasDraggedP1 = false;
        wasDraggedP2 = false;
        wasRightClickedP1 = false;
        wasRightClickedP2 = false;
        wasClickedRed = false;
        laserContinues = true;
        laserDirection = false;
        laserRow = -1;
        laserCol = -1;
        r1x = 321;
        r1y = 218;
        r1w = 0;
        r1h = 0;
        card = new Card1();
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
                //If you click on the laser or the Fire Button
                //needs instructions
                if (e.getX() >= 1063 && e.getX() <= 1100 && 
                e.getY() >= 714 && e.getY() <= 745){ 
                    needsInfo++;
                }
                else if ((e.getX() >= 261 && e.getX() <= 387 && 
                    e.getY() >= 87 && e.getY() <= 220) || 
                e.getX() >= 941 && e.getX() <= 1069 && e.getY() 
                >= 748 && e.getY() <= 875){
                    wasClickedRed = true;
                    repaint();
                }

                //If clicked on non-movable purple piece
                else if (e.getX() >= 112 && e.getX() <= 236 && 
                e.getY() >= 682 && e.getY() <= 806){
                    //increment variable to use for mod 4
                    clickedPurple1++;
                    ImageIcon purple = 
                        new ImageIcon("purpleRotatableDown.png");
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
                //If clicked on first movable purple piece
                else if (e.getX() >= p1x && e.getX() <=
                p1x + SIZE && e.getY() >= p1y && e.getY()
                <= p1y + SIZE){
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
                else if (e.getX() >= p2x && e.getX() <= p2x +
                SIZE && e.getY() >= p2y && e.getY() <= p2y + 
                SIZE){
                    clickedPurple3++;
                    ImageIcon purple = new 
                        ImageIcon("purpleTargetRotatableDown.png");
                    if (clickedPurple3 % 4 == 1){
                        purple = new 
                        ImageIcon("purpleTargetRotatableDown.png");
                    }
                    else if (clickedPurple3 % 4 == 2){
                        purple = new 
                        ImageIcon("purpleTargetRotatableRight.png");
                    }
                    else if (clickedPurple3 % 4 == 3){
                        purple = new 
                        ImageIcon("purpleTargetRotatableUp.png");
                    }
                    else if (clickedPurple3 % 4 == 0){
                        purple = new 
                        ImageIcon("purpleTargetRotatableLeft.png");
                    }
                    purpleQuestionMovable2 = purple.getImage();
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
                else if (e.getX() >= p2x && e.getX() <= p2x + 
                SIZE && e.getY() >= p2y && e.getY() <= p2y + 
                SIZE){ //2nd purple
                    if (wasRightClickedP1 == true){
                        wasRightClickedP1 = false;
                    }
                    wasRightClickedP2 = true;
                }
                else { //Clicked outside of both purple peices
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
            draggingP1 = (e.getX() >= p1x && e.getX() <= p1x +
                SIZE &&
                e.getY() >= p1y && e.getY() <= p1y + SIZE);
            p1xOffset = e.getX() - p1x;
            p1yOffset = e.getY() - p1y;

            //p2
            draggingP2 = (e.getX() >= p2x && e.getX() <= p2x + 
                SIZE &&
                e.getY() >= p2y && e.getY() <= p2y + SIZE);
            p2xOffset = e.getX() - p2x;
            p2yOffset = e.getY() - p2y;

            if ((e.getX() >= 261 && e.getX() <= 387 && e.getY()
                >= 87 && e.getY() <= 220) || 
            e.getX() >= 941 && e.getX() <= 1069 && e.getY() >= 
            748 && e.getY() <= 875){
                pressedRed = true;
                try {
                    AudioInputStream audioInputStream = 
                    AudioSystem.getAudioInputStream(
 new File("Laser1.wav").getAbsoluteFile());
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
     * and will snap that piece into place.
     * 
     * @param MousEvent e gives information about the mouse event
     * @return Nothing
     */
    public void mouseReleased(MouseEvent e) {
        if (enabled){
            //Need to make sure that dragged object is close 
            //to a spot and then is put into that spot.
            int px = 0;
            int py = 0;
            int row = 0;
            int col = 0;
            pressedRed = false;
            if (e.getX() >= 107 && e.getX() <= 240 && e.getY() >=
            82 && e.getY() <= 222){
                px = 109;
                py = 88;
                row = 0;
                col = 0;
            }
            else if (e.getX() >= 403 && e.getX() <= 538 && 
            e.getY() >= 83 && e.getY() <= 221){
                px = 408;
                py = 91;
                col = 2;
            }
            else if (e.getX() >= 547 && e.getX() <= 689 && 
            e.getY() >= 83 && e.getY() <= 221){
                px = 556;
                py = 90;
                col = 3;
            }
            else if (e.getX() >= 691 && e.getX() <= 837 && 
            e.getY() >= 83 && e.getY() <= 221){//end row 1
                px = 704;
                py = 91;
                col = 4;
            }
            else if (e.getX() >= 251 && e.getX() <= 392 && 
            e.getY() >= 230 && e.getY() <= 372){
                px = 261;
                py = 238;
                row = 1;
                col = 1;
            }
            else if (e.getX() >= 403 && e.getX() <= 538 && 
            e.getY() >= 230 && e.getY() <= 372){
                px = 407;
                py = 239;
                row = 1;
                col = 2;
            }
            else if (e.getX() >= 547 && e.getX() <= 689 &&
            e.getY() >= 230 && e.getY() <= 372){
                px = 556;
                py = 238;
                row = 1;
                col = 3;
            }
            else if (e.getX() >= 691 && e.getX() <= 837 &&
            e.getY() >= 230 && e.getY() <= 372){
                //end row 2
                px = 702;
                py = 238;
                row = 1;
                col = 4;
            }
            else if (e.getX() >= 107 && e.getX() <= 240 &&
            e.getY() >= 376 && e.getY() <= 520){
                px = 112;
                py = 385;
                row = 2;
                col = 0;
            }
            else if (e.getX() >= 251 && e.getX() <= 392 &&
            e.getY() >= 376 && e.getY() <= 520){
                px = 260;
                py = 385;
                row = 2;
                col = 1;
            }
            else if (e.getX() >= 403 && e.getX() <= 538 &&
            e.getY() >= 376 && e.getY() <= 520){
                px = 407;
                py = 385;
                row = 2;
                col = 2;
            }
            else if (e.getX() >= 547 && e.getX() <= 689 &&
            e.getY() >= 376 && e.getY() <= 520){
                px = 556;
                py = 385;
                row = 2;
                col = 3;
            }
            else if (e.getX() >= 691 && e.getX() <= 837 &&
            e.getY() >= 376 && e.getY() <= 520){ 
                //end row 3
                px = 703;
                py = 385;
                row = 2;
                col = 4;
            }
            else if (e.getX() >= 107 && e.getX() <= 240 &&
            e.getY() >= 525 && e.getY() <= 666){
                px = 112;
                py = 535;
                row = 3;
                col = 0;
            }
            else if (e.getX() >= 251 && e.getX() <= 392 &&
            e.getY() >= 525 && e.getY() <= 666){
                px = 260;
                py = 535;
                row = 3;
                col = 1;
            }
            else if (e.getX() >= 403 && e.getX() <= 538 &&
            e.getY() >= 525 && e.getY() <= 666){
                px = 407;
                py = 535;
                row = 3;
                col = 2;
            }
            else if (e.getX() >= 547 && e.getX() <= 689 &&
            e.getY() >= 525 && e.getY() <= 666){
                px = 556;
                py = 535;
                row = 3;
                col = 3;
            }
            else if (e.getX() >= 691 && e.getX() <= 837 &&
            e.getY() >= 525 && e.getY() <= 666){ 
                //end row 4
                px = 703;
                py = 535;
                row = 3;
                col = 4;
            }
            else if (e.getX() >= 251 && e.getX() <= 392 &&
            e.getY() >= 672 && e.getY() <= 815){
                px = 260;
                py = 681;
                row = 4;
                col = 1;
            }
            else if (e.getX() >= 403 && e.getX() <= 538 &&
            e.getY() >= 672 && e.getY() <= 815){
                px = 407;
                py = 681;
                row = 4;
                col = 2;
            }
            else if (e.getX() >= 547 && e.getX() <= 689 &&
            e.getY() >= 672 && e.getY() <= 815){
                px = 556;
                py = 681;
                row = 4;
                col = 3;
            }
            else if (e.getX() >= 691 && e.getX() <= 837 &&
            e.getY() >= 672 && e.getY() <= 815){
                //end row 5
                px = 703;
                py = 681;
                row = 4;
                col = 4;
            }
            if (wasDraggedP1){
                wasDraggedP1 = false;
                if (card.alreadyHas(4) == true){
                    card.remove(4);
                }
                card.setBoard(4,row,col);
                p1x = px;
                p1y = py;
                if (px == 0 && p2x == 941 && p2y == 562){
                    p1x = 941;
                    p1y = 432;
                }
                else if (px == 0 && p2x == 941 && p2y == 
                432){ //432
                    p1x = 941;
                    p1y = 562;
                }
                else if (px == 0){
                    p1x = 941;
                    p1y = 562;
                }
                else if (px == p2x && py == p2y && p2y 
                != 562){
                    p1x = 941;
                    p1y = 562;
                }
                else if (px == p2x && py == p2y && p2y 
                == 562){
                    p1x = 941;
                    p1y = 432;
                }
            }
            else if (wasDraggedP2){
                wasDraggedP2 = false;
                if (card.alreadyHas(5) == true){
                    card.remove(5);
                }
                card.setBoard(5,row,col);
                p2x = px;
                p2y = py;
                if (px == 0 && p1x == 941 && p1y == 
                562){
                    p2x = 941;
                    p2y = 432;
                }
                else if (px == 0 && p1x == 941 && p1y 
                == 432){
                    p2x = 941;
                    p2y = 562;
                }
                else if (px == 0){
                    p2x = 941;
                    p2y = 562;
                }
                else if (px == p1x && py == p1y && p1y 
                != 562){
                    p1x = 941;
                    p1y = 562;
                }
                else if (px == p1x && py == p1y && p1y 
                == 562){
                    p1x = 941;
                    p1y = 432;
                }
            }
            repaint();
        }
    }

    /**
     * Repaints every time the mouse drags a piece around the
     * board before placeing it.
     * 
     * @param MouseEvent e gives information on the mouse event
     */
    public void mouseDragged(MouseEvent e) {

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
        repaint();
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
     * Loads the board and sets the dimensions for board1
     * 
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

        ImageIcon yellowH = new 
            ImageIcon("yellowHorizontal.jpg");
        yellowHorizontal = yellowH.getImage();

        ImageIcon won = new ImageIcon("youWon.png");
        youWon = won.getImage();

        ImageIcon questions = new 
            ImageIcon("smallQuestionIcon.png");
        instructions = questions.getImage();

        ImageIcon information = new ImageIcon("GameRules.jpg");
        info = information.getImage();

        ImageIcon purpleSpin = new 
            ImageIcon("purpleQuestionMovable.png");
        purpleQuestionMovable1 = purpleSpin.getImage();
        ImageIcon purpleTarget = new 
            ImageIcon("purpleRedCircleRotatable.png");
        purpleQuestionMovable2 = purpleTarget.getImage();
        purpleQuestionMovable3 = purpleSpin.getImage();
        purpleQuestionMovable4 = purpleSpin.getImage();
        purpleQuestionMovable5 = purpleSpin.getImage();
    }

    //Updates positions matrix in card class
    /**
     * Updates positions matrix in the card class.
     */
    public void updatePositions(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (card.board[i][j] == 3){ //purple rotatable
                    card.positions[i][j] = clickedPurple1 % 4;
                }
                else if (card.board[i][j] == 4){ //purple1
                    card.positions[i][j] = clickedPurple2 % 4;
                }
                else if (card.board[i][j] == 5){ //purple2
                    card.positions[i][j] = clickedPurple3 % 4;
                }
            }
        }
    }

    /**
     * Paints the board and paints the laser and how it will 
     * react to the different surfaces of the blocks it hits.
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
                g.drawImage(instructions,1068,720,null);
                //Creates Label for instructions
                Font courierBold10 = new Font("Courier", 
                        Font.BOLD, 13);
                g.setFont(courierBold10);
                g.drawString(" OPEN/CLOSE", 1020, 700);
                g.drawString("INSTRUCTIONS", 1020, 715);

                g.setColor(Color.RED);
                Graphics2D g2 = (Graphics2D) g;
                boolean hitWall = false;
                if (needsInfo % 2 == 1){
                    g.drawImage(info,250,83,null);
                }

                if (pressedRed == true){
                    g2.setStroke(new BasicStroke(5));
                    g2.setColor(Color.RED);
                    int firstPiece = 0;
                    int row = -1;
                    int laserDirection = 1;
                    boolean done = false;
                    if (done == false){
                        for (int i = card.getRow(1) + 1; i < 5;
                        i++){
                            if (card.board[i][1] != 0){
                                firstPiece = card.board[i][1];
                                row = i;
                                break;
                            }
                        }
                        //doesn't hit any pieces, hits wall
                        if (firstPiece == 0){ 
                            g2.drawLine(321,215,321,825);
                            done = true;
                        }
                        else { //piece is either 4 or 5
                            updatePositions();
                            if (card.getPos(firstPiece) == 1){
                                laserDirection = 2;
                                g2.drawLine(321,215,321,825 - 93
                                    - (150 * (4 - row)));
                            }
                            else if (card.getPos(firstPiece) == 
                            2){
                                laserDirection = 0;
                                g2.drawLine(321,215,321,825 - 93
                                    - (150 * (4 - row)));
                            }
                            else {
                                //Ends & doesn't reflect
                                laserDirection = -1; 
                                g2.drawLine(321,215,321,825 - 138
                                    - (150 * (4 - row)));
                                done = true;
                            }
                        }
                        int count = 1;

                        updatePositions();
                        //goes to the right
                        if (laserDirection == 2){ 
                            for (int k = 2; k < 5; k++){
                                updatePositions();
                                int r = card.getRow(firstPiece);
                                if (card.board[r][k] != 0){
                                    int lastPiece = 
                                        card.board[r][k];
                                    int pos = 
                                        card.positions[r][k];
                                    done = true;
                                    //end
                                    if (pos == 1 || pos == 0){
                                        g.drawLine(321,825 - 93 -
                                            (150 * (4 - r)), 713 - (150*(4-k)),825 - 93 - (150 * (4 - r)));
                                    }
                                    else if (pos == 2){ //goes up
                                        g.drawLine(321,825 - 93 -
                                            (150 * (4 - r)), 758 - (150*(4-k)),825 - 93 - (150 * (4 - r)));
                                        g.drawLine(758 - 
                                            (150*(4-k)),825 - 93 - (150 * (4 - row)),758 - 
                                            (150*(4-k)),66);
                                    }
                                    else { //goes down
                                        g.drawLine(321,825 - 93 - 
                                            (150 * (4 - r)), 758 - (150*(4-k)),825 - 93 - (150 * (4 - r)));
                                        g.drawLine(758 - 
                                            (150*(4-k)),825 - 93 - (150 * (4 - row)),758 - (150*(4-k)),825);
                                    }
                                    break;
                                }
                            }
                            if (!done){ //no piece, hits wall
                                g.drawLine(321,825 - 93 - (150 * 
                                        (4 - row)),851,825 - 93 - (150 * (4 - row)));
                            }
                        }
                        //goes to the left
                        else if (laserDirection == 0){ 
                            int newPiece = -1;
                            //theres piece next to 1st piece
                            if (card.board[row][0] != 0){ 
                                int piece = card.board[row][0];
                                newPiece = piece;
                                if (piece == 2){ //yellow
                                    g.drawLine(321, 282, 236, 
                                        282);
                                }
                                //purple rotatable
                                else if (piece == 3){ 
                                    if (card.positions[row][0] == 
                                    2 || card.positions[row][0]
                                    == 3){
                                        g.drawLine(321,732,235,732);
                                        laserDirection = 0;
                                    }
                                    else if 
                                    (card.positions[row][0] == 1){
                                        g.drawLine(321,732,179,732);
                                        laserDirection = 3;
                                    }
                                    else {
                                        g.drawLine(321,732,179,732);
                                        laserDirection = 1;
                                    }
                                }
                                else if (piece == 4 || piece
                                == 5){ //purple1
                                    if (card.positions[row][0] ==
                                    2 || card.positions[row][0]
                                    == 3){
                                        g.drawLine(321,825 - 93 -
                                            (150 * (4 - row)),235,825 - 93 - (150 * (4 - row)));
                                        laserDirection = 0;
                                    }
                                    else if 
                                    (card.positions[row][0] == 1){
                                        g.drawLine(321,825 - 93 -
                                            (150 * (4 - row)),179,825 - 93 - (150 * (4 - row)));
                                        laserDirection = 3;
                                    }
                                    else {
                                        g.drawLine(321,825 - 93 -
                                            (150 * (4 - row)),179,825 - 93 - (150 * (4 - row)));
                                        laserDirection = 1;
                                    }
                                }
                            }
                            else{ //there was no piece
                                g.drawLine(321,825 - 93 - (150 *
                                        (4 - row)),86,825 - 93 - (150 * (4 - row)));
                                laserDirection = 0;
                                done = true;
                            }
                            //After two lasers
                            //laser goes down
                            if (laserDirection == 1){ 
                                if (card.getRow(newPiece) != 4 
                                && newPiece != 2){
                                    if (card.getPos(3) == 1){
                                        g.drawLine(179,825 - 93 -
                                            (150 * (4 - row)),179,732);
                                        g.drawLine(179,732,851,732);
                                    }
                                    else if (card.getPos(3) == 
                                    2){
                                        g.drawLine(179,825 - 93 -
                                            (150 * (4 - row)),179,732);
                                        g.drawLine(179,732,87,732);
                                    }
                                    else {
                                        g.drawLine(179,825 - 93 -
                                            (150 * (4 - row)),179,687);
                                    }
                                }
                                else {
                                    g.drawLine(179,825 - 93 - (150 * (4 - row)),179,825); 
                                }
                            }
                            //laser goes up
                            else if (laserDirection == 3){ 
                                int thirdPiece = 0;
                                int tempRow = -1;
                                updatePositions();
                                for (int k = 
                                    card.getRow(newPiece) - 1; k >= 0; k--){
                                    if (card.board[k][0] == 4 ||
                                    card.board[k][0] == 5){
                                        thirdPiece = 
                                        card.board[k][0];
                                        tempRow = k;
                                        break;
                                    }
                                }
                                //if no pieces besides yellow
                                if (thirdPiece == 0){ 
                                    g.drawLine(179,825 - 93 -
                                        (150 * (4 - row)),179,65);
                                }
                                //hits side of red block
                                else if (tempRow == 0 && 
                                card.getPos(thirdPiece) == 0){ 
                                    g.drawLine(179,825 - 93 -
                                        (150 * (4 - row)),179,159);
                                    g.drawLine(179,159,259,159);
                                }
                                else if (card.getPos(thirdPiece)
                                == 1 || card.getPos(thirdPiece)
                                == 2){ //ends
                                    g.drawLine(179,825 - 93 -
                                        (150 * (4 - row)),179, 66
                                        + 138 + (150 * tempRow));
                                }
                                else if (card.getPos(thirdPiece) 
                                == 3){
                                    g.drawLine(179,825 - 93 - 
                                        (150 * (4 - row)),179, 66 
                                        + 93 + (150 * tempRow));
                                    g.drawLine(179, 66 + 93 + 
                                        (150 * tempRow),86,66 + 93
                                        + (150 * tempRow));
                                }
                                else if (card.getPos(thirdPiece) 
                                == 0){
                                    g.drawLine(179,825 - 93 - 
                                        (150 * (4 - row)),179, 66 
                                        + 93 + (150 * tempRow));
                                    g.drawLine(179, 66 + 93 + 
                                        (150 * tempRow),851,66 + 
                                        93 + (150 * tempRow));
                                }
                                if (card.solvedGame()){
                                    g.drawImage(youWon,400,150,null);
                                    EventQueue.invokeLater(new 
                                        Runnable() {
                                            protected String
                                            answer;
                                            @Override
                                            public void run() {
                                                enabled = false;
                                                Driver b1 = new 
                                                    Driver(1);
                                                Driver b2 = new 
                                                    Driver(2);
                                                Object[] options
                                                = { "Beginner","Intermediate", "Quit"};
                                                int result = 
                                                    JOptionPane.showOptionDialog(null,"Select a level",
                                                        "Laser Maze", JOptionPane.YES_NO_CANCEL_OPTION,
                                                        JOptionPane.QUESTION_MESSAGE, null, options, null);
                                                while (correct
                                                == false){
                                                    if (result ==
                                                    JOptionPane.YES_OPTION){
                                                        correct
                                                        =true;
                                                        b1.setVisible(true);
                                                    }
                                                    else if 
                                                    (result == JOptionPane.NO_OPTION){
                                                        correct 
                                                        = true;
                                                        b2.setVisible(true);
                                                    }
                                                    else if
                                                    (result == JOptionPane.CANCEL_OPTION){
                                                        correct
                                                        = true;
                                                        System.exit(0);
                                                    }
                                                    else {
                                                        result =
                                                        JOptionPane.showOptionDialog(null,"Select a level","Laser Maze",
                                                            JOptionPane.YES_NO_OPTION,
                                                            JOptionPane.QUESTION_MESSAGE, null, options, null);
                                                        correct
                                                        = false;
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
        else { //card2
            g.drawImage(boardPic2,0,0,null);
            g.drawImage(purpleQuestionMovable3,941,562,null);
            g.drawImage(purpleQuestionMovable4,941,432,null);
            g.drawImage(purpleQuestionMovable5,941,302,null);
        }
    }
    //JFrameVariable.setResisable = false
}
