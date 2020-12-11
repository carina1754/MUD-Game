
import javax.swing.JPanel;

public class Tile extends JPanel{//타일 클래스
    int x, y;//가로 세로 변수
    boolean wall = true;//벽인지 아닌지 설정하는 변수
    
    public Tile(int x, int y){//타일 클래스 객체 
        this.x = x;//가로값 불러오기
        this.y = y;//세로값 불러오기
    }
    
    public void setWall(boolean wall){//벽으로 지정
        this.wall = wall;//벽을 true로 설정 = 벽으로 지정
    }
}
