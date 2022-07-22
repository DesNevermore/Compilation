package com.manualImpl.lexer;

import com.manualImpl.Token;
import com.manualImpl.TokenType;

public class SimpleToken implements Token {
    private TokenType type = null;
    private String text = null;

    @Override
    public TokenType getType() {
        return type;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public void setText(String text) {
        this.text = text;
    }
}
