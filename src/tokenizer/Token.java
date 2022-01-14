package tokenizer;

public class Token {
  private int initialIndex;
  private int finalIndex;
  private TokenType tokenType;
  private String tokenString;

  public Token(int initialIndex, int finalIndex, TokenType tokenType, String tokenString) {
    this.initialIndex = initialIndex;
    this.finalIndex = finalIndex;
    this.tokenType = tokenType;
    this.tokenString = tokenString;
  }

  public int getIntialIndex() {
    return initialIndex;
  }

  public int getFinalIndex() {
    return finalIndex;
  }

  public TokenType getTokenType() {
    return tokenType;
  }

  public String getTokenString() {
    return tokenString;
  }

  @Override
  public String toString() {
    if(!this.getTokenType().isAuxiliary()) {
      return tokenType + " '" + tokenString + "'";
    } else {
      return tokenType + " ";
    }
  }
}
