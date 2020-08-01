package ui;

import model.Date;
import model.Day;
import model.DaySet;
import model.entries.*;
import persistence.*;
import persistence.Writer;

import java.io.*;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

// The main app
public class DaysApp {
    private DaySet dayset;
    private Scanner input;
    private Date today;

    private static final String DATE_FILE = "./data/dates.txt";
    private static final String ANNI_FILE = "./data/anniversary.txt";
    private static final String DIARY_FILE = "./data/diary.txt";
    private static final String MOOD_FILE = "./data/mood.txt";
    private static final String HABIT_FILE = "./data/habit.txt";
    private static final String EVENTS_FILE = "./data/events.txt";

    private static final String SETHABIT_FILE = "./data/sethabit.txt";

    public DaysApp() {
        runDays();
    }

    // run the program
    public void runDays() {
        boolean keepGoing = true;
        String command;

        input = new Scanner(System.in);
        loadDays();
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
                saveDays();
            } else {
                processCommand(command);
            }
        }

    }

    // grab today's yyyy-MM-dd
    private void setToday() {
        int tdYear = Calendar.getInstance().get(Calendar.YEAR);
        int tdMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int tdDay = Calendar.getInstance().get(Calendar.DATE);
        today = new Date(tdYear, tdMonth, tdDay);
    }

    // MODIFIES: this
    // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
    // otherwise initializes accounts with default values
    private void loadDays() {
        try {
            DaySet savedDayset = new DaySet();

            List<Date> dates = DateReader.readDates(new File(DATE_FILE));
            List<Anniversary> anniversaries = AnniversaryReader.readAnniversary(new File(ANNI_FILE));
            List<Diary> diaries = DiaryReader.readDiary(new File(DIARY_FILE));
            List<Mood> moods = MoodReader.readMood(new File(MOOD_FILE));
            List<HabitList> habitLists = HabitListReader.readHabitLists(new File(HABIT_FILE));
            List<List<TodoEvent>> todoEventLists = TodoEventReader.readEvent(new File(EVENTS_FILE));

            List<Habit> setHabitList = SetHabitListReader.readHabit(new File(SETHABIT_FILE));


            int daysSize = dates.size();


            for (int i = 0; i < daysSize; i++) {
                Day day = new Day(dates.get(i));
                day.setDayAnniversary(anniversaries.get(i));
                day.setDiary(diaries.get(i));
                day.setMood(moods.get(i));
                day.setDailyHabitList(habitLists.get(i));

                day.setTodoEvents(todoEventLists.get(i));

                savedDayset.getDays().add(day);
            }

            savedDayset.setSetHabitList(setHabitList);
            dayset = savedDayset;
        } catch (IOException e) {
            init();
        }
    }

    // EFFECTS: saves state of chequing and savings accounts to ACCOUNTS_FILE
    private void saveDays() {
        try {
            Writer dateWriter = new Writer(new File(DATE_FILE));
            Writer anniWriter = new Writer(new File(ANNI_FILE));
            Writer diaryWriter = new Writer(new File(DIARY_FILE));
            Writer moodWriter = new Writer(new File(MOOD_FILE));
            Writer habitWriter = new Writer(new File(HABIT_FILE));
            Writer todoEventWriter = new Writer(new File(EVENTS_FILE));

            Writer setHabitWriter = new Writer(new File(SETHABIT_FILE));



            for (Day day : dayset.getDays()) {
                dateWriter.write(day.getDate());
                anniWriter.write(day.getAnniversary());
                diaryWriter.write(day.getDiary());
                moodWriter.write(day.getMood());
                habitWriter.write(day.getDailyHabitList());
                todoEventWriter.write(day);
            }

            for (Habit habit : dayset.getSetHabitList().getHabitList()) {
                setHabitWriter.write(habit);
            }

            dateWriter.close();
            anniWriter.close();
            diaryWriter.close();
            moodWriter.close();
            habitWriter.close();
            todoEventWriter.close();


            setHabitWriter.close();
            System.out.println("Days has been saved to file!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save file...");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    private void init() {
        setToday();
        dayset = new DaySet();
        dayset.getDay(today);
    }

    // control the days app
    private void processCommand(String command) {

        switch (command) {
            case "a":
                doAnniversary();
                break;
            case "d":
                doDiary();
                break;
            case "h":
                doHabit();
                break;
            case "m":
                doMood();
                break;
            case "e":
                doEvent();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // anniversary control
    private void doAnniversary() {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        while (keepGoing) {

            System.out.println("Select Anniversary function:");
            String s = input.next();
            switch (s) {
                case "set":
                    setAnniversary();
                    break;
                case "view":
                    viewAnniversary();
                    break;
                case "remove":
                    removeAnniversary();
                    break;
                case "edit":
                    editAnniversary();
                    break;
                case "q":

                    keepGoing = false;
                    break;
                default:
                    System.out.println("Selection not valid...");
                    break;
            }
        }

    }

    // set anniversary in given day
    private void setAnniversary() {
        input = new Scanner(System.in);
        System.out.println("Enter Anniversary Year:");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        System.out.println("Enter Anniversary Month:");
        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        System.out.println("Enter Anniversary Date:");
        commend = input.next();
        int inputDay = Integer.parseInt(commend);

        Date anniDate = new Date(inputYear, inputMonth, inputDay);
        System.out.println("Enter Anniversary Name:");
        input = new Scanner(System.in);
        String l = input.nextLine();
        if (l.equals("")) {
            l = "No label";
        }

        System.out.println("Enter Anniversary Comment:");
        String c = input.nextLine();
        if (c.equals("")) {
            c = " ";
        }
        Anniversary anniversary = new Anniversary(anniDate, l, c);
        anniversary.setAnniversary();
        dayset.getDay(anniDate).setDayAnniversary(anniversary);
    }

    // view all anniversary
    private void viewAnniversary() {
        for (Day day : dayset.getDays()) {
            if (day.getAnniversary().getIsAnniversary()) {
                System.out.println(day.getAnniversary().getDate().getMonth() + "."
                        + day.getAnniversary().getDate().getDay());

                System.out.println(day.getAnniversary().getLabel());
                System.out.println("Comment: " + day.getAnniversary().getComment());
                System.out.println("You have passed " + dayset.calAnniversary(today, day.getAnniversary())
                        + " anniversary - start from " + day.getAnniversary().getDate().getYear());
            }
        }
    }



    // remove one anniversary with given name
    private void removeAnniversary() {
        input = new Scanner(System.in);
        System.out.println("Enter Anniversary's date: ");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        commend = input.next();
        int inputDay = Integer.parseInt(commend);
        Date date = new Date(inputYear,inputMonth,inputDay);
        dayset.getDay(date).removeDayAnniversary();
    }

    // edit the Anniversary's comment with given name
    private void editAnniversary() {
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


    // Diary Control
    private void doDiary() {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        while (keepGoing) {

            System.out.println("Select Diary function:");
            String s = input.next();
            switch (s) {
                case "write":
                    writeDiary();
                    break;
                case "modify":
                    modifyDiary();
                    break;
                case "view":
                    viewDiary();
                    break;
                case "tag":
                    diaryTag();
                    break;
                case "q":
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Selection not valid...");
                    break;
            }
        }
    }


    // write diary in given date
    private void writeDiary() {
        input = new Scanner(System.in);
        setToday();
        System.out.println("Write your diary here:");
        String s = input.nextLine();
        Diary d = new Diary(today);
        d.setContent(s);
        dayset.getDay(today).setDiary(d);

        System.out.println("Add tag or not:");
        s = input.next();
        if (s.equals("y")) {
            System.out.println("Enter your tag:");
            s = input.next();
            d.setTag(s);
        } else {
            System.out.println("This diary is created without tag.");
        }
    }

    // modify the diary in the given day
    private void modifyDiary() {

        input = new Scanner(System.in);
        System.out.println("Modify diary in which day: ");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        commend = input.next();
        int inputDay = Integer.parseInt(commend);
        Date diaryDate = new Date(inputYear, inputMonth, inputDay);

        System.out.println("Modify your diary:");
        input = new Scanner(System.in);
        String s = input.nextLine();
        dayset.getDay(diaryDate).getDiary().setContent(s);
        System.out.println("Diary has been modified");
    }

    // view the diary in the given day
    private void viewDiary() {

        input = new Scanner(System.in);
        System.out.println("View diary in day: ");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        commend = input.next();
        int inputDay = Integer.parseInt(commend);
        Date diaryDate = new Date(inputYear, inputMonth, inputDay);

        System.out.println(inputYear + "." + inputMonth + "." + inputDay);
        System.out.println("Tag: " + dayset.getDay(diaryDate).getDiary().getTag());
        System.out.println(dayset.getDay(diaryDate).getDiary().getContent());
    }

    // diary tag control
    private void diaryTag() {

        input = new Scanner(System.in);

        boolean keepGoing = true;
        while (keepGoing) {

            System.out.println("You want to ");
            System.out.println("1: change tag");
            System.out.println("2: view diary with selected tag");

            String s = input.next();
            switch (s) {
                case "1":
                    changeTag();
                    break;
                case "2":
                    viewTag();
                    break;
                case "q":
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Selection not valid...");
                    break;
            }
        }

    }

    // change a diary's tag
    private void changeTag() {
        input = new Scanner(System.in);
        System.out.println("Change tag on day: ");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        commend = input.next();
        int inputDay = Integer.parseInt(commend);
        Date diaryDate = new Date(inputYear, inputMonth, inputDay);

        System.out.println("Enter your tag below: ");
        String s = input.next();
        dayset.getDay(diaryDate).getDiary().setTag(s);
        System.out.println("Your tag has been changed to " + s);
    }

    // view diary with given tag
    private void viewTag() {

        input = new Scanner(System.in);

        System.out.println("Search diary with tag: ");
        String s = input.next();
        System.out.println(s + " result:");
        for (Diary d : dayset.searchByTag(s)) {
            System.out.println(d.getDate().getYear() + "." + d.getDate().getMonth() + "." + d.getDate().getDay());
            System.out.println();
            System.out.println(d.getContent());
            System.out.println();
        }
    }


    // Habit Control
    private void doHabit() {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        while (keepGoing) {
            System.out.println("Select Habit function:");
            String s = input.next();
            switch (s) {
                case "list":
                    habitList();
                    viewHabit();
                    break;
                case "mark":
                    markHabit();
                    break;
                case "view":

                    searchDateHabit();
                    break;
                case "edit":
                    editHabit();
                    break;
                case "month":
                    monthHabit();
                    break;
                case "q":
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Selection not valid...");
                    break;
            }
        }
    }

    // habitlist control
    private void habitList() {
        input = new Scanner(System.in);
        boolean keepGoing = true;
        while (keepGoing) {

            System.out.println("Select add or remove habit: ");
            String s = input.next();
            switch (s) {
                case "add":
                    addHabit();
                    break;
                case "remove":
                    removeHabit();
                    break;
                case "q":
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Selection not valid...");
                    break;
            }
        }
    }

    // add a new habit
    private void addHabit() {
        input = new Scanner(System.in);
        System.out.println("Enter an habit: ");
        String s = input.nextLine();
        Habit habit = new Habit(s);
        dayset.addDailyHabitList(habit);
        System.out.println(habit.getLabel() + " has been added to your habit list!");

    }

    // remove the habit with a given name
    private void removeHabit() {
        input = new Scanner(System.in);
        System.out.println("Enter an habit: ");
        String s = input.nextLine();

        Habit habit = new Habit(s);
        dayset.removeDailyHabitList(habit);

        System.out.println(habit.getLabel() + " has been removed from your habit list!");


    }


    // flip the state of one particular day
    private void markHabit() {

        input = new Scanner(System.in);
        System.out.println("Mark habit in day: ");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        commend = input.next();
        int inputDay = Integer.parseInt(commend);
        Date habitDate = new Date(inputYear, inputMonth, inputDay);

        System.out.println("Enter an habit: ");
        input = new Scanner(System.in);
        String s = input.nextLine();

        for (Habit h : dayset.getDay(habitDate).getDailyHabitList().getHabitList()) {
            if (h.getLabel().equals(s)) {
                h.flipDone();
            }
        }
        viewDateHabit(habitDate);

    }

    // search the habit in a particular date
    private void searchDateHabit() {
        System.out.println("Today your habit:");
        viewDateHabit(today);

        input = new Scanner(System.in);
        System.out.println("Search Habit in day: ");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        commend = input.next();
        int inputDay = Integer.parseInt(commend);
        Date habitDate = new Date(inputYear, inputMonth, inputDay);

        viewDateHabit(habitDate);
    }

    // change the name of one habit
    private void editHabit() {
        input = new Scanner(System.in);
        System.out.println("Enter an habit to change its name: ");
        String s = input.next();
        Habit habit = new Habit(s);
        String ori = s;
        System.out.println("Enter a new name: ");
        s = input.next();

        dayset.editDailyHabitList(habit, s);
        System.out.println("Habit " + ori + " has been changed to " + s + "!");

    }

    // view all the habit in the list
    private void viewHabit() {

        System.out.println("Here is your habit list:");
        for (String s : dayset.getSetHabitList().getHabitLabel()) {
            System.out.println(s);
        }
    }

    // view the condition of all habit in one particular day (whether completed or not)
    private void viewDateHabit(Date habitDate) {

        for (Habit h : dayset.getDay(habitDate).getDailyHabitList().getHabitList()) {
            if (h.getIsDone()) {
                System.out.println(h.getLabel() + ": Done!");
            } else {
                System.out.println(h.getLabel() + ": Not done!");
            }
        }
    }

    // view the statistic data of all habit done in one particular month
    private void monthHabit() {

        input = new Scanner(System.in);
        System.out.println("View completed habit in month: ");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        System.out.println("Enter the habit you want to search: ");
        commend = input.next();
        Habit habit = new Habit(commend);

        int i = dayset.countHabit(inputYear, inputMonth, habit);
        System.out.println("Your have completed " + habit.getLabel() + " "
                + i + " times in: " + inputYear + "." + inputMonth);


    }


    // Mood Control
    private void doMood() {

        boolean keepGoing = true;
        input = new Scanner(System.in);
        while (keepGoing) {

            System.out.println("Select Mood function:");
            String s = input.next();
            switch (s) {
                case "today":
                    setTodayMood();
                    break;
                case "modify":
                    setMood();
                    break;
                case "remove":
                    removeMood();
                    break;
                case "view":
                    viewMood();
                    break;
                case "month":
                    viewMonthMood();
                    break;
                case "q":
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Selection not valid...");
                    break;
            }
        }
    }

    // set today's mood
    private void setTodayMood() {
        input = new Scanner(System.in);
        System.out.println("What's your mood today:");
        String commend = input.next();


        dayset.getDay(today).setMood(selectMood(commend));
    }


    // set mood to one particular date
    private void setMood() {
        input = new Scanner(System.in);
        System.out.println("Add mood to day: ");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        commend = input.next();
        int inputDay = Integer.parseInt(commend);
        Date moodDate = new Date(inputYear, inputMonth, inputDay);


        System.out.println("What's your mood:");
        commend = input.next();


        dayset.getDay(moodDate).setMood(selectMood(commend));
    }

    // select Mood
    private Mood selectMood(String commend) {

        Mood mood = Mood.Default;
        switch (commend) {
            case "1":
                mood = Mood.Cheerful;
                break;
            case "2":
                mood = Mood.Calm;
                break;
            case "3":
                mood = Mood.Angry;
                break;
            case "4":
                mood = Mood.Depressed;
                break;
            case "5":
                mood = Mood.Energetic;
                break;
            case "6":
                mood = Mood.Sad;
                break;
        }
        return mood;

    }

    // remove mood on one particular day
    private void removeMood() {

        input = new Scanner(System.in);
        System.out.println("Remove mood in date: ");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        commend = input.next();
        int inputDay = Integer.parseInt(commend);
        Date moodDate = new Date(inputYear, inputMonth, inputDay);

        dayset.getDay(moodDate).removeMood();
    }

    // view a mood in a particular day
    private void viewMood() {

        input = new Scanner(System.in);
        System.out.println("View mood in date: ");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        commend = input.next();
        int inputDay = Integer.parseInt(commend);
        Date moodDate = new Date(inputYear, inputMonth, inputDay);

        System.out.println("Your mood in that day is: " + dayset.getDay(moodDate).getMood().name());
    }

    // view the statistic data in one particular month
    private void viewMonthMood() {

        input = new Scanner(System.in);
        System.out.println("View all moods in month: ");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        System.out.println("Your have " + dayset.countMood(Mood.Cheerful, inputYear, inputMonth) + " cheerful days.");
        System.out.println("Your have " + dayset.countMood(Mood.Calm, inputYear, inputMonth) + " calm days.");
        System.out.println("Your have " + dayset.countMood(Mood.Angry, inputYear, inputMonth) + " angry days.");
        System.out.println("Your have " + dayset.countMood(Mood.Depressed, inputYear, inputMonth) + " depressed days.");
        System.out.println("Your have " + dayset.countMood(Mood.Energetic, inputYear, inputMonth) + " energetic days.");
        System.out.println("Your have " + dayset.countMood(Mood.Sad, inputYear, inputMonth) + " sad days.");
    }


    //Event control
    public void doEvent() {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        while (keepGoing) {

            System.out.println("Select TodoEvent function:");
            String s = input.next();
            switch (s) {
                case "set":
                    setEvent();
                    break;
                case "view":
                    viewEvent();
                    break;
                case "remove":
                    removeEvent();
                    break;
                case "edit":
                    editEventTime();
                    break;
                case "q":
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Selection not valid...");
                    break;
            }
        }

    }

    // add an event in a particular day and particular time
    private void setEvent() {
        input = new Scanner(System.in);
        System.out.println("Enter Event Year:");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        System.out.println("Enter Event Month:");
        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        System.out.println("Enter Event Date:");
        commend = input.next();
        int inputDay = Integer.parseInt(commend);

        Date eventDate = new Date(inputYear, inputMonth, inputDay);

        System.out.println("Enter Event Name:");
        input = new Scanner(System.in);
        String l = input.nextLine();

        System.out.println("Enter Event Time hour:");
        commend = input.next();
        int inputHour = Integer.parseInt(commend);

        System.out.println("Enter Event Time minute:");
        commend = input.next();
        int inputMin = Integer.parseInt(commend);

        dayset.getDay(eventDate).addEvent(new TodoEvent(eventDate, l, inputHour, inputMin));
    }

    // view all todoevent in a particular day
    private void viewEvent() {
        input = new Scanner(System.in);
        System.out.println("Enter Event Year:");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        System.out.println("Enter Event Month:");
        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        System.out.println("Enter Event Date:");
        commend = input.next();
        int inputDay = Integer.parseInt(commend);
        Date eventDate = new Date(inputYear, inputMonth, inputDay);

        for (TodoEvent event : dayset.getDay(eventDate).getTodoEventList()) {
            System.out.println(event.getLabel());
            System.out.println(event.getHour() + ":" + event.getMin());
            System.out.println();
        }

    }

    // remove one particular event in given date and time
    private void removeEvent() {

        input = new Scanner(System.in);
        System.out.println("Enter Event Year:");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        System.out.println("Enter Event Month:");
        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        System.out.println("Enter Event Date:");
        commend = input.next();
        int inputDay = Integer.parseInt(commend);

        Date eventDate = new Date(inputYear, inputMonth, inputDay);

        System.out.println("Enter Event Time hour:");
        commend = input.next();
        int inputHour = Integer.parseInt(commend);

        System.out.println("Enter Event Time minute:");
        commend = input.next();
        int inputMin = Integer.parseInt(commend);

        dayset.getDay(eventDate).removeEvent(inputHour, inputMin);

    }

    // change an event time
    private void editEventTime() {

        input = new Scanner(System.in);
        System.out.println("Enter Event Year:");
        String commend = input.next();
        int inputYear = Integer.parseInt(commend);

        System.out.println("Enter Event Month:");
        commend = input.next();
        int inputMonth = Integer.parseInt(commend);

        System.out.println("Enter Event Date:");
        commend = input.next();
        int inputDay = Integer.parseInt(commend);

        Date eventDate = new Date(inputYear, inputMonth, inputDay);

        System.out.println("Enter Event Time hour:");
        commend = input.next();
        int inputHour = Integer.parseInt(commend);

        System.out.println("Enter Event Time minute:");
        commend = input.next();
        int inputMin = Integer.parseInt(commend);

        System.out.println("Enter New Event Time hour and minute:");
        commend = input.next();
        int newHour = Integer.parseInt(commend);

        commend = input.next();
        int newMin = Integer.parseInt(commend);

        dayset.getDay(eventDate).getEvent(inputHour, inputMin).setTime(newHour, newMin);
    }


}
