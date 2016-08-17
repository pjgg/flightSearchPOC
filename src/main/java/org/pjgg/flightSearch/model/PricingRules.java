package org.pjgg.flightSearch.model;


public class PricingRules {

    private int from;

    private int to;

    private int percentage;

    public PricingRules(int from, int to, int percentage) {
        this.from = from;
        this.to = to;
        this.percentage = percentage;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
