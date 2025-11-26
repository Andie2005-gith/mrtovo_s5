package service;

import entity.Evaluation;
import repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    // Enregistrer une évaluation
    public Evaluation save(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    // Récupérer toutes les évaluations d’un employé
    public List<Evaluation> findByEmploye(Long employeId) {
        return evaluationRepository.findByEmployeId(employeId);
    }
}
