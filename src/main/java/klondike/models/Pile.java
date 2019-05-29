package klondike.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Pile {

	private final int number;

	private int numberOfFaceUpCards;
	
    private Stack<Card> cards;
    
	public Pile(int number, List<Card> cards) {
		assert cards.size() > 0;
		
		this.cards = new Stack<Card>();
		this.number = number;
		this.numberOfFaceUpCards = 0;
		this.cards.addAll(cards);
		this.flipFirstCard();
	}

	public void push(Card card) {
		assert this.fitsIn(card);
		this.cards.push(card);
		this.numberOfFaceUpCards++;
	}

	public void push(List<Card> cards) {
		assert cards != null;
		for (Card card: cards) {
			this.push(card);
		}
	}
	
	public Card pop() {
		this.numberOfFaceUpCards--;
		return this.cards.pop();
	}

	private void flipFirstCard() {
		assert !this.empty() && this.numberOfFaceUpCards == 0 && !this.peek().isFacedUp();
		this.peek().flip();
		this.numberOfFaceUpCards++;
	}

	public boolean fitsIn(Card card) {
		assert card != null;
		return (this.empty() && card.getNumber() == Number.KING) || (!this.empty()
				&& this.peek().isNextTo(card) && this.peek().getColor() != card.getColor());
	}

	public List<Card> getTop(int numberOfCards) {
		assert numberOfCards <= this.numberOfFaceUpCards;
		return new ArrayList<Card>(this.cards.subList(this.cards.size() - numberOfCards, this.cards.size()));
	}
	
	public void removeTop(int numberOfCards) {
		assert numberOfCards <= this.numberOfFaceUpCards;
		for (int i = 0; i < numberOfCards; i++) {
			this.pop();
		}
		if (this.numberOfFaceUpCards == 0 && !this.empty()) {
			flipFirstCard();
		}
	}

	public int numberOfFaceUpCards() {
		return this.numberOfFaceUpCards;
	}

	public boolean empty() {
		return this.cards.empty();
	}

	public Stack<Card> getCards() {
		return this.cards;
	}

	public int getNumber() {
		return this.number;
	}

    public Card peek() {
        return this.cards.peek();
    }
	
}
