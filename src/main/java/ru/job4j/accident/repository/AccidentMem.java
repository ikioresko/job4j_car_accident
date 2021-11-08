package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements MemStore {
    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger accidentId = new AtomicInteger(5);
    private final List<AccidentType> accidentTypes = new ArrayList<>();
    private final List<Rule> rules = new ArrayList<>();

    public AccidentMem() {
        generateAccidentTypes();
        generateRules();
        for (Accident acc : generate()) {
            accidents.put(acc.getId(), acc);
        }
    }

    private List<Accident> generate() {
        List<Accident> list = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Accident accident = new Accident();
            accident.setId(i);
            accident.setName("Accident" + i);
            accident.setText("Text" + i);
            accident.setAddress("Address" + i);
            randomSetTypeOfAccident(accident);
            list.add(accident);
        }
        return list;
    }

    private void generateAccidentTypes() {
        accidentTypes.add(AccidentType.of(0, "Две машины"));
        accidentTypes.add(AccidentType.of(1, "Машина и человек"));
        accidentTypes.add(AccidentType.of(2, "Машина и велосипед"));
    }

    private void generateRules() {
        rules.add(Rule.of(1, "Статья. 1"));
        rules.add(Rule.of(2, "Статья. 2"));
        rules.add(Rule.of(3, "Статья. 3"));
    }

    private void randomSetTypeOfAccident(Accident accident) {
        int randomId = new Random().nextInt(3);
        AccidentType accidentType = new AccidentType();
        accidentType.setId(randomId);
        accidentType.setName(accidentTypes.get(randomId).getName());
        accident.setType(accidentType);
    }

    @Override
    public List<Accident> getAccidents() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public void save(Accident accident) {
        if (accident.getId() == 0) {
            create(accident);
        } else {
            update(accident);
        }
    }

    private void create(Accident accident) {
        int id = accidentId.incrementAndGet();
        setupAccidentType(accident);
        accident.setId(id);
        accidents.put(id, accident);
    }

    private void update(Accident accident) {
        setupAccidentType(accident);
        accidents.put(accident.getId(), accident);
    }

    private void setupAccidentType(Accident accident) {
        String nameOfType = accidentTypes.get(accident.getType().getId()).getName();
        accident.getType().setName(nameOfType);
    }

    @Override
    public List<AccidentType> getAccidentTypes() {
        return new ArrayList<>(accidentTypes);
    }

    @Override
    public List<Rule> getRules() {
        return new ArrayList<>(rules);
    }
}
