package events;

import assessments.DoubleExamAssessment;
import assessments.TestAssessment;
import candidats.Candidate_3;
import controls.Exam;
import controls.Test;
import exception.ControlEventException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ControlEvent_3 implements ControlEvent<Candidate_3> {

    private String arg;

    double firstExamPassingScore;
    double secondExamPassingScore;

    private Exam firstExam;
    private Exam secondExam;
    private Test test;
    ArrayList<Candidate_3> candidates = new ArrayList();


    public ControlEvent_3(String arg) throws ControlEventException {
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
                candidates.add(new Candidate_3(scannerCandidateLine.next(), scannerCandidateLine.next(),
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
    public ArrayList<Candidate_3> getValidateCandidates() {

        ArrayList<Candidate_3> listValidateCandidates = new ArrayList<>();

        if (candidates != null) {

            for (Candidate_3 candidate : candidates) {

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
            ArrayList<Candidate_3> filterListCandidates = new ArrayList<>();

            for (Candidate_3 candidate : this.candidates) {
                if (candidate.getMaxValue() > 15) {
                    filterListCandidates.add(candidate);
                }
            }
            System.out.println("_______________________________________________________");
            System.out.println(Collections.min(filterListCandidates, new Comparator<Candidate_3>() {
                @Override
                public int compare(Candidate_3 o1, Candidate_3 o2) {
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

        for (Iterator<Candidate_3> iterator = listCollection.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }

    Comparator<Candidate_3> candidateComparator = new Comparator<Candidate_3>() {
        @Override
        public int compare(Candidate_3 candidate_1, Candidate_3 candidate_2) {

            return (int) (Math.ceil(candidate_2.getMaxValue() - candidate_1.getMaxValue()));
        }
    };
}
