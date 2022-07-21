package com.manualImpl;

import java.util.List;

/**
 * ASTNode
 * including type of AST, text value, child node and parent node
 */
public interface ASTNode {
    ASTNode getParent();

    List<ASTNode> getChild();

    ASTNodeType getType();

    String getText();
}
