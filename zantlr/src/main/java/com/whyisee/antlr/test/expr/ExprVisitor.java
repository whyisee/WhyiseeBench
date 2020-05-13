// Generated from D:/git/zantlr/src/main/java/com/zoukh/antlr/test\Expr.g4 by ANTLR 4.8
package com.whyisee.antlr.test.expr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(ExprParser.ProgContext ctx);

	T visitStat(ExprParser.StatContext ctx);

	T visitExpr(ExprParser.ExprContext ctx);
}