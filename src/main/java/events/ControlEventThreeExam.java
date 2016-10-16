package events;

import assessments.DoubleExamAssessment;
import assessments.IntegerExamAssessment;
import candidates.CandidateThreeExam;
import controls.Exam;
import exception.ControlEventException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class ControlEventThreeExam implements ControlEvent<CandidateThreeExam> {

    private String arg;

    private double passingScore;

    private Exam firstExam;
    private Exam secondExam;
    private Exam thirdExam;
    ArrayList<CandidateThreeExam> candidates = new ArrayList();

    public ControlEventThreeExam(String arg) throws ControlEventException {
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
                candidates.add(new CandidateThreeExam(scannerCandidateLine.next(), scannerCandidateLine.next(),
                        new IntegerExamAssessment(scannerCandidateLine.nextInt(), firstExam),
                        new IntegerExamAssessment(scannerCandidateLine.nextInt(), secondExam),
                        new DoubleExamAssessment(scannerCandidateLine.nextDouble(), thirdExam)));
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
        thirdExam = new Exam(scannerFirstLine.next());
        passingScore = scannerFirstLine.nextDouble();
    }

    @Override
    public ArrayList<CandidateThreeExam> getValidateCandidates() {

        ArrayList<CandidateThreeExam> listValidateCandidates = new ArrayList<>();

        if (candidates != null) {

            for (CandidateThreeExam candidate : candidates) {

                if (candidate.getSumValues() >= passingScore) {
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
            ArrayList<CandidateThreeExam> filterListCandidates = new ArrayList<>();


            for (CandidateThreeExam candidate : this.candidates) {
                if (candidate.getMaxValue() > 15) {
                    filterListCandidates.add(candidate);
                }
            }
            System.out.println("_______________________________________________________");
            System.out.println(Collections.min(filterListCandidates, new Comparator<CandidateThreeExam>() {
                @Override
                public int compare(CandidateThreeExam o1, CandidateThreeExam o2) {
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

        for (Iterator<CandidateThreeExam> iterator = listCollection.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }

    Comparator<CandidateThreeExam> candidateComparator = new Comparator<CandidateThreeExam>() {
        @Override
        public int compare(CandidateThreeExam candidate_1, CandidateThreeExam candidate_2) {

            return (int) (Math.ceil(candidate_2.getMaxValue() - candidate_1.getMaxValue()));
        }
    };
}
