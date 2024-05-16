package com.JonasSmendes.musicSoundScreen.model;

import java.util.HashMap;
import java.util.Map;

public enum Estilo {

    SOLO("solo"),
    DUPLA("dupla"),
    BANDA("banda");

    private String categoriaBanda;
    private static final Map<String, Estilo> estiloMap = new HashMap<>();

    static {
        for (Estilo estilo : Estilo.values()) {
            estiloMap.put(estilo.categoriaBanda.toLowerCase(), estilo);
        }
    }

    Estilo(String categoriaBanda) {
        this.categoriaBanda = categoriaBanda;
    }

    public static Estilo fromEstilo(String text) {
        Estilo estilo = estiloMap.get(text.toLowerCase());
        if (estilo != null) {
            return estilo;
        }
        throw new IllegalArgumentException("Nenhum estilo encontrado para a string fornecida: " + text);
    }
}

