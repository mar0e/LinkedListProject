package oblig3_2;

class UgyldigListeIndeks extends RuntimeException {
    UgyldigListeIndeks(int indeks){
        super("Ugyldig indeks:" + indeks);
    }
}
