package ui;

import exceptions.DateErrorException;
import model.Date;
import model.Day;
import model.DaySet;
import model.entries.*;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import static javax.swing.SwingConstants.LEFT;

// The main app
public class DaysApp extends JFrame implements ActionListener {
    private static final String DATE_FILE = "./data/dates.txt";
    private static final String ANNI_FILE = "./data/anniversary.txt";
    private static final String DIARY_FILE = "./data/diary.txt";
    private static final String MOOD_FILE = "./data/mood.txt";
    private static final String HABIT_FILE = "./data/habit.txt";
    private static final String EVENTS_FILE = "./data/events.txt";
    private static final String SETHABIT_FILE = "./data/sethabit.txt";
    public static final int WIDTH = 2000;
    public static final int HEIGHT = 1600;


    private DaySet dayset;
    private Scanner input;
    private Date today;

    private JLabel dayLabel;
    private JLabel label;

    private JPanel mainDisplay;
    private JPanel main;
    private JPanel mainBtn;
    private JPanel anniPanel;
    private JPanel diaryPanel;
    private JPanel habitPanel;
    private JPanel moodPanel;
    private JPanel eventPanel;

    private JPanel saveLoadPanel;

    private JButton anniButton;
    private JButton diaryButton;
    private JButton moodButton;
    private JButton eventButton;
    private JButton habitButton;
    private JButton backButton;
    private JButton saveButton;
    private JButton loadButton;

    private Font labelFont;
    private Font btnFont;

    public DaysApp() {
        super("Days");
        init();

        labelFont = new Font("",Font.ITALIC,150);
        btnFont = new Font("",Font.BOLD,45);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setLocation(100,100);


        initializePanel();

        initializeMain();
        initializeBack();

        setResizable(false);
        pack();
        setVisible(true);
    }

    private void initializePanel() {
        anniPanel = new JPanel();
        diaryPanel = new JPanel();
        habitPanel = new JPanel();
        moodPanel = new JPanel();
        eventPanel = new JPanel();
    }

    private void initializeBack() {
        backButton = new JButton("Back");
        backButton.setFont(new Font("",Font.PLAIN,30));
        backButton.addActionListener(this);
    }

    private void initializeMain() {
        main = new JPanel();
        main.setLayout(new BorderLayout(0, 0));

        setMainDisplay();
        setMainBtn();
        initializeSaveLoadBtn();


        main.setVisible(true);
        setContentPane(main);

    }

    private void initializeAnniRemider() {
        label = new JLabel("Today is:");
        label.setFont(labelFont);
        for (Day day : dayset.getDays()) {
            if (day.getAnniversary().getDate().getDay() == today.getDay()
                    && day.getAnniversary().getDate().getMonth() == today.getMonth()
                    && day.getAnniversary().getIsAnniversary()) {
                label.setText("Today is:  " + day.getAnniversary().getLabel());
            }
        }
    }

    private void initializeSaveLoadBtn() {
        saveLoadPanel = new JPanel();
        saveLoadPanel.setLayout(new GridLayout(2,1));
        saveLoadPanel.setPreferredSize(new Dimension(100,500));

        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        saveButton.setFont(new Font("",Font.BOLD,25));
        loadButton.setFont(new Font("",Font.BOLD,25));

        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

        saveLoadPanel.add(saveButton);
        saveLoadPanel.add(loadButton);
        main.add(saveLoadPanel,BorderLayout.EAST);
    }


