package oblig3_2;

public class Lenkeliste<T> implements Liste<T>{
	Node fremste, bakerste = null;
	int str = 0;
	final int HJELPER = 1; // brukes for å få tak i elementet foran "pos"-elementet.
	final int MINST_ETT_ELEMENT = 1; // brukes for å bekrefte at det ER et element BAK "pos"-element

	class Node{
		Node neste, forrige = null;
		T data;
		
		Node(T x){
			data = x;
		}
		
		T hentData() {
			return data;
		}
		
		public String toString() {
			String alt = "Data: " + data;
			return alt;
		}
	}
	
	public int stoerrelse() {
		return str;
	}
	
	public void leggTil(T x) {
		Node ny = new Node(x);
		Node beholder;
		
		if (fremste == null) { // hvis listen er tom
			fremste = ny;
			fremste.forrige = null;
			str++;
			return;
		}
		else if (bakerste == null && fremste != null) { // hvis kun ett element 
			bakerste = ny;
			bakerste.neste = fremste;
			fremste.forrige = bakerste;
			str++;
			return;
		}
		else if (bakerste != null) { // hvis minst 2 elementer
			beholder = bakerste;
			
			ny.neste = beholder;
			beholder.forrige = ny;
			bakerste = ny;
			str++;
		}
	}
	
	public T fjern() {
		T fjernet = null;
		Node beholder = fremste;
		int total = stoerrelse();
		
		if (total == 0) {
			throw new UgyldigListeIndeks(-1);
		}
		
		fjernet = beholder.data;
		
		if(fremste.forrige != null) {
			fremste = fremste.forrige;
		}
		else { fremste = null; }
		
		str--;
		
		return fjernet;
	}
	
	public void sett(int pos, T x) {
		Node ny = new Node(x);
		Node peker = fremste;
		Node beholderForan;
		Node beholderBak;
		int total = stoerrelse() - 1;
		int diff = total - pos;
		
		if(pos > total || pos < 0) {
			throw new UgyldigListeIndeks(-1);
		}
		
		if(pos == 0) {
			if(fremste == null || fremste.forrige == null) {
				fremste = ny;
			}
			
			if(diff >= MINST_ETT_ELEMENT) {
				beholderBak = fremste.forrige;
				ny.forrige = beholderBak;
				beholderBak.neste = ny; // "bytter" nest-fremste sin neste-ref.
				fremste = ny;
			}
		}
		
		if(pos > 0) {
			for(int i = 0; i < pos - HJELPER; i++) {
				peker = peker.forrige;
			}
		
			if(diff >= MINST_ETT_ELEMENT) {
				beholderBak = peker.forrige.forrige; 
				beholderBak.neste = ny; // setter og sletter index-element.
				ny.forrige = beholderBak;
			}
		
			beholderForan = peker;
			beholderForan.forrige = ny; // setter og sletter index-element.
			ny.neste = beholderForan;
			
		}
	}
	
	public void leggTil(int pos, T x) {
		Node ny = new Node(x);
		Node peker = fremste;
		Node beholderForan;
		Node beholderBak;
		int total = stoerrelse();
		
		if(pos > total || pos < 0) {
			throw new UgyldigListeIndeks(-1);
		}
		
		if(pos == 0) { // hvis listen er tom
			if(fremste == null) {
				fremste = ny;
				str++;
				return;
			}
			if(fremste != null) { // hvis det er kun 1 element
				beholderBak = fremste;
				beholderBak.neste = ny;
				ny.forrige = beholderBak;
				fremste = ny; // setter instansvariabel til "ny"
				str++;
				return;
			}
		}
		if (str == 2 && pos == 1) { // hvis 2 elementer, og skal legges inn i midten
			ny.neste = fremste;
			fremste.forrige = ny;
			bakerste.neste = ny;
			ny.forrige = bakerste;
					
			str++;
			return;
		}
		
		if(pos > 0) { // hvis minst 2 elementer
			for(int i = 0; i < pos - HJELPER; i++) {
				peker = peker.forrige;
			}
			
			if(pos == total) { // hvis den skal legges inn bakerst
				beholderForan = peker;
				
				ny.neste = beholderForan;
				
				beholderForan.forrige = ny;
				
				bakerste = ny;
				str++;
				return;
			}		
			
			else if (pos < total - HJELPER){ // hvis den skal legges inn annet sted
				beholderBak = peker.forrige;
				beholderForan = peker;
			
				beholderForan.forrige = ny;
				ny.neste = beholderForan;
				ny.forrige = beholderBak;
			
				beholderBak.neste = ny; // kobler "pos"-element til ny
				str++;
				return;
			}
		}
	}
	
	public T fjern(int pos) { 
		T fjernet = null;
		Node peker = fremste;
		Node beholderForan;
		Node beholderBak;
		int total = stoerrelse() - 1;
		int diff = total - pos;
		
		if(pos > total || pos < 0) {
			throw new UgyldigListeIndeks(-1);
		}
		
		if(pos == 0) { // hvis det første elementet skal fjernes...
			if(diff >= MINST_ETT_ELEMENT) { // ... og det ikke kun er 1 element
				beholderForan = fremste;
				beholderBak = fremste.forrige;
				beholderBak.neste = null;
				beholderForan.forrige = null;
				
				fjernet = fremste.data;
				fremste = beholderBak;
			}
			else { // hvis det kun er 1 element
				fjernet = fremste.data;
				fremste.data = null;
			}
		}
		
		if(pos > 0) { // hvis det ikke er det første elementet som skal fjernes
			for(int i = 0; i < pos - HJELPER; i++) {
				peker = peker.forrige;
			}
			if(diff >= MINST_ETT_ELEMENT) { // hvis det finnes elementer bak 'pos'
				beholderBak = peker.forrige.forrige;
				beholderForan = peker;
				
				fjernet = peker.forrige.data;
				
				beholderForan.forrige = beholderBak;
				beholderBak.neste = beholderForan; // "hopper" over ref. til "pos"-element.
			}
			else { // hvis bakerste element skal fjernes
				beholderForan = peker;
				
				fjernet = peker.forrige.data;
				
				beholderForan.forrige = null;
				bakerste = beholderForan;
			}
		}
		str--;

		return fjernet;
	}
	
	public T hent(int pos) {
		T hentet = null;
		Node peker = fremste;
		int total = stoerrelse() - 1;
		
		if(pos > total || pos < 0) {
			throw new UgyldigListeIndeks(-1);
		}
		
		if(pos == 0) {
			hentet = fremste.data;
		}
		
		if(pos > 0) { 
			for(int i = 0; i < pos - HJELPER; i++) {
				peker = peker.forrige;
			}
			hentet = peker.forrige.data; 
		}
		
		return hentet;
	}
	
	
	
	
	
	
}
