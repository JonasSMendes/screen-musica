package com.JonasSmendes.musicSoundScreen.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "cantores")
public class Cantor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String genero;
    @Enumerated(EnumType.STRING)
    private Estilo estilo;
    @OneToMany(mappedBy = "cantor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musica;

    public Cantor(){}

    public Cantor(String nome, String genero, Estilo estilo) {
        this.nome = nome;
        this.genero = genero;
        this.estilo = Estilo.fromEstilo(String.valueOf(estilo));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public List<Musica> getMusica() {
        return musica;
    }

    public void setMusica(List<Musica> musica) {
        this.musica = musica;
    }

    @Override
    public String toString() {
        return "Cantor{" +
                ", nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", estilo=" + estilo +
                '}';
    }
}
