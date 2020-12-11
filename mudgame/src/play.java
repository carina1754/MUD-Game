import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import java.util.ArrayList;
import java.util.Random;
	
public class play extends JFrame{
	
	static int[] width = {775, 285};//안내 판넬의 가로 사이즈
    static int[] height = {300, 100, 62};//안내 판넬의 세로 사이즈
    
    static int rostr = 1;//라운드 수
    
    static String upstr = new String("↑");//위쪽 표시
    static String downstr = new String("↓");//아래쪽 표시
    static String rightstr = new String("→");// 오른쪽 표시
    static String leftstr = new String("←");//왼쪽 표시
    
    public static int rows = 31;//맵의 가로축 길이
    public static int columns = 31;//맵의 세로축 길이
    public static int panelSize = 25;//맵 타일 하나의 사이즈
    public static int map[][] = new int[columns+1][rows+1];//지도 배열
    public static int endLevelLoc=30;//출구의 y축 위치
    
    Player player;//플레이어 객체 생성
    Random random = new Random();//카드를 랜덤으로 뽑기 위한 변수
    int gameflag = 0;//플레이어의 체력 FLAG
     int x;//현재 위치의 가로 값
	 int y;//현재 위치의 세로 값
	 int count;//처치한 보스의 수
	 int bossnum=0;//맵에 표시된 보스의 수
	 int bossmap[][] = new int [9][4];//보스 저장 배열
	 int bossattack =1;//보스 기본 공격력(1단계)
	 int bosshealth = 5;//보스 기본 체력(1단계)
	 int aceval = 11;//초기 플레이어 ACE값 (블랙잭을 위해 11로 설정)
	 int baceval = 11;//초기 보스 ACE값 (블랙잭을 위해 11로 설정)
	 
