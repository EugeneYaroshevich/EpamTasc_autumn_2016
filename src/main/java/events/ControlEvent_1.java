package events;

import assessments.IntegerExamAssessment;
import candidats.Candidate_1;
import controls.Exam;
import exception.ControlEventException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ControlEvent_1 implements ControlEvent<Candidate_1> {

    private String arg;

    private int passingScore;

    private Exam firstExam;
    private Exam secondExam;
    private ArrayList<Candidate_1> candidates = new ArrayList();


    public ControlEvent_1(String arg) throws ControlEventException {
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

                this.candidates.add(new Candidate_1(scannerCandidateLine.next(), scannerCandidateLine.next(),
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
    public ArrayList<Candidate_1> getValidateCandidates() {

        ArrayList<Candidate_1> listValidateCandidates = new ArrayList<>();

        if (this.candidates != null) {

            for (Candidate_1 candidate : this.candidates) {

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
            ArrayList<Candidate_1> filterListCandidates = new ArrayList<>();

            for (Candidate_1 candidate : this.candidates) {
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
    public void sort(List<Candidate_1> listSort) {
        Collections.sort(listSort, candidateComparator);
    }

    @Override
    public void print(List<Candidate_1> listCollection) {

        for (Iterator<Candidate_1> iterator = listCollection.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }

    Comparator<Candidate_1> candidateComparator = new Comparator<Candidate_1>() {
        @Override
        public int compare(Candidate_1 candidate_1, Candidate_1 candidate_2) {

            return candidate_2.getMaxValue() - candidate_1.getMaxValue();
        }
    };

}
