package controller;

import entity.Employe;
import entity.Evaluation;
import service.EmployeService;
import service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EvaluationController {

    @Autowired
    private EmployeService employeService;

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/rh/evaluation")
    public String afficherFormulaireEvaluation(
            @RequestParam("id_employe") Integer idEmploye,
            Model model
    ) {
        Employe employe = employeService.findById(idEmploye);
        model.addAttribute("employe", employe);
        return "rh/evaluation"; // correspond à evaluation.jsp
    }

    @PostMapping("/rh/evaluation/save")
    public String enregistrerEvaluation(
            @RequestParam("id_employe") Integer idEmploye,
            @RequestParam("mois") String mois,
            @RequestParam("note") int note,
            Model model
    ) {
        Employe employe = employeService.findById(idEmploye);

        Evaluation evaluation = new Evaluation();
        evaluation.setEmploye(employe);
        evaluation.setMois(mois);
        evaluation.setNote(note);

        evaluationService.save(evaluation);

        model.addAttribute("employe", employe);
        model.addAttribute("message", "Évaluation enregistrée avec succès !");
        return "rh/evaluation"; // correspond à evaluation.jsp
    }
}
