package com.compiladores;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeradorHTML extends LogaBaseVisitor<Void> {
  public Void visitGate(LogaParser.EstruturaContext ctx) {
    int espaco_gate = 50;
    try {
      StringBuilder saida = new StringBuilder();

      saida.append("<!DOCTYPE html>" +
          "<html>" +
          "<script src=\"C:\\Users\\rosam\\Desktop\\Dave\\compilador_portas-logicas\\demo\\src\\main\\java\\com\\compiladores\\anseki-leader-line-6c26a9d\\leader-line.min.js\"></script>"
          +
          "<head>" +
          "<style>" +
          "body {" +
          "display: flex;" +
          "justify-content: center;" +
          "align-items: center;" +
          "height: 100vh;" +
          "background-color: #fffdfd;" +
          "}" +
          "" +
          ".rectangle {" +
          "width: 1200px;" +
          "height: 600px;" +
          "background-color: #ffffff;" +
          "border: 2px solid #333;" +
          "position: absolute;" +
          "display: flex;" +
          "flex-direction: column;" +
          "align-items: center;" +
          "justify-content: flex-start;" +
          "padding: 20px;" +
          "}" +
          "" +
          ".box {" +
          "width: 50px;" +
          "height: 50px;" +
          "position: relative;" +
          "top: 220px;" +
          "left: -280px;" +
          "background-color: blueviolet;" +
          "display: flex;" +
          "flex-direction: column;" +
          "justify-content: flex-start;" +
          "padding: 20px;" +
          "}" +
          "" +
          ".and-gate {" +
          "position: relative;" +
          "width: 50px;" +
          "height: 50px;" +
          "border: 2px solid black;" +
          "transform: rotate(90deg);" +
          "border-radius: 50px 50px 0 0;" +
          "}" +
          "" +
          ".circle {" +
          "position: relative;" +
          "height: 10px;" +
          "width: 10px;" +
          "bottom: 10px;" +
          "left: 20px;" +
          "bottom: 12px;" +
          "background-color: #ffffff;" +
          "border: 2px solid #040404;" +
          "border-radius: 50%;" +
          "}" +

          ".out {" +
          "content: 'out';" +
          "position: absolute;" +
          "top: -130%;" +
          "right: -50%;" +
          "width: 20px;" +
          "height: 2px;" +
          "transform: rotate(-90deg);" +
          "background: black;" +
          "}" +

          "" +
          ".entradaA {" +
          "position: absolute;" +
          "top: 130%;" +
          "right: 50%;" +
          "width: 30px;" +
          "height: 2px;" +
          "transform: rotate(90deg);" +
          "background: black;" +
          "}" +
          ".entradaB {" +
          "position: absolute;" +
          "top: 130%;" +
          "right: -11%;" +
          "width: 30px;" +
          "height: 2px;" +
          "transform: rotate(90deg);" +
          "background: black;" +
          "}" +

          ".subir {" +
          "position: absolute;" +
          "width: 20px;" +
          "height: 2px;" +
          "transform: rotate(-90deg);" +
          "background: black;" +
          "}" +
          "" +
          ".descer {" +
          "position: absolute;" +
          "width: 20px;" +
          "height: 2px;" +
          "transform: rotate(-90deg);" +
          "background: black;" +
          "}" +
          "" +
          ".reto{" +
          "position: absolute;" +
          "width: 20px;" +
          "height: 2px;" +
          "transform: rotate(90deg);" +
          "background: black;" +
          "}" +

          "</style>" +
          "</head>" +
          "" +
          "<body>" +
          "<div class=\"rectangle\">");

          ArrayList<String> posicoesA = new ArrayList<>();

      for (int i = 0; i < ctx.countGate.size(); i++) {
        //System.out.printf("\ncountgate: %d \ti: %d\tcountgate-1: %d", ctx.countGate.size(),i, ctx.countGate.size() - 1);
        saida.append("<div class=\"and-gate\" style=\"left:" + espaco_gate + "px; top: 300px; position: absolute;\">" +
            "<div class=\"circle\"><div class=\"out\"id=\"" + ctx.countGate.get(i).var_out().getText()
            + "\">");

        saida.append("<p style=\"rotate: 0deg; position: absolute; left: -15px; top: -8px;\">"
            + ctx.countGate.get(i).var_out().getText() + "</p></div></div>" +
            "<div id=\"" + ctx.countGate.get(i).var_a().getText() + i +
            "\"class=\"entradaA\"><p style=\"rotate: 180deg; position: absolute; left: 6px; top: -8px;\">"
            + ctx.countGate.get(i).var_a().getText() + "</p></div>" +
            "<div id=\"" + ctx.countGate.get(i).var_b().getText() + i 
            + "\"class=\"entradaB\"><p style=\"rotate: 180deg; position: absolute; left: 6px; top: -38px;\">"
            + ctx.countGate.get(i).var_b().getText() + "</p></div>" +
            "</div>");

        espaco_gate += 150;
        if (i == ctx.countGate.size() - 1) {
          saida.append("<div class=\"and-gate\" style=\"left:" + espaco_gate + "px; top: 300px; position: absolute;\">"
              +
              "<div class=\"circle\"><div class=\"out\"><p style=\"rotate: 0deg; position: absolute; left: -15px; top: -8px;\">out</p></div></div>"
              +
              "<div id=\"" + ctx.endGate.var_a().getText() +i + 
              "\"class=\"entradaA\"><p style=\"rotate: 180deg; position: absolute; left: 6px; top: -8px;\">"
              + ctx.endGate.var_a().getText() + "</p></div>" +

              "<div id=\"" + ctx.endGate.var_b().getText() + i+
              "\"class=\"entradaB\"><p style=\"rotate: 180deg; position: absolute; left: 6px; top: -38px;\">"
              + ctx.endGate.var_b().getText() + "</p></div>"
              +
              "</div>");
        }

        for (int j = 1; j < ctx.countGate.size(); j++) {
          System.out.println(j);
          if(ctx.countGate.get(i).var_out().getText().equals(ctx.countGate.get(j).var_a().getText())){
            posicoesA.add(ctx.countGate.get(j).var_a().getText());
            System.out.printf("\nnome porta: %s",ctx.countGate.get(j).var_a().getText()+1);
          }

          /*if (ctx.countGate.get(i).var_out().getText().equals(ctx.countGate.get(j).var_a().getText())) {
            System.out.printf("\nant: %s\tprox: %s\n",ctx.countGate.get(i).var_out().getText(),
            ctx.countGate.get(j).var_a().getText());
            saida.append("<script> window.addEventListener('load', function () {"+
            "'use strict';"+
            "var point1 = LeaderLine.pointAnchor(document.getElementById('"+ctx.countGate.get(i).var_out().getText()+"'), { x: 17, y: 2 });"+
            "new LeaderLine(point1, document.getElementById('"+ctx.countGate.get(j).var_a().getText()+"i'), {endPlug:'disc',startPlug:'disc', size: 2, path: 'straight', endSocket:'auto', color:'#bbb'});"+
            "});</script>"
            );}

            if (ctx.countGate.get(i).var_out().getText().equals(ctx.countGate.get(j).var_b().getText())) {
            System.out.printf("\nant: %s\tprox: %s\n",ctx.countGate.get(i).var_out().getText(),
            ctx.countGate.get(j).var_a().getText());
            saida.append("<script> window.addEventListener('load', function () {"+
            "'use strict';"+
            "var point1 = LeaderLine.pointAnchor(document.getElementById('"+ctx.countGate.get(i).var_out().getText()+"'), { x: 17, y: 2 });"+
            "new LeaderLine(point1, document.getElementById('"+ctx.countGate.get(j).var_b().getText()+"i'), {endPlug:'disc',startPlug:'disc', size: 2, path: 'straight', endSocket:'auto', color:'#bbb'});"+
            "});</script>"
            );
          }*/
        }

        //System.out.printf("\n chave_Gate: %s", chave_gate);
        //System.out.printf("\n espaco_Gate: %s", espaco_gate);
      }

      saida.append("</div> </body></html>");
      System.out.println("Saida gerada");

      FileWriter writer = new FileWriter("demo\\src\\main\\java\\com\\compiladores\\PORTA-LOGICA.html");
      writer.write(saida.toString());
      writer.close();
      return super.visitEstrutura(ctx);
    } catch (IOException ex) {
      Logger.getLogger(GeradorHTML.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println();
    return super.visitEstrutura(ctx);
  }

}