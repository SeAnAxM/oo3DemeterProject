package com.example.oo3demeterproject;

public class Card {
    private String imageIdentifier;
    private boolean flipped;
    private boolean matched;

    public Card(String imageIdentifier) {
        this.imageIdentifier = imageIdentifier;
        this.flipped = false;
        this.matched = false;
    }

    public String getImageIdentifier() {
        return imageIdentifier;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void flip() {
        this.flipped = !this.flipped;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}
