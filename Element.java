/*
CS 1027B – Assignment 1
Name: Christian Tamayo
Student Number: 251 433 749
Email: ctamayo@uwo.ca
Created: Jan 25, 2025
*/

public class Element {
    //instance variables
    private int atomicNo;
    private float atomicWeight;
    private String symbol;
    private String name;
    private String state;
    private String type;

    //constructor
    public Element(int num, float wt, String sym, String nm, String st, String ty) {
        this.atomicNo = num;
        this.atomicWeight = wt;
        this.symbol = sym;
        this.name = nm;
        this.state = st;
        this.type = ty;
    }

    //getters

    public int getAtomicNo() {
        return this.atomicNo;
    }

    public float getAtomicWeight() {
        return this.atomicWeight;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }
    
    public String getType() {
        return this.type;
    }

    //setters

    public void setName(String newName) {
        this.name = newName;
    }

    public void setState(String newState) {
        this.state = newState;
    }

    public void setType(String newType) {
        this.type = newType;
    }

    //toString
    public String toString() {
        return this.symbol + " (" + this.name + ")";
    }

    //determines equality of element objects
    public boolean equals(Element other) {
        return (this.atomicNo == other.atomicNo);
    }
}
