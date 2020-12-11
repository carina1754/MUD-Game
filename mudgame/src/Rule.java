import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Rule {
	String result = "\0";//블랙잭 힛 결과 출력
	String intro = "\0";//블랙잭 처음 카드 배분 출력
	public int printCard(String user, ArrayList<Card> deck,int ace) {//중간 과정 출력
		Rule rule = new Rule();//rule객체 사용
		int sum = 0;//합계 변수
		
		for(int i=0;i<deck.size();i++) {//덱 사이즈 만큼 반복
			if(i==0) {//만약 처음이라면 보스 카드와 유저의 카드를 2개씩 출력
				if(user.equals("boss")) {//보스일 시
					result += "보스 카드 : (" + deck.get(0).getPattern() + "," + deck.get(0).getNumber() + ")";
				}
				else {//사용자일 시
					result += "\r\n 유저 카드 : (" + deck.get(0).getPattern() + "," + deck.get(0).getNumber() + ")";
				}
			}
			else {//첫번째 카드들이 아닐 시
				result += "(" + deck.get(i).getPattern() + "," + deck.get(i).getNumber() + ")";
					
				}
			if(i==deck.size()-1) {//배열 끝은 null이므로 -1 하고 합계를 구함
				sum = rule.getSum(deck,ace);
			}
			}
		return sum;//합계 return
	}
	public int printIntro(String user, ArrayList<Card> deck,int ace) {//처음 화면 출력
		Rule rule = new Rule();//rule객체 사용
		int sum = 0;//합계 변수
		
		for(int i=0;i<deck.size();i++) {//덱 사이즈 만큼 반복
			if(i==0) {//만약 처음이라면 보스 카드와 유저의 카드를 2개씩 출력
				if(user.equals("boss")) {//보스일 시 처음 카드를 보여주지 않음
					intro += "보스 카드 : (? , ?)";
				}
				else {//사용자일 시
					intro += " 유저 카드 : (" + deck.get(0).getPattern() + "," + deck.get(0).getNumber() + ")";
				}
			}
			else {//첫번째 카드들이 아닐 시
				intro += "(" + deck.get(i).getPattern() + "," + deck.get(i).getNumber() + ")";
					if(user.equals("boss")) {//만약 보스일 시 한줄 띄고 플레이어 카드 출력
						intro += "\r\n";
					}
				}
			if(i==deck.size()-1) {//배열 끝은 null이므로 -1 하고 합계를 구함
				sum = rule.getSum(deck,ace);
			}
			}
		return sum;//합계 return
	}
	public int getSum(ArrayList<Card> deck,int ace) {//합계 계산 
		// TODO Auto-generated method stub
		int sum = 0;//합계 변수
		for(int i=0;i<deck.size();i++) {//덱 사이즈 만큼 반복
			String number = deck.get(i).getNumber();//덱에서 카드 크기 부분 가져오기
			if(number.equals("A")) {//ace일 시 ace알고리즘의 수에 따라서 달라짐
				sum += ace;//11 or 1
			}
			else if(number.equals("J") || number.equals("Q") || number.equals("K")) {// jack,queen,king일 시 10을 추가
				sum += 10;
			}
			else {
				sum += Integer.parseInt(number);// 그 이외 숫자들을 int로 파싱하여 추가
			}
		}
		return sum;//합게 return
	}
	public boolean bust(String user, int sum) {//bust 유무 (사용자에 따라서)
		if(sum>21) {//합계가 21이 넘을 시
			return true;
		}
		else {
			return false;//no bust return
		}
	}
	
	public boolean bust(int sum) {//bust 유무
		if(sum>21) {//합계가 21이 넘을 시
			return true;//bust return
		}
		else {
			return false;//no bust return
		}
	}
	
	public int game(int boss, int player) {//승리 유무 

		int result = 0;//결과 변수
		
		if(boss > player) {//보스가 더 클시 보스 우승
			result = 0;
			}
		else if(boss < player) {//플레이어가 더 클시 플레이어 우승
			result = 1;
			}
		else if(boss == player) {//보스와 플레이어가 점수가 같을 시
			result = 2;
			}
		return result; //결과 flag 출력
		
	}
}
