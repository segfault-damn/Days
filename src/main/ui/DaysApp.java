package ui;

import model.Date;
import model.Day;
import model.DaySet;
import model.entries.TodoEvent;


import java.util.Calendar;
import java.util.Scanner;

public class DaysApp {
    private Scanner input;
    private Date today;
    private DaySet dayset = new DaySet();

    public DaysApp() {
        runDays();
    }

    public void runDays() {
        boolean keepGoing = true;
        String command = null;

        input = new Scanner(System.in);


        while (keepGoing) {
            setToday();
            System.out.println("Today is: ");
            System.out.println(today.getYear() + "." + today.getMonth() + "." + today.getDay());

            System.out.println("You want days to: ");
            System.out.println("a: anniversary");
            System.out.println("d: diary");
            System.out.println("h: habit");
            System.out.println("m: mood");
            System.out.println("e: event");
            System.out.println("q: quit");


            command = input.next();
            command = command.toLowerCase();


            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

    }

    public void setToday() {
        int tdYear = Calendar.getInstance().get(Calendar.YEAR);
        int tdMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int tdDay = Calendar.getInstance().get(Calendar.DATE);
        today = new Date(tdYear,tdMonth, tdDay);
    }

    public void processCommand(String command) {

        if (command.equals("a")) {
            doAnniversary();
        } else if (command.equals("d")) {
            doDiary();
        } else if (command.equals("h")) {
            doHabit();
        } else if (command.equals("m")) {
            doMood();
        } else if (command.equals("e")) {
            doEvent();
        } else {
            System.out.println("Selection not valid...");
        }


    }

    public void doAnniversary() {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        while (keepGoing) {

            System.out.println("Select Anniversary function:");
            String s = input.next();
            if (s.equals("set")) {
                setAnniversary();
            } else if (s.equals("view")) {
                viewAnniversary();
            } else if (s.equals("remove")) {
                removeAnniversary();
            } else if (s.equals("edit")) {
                editAnniversary();
            } else if (s.equals("q")) {

                keepGoing = false;
            } else {
                System.out.println("Selection not valid...");
            }
        }

    }

    public void setAnniversary() {
        input = new Scanner(System.in);
        System.out.println("Enter Anniversary Year:");
        String commend = input.next();
        int inputYear = Integer. parseInt(commend);

        System.out.println("Enter Anniversary Month:");
        commend = input.next();
        int inputMonth = Integer. parseInt(commend);

        System.out.println("Enter Anniversary Date:");
        commend = input.next();
        int inputDay = Integer. parseInt(commend);

        Date anniDate = new Date(inputYear,inputMonth,inputDay);
        System.out.println("Enter Anniversary Name:");
        String l = input.next();

        System.out.println("Enter Anniversary Comment:");
        input = new Scanner(System.in);
        String c = input.nextLine();
        dayset.getDay(anniDate).setAnniversary(l,c);

    }

    public void viewAnniversary() {
        input = new Scanner(System.in);
        for (Day day : dayset.getDays()) {
            System.out.println(day.getAnniversary().getDate().getMonth() + "."
                    + day.getAnniversary().getDate().getDay());

            System.out.println(day.getAnniversary().getLabel());
            System.out.println("Comment: " + day.getAnniversary().getComment());


        }

    }

    public void removeAnniversary() {
        input = new Scanner(System.in);
        System.out.println("Enter Anniversary's name: ");
        String s = input.next();
        for (Day day : dayset.getDays()) {
            if (day.getAnniversary().getLabel().equals(s)) {
                day.removeAnniversary();
                System.out.println("Anniversary" + s + "has been removed!");

            }
        }
    }

    public void editAnniversary() {
        input = new Scanner(System.in);
        System.out.println("Enter Anniversary's name: ");
        String s = input.nextLine();
        for (Day day : dayset.getDays()) {
            if (day.getAnniversary().getLabel().equals(s)) {
                System.out.println("Enter a new comment: ");
                s = input.nextLine();
                day.getAnniversary().editComment(s);
                System.out.println("New comment: " + day.getAnniversary().getComment());
            }
        }
    }


    public void doDiary() {

    }

    public void doHabit() {

    }

    public void doMood() {

    }

    public void doEvent() {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        while (keepGoing) {

            System.out.println("Select TodoEvent function:");
            String s = input.next();
            if (s.equals("set")) {
                setEvent();
            } else if (s.equals("view")) {
                viewEvent();
            } else if (s.equals("remove")) {
                removeEvent();
            } else if (s.equals("edit")) {
                editEventTime();
            } else if (s.equals("q")) {
                keepGoing = false;
            } else {
                System.out.println("Selection not valid...");
            }
        }

    }

    public void setEvent() {
        input = new Scanner(System.in);
        System.out.println("Enter Event Year:");
        String commend = input.next();
        int inputYear = Integer. parseInt(commend);

        System.out.println("Enter Event Month:");
        commend = input.next();
        int inputMonth = Integer. parseInt(commend);

        System.out.println("Enter Event Date:");
        commend = input.next();
        int inputDay = Integer. parseInt(commend);

        Date eventDate = new Date(inputYear,inputMonth,inputDay);

        System.out.println("Enter Event Name:");
        String l = input.next();

        System.out.println("Enter Event Time hour:");
        commend = input.next();
        int inputHour = Integer. parseInt(commend);

        System.out.println("Enter Event Time minute:");
        commend = input.next();
        int inputMin = Integer. parseInt(commend);

        dayset.getDay(eventDate).addEvent(new TodoEvent(eventDate,l,inputHour,inputMin));
    }

    public void viewEvent() {
        input = new Scanner(System.in);
        System.out.println("Enter Event Year:");
        String commend = input.next();
        int inputYear = Integer. parseInt(commend);

        System.out.println("Enter Event Month:");
        commend = input.next();
        int inputMonth = Integer. parseInt(commend);

        System.out.println("Enter Event Date:");
        commend = input.next();
        int inputDay = Integer. parseInt(commend);
        Date eventDate = new Date(inputYear,inputMonth,inputDay);

        for (TodoEvent event : dayset.getDay(eventDate).getTodoEvents()) {
            System.out.println(event.getLabel());
            System.out.println(event.getHour() + ":" + event.getMin());
            System.out.println("");
        }

    }

    public void removeEvent() {

        input = new Scanner(System.in);
        System.out.println("Enter Event Year:");
        String commend = input.next();
        int inputYear = Integer. parseInt(commend);

        System.out.println("Enter Event Month:");
        commend = input.next();
        int inputMonth = Integer. parseInt(commend);

        System.out.println("Enter Event Date:");
        commend = input.next();
        int inputDay = Integer. parseInt(commend);

        Date eventDate = new Date(inputYear,inputMonth,inputDay);

        System.out.println("Enter Event Time hour:");
        commend = input.next();
        int inputHour = Integer. parseInt(commend);

        System.out.println("Enter Event Time minute:");
        commend = input.next();
        int inputMin = Integer. parseInt(commend);

        dayset.getDay(eventDate).removeEvent(inputHour,inputMin);

    }

    public void editEventTime() {

        input = new Scanner(System.in);
        System.out.println("Enter Event Year:");
        String commend = input.next();
        int inputYear = Integer. parseInt(commend);

        System.out.println("Enter Event Month:");
        commend = input.next();
        int inputMonth = Integer. parseInt(commend);

        System.out.println("Enter Event Date:");
        commend = input.next();
        int inputDay = Integer. parseInt(commend);

        Date eventDate = new Date(inputYear,inputMonth,inputDay);

        System.out.println("Enter Event Time hour:");
        commend = input.next();
        int inputHour = Integer. parseInt(commend);

        System.out.println("Enter Event Time minute:");
        commend = input.next();
        int inputMin = Integer. parseInt(commend);

        System.out.println("Enter New Event Time hour and minute:");
        commend = input.next();
        int newHour = Integer. parseInt(commend);

        commend = input.next();
        int newMin = Integer. parseInt(commend);

        dayset.getDay(eventDate).getEvent(inputHour,inputMin).setTime(newHour,newMin);
    }



}
