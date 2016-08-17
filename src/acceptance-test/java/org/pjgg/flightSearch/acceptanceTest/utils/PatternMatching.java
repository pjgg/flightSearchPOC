package org.pjgg.flightSearch.acceptanceTest.utils;

// CODE INSPIRED FROM THE FOLLOWING WEB SITE
//https://kerflyn.wordpress.com/2012/05/09/towards-pattern-matching-in-java/
public class PatternMatching {

    private Pattern[] patterns;

    public PatternMatching(Pattern... patterns) {
        this.patterns = patterns;
    }

    public Object matchFor(Object value) {
        for (Pattern pattern : patterns)
            if (pattern.matches(value))
                return pattern.apply(value);

        throw new IllegalArgumentException("cannot match " + value);
    }
}
