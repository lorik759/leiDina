package main.java.leiDina.biz.service;

import java.util.ArrayList;
import java.util.List;
import main.java.leiDina.biz.controllers.leitura.LeituraModel;
import main.java.leiDina.biz.model.Configuracao;
import main.java.leiDina.tec.core.utils.StringUtils;

/**
 * @author vitor.alves
 */
public class LeituraService {

    private ConfiguracaoService configuracaoService;

    private int next;

    private List<String> texts;

    public String getNextString() {
        if (texts != null && next < texts.size()) {
            String nextText = texts.get(next);
            next++;
            return nextText;
        }
        return "";
    }

    public void startReading(LeituraModel model) {
        this.texts = new ArrayList<>();
        String msg = model.getTextoEntrada();
        this.next = 0;
        if (StringUtils.isNotEmpty(msg)) {
            this.createTexts(msg.split("\\W+"));
        }
    }

    private void createTexts(String[] split) {
        StringBuilder lastString = new StringBuilder();
        int nextWord = 0;
        int length = split.length;
        int numeroDePalavras = this.getNumeroDePalavras();
        int numberOfTimesToSeparate = (length + numeroDePalavras - 1) / numeroDePalavras;
        for (int j = 0; j < numberOfTimesToSeparate; j++) {
            for (int i = 0; i < numeroDePalavras; i++) {
                if (nextWord < length) {
                    String string = split[nextWord];
                    nextWord++;
                    lastString.append(" ").append(string);
                }
            }
            this.texts.add(lastString.toString());
        }
    }

    private int getNumeroDePalavras() {
        Configuracao configuracao = configuracaoService.findOrCreatConfiguracaoPadrao();
        return configuracao.getNumeroPalavras();
    }

    public int getNumberOfTimesToRead() {
        return this.texts.size();
    }

    public double getSegundos() {
        return configuracaoService.findOrCreatConfiguracaoPadrao().getSeconds();
    }

    public boolean isTharSomethingToRead() {
        return texts != null && texts.size() > 0;
    }

    public void setConfiguracaoService(ConfiguracaoService configuracaoService) {
        this.configuracaoService = configuracaoService;
    }
}
