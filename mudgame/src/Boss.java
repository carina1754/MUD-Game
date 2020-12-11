import java.util.ArrayList;

public class Boss {
	public ArrayList<Card> setCard(){//카드 뽑기
		ArrayList<Card> deck = new ArrayList<Card>();//카드 배열 생성
		
		for(int i =0; i<52; i++) {//총 카드수 52번 반복
			Card card = new Card();//카드 객체 생성
			switch(i%4) {//반복 수의 4로 나눈 나머지로 문양을 생성
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
			switch(i%13) {//반복 수의 13으로 나눈 나머지로 숫자 및 ace,j,q,k를 생성
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
			deck.add(card);//카드 배열에 카드 추가
		}
		return deck;//카드 덱 return
		
	}
	public Card getCard(ArrayList<Card> deck,float f) {//새로운 카드를 생성
		int num;//랜덤 카드 저장용
		int size = deck.size();
		num = (int)(f*size);
		Card card = deck.get(num);
		deck.remove(num);
		return card;
	}
	public ArrayList<Card> bossGetCard(int bossSum,ArrayList<Card> deck, ArrayList<Card> bossCard,float f){//보스는 17이전까지 카드를 생성
		Boss boss = new Boss();//보스 객체 생성
		Rule rule = new Rule();//룰 객체 생성
		int sum = bossSum;//보스 합계 변수
		
		while(sum<17) {//17이전이면 새로운 카드를 생성
			Card card = boss.getCard(deck,f);
			bossCard.add(card);
			sum = rule.getSum(bossCard,11);
		}
		return bossCard;//보스 덱 return
	}
}
