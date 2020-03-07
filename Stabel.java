package oblig3_2;

public class Stabel<T> extends Lenkeliste<T> implements Liste<T>{
	public void leggPaa(T x) {
		leggTil(x);
	}
	
	public T taAv() {
		return fjern(str - 1);
	}
	
}
