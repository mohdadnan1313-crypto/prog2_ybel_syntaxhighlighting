package highlighting.presets;

import highlighting.regex.Token;
import java.util.List;
import java.util.regex.Pattern;

public final class MiniJavaTokens {

  public static List<Token> defaultTokens() {
    return List.of(
        // Javadoc comments (must come before block comments!)
        Token.of(
            Pattern.compile("/\\*\\*.*?\\*/", Pattern.DOTALL),
            MiniJavaColours.JAVADOC_COMMENT_COLOUR),

        // Block comments
        Token.of(
            Pattern.compile("/\\*.*?\\*/", Pattern.DOTALL), MiniJavaColours.BLOCK_COMMENT_COLOUR),

        // Line comments
        Token.of(Pattern.compile("//[^\n]*"), MiniJavaColours.LINE_COMMENT_COLOUR),

        // String literals
        Token.of(Pattern.compile("\"([^\"\\\\]|\\\\.)*\""), MiniJavaColours.STRING_LITERAL_COLOUR),

        // Character literals
        Token.of(Pattern.compile("'([^'\\\\]|\\\\.)'"), MiniJavaColours.CHAR_LITERAL_COLOUR),

        // Keywords
        Token.of(
            Pattern.compile(
                "\\b(package|import|class|public|private|protected|final|return|null|new|void|int|long|double|float|boolean|char|byte|short|static|if|else|for|while|do|switch|case|break|continue|throws|throw|try|catch|finally|extends|implements|interface|enum|super|this|instanceof)\\b"),
            MiniJavaColours.KEYWORD_COLOUR),

        // Annotations
        Token.of(Pattern.compile("@[A-Za-z-]+"), MiniJavaColours.ANNOTATION_COLOUR));
  }
}
