package tokenizer;

public enum TokenType {
  CloseBrace,

	OpenBrace,

	OpeningCurlyBrace,

	ClosingCurlyBrace,

	DoubleConstant,

	IntConstant,

	Plus,

	Minus,

	Multiply,

	Divide,

	Point,

	EqualEqual,

	Equal,

	ExclameEqual,

	Greater,

	Less,

	Static,

	Public,

	Private,

	Int,

	Double,

	Void,

	False,

	True,

	Null,

	Return,

	New,

	Class,

	If,

	While,

	Else,

	Semicolon,

	Comma,

	Identifier,

  // auxiliary tokens

  BlockComment,

  LineComment,

  NewLine,

  Tab,

  WhiteSpace;

  public boolean isAuxiliary() {
    return this == BlockComment || 
           this == LineComment || 
           this == NewLine || 
           this == Tab || 
           this == WhiteSpace;
  }
}
