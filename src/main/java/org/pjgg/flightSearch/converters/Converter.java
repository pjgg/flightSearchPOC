package org.pjgg.flightSearch.converters;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public interface Converter <A, B>{

    default Stream<B> convertToStream(final Stream<A> input) {
        return input.map(element -> this.apply(element));
    }

    default List<B> convertToList(final Stream<A> input) {
        return input.map(element -> this.apply(element)).collect(Collectors.toList());
    }

    B apply(A t);

}
