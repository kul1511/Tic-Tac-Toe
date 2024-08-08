import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;//50px for text panel displaying the winner on top
 
    JFrame frame = new JFrame();
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board =  new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    Boolean gameOver =  false;
    int turns = 0;


    TicTacToe(){

        //setting the borad to be visible on running the code
        frame.setVisible(true);//to make visible when click on run
        frame.setSize(boardWidth, boardHeight);//set the dimension 
        frame.setLocationRelativeTo(null);// this will be opening the window on the center
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //customizing the tic tac toe row
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        //aligning the panel at the top
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);


        //creating the 3x3 matrix the the game
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        //creating the six buttons
        for(int r =0; r < 3; r++){
            for(int c =0; c < 3; c++){
               JButton tile = new JButton();
               board[r][c] = tile;
               boardPanel.add(tile);

               //stying the six buttons
               tile.setBackground(Color.DARK_GRAY);
               tile.setForeground(Color.WHITE);
               tile.setFont(new Font("Arial", Font.BOLD, 120));
               tile.setFocusable(false);
               //tile.setText(currentPlayer);

               //adding values / text to the button for X
               tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(gameOver)return;
                    JButton tile = (JButton) e.getSource();
                
                    //here we are restricting that values cannot be changed and the tile should be empty to enter anything.
                    if(tile.getText() == ""){
                        tile.setText(currentPlayer);
                        turns++;
                        checkWinner();
                        if(!gameOver){
                            currentPlayer = currentPlayer == playerX ? playerO : playerX;
                            textLabel.setText(currentPlayer + "'s turn.");
                        }
                        
                    }
                    
                }
               });
            }
        }

    }

    void checkWinner(){
        // horizontally
        for(int r = 0; r < 3; r++){

            if(board[r][0].getText() == "")continue;

            if(board[r][0].getText() == board[r][1].getText() && 
            board[r][1].getText() == board[r][2].getText()) {
                for(int i=0;i<3;i++){
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        //vertically
        for(int c = 0; c < 3; c++){

            if(board[0][c].getText() == "")continue;

            if(board[0][c].getText() == board[1][c].getText() && 
            board[1][c].getText() == board[2][c].getText()) {
                for(int i=0;i<3;i++){
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        //digonally
        if(board[0][0].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[2][2].getText() &&
        board[0][0].getText()!=""){
            for(int i=0;i<3;i++){
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        //anti- digonally
        if(board[0][2].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[2][0].getText() &&
        board[0][2].getText()!=""){
            
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }  
        

        //tie
        if(turns == 9){
            for(int r = 0; r < 3; r++){
                for(int c =0; c < 3; c++){
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }


    }

    void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!");
        
    }


    void setTie(JButton tile){
        tile.setBackground(Color.gray);
        tile.setForeground(Color.orange);
        textLabel.setText("Tie!");
    }
}
