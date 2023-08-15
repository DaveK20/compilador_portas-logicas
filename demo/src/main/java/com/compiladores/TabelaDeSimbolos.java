package com.compiladores;

import java.util.HashMap;
import java.util.Map;

public class TabelaDeSimbolos {
    class EntradaTabelaDeSimbolos {
        String nome;
        
        private EntradaTabelaDeSimbolos(String nome) {
            this.nome = nome;
        }
    }
    
    private final Map<String, EntradaTabelaDeSimbolos> tabela;
    
    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }
    
    public void adicionar(String nome) {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome));
    }
    
    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }
}