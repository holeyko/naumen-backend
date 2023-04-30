package com.holeyko.naumentask.exception;

import java.util.List;

public class PeopleParseException extends RuntimeException {
    private final List<String> incorrectPeople;

    public PeopleParseException(List<String> incorrectPeople) {
        this.incorrectPeople = incorrectPeople;
    }

    public List<String> getIncorrectPeople() {
        return incorrectPeople;
    }
}
