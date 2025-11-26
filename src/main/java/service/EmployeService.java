package service;

import entity.Contrat;
import entity.Employe;
import repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepository repository;

    @Autowired
    private ContratService contratService;

    // ===== Sauvegarder un employé =====
    public Employe save(Employe employe) {
        return repository.save(employe);
    }

    // ===== Récupérer tous les employés =====
    public List<Employe> findAll() {
        return repository.findAll();
    }

    // ===== Récupérer un employé par ID =====
    // Retourne directement l'objet Employe ou null si non trouvé
    public Employe findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    // ===== Vérifier si un email existe déjà =====
    public boolean existsByMail(String mail) {
        return repository.findAll().stream()
                .anyMatch(e -> e.getMail() != null && e.getMail().equalsIgnoreCase(mail));
    }

    // ===== Récupérer par id_candidat =====
    public Employe findByIdCandidat(Integer idCandidat) {
        return repository.findAll().stream()
                .filter(e -> e.getIdCandidat() != null && e.getIdCandidat().equals(idCandidat))
                .findFirst()
                .orElse(null);
    }

    // ===== Employés sans contrat =====
    public List<Employe> findEmployesSansContrat() {
        return repository.findAll().stream()
                .filter(employe -> !contratService.existsContratActifByEmploye(employe.getId()))
                .collect(Collectors.toList());
    }

    public List<Employe> findEmployesSansContratV2() {
        return repository.findAll().stream()
                .filter(employe -> contratService.findContratsActifsByEmploye(employe.getId()).isEmpty())
                .collect(Collectors.toList());
    }

    // ===== Statistiques =====
    public List<Object[]> getBirthYearStats() {
        return repository.countByBirthYear();
    }

    public List<Object[]> countByDepartement() {
        return repository.countByDepartement();
    }
}