    private void setMainDisplay() {
        mainDisplay = new JPanel();

        dayLabel = new JLabel(" Days");
        dayLabel.setFont(new Font("",Font.ITALIC,250));
        main.add(dayLabel,BorderLayout.NORTH);

        mainDisplay.setLayout(new BoxLayout(mainDisplay,1));
        initializeAnniRemider();
        mainDisplay.add(label,BoxLayout.X_AXIS);

        JLabel todayLabel = new JLabel("         "
                + today.getMonth() + "." + today.getDay());
        todayLabel.setFont(new Font("",Font.PLAIN,300));
        mainDisplay.add(todayLabel,BoxLayout.Y_AXIS);

        JLabel yearLabel = new JLabel("                        ----"
                + today.getYear());
        yearLabel.setFont(new Font("",Font.ITALIC,150));
        mainDisplay.add(yearLabel,BoxLayout.LINE_AXIS);

        main.add(mainDisplay,BorderLayout.CENTER);
    }

    private void setMainBtn() {
        mainBtn = new JPanel();
        mainBtn.setLayout(new FlowLayout());

        anniButton = new JButton("Anniversary");
        anniButton.setPreferredSize(new Dimension(350,200));
        anniButton.setFont(btnFont);

        diaryButton = new JButton("Diary");
        diaryButton.setPreferredSize(new Dimension(350,200));
        diaryButton.setFont(btnFont);

        habitButton = new JButton("Habit");
        habitButton.setPreferredSize(new Dimension(350,200));
        habitButton.setFont(btnFont);

        moodButton = new JButton("Mood");
        moodButton.setPreferredSize(new Dimension(350,200));
        moodButton.setFont(btnFont);

        eventButton = new JButton("Event");
        eventButton.setPreferredSize(new Dimension(350,200));
        eventButton.setFont(btnFont);


        anniButton.addActionListener(this);
        diaryButton.addActionListener(this);
        habitButton.addActionListener(this);
        moodButton.addActionListener(this);
        eventButton.addActionListener(this);

        mainBtn.add(anniButton,FlowLayout.LEFT);
        mainBtn.add(diaryButton,FlowLayout.CENTER);
        mainBtn.add(habitButton,FlowLayout.RIGHT);
        mainBtn.add(moodButton,FlowLayout.LEADING);
        mainBtn.add(eventButton,FlowLayout.TRAILING);

        main.add(mainBtn,BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == anniButton) {
            main.setVisible(false);
            anniPanel = new AnniversaryApp(dayset,today);
            anniPanel.add(backButton,BorderLayout.EAST);
            anniPanel.setVisible(true);
            setContentPane(anniPanel);
        } else if (source == diaryButton) {
            main.setVisible(false);
            diaryPanel = new DiaryApp(dayset,today);
            diaryPanel.add(backButton,BorderLayout.EAST);
            diaryPanel.setVisible(true);
            setContentPane(diaryPanel);
        } else if (source == habitButton) {
            main.setVisible(false);
            habitPanel = new HabitApp(dayset);
            habitPanel.add(backButton,BorderLayout.EAST);
            habitPanel.setVisible(true);
            setContentPane(habitPanel);
        } else if (source == moodButton) {
            main.setVisible(false);
            moodPanel = new MoodApp(dayset,today);
            moodPanel.add(backButton,BorderLayout.EAST);
            moodPanel.setVisible(true);
            setContentPane(moodPanel);
        } else if (source == eventButton) {
            main.setVisible(false);
            eventPanel = new EventApp(dayset);
            eventPanel.add(backButton,BorderLayout.EAST);
            eventPanel.setVisible(true);
            setContentPane(eventPanel);
        } else if (source == backButton) {
            setContentPane(main);
            main.setVisible(true);
        } else if (source == saveButton) {
            saveDays();
        } else if (source == loadButton) {
            loadDays();
        }
    }

