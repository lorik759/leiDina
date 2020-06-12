package main.java.leiDina.biz.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto responsável pelo pars do texto de entrada e agrupar ele para uma leitura dinâmica.
 *
 * @author vitor.alves
 */
public class LeituraDinamicaParser {

    private String textoNaoFormatado;

    private int numeroDePalavrasParaAgrupar;

    public LeituraDinamicaParser(String textoNaoFormatado, int numeroDePalavrasParaAgrupar) {
        this.textoNaoFormatado = textoNaoFormatado;
        this.numeroDePalavrasParaAgrupar = numeroDePalavrasParaAgrupar;
    }


    /**
     * Pases o texto de entrada e agrupa ele em uma lista. Cada item da lista contem um conjunto de texto que deve ser utilizado para a leitura
     * dinâmica.
     */
    public List<String> parse() {
        return this.agrupar(getTextoSeparadoPorPalavraComQuebraDeLinha());
    }

    /**
     * Agrupa as palavers em quantidade de palavras a ser apresentada.
     *
     * @param everyWord Uma lista de todas as palavras do texto.
     * @return Uma lista com as palavras agrupadas.
     */
    private List<String> agrupar(List<String> everyWord) {
        int inicio = 0;
        List<String> groupWords = new ArrayList<>();
        int numeroDeBlocosParaDividir = getNumeroDeBlocosParaDividirTexto(everyWord);
        for (int i = 0; i < numeroDeBlocosParaDividir; i++) {
            int fim = inicio + numeroDePalavrasParaAgrupar;
            groupWords.add(getPalavarsAgrupadasAte(inicio, fim, everyWord));
            inicio = fim;
        }
        return groupWords;
    }

    private int getNumeroDeBlocosParaDividirTexto(List<String> everyWord) {
        return (everyWord.size() + numeroDePalavrasParaAgrupar - 1) / numeroDePalavrasParaAgrupar;
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

    private List<String> getTextoSeparadoPorPalavraComQuebraDeLinha() {
        List<String> strings = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (char proximaLetra : textoNaoFormatado.toCharArray()) {
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
