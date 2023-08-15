package com.compiladores;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class App {
    public static void main(String args[]) throws IOException {
        CharStream cs = CharStreams.fromFileName(args[0]);
        LogaLexer lexer = new LogaLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LogaParser parser = new LogaParser(tokens);
        LogaParser.EstruturaContext arvore = parser.estrutura();
        Token t = null;

        while ((t = lexer.nextToken()).getType() != Token.EOF) {
            System.out.println("<" + LogaLexer.VOCABULARY.getDisplayName(t.getType()) + "," + t.getText() + ">");
        }

        System.out.printf("\nsimpleGates: %d", arvore.countGate.size());
        System.out.printf("\ncomplexGates: %d\n", arvore.countCircuits.size());
        System.out.printf("\n END-GATE\n a=%s\tb=%s\tout=out", arvore.endGate.var_a().getText(),
                arvore.endGate.var_b().getText());
        System.out.println();
        Semantico s = new Semantico();
        s.visitEstrutura(arvore);

        GeradorHTML g = new GeradorHTML();
        g.visitGate(arvore);

        // parser.inicial();
    }
}
