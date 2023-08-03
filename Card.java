/*
 * Classname: Card.java
 *
 * Authors: Ray Derick Co, Sean Alexander Morales, & Joshua Inigo Salgado
 *
 * Date: August 3, 2023
 *
 * Description: The Card class represents a playing card in a memory-matching game. Each Card object encapsulates an
 * image identifier corresponding to the face of the card, and two state indicators, "flipped" and "matched". The
 * "flipped" state indicates whether the card is currently flipped over (face up) in the game, and the "matched" state
 * indicates whether the card has been successfully matched with its pair. The Card class provides methods for getting
 * the card's image identifier, checking its flipped and matched states, flipping the card, and setting its matched state.
 */



package com.example.oo3demeterproject;

/**
 * Card class represents a playing card in a card game.
 * A card has an image, and can be in a flipped or matched state.
 */
public class Card {
    private String imageIdentifier;
    private boolean flipped;
    private boolean matched;

    /**
     * Constructs a Card with a specified image identifier.
     *
     * @param imageIdentifier The identifier for the image to be associated with this card.
     */
    public Card(String imageIdentifier) {
        this.imageIdentifier = imageIdentifier;
        this.flipped = false;
        this.matched = false;
    }

    /**
     * Retrieves the image identifier for this card.
     *
     * @return The image identifier for this card.
     */
    public String getImageIdentifier() {
        return imageIdentifier;
    }

    /**
     * Checks if this card is flipped.
     *
     * @return true if this card is flipped; false otherwise.
     */
    public boolean isFlipped() {
        return flipped;
    }

    /**
     * Flips the state of this card. If it was flipped, it becomes unflipped, and vice versa.
     */
    public void flip() {
        this.flipped = !this.flipped;
    }

    /**
     * Checks if this card is matched.
     *
     * @return true if this card is matched; false otherwise.
     */
    public boolean isMatched() {
        return matched;
    }

    /**
     * Sets the matched state of this card.
     *
     * @param matched The new matched state of this card.
     */
    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}
