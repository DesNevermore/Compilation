package com.manualImpl;

/**
 * A simple Token which contains text value and type
 */
public interface Token {
    TokenType getType();

    String getText();
}
