import java.util.ArrayList;

public class Boss {
	public ArrayList<Card> setCard(){
		ArrayList<Card> deck = new ArrayList<Card>();
		
		for(int i =0; i<52; i++) {
			Card card = new Card();
			switch(i%4) {
			case 0:
				card.setPattern("♥");
				break;
			case 1:
				card.setPattern("♠");
				break;
			case 2:
				card.setPattern("♦");
				break;
			case 3:
				card.setPattern("♣");
				break;
			}
			switch(i%13) {
			case 1:
				card.setNumber("A");
				break;
			case 2:
				card.setNumber("2");
				break;
			case 3:
				card.setNumber("3");
				break;
			case 4:
				card.setNumber("4");
				break;
			case 5:
				card.setNumber("5");
				break;
			case 6:
				card.setNumber("6");
				break;
			case 7:
				card.setNumber("7");
				break;
			case 8:
				card.setNumber("8");
				break;
			case 9:
				card.setNumber("9");
				break;
			case 10:
				card.setNumber("10");
				break;
			case 11:
				card.setNumber("J");
				break;
			case 12:
				card.setNumber("Q");
				break;
			case 0:
				card.setNumber("K");
				break;
			}
			deck.add(card);
		}
		return deck;
		
	}
	public Card getCard(ArrayList<Card> deck,float f) {
		int num;
		int size = deck.size();
		num = (int)(f*size);
		Card card = deck.get(num);
		deck.remove(num);
		return card;
	}
	public ArrayList<Card> bossGetCard(int bossSum,ArrayList<Card> deck, ArrayList<Card> bossCard,float f){
		Boss boss = new Boss();
		Rule rule = new Rule();
		int sum = bossSum;
		
		while(sum<17) {
			Card card = boss.getCard(deck,f);
			bossCard.add(card);
			sum = rule.getSum(bossCard,11);
		}
		return bossCard;
	}
	public Card hit(Boss boss, ArrayList<Card> deck, float f) {
		Card card = boss.getCard(deck,f);
    	return card;
	}
}
