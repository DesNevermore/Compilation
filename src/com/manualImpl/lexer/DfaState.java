package com.manualImpl.lexer;

/**
 * Different states of deterministic finite automation
 */
public enum DfaState {
    Initial,

    If, Id_int1, Id_int2, Id_int3, Id, GT, GE,

    Assignment,

    Plus, Minus, Star, Slash,

    SemiColon,
    LeftParen,
    RightParen,

    IntLiteral,
}
