package klondike.models;

public class Foundation {

    private Suit suit;
    
	private CardStack cardStack;
	
    public Foundation(Suit suit) {
		this.cardStack = new CardStack();
        this.suit = suit;
    }

    public boolean isComplete() {
        return this.cardStack.cards.size() == Number.values().length;
    }

    public boolean fitsIn(Card card) {
        assert card != null;
        return card.getSuit() == this.suit &&
                (card.getNumber() == Number.AS ||
                        (!this.cardStack.empty() && card.isNextTo(this.cardStack.peek())));
    }

    public Suit getSuit() {
        return this.suit;
    }
    
    public boolean empty() {
        return this.cardStack.empty();
    }

    public Card peek() {
        return this.cardStack.peek();
    }

    public Card pop() {
        return this.cardStack.pop();
    }

    public void push(Card card) {
        this.cardStack.push(card);
    }
}
