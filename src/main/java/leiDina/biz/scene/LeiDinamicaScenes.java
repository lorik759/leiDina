package main.java.leiDina.biz.scene;

import main.java.leiDina.tec.javafx.scene.Scenes;

/**
 * @author vitor.alves
 */
public enum LeiDinamicaScenes {
    MAIN {
        @Override
        public Scenes getScene() {
            return new MainScene();
        }
    },

    LEITURA {
        @Override
        public Scenes getScene() {
            return new LeituraScene();
        }
    };

    public abstract Scenes getScene();
}
