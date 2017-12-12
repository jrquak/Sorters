package com.jordy.sorters;

/**
 * Genereerd een lijst met klasnamen voor een aantal studenten.
 */

public class KlasGenerator {

    private static final String[] RICHTINGEN = { "A", "B", "C", "D", "E" };
    private static final int MAXIMUM_STUDENTEN_PER_KLAS = 32;

    /**
     * Genereerd een willekeurig verdeelde lijst met klasnamen voor een aantal studenten.
     *
     * @param aantalStudenten
     *            het aantal studenten waarvoor de klassen gemaakt moeten worden.
     * @return een lijst met per student de klas waar hij/zijn aan is toegewezen.
     */
    public static String[] maakKlassen(int aantalStudenten) {
        int aantalStudentenPerRichting = (int) Math.ceil((double) aantalStudenten / RICHTINGEN.length);
        int aantalKlassenPerRichting = (int) Math.ceil((double) aantalStudentenPerRichting / MAXIMUM_STUDENTEN_PER_KLAS);

        String[] klassen = new String[aantalStudenten];
        for (int i = 0; i < aantalStudenten; i++) {
            klassen[i] = String.format("%s2%04d", RICHTINGEN[i % RICHTINGEN.length],
                    (i % aantalKlassenPerRichting) + 1);
        }
        Schudder.schud(klassen);

        return klassen;
    }
}
