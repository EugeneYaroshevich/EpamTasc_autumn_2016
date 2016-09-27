package events;

import exception.ControlEventException;

public class EventsStrategy<T extends ControlEvent> {

    private final String CONTROL_EVENT_1 = "CE_1";
    private final String CONTROL_EVENT_2 = "CE_2";
    private final String CONTROL_EVENT_3 = "CE_3";
    private final String CONTROL_EVENT_4 = "CE_4";
    private final String CONTROL_EVENT_5 = "CE_5";


    private EventsStrategy() throws ControlEventException {

    }

    public static EventsStrategy getInstance() throws ControlEventException {
        return new EventsStrategy();
    }


    public T getEvent(String arg) throws ControlEventException {

        T controlEvent = null;

        switch (arg) {

            case CONTROL_EVENT_1:
                controlEvent = (T) getControlEvent_1(arg);
                break;
            case CONTROL_EVENT_2:
                controlEvent = (T) getControlEvent_2(arg);
                break;
            case CONTROL_EVENT_3:
                controlEvent = (T) getControlEvent_3(arg);
                break;
            case CONTROL_EVENT_4:
                controlEvent = (T) getControlEvent_4(arg);
                break;
            case CONTROL_EVENT_5:
                controlEvent = (T) getControlEvent_5(arg);
                break;
            default:
                System.out.println("Please check the input parameters");
                break;
        }

        return controlEvent;
    }


    private ControlEvent_1 getControlEvent_1(String arg) throws ControlEventException {
        return new ControlEvent_1(arg);
    }

    private ControlEvent_2 getControlEvent_2(String arg) throws ControlEventException {
        return new ControlEvent_2(arg);
    }

    private ControlEvent_3 getControlEvent_3(String arg) throws ControlEventException {
        return new ControlEvent_3(arg);
    }

    private ControlEvent_4 getControlEvent_4(String arg) throws ControlEventException {
        return new ControlEvent_4(arg);
    }

    private ControlEvent_5 getControlEvent_5(String arg) throws ControlEventException {
        return new ControlEvent_5(arg);
    }

}
