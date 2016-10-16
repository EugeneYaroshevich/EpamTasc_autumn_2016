package candidates;

import assessments.DoubleExamAssessment;
import assessments.TestAssessment;

import java.util.regex.Pattern;

public class CandidateTwoExamAndTest extends Candidate {

    private final String HALF_ASSESSMENT_VALIDATE = "^[1-9](\\.[05]?)|10(\\.[0]?)$";//0;0.5;1;1.5.....9.5;10

    private String name;
    private String surname;
    private DoubleExamAssessment firstExamAssessment;
    private DoubleExamAssessment secondExamAssessment;
    private TestAssessment testAssessment;

    public CandidateTwoExamAndTest(String name, String surname, DoubleExamAssessment firstExamAssessment, DoubleExamAssessment secondExamAssessment, TestAssessment testAssessment) {
        setName(name);
        setSurname(surname);
        setFirstExamAssessment(firstExamAssessment);
        setSecondExamAssessment(secondExamAssessment);
        setTestAssessment(testAssessment);
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

    public DoubleExamAssessment getFirstExamAssessment() {
        return firstExamAssessment;
    }

    private void setFirstExamAssessment(DoubleExamAssessment firstExamAssessment) {
        if (Pattern.compile(HALF_ASSESSMENT_VALIDATE).matcher(String.valueOf(firstExamAssessment.getValue())).matches()) {
            this.firstExamAssessment = firstExamAssessment;
        } else {
            System.out.println("Enter a value from 0 or 0.5; 1; 1.5...9.5; 10");
        }
    }

    private DoubleExamAssessment getSecondExamAssessment() {
        return secondExamAssessment;
    }

    private void setSecondExamAssessment(DoubleExamAssessment secondExamAssessment) {
        if (Pattern.compile(HALF_ASSESSMENT_VALIDATE).matcher(String.valueOf(secondExamAssessment.getValue())).matches()
                ) {

            this.secondExamAssessment = secondExamAssessment;
        }else {
            System.out.println("Enter a value from 0 or 0.5; 1; 1.5...9.5; 10");
        }

    }

    public TestAssessment getTestAssessment() {
        return testAssessment;
    }

    private void setTestAssessment(TestAssessment testAssessment) {
        if (testAssessment.getValue().equals("сдан") || testAssessment.getValue().equals("не сдан")) {
            this.testAssessment = testAssessment;
        } else {
            System.out.println("Please enter 'сдан' or 'не сдан'");
        }
    }

    @Override
    public String toString() {
        return "CandidateTwoExamAndTest{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", firstExamAssessment=" + getFirstExamAssessment().getValue() +
                ", secondExamAssessment=" + getSecondExamAssessment().getValue() +
                ", testAssessment=" + getTestAssessment().getValue() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandidateTwoExamAndTest)) return false;

        CandidateTwoExamAndTest that = (CandidateTwoExamAndTest) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (firstExamAssessment != null ? !firstExamAssessment.equals(that.firstExamAssessment) : that.firstExamAssessment != null)
            return false;
        if (secondExamAssessment != null ? !secondExamAssessment.equals(that.secondExamAssessment) : that.secondExamAssessment != null)
            return false;
        return testAssessment != null ? testAssessment.equals(that.testAssessment) : that.testAssessment == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (firstExamAssessment != null ? firstExamAssessment.hashCode() : 0);
        result = 31 * result + (secondExamAssessment != null ? secondExamAssessment.hashCode() : 0);
        result = 31 * result + (testAssessment != null ? testAssessment.hashCode() : 0);
        return result;
    }

    public double getMaxValue() {
        if (getFirstExamAssessment().getValue() >= getSecondExamAssessment().getValue()) {
            return getFirstExamAssessment().getValue();
        } else {
            return getSecondExamAssessment().getValue();
        }
    }
}
