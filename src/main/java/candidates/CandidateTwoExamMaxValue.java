package candidates;

import assessments.IntegerExamAssessment;

import java.util.regex.Pattern;

public class CandidateTwoExamMaxValue extends Candidate {

    private final String WHOLE_ASSESSMENT_VALIDATE = "^[1-9]|10$";//1;2;3.....9;10

    private String name;
    private String surname;
    private IntegerExamAssessment firstExamAssessment;
    private IntegerExamAssessment secondExamAssessment;

    public CandidateTwoExamMaxValue(String name, String surname, IntegerExamAssessment firstExamAssessment, IntegerExamAssessment secondExamAssessment) {
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

    public IntegerExamAssessment getFirstExamAssessment() {
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
        return "CandidateTwoExamMaxValue{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", firstExamAssessment=" + getFirstExamAssessment().getValue() +
                ", secondExamAssessment=" + getSecondExamAssessment().getValue() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandidateTwoExamMaxValue)) return false;

        CandidateTwoExamMaxValue candidate = (CandidateTwoExamMaxValue) o;

        if (name != null ? !name.equals(candidate.name) : candidate.name != null) return false;
        if (surname != null ? !surname.equals(candidate.surname) : candidate.surname != null) return false;
        if (firstExamAssessment != null ? !firstExamAssessment.equals(candidate.firstExamAssessment) : candidate.firstExamAssessment != null)
            return false;
        return secondExamAssessment != null ? secondExamAssessment.equals(candidate.secondExamAssessment) : candidate.secondExamAssessment == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (firstExamAssessment != null ? firstExamAssessment.hashCode() : 0);
        result = 31 * result + (secondExamAssessment != null ? secondExamAssessment.hashCode() : 0);
        return result;
    }


    public int getMaxValue() {
        if (getFirstExamAssessment().getValue() >= getSecondExamAssessment().getValue()) {
            return getFirstExamAssessment().getValue();
        } else {
            return getSecondExamAssessment().getValue();
        }
    }
}

