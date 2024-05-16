package com.JonasSmendes.musicSoundScreen.principal;

import com.JonasSmendes.musicSoundScreen.model.Cantor;
import com.JonasSmendes.musicSoundScreen.model.Estilo;
import com.JonasSmendes.musicSoundScreen.model.Musica;
import com.JonasSmendes.musicSoundScreen.repository.CantorRepository;

import java.util.*;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private List<Cantor> cantores = new ArrayList<>();
    private List<Musica> musicas = new ArrayList<>();

    private CantorRepository repository;

    public Principal(CantorRepository cantorRepository) {
        this.repository = cantorRepository;
    }

    String menu = """
            ************ Screen Music *************
            
            1 - Cadastrar artistas
            2 - Lista de cantores adicionados
            3 - Adicionar musica
            4 - Lista de musicas adicionadas
            5 - Lista de musica de um cantor cadastrado
            
            0 - sair
            
            """;

    public void exibeMenu(){

        var opcoes = -1;

        while (opcoes != 0){
            System.out.println(menu);

            opcoes = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcoes){
                case 1:
                    adicionarCantor();
                    break;
                case 2:
                    listaDeCantores();
                    break;
                case 3:
                    adiconarMusica();
                    break;
                case 4:
                    listaMusicas();
                    break;
                case 5:
                    listaDeMusicaPorCantor();
                    break;
                case 0:
                    System.out.println("saindo...");
                    break;
            }

        }

    }

    private void adicionarCantor() {
        System.out.println("Qual cantor que deseja adicionar?");
        var nomeCantor = scanner.nextLine();

        System.out.println("ele é solo, dupla ou banda?");
        var nomeEstilo = scanner.nextLine();

        System.out.println("qual o genero que canta a(o) " + nomeCantor + "?" );
        var nomeGenero = scanner.nextLine();

        Estilo estilo = Estilo.fromEstilo(nomeEstilo);
        Cantor cantor = new Cantor(nomeCantor, nomeGenero, estilo);

        Cantor novoCantor = repository.save(cantor);
        System.out.println("cantor salvo com sucesso");
        System.out.println(novoCantor);

    }

    private void listaDeCantores(){
        cantores = repository.findAll();
        cantores.stream()
                .sorted(Comparator.comparing(Cantor::getEstilo))
                .forEach(System.out::println);
    }

    private void adiconarMusica(){
        listaDeCantores();
        System.out.println("qual cantor deseja escolher?");
        var nomeCantor = scanner.nextLine();

        System.out.println("qual musica quer adicionar a ele?");
        var nomeMusica = scanner.nextLine();

        Cantor cantor = repository.findByNome(nomeCantor)
                .orElseThrow(() -> new IllegalArgumentException("cantor não encontrado " + nomeCantor));

        Musica novaMusica = new Musica(nomeMusica, cantor);

        cantor.getMusica().add(novaMusica);
        repository.save(cantor);

        System.out.println("musica adicionada ao cantor " + nomeCantor);

    }

    private void listaMusicas(){
        List<Cantor> cantores = repository.findAll();

        cantores.stream()
                .flatMap(c -> c.getMusica().stream()
                        .sorted(Comparator.comparing(m -> m.getCantor().getNome())))
                .forEach(f ->
                        System.out.println("Musica: " + f.getNome() + " , " + f.getCantor().getEstilo()+": " + f.getCantor().getNome()));
    }

    private void listaDeMusicaPorCantor(){
        System.out.println("qual cantor deseja escolher?");
        var nomeCantor = scanner.nextLine();

        List<Musica> listaDeMusicaCantor = repository.listaDeMusicaPorCantor(nomeCantor);

        listaDeMusicaCantor.forEach(System.out::println);

    }

}
