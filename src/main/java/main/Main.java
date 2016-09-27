package main;

import events.ControlEvent;
import events.EventsStrategy;
import exception.ControlEventException;

import java.util.List;


public class Main {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Please check the input parameters");
        } else {
            try {
                ControlEvent strategy = EventsStrategy.getInstance().getEvent(args.toString());

                //читаем данные и парсим
                strategy.readAndParse();

                //создаём коллекцию кандидатов прошедших по баллам
                List list = strategy.getValidateCandidates();

                //выводим коллекцию
                strategy.print(list);

                //упорядочиваем коллекцию по убыванию наибольшей среди всех оценок, полученных кандидатом;
                strategy.sort(list);

                //выводим коллекцию
                strategy.print(list);

                //выводим такого кандидата из коллекции, у которого наибольшая оценка больше, чем 15, и она наименьшая среди наибольших оценок других кандидатов
                strategy.withdrawCandidate();


            } catch (ControlEventException e) {
                e.printStackTrace();
            }
        }

    }
}