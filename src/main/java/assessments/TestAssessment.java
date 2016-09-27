package assessments;

import controls.Test;

public class TestAssessment {

    private String value;
    private Test test;

    public TestAssessment(String value, Test test) {
        setValue(value);
        setTest(test);
    }

    public String getValue() {
        return value;
    }

    private void setValue(String value) {
        this.value = value;
    }

    public Test getTest() {
        return test;
    }

    private void setTest(Test test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "assessments.TestAssessment{" +
                "value='" + value + '\'' +
                ", test=" + test.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestAssessment)) return false;

        TestAssessment that = (TestAssessment) o;

        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return test != null ? test.equals(that.test) : that.test == null;

    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (test != null ? test.hashCode() : 0);
        return result;
    }
}
