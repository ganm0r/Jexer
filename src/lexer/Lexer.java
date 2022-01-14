package lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tokenizer.Token;
import tokenizer.TokenType;

public class Lexer {
  private Map<TokenType, String> tokenRegexMap;
  private List<Token> resultingTokenList;

  private void mapTokenToRegEx() {
    tokenRegexMap.put(TokenType.OpeningCurlyBrace, "(\\{).*");
		tokenRegexMap.put(TokenType.ClosingCurlyBrace, "(\\}).*");
		tokenRegexMap.put(TokenType.DoubleConstant, "\\b(\\d{1,9}\\.\\d{1,32})\\b.*");
		tokenRegexMap.put(TokenType.IntConstant, "\\b(\\d{1,9})\\b.*");
		tokenRegexMap.put(TokenType.Void, "\\b(void)\\b.*");
		tokenRegexMap.put(TokenType.Int, "\\b(int)\\b.*");
		tokenRegexMap.put(TokenType.Double, "\\b(int|double)\\b.*");
		tokenRegexMap.put(TokenType.Tab, "(\\t).*");
		tokenRegexMap.put(TokenType.NewLine, "(\\n).*");
		tokenRegexMap.put(TokenType.Public, "\\b(public)\\b.*");
		tokenRegexMap.put(TokenType.Private, "\\b(private)\\b.*");
		tokenRegexMap.put(TokenType.False, "\\b(false)\\b.*");
		tokenRegexMap.put(TokenType.True, "\\b(true)\\b.*");
		tokenRegexMap.put(TokenType.Null, "\\b(null)\\b.*");
		tokenRegexMap.put(TokenType.Return, "\\b(return)\\b.*");
		tokenRegexMap.put(TokenType.New, "\\b(new)\\b.*");
		tokenRegexMap.put(TokenType.Class, "\\b(class)\\b.*");
		tokenRegexMap.put(TokenType.If, "\\b(if)\\b.*");
		tokenRegexMap.put(TokenType.Else, "\\b(else)\\b.*");
		tokenRegexMap.put(TokenType.While, "\\b(while)\\b.*");
		tokenRegexMap.put(TokenType.Static, "\\b(static)\\b.*");
		tokenRegexMap.put(TokenType.Point, "(\\.).*");
		tokenRegexMap.put(TokenType.Plus, "(\\+{1}).*");
		tokenRegexMap.put(TokenType.Minus, "(\\-{1}).*");
		tokenRegexMap.put(TokenType.Multiply, "(\\*).*");
		tokenRegexMap.put(TokenType.Divide, "(/).*");
		tokenRegexMap.put(TokenType.EqualEqual, "(==).*");
		tokenRegexMap.put(TokenType.Equal, "(=).*");
		tokenRegexMap.put(TokenType.ExclameEqual, "(\\!=).*");
		tokenRegexMap.put(TokenType.Greater, "(>).*");
		tokenRegexMap.put(TokenType.Less, "(<).*");
		tokenRegexMap.put(TokenType.Identifier, "\\b([a-zA-Z]{1}[0-9a-zA-Z_]{0,31})\\b.*");

    tokenRegexMap.put(TokenType.BlockComment, "(/\\*.*?\\*/).*");
		tokenRegexMap.put(TokenType.LineComment, "(//(.*?)[\r$]?\n).*");
		tokenRegexMap.put(TokenType.WhiteSpace, "( ).*");
		tokenRegexMap.put(TokenType.OpenBrace, "(\\().*");
		tokenRegexMap.put(TokenType.CloseBrace, "(\\)).*");
		tokenRegexMap.put(TokenType.Semicolon, "(;).*");
		tokenRegexMap.put(TokenType.Comma, "(,).*");
  }

	private Token separateToken(String source, int initialIndex) {
		for(TokenType tokenType : TokenType.values()) {
			Pattern p = Pattern.compile(".{" + initialIndex + "}" + tokenRegexMap.get(tokenType), Pattern.DOTALL);
			Matcher m = p.matcher(source);
			if(m.matches()) {
				String lexema = m.group(1);
				return new Token(initialIndex, initialIndex + lexema.length(), tokenType, lexema);
			}
		}
		return null;
	}

  public Lexer() {
    this.tokenRegexMap = new TreeMap<TokenType, String>();
    this.mapTokenToRegEx();
    this.resultingTokenList = new ArrayList<Token>();
  }

	public List<Token> getTokens() {
		return resultingTokenList;
	}

	public List<Token> getFilteredTokens() {
		List<Token> filteredResultingTokenList = new ArrayList<Token>();
		for(Token token : this.resultingTokenList) {
			if(!token.getTokenType().isAuxiliary()) {
				filteredResultingTokenList.add(token);
			}
		}
		return filteredResultingTokenList;
	}

  public void tokenize(String sourceCode) {
    int index = 0;
    Token token = null;

    do {
      token = separateToken(sourceCode, index);
			if(token != null) {
				index = token.getFinalIndex();
				resultingTokenList.add(token);
			}
    } while (token != null && index != sourceCode.length());
  }
}
