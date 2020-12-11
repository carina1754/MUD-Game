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
	
	static int[] width = {775, 285};//�ȳ� �ǳ��� ���� ������
    static int[] height = {300, 100, 62};//�ȳ� �ǳ��� ���� ������
    
    static int rostr = 1;//���� ��
    
    static String upstr = new String("��");//���� ǥ��
    static String downstr = new String("��");//�Ʒ��� ǥ��
    static String rightstr = new String("��");// ������ ǥ��
    static String leftstr = new String("��");//���� ǥ��
    
    public static int rows = 31;//���� ������ ����
    public static int columns = 31;//���� ������ ����
    public static int panelSize = 25;//�� Ÿ�� �ϳ��� ������
    public static int map[][] = new int[columns+1][rows+1];//���� �迭
    public static int endLevelLoc=30;//�ⱸ�� y�� ��ġ
    
    Player player;//�÷��̾� ��ü ����
    Random random = new Random();//ī�带 �������� �̱� ���� ����
    int gameflag = 0;//�÷��̾��� ü�� FLAG
     int x;//���� ��ġ�� ���� ��
	 int y;//���� ��ġ�� ���� ��
	 int count;//óġ�� ������ ��
	 int bossnum=0;//�ʿ� ǥ�õ� ������ ��
	 int bossmap[][] = new int [9][4];//���� ���� �迭
	 int bossattack =1;//���� �⺻ ���ݷ�(1�ܰ�)
	 int bosshealth = 5;//���� �⺻ ü��(1�ܰ�)
	 int aceval = 11;//�ʱ� �÷��̾� ACE�� (������ ���� 11�� ����)
	 int baceval = 11;//�ʱ� ���� ACE�� (������ ���� 11�� ����)
	 
    public play() {
    	BevelBorder border = new BevelBorder(BevelBorder.RAISED);//�ȳ��� ����� BROEDER��� ��ü
    	JPanel pn = new JPanel();//Ű �Է� ���������� ���ΰ�ħ���� �ǳ� ����
    	pn.setLayout(null);
    	new Maze();//���� �� ����
    	loadMap("load.map");//�ʷε�
    	
        this.setSize(1075,814);//��ũ�� ����
        this.setTitle("2019253071 ������");//Ÿ��Ʋ ����
        this.setLayout(null);
    	this.setVisible(true);
    	
    	JLabel round = new JLabel(Integer.toString(rostr) + " ����" , JLabel.CENTER);//���� �� ���
    	JLabel instruction = new JLabel("<html>A &nbsp; : &nbsp; ���� &nbsp; �̵� &nbsp;&nbsp; D &nbsp; : &nbsp; ������ &nbsp; �̵� "
    									+ "<br>" + "W &nbsp; : &nbsp; ���� &nbsp; �̵� &nbsp;&nbsp; S &nbsp; : &nbsp; �Ʒ��� &nbsp; �̵� ",JLabel.CENTER);//����Ű �ȳ�
    	
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
    	        
    	    	player = new Player();//�÷��̾� ��ü ����
    	    	player.setVisible(true);
    	    	player.setLocation(0,0);//��ġ����
    	    	player.life =5;//ü�� ����
    	    	this.add(player);
    	    	
    	        for(int y = 0; y < columns; y++){//���� ���μ� ��ŭ
    	            for(int x = 0; x < rows; x++){//���� ���μ� ��ŭ
    	                Tile tile = new Tile(x, y);//��ĥ Ÿ�� ��ü ����
    	                tile.setSize(panelSize, panelSize);
    	                tile.setLocation((x*panelSize), (y*panelSize));
    	                if((x==29&&y==30))//���� �ⱸ�� ��
    	                    tile.setBackground(Color.magenta);
    	                else if(map[x][y] == 0){//���� ��
    	                    tile.setBackground(Color.GRAY);
    	                }
    	                else if(map[x][y]>=2) {//������ ��

        	                tile.setSize(panelSize-6, panelSize-6);
        	                tile.setLocation((x*panelSize+3), (y*panelSize+3));

        	                tile.setWall(false);
    	                	tile.setBackground(Color.RED);
    	                }
    	                else{//����� ��
    	                    tile.setBackground(Color.WHITE);
    	                    tile.setWall(false);
    	                    if(x == 0){//�÷��̾��� ��ġ����
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
    	        
    	        this.addKeyListener(new KeyListener(){//Ű �Է� �̺�Ʈ

    				@Override
    				public void keyPressed(KeyEvent e) {//Ű ���� �̺�Ʈ
    					int key = e.getKeyCode();//Ű �̺�Ʈ ����
    					revalidate();//���� ��� ����
    					repaint();//���� ��� ����
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
    			    	
    					if(key == KeyEvent.VK_W){//���� �̵�
    						player.moveUp();	
    					}
    					
    					if(key == KeyEvent.VK_A){//�������� �̵�
    						player.moveLeft();	
    					}
    					
    					if(key == KeyEvent.VK_S){//�Ʒ��� �̵�
    						player.moveDown();	
    					}
    					
    					if(key == KeyEvent.VK_D){//���������� �̵�
    						player.moveRight();	
    					}
    			
						x=player.x;
						y=player.y;
						System.out.println("��ǥ : " + x +" "+ y +" �� : " + map[x][y]);
						
						if(x==0 && y == 0)//ó�� ��ġ ǥ��
							r.setVisible(true);
						
						else if(x==1 && y==0)//ó�� ��ġ ǥ��
							dl.setVisible(true);
						
						else {//���� ��ġ ǥ��
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
						
					 	if(player.life <= 0) {//�÷��̾��� ü���� ������
    					    String[] nolife = {"�ٽý���","�׸��ϱ�"};
					        int nolifepkg = JOptionPane.showOptionDialog(null, "ü���� ��� ������ϴ�. �ٽ� �Ͻðڽ��ϱ�?", "The END",
					                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, nolife, "�׸��ϱ�");
					        if(nolifepkg==0) {
					        	gameflag =1;
    						}
					        else
					        	System.exit(0);
						}
					 	
					 	if(map[x][y]>=2) {//�ʿ��� ������ ������ ���
					    	int flag =0;
					    	if(bossnum<8) {//������ ���� 8������ ���� ���
					    	for(int l=0;l<8;l++) {//������ ����ŭ �ݺ�
					    		if(bossmap[l][0] == x && bossmap[l][1] == y) {//�����迭�� �̹� ����Ǿ� ������
					    			flag=0;
					    		break;
					    		}
					    		else //������ �ȵǾ�������
					    			flag=1;
					    	}
					    	if(flag==1){//���ο� ������ ��
					    			System.out.print("���� ����");
					    			for(int l1=0;l1<8;l1++) {//������ �ִ� �� 8��ŭ �ݺ�
							    		if(bossmap[l1][0] != x || bossmap[l1][1] != y ) {//���� �����迭�� ����Ǿ����� ���� ��� ���ο� ���� ����
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
					 					if(bossmap[i][0] == x && bossmap[i][1] == y && bossmap[i][2]>0) {//���� ������ġ�� �÷��̾��� ��ġ�� ���� ������ ü���� 0���� Ŭ��
					 						String[] buttons = {"��Ʋ����","��������"};
									        int num = JOptionPane.showOptionDialog(null, i+1 + "�ܰ� �����Դϴ�. ��Ʋ�� �����ϰڽ��ϱ�?", "BOSS ����!",
									                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "��������");
						    	    		System.out.print("���� ����");
						    	    		int result = 4;
						    	    		
						    	    		for(;;) {//���� ������ ���� ���� ����
						    	    				
					        			if(num==0 && result == 4 && bossmap[i][2]>0) {//������ ü���� 0���� ũ�� ������ ������ �� ���� ����
					        				pn.setVisible(false);
					        				Boss boss = new Boss();
					 						Rule rule = new Rule();
					 					
					    					revalidate();
					    					repaint();
					 						ArrayList<Card> deck = boss.setCard();
					 						ArrayList<Card> bossCard = new ArrayList<Card>();
					 						ArrayList<Card> playerCard = new ArrayList<Card>();
					 						
					 						System.out.println("���� ���� ����");
					 						
					 						bossCard.add(boss.getCard(deck,random.nextFloat()));
					 						bossCard.add(boss.getCard(deck,random.nextFloat()));
					 						playerCard.add(boss.getCard(deck,random.nextFloat()));
					 						playerCard.add(boss.getCard(deck,random.nextFloat()));
					 						rule.intro = "\0";	
					 						int bossSum = rule.printIntro("boss",bossCard,baceval);
					 						int playerSum = rule.printIntro("player",playerCard,aceval);
					 						rule.printCard("boss",bossCard,baceval);
					 						rule.printCard("player",playerCard,aceval);
					 						JOptionPane.showMessageDialog(null, "���� ������ ü�� : " + bossmap[i][2] + " ���� ü�� : " + player.life);
					 						
					 						while(!(rule.bust(bossSum))) {//������ ����Ʈ�� ������
					 							
					 							if((playerSum == 21 || bossSum ==21) && playerCard.size()<=2) {//�÷��̾�� ���� �� �� �����Ͻ�
					 								
                                                    if(bossSum != 21) {//������ ������ �ƴҽ� �÷��̾ ����
                                                       String[] blackwin = {"����ϱ�"};
                                                       int blackwinpkg = JOptionPane.showOptionDialog(null, rule.result + "\r\n" + "�����Դϴ�!", "YOU WIN",
                                                               JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, blackwin, "����ϱ�");
                                                       if(blackwinpkg == 0) {//����ϱ�
                                                           result = 4;
                                                           bossmap[i][2] -= 2;
                                                           break;
                                                       }
                                                    }
                                                    
                                                    else {
                                                    	JOptionPane.showMessageDialog(null, rule.result);
                                                        String[] blacklose = {"����ϱ�"};
                                                        int blacklosepkg = JOptionPane.showOptionDialog(null, rule.result + "\r\n" + "�����Դϴ�!", "YOU LOSE",
                                                               JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, blacklose, "����ϱ�");
                                                       if(blacklosepkg == 0) {//����ϱ�
                                                           result = 4;
                                                            player.life -= bossmap[i][3]*2;
                                                           break;
                                                       }
                                                    }
                                                }
					 							else//������ �ƴϸ� ����
					 							{
					 								rule.intro = "\0";	
					 								bossSum = rule.printIntro("boss",bossCard,baceval);
							 						playerSum = rule.printIntro("player",playerCard,aceval);
					 							String[] black = {"hit","stand"};
										        int jack = JOptionPane.showOptionDialog(null, rule.intro , "YOUR TURN",
										                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, black, "stand");
										        	
										        if(jack==0) {//hit ��
										        	if(rule.bust("boss", bossSum)) {//������ ����Ʈ�� �� �÷��̾� ��
										        		rule.result = "\0";
										        		rule.printCard("boss",bossCard,baceval);
								 						rule.printCard("player",playerCard,aceval);
										        		JOptionPane.showMessageDialog(null, rule.result + "\r\n" + bossnum + "�ܰ� �������� �̰���ϴ�.");
										        		result=4;
									        			bossmap[i][2]--;
										        		break;
										        	}
										        	
										        	Card card = player.hit(boss, deck,random.nextFloat());
										        	playerCard.add(card);
										        	rule.result = "\0";	
										        	bossSum = rule.printCard("boss",bossCard,baceval);
										        	playerSum = rule.printCard("player", playerCard,aceval);
										        	
										        	if(rule.bust("player", playerSum)) {//�÷��̾ ����Ʈ�� �� ���� ��
								 						rule.printIntro("player",playerCard,aceval);
						    							JOptionPane.showMessageDialog(null,bossnum + "�ܰ� �������� �����ϴ�.");
										        		result = 4;
										        		player.life -= bossmap[i][3];
										        		break;
										        	}
										        	else//�÷��̾ ����Ʈ�� �ƴ� �� ������ ī�带 ����
										        		bossCard.add(boss.getCard(deck,random.nextFloat()));
										        }
										        
										        else if(jack == 1) {//���ĵ� ��
										        	result = rule.game(bossSum, playerSum);
										        	if(rule.bust("boss", bossSum)) {//������ ����Ʈ�� �� �÷��̾� ��
										        		rule.result = "\0";
										        		rule.printCard("boss",bossCard,baceval);
								 						rule.printCard("player",playerCard,aceval);
										        		JOptionPane.showMessageDialog(null, rule.result + "\r\n" + bossnum + "�ܰ� �������� �̰���ϴ�.");
										        		result=4;
									        			bossmap[i][2]--;
										        		break;
										        	}
										        	else if(result == 1) {//�������� �̰��� ��
										        		rule.result = "\0";
										        		rule.printCard("boss",bossCard,baceval);
								 						rule.printCard("player",playerCard,aceval);
						    							JOptionPane.showMessageDialog(null, rule.result + "\r\n" + bossnum + "�ܰ� �������� �̰���ϴ�.");
										        			bossmap[i][2]--;
										        			result=4;
										        			break;
										        	}
										        	else if(result == 0) {//�������� ���� ��
										        		rule.result = "\0";
										        		rule.printCard("boss",bossCard,baceval);
								 						rule.printCard("player",playerCard,aceval);
						    							JOptionPane.showMessageDialog(null,rule.result + "\r\n" + bossnum + "�ܰ� �������� �����ϴ�.");
										        		result=4;
										        		player.life -= bossmap[i][3];
										        		break;
										        	}
										        	else if(result == 2) {//�������� ����� ��
										        		rule.result = "\0";
										        		rule.printCard("boss",bossCard,baceval);
								 						rule.printCard("player",playerCard,aceval);
										        		String[] push = {"����ϱ�"};
										        		int pushpkg = JOptionPane.showOptionDialog(null, rule.result + "\r\n" +bossnum + "�ܰ� �������� �����ϴ�.", "DRAW",
												                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, push, "����ϱ�");
										        	     if(pushpkg==0) {//����� ��
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
					 						
					 						if(num==0 && player.life <= 0) {//�÷��̾ 0 �����̰� ������ ������� ���� �� 
					 							bossCard = boss.bossGetCard(bossSum,deck,bossCard,random.nextFloat());
					 							if(bossSum >=17) {//18���� ũ�� ace ���� 1
					 								baceval = 1;
					 							bossSum = rule.getSum(bossCard, baceval);
					 							}
					 							else {//�ƴϸ� 11
					 								baceval = 11;
					 								bossSum = rule.getSum(bossCard, baceval);
					 							}
						 						player.life -= bossmap[i][3];
						 							if(player.life <= 0) {//�÷��̾��� ü���� 0���� �϶�
						 	    					    String[] nolife = {"�ٽý���","�׸��ϱ�"};
						 						        int nolifepkg = JOptionPane.showOptionDialog(null, "ü���� ��� ������ϴ�. �ٽ� �Ͻðڽ��ϱ�?", "The END",
						 						                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, nolife, "�׸��ϱ�");
						 						        if(nolifepkg==0) {//�ǰ� �� �� �ٽý����� �� gmaeflag = 1 
						 						        	gameflag = 1;
						 	    						break;
						 	    						}
						 						        else//���� ����
						 						        	System.exit(0);
						 							}
					 						}
					 						else
					 							continue;
						 				}
					        			
							        	else if(num==0 && bossmap[i][2] <= 0) {//���� ������ �ǰ� �� ����� ��
						        			player.life += 2;
							        		String[] win = {"����ϱ�"};
							        		int winpkg = JOptionPane.showOptionDialog(null, bossnum + "�ܰ� ������ �����ƽ��ϴ�.", "YOU WIN",
									                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, win, "����ϱ�");
							        		if(winpkg == 0) {//����ϱ�
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
					 	
					 	if(gameflag == 1) {//�÷��̾ �ǰ� �� �𿩼� ������ ������ �� ���ο� ���� ����
					 		player.life=5;
					 		dispose();
					 		new play();
					 	}
					 	
    					if(x == 29 && y == endLevelLoc){//�������� �������� ��
    						if(count>=4) {//������ 4���� �̻� ����� �� ���ο� ���� ����
    					    String[] buttons = {"���� ����","�׸��ϱ�"};
					        int num = JOptionPane.showOptionDialog(null, "������ ã�ҽ��ϴ�! ���� ����� ���� �ϰڽ��ϱ�?", "���� ã�� ����!",
					                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "���� ����");
					        player.life=5;
    						dispose();
    						rostr++;
    						new play();
    						}
    						else//������ 4���� �̻� ���� ������ ��
    							JOptionPane.showMessageDialog(null, "������ 4���� �̻� ��� ���ƿ�����.");
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
	
public void loadMap(String str){//������ ���� 2�����迭�� ����
    try{
        BufferedReader br = new BufferedReader(new FileReader(str));//������ �б� ���� ���� ����
        StringBuilder sb = new StringBuilder();//������ ���� �б� ����
        String line = br.readLine();//���Ͽ��� �ٹٲ� �б� ����

        while (line != null) {//��� ������ ���� �� ����
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        
        String mappasing = sb.toString();//���ڿ� �а� string������ ��ȯ
        
        int tile = 0;//�� Ÿ���� ��
        for(int y = 0; y < columns; y++){//���θ�ŭ �ݺ�
        	 for(int x = 0; x < rows; x++){//���θ�ŭ �ݺ�
                String mapdata = mappasing.substring(tile, tile+1);//���� ������� ������
                if(!mapdata.equals("\n") && !mapdata.equals("\r\n") && !mapdata.equals("\r")){//���� ������ �� 
                    map[x][y] = Integer.parseInt(mapdata);
                }else{//���� �ٹٲ��� ��
                    x--;
                    }
                tile++;
            }
        }
    }catch(Exception e){//�� �ε� ����ó��
        System.out.println("���� �̻���~");
    }
}
}
