package com.mycompany.cosc455project1;

//tandduong
import java.io.*;
import java.util.*;

public class LexicalAnalysis
{
   int lexemebegin;
   int state;
   Token tokenValue;
   TokenTypes currentTypes;
   String str;
   int lineNumber;
   int position= -1; //set initial posiition. 
   String lexeme;
   TokenDefinedTable tdf;
   Scanner scan;
  
   public LexicalAnalysis(TokenDefinedTable hash)
   {
       tdf = hash;
   }
  
   //case and state based on the finite automata sketched in my note
   
   public TokenTypes nextToken()
   {
       char c;
       state = 0;
       c = nextChar();
      
       while (true)//there is a token
       {
           switch (state)
           {
               case 0:   
                   switch (c) {
                   case '<':
                       state=1;
                       break;
                   case '=':
                       tokenValue = tdf.get("=");
                       return currentTypes = TokenTypes.EQUAL; //state5
                   case '>':
                       state=6;
                       break;
                   default:
                       Get_Failed();
                       break;
               }
                       break;

               case 1:  
               {  
               c = nextChar();
               switch (c) {
                   case '=':
                       tokenValue = tdf.get("<=");
                       return currentTypes = TokenTypes.LESS_EQUAL; //state2
                   case '>':
                       tokenValue = tdf.get("<>");
                       return currentTypes = TokenTypes.NOT_EQUAL; //state3
                   default:
                       retract();
                       tokenValue= tdf.get("<");
                       return currentTypes = TokenTypes.LESS_THAN; //state4
               }
               }  
                      
               case 6:  
               {
                   c = nextChar( );  
                   if (c=='=')
                       {
                           tokenValue= tdf.get(">=");
                           return currentTypes = TokenTypes.GREAT_EUQAL; //state7
                       }
                       else
                       {
                           retract();
                           tokenValue = tdf.get(">");
                           return currentTypes = TokenTypes.GREATER_THAN; //state8
                       }
               }
               case 9:   
                   if ((c>='A' && c<='Z')||(c>='a' && c<='z'))
                       {
                           lexeme = "" +c;
                           state = 10;
                       }
                       else
                           Get_Failed();
               break;
               case 10:
               {
                   c = nextChar();
                   while ((c>='A' && c<='Z')||(c>='a' && c<='z')||(c>='0' && c<='9'))
                   {
                       lexeme = lexeme + c;
                       c = nextChar(); 
                   }
                   //retract();
                   if (tdf.Kw(lexeme))
                       tokenValue = tdf.get(lexeme); 
                   else
                   {
                       tokenValue = new Token(TokenTypes.ID,lineNumber,position,lexeme);
                       tdf.put(lexeme, tokenValue);
                   }
                   return currentTypes= TokenTypes.ID; //state11
               }
               case 12:
               switch (c) {
                   case ':':
                       state=13;
                       break;
                   case ';':
                       tokenValue = tdf.get(";");
                       return currentTypes = TokenTypes.SEMI_COLON;//state14
                   case '.':
                       tokenValue = tdf.get(".");
                       return currentTypes = TokenTypes.PERIOD;//15
                   case ',':
                       tokenValue = tdf.get(",");
                       return currentTypes = TokenTypes.COMMA;//16
                   case '(':
                       tokenValue = tdf.get("(");
                       return currentTypes = TokenTypes.OPEN_PARA;//17
                   case ')':
                       tokenValue = tdf.get(")");
                       return currentTypes = TokenTypes.CLOSE_PARA;//18
                   default:
                       Get_Failed();
                       break;
               }
                   break;

               case 13:
               {
                   c = nextChar();
                   if (c=='=')
                   {
                       tokenValue = tdf.get(":"+c);
                       return currentTypes = TokenTypes.ASSIGN;
                   }
                   else
                   {
                       retract();
                       tokenValue= tdf.get(":");
                       return currentTypes = TokenTypes.COLON;
                   }
               }
               case 21:
               switch (c) {
                   case '+':
                       tokenValue = tdf.get("+");
                       return currentTypes = TokenTypes.PLUS; //state22
                   case '-':
                       tokenValue = tdf.get("-");
                       return currentTypes = TokenTypes.MINUS;//state23
                   case '*':
                       tokenValue = tdf.get("*");
                       return currentTypes = TokenTypes.MULT;//state24
                   case '/':
                       tokenValue = tdf.get("/");
                       return currentTypes = TokenTypes.DIV;//state25
                   default:
                       Get_Failed();
                       break;
               }
                   break;

               case 26:
                   if (c>='0' && c<='9')
                   {
                       lexeme = "" + c;
                       state = 27;
                   }
                   else
                       Get_Failed();
                   break;
               case 27:
               {
                   c = nextChar();
                   while (c>='0' && c<='9')
                   {
                       lexeme = lexeme + c;
                       c = nextChar();
                   }
                   retract();
                   if (tdf.Kw(lexeme))
                       tokenValue = tdf.get(lexeme);
                   else
                   {
                       tokenValue=new Token(TokenTypes.NUM,lineNumber,position,lexeme);
                       tdf.put(lexeme, tokenValue);
                      
                   }
                   return currentTypes = TokenTypes.NUM;//state28
               }
                  
               case 29:
                   if(c==' ')
                       state = 30;
                   else   
                       return currentTypes = TokenTypes.DELIMINATES;
                   break;
               case 30: {
                    c = nextChar();
                    while (c == ' ') {
                    //
                    if (currentTypes == TokenTypes.FINISH) {
                    return currentTypes;
                    }
                    //
                    c = nextChar();
                    }
                    state = 0;
                    break;
                    }
  
           }
       }
   }          
  
   public Token Get_Val()
   {
       return this.tokenValue;
   }
  
   //next character --> position increment
   public char nextChar()
   {
       position++;
           if (position >= str.length())//end on line
               if(scan.hasNext())
               {
                   str = scan.nextLine();
                   lineNumber++;
                   position = 0;
                   return str.charAt(position);
               }
               else // end of file
               {
                   currentTypes = TokenTypes.FINISH;
                   return (' ');
               }
           else //middle of line
               return str.charAt(position);
           }
  
   public void retract()
   {
       position --;
   }
  
   
   //In case the finite automata gets fail (default case)
   public void Get_Failed()
   {
       switch (state)
       {
       case 0: state=9; break;
       case 9: state=12; break;
       case 12: state=21; break;
       case 21: state=26; break;
       case 26: state=29; break;
       }
   }
  
   public void PrintToken()
   {
       System.out.println("value :"+ tokenValue.value.toString());
       System.out.println("position of token :"+ tokenValue.position);
       System.out.println("kind of lexeme :" + currentTypes.toString());
   }
  
   public void inputFileName(String fname)
   {
       try
       {
           scan=new Scanner(new File(fname));
           str=scan.nextLine();
       }
       catch (FileNotFoundException e)
       {
           System.out.println ("File not found!!!");
       }      
   }
}

