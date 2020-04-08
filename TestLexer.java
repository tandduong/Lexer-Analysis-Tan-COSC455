package com.mycompany.cosc455project1;

//tandduong

import java.util.*;


//this class work as the test program
//user type the input file link
//the output shown
public class TestLexer
{
   public static void main(String args[])
   {
       try
       {
           Scanner input = new Scanner(System.in);
           String fname;
      
           TokenDefinedTable tdf = new TokenDefinedTable();
           LexicalAnalysis la = new LexicalAnalysis(tdf);
      
           //Search the input file
           System.out.println("Type the file name: ");
           fname = input.nextLine();
           la.inputFileName(fname);
          
           TokenTypes currentToken=la.nextToken();
           while(currentToken != TokenTypes.FINISH)
           {
               //print the current token, then move to the next one
               la.PrintToken();
               System.out.println();
               currentToken=la.nextToken();
           }
       }
       catch (Exception e)
       {
           System.out.println(e.getMessage());
       }
   }
}

//sample output
//Type the file name: 
//C:\Users\tduong2\Desktop\project1-testcases\testArithmatic.txt
//position :0
//kind of lexeme:NUM
//value :7

//position:0
//kind of lexeme :PLUS
//value :+

//position :0
//kind of lexeme :NUM
//value :98

//position is :0
//kind of lexeme :MINUS
//value :-

//position :0
//kind of lexeme:NUM
//value :25

//position :0
//kind of lexeme :MULT
//value :*

//position  :1
//kind of lexeme :NUM
//value :710

//position  :0
//kind of lexeme  :DIV
//value :/

//position  :3
//kind of lexeme  :NUM
//value :2
