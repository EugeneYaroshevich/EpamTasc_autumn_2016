package events;

import assessments.IntegerExamAssessment;
import candidates.CandidateTwoExamSumValue;
import controls.Exam;
import exception.ControlEventException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ControlEventTwoExamSumValue implements ControlEvent<CandidateTwoExamSumValue> {

    private String arg;

    private int passingScore;

    private Exam firstExam;
    private Exam secondExam;
    private ArrayList<CandidateTwoExamSumValue> candidates = new ArrayList();


    public ControlEventTwoExamSumValue(String arg) throws ControlEventException {
        this.arg = arg;
    }

    @Override
    public void readAndParse() throws ControlEventException {

        try (FileReader fileReader = new FileReader(new File("").getAbsolutePath() + File.separator +
                "src" + File.separator + "main" + File.separator + "resources" + File.separator + arg);
             Scanner scanner = new Scanner(fileReader)) {
            scanner.useDelimiter(System.getProperty("line.separator"));

            Scanner scannerFirstLine = new Scanner(scanner.next());
            scannerFirstLine.useDelimiter(";");

            firstExam = new Exam(scannerFirstLine.next());
            secondExam = new Exam(scannerFirstLine.next());
            passingScore = scannerFirstLine.nextInt();

            while (scanner.hasNext()) {
                Scanner scannerCandidateLine = new Scanner(scanner.next());
                scannerCandidateLine.useDelimiter(";");

                this.candidates.add(new CandidateTwoExamSumValue(scannerCandidateLine.next(), scannerCandidateLine.next(),
                        new IntegerExamAssessment(scannerCandidateLine.nextInt(), firstExam),
                        new IntegerExamAssessment(scannerCandidateLine.nextInt(), secondExam)));
            }

        } catch (FileNotFoundException e) {
            throw new ControlEventException("File not found", e);
        } catch (IOException e) {
            throw new ControlEventException(e);
        }
    }

    @Override
    public ArrayList<CandidateTwoExamSumValue> getValidateCandidates() {

        ArrayList<CandidateTwoExamSumValue> listValidateCandidates = new ArrayList<>();

        if (this.candidates != null) {

            for (CandidateTwoExamSumValue candidate : this.candidates) {

                if (candidate.getSumValues() >= this.passingScore) {
                    listValidateCandidates.add(candidate);
                }
            }
            return listValidateCandidates;
        }
        return listValidateCandidates;

    }

    @Override
    public void withdrawCandidate() throws ControlEventException {

        try {
            ArrayList<CandidateTwoExamSumValue> filterListCandidates = new ArrayList<>();

            for (CandidateTwoExamSumValue candidate : this.candidates) {
                if (candidate.getMaxValue() > 15) {
                    filterListCandidates.add(candidate);
                }
            }
            System.out.println("_______________________________________________________");
            System.out.println(Collections.min(filterListCandidates, (a, b) -> a.getMaxValue() - b.getMaxValue()));
        } catch (Exception e) {
            throw new ControlEventException("Нет таких кандидатов", e);
        }


    }

    @Override
    public void sort(List<CandidateTwoExamSumValue> listSort) {
        Collections.sort(listSort, candidateComparator);
    }

    @Override
    public void print(List<CandidateTwoExamSumValue> listCollection) {

        for (Iterator<CandidateTwoExamSumValue> iterator = listCollection.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }

    Comparator<CandidateTwoExamSumValue> candidateComparator = new Comparator<CandidateTwoExamSumValue>() {
        @Override
        public int compare(CandidateTwoExamSumValue candidate_1, CandidateTwoExamSumValue candidate_2) {

            return candidate_2.getMaxValue() - candidate_1.getMaxValue();
        }
    };

}
