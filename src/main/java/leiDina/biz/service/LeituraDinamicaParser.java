package main.java.leiDina.biz.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vitor.alves
 */
public class LeituraDinamicaParser {

    private String textoNaoFormatado;

    private int numeroDePalavrasParaAgrupar;

    public LeituraDinamicaParser(String textoNaoFormatado, int numeroDePalavrasParaAgrupar) {
        this.textoNaoFormatado = textoNaoFormatado;
        this.numeroDePalavrasParaAgrupar = numeroDePalavrasParaAgrupar;
    }


    public List<String> parse() {
        return this.agrupar(getSeparadoPorPalavraComQuebraDeLinha());
    }

    private List<String> agrupar(List<String> everyWord) {
        int numeroDePalavarsAgrupadas = 0;
        StringBuilder stringBuilder = new StringBuilder();
        List<String> groupWords = new ArrayList<>();
        for (String word : everyWord) {
            if (numeroDePalavrasParaAgrupar == numeroDePalavarsAgrupadas) {
                groupWords.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                numeroDePalavarsAgrupadas = 0;
            } else if (isUltimaPalavra(word, everyWord)) {
                stringBuilder.append(word);
                groupWords.add(stringBuilder.toString());
            } else {
                stringBuilder.append(word);
                numeroDePalavarsAgrupadas++;
            }
        }
        return groupWords;
    }

    private boolean isUltimaPalavra(String word, List<String> everyWord) {
        return everyWord.get(everyWord.size() - 1).equals(word);
    }

    private List<String> getSeparadoPorPalavraComQuebraDeLinha() {
        List<String> strings = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < textoNaoFormatado.length(); i++) {
            if (textoNaoFormatado.charAt(i) == ' ') {
                strings.add(stringBuilder.append(" ").toString());
                stringBuilder = new StringBuilder();
            } else if (isCharInicioDeQuebraDeLinha(i)) {
                stringBuilder.append(System.lineSeparator());
                strings.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            } else if (i == textoNaoFormatado.length() - 1) {
                strings.add(stringBuilder.toString());
            } else {
                stringBuilder.append(textoNaoFormatado.charAt(i));
            }
        }
        return strings;
    }

    private boolean isCharInicioDeQuebraDeLinha(int i) {
        return textoNaoFormatado.length() >= i + 1 && textoNaoFormatado.substring(i, i + 1).equals(System.lineSeparator());
    }
}
