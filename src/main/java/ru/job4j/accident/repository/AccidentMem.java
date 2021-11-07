package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements MemStore {
    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger accidentId = new AtomicInteger(5);

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

    @Override
    public void save(Accident accident) {
        int id = accidentId.incrementAndGet();
        accident.setId(id);
        accidents.put(id, accident);
    }
}
