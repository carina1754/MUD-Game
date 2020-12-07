import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Player extends JPanel{
	int x, y;
	int life = 5;
	int attack = 1;
    public Player() {
    	this.setBackground(Color.getHSBColor(0.3f, 0.3f, 1));
    	this.setSize(play.panelSize, play.panelSize);
    }
    public Card hit(Boss boss, ArrayList<Card> deck) {
    	Card card = boss.getCard(deck);
    	return card;
    }
    public void moveLeft() {
    	if(x > 0 && play.map[x-1][y] >= 1){
	    	this.setLocation(this.getX()-25, this.getY());
	    	x--;
    	}
    }

    public void moveRight() {
    	if(x < play.columns-1 && play.map[x+1][y] >= 1){
	    	this.setLocation(this.getX()+25, this.getY());
	    	x++;
    	}
    }

    public void moveUp() {
    	if(y > 0 && play.map[x][y-1] >= 1){
	    	this.setLocation(this.getX(), this.getY()-25);
	    	y--;
    	}
    }

    public void moveDown() {
    	if(y < play.rows-1 && play.map[x][y+1] >= 1){
	    	this.setLocation(this.getX(), this.getY()+25);
	    	y++;
    	}
    }
}
