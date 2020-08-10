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

// The main frame controls 6 panel and implement the main panel
public class DaysApp extends JFrame implements ActionListener {
    private static final String DATE_FILE = "./data/dates.txt";
    private static final String ANNI_FILE = "./data/anniversary.txt";
    private static final String DIARY_FILE = "./data/diary.txt";
    private static final String MOOD_FILE = "./data/mood.txt";
    private static final String HABIT_FILE = "./data/habit.txt";
    private static final String EVENTS_FILE = "./data/events.txt";
    private static final String SETHABIT_FILE = "./data/sethabit.txt";
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 750;

    private DaySet dayset;
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

    private Dimension buttonDimension;

    // construct a main app
    public DaysApp() {
        super("Days");
        init();

        labelFont = new Font("", Font.ITALIC, 60);
        btnFont = new Font("", Font.BOLD, 20);
        buttonDimension = new Dimension(170,80);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocation(100, 100);


        initializePanel();

        initializeMain();
        initializeBack();

        setResizable(false);
        pack();
        setVisible(true);
    }

    // initialize all panels
    private void initializePanel() {
        anniPanel = new JPanel();
        diaryPanel = new JPanel();
        habitPanel = new JPanel();
        moodPanel = new JPanel();
        eventPanel = new JPanel();
    }

    // initialize back button
    private void initializeBack() {
        backButton = new JButton("Back");
        backButton.setFont(new Font("", Font.BOLD, 12));
        backButton.addActionListener(this);
    }

    // initialize main panel
    private void initializeMain() {
        main = new JPanel();
        main.setLayout(new BorderLayout(0, 0));

        setMainDisplay();
        setMainBtn();
        initializeSaveLoadBtn();


        main.setVisible(true);
        setContentPane(main);

    }

    // initialize the save and load button
    private void initializeSaveLoadBtn() {
        saveLoadPanel = new JPanel();
        saveLoadPanel.setLayout(new GridLayout(2, 1));
        saveLoadPanel.setPreferredSize(new Dimension(60, 200));

        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        saveButton.setFont(new Font("", Font.BOLD, 10));
        loadButton.setFont(new Font("", Font.BOLD, 10));

        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

        saveLoadPanel.add(saveButton);
        saveLoadPanel.add(loadButton);
        main.add(saveLoadPanel, BorderLayout.EAST);
    }

    // build the main display panel
    private void setMainDisplay() {
        mainDisplay = new JPanel();

        dayLabel = new JLabel(" Days");
        dayLabel.setFont(new Font("", Font.ITALIC, 130));
        main.add(dayLabel, BorderLayout.NORTH);

        mainDisplay.setLayout(new BoxLayout(mainDisplay, 1));
        label = new JLabel("Today is:");
        label.setFont(labelFont);
        mainDisplay.add(label, BoxLayout.X_AXIS);

        JLabel todayLabel = new JLabel("              "
                + today.getMonth() + "." + today.getDay());
        todayLabel.setFont(new Font("", Font.PLAIN, 100));
        mainDisplay.add(todayLabel, BoxLayout.Y_AXIS);

        JLabel yearLabel = new JLabel("                          ----"
                + today.getYear());
        yearLabel.setFont(new Font("", Font.ITALIC, 80));
        mainDisplay.add(yearLabel, BoxLayout.LINE_AXIS);

        main.add(mainDisplay, BorderLayout.CENTER);
    }

    // set main buttons in main menu
    private void setMainBtn() {
        mainBtn = new JPanel();
        mainBtn.setLayout(new FlowLayout());

        setMainBtnHelper();
        anniButton.addActionListener(this);
        diaryButton.addActionListener(this);
        habitButton.addActionListener(this);
        moodButton.addActionListener(this);
        eventButton.addActionListener(this);

        mainBtn.add(anniButton, FlowLayout.LEFT);
        mainBtn.add(diaryButton, FlowLayout.CENTER);
        mainBtn.add(habitButton, FlowLayout.RIGHT);
        mainBtn.add(moodButton, FlowLayout.LEADING);
        mainBtn.add(eventButton, FlowLayout.TRAILING);

        main.add(mainBtn, BorderLayout.SOUTH);

    }

    // initialize all button's setting
    private void setMainBtnHelper() {
        anniButton = new JButton("Anniversary");
        anniButton.setPreferredSize(buttonDimension);
        anniButton.setFont(btnFont);

        diaryButton = new JButton("Diary");
        diaryButton.setPreferredSize(buttonDimension);
        diaryButton.setFont(btnFont);

        habitButton = new JButton("Habit");
        habitButton.setPreferredSize(buttonDimension);
        habitButton.setFont(btnFont);

        moodButton = new JButton("Mood");
        moodButton.setPreferredSize(buttonDimension);
        moodButton.setFont(btnFont);

        eventButton = new JButton("Event");
        eventButton.setPreferredSize(buttonDimension);
        eventButton.setFont(btnFont);
    }

    // perform something after any event happens
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == anniButton) {
            main.setVisible(false);
            anniPanel = new AnniversaryApp(dayset, today);
            anniPanel.add(backButton, BorderLayout.EAST);
            anniPanel.setVisible(true);
            setContentPane(anniPanel);
        } else if (source == diaryButton) {
            main.setVisible(false);
            diaryPanel = new DiaryApp(dayset, today);
            diaryPanel.add(backButton, BorderLayout.EAST);
            diaryPanel.setVisible(true);
            setContentPane(diaryPanel);
        } else if (source == habitButton) {
            main.setVisible(false);
            habitPanel = new HabitApp(dayset, today);
            habitPanel.add(backButton, BorderLayout.EAST);
            habitPanel.setVisible(true);
            setContentPane(habitPanel);
        } else {
            actionPerformHelper(source);
        }
    }

    // another perform function helper
    private void actionPerformHelper(Object source) {
        if (source == moodButton) {
            main.setVisible(false);
            moodPanel = new MoodApp(dayset, today);
            moodPanel.add(backButton, BorderLayout.EAST);
            moodPanel.setVisible(true);
            setContentPane(moodPanel);
        } else if (source == eventButton) {
            main.setVisible(false);
            eventPanel = new EventApp(dayset, today);
            eventPanel.add(backButton, BorderLayout.EAST);
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

        } catch (IOException e) {
            init();
        }
    }

    // Additional function created due to checkstyle
    private void initLoading(DaySet savedDayset, HabitList setHabitList) {
        savedDayset.setSetHabitList(setHabitList);
        dayset = savedDayset;
    }

    // EFFECTS: saves state of days and their components to XXXX_FILE
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

    // save days helper(checkstyle)
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

    // initialize the days app
    private void init() {
        setToday();
        dayset = new DaySet();
        dayset.getDay(today);
    }

}
