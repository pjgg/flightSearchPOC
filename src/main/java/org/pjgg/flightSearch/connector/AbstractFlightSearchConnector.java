package org.pjgg.flightSearch.connector;


import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractFlightSearchConnector<E> {

    private Collection<E> entities = new ArrayList<E>();

    public void addEntity(E entity) {
        entities.add(entity);
    }

    public Stream<E> filterEntities(Predicate<E> predicate) {
        return entities.stream().filter(predicate);
    }

    public void removeEntities(Predicate<E> predicate) {
        entities.removeAll(
                filterEntities(predicate).collect(Collectors.toList())
        );
    }

}
