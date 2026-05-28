package highlighting.regex;

import highlighting.core.HighlightRegion;
import highlighting.core.SyntaxHighlighter;
import highlighting.presets.MiniJavaTokens;
import java.util.ArrayList;
import java.util.List;

public class RegexHighlighter extends SyntaxHighlighter {

  @Override
  public List<HighlightRegion> collectMatches(String text) {
    List<HighlightRegion> regions = new ArrayList<>();
    for (Token token : MiniJavaTokens.defaultTokens()) {
      regions.addAll(token.test(text));
    }
    return regions;
  }

  @Override
  public List<HighlightRegion> resolveConflicts(List<HighlightRegion> regions) {
    List<HighlightRegion> result = new ArrayList<>();
    for (HighlightRegion region : regions) {
      boolean overlaps = false;
      for (HighlightRegion accepted : result) {
        if (region.start() < accepted.end() && region.end() > accepted.start()) {
          overlaps = true;
          break;
        }
      }
      if (!overlaps) {
        result.add(region);
      }
    }
    return result;
  }
}
