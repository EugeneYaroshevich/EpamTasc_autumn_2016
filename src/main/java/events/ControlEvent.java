package events;

import candidates.Candidate;
import exception.ControlEventException;

import java.util.ArrayList;
import java.util.List;


public interface ControlEvent<T extends Candidate> {

    //читает и парсит файл
    void readAndParse() throws ControlEventException;

    //возвращает коллекцию из кандидатов, которые успешно прошли контрольное мероприятие
    ArrayList<T> getValidateCandidates();

    //выводит такого кандидата из коллекции, у которого наибольшая оценка больше, чем 15 и меньше чем у всех
    void withdrawCandidate() throws ControlEventException;

    //упорядочивает коллекцию по убыванию наибольшей среди всех оценок, полученных кандидатом;
    void sort(List<T> listSort);

    //выводим коллекцию
    void print(List<T> listCollection);

}
