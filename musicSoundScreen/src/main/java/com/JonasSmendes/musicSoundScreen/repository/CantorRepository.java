package com.JonasSmendes.musicSoundScreen.repository;

import com.JonasSmendes.musicSoundScreen.model.Cantor;
import com.JonasSmendes.musicSoundScreen.model.Estilo;
import com.JonasSmendes.musicSoundScreen.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CantorRepository extends JpaRepository<Cantor, Long> {

    Optional<Cantor> findByNome(String nomeCantor);

    @Query("SELECT m FROM Cantor c JOIN c.musica m WHERE LOWER(c.nome) = LOWER(:nomeCantor)")
    List<Musica> listaDeMusicaPorCantor(String nomeCantor);

}
