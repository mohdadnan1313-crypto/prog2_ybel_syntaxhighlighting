package highlighting.regex;

import highlighting.core.HighlightRegion;
import highlighting.core.SyntaxHighlighter;
import highlighting.presets.MiniJavaTokens;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class ScanningHighlighter extends SyntaxHighlighter {

  @Override
  public List<HighlightRegion> collectMatches(String text) {
    List<Token> tokens = MiniJavaTokens.defaultTokens();
    List<HighlightRegion> regions = new ArrayList<>();
    int i = 0;
    while (i < text.length()) {
      HighlightRegion best = null;
      for (Token token : tokens) {
        Matcher matcher = token.pattern().matcher(text);
        if (matcher.find(i) && matcher.start() == i) {
          int start = matcher.start(token.matchingGroup());
          int end = matcher.end(token.matchingGroup());
          if (best == null || (end - start) > (best.end() - best.start())) {
            best = new HighlightRegion(start, end, token.colour());
          }
        }
      }
      if (best != null) {
        regions.add(best);
        i = best.end();
      } else {
        i++;
      }
    }
    return regions;
  }

  @Override
  public List<HighlightRegion> normalize(List<HighlightRegion> candidates) {
    return candidates;
  }
}
