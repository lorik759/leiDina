package main.java.leiDina.biz.service;

import java.util.List;
import main.java.leiDina.biz.controllers.leitura.LeituraModel;

/**
 * @author vitor.alves
 */
public class LeituraService {

    private ConfiguracaoService configuracaoService;

    private int next;

    private List<String> texts;

    private StringBuilder stringBuilder;

    public void iniciarLeitura(LeituraModel model) {
        this.texts = this.getText(model);
        next = 0;
        this.stringBuilder = new StringBuilder();
    }

    private int getNumeroDePalavras() {
        return configuracaoService.findOrCreatConfiguracaoPadrao().getNumeroPalavras();
    }

    public int getNumeroDeVezParaLer() {
        return this.texts.size();
    }

    public double getSegundos() {
        return configuracaoService.findOrCreatConfiguracaoPadrao().getSeconds();
    }

    public boolean temAlgumaCoisaALer() {
        return texts != null && texts.size() > 0;
    }

    public void setConfiguracaoService(ConfiguracaoService configuracaoService) {
        this.configuracaoService = configuracaoService;
    }

    public List<String> getText(LeituraModel model) {
        return new LeituraDinamicaParser(model.getTextoEntrada(), this.getNumeroDePalavras()).parse();
    }

    public String getProximaString() {
        if (next < texts.size()) {
            stringBuilder.append(texts.get(next));
            next++;
            return stringBuilder.toString();
        } else {
            return "";
        }
    }
}
