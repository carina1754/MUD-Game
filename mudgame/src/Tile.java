
import javax.swing.JPanel;

public class Tile extends JPanel{//Ÿ�� Ŭ����
    int x, y;//���� ���� ����
    boolean wall = true;//������ �ƴ��� �����ϴ� ����
    
    public Tile(int x, int y){//Ÿ�� Ŭ���� ��ü 
        this.x = x;//���ΰ� �ҷ�����
        this.y = y;//���ΰ� �ҷ�����
    }
    
    public void setWall(boolean wall){//������ ����
        this.wall = wall;//���� true�� ���� = ������ ����
    }
}
