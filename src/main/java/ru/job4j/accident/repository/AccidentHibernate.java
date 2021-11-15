package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.function.Function;

public class AccidentHibernate implements MemStore {
    private static final Logger LOG = LoggerFactory.getLogger(AccidentHibernate.class.getName());
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        T rsl = null;
        try {
            rsl = command.apply(session);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            LOG.error("Exception: ", e);
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public Accident save(Accident accident) {
        return accident.getId() == 0 ? create(accident) : update(accident);

    }

    private Accident create(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            for (Rule rule : accident.getRules()) {
                accident.getRules().add(rule);
            }
            session.save(accident);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
        return accident;
    }

    private Accident update(Accident accident) {
        this.tx(session -> session.merge(accident));
        return accident;
    }

    @Override
    public List<Accident> getAccidents() {
        return this.tx(session -> session.createQuery("select distinct a from Accident a "
                + "join fetch a.rules order by a.id asc", Accident.class).list());
    }

    @Override
    public Accident getAccidentByID(int id) {
        return this.tx(session -> session.createQuery("select distinct a from Accident a "
                + "join fetch a.rules where a.id =: ID", Accident.class)
                .setParameter("ID", id).uniqueResult());
    }

    @Override
    public List<Rule> getRules() {
        return this.tx(session -> session.createQuery("from Rule", Rule.class)
                .list());
    }

    @Override
    public List<AccidentType> getAccidentTypes() {
        return this.tx(session -> session.createQuery("from AccidentType", AccidentType.class)
                .list());
    }
}