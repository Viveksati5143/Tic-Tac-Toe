import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppGUI implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton[][] buttons = new JButton[3][3];
    private char[][] box = new char[3][3];
    private char player = 'X';
    private JButton resetButton;
    private JLabel turnLabel;
    private JButton quitButton;


    public AppGUI() {
        frame = new JFrame("Tic Tac Toe (X,0)");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
                buttons[row][col].addActionListener(this);
                panel.add(buttons[row][col]);
                box[row][col] = ' ';
            }
        }
        turnLabel = new JLabel("Turn: Player " + player);
        quitButton = new JButton("Quit");
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        panel.add(turnLabel);
        panel.add(resetButton);
        panel.add(quitButton);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void reset() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                box[row][col] = ' ';
                buttons[row][col].setBackground(null); // reset color
            }
        }
        player = 'X';
    }

    public static void main(String[] args) {
        new AppGUI();
    }

    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttonClicked == buttons[row][col]) {
                    if (box[row][col] == ' ') {
                        box[row][col] = player;
                        buttonClicked.setText(Character.toString(player));

                        Color color = (player == 'X') ? Color.GREEN : Color.pink;
                        buttonClicked.setBackground(color);

                        if (winner(box, player)) {
                            JOptionPane.showMessageDialog(null, "Player " + player + " has won!");
                            reset();
                        }
                        player = (player == 'X') ? '0' : 'X';

                        turnLabel.setText("Turn: Player " + player);
                        quitButton.setText("Quit");

                        // check for tie
                        boolean tie = true;
                        for (int r = 0; r < 3; r++) {
                            for (int c = 0; c < 3; c++) {
                                if (box[r][c] == ' ') {
                                    tie = false;
                                    break;
                                }
                            }
                            if (!tie) {
                                break;
                            }
                        }
                        if (tie) {
                            JOptionPane.showMessageDialog(null, "Game Over! It's a tie!");
                            reset();
                        }
                    }              
                    else {
                        JOptionPane.showMessageDialog(null, "Invalid move. Try again!");
                    }
                }
            }
        }
    }

    public static boolean winner(char[][] box, char player) {
        for (int row = 0; row < 3; row++) {
            if (box[row][0] == player && box[row][1] == player && box[row][2] == player) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (box[0][col] == player && box[1][col] == player && box[2][col] == player) {
                return true;
            }
        }

        if (box[0][0] == player && box[1][1] == player && box[2][2] == player) {
            return true;
        }

        if (box[0][2] == player && box[1][1] == player && box[2][0] == player) {
            return true;
        }

        return false;
    }
}