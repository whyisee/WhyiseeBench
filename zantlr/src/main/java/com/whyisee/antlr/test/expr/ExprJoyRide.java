package com.whyisee.antlr.test.expr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExprJoyRide {

    public static void main(String[] args) throws IOException {
        String input = null;
        InputStream is = System.in;
        if (args.length>0){
            input=args[0];
        }


        if ( input!=null ){
            is = new FileInputStream(input);

        }

        ANTLRInputStream inputStream = new ANTLRInputStream(is);

        ExprLexer lexer = new ExprLexer(inputStream);

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        ExprParser parser = new ExprParser(tokenStream);

        ParseTree tree = parser.prog();

        System.out.println(tree.toStringTree(parser));
    }

}
