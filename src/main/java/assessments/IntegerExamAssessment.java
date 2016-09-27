package assessments;

import controls.Exam;

public class IntegerExamAssessment {

    private int value;
    private Exam exam;

    public IntegerExamAssessment(int value, Exam exam) {
        setValue(value);
        setExam(exam);
    }

    public int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
    }

    public Exam getExam() {
        return exam;
    }

    private void setExam(Exam exam) {
        this.exam = exam;
    }

    @Override
    public String toString() {
        return "assessments.IntegerExamAssessment{" +
                "value=" + value +
                ", exam=" + exam.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerExamAssessment)) return false;

        IntegerExamAssessment that = (IntegerExamAssessment) o;

        if (value != that.value) return false;
        return exam != null ? exam.equals(that.exam) : that.exam == null;

    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + (exam != null ? exam.hashCode() : 0);
        return result;
    }
}
