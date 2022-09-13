package org.brienze.biscoint.enums;

public enum Quote {

    BRL, BTC;

    public static Quote get(Boolean quotedOnBrl) {
        return quotedOnBrl ? Quote.BRL : Quote.BTC;
    }

}
