package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Repository
public class AccidentMem implements MemStore {
    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
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
            list.add(accident);
        }
        return list;
    }

    @Override
    public Collection<Accident> getAccidents() {
        return accidents.values();
    }
}
