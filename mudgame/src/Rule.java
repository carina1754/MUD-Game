import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Rule {
	public int printCard(String user, ArrayList<Card> deck,int ace) {
		
		Rule rule = new Rule();
		int sum = 0;
		for(int i=0;i<deck.size();i++) {
			if(i==0) {
				if(user.equals("boss")) {
					System.out.print("딜러카드 : ?");
				}
				else {
					System.out.print("유저 카드 : (" + deck.get(0).getPattern() + "," + deck.get(0).getNumber() + ")");
				}
			}
			else {
				System.out.print("(" + deck.get(i).getPattern() + "," + deck.get(i).getNumber() + ")");
					if(user.equals("boss")) {
						System.out.println();
					}
				}
			if(i==deck.size()-1 && user.equals("player")) {
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
	public int getAce(ArrayList<Card> deck) {
		for(int i=0;i<deck.size();i++) {
			String number = deck.get(i).getNumber();
			if(number.equals("A")) {
				String[] ace = {"1","11"};
		        int acepkg = JOptionPane.showOptionDialog(null, "ACE를 어떤 값으로 주시겠습니까?", "ACE VALUE",
		                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, ace, "11");
		        if(acepkg==0)
		        	return 1;
		        else
		        	return 11;
			}
		}
		return 1;
	}
	
	public boolean isBlackJack(ArrayList<Card> deck) {
		for(int i=0;i<deck.size();i++) {
			String number = deck.get(i).getNumber();
			if(number.equals("A")) {
				if(number.equals("J") || number.equals("Q") || number.equals("K")) {
					return true;
				}
			}
		}
		return false;
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
		int dresult = 21 - dealer;
		int presult = 21 - player;
		int result;
		
		if(dresult > presult) result = 0;
		if(dresult < presult) result = 1;
		else result =2;
		return result; 
		
	}
}
