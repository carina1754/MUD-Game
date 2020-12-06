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
import java.util.Random;

public class play extends JFrame{
	static int[] width = {775, 285};
    static int[] height = {300, 100, 62};
    
    static int rostr = 1;
    
    static String insstr1 = new String("← : 왼쪽 이동  → : 오른쪽 이동");    
    static String insstr2 = new String("↑ : 위로 이동  ↓ : 아래로 이동 ");
    
    static String upstr = new String("↑");
    static String downstr = new String("↓");
    static String rightstr = new String("→");
    static String leftstr = new String("←");
	static String nowStatestr = new String("");
	
    public static int rows = 31;
    public static int columns = 31;
    public static int panelSize = 25;
    public static int map[][] = new int[columns][rows];
    public static int endLevelLoc;

    Player p;
     int x;
	 int y;
	 int count;
	 int bossnum=0;
	 int bossmap[][] = new int [9][2];
    public play(String str) {
    	BevelBorder border = new BevelBorder(BevelBorder.RAISED);
    	border=new BevelBorder(BevelBorder.RAISED);
    	JPanel pn = new JPanel();
    	pn.setLayout(null);
    	new Maze();
    	loadMap("load.map");
    	
        this.setSize(1075,814);
        this.setTitle("2019253071 안정수");
        this.setLayout(null);
    	this.setVisible(true);
    	
    	JLabel round = new JLabel(Integer.toString(rostr) + " 라운드" , JLabel.CENTER);
    	JLabel instruction = new JLabel("<html>← &nbsp; : &nbsp; 왼쪽 &nbsp; 이동 &nbsp;&nbsp; → &nbsp; : &nbsp; 오른쪽 &nbsp; 이동 "
    									+ "<br>" + "↑ &nbsp; : &nbsp; 위로 &nbsp; 이동 &nbsp;&nbsp; ↓ &nbsp; : &nbsp; 아래로 &nbsp; 이동 ",JLabel.CENTER);
    	
    	JLabel ur = new JLabel(upstr+rightstr,JLabel.CENTER);	
    	JLabel ul = new JLabel(upstr+leftstr,JLabel.CENTER);	
    	JLabel ulr = new JLabel(upstr+leftstr+rightstr,JLabel.CENTER);
    	JLabel u = new JLabel(upstr,JLabel.CENTER);	
    	JLabel dr = new JLabel(downstr+rightstr,JLabel.CENTER);	
    	JLabel dl = new JLabel(downstr+leftstr,JLabel.CENTER);	
    	JLabel d = new JLabel(downstr,JLabel.CENTER);	
    	JLabel du = new JLabel(upstr+downstr,JLabel.CENTER);	
    	JLabel lr = new JLabel(leftstr+rightstr,JLabel.CENTER);	
    	JLabel l = new JLabel(leftstr,JLabel.CENTER);
    	JLabel r = new JLabel(rightstr,JLabel.CENTER);
    	JLabel dlr = new JLabel(leftstr+rightstr+downstr,JLabel.CENTER);	
    	JLabel dur = new JLabel(downstr+rightstr+upstr,JLabel.CENTER);	
    	JLabel dul = new JLabel(leftstr+upstr+downstr,JLabel.CENTER);	
    	JLabel durl = new JLabel(leftstr+rightstr+downstr+upstr,JLabel.CENTER);	
    	
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
    	        loadMap(str);
    	        
    	        this.addKeyListener(new KeyListener(){

    				@Override
    				public void keyPressed(KeyEvent e) {
    					int key = e.getKeyCode();
    					revalidate();
    					repaint();
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
    			    	
    					if(key == KeyEvent.VK_W){
    						p.moveUp();	
    					}
    					
    					if(key == KeyEvent.VK_A){
    						p.moveLeft();	
    					}
    					
    					if(key == KeyEvent.VK_S){
    						p.moveDown();	
    					}
    					
    					if(key == KeyEvent.VK_D){
    						p.moveRight();	
    					}
    			
						x=p.x;
						y=p.y;
						System.out.println("좌표 : " + x +" "+ y +" 값 : " + map[x][y]);
						
						if(x==0 && y == 0)
							r.setVisible(true);
						else if(x==1 && y==0)
							dl.setVisible(true);
						else {
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
						
					 	if(map[x][y]>=2) {
					    	Tile tile1 = new Tile(x, y);
					    	pn.add(tile1);
					    	int flag =0;
					    	if(bossnum<8) {
					    	for(int l=0;l<8;l++) {
					    		if(bossmap[l][0] == x && bossmap[l][1] == y) {
					    			flag=0;
					    		break;
					    		}
					    		else 
					    			flag=1;
					    	}
					    	if(flag==1){
					    			System.out.print("보스 진입");
					    			for(int l1=0;l1<8;l1++) {
							    		if(bossmap[l1][0] != x || bossmap[l1][1] != y ) {
									        bossmap[bossnum][0] = x;
									 		bossmap[bossnum][1] = y;
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
					 					if(bossmap[i][0] == x && bossmap[i][1] == y) {
					 						String[] buttons = {"배틀시작","도망가기"};
									        int num = JOptionPane.showOptionDialog(null, i+1 + "단계 보스입니다. 배틀을 시작하겠습니까?", "BOSS 출현!",
									                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "도망가기");
						    	    		System.out.print("보스 출현");
					        			if(num==0) {
					        			JPanel pn1 = new JPanel();
					        			pn.setVisible(false);
					        			tile1.setSize(panelSize, panelSize);
				    	                tile1.setLocation((x*panelSize), (y*panelSize));
				    	                tile1.setBackground(Color.YELLOW);
				    	                tile1.setVisible(true);
					            		map[x][y]=1;
					 					
					        			}
					        			else if(num==1)
					        				break;
					        			else
					        				break;
					        			}
					        		}
		            	}
					 	
    					if(x == columns-2 && y == endLevelLoc && count>=4){
    					    String[] buttons = {"다음 라운드","그만하기"};
					        int num = JOptionPane.showOptionDialog(null, "보물을 찾았습니다! 다음 라운드로 진행 하겠습니까?", "보물 찾기 성공!",
					                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "다음 라운드");
    						dispose();
    						rostr++;
    						new Maze();
    						new play("load.map");
    					}
    					else if(x == columns-2 && y == endLevelLoc && count<4)
    						JOptionPane.showMessageDialog(null, "보스를 4마리 이상 잡고 돌아오세요.");
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
    	        this.setLocationRelativeTo(null);
    	        //Create player
    	    	p = new Player();
    	    	p.setVisible(true);
    	    	p.setLocation(0,0);
    	    	this.add(p);
    	        //Color map
    	        for(int y = 0; y < columns; y++){
    	            for(int x = 0; x < rows; x++){
    	                Tile tile = new Tile(x, y);
    	                tile.setSize(panelSize, panelSize);
    	                tile.setLocation((x*panelSize), (y*panelSize));
    	                if((x==0&&y==0)||(x==29&&y==30))
    	                    tile.setBackground(Color.YELLOW);
    	                else if(map[x][y] == 0){
    	                    tile.setBackground(Color.PINK);
    	                }
    	                else if(map[x][y]>=2) {
    	                	tile.setWall(false);
    	                	tile.setBackground(Color.RED);
    	                }
    	                else{
    	                    tile.setBackground(Color.WHITE);
    	                    tile.setWall(false);
    	                    if(x == 0){
    	                    	p.setLocation((x*panelSize), (y*panelSize));
    	                    	p.y = y;
    	                    }
    	                    if(y == rows-1){
    	                    	endLevelLoc = y;
    	                    }
    	                }
    	                tile.setVisible(true);
    	                this.add(tile);
    	            }
    	        }
    	        this.setVisible(true);
    
    }
    
public void loadMap(String str){
    try{
        BufferedReader br = new BufferedReader(new FileReader(str));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        
        String mapStr = sb.toString();
        
        int counter = 0;
        for(int y = 0; y < columns; y++){
            for(int x = 0; x < rows; x++){
                String mapChar = mapStr.substring(counter, counter+1);
                if(!mapChar.equals("\r\n") && !mapChar.equals("\n")&& !mapChar.equals("\r")){//If it's a number
                    //System.out.print(mapChar);
                    map[x][y] = Integer.parseInt(mapChar);
                }else{//If it is a line break
                    x--;
                    System.out.print(mapChar);
                }
                counter++;
            }
        }
    }catch(Exception e){
        System.out.println("맵이 이상해~");
    }
}
}




/*
if (map[(int)p.getLocation().getX() + 1][(int)p.getLocation().getY()] != 0) {
	if (map[(int)p.getLocation().getX() + 1][(int)p.getLocation().getY() + 1] != 0)
		status.setText(right + down);
	else if(map[(int)p.getLocation().getX() + 1][(int)p.getLocation().getY() - 1] != 0)
		status.setText(right + up);
	else
		status.setText(right);
}
else if (map[(int)p.getLocation().getX() - 1][(int)p.getLocation().getY()] != 0) {
	if (map[(int)p.getLocation().getX() - 1][(int)p.getLocation().getY() + 1] != 0)
		status.setText(left + down);
	else if(map[(int)p.getLocation().getX() - 1][(int)p.getLocation().getY() - 1] != 0)
		status.setText(left + up);
	else
		status.setText(left);
}
else if (map[(int)p.getLocation().getX()][(int)p.getLocation().getY() + 1] != 0) {
	status.setText(up);
}
else if (map[(int)p.getLocation().getX()][(int)p.getLocation().getY() - 1] != 0) {
	status.setText(down);
}*/

