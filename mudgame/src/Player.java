import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Player extends JPanel{
	int x, y;//가로 세로 변수
	int life = 5;//플레이어의 생명 변수
	int attack = 1;//플레이어의 공격력 변수
    public Player() {//플레이어 객체
    	this.setBackground(Color.getHSBColor(0.3f, 0.3f, 1));
    	this.setSize(play.panelSize, play.panelSize);
    }
    public Card hit(Boss boss, ArrayList<Card> deck,float f) {//플레이어 hit
    	Card card = boss.getCard(deck,f);
    	return card;//카드 return
    }
    public void moveLeft() {//플레이어의 왼쪽 움직임
    	if(x > 0 && play.map[x-1][y] >= 1){
	    	this.setLocation(this.getX()-25, this.getY());
	    	x--;
    	}
    }
    
    public void moveRight() {//플레이어의 오른쪽 움직임
    	if(x < play.columns-1 && play.map[x+1][y] >= 1){
	    	this.setLocation(this.getX()+25, this.getY());
	    	x++;
    	}
    }

    public void moveUp() {//플레이어의 위쪽 움직임
    	if(y > 0 && play.map[x][y-1] >= 1){
	    	this.setLocation(this.getX(), this.getY()-25);
	    	y--;
    	}
    }

    public void moveDown() {//플레이어의 아래쪽 움직임
    	if(y < play.rows-1 && play.map[x][y+1] >= 1){
	    	this.setLocation(this.getX(), this.getY()+25);
	    	y++;
    	}
    }
}
