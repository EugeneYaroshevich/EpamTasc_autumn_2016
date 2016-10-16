package events;

import assessments.IntegerExamAssessment;
import candidates.CandidateTwoExamMaxValue;
import controls.Exam;
import exception.ControlEventException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ControlEventTwoExamMaxValue implements ControlEvent<CandidateTwoExamMaxValue> {

    private String arg;

    private int firstExamPassingScore;
    private int secondExamPassingScore;

    private Exam firstExam;
    private Exam secondExam;
    ArrayList<CandidateTwoExamMaxValue> candidates = new ArrayList();

    public ControlEventTwoExamMaxValue(String arg) throws ControlEventException {
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

                candidates.add(new CandidateTwoExamMaxValue(scannerCandidateLine.next(), scannerCandidateLine.next(),
                        new IntegerExamAssessment(scannerCandidateLine.nextInt(), firstExam),
                        new IntegerExamAssessment(scannerCandidateLine.nextInt(), secondExam)));
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
        firstExamPassingScore = scannerFirstLine.nextInt();
        secondExamPassingScore = scannerFirstLine.nextInt();

    }

    @Override
    public ArrayList<CandidateTwoExamMaxValue> getValidateCandidates() {

        ArrayList<CandidateTwoExamMaxValue> listValidateCandidates = new ArrayList<>();

        if (candidates != null) {

            for (CandidateTwoExamMaxValue candidate : candidates) {

                if (candidate.getFirstExamAssessment().getValue() >= firstExamPassingScore
                        && secondExamPassingScore <= candidate.getFirstExamAssessment().getValue()) {
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
            ArrayList<CandidateTwoExamMaxValue> filterListCandidates = new ArrayList<>();

            for (CandidateTwoExamMaxValue candidate : this.candidates) {
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
    public void sort(List<CandidateTwoExamMaxValue> listSort) {

        Collections.sort(listSort, candidateComparator);
    }

    @Override
    public void print(List<CandidateTwoExamMaxValue> listCollection) {

        for (Iterator<CandidateTwoExamMaxValue> iterator = listCollection.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }

    Comparator<CandidateTwoExamMaxValue> candidateComparator = new Comparator<CandidateTwoExamMaxValue>() {
        @Override
        public int compare(CandidateTwoExamMaxValue candidate_1, CandidateTwoExamMaxValue candidate_2) {

            return candidate_2.getMaxValue() - candidate_1.getMaxValue();
        }
    };
}
