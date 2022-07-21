package com.manualImpl;

/**
 * type of ASTNode
 */
public enum ASTNodeType {
    Program,        // root node, entrance of program

    ExpressionStmt, // 表达式语句（表达式后跟个分号）
    IntDeclaration, // 整型变量语句
    AssignmentStmt, // 赋值语句

    Primary,        // 基础表达式
    Multiplicative, // 乘法表达式
    additive,       // 加法表达式

    Identifier,     // 标识符
    IntLiteral,     // 整型字面量
}
