package assessments;

import controls.Exam;

public class DoubleExamAssessment {

    private double value;
    private Exam exam;

    public DoubleExamAssessment(double value, Exam exam) {
        setValue(value);
        setExam(exam);
    }

    public double getValue() {
        return value;
    }

    private void setValue(double value) {
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
        return "assessments.DoubleExamAssessment{" +
                "value=" + value +
                ", exam=" + exam.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoubleExamAssessment)) return false;

        DoubleExamAssessment that = (DoubleExamAssessment) o;

        if (Double.compare(that.value, value) != 0) return false;
        return exam != null ? exam.equals(that.exam) : that.exam == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(value);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (exam != null ? exam.hashCode() : 0);
        return result;
    }
}
