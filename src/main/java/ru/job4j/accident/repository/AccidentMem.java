package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
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
        for (int i = 0; i < 6; i++) {
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
        accident.setId(id);
        accidents.put(id, accident);
    }

    private void update(Accident accident) {
        accidents.put(accident.getId(), accident);
    }
}
