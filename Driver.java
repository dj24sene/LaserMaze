import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Where the LazerMaze is created and ran
 *
 * @author Brianna Davis, Zach Giannuzzi, Abdul Samad, 
 * Eric Sauer, Daniel Senecal
 * @version 4/30/2018
 */
public class Driver extends JFrame
{
    /**
     * Constructs a driver object based on which board the user
     * prompts for.
     * 
     * @param board determines which board difficulty will be
     * loaded based on which is picked
     */
    public Driver(int board) {
        if (board == 1){
            initUI1();
        }
        else {
            initUI2();
        }

    }
    /**
     * Loads the board and images needed if the board is selected 
     * as beginner
     */
    private void initUI1() {
        add(new LaserMaze1(true));
        pack();
        setTitle("Board");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    /**
     * Loads the board and images needed if the board is selected 
     * as intermediate
     */
    private void initUI2() {

        add(new LaserMaze2(false));

        pack();
        setResizable(false);
        setTitle("Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    /**
     * When ran, it creates a JOptionPane for the user to select 
     * a difficulty level and prompts the user until one is 
     * selected.
     * 
     * @param args is not used.
     */
    public static void main(String[] args) { 

        EventQueue.invokeLater(new Runnable() {
                protected String answer;
                @Override
                public void run() {
                    boolean correct = false;
                    Driver b1 = new Driver(1);
                    Driver b2 = new Driver(2);
                    Object[] options = { "Beginner","Intermediate"};
                    int result = 
    JOptionPane.showOptionDialog(null,"Select a level","Laser Maze", 
    JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, null);
                    while (correct == false){
                        if (result == JOptionPane.YES_OPTION){
                            correct = true;
                            b1.setVisible(true);
                        }
                        else if (result == JOptionPane.NO_OPTION){
                            correct = true;
                            b2.setVisible(true);
                        }
                        else {
                            result = 
  JOptionPane.showOptionDialog(null,"Select a level","Laser Maze", 
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, 
                                null, options, null);
                            correct = false; //set exit on close
                        }
                    }
                }
                
            });
    }
}
