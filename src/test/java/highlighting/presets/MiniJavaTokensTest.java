package highlighting.presets;

import static org.junit.jupiter.api.Assertions.*;

import highlighting.core.HighlightRegion;
import highlighting.regex.Token;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MiniJavaTokensTest {

  private Token getToken(int index) {
    return MiniJavaTokens.defaultTokens().get(index);
  }

  @Test
  void testStringLiteralMatch() {
    // given
    Token token =
        MiniJavaTokens.defaultTokens().stream()
            .filter(t -> t.colour().equals(MiniJavaColours.STRING_LITERAL_COLOUR))
            .findFirst()
            .get();
    // when
    List<HighlightRegion> regions = token.test("String s = \"hello world\";");
    // then
    assertEquals(1, regions.size());
    assertEquals(11, regions.get(0).start());
    assertEquals(24, regions.get(0).end());
  }

  @Test
  void testStringLiteralNoMatch() {
    // given
    Token token =
        MiniJavaTokens.defaultTokens().stream()
            .filter(t -> t.colour().equals(MiniJavaColours.STRING_LITERAL_COLOUR))
            .findFirst()
            .get();
    // when
    List<HighlightRegion> regions = token.test("int x = 5;");
    // then
    assertTrue(regions.isEmpty());
  }

  @Test
  void testMultipleStringLiterals() {
    // given
    Token token =
        MiniJavaTokens.defaultTokens().stream()
            .filter(t -> t.colour().equals(MiniJavaColours.STRING_LITERAL_COLOUR))
            .findFirst()
            .get();
    // when
    List<HighlightRegion> regions = token.test("\"foo\" + \"bar\"");
    // then
    assertEquals(2, regions.size());
  }

  @Test
  void testCharLiteralMatch() {
    // given
    Token token =
        MiniJavaTokens.defaultTokens().stream()
            .filter(t -> t.colour().equals(MiniJavaColours.CHAR_LITERAL_COLOUR))
            .findFirst()
            .get();
    // when
    List<HighlightRegion> regions = token.test("char c = 'a';");
    // then
    assertEquals(1, regions.size());
  }

  @Test
  void testKeywordMatch() {
    // given
    Token token =
        MiniJavaTokens.defaultTokens().stream()
            .filter(t -> t.colour().equals(MiniJavaColours.KEYWORD_COLOUR))
            .findFirst()
            .get();
    // when
    List<HighlightRegion> regions = token.test("public class Foo {}");
    // then
    assertEquals(2, regions.size());
  }

  @Test
  void testKeywordNotMatchedInsideWord() {
    // given
    Token token =
        MiniJavaTokens.defaultTokens().stream()
            .filter(t -> t.colour().equals(MiniJavaColours.KEYWORD_COLOUR))
            .findFirst()
            .get();
    // when
    List<HighlightRegion> regions = token.test("returning");
    // then
    assertTrue(regions.isEmpty());
  }

  @Test
  void testAnnotationMatch() {
    // given
    Token token =
        MiniJavaTokens.defaultTokens().stream()
            .filter(t -> t.colour().equals(MiniJavaColours.ANNOTATION_COLOUR))
            .findFirst()
            .get();
    // when
    List<HighlightRegion> regions = token.test("@Override");
    // then
    assertEquals(1, regions.size());
  }

  @Test
  void testLineCommentMatch() {
    // given
    Token token =
        MiniJavaTokens.defaultTokens().stream()
            .filter(t -> t.colour().equals(MiniJavaColours.LINE_COMMENT_COLOUR))
            .findFirst()
            .get();
    // when
    List<HighlightRegion> regions = token.test("int x = 5; // comment");
    // then
    assertEquals(1, regions.size());
  }

  @Test
  void testBlockCommentMatch() {
    // given
    Token token =
        MiniJavaTokens.defaultTokens().stream()
            .filter(t -> t.colour().equals(MiniJavaColours.BLOCK_COMMENT_COLOUR))
            .findFirst()
            .get();
    // when
    List<HighlightRegion> regions = token.test("/* block comment */");
    // then
    assertEquals(1, regions.size());
  }

  @Test
  void testJavadocCommentMatch() {
    // given
    Token token =
        MiniJavaTokens.defaultTokens().stream()
            .filter(t -> t.colour().equals(MiniJavaColours.JAVADOC_COMMENT_COLOUR))
            .findFirst()
            .get();
    // when
    List<HighlightRegion> regions = token.test("/** javadoc */");
    // then
    assertEquals(1, regions.size());
  }
}
