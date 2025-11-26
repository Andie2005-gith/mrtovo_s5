package repository;

import entity.Evaluation;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    // Récupérer toutes les évaluations d’un employé par son id
    @Query("SELECT e FROM Evaluation e WHERE e.employe.id = :idEmploye")
    List<Evaluation> findByEmployeId(@Param("idEmploye") Long idEmploye);

    // Récupérer une évaluation spécifique par employé et mois
    @Query("SELECT e FROM Evaluation e WHERE e.employe.id = :idEmploye AND e.mois = :mois")
    Optional<Evaluation> findByEmployeAndMois(@Param("idEmploye") Long idEmploye, @Param("mois") String mois);
}
