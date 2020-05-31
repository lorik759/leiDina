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
        int inicio = 0;
        List<String> groupWords = new ArrayList<>();
        int numeroDeBlocosParaDividir = (everyWord.size() + numeroDePalavrasParaAgrupar - 1) / numeroDePalavrasParaAgrupar;
        for (int i = 0; i < numeroDeBlocosParaDividir; i++) {
            int fim = inicio + numeroDePalavrasParaAgrupar;
            groupWords.add(getPalavarsAgrupadasAte(inicio, fim, everyWord));
            inicio = fim;
        }
        return groupWords;
    }

    private String getPalavarsAgrupadasAte(int inicio, int fim, List<String> everyWord) {
        StringBuilder stringBuilder = new StringBuilder();
        int posicao = inicio;
        int pontoDeParada = Math.min(everyWord.size(), fim);
        for (; posicao < pontoDeParada; posicao++) {
            stringBuilder.append(everyWord.get(posicao));
        }
        return stringBuilder.toString();
    }

    private List<String> getSeparadoPorPalavraComQuebraDeLinha() {
        List<String> strings = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = textoNaoFormatado.toCharArray();
        for (char proximaLetra : chars) {
            if (proximaLetra == ' ') {
                strings.add(stringBuilder.append(" ").toString());
                stringBuilder = new StringBuilder();
            } else if (isCharInicioDeQuebraDeLinha(proximaLetra)) {
                stringBuilder.append("\n");
                String toString = stringBuilder.toString();
                strings.add(toString);
                stringBuilder = new StringBuilder();
            } else {
                stringBuilder.append(proximaLetra);
            }
        }
        addUltimaPalavra(strings, stringBuilder);
        return strings;
    }

    private void addUltimaPalavra(List<String> strings, StringBuilder stringBuilder) {
        strings.add(stringBuilder.toString());
    }

    private boolean isCharInicioDeQuebraDeLinha(char letra) {
        return letra == '\n';
    }
}
