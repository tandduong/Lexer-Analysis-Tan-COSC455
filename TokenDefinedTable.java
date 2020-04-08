package com.mycompany.cosc455project1;

//tandduong
import java.util.*;

//the type of tokens defined below:
enum TokenTypes {
    NUMBER, DEFAULT_KW, ID, LESS_THAN, GREATER_THAN, EQUAL, LESS_EQUAL, GREAT_EUQAL, OPEN_PARA, CLOSE_PARA, ASSIGN, PLUS, MINUS, MULT, DIV, DELIMINATES, COMMA, COLON, PERIOD, SEMI_COLON, NUM, FINISH, TRUE, FALSE,NOT_EQUAL
}

public class TokenDefinedTable {

    Hashtable<String, Token> h;

    public Token get(String str) {
        return h.get(str);
    }

    public boolean Kw(String lexeme) {
        return h.containsKey(lexeme);
    }

    public void put(String lexeme, Token sym) {
        h.put(lexeme, sym);
    }

    public TokenDefinedTable() {
        h = new Hashtable<>();

        //set the position and the line number 0 as initial
        Token s = new Token(TokenTypes.DEFAULT_KW, 0, 0, "program");
        h.put("program", s);
        s = new Token(TokenTypes.DEFAULT_KW, 0, 0, "var");
        h.put("var", s);
        s = new Token(TokenTypes.DEFAULT_KW, 0, 0, "begin");
        h.put("begin", s);
        s = new Token(TokenTypes.DEFAULT_KW, 0, 0, "end");
        h.put("end", s);
        s = new Token(TokenTypes.DEFAULT_KW, 0, 0, "if");
        h.put("if", s);
        s = new Token(TokenTypes.DEFAULT_KW, 0, 0, "then");
        h.put("then", s);
        s = new Token(TokenTypes.DEFAULT_KW, 0, 0, "else");
        h.put("else", s);
        s = new Token(TokenTypes.DEFAULT_KW, 0, 0, "while");
        h.put("while", s);
        s = new Token(TokenTypes.DEFAULT_KW, 0, 0, "do");
        h.put("do", s);
        s = new Token(TokenTypes.DEFAULT_KW, 0, 0, "integer");
        h.put("integer", s);
        s = new Token(TokenTypes.DEFAULT_KW, 0, 0, "read");
        h.put("read", s);
        s = new Token(TokenTypes.DEFAULT_KW, 0, 0, "write");
        h.put("write", s);
        s = new Token(TokenTypes.DEFAULT_KW, 0, 0, "or");
        h.put("or", s);
        s = new Token(TokenTypes.DEFAULT_KW, 0, 0, "and");
        h.put("and", s);
        s = new Token(TokenTypes.DEFAULT_KW, 0, 0, "not");
        h.put("not", s);
        s = new Token(TokenTypes.FALSE, 0, 0, "false");
        h.put("false", s);
        s = new Token(TokenTypes.TRUE, 0, 0, "true");
        h.put("true", s);

        s = new Token(TokenTypes.ASSIGN, 0, 0, ":=");
        h.put(":=", s);
        s = new Token(TokenTypes.SEMI_COLON, 0, 0, ";");
        h.put(";", s);
        s = new Token(TokenTypes.PERIOD, 0, 0, ".");
        h.put(".", s);
        s = new Token(TokenTypes.COMMA, 0, 0, ",");
        h.put(",", s);
        s = new Token(TokenTypes.OPEN_PARA, 0, 0, "(");
        h.put("(", s);
        s = new Token(TokenTypes.CLOSE_PARA, 0, 0, ")");
        h.put(")", s);
        s = new Token(TokenTypes.COLON, 0, 0, ":");
        h.put(":", s);

        s = new Token(TokenTypes.LESS_THAN, 0, 0, "<");
        h.put("<", s);
        s = new Token(TokenTypes.GREATER_THAN, 0, 0, ">");
        h.put(">", s);
        s = new Token(TokenTypes.LESS_EQUAL, 0, 0, "<=");
        h.put("<=", s);
        s = new Token(TokenTypes.GREAT_EUQAL, 0, 0, ">=");
        h.put(">=", s);
        s = new Token(TokenTypes.EQUAL, 0, 0, "=");
        h.put("=", s);
        s = new Token(TokenTypes.NOT_EQUAL, 0, 0, "<>");
        h.put("<>", s);

        s = new Token(TokenTypes.PLUS, 0, 0, "+");
        h.put("+", s);
        s = new Token(TokenTypes.MINUS, 0, 0, "-");
        h.put("-", s);
        s = new Token(TokenTypes.MULT, 0, 0, "*");
        h.put("*", s);
        s = new Token(TokenTypes.DIV, 0, 0, "/");
        h.put("/", s);
    }
}

