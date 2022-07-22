package com.manualImpl.lexer;

import com.manualImpl.Token;
import com.manualImpl.TokenReader;

import java.util.List;

/**
 * A simple realization of TokenReader
 */
public class SimpleTokenReader implements TokenReader {
    List<Token> tokens = null;
    int index = 0;

    public SimpleTokenReader(List<Token> tokens) {
        this.tokens = tokens;
    }

    @Override
    public Token read() {
        if (index < tokens.size()) {
            return tokens.get(index++);
        }
        return null;
    }

    @Override
    public Token peek() {
        if (index < tokens.size()) {
            return tokens.get(index);
        }
        return null;
    }

    @Override
    public void unread() {
        if (index > 0) {
            index--;
        }
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        if (index >= 0 && index < tokens.size()) {
            this.index = index;
        }
    }
}
