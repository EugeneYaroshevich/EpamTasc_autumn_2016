package events;

import assessments.DoubleExamAssessment;
import assessments.TestAssessment;
import candidates.CandidateTwoExamAndTest;
import controls.Exam;
import controls.Test;
import exception.ControlEventException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ControlEventTwoExamAndTest implements ControlEvent<CandidateTwoExamAndTest> {

    private String arg;

    double firstExamPassingScore;
    double secondExamPassingScore;

    private Exam firstExam;
    private Exam secondExam;
    private Test test;
    ArrayList<CandidateTwoExamAndTest> candidates = new ArrayList();


    public ControlEventTwoExamAndTest(String arg) throws ControlEventException {
        this.arg = arg;
    }


    @Override
    public void readAndParse() throws ControlEventException {

        try (FileReader fileReader = new FileReader(new File("").getAbsolutePath() + File.separator +
                "src" + File.separator + "main" + File.separator + "resources" + File.separator + arg);
             Scanner scanner = new Scanner(fileReader)) {

            scanner.useDelimiter(System.getProperty("line.separator"));

            parseFirstLine(scanner.next());

            while (scanner.hasNext()) {
                Scanner scannerCandidateLine = new Scanner(scanner.next());
                scannerCandidateLine.useDelimiter(";");
                candidates.add(new CandidateTwoExamAndTest(scannerCandidateLine.next(), scannerCandidateLine.next(),
                        new DoubleExamAssessment(scannerCandidateLine.nextDouble(), firstExam),
                        new DoubleExamAssessment(scannerCandidateLine.nextDouble(), secondExam),
                        new TestAssessment(scannerCandidateLine.next(), test)));
            }

        } catch (FileNotFoundException e) {
            throw new ControlEventException("File not found", e);
        } catch (IOException e) {
            throw new ControlEventException(e);
        }
    }

    private void parseFirstLine(String firstLine) {
        Scanner scannerFirstLine = new Scanner(firstLine);
        scannerFirstLine.useDelimiter(";");
        firstExam = new Exam(scannerFirstLine.next());
        secondExam = new Exam(scannerFirstLine.next());
        test = new Test(scannerFirstLine.next());
        firstExamPassingScore = scannerFirstLine.nextDouble();
        secondExamPassingScore = scannerFirstLine.nextDouble();
    }

    @Override
    public ArrayList<CandidateTwoExamAndTest> getValidateCandidates() {

        ArrayList<CandidateTwoExamAndTest> listValidateCandidates = new ArrayList<>();

        if (candidates != null) {

            for (CandidateTwoExamAndTest candidate : candidates) {

                if (candidate.getFirstExamAssessment().getValue() >= firstExamPassingScore
                        && candidate.getFirstExamAssessment().getValue() >= secondExamPassingScore
                        && candidate.getTestAssessment().getValue().equals("сдан")) {
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
            ArrayList<CandidateTwoExamAndTest> filterListCandidates = new ArrayList<>();

            for (CandidateTwoExamAndTest candidate : this.candidates) {
                if (candidate.getMaxValue() > 15) {
                    filterListCandidates.add(candidate);
                }
            }
            System.out.println("_______________________________________________________");
            System.out.println(Collections.min(filterListCandidates, new Comparator<CandidateTwoExamAndTest>() {
                @Override
                public int compare(CandidateTwoExamAndTest o1, CandidateTwoExamAndTest o2) {
                    return (int) (Math.ceil(o1.getMaxValue() - o2.getMaxValue()));
                }
            }));
        } catch (Exception e) {
            throw new ControlEventException("Нет таких кандидатов", e);
        }
    }

    @Override
    public void sort(List listSort) {

        Collections.sort(listSort, candidateComparator);
    }

    @Override
    public void print(List listCollection) {

        for (Iterator<CandidateTwoExamAndTest> iterator = listCollection.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }

    Comparator<CandidateTwoExamAndTest> candidateComparator = new Comparator<CandidateTwoExamAndTest>() {
        @Override
        public int compare(CandidateTwoExamAndTest candidate_1, CandidateTwoExamAndTest candidate_2) {

            return (int) (Math.ceil(candidate_2.getMaxValue() - candidate_1.getMaxValue()));
        }
    };
}
