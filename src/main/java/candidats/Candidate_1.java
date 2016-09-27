package candidats;

import assessments.IntegerExamAssessment;

import java.util.Comparator;
import java.util.regex.Pattern;

public class Candidate_1 extends Candidate {

    private final String WHOLE_ASSESSMENT_VALIDATE = "^[1-9]|10$";//1;2;3.....9;10

    private String name;
    private String surname;
    private IntegerExamAssessment firstExamAssessment;
    private IntegerExamAssessment secondExamAssessment;

    public Candidate_1(String name, String surname, IntegerExamAssessment firstExamAssessment, IntegerExamAssessment secondExamAssessment) {
        setName(name);
        setSurname(surname);
        setFirstExamAssessment(firstExamAssessment);
        setSecondExamAssessment(secondExamAssessment);
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
        if (firstExamAssessment.getValue() < 0 && 10 < firstExamAssessment.getValue()) {
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

    @Override
    public String toString() {
        return "Candidate_1{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", firstExamAssessment=" + getFirstExamAssessment().getValue() +
                ", secondExamAssessment=" + getSecondExamAssessment().getValue() +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate_1)) return false;

        Candidate_1 that = (Candidate_1) o;

        if (WHOLE_ASSESSMENT_VALIDATE != null ? !WHOLE_ASSESSMENT_VALIDATE.equals(that.WHOLE_ASSESSMENT_VALIDATE) : that.WHOLE_ASSESSMENT_VALIDATE != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (firstExamAssessment != null ? !firstExamAssessment.equals(that.firstExamAssessment) : that.firstExamAssessment != null)
            return false;
        return secondExamAssessment != null ? secondExamAssessment.equals(that.secondExamAssessment) : that.secondExamAssessment == null;

    }

    @Override
    public int hashCode() {
        int result = WHOLE_ASSESSMENT_VALIDATE != null ? WHOLE_ASSESSMENT_VALIDATE.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (firstExamAssessment != null ? firstExamAssessment.hashCode() : 0);
        result = 31 * result + (secondExamAssessment != null ? secondExamAssessment.hashCode() : 0);
        return result;
    }


    public int getSumValues() {
        return getFirstExamAssessment().getValue() + getSecondExamAssessment().getValue();
    }

    public int getMaxValue() {
        if (getFirstExamAssessment().getValue() >= getSecondExamAssessment().getValue()) {
            return getFirstExamAssessment().getValue();
        } else {
            return getSecondExamAssessment().getValue();
        }
    }
}
