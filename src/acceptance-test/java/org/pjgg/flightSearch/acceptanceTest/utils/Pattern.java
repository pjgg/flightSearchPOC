package org.pjgg.flightSearch.acceptanceTest.utils;


// CODE INSPIRED FROM THE FOLLOWING WEB SITE
//https://kerflyn.wordpress.com/2012/05/09/towards-pattern-matching-in-java/
public interface Pattern {

    boolean matches(Object value);

    Object apply(Object value);
}
