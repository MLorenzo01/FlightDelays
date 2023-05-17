package it.polito.tdp.extflightdelays.model;

public class Rotta {
	private Airport origin;
	private Airport destination;
	int N;
	public Rotta(Airport origin, Airport destination, int n) {
		super();
		this.origin = origin;
		this.destination = destination;
		N = n;
	}
	public Airport getOrigin() {
		return origin;
	}
	public Airport getDestination() {
		return destination;
	}
	public int getN() {
		return N;
	}
	

}
