package com.compiladores;

public class Semantico extends LogaBaseVisitor<Void> {
        public TabelaDeSimbolos tabela;

        public void criarPorta(LogaParser.EstruturaContext ctx){
        System.out.printf("\nsimpleGates: %i", ctx.countGate.size());
        System.out.printf("\ncomplexGates: %i", ctx.countCircuits.size());
        }
}
