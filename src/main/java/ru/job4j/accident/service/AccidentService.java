package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.MemStore;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {
    private final MemStore store;

    public AccidentService(MemStore store) {
        this.store = store;
    }

    public List<Accident> getAccidents() {
        return store.getAccidents();
    }

    public void save(Accident accident, String[] rules) {
        List<Rule> rulesList = store.getRules();
        for (String str : rules) {
            accident.getRules().add(rulesList.get(Integer.parseInt(str) - 1));
        }
        accident.getType()
                .setName(store.getAccidentTypes()
                        .get(accident.getType().getId())
                        .getName());
        store.save(accident);
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(store.getAccidents().get(id - 1));
    }

    public List<AccidentType> getAccidentTypes() {
        return store.getAccidentTypes();
    }

    public List<Rule> getRules() {
        return store.getRules();
    }
}
