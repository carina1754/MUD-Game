import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Rule {
	String result = "\0";//���� �� ��� ���
	String intro = "\0";//���� ó�� ī�� ��� ���
	public int printCard(String user, ArrayList<Card> deck,int ace) {//�߰� ���� ���
		Rule rule = new Rule();//rule��ü ���
		int sum = 0;//�հ� ����
		
		for(int i=0;i<deck.size();i++) {//�� ������ ��ŭ �ݺ�
			if(i==0) {//���� ó���̶�� ���� ī��� ������ ī�带 2���� ���
				if(user.equals("boss")) {//������ ��
					result += "���� ī�� : (" + deck.get(0).getPattern() + "," + deck.get(0).getNumber() + ")";
				}
				else {//������� ��
					result += "\r\n ���� ī�� : (" + deck.get(0).getPattern() + "," + deck.get(0).getNumber() + ")";
				}
			}
			else {//ù��° ī����� �ƴ� ��
				result += "(" + deck.get(i).getPattern() + "," + deck.get(i).getNumber() + ")";
					
				}
			if(i==deck.size()-1) {//�迭 ���� null�̹Ƿ� -1 �ϰ� �հ踦 ����
				sum = rule.getSum(deck,ace);
			}
			}
		return sum;//�հ� return
	}
	public int printIntro(String user, ArrayList<Card> deck,int ace) {//ó�� ȭ�� ���
		Rule rule = new Rule();//rule��ü ���
		int sum = 0;//�հ� ����
		
		for(int i=0;i<deck.size();i++) {//�� ������ ��ŭ �ݺ�
			if(i==0) {//���� ó���̶�� ���� ī��� ������ ī�带 2���� ���
				if(user.equals("boss")) {//������ �� ó�� ī�带 �������� ����
					intro += "���� ī�� : (? , ?)";
				}
				else {//������� ��
					intro += " ���� ī�� : (" + deck.get(0).getPattern() + "," + deck.get(0).getNumber() + ")";
				}
			}
			else {//ù��° ī����� �ƴ� ��
				intro += "(" + deck.get(i).getPattern() + "," + deck.get(i).getNumber() + ")";
					if(user.equals("boss")) {//���� ������ �� ���� ��� �÷��̾� ī�� ���
						intro += "\r\n";
					}
				}
			if(i==deck.size()-1) {//�迭 ���� null�̹Ƿ� -1 �ϰ� �հ踦 ����
				sum = rule.getSum(deck,ace);
			}
			}
		return sum;//�հ� return
	}
	public int getSum(ArrayList<Card> deck,int ace) {//�հ� ��� 
		// TODO Auto-generated method stub
		int sum = 0;//�հ� ����
		for(int i=0;i<deck.size();i++) {//�� ������ ��ŭ �ݺ�
			String number = deck.get(i).getNumber();//������ ī�� ũ�� �κ� ��������
			if(number.equals("A")) {//ace�� �� ace�˰����� ���� ���� �޶���
				sum += ace;//11 or 1
			}
			else if(number.equals("J") || number.equals("Q") || number.equals("K")) {// jack,queen,king�� �� 10�� �߰�
				sum += 10;
			}
			else {
				sum += Integer.parseInt(number);// �� �̿� ���ڵ��� int�� �Ľ��Ͽ� �߰�
			}
		}
		return sum;//�հ� return
	}
	public boolean bust(String user, int sum) {//bust ���� (����ڿ� ����)
		if(sum>21) {//�հ谡 21�� ���� ��
			return true;
		}
		else {
			return false;//no bust return
		}
	}
	
	public boolean bust(int sum) {//bust ����
		if(sum>21) {//�հ谡 21�� ���� ��
			return true;//bust return
		}
		else {
			return false;//no bust return
		}
	}
	
	public int game(int boss, int player) {//�¸� ���� 

		int result = 0;//��� ����
		
		if(boss > player) {//������ �� Ŭ�� ���� ���
			result = 0;
			}
		else if(boss < player) {//�÷��̾ �� Ŭ�� �÷��̾� ���
			result = 1;
			}
		else if(boss == player) {//������ �÷��̾ ������ ���� ��
			result = 2;
			}
		return result; //��� flag ���
		
	}
}
