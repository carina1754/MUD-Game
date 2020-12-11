  
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

      private int WALL = 0;//벽일시 0을 파일에 입력할 변수
      private int SPACE = 1;//통로일시 1을 파일에 입력할 변수

      private int[][] mapdata;//파일에 저장할 맵 전체 내용 배열
      private int w;//맵의 가로
      private int h;//맵의 세로
      private java.util.Random rand = new java.util.Random();//보스생성 시 필요한 랜덤 변수
      int bossloc[] = new int[100];//보스의 위치 저장
      private int bossnum = 2;//보스의 단계수
      
      
      
      
      public MazeObj(int w, int h) {//맵의 가로 세로 설정
         this.w = w;
         this.h = h;
         mapdata = new int[w][];
      }

      private void carve(int x, int y) {//맵 도굴 (미로 만들기)
         final int[] upx = { 1, -1, 0, 0 };// x축 배열
         final int[] upy = { 0, 0, 1, -1 };// y축 배열
         int random = rand.nextInt(4);//도굴할 맵의 랜덤으로 이동할 방향
         int count = 0;//4방향 카운트
         while(count < 4) {//방향이 4개 모두 돌떄까지
            final int x1 = x + upx[random];
            final int y1 = y + upy[random];
            final int x2 = x1 + upx[random];
            final int y2 = y1 + upy[random];
            if(mapdata[x1][y1] == WALL && mapdata[x2][y2] == WALL) {//4방향 중 2방향이 벽이면 통로로 변경 후 통로 마지막으로 이동
               mapdata[x1][y1] = SPACE;
               mapdata[x2][y2] = SPACE;
               carve(x2, y2);
            } else {//랜덤함수에 홀수를 더해서 4방향으로 나눈 나머지를 랜덤에 저장 후 카운트변수에 +1
               random = (random + 3) % 4;
               count++;
            }
         }
      }

      public void generate() {//맵 생성
         for(int x = 0; x < w; x++) {//가로만큼 반복하여 가로 배열에 세로 배열 입력
            mapdata[x] = new int[h];
            for(int y = 0; y < h; y++) {//맵 전체를 모두 벽으로 설정
               mapdata[x][y] = WALL;
            }
         }
         for(int x = 0; x < w; x++) {//가로 테두리 통로로 설정
            mapdata[x][0] = SPACE;
            mapdata[x][h - 1] = SPACE;
         }
         for(int y = 0; y < h; y++) {//세로 테두리 통로로 설정
            mapdata[0][y] = SPACE;
            mapdata[w - 1][y] = SPACE;
         }

         mapdata[2][2] = SPACE;
         carve(2, 2);

         mapdata[2][1] = SPACE;
         mapdata[w - 3][h - 2] = SPACE;
      }

      public void print() {//미로 파일로 출력
    	  try {
    		  for(int i=0;i<10;i++) {//보스를 랜덤적으로 10개 안으로 생성
    	  			bossloc[i] =(int) ((Math.random() * (20 - 50)) + 50);//보스의 간격 성정
    	  			System.out.println(bossloc[i]);
    	  			}
    		  int i=0,j=0;//i=보스의 수 j=길의 위치와 보스의 위치와 같을 시 사용하는 변수
			PrintWriter writer = new PrintWriter("load.map", "UTF-8");//파일 입출력 load.map에 저장
	         for(int y = 1; y < h-1; y++) {//세로 축 만큼 반복
	             for(int x = 1; x < w-1; x++) {//가로 축 만큼 반복
	                if(mapdata[x][y] == WALL) {//맵의 항목이 벽일 시
	                	if(x==1&&y==1) {//스타트 지점 설정
	                		writer.print("1");
	                		}
	                	else//벽은 0으로 지정
	                	writer.print("0");
	                } else {//맵의 항목이 벽이 아닐시
						if(bossloc[i]==j && i<30 && bossnum<9) {//보스의 위치와 보스의 랜덤 수와 같고 보스의 수가 10보다 작고 보스의 단계가 9보다 작을 시
							writer.print(Integer.toString(bossnum));
							bossnum++;
							i++;
							j=1;
						}
						else {//통로는 1로 지정
	                	writer.print("1");
	                	j++;
						}
	                }
	             }
	             writer.println();
	          }
	         writer.close();
		} catch (FileNotFoundException e) {//파일 예외처리
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {//인코딩 예외처리
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
   }

   public Maze() {//미로 생성
      MazeObj m = new MazeObj(33, 33);//새로운 미로 생성
      m.generate();//미로 생성
      m.print();//미로 파일로 프린트       
   }

}