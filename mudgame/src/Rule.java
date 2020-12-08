import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Rule {
	String result = "\0";
	String intro = "\0";
	public int printCard(String user, ArrayList<Card> deck,int ace) {
		Rule rule = new Rule();
		int sum = 0;
		
		for(int i=0;i<deck.size();i++) {
			if(i==0) {
				if(user.equals("boss")) {
					result += "딜러카드 : (" + deck.get(0).getPattern() + "," + deck.get(0).getNumber() + ")";
				}
				else {
					result += " 유저 카드 : (" + deck.get(0).getPattern() + "," + deck.get(0).getNumber() + ")";
				}
			}
			else {
				result += "(" + deck.get(i).getPattern() + "," + deck.get(i).getNumber() + ")";
					if(user.equals("boss")) {
						result += "\r\n";
					}
				}
			if(i==deck.size()-1) {
				sum = rule.getSum(deck,ace);
			}
			}
		return sum;
	}
	public int printIntro(String user, ArrayList<Card> deck,int ace) {
		Rule rule = new Rule();
		int sum = 0;
		
		for(int i=0;i<deck.size();i++) {
			if(i==0) {
				if(user.equals("boss")) {
					intro += "딜러카드 : (? , ?)";
				}
				else {
					intro += " 유저 카드 : (" + deck.get(0).getPattern() + "," + deck.get(0).getNumber() + ")";
				}
			}
			else {
				intro += "(" + deck.get(i).getPattern() + "," + deck.get(i).getNumber() + ")";
					if(user.equals("boss")) {
						intro += "\r\n";
					}
				}
			if(i==deck.size()-1) {
				sum = rule.getSum(deck,ace);
			}
			}
		return sum;
	}
	public int getSum(ArrayList<Card> deck,int ace) {
		// TODO Auto-generated method stub
		int sum = 0;
		for(int i=0;i<deck.size();i++) {
			String number = deck.get(i).getNumber();
			if(number.equals("A")) {
				sum += ace;
			}
			else if(number.equals("J") || number.equals("Q") || number.equals("K")) {
				sum += 10;
			}
			else {
				sum += Integer.parseInt(number);
			}
		}
		return sum;
	}
	public boolean isBust(String user, int sum) {
		if(sum>21) {
			System.out.println(user + " Bust (총합 : " + sum + ")");
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isBust(int sum) {
		if(sum>21) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int userWin(int dealer, int player) {
		int dresult = dealer;
		int presult = player;
		int result = 0;
		
		if(dresult > presult) {
			result = 0;
			}
		else if(dresult < presult) {
			result = 1;
			}
		else if(dresult == presult) {
			result = 2;
			}
		
		System.out.println(presult + "dd" + dresult);
		return result; 
		
	}
}
