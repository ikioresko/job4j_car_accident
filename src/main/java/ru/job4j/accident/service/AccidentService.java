package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.MemStore;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {
    private final MemStore store;

    public AccidentService(MemStore store) {
        this.store = store;
    }

    public List<Accident> getAccidents() {
        return new ArrayList<>(store.getAccidents());
    }

    public void save(Accident accident) {
        store.save(accident);
    }
}
