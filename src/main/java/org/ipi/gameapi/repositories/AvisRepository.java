package org.ipi.gameapi.repositories;

import org.ipi.gameapi.models.Avis;
import org.ipi.gameapi.models.Jeu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisRepository extends CrudRepository<Avis, Long> {

    @Override
    List<Avis> findAll();

    @Query("FROM Avis avis WHERE avis.jeu  = :jeu ORDER BY avis.id DESC LIMIT 1")
    Avis findLastAvis(@Param("jeu") Jeu jeu);

    @Query("FROM Avis avis WHERE avis.jeu  = :jeu")
    List<Avis> findAllAvisByJeu(@Param("jeu") Jeu jeu);

}
