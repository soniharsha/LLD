package AnnotationOperation;

public class Person {

    @StringAnalyzer({StringAnalyzer.Analyze.CAPITAL_CASE, StringAnalyzer.Analyze.NULL})
    private String firstName;

    @StringAnalyzer(StringAnalyzer.Analyze.SMALL_CASE)
    private String lastName;

    @StringAnalyzer(StringAnalyzer.Analyze.NULL)
    private String emailId;

    public Person(String firstName, String lastName, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }
}
