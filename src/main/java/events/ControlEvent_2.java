package events;

import assessments.IntegerExamAssessment;
import candidats.Candidate_2;
import controls.Exam;
import exception.ControlEventException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ControlEvent_2 implements ControlEvent<Candidate_2> {

    private String arg;

    private int firstExamPassingScore;
    private int secondExamPassingScore;

    private Exam firstExam;
    private Exam secondExam;
    ArrayList<Candidate_2> candidates = new ArrayList();

    public ControlEvent_2(String arg) throws ControlEventException {
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

                candidates.add(new Candidate_2(scannerCandidateLine.next(), scannerCandidateLine.next(),
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
    public ArrayList<Candidate_2> getValidateCandidates() {

        ArrayList<Candidate_2> listValidateCandidates = new ArrayList<>();

        if (candidates != null) {

            for (Candidate_2 candidate : candidates) {

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
            ArrayList<Candidate_2> filterListCandidates = new ArrayList<>();

            for (Candidate_2 candidate : this.candidates) {
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
    public void sort(List<Candidate_2> listSort) {

        Collections.sort(listSort, candidateComparator);
    }

    @Override
    public void print(List<Candidate_2> listCollection) {

        for (Iterator<Candidate_2> iterator = listCollection.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }

    Comparator<Candidate_2> candidateComparator = new Comparator<Candidate_2>() {
        @Override
        public int compare(Candidate_2 candidate_1, Candidate_2 candidate_2) {

            return candidate_2.getMaxValue() - candidate_1.getMaxValue();
        }
    };
}
