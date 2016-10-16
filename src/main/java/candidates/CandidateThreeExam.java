package candidates;

import assessments.DoubleExamAssessment;
import assessments.IntegerExamAssessment;

import java.util.regex.Pattern;

public class CandidateThreeExam extends Candidate {

    private final String WHOLE_ASSESSMENT_VALIDATE = "^[1-9]|10$";//1;2;3.....9;10
    private final String HALF_ASSESSMENT_VALIDATE = "^\\d(\\.[05]?)|10$|^1\\d(\\.[05]?)|^20(\\.[0]?)$";//0;0.5;1;1.5.....19.5;20


    private String name;
    private String surname;
    private IntegerExamAssessment firstExamAssessment;
    private IntegerExamAssessment secondExamAssessment;
    private DoubleExamAssessment thirdExamAssessment;

    public CandidateThreeExam(String name, String surname, IntegerExamAssessment firstExamAssessment, IntegerExamAssessment secondExamAssessment, DoubleExamAssessment thirdExamAssessment) {
        setName(name);
        setSurname(surname);
        setFirstExamAssessment(firstExamAssessment);
        setSecondExamAssessment(secondExamAssessment);
        setThirdExamAssessment(thirdExamAssessment);
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

    private IntegerExamAssessment getFirstExamAssessment() {
        return firstExamAssessment;
    }

    private void setFirstExamAssessment(IntegerExamAssessment firstExamAssessment) {
        if (!Pattern.compile(WHOLE_ASSESSMENT_VALIDATE).matcher(String.valueOf(firstExamAssessment.getValue())).matches()) {
            System.out.println("Enter a value from 0 to 10");
        }
        this.firstExamAssessment = firstExamAssessment;
    }

    private IntegerExamAssessment getSecondExamAssessment() {
        return secondExamAssessment;
    }

    private void setSecondExamAssessment(IntegerExamAssessment secondExamAssessment) {
        if (!Pattern.compile(WHOLE_ASSESSMENT_VALIDATE).matcher(String.valueOf(secondExamAssessment.getValue())).matches()) {
            System.out.println("Enter a value from 0 to 10");
        }
        this.secondExamAssessment = secondExamAssessment;
    }

    private DoubleExamAssessment getThirdExamAssessment() {
        return thirdExamAssessment;
    }

    private void setThirdExamAssessment(DoubleExamAssessment thirdExamAssessment) {
        if (Pattern.compile(HALF_ASSESSMENT_VALIDATE).matcher(String.valueOf(thirdExamAssessment.getValue())).matches()) {

            this.thirdExamAssessment = thirdExamAssessment;
        } else {
            System.out.println("Enter a value from 0 or 0.5; 1; 1.5...19.5; 20");
        }

    }

    @Override
    public String toString() {
        return "CandidateThreeExam{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", firstExamAssessment=" + getFirstExamAssessment().getValue() +
                ", secondExamAssessment=" + getSecondExamAssessment().getValue() +
                ", thirdExamAssessment=" + getThirdExamAssessment().getValue() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandidateThreeExam)) return false;

        CandidateThreeExam that = (CandidateThreeExam) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (firstExamAssessment != null ? !firstExamAssessment.equals(that.firstExamAssessment) : that.firstExamAssessment != null)
            return false;
        if (secondExamAssessment != null ? !secondExamAssessment.equals(that.secondExamAssessment) : that.secondExamAssessment != null)
            return false;
        return thirdExamAssessment != null ? thirdExamAssessment.equals(that.thirdExamAssessment) : that.thirdExamAssessment == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (firstExamAssessment != null ? firstExamAssessment.hashCode() : 0);
        result = 31 * result + (secondExamAssessment != null ? secondExamAssessment.hashCode() : 0);
        result = 31 * result + (thirdExamAssessment != null ? thirdExamAssessment.hashCode() : 0);
        return result;
    }

    public double getSumValues() {
        return getFirstExamAssessment().getValue() + getSecondExamAssessment().getValue() + getThirdExamAssessment().getValue();
    }

    public double getMaxValue() {
        if (getFirstExamAssessment().getValue() >= getSecondExamAssessment().getValue()
                && getFirstExamAssessment().getValue() >= getThirdExamAssessment().getValue()) {
            return getFirstExamAssessment().getValue();
        } else if (getSecondExamAssessment().getValue() >= getFirstExamAssessment().getValue()
                && this.getSecondExamAssessment().getValue() >= getThirdExamAssessment().getValue()) {
            return getSecondExamAssessment().getValue();
        } else {
            return getThirdExamAssessment().getValue();
        }

    }
}
