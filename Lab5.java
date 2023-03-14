import cs2030s.fp.Maybe;
import cs2030s.fp.Transformer;
import java.util.Map;

class Lab5 {
  public static String getGrade(String student, String module, String assessment,
      Map<String, Map<String, Map<String, String>>> db) {
    // Transformer1
    Transformer<Map<String, Map<String, String>>, Maybe<Map<String, String>>> extractMods
        = new Transformer<>() {
          @Override
          public Maybe<Map<String, String>> transform(Map<String, Map<String, String>> mods) {
            return Maybe.of(mods.get(module));
          }
        };

    // Transformer2
    Transformer<Map<String, String>, Maybe<String>> extractAssessment = new Transformer<>() {
      @Override
      public Maybe<String> transform(Map<String, String> assessments) {
        return Maybe.of(assessments.get(assessment));
      }
    };

    return Maybe.of(db.get(student))
      .flatMap(extractMods)
      .flatMap(extractAssessment)
      .orElse("No such entry");
  }

  /*
   * Use this skeleton to replace getGrade
   *
  public static String getGrade(String student, String module, String assessment,
      Map<String, Map<String, Map<String, String>>> map) {

    Transformer<Map<String, Map<String, String>>, Maybe<Map<String, String>>> getModule = ..

    Transformer<Map<String, String>, Maybe<String>> getAssessment = ..

    return Maybe...;
  }
  */

  public static void main(String[] args) {
    Map<String, Map<String, Map<String, String>>> students =
        Map.of(
            "Steve", Map.of(
                "CS2030S", Map.of(
                        "lab1", "A",
                        "lab2", "A-",
                        "lab3", "A+",
                        "lab4", "B",
                        "pe1", "C"),
                "CS2040S", Map.of(
                        "lab1", "A",
                        "lab2", "A+",
                        "lab3", "A+",
                        "lab4", "A",
                        "midterm", "A+")),
            "Tony", Map.of(
                "CS2030S", Map.of(
                    "lab1", "C",
                    "lab2", "C",
                    "lab3", "B-",
                    "lab4", "B+",
                    "pe1", "A")));

    System.out.println(getGrade("Steve", "CS2030S", "lab1", students));
    System.out.println(getGrade("Steve", "CS2030S", "lab2", students));
    System.out.println(getGrade("Steve", "CS2040S", "lab3", students));
    System.out.println(getGrade("Steve", "CS2040S", "lab4", students));
    System.out.println(getGrade("Tony", "CS2030S", "lab1", students));
    System.out.println(getGrade("Tony", "CS2030S", "midterm", students));
    System.out.println(getGrade("Tony", "CS2040S", "lab4", students));
    System.out.println(getGrade("Bruce", "CS2040S", "lab4", students));
  }
}