    public play() {
    	BevelBorder border = new BevelBorder(BevelBorder.RAISED);//안내에 사용할 BROEDER모양 객체
    	JPanel pn = new JPanel();//키 입력 받을때마다 새로고침해줄 판넬 생성
    	pn.setLayout(null);
    	new Maze();//랜덤 맵 생성
    	loadMap("load.map");//맵로딩
    	
        this.setSize(1075,814);//맵크기 설정
        this.setTitle("2019253071 안정수");//타이틀 설정
        this.setLayout(null);
    	this.setVisible(true);
    	
    	JLabel round = new JLabel(Integer.toString(rostr) + " 라운드" , JLabel.CENTER);//라운드 수 출력
    	JLabel instruction = new JLabel("<html>A &nbsp; : &nbsp; 왼쪽 &nbsp; 이동 &nbsp;&nbsp; D &nbsp; : &nbsp; 오른쪽 &nbsp; 이동 "
    									+ "<br>" + "W &nbsp; : &nbsp; 위로 &nbsp; 이동 &nbsp;&nbsp; S &nbsp; : &nbsp; 아래로 &nbsp; 이동 ",JLabel.CENTER);//방향키 안내
    	
    	JLabel ur = new JLabel(upstr+rightstr,JLabel.CENTER);	//UP + RIGHT
    	JLabel ul = new JLabel(upstr+leftstr,JLabel.CENTER);	//UP + LEFT
    	JLabel ulr = new JLabel(upstr+leftstr+rightstr,JLabel.CENTER);//UP + LEFT + RIGHT
    	JLabel u = new JLabel(upstr,JLabel.CENTER);	//UP
    	JLabel dr = new JLabel(downstr+rightstr,JLabel.CENTER);	//DOWN + RIGHT
    	JLabel dl = new JLabel(downstr+leftstr,JLabel.CENTER);	//DOWN + LEFT
    	JLabel d = new JLabel(downstr,JLabel.CENTER);	//DOWN
    	JLabel du = new JLabel(upstr+downstr,JLabel.CENTER);	//DOWM + UP
    	JLabel lr = new JLabel(leftstr+rightstr,JLabel.CENTER);	//LEFT + RIGHT
    	JLabel l = new JLabel(leftstr,JLabel.CENTER);//LEFT
    	JLabel r = new JLabel(rightstr,JLabel.CENTER);//RIGHT
    	JLabel dlr = new JLabel(leftstr+rightstr+downstr,JLabel.CENTER);	//DOWN + LEFT + RIGHT
    	JLabel dur = new JLabel(downstr+rightstr+upstr,JLabel.CENTER);	//DOWN + UP + RIGHT
    	JLabel dul = new JLabel(leftstr+upstr+downstr,JLabel.CENTER);	//DOWN + UP + LEFT
    	JLabel durl = new JLabel(leftstr+rightstr+downstr+upstr,JLabel.CENTER);	//DOWN + UP + RIGHT + LEFT
    	
    	instruction.setBounds(775, 161, width[1], height[1]);
    	round.setBounds(775, 100, width[1], height[2]);
    	
    	ur.setBounds(775, 0, width[1], height[1]);
    	ul.setBounds(775, 0, width[1], height[1]);
    	ulr.setBounds(775, 0, width[1], height[1]);
    	u.setBounds(775, 0, width[1], height[1]);
    	dr.setBounds(775, 0, width[1], height[1]);
    	dl.setBounds(775, 0, width[1], height[1]);
    	d.setBounds(775, 0, width[1], height[1]);
    	du.setBounds(775, 0, width[1], height[1]);
    	lr.setBounds(775, 0, width[1], height[1]);
    	l.setBounds(775, 0, width[1], height[1]);
    	r.setBounds(775, 0, width[1], height[1]);
    	dlr.setBounds(775, 0, width[1], height[1]);
    	dur.setBounds(775, 0, width[1], height[1]);
    	dul.setBounds(775, 0, width[1], height[1]);
    	durl.setBounds(775, 0, width[1], height[1]);
    	
    	pn.add(round);
    	pn.add(instruction);
    	
    	pn.add(u);
    	pn.add(ul);
    	pn.add(ur);
    	pn.add(ulr);
    	pn.add(d);
    	pn.add(dl);
    	pn.add(dr);
    	pn.add(du);
    	pn.add(lr);
    	pn.add(l);
    	pn.add(r);
    	pn.add(dlr);
    	pn.add(dur);
    	pn.add(dul);
    	pn.add(durl);
    	
    	round.setBorder(border);
    	instruction.setBorder(border);
    	
    	u.setBorder(border);
    	ul.setBorder(border);
    	ur.setBorder(border);
    	ulr.setBorder(border);
    	d.setBorder(border);
    	dl.setBorder(border);
    	dr.setBorder(border);
    	du.setBorder(border);
    	lr.setBorder(border);
    	l.setBorder(border);
    	r.setBorder(border);
    	dlr.setBorder(border);
    	dur.setBorder(border);
    	dul.setBorder(border);
    	durl.setBorder(border);
    	this.setContentPane(pn);
    	        this.setLocationRelativeTo(null);
    	        
    	    	player = new Player();//플레이어 객체 생성
    	    	player.setVisible(true);
    	    	player.setLocation(0,0);//위치선정
    	    	player.life =5;//체력 설정
    	    	this.add(player);
    	    	
    	        for(int y = 0; y < columns; y++){//맵의 세로수 만큼
    	            for(int x = 0; x < rows; x++){//맵의 가로수 만큼
    	                Tile tile = new Tile(x, y);//색칠 타일 객체 생성
    	                tile.setSize(panelSize, panelSize);
    	                tile.setLocation((x*panelSize), (y*panelSize));
    	                if((x==29&&y==30))//만약 출구일 시
    	                    tile.setBackground(Color.magenta);
    	                else if(map[x][y] == 0){//벽일 시
    	                    tile.setBackground(Color.GRAY);
    	                }
    	                else if(map[x][y]>=2) {//보스일 시

        	                tile.setSize(panelSize-6, panelSize-6);
        	                tile.setLocation((x*panelSize+3), (y*panelSize+3));

        	                tile.setWall(false);
    	                	tile.setBackground(Color.RED);
    	                }
    	                else{//통로일 시
    	                    tile.setBackground(Color.WHITE);
    	                    tile.setWall(false);
    	                    if(x == 0){//플레이어의 위치선정
    	                    	player.setLocation((x*panelSize), (y*panelSize));
    	                    	player.y = y;
    	                    }
    	                }
    	                tile.setVisible(true);
    	                this.add(tile);
    	            }
    	        }
    	        
		    	u.setVisible(false);
		    	ul.setVisible(false);
		    	ur.setVisible(false);
		    	ulr.setVisible(false);
		    	d.setVisible(false);
		    	dr.setVisible(false);
		    	dl.setVisible(false);
		    	dlr.setVisible(false);
		    	du.setVisible(false);
		    	dur.setVisible(false);
		    	dul.setVisible(false);
		    	durl.setVisible(false);
		    	lr.setVisible(false);
		    	l.setVisible(false);
		    	r.setVisible(false);
    	        this.setVisible(true);
    	        this.setContentPane(pn);
    	        
    	        this.addKeyListener(new KeyListener(){//키 입력 이벤트

    				@Override
    				public void keyPressed(KeyEvent e) {//키 누름 이벤트
    					int key = e.getKeyCode();//키 이벤트 변수
    					revalidate();//새로 계속 갱신
    					repaint();//새로 계속 갱신
    			    	u.setVisible(false);
    			    	ul.setVisible(false);
    			    	ur.setVisible(false);
    			    	ulr.setVisible(false);
    			    	d.setVisible(false);
    			    	dr.setVisible(false);
    			    	dl.setVisible(false);
    			    	dlr.setVisible(false);
    			    	du.setVisible(false);
    			    	dur.setVisible(false);
    			    	dul.setVisible(false);
    			    	durl.setVisible(false);
    			    	lr.setVisible(false);
    			    	l.setVisible(false);
    			    	r.setVisible(false);
    					//Player movement
    			    	
    					if(key == KeyEvent.VK_W){//위로 이동
    						player.moveUp();	
    					}
    					
    					if(key == KeyEvent.VK_A){//왼쪽으로 이동
    						player.moveLeft();	
    					}
    					
    					if(key == KeyEvent.VK_S){//아래로 이동
    						player.moveDown();	
    					}
    					
    					if(key == KeyEvent.VK_D){//오른쪽으로 이동
    						player.moveRight();	
    					}
    			
						x=player.x;
						y=player.y;
						System.out.println("좌표 : " + x +" "+ y +" 값 : " + map[x][y]);
						
						if(x==0 && y == 0)//처음 위치 표시
							r.setVisible(true);
						
						else if(x==1 && y==0)//처음 위치 표시
							dl.setVisible(true);
						
						else {//현재 위치 표시
						if(map[x+1][y]>=1 && map[x-1][y]==0 &&  map[x][y+1]==0 && map[x][y-1] ==0)
							r.setVisible(true);
						if(map[x+1][y]>=1 && map[x-1][y]>=1 &&  map[x][y+1]==0 && map[x][y-1] ==0)
							lr.setVisible(true);
						if(map[x+1][y]>=1 && map[x-1][y]>=1 &&  map[x][y+1]>=1 && map[x][y-1] ==0 )
							dlr.setVisible(true);
						if(map[x+1][y]>=1 && map[x-1][y]>=1 &&  map[x][y+1]>=1 && map[x][y-1] >=1 )
							durl.setVisible(true);		
						if(map[x+1][y]==0 && map[x-1][y]>=1 &&  map[x][y+1]==0 && map[x][y-1] ==0 )
							l.setVisible(true);
						if(map[x+1][y]==0 && map[x-1][y]>=1 &&  map[x][y+1]>=1 && map[x][y-1] ==0 )
							dl.setVisible(true);				
    					if(map[x+1][y]==0 && map[x-1][y]>=1 &&  map[x][y+1]>=1 && map[x][y-1] >=1 )
    						dul.setVisible(true);
        				if(map[x+1][y]==0 && map[x-1][y]==0 &&  map[x][y+1]>=1 && map[x][y-1] >=1 )
        					du.setVisible(true);
            			if(map[x+1][y]==0 && map[x-1][y]==0 &&  map[x][y+1]==0 && map[x][y-1] >=1 )
            				u.setVisible(true);
                		if(map[x+1][y]>=1 && map[x-1][y]==0 &&  map[x][y+1]>=1 && map[x][y-1] ==0 )
                			dr.setVisible(true);
                    	if(map[x+1][y]>=1 && map[x-1][y]==0 &&  map[x][y+1]>=1 && map[x][y-1] >=1 )
                    		dur.setVisible(true);
                        if(map[x+1][y]==0 && map[x-1][y]==0 &&  map[x][y+1]>=1 && map[x][y-1] ==0 )
                        	d.setVisible(true);
                        if(map[x+1][y]>=1 && map[x-1][y]>=1 &&  map[x][y+1]==0 && map[x][y-1] >=1 )
                        	ulr.setVisible(true);
                        if(map[x+1][y]>=1 && map[x-1][y]==0 &&  map[x][y+1]==0 && map[x][y-1] >=1 )
                        	ur.setVisible(true);
                        if(map[x+1][y]==0 && map[x-1][y]>=1 &&  map[x][y+1]==0 && map[x][y-1] >=1 )
                        	ul.setVisible(true);
                        if(map[x+1][y]==0 && map[x-1][y]==0 &&  map[x][y+1]==0 && map[x][y-1] >=1 )
							u.setVisible(true);	
						}
						
					 	if(player.life <= 0) {//플레이어의 체력이 없을때
    					    String[] nolife = {"다시시작","그만하기"};
					        int nolifepkg = JOptionPane.showOptionDialog(null, "체력이 모두 깎었습니다. 다시 하시겠습니까?", "The END",
					                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, nolife, "그만하기");
					        if(nolifepkg==0) {
					        	gameflag =1;
    						}
					        else
					        	System.exit(0);
						}
					 	
					 	if(map[x][y]>=2) {//맵에서 보스가 등장할 경우
					    	int flag =0;
					    	if(bossnum<8) {//보스의 수가 8개보다 작을 경우
					    	for(int l=0;l<8;l++) {//보스의 수만큼 반복
					    		if(bossmap[l][0] == x && bossmap[l][1] == y) {//보스배열에 이미 저장되어 있으면
					    			flag=0;
					    		break;
					    		}
					    		else //저장이 안되어있으면
					    			flag=1;
					    	}
					    	if(flag==1){//새로운 보스일 시
					    			System.out.print("보스 진입");
					    			for(int l1=0;l1<8;l1++) {//보스의 최대 수 8만큼 반복
							    		if(bossmap[l1][0] != x || bossmap[l1][1] != y ) {//만약 보스배열에 저장되어있지 않을 경우 새로운 보스 저장
									        bossmap[bossnum][0] = x;
									 		bossmap[bossnum][1] = y;
									 		bossmap[bossnum][2] = bosshealth;
									 		bossmap[bossnum][3] = bossattack;
									 		
									 		bosshealth++;
									 		bossattack++;
									 		bossnum++;
									 		
									 		System.out.print(bossnum);
									 		break;
							    			}
							    		else
							    			break;
					    			}
					    		}
					    	}
					 				for(int i=0;i<bossnum;i++) {
					 					if(bossmap[i][0] == x && bossmap[i][1] == y && bossmap[i][2]>0) {//만약 보스위치와 플레이어의 위치가 같고 보스의 체력이 0보다 클때
					 						String[] buttons = {"배틀시작","도망가기"};
									        int num = JOptionPane.showOptionDialog(null, i+1 + "단계 보스입니다. 배틀을 시작하겠습니까?", "BOSS 출현!",
									                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "도망가기");
						    	    		System.out.print("보스 출현");
						    	    		int result = 4;
						    	    		
						    	    		for(;;) {//게임 진행을 위한 무한 루프
						    	    				
					        			if(num==0 && result == 4 && bossmap[i][2]>0) {//보스의 체력이 0보다 크고 보스가 존재할 시 게임 시작
					        				pn.setVisible(false);
					        				Boss boss = new Boss();
					 						Rule rule = new Rule();
					 					
					    					revalidate();
					    					repaint();
					 						ArrayList<Card> deck = boss.setCard();
					 						ArrayList<Card> bossCard = new ArrayList<Card>();
					 						ArrayList<Card> playerCard = new ArrayList<Card>();
					 						
					 						System.out.println("블랙잭 게임 시작");
					 						
					 						bossCard.add(boss.getCard(deck,random.nextFloat()));
					 						bossCard.add(boss.getCard(deck,random.nextFloat()));
					 						playerCard.add(boss.getCard(deck,random.nextFloat()));
					 						playerCard.add(boss.getCard(deck,random.nextFloat()));
					 						rule.intro = "\0";	
					 						int bossSum = rule.printIntro("boss",bossCard,baceval);
					 						int playerSum = rule.printIntro("player",playerCard,aceval);
					 						rule.printCard("boss",bossCard,baceval);
					 						rule.printCard("player",playerCard,aceval);
					 						JOptionPane.showMessageDialog(null, "현재 보스의 체력 : " + bossmap[i][2] + " 나의 체력 : " + player.life);
					 						
					 						while(!(rule.bust(bossSum))) {//보스가 버스트될 때까지
					 							
					 							if((playerSum == 21 || bossSum ==21) && playerCard.size()<=2) {//플레이어와 보스 둘 중 블랙잭일시
					 								
                                                    if(bossSum != 21) {//보스가 블랙잭이 아닐시 플레이어가 블랙잭
                                                       String[] blackwin = {"계속하기"};
                                                       int blackwinpkg = JOptionPane.showOptionDialog(null, rule.result + "\r\n" + "블랙잭입니다!", "YOU WIN",
                                                               JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, blackwin, "계속하기");
                                                       if(blackwinpkg == 0) {//계속하기
                                                           result = 4;
                                                           bossmap[i][2] -= 2;
                                                           break;
                                                       }
                                                    }
                                                    
                                                    else {
                                                    	JOptionPane.showMessageDialog(null, rule.result);
                                                        String[] blacklose = {"계속하기"};
                                                        int blacklosepkg = JOptionPane.showOptionDialog(null, rule.result + "\r\n" + "블랙잭입니다!", "YOU LOSE",
                                                               JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, blacklose, "계속하기");
                                                       if(blacklosepkg == 0) {//계속하기
                                                           result = 4;
                                                            player.life -= bossmap[i][3]*2;
                                                           break;
                                                       }
                                                    }
                                                }
					 							else//블랙잭이 아니면 실행
					 							{
					 								rule.intro = "\0";	
					 								bossSum = rule.printIntro("boss",bossCard,baceval);
							 						playerSum = rule.printIntro("player",playerCard,aceval);
					 							String[] black = {"hit","stand"};
										        int jack = JOptionPane.showOptionDialog(null, rule.intro , "YOUR TURN",
										                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, black, "stand");
										        	
										        if(jack==0) {//hit 시
										        	if(rule.bust("boss", bossSum)) {//보스가 버스트될 때 플레이어 승
										        		rule.result = "\0";
										        		rule.printCard("boss",bossCard,baceval);
								 						rule.printCard("player",playerCard,aceval);
										        		JOptionPane.showMessageDialog(null, rule.result + "\r\n" + bossnum + "단계 보스에게 이겼습니다.");
										        		result=4;
									        			bossmap[i][2]--;
										        		break;
										        	}
										        	
										        	Card card = player.hit(boss, deck,random.nextFloat());
										        	playerCard.add(card);
										        	rule.result = "\0";	
										        	bossSum = rule.printCard("boss",bossCard,baceval);
										        	playerSum = rule.printCard("player", playerCard,aceval);
										        	
										        	if(rule.bust("player", playerSum)) {//플레이어가 버스트될 때 보스 승
								 						rule.printIntro("player",playerCard,aceval);
						    							JOptionPane.showMessageDialog(null,bossnum + "단계 보스에게 졌습니다.");
										        		result = 4;
										        		player.life -= bossmap[i][3];
										        		break;
										        	}
										        	else//플레이어가 버스트가 아닐 시 보스가 카드를 뽑음
										        		bossCard.add(boss.getCard(deck,random.nextFloat()));
										        }
										        
										        else if(jack == 1) {//스탠드 시
										        	result = rule.game(bossSum, playerSum);
										        	if(rule.bust("boss", bossSum)) {//보스가 버스트될 때 플레이어 승
										        		rule.result = "\0";
										        		rule.printCard("boss",bossCard,baceval);
								 						rule.printCard("player",playerCard,aceval);
										        		JOptionPane.showMessageDialog(null, rule.result + "\r\n" + bossnum + "단계 보스에게 이겼습니다.");
										        		result=4;
									        			bossmap[i][2]--;
										        		break;
										        	}
										        	else if(result == 1) {//보스에게 이겼을 때
										        		rule.result = "\0";
										        		rule.printCard("boss",bossCard,baceval);
								 						rule.printCard("player",playerCard,aceval);
						    							JOptionPane.showMessageDialog(null, rule.result + "\r\n" + bossnum + "단계 보스에게 이겼습니다.");
										        			bossmap[i][2]--;
										        			result=4;
										        			break;
										        	}
										        	else if(result == 0) {//보스에게 졌을 때
										        		rule.result = "\0";
										        		rule.printCard("boss",bossCard,baceval);
								 						rule.printCard("player",playerCard,aceval);
						    							JOptionPane.showMessageDialog(null,rule.result + "\r\n" + bossnum + "단계 보스에게 졌습니다.");
										        		result=4;
										        		player.life -= bossmap[i][3];
										        		break;
										        	}
										        	else if(result == 2) {//보스에게 비겼을 때
										        		rule.result = "\0";
										        		rule.printCard("boss",bossCard,baceval);
								 						rule.printCard("player",playerCard,aceval);
										        		String[] push = {"계속하기"};
										        		int pushpkg = JOptionPane.showOptionDialog(null, rule.result + "\r\n" +bossnum + "단계 보스에게 비겼습니다.", "DRAW",
												                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, push, "계속하기");
										        	     if(pushpkg==0) {//비겼을 때
													        	result = 4;
													        	break;
													        	}
										        	}
										        	else
										        		break;
										        }
										        
										        else
										        	break;
					 						}
					 						}
					 						
					 						if(num==0 && player.life <= 0) {//플레이어가 0 이하이고 보스가 사라지지 않을 떄 
					 							bossCard = boss.bossGetCard(bossSum,deck,bossCard,random.nextFloat());
					 							if(bossSum >=17) {//18보다 크면 ace 값은 1
					 								baceval = 1;
					 							bossSum = rule.getSum(bossCard, baceval);
					 							}
					 							else {//아니면 11
					 								baceval = 11;
					 								bossSum = rule.getSum(bossCard, baceval);
					 							}
						 						player.life -= bossmap[i][3];
						 							if(player.life <= 0) {//플레이어의 체력이 0이하 일때
						 	    					    String[] nolife = {"다시시작","그만하기"};
						 						        int nolifepkg = JOptionPane.showOptionDialog(null, "체력이 모두 깎었습니다. 다시 하시겠습니까?", "The END",
						 						                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, nolife, "그만하기");
						 						        if(nolifepkg==0) {//피가 다 깎여 다시시작할 때 gmaeflag = 1 
						 						        	gameflag = 1;
						 	    						break;
						 	    						}
						 						        else//게임 종료
						 						        	System.exit(0);
						 							}
					 						}
					 						else
					 							continue;
						 				}
					        			
							        	else if(num==0 && bossmap[i][2] <= 0) {//만약 보스의 피가 다 닳았을 때
						        			player.life += 2;
							        		String[] win = {"계속하기"};
							        		int winpkg = JOptionPane.showOptionDialog(null, bossnum + "단계 보스를 물리쳤습니다.", "YOU WIN",
									                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, win, "계속하기");
							        		if(winpkg == 0) {//계속하기
							        			pn.setVisible(true);
							        			num=1;
							        			break;
							        		}
							        	}
					        			else
					        				break;
					        			}
						    	    		}
					        		}
					 				
		            	}					 	
					 	
					 	if(gameflag == 1) {//플레이어가 피가 다 깎여서 게임이 끝났을 떄 새로운 게임 시작
					 		player.life=5;
					 		dispose();
					 		new play();
					 	}
					 	
    					if(x == 29 && y == endLevelLoc){//도착지에 도착했을 때
    						if(count>=4) {//보스를 4마리 이상 잡았을 때 새로운 게임 시작
    					    String[] buttons = {"다음 라운드","그만하기"};
					        int num = JOptionPane.showOptionDialog(null, "보물을 찾았습니다! 다음 라운드로 진행 하겠습니까?", "보물 찾기 성공!",
					                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "다음 라운드");
					        player.life=5;
    						dispose();
    						rostr++;
    						new play();
    						}
    						else//보스를 4마리 이상 잡지 못했을 때
    							JOptionPane.showMessageDialog(null, "보스를 4마리 이상 잡고 돌아오세요.");
    					}
    				}

					@Override
    				public void keyReleased(KeyEvent arg0) {
    					// TODO Auto-generated method stub
    				}

    				@Override
    				public void keyTyped(KeyEvent arg0) {
    					// TODO Auto-generated method stub
    				}
    	        });
    	        this.addWindowListener(new WindowAdapter(){
    	            public void windowClosing(WindowEvent e) {
    	                System.exit(0);
    	            }
    	        });
    
    }
	
public void loadMap(String str){//파일의 맵을 2차원배열로 저장
    try{
        BufferedReader br = new BufferedReader(new FileReader(str));//파일을 읽기 위한 버퍼 변수
        StringBuilder sb = new StringBuilder();//파일의 내용 읽기 변수
        String line = br.readLine();//파일에서 줄바꿈 읽기 변수

        while (line != null) {//모든 파일을 읽을 때 까지
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        
        String mappasing = sb.toString();//문자열 읽고 string형으로 변환
        
        int tile = 0;//맵 타일의 수
        for(int y = 0; y < columns; y++){//세로만큼 반복
        	 for(int x = 0; x < rows; x++){//가로만큼 반복
                String mapdata = mappasing.substring(tile, tile+1);//파일 입출력한 데이테
                if(!mapdata.equals("\n") && !mapdata.equals("\r\n") && !mapdata.equals("\r")){//만약 숫자일 시 
                    map[x][y] = Integer.parseInt(mapdata);
                }else{//만약 줄바꿈일 시
                    x--;
                    }
                tile++;
            }
        }
    }catch(Exception e){//맵 로딩 예외처리
        System.out.println("맵이 이상해~");
    }
}
}
