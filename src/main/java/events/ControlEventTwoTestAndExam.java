package events;

import assessments.IntegerExamAssessment;
import assessments.TestAssessment;
import candidates.CandidateTwoTestAndExam;
import controls.Exam;
import controls.Test;
import exception.ControlEventException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ControlEventTwoTestAndExam implements ControlEvent<CandidateTwoTestAndExam> {

    private String arg;

    int examPassingScore;

    private Test firstTest;
    private Test secondTest;
    private Exam exam;
    ArrayList<CandidateTwoTestAndExam> candidates = new ArrayList();

    public ControlEventTwoTestAndExam(String arg) throws ControlEventException {
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
                candidates.add(new CandidateTwoTestAndExam(scannerCandidateLine.next(), scannerCandidateLine.next(),
                        new TestAssessment(scannerCandidateLine.next(), firstTest),
                        new TestAssessment(scannerCandidateLine.next(), secondTest),
                        new IntegerExamAssessment(scannerCandidateLine.nextInt(), exam)));
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
        firstTest = new Test(scannerFirstLine.next());
        secondTest = new Test(scannerFirstLine.next());
        exam = new Exam(scannerFirstLine.next());
        examPassingScore = scannerFirstLine.nextInt();
    }

    @Override
    public ArrayList<CandidateTwoTestAndExam> getValidateCandidates() {

        ArrayList<CandidateTwoTestAndExam> listValidateCandidates = new ArrayList<>();

        if (candidates != null) {

            for (CandidateTwoTestAndExam candidate : candidates) {

                if (candidate.getFirstTestAssessment().getValue().equals("сдан")
                        && candidate.getSecondTestAssessment().getValue().equals("сдан")
                        && candidate.getExamAssessment().getValue() >= examPassingScore) {
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
            ArrayList<CandidateTwoTestAndExam> filterListCandidates = new ArrayList<>();

            for (CandidateTwoTestAndExam candidate : this.candidates) {
                if (candidate.getExamAssessment().getValue() > 15) {
                    filterListCandidates.add(candidate);
                }
            }
            System.out.println("_______________________________________________________");
            System.out.println(Collections.min(filterListCandidates, (a, b) -> a.getExamAssessment().getValue() - b.getExamAssessment().getValue()));
        } catch (Exception e) {
            throw new ControlEventException("Нет таких кандидатов", e);
        }
    }

    @Override
    public void sort(List listSort) {

        Collections.sort(listSort, candidateComparator);
        //        Collections.sort(validateCandidates, (a, b) -> b.getMaxValue() - a.getMaxValue());
    }

    @Override
    public void print(List listCollection) {

        for (Iterator<CandidateTwoTestAndExam> iterator = listCollection.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }

    Comparator<CandidateTwoTestAndExam> candidateComparator = new Comparator<CandidateTwoTestAndExam>() {
        @Override
        public int compare(CandidateTwoTestAndExam candidate_1, CandidateTwoTestAndExam candidate_2) {

            return candidate_2.getExamAssessment().getValue() - candidate_1.getExamAssessment().getValue();
        }
    };
}
