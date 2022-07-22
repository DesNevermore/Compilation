package com.manualImpl.lexer;

import com.manualImpl.Token;
import com.manualImpl.TokenType;

import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleLexer {
    private StringBuffer tokenTextBuffer = null;    // transient storage of token text
    private List<Token> tokens = null;
    private SimpleToken token = null;               // token being parsed

    private boolean isAlpha(int ch) {
        return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
    }

    private boolean isDigit(int ch) {
        return ch >= '0' && ch <= '9';
    }

    private boolean isBlank(int ch) {
        return ch == ' ' || ch == '\t' || ch == '\n';
    }

    private DfaState initToken(char ch) {
        if (tokenTextBuffer.length() > 0) {
            token.setText(tokenTextBuffer.toString());
            tokens.add(token);
            tokenTextBuffer = new StringBuffer();
            token = new SimpleToken();
        }
        DfaState newState = DfaState.Initial;
        if (isAlpha(ch)) {
            if (ch == 'i') {
                newState = DfaState.Id_int1;
            } else {
                newState = DfaState.Id;
            }
            token.setType(TokenType.Identifier);
            tokenTextBuffer.append(ch);
        } else if (isDigit(ch)) {
            newState = DfaState.IntLiteral;
            token.setType(TokenType.IntLiteral);
            tokenTextBuffer.append(ch);
        } else if (ch == '>') {
            newState = DfaState.GT;
            token.setType(TokenType.GT);
            tokenTextBuffer.append(ch);
        } else if (ch == '+') {
            newState = DfaState.Plus;
            token.setType(TokenType.Plus);
            tokenTextBuffer.append(ch);
        } else if (ch == '-') {
            newState = DfaState.Minus;
            token.setType(TokenType.Minus);
            tokenTextBuffer.append(ch);
        } else if (ch == '*') {
            newState = DfaState.Star;
            token.setType(TokenType.Star);
            tokenTextBuffer.append(ch);
        } else if (ch == '/') {
            newState = DfaState.Slash;
            token.setType(TokenType.Slash);
            tokenTextBuffer.append(ch);
        } else if (ch == ';') {
            newState = DfaState.SemiColon;
            token.setType(TokenType.SemiColon);
            tokenTextBuffer.append(ch);
        } else if (ch == '(') {
            newState = DfaState.LeftParen;
            token.setType(TokenType.LeftParen);
            tokenTextBuffer.append(ch);
        } else if (ch == ')') {
            newState = DfaState.RightParen;
            token.setType(TokenType.RightParen);
            tokenTextBuffer.append(ch);
        } else if (ch == '=') {
            newState = DfaState.Assignment;
            token.setType(TokenType.Assignment);
            tokenTextBuffer.append(ch);
        } else {
            newState = DfaState.Initial;
        }
        return newState;
    }

    private SimpleTokenReader tokenize(String stmt) {
        tokens = new ArrayList<>();
        CharArrayReader arrayReader = new CharArrayReader(stmt.toCharArray());
        tokenTextBuffer = new StringBuffer();
        token = new SimpleToken();
        int ch_index = 0;
        char ch = ' ';
        DfaState state = DfaState.Initial;
        try {
            while ((ch_index = arrayReader.read()) != -1) {
                ch = (char) ch_index;
                switch (state) {
                    case Initial:
                        state = initToken(ch);
                        break;
                    case Id:
                        if (isAlpha(ch) || isDigit(ch)) {
                            tokenTextBuffer.append(ch);
                        } else {
                            state = initToken(ch);
                        }
                        break;
                    case GT:
                        if (ch == '=') {
                            token.setType(TokenType.GE);
                            state = DfaState.GE;
                            tokenTextBuffer.append(ch);
                        } else {
                            state = initToken(ch);
                        }
                        break;
                    case GE:
                    case Assignment:
                    case Plus:
                    case Minus:
                    case Slash:
                    case Star:
                    case SemiColon:
                    case LeftParen:
                    case RightParen:
                        state = initToken(ch);
                        break;
                    case IntLiteral:
                        if (isDigit(ch)) {
                            tokenTextBuffer.append(ch);
                        } else {
                            state = initToken(ch);
                        }
                        break;
                    case Id_int1:
                        if (ch == 'n') {
                            state = DfaState.Id_int2;
                            tokenTextBuffer.append(ch);
                        } else if (isDigit(ch) || isAlpha(ch)) {
                            state = DfaState.Id;
                            tokenTextBuffer.append(ch);
                        } else {
                            state = initToken(ch);
                        }
                        break;
                    case Id_int2:
                        if (ch == 't') {
                            state = DfaState.Id_int3;
                            tokenTextBuffer.append(ch);
                        } else if (isDigit(ch) || isAlpha(ch)) {
                            state = DfaState.Id_int3;
                            tokenTextBuffer.append(ch);
                        } else {
                            state = initToken(ch);
                        }
                        break;
                    case Id_int3:
                        if (isBlank(ch)) {
                            token.setType(TokenType.Int);
                            state = initToken(ch);
                        } else {
                            state = DfaState.Id;
                            tokenTextBuffer.append(ch);
                        }
                        break;
                    default:
                }
                if (tokenTextBuffer.length() > 0) {
                    initToken(ch);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SimpleTokenReader(tokens);
    }

    /**
     * print all token
     * @param tokenReader
     */
    public static void dump(SimpleTokenReader tokenReader) {
        System.out.println("text\ttype");
        Token token = null;
        while ((token = tokenReader.read()) != null) {
            System.out.println(token.getText() + "\t\t" + token.getType());
        }
    }
}
