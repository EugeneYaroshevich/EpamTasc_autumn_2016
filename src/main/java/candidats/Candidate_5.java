package candidats;

import assessments.IntegerExamAssessment;
import assessments.TestAssessment;

import java.util.regex.Pattern;

public class Candidate_5 extends Candidate {

    private final String WHOLE_ASSESSMENT_VALIDATE = "^\\d{1,2}|100$";//0;1;2;3.....98;99;100

    private String name;
    private String surname;
    private TestAssessment firstTestAssessment;
    private TestAssessment secondTestAssessment;
    private IntegerExamAssessment examAssessment;

    public Candidate_5(String name, String surname, TestAssessment firstTestAssessment, TestAssessment secondTestAssessment, IntegerExamAssessment examAssessment) {
        setName(name);
        setSurname(surname);
        setFirstTestAssessment(firstTestAssessment);
        setSecondTestAssessment(secondTestAssessment);
        setExamAssessment(examAssessment);
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getSurname() {
        return surname;
    }

    private void setSurname(String surname) {
        this.surname = surname;
    }

    public TestAssessment getFirstTestAssessment() {
        return firstTestAssessment;
    }

    private void setFirstTestAssessment(TestAssessment firstTestAssessment) {
        if (firstTestAssessment.getValue().equals("сдан") || firstTestAssessment.getValue().equals("не сдан")) {
            this.firstTestAssessment = firstTestAssessment;
        } else {
            System.out.println("Please enter 'сдан' or 'не сдан'");
        }

    }

    public TestAssessment getSecondTestAssessment() {
        return secondTestAssessment;
    }

    private void setSecondTestAssessment(TestAssessment secondTestAssessment) {
        if (secondTestAssessment.getValue().equals("сдан") || secondTestAssessment.getValue().equals("не сдан")) {
            this.secondTestAssessment = secondTestAssessment;
        } else {
            System.out.println("Please enter 'сдан' or 'не сдан'");
        }

    }

    public IntegerExamAssessment getExamAssessment() {
        return examAssessment;
    }

    private void setExamAssessment(IntegerExamAssessment examAssessment) {
        if (!Pattern.compile(WHOLE_ASSESSMENT_VALIDATE).matcher(String.valueOf(examAssessment.getValue())).matches()) {
            System.out.println("Enter a value from 0 to 100");
        }
        this.examAssessment = examAssessment;
    }

    @Override
    public String toString() {
        return "Candidate_5{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", firstTestAssessment=" + getFirstTestAssessment().getValue() +
                ", secondTestAssessment=" + getSecondTestAssessment().getValue() +
                ", examAssessment=" + getExamAssessment().getValue() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate_5)) return false;

        Candidate_5 that = (Candidate_5) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (firstTestAssessment != null ? !firstTestAssessment.equals(that.firstTestAssessment) : that.firstTestAssessment != null)
            return false;
        if (secondTestAssessment != null ? !secondTestAssessment.equals(that.secondTestAssessment) : that.secondTestAssessment != null)
            return false;
        return examAssessment != null ? examAssessment.equals(that.examAssessment) : that.examAssessment == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (firstTestAssessment != null ? firstTestAssessment.hashCode() : 0);
        result = 31 * result + (secondTestAssessment != null ? secondTestAssessment.hashCode() : 0);
        result = 31 * result + (examAssessment != null ? examAssessment.hashCode() : 0);
        return result;
    }
}
