package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;

import java.util.Collection;

public interface MemStore {

    Collection<Accident> getAccidents();

    void save(Accident accident);
}