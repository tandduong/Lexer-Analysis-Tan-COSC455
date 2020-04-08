package com.mycompany.cosc455project1;

//tandduong
class Token
{
   TokenTypes sym;   //token type
   int lineNumber;
   int position;
   Object value;   //semantic value of token
   Token(TokenTypes symb, int ln, int pos, Object val)
   {
       sym = symb;
       lineNumber = ln;
       position = pos;
       value = val;
   }
}