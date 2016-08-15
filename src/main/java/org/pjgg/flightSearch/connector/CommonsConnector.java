package org.pjgg.flightSearch.connector;


import java.util.function.Predicate;
import java.util.stream.Stream;

public interface CommonsConnector<E> {

    public void addEntity(E airport);

    public Stream<E> filterEntities(Predicate<E> predicate);

    public void removeEntities(Predicate<E> predicate);

}
