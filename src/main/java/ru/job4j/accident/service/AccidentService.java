package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.RuleRepo;
import ru.job4j.accident.repository.TypeRepo;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {
    private final AccidentRepository accidentRepository;
    private final RuleRepo ruleRepo;
    private final TypeRepo typeRepo;

    public AccidentService(AccidentRepository store, RuleRepo ruleRepo, TypeRepo typeRepo) {
        this.accidentRepository = store;
        this.ruleRepo = ruleRepo;
        this.typeRepo = typeRepo;
    }

    public List<Accident> getAccidents() {
        return accidentRepository.findAll();
    }

    public void save(Accident accident, String[] rules) {
        List<Rule> rulesList = (List<Rule>) ruleRepo.findAll();
        for (String str : rules) {
            accident.getRules().add(rulesList.get(Integer.parseInt(str) - 1));
        }
        accidentRepository.save(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    public List<AccidentType> getAccidentTypes() {
        return (List<AccidentType>) typeRepo.findAll();
    }

    public List<Rule> getRules() {
        return (List<Rule>) ruleRepo.findAll();
    }
}
