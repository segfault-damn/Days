package ui;

import exceptions.DateErrorException;
import model.Date;
import model.DaySet;
import model.entries.Mood;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class MoodApp extends JPanel implements ActionListener {
    private DaySet daySet;
    private Date today;

    private JLabel message;
    private JLabel title;

    private JPanel moodDisplay;
    private JPanel moodBtn;
    private JPanel datePanel;
    private JPanel monthPanel;
    private JPanel setMoodPanel;

    private Font labelFont;
    private Font btnFont;
    private Font contentFont;

    private JLabel moodLabel;
    private JLabel imageAsLabel;

    private JButton setBtn;
    private JButton removeBtn;
    private JButton viewBtn;
    private JButton monthBtn;

    private JButton confirmRemove;
    private JButton confirmView;
    private JButton confirmMonth;

    private JButton calm;
    private JButton cheerful;
    private JButton angry;
    private JButton depressed;
    private JButton energetic;
    private JButton sad;

    private JTextField dateField1;
    private JTextField dateField2;
    private JTextField dateField3;

    private ImageIcon cheerfulImage;
    private ImageIcon calmImage;
    private ImageIcon angryImage;
    private ImageIcon depressedImage;
    private ImageIcon energeticImage;
    private ImageIcon sadImage;





    public MoodApp(DaySet dayset,Date today) {
        this.daySet = dayset;

        labelFont = new Font("",Font.ITALIC,150);
        btnFont = new Font("",Font.BOLD,45);
        contentFont = new Font("",Font.PLAIN,80);

        message = new JLabel("");
        title = new JLabel("");

        this.daySet = dayset;
        this.today = today;

        runMood();

    }

    private void runMood() {
        generateDateInput();
        initializeMood();
        setVisible(true);
    }

    private void initializeMood() {
        setLayout(new BorderLayout());

        moodLabel = new JLabel("Mood: ");
        moodLabel.setFont(labelFont);

        moodDisplay = new JPanel();
        moodDisplay.setLayout(new BoxLayout(moodDisplay,1));

        loadImages();
        initializeDisplay();
        setMoodBtn();

        add(moodLabel,BorderLayout.NORTH);
        add(moodDisplay,BorderLayout.CENTER);
        add(moodBtn,BorderLayout.SOUTH);
    }

    private void initializeDisplay() {

        moodDisplay.removeAll();
        title("Select Function: ");
        message(" ");
        moodDisplay.updateUI();
    }

    private void setMoodBtn() {
        moodBtn = new JPanel();
        moodBtn.setLayout(new FlowLayout());

        setBtn = new JButton("Set");
        setBtn.setFont(btnFont);
        setBtn.setPreferredSize(new Dimension(350,200));

        removeBtn = new JButton("Remove");
        removeBtn.setFont(btnFont);
        removeBtn.setPreferredSize(new Dimension(350,200));

        viewBtn = new JButton("View");
        viewBtn.setFont(btnFont);
        viewBtn.setPreferredSize(new Dimension(350,200));

        monthBtn = new JButton("Month");
        monthBtn.setFont(btnFont);
        monthBtn.setPreferredSize(new Dimension(350,200));



        moodBtn.add(setBtn,FlowLayout.LEFT);
        moodBtn.add(removeBtn,FlowLayout.CENTER);
        moodBtn.add(viewBtn,FlowLayout.RIGHT);
        moodBtn.add(monthBtn,FlowLayout.LEADING);

        setBtn.addActionListener(this);
        removeBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        monthBtn.addActionListener(this);
    }

    private void title(String s) {
        moodDisplay.remove(title);
        title = new JLabel(s);
        title.setFont(contentFont);

        moodDisplay.add(title,BoxLayout.X_AXIS);
        moodDisplay.updateUI();
    }

    private void message(String s) {
        moodDisplay.remove(message);
        message = new JLabel(s);

        message.setFont(contentFont);

        moodDisplay.add(message,BoxLayout.Y_AXIS);
        moodDisplay.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == setBtn) {
            initializeDisplay();
            setMood();
        } else if (source == removeBtn) {
            initializeDisplay();
            removeMood();
        } else if (source == viewBtn) {
            initializeDisplay();
            viewMood();
        } else if (source == monthBtn) {
            initializeDisplay();
            monthMood();
        } else if (source == confirmRemove) {
            removeMoodPerform();
            moodDisplay.remove(confirmRemove);
        } else if (source == confirmView) {
            viewMoodPerform();
            moodDisplay.remove(datePanel);
            moodDisplay.remove(confirmView);
        } else if (source == confirmMonth) {
            monthPerform();
            moodDisplay.remove(monthPanel);
            moodDisplay.remove(confirmMonth);
        } else {
            performMoodButton(source);
        }
    }

    private void performMoodButton(Object source) {
        title("Select Function: ");
        if (source == cheerful) {
            message("Your mood today is cheerful!");
            daySet.getDay(today).setMood(Mood.Cheerful);
        } else if (source == calm) {
            message("Your mood today is calm!");
            daySet.getDay(today).setMood(Mood.Calm);
        } else if (source == angry) {
            message("Your mood today is angry!");
            daySet.getDay(today).setMood(Mood.Angry);
        } else if (source == depressed) {
            message("Your mood today is depressed!");
            daySet.getDay(today).setMood(Mood.Depressed);
        } else if (source == energetic) {
            message("Your mood today is energetic!");
            daySet.getDay(today).setMood(Mood.Energetic);
        } else if (source == sad) {
            message("Your mood today is sad!");
            daySet.getDay(today).setMood(Mood.Sad);
        }
        moodDisplay.remove(setMoodPanel);
    }


    // set today's mood
    private void setMood() {
        title("How are you feeling today?");
        message("Today is: " + today.getYear() + "/" + today.getMonth() + "/" + today.getDay());

        setMoodPanel = new JPanel(new GridLayout(2,3));
        setMoodPanel.setSize(new Dimension(1000,500));

        initializeMoodButton();

        setMoodPanel.add(cheerful);
        setMoodPanel.add(calm);
        setMoodPanel.add(angry);
        setMoodPanel.add(depressed);
        setMoodPanel.add(energetic);
        setMoodPanel.add(sad);
        moodDisplay.add(setMoodPanel,BoxLayout.LINE_AXIS);

        moodDisplay.updateUI();
    }

    private void initializeMoodButton() {
        cheerful = new JButton("Cheerful");
        cheerful.setFont(btnFont);
        cheerful.setPreferredSize(new Dimension(350,200));

        calm = new JButton("Calm");
        calm.setFont(btnFont);
        calm.setPreferredSize(new Dimension(350,200));

        angry = new JButton("Angry");
        angry.setFont(btnFont);
        angry.setPreferredSize(new Dimension(350,200));

        depressed = new JButton("Depressed");
        depressed.setFont(btnFont);
        depressed.setPreferredSize(new Dimension(350,200));

        energetic = new JButton("Energetic");
        energetic.setFont(btnFont);
        energetic.setPreferredSize(new Dimension(350,200));

        sad = new JButton("Sad");
        sad.setFont(btnFont);
        sad.setPreferredSize(new Dimension(350,200));

        addMoodsListener();
    }

    private void addMoodsListener() {
        cheerful.addActionListener(this);
        calm.addActionListener(this);
        angry.addActionListener(this);
        depressed.addActionListener(this);
        energetic.addActionListener(this);
        sad.addActionListener(this);
    }

    // remove the select date's mood
    private void removeMood() {
        title("Remove today's mood?");
        message(" ");
        removeConfirm();
        moodDisplay.add(confirmRemove,BoxLayout.LINE_AXIS);

        moodDisplay.updateUI();
    }

    private void removeConfirm() {
        confirmRemove = new JButton("Confirm");
        confirmRemove.setFont(btnFont);
        confirmRemove.setPreferredSize(new Dimension(350,200));
        confirmRemove.addActionListener(this);
    }

    private void viewMood() {
        title("View Mood on date:");
        message(" ");

        moodDisplay.add(datePanel,BoxLayout.LINE_AXIS);

        viewConfirm();
        moodDisplay.add(confirmView,BoxLayout.PAGE_AXIS);
        moodDisplay.updateUI();
    }

    private void viewConfirm() {
        confirmView = new JButton("Confirm");
        confirmView.setFont(btnFont);
        confirmView.setPreferredSize(new Dimension(350,200));
        confirmView.addActionListener(this);
    }

    private void monthMood() {
        title("View moods!");
        message("Enter Month: ");

        generateMonthInput();
        moodDisplay.add(monthPanel,BoxLayout.LINE_AXIS);

        monthConfirm();
        moodDisplay.add(confirmMonth,BoxLayout.PAGE_AXIS);

        moodDisplay.updateUI();
    }

    private void monthConfirm() {
        confirmMonth = new JButton("Confirm");
        confirmMonth.setFont(btnFont);
        confirmMonth.setPreferredSize(new Dimension(350,200));
        confirmMonth.addActionListener(this);
    }

    private void generateDateInput() {
        datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout());

        String year = Integer.toString(today.getYear());
        String month = Integer.toString(today.getMonth());
        String date = Integer.toString(today.getDay());
        dateField1 = new JTextField(year,4);
        datePanel.add(dateField1,FlowLayout.LEFT);
        dateField1.setFont(labelFont);

        dateField2 = new JTextField(month,2);
        datePanel.add(dateField2,FlowLayout.CENTER);
        dateField2.setFont(labelFont);

        dateField3 = new JTextField(date,2);
        datePanel.add(dateField3,FlowLayout.RIGHT);
        dateField3.setFont(labelFont);
    }

    private void generateMonthInput() {
        monthPanel = new JPanel();
        monthPanel.setLayout(new FlowLayout());
        String year = Integer.toString(today.getYear());
        String month = Integer.toString(today.getMonth());

        dateField1 = new JTextField(year,4);
        monthPanel.add(dateField1,FlowLayout.LEFT);
        dateField1.setFont(labelFont);

        dateField2 = new JTextField(month,2);
        monthPanel.add(dateField2,FlowLayout.CENTER);
        dateField2.setFont(labelFont);
    }

    // modify the diary in the given day
    private void removeMoodPerform() {
        title("Select Function:");
        daySet.getDay(today).removeMood();
        message("Your mood has been removed.");
    }

    // view mood of given date
    private void viewMoodPerform() {
        title("Select Function: ");
        try {
            int inputYear = Integer.parseInt(dateField1.getText());
            int inputMonth = Integer.parseInt(dateField2.getText());
            int inputDate = Integer.parseInt(dateField3.getText());

            Date moodDate = new Date(inputYear, inputMonth, inputDate);

            message("Your mood on " + inputYear + "." + inputMonth + "." + inputDate
                    + " is: " + daySet.getDay(moodDate).getMood().name());
            imageAsLabel = new JLabel();
            switch (daySet.getDay(moodDate).getMood().name()) {
                case "Cheerful":
                    imageAsLabel = new JLabel(cheerfulImage);
                    break;
                case "Calm":
                    imageAsLabel = new JLabel(calmImage);
                    break;
                case "Angry":
                    imageAsLabel = new JLabel(angryImage);
                    break;
                case "Depressed":
                    imageAsLabel = new JLabel(depressedImage);
                    break;
                case "Energetic":
                    imageAsLabel = new JLabel(energeticImage);
                    break;
                case "Sad":
                    imageAsLabel = new JLabel(sadImage);
                    break;
            }
            moodDisplay.add(imageAsLabel,BoxLayout.LINE_AXIS);

        } catch (DateErrorException | NumberFormatException e) {
            message("Date Entered is invalid");
        }
    }

    // view the statistic data in one particular month
    private void monthPerform() {
        title("Your mood");
        try {
            int inputYear = Integer.parseInt(dateField1.getText());
            int inputMonth = Integer.parseInt(dateField2.getText());

            JTextArea contents = new JTextArea(10, 400);
            contents.setFont(contentFont);
            contents.setEditable(false);
            contents.setText("Your have " + daySet.countMood(Mood.Cheerful, inputYear, inputMonth) + " cheerful days."
                    + "\n" + "Your have " + daySet.countMood(Mood.Calm, inputYear, inputMonth) + " calm days."
                    + "\n" + "Your have " + daySet.countMood(Mood.Angry, inputYear, inputMonth) + " angry days."
                    + "\n" + "Your have " + daySet.countMood(Mood.Depressed, inputYear, inputMonth) + " depressed days."
                    + "\n" + "Your have " + daySet.countMood(Mood.Energetic, inputYear, inputMonth) + " energetic days."
                    + "\n" + "Your have " + daySet.countMood(Mood.Sad, inputYear, inputMonth) + " sad days.");

            message(inputYear + "." + inputMonth);
            moodDisplay.add(contents, BoxLayout.LINE_AXIS);
            moodDisplay.updateUI();
        } catch (DateErrorException | NumberFormatException e) {
            message("Date Entered is invalid");
        }
    }

    private void loadImages() {
        String sep = System.getProperty("file.separator");
        cheerfulImage = new ImageIcon(System.getProperty("user.dir") + sep + "lib" + sep
                + "images" + sep + "cheerful.jpg");
        depressedImage = new ImageIcon(System.getProperty("user.dir") + sep + "lib" + sep
                + "images" + sep + "depressed.jpg");
        calmImage = new ImageIcon(System.getProperty("user.dir") + sep + "lib" + sep
                + "images" + sep + "calm.jpg");
        angryImage = new ImageIcon(System.getProperty("user.dir") + sep + "lib" + sep
                + "images" + sep + "angry.png");
        sadImage = new ImageIcon(System.getProperty("user.dir") + sep + "lib" + sep
                + "images" + sep + "sad.png");
        energeticImage = new ImageIcon(System.getProperty("user.dir") + sep + "lib" + sep
                + "images" + sep + "energetic.jpg");
    }
}
