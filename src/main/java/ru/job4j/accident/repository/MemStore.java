package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

public interface MemStore {

    List<Accident> getAccidents();

    void save(Accident accident);

    List<AccidentType> getAccidentTypes();
}