    // grab today's yyyy-MM-dd
    private void setToday() {
        int tdYear = Calendar.getInstance().get(Calendar.YEAR);
        int tdMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int tdDay = Calendar.getInstance().get(Calendar.DATE);

        try {
            today = new Date(tdYear, tdMonth, tdDay);
        } catch (DateErrorException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads accounts from XXXX_FILE, if that file exists;
    // otherwise initializes dayset with default values
    private void loadDays() {
        try {
            DaySet savedDayset = new DaySet();

            List<Date> dates = DateReader.readDates(new File(DATE_FILE));
            List<Anniversary> anniversaries = AnniversaryReader.readAnniversary(new File(ANNI_FILE));
            List<Diary> diaries = DiaryReader.readDiary(new File(DIARY_FILE));
            List<Mood> moods = MoodReader.readMood(new File(MOOD_FILE));
            List<HabitList> habitLists = HabitListReader.readHabitLists(new File(HABIT_FILE));
            List<List<TodoEvent>> todoEventLists = TodoEventReader.readEvent(new File(EVENTS_FILE));

            HabitList setHabitList = SetHabitListReader.readHabit(new File(SETHABIT_FILE));

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

            initLoading(savedDayset, setHabitList);
            System.out.println("Days has been loaded");

        } catch (IOException e) {
            init();
            System.out.println("No record find! Create a new days");
        }
    }

    // Additional function created due to checkstyle
    private void initLoading(DaySet savedDayset, HabitList setHabitList) {
        savedDayset.setSetHabitList(setHabitList);
        dayset = savedDayset;
    }

    // EFFECTS: saves state of chequing and savings accounts to XXXX_FILE
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

            saveDaysHelper(dateWriter, anniWriter, diaryWriter, moodWriter,
                    habitWriter, todoEventWriter, setHabitWriter);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to save file...");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    private void saveDaysHelper(Writer dateWriter, Writer anniWriter, Writer diaryWriter, Writer moodWriter,
                                Writer habitWriter, Writer todoEventWriter, Writer setHabitWriter) {
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
    }

    private void init() {
        setToday();
        dayset = new DaySet();
        dayset.getDay(today);
    }



    // Habit Control
    private void doHabit() {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        while (keepGoing) {
            System.out.println("Select Habit function:");
            String s = input.next();
            if (s.equals("q")) {
                keepGoing = false;
            } else {
                doHabitHelper(s);
            }

        }
    }

    //checkstyle
    private void doHabitHelper(String s) {

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
            default:
                System.out.println("Selection not valid...");
                break;
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

        try {
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
        } catch (DateErrorException e) {
            System.out.println("Date Entered is invalid");
        }

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

        try {
            Date habitDate = new Date(inputYear, inputMonth, inputDay);

            viewDateHabit(habitDate);
        } catch (DateErrorException e) {
            System.out.println("Date Entered is invalid");
        }
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





    //Event control
    public void doEvent() {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        while (keepGoing) {

            System.out.println("Select TodoEvent function:");
            String s = input.next();
            if (s.equals("q")) {
                keepGoing = false;
            } else {
                doEventHelper(s);
            }

        }

    }

    //checkstyle
    private void doEventHelper(String s) {

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
            default:
                System.out.println("Selection not valid...");
                break;
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

        try {
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
        } catch (DateErrorException e) {
            System.out.println("Date Entered is invalid");
        }
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
        try {
            Date eventDate = new Date(inputYear, inputMonth, inputDay);

            for (TodoEvent event : dayset.getDay(eventDate).getTodoEventList()) {
                System.out.println(event.getLabel());
                System.out.println(event.getHour() + ":" + event.getMin());
                System.out.println();
            }
        } catch (DateErrorException e) {
            System.out.println("Date Entered is invalid");
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

        try {
            Date eventDate = new Date(inputYear, inputMonth, inputDay);

            System.out.println("Enter Event Time hour:");
            commend = input.next();
            int inputHour = Integer.parseInt(commend);

            System.out.println("Enter Event Time minute:");
            commend = input.next();
            int inputMin = Integer.parseInt(commend);

            dayset.getDay(eventDate).removeEvent(inputHour, inputMin);
        } catch (DateErrorException e) {
            System.out.println("Date Entered is invalid");
        }

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

        try {
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
        } catch (DateErrorException e) {
            System.out.println("Date Entered is invalid");
        }
    }
}
