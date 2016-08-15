package org.pjgg.flightSearch.service;



public enum PriceServiceLocator {

    INSTANCE;

    private final PriceCalculator pricingRulesCalculator;
    private final PriceCalculator passagerTypeCalculator;

    private PriceServiceLocator(){
        pricingRulesCalculator = new PricingRuleImpl();
        passagerTypeCalculator = new PassengerTypeImpl();
    }

    public PriceCalculator getPricingRulesCalculator(){return this.pricingRulesCalculator;}

    public PriceCalculator getPassagerTypeCalculator(){return this.passagerTypeCalculator;}

}
