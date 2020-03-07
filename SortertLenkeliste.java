package oblig3_2;

public class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> implements Liste<T>{
	
	@Override
	public void sett(int pos, T x) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void leggTil(int pos, T x) {
		throw new UnsupportedOperationException();
	}

	public void leggTil(T x) {
		Node ny = new Node(x); 
		Node beholderFre;
		Node beholderBak;
		Node beholder;
		
		if (fremste == null) { // hvis listen er tom
			fremste = ny;
			fremste.forrige = null;
			str++;
		}
		else if (bakerste == null && fremste != null) { // hvis 1 element i listen
			bakerste = ny;
			bakerste.neste = fremste;
			fremste.forrige = bakerste;
			str++;
			if (bakerste.data.compareTo(fremste.data) < 0) { // hvis nytt element skal fremst
				beholder = fremste;
				bakerste.neste = null;
				fremste.forrige = null;
				fremste.neste = bakerste;
				bakerste.forrige = fremste;
				fremste = bakerste;
				bakerste = beholder;
			}
			return;
			
		}
		else if (bakerste != null) { // hvis minst 2 elementer
			beholderFre = fremste;
			beholderBak = ny;
			for (int i = 0; i < str; i++) {
				System.out.println("Sammenligner nå: " + ny.data + " mot: " + beholderFre.data);
				if (beholderBak.data.compareTo(beholderFre.data) < 0) {
					System.out.println("Legger til " + x + " på indeks " + i);
					super.leggTil(i, x);
					
					return;
				}
				beholderFre = fremste.forrige;
			}
			
			super.leggTil(x);
		}
	}
	
	@Override
	public T fjern() {
		
		if (str == 0) {
			throw new UgyldigListeIndeks(-1);
		}
		
		T stoerst = super.fjern(str - 1);
		return stoerst;
	}
	
	@Override
	public T fjern(int pos) {
		
		if (pos < 0 || str == 0 ||pos >= str) {
			throw new UgyldigListeIndeks(-1);
		}
		
		T fjernet = super.fjern(pos);
		return fjernet;
	}
	
	public T hent(int pos) {
		return super.hent(pos);
	}
}