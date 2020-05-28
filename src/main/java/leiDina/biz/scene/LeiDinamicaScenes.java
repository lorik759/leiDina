package main.java.leiDina.biz.scene;

import main.java.leiDina.tec.javafx.scene.Scenes;

/**
 * @author vitor.alves
 */
public enum LeiDinamicaScenes {
    MAIN(new MainScene()), LEITURA(new LeituraScene()), CONFIGURACAO(new ConfiguracaoScene());

    private Scenes scenes;

    LeiDinamicaScenes(Scenes scenes) {
        this.scenes = scenes;
    }

    public Scenes getScenes() {
        return scenes;
    }
}
