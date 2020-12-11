  
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;

// Maze generator in Java
// Joe Wingbermuehle
// 2015-07-27

public class Maze {

   private class MazeObj {

      private int WALL = 0;//���Ͻ� 0�� ���Ͽ� �Է��� ����
      private int SPACE = 1;//����Ͻ� 1�� ���Ͽ� �Է��� ����

      private int[][] mapdata;//���Ͽ� ������ �� ��ü ���� �迭
      private int w;//���� ����
      private int h;//���� ����
      private java.util.Random rand = new java.util.Random();//�������� �� �ʿ��� ���� ����
      int bossloc[] = new int[100];//������ ��ġ ����
      private int bossnum = 2;//������ �ܰ��
      
      
      
      
      public MazeObj(int w, int h) {//���� ���� ���� ����
         this.w = w;
         this.h = h;
         mapdata = new int[w][];
      }

      private void carve(int x, int y) {//�� ���� (�̷� �����)
         final int[] upx = { 1, -1, 0, 0 };// x�� �迭
         final int[] upy = { 0, 0, 1, -1 };// y�� �迭
         int random = rand.nextInt(4);//������ ���� �������� �̵��� ����
         int count = 0;//4���� ī��Ʈ
         while(count < 4) {//������ 4�� ��� ��������
            final int x1 = x + upx[random];
            final int y1 = y + upy[random];
            final int x2 = x1 + upx[random];
            final int y2 = y1 + upy[random];
            if(mapdata[x1][y1] == WALL && mapdata[x2][y2] == WALL) {//4���� �� 2������ ���̸� ��η� ���� �� ��� ���������� �̵�
               mapdata[x1][y1] = SPACE;
               mapdata[x2][y2] = SPACE;
               carve(x2, y2);
            } else {//�����Լ��� Ȧ���� ���ؼ� 4�������� ���� �������� ������ ���� �� ī��Ʈ������ +1
               random = (random + 3) % 4;
               count++;
            }
         }
      }

      public void generate() {//�� ����
         for(int x = 0; x < w; x++) {//���θ�ŭ �ݺ��Ͽ� ���� �迭�� ���� �迭 �Է�
            mapdata[x] = new int[h];
            for(int y = 0; y < h; y++) {//�� ��ü�� ��� ������ ����
               mapdata[x][y] = WALL;
            }
         }
         for(int x = 0; x < w; x++) {//���� �׵θ� ��η� ����
            mapdata[x][0] = SPACE;
            mapdata[x][h - 1] = SPACE;
         }
         for(int y = 0; y < h; y++) {//���� �׵θ� ��η� ����
            mapdata[0][y] = SPACE;
            mapdata[w - 1][y] = SPACE;
         }

         mapdata[2][2] = SPACE;
         carve(2, 2);

         mapdata[2][1] = SPACE;
         mapdata[w - 3][h - 2] = SPACE;
      }

      public void print() {//�̷� ���Ϸ� ���
    	  try {
    		  for(int i=0;i<10;i++) {//������ ���������� 10�� ������ ����
    	  			bossloc[i] =(int) ((Math.random() * (20 - 50)) + 50);//������ ���� ����
    	  			System.out.println(bossloc[i]);
    	  			}
    		  int i=0,j=0;//i=������ �� j=���� ��ġ�� ������ ��ġ�� ���� �� ����ϴ� ����
			PrintWriter writer = new PrintWriter("load.map", "UTF-8");//���� ����� load.map�� ����
	         for(int y = 1; y < h-1; y++) {//���� �� ��ŭ �ݺ�
	             for(int x = 1; x < w-1; x++) {//���� �� ��ŭ �ݺ�
	                if(mapdata[x][y] == WALL) {//���� �׸��� ���� ��
	                	if(x==1&&y==1) {//��ŸƮ ���� ����
	                		writer.print("1");
	                		}
	                	else//���� 0���� ����
	                	writer.print("0");
	                } else {//���� �׸��� ���� �ƴҽ�
						if(bossloc[i]==j && i<30 && bossnum<9) {//������ ��ġ�� ������ ���� ���� ���� ������ ���� 10���� �۰� ������ �ܰ谡 9���� ���� ��
							writer.print(Integer.toString(bossnum));
							bossnum++;
							i++;
							j=1;
						}
						else {//��δ� 1�� ����
	                	writer.print("1");
	                	j++;
						}
	                }
	             }
	             writer.println();
	          }
	         writer.close();
		} catch (FileNotFoundException e) {//���� ����ó��
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {//���ڵ� ����ó��
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
   }

   public Maze() {//�̷� ����
      MazeObj m = new MazeObj(33, 33);//���ο� �̷� ����
      m.generate();//�̷� ����
      m.print();//�̷� ���Ϸ� ����Ʈ       
   }

}