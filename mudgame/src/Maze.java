import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;

// Maze generator in Java
// Joe Wingbermuehle
// 2015-07-27

public class Maze {

   private static final class MazeObj {

      private static final int WALL = 0;
      private static final int SPACE = 1;

      private byte[][] data;
      private int width;
      private int height;
      private java.util.Random rand = new java.util.Random();
      int bossloc[] = new int[100];
      
      
      
      
      public MazeObj(int width, int height) {
         this.width = width;
         this.height = height;
         data = new byte[width][];
      }

      private void carve(int x, int y) {
         final int[] upx = { 1, -1, 0, 0 };
         final int[] upy = { 0, 0, 1, -1 };
         int dir = rand.nextInt(4);
         int count = 0;
         while(count < 4) {
            final int x1 = x + upx[dir];
            final int y1 = y + upy[dir];
            final int x2 = x1 + upx[dir];
            final int y2 = y1 + upy[dir];
            if(data[x1][y1] == WALL && data[x2][y2] == WALL) {
               data[x1][y1] = SPACE;
               data[x2][y2] = SPACE;
               carve(x2, y2);
            } else {
               dir = (dir + 1) % 4;
               count += 1;
            }
         }
      }

      public void generate() {
         for(int x = 0; x < width; x++) {
            data[x] = new byte[height];
            for(int y = 0; y < height; y++) {
               data[x][y] = WALL;
            }
         }
         for(int x = 0; x < width; x++) {
            data[x][0] = SPACE;
            data[x][height - 1] = SPACE;
         }
         for(int y = 0; y < height; y++) {
            data[0][y] = SPACE;
            data[width - 1][y] = SPACE;
         }

         data[2][2] = SPACE;
         carve(2, 2);

         data[2][1] = SPACE;
         data[width - 3][height - 2] = SPACE;
      }

      public void print() {
    	  try {
    		  for(int i=0;i<10;i++) {
    	  			bossloc[i] =(int) ((Math.random() * (10 - 50)) + 50);
    	  			System.out.println(bossloc[i]);
    	  			}
    		  int i=0,j=0;
			PrintWriter writer = new PrintWriter("load.map", "UTF-8");
	         for(int y = 1; y < height-1; y++) {
	             for(int x = 1; x < width-1; x++) {
	                if(data[x][y] == WALL) {
	                	if(x==1&&y==1) {
	                		writer.print("1");
	                		}
	                	else
	                	writer.print("0");
	                } else {
						if(bossloc[i]==j && i<30) {
							writer.print("2");
							i++;
							j=1;
						}
						else {
	                	writer.print("1");
	                	j++;
						}
	                }
	             }
	             writer.println();
	          }
	         writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
   }

   public Maze() {
      MazeObj m = new MazeObj(33, 33);
      m.generate();
      m.print();       
   }

}