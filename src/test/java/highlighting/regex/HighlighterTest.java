package highlighting.regex;

import static org.junit.jupiter.api.Assertions.*;

import highlighting.core.HighlightRegion;
import java.util.List;
import org.junit.jupiter.api.Test;

public class HighlighterTest {
  @Test
  void testRegexHighlighterPlain() {
    RegexHighlighter highlighter = new RegexHighlighter();
    List<HighlightRegion> regions = highlighter.collectMatches("public class Test {}");
    assertFalse(regions.isEmpty(), "RegexHighlighter sollte Keywords finden");
  }

  @Test
  void testScanningHighlighterPlain() {
    ScanningHighlighter highlighter = new ScanningHighlighter();
    List<HighlightRegion> regions = highlighter.collectMatches("public class Test {}");
    assertFalse(regions.isEmpty(), "ScanningHighlighter sollte Keywords finden");
  }
}
