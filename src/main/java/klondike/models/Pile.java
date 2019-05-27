package klondike.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Pile {

	private final int number;

	private int numberOfFaceUpCards;
	
	private CardStack cardStack;	

	public Pile(int number, List<Card> cards) {
		assert cards.size() > 0;
		
		this.cardStack = new CardStack();		
		this.number = number;
		this.numberOfFaceUpCards = 0;
		this.cardStack.cards.addAll(cards);
		this.flipFirstCard();
	}

	public void push(Card card) {
		assert this.fitsIn(card);
		this.cardStack.push(card);
		this.numberOfFaceUpCards++;
	}

	public Card pop() {
		this.numberOfFaceUpCards--;
		return this.cardStack.pop();
	}

	private void flipFirstCard() {
		assert !this.cardStack.empty() && this.numberOfFaceUpCards == 0 && !this.cardStack.peek().isFacedUp();
		this.cardStack.peek().flip();
		this.numberOfFaceUpCards++;
	}

	public boolean fitsIn(Card card) {
		assert card != null;
		return (this.cardStack.empty() && card.getNumber() == Number.KING) || (!this.cardStack.empty()
				&& this.cardStack.peek().isNextTo(card) && this.cardStack.peek().getColor() != card.getColor());
	}

	public List<Card> getTop(int numberOfCards) {
		assert numberOfCards <= this.numberOfFaceUpCards;
		return new ArrayList<Card>(this.cardStack.cards.subList(this.cardStack.cards.size() - numberOfCards, this.cardStack.cards.size()));
	}

	public void addToTop(List<Card> cards) {
		assert cards != null;
		this.cardStack.cards.addAll(cards);
		this.numberOfFaceUpCards += cards.size();
	}

	public void removeTop(int numberOfCards) {
		assert numberOfCards <= this.numberOfFaceUpCards;
		for (int i = 0; i < numberOfCards; i++) {
			this.cardStack.pop();
			numberOfFaceUpCards--;
		}
		if (this.numberOfFaceUpCards == 0 && !this.cardStack.empty()) {
			flipFirstCard();
		}
	}

	public int numberOfFaceUpCards() {
		return this.numberOfFaceUpCards;
	}

	public boolean empty() {
		return this.cardStack.empty();
	}

	public Stack<Card> getCards() {
		return this.cardStack.cards;
	}

	public int getNumber() {
		return this.number;
	}

    public Card peek() {
        return this.cardStack.peek();
    }
	
}
