package com.manualImpl;

/**
 * Type of Token
 */
public enum TokenType {
    Plus,   // +
    Minus,  // -
    Star,   // *
    Slash,  // /

    GE,     // >=
    GT,     // >
    EQ,     // ==
    LE,     // <=
    LT,     // <

    Semicolon,  // ;
    LeftParen,  // (
    RightParen, // )

    Assignment, // =

    If,
    Else,

    Int,

    Identifier,     // 标识符

    IntLiteral,     // 整型字面量
    StringLiteral,  // 字符串字面量
}
