package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

public interface MemStore {

    List<Accident> getAccidents();

    List<Rule> getRules();

    Accident save(Accident accident);

    List<AccidentType> getAccidentTypes();

    Accident getAccidentByID(int id);
}