package ui;

import exceptions.DateErrorException;
import model.Date;
import model.DaySet;
import model.entries.Habit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// a habit app
public class HabitApp extends JPanel implements ActionListener {
    private DaySet daySet;
    private Date today;

    private Font labelFont;
    private Font btnFont;
    private Font contentFont;

    private JLabel habitLabel;

    private JPanel habitDisplay;
    private JPanel habitBtn;
    private JPanel datePanel;
    private JPanel monthPanel;

    private JButton markBtn;
    private JButton viewBtn;
    private JButton editBtn;
    private JButton monthBtn;

    private JButton confirmAdd;
    private JButton confirmRemove;
    private JButton confirmMark;
    private JButton confirmDate;
    private JButton confirmView;
    private JButton confirmEdit;
    private JButton confirmMonth;

    private JLabel message;
    private JLabel title;

    private JTextField dateField1;
    private JTextField dateField2;
    private JTextField dateField3;

    private JScrollPane habitsPane;

    private JTextField habitName;
    private JTextField newHabitName;

    private JMenuBar listBar;
    private JMenu listMenu;
    private JMenuItem addList;
    private JMenuItem removeList;

    private int markInputYear;
    private int markInputMonth;
    private int markInputDate;
    private Dimension buttonDimension;

    // construct a new habit panel
    public HabitApp(DaySet dayset, Date today) {
        this.daySet = dayset;
        this.today = today;

        labelFont = new Font("", Font.ITALIC, 60);
        btnFont = new Font("", Font.BOLD, 20);
        contentFont = new Font("", Font.PLAIN, 40);
        buttonDimension = new Dimension(170,80);

        message = new JLabel("");
        title = new JLabel("");

        runHabit();
    }

    // run habit app
    private void runHabit() {
        generateDateInput();
        initializeHabit();
        setVisible(true);
    }

    // initialize a habit panel
    private void initializeHabit() {
        setLayout(new BorderLayout());

        habitLabel = new JLabel("Habit");
        habitLabel.setFont(labelFont);

        habitDisplay = new JPanel();
        habitDisplay.setLayout(new BoxLayout(habitDisplay, 1));

        initializeDisplay();
        setHabitBtn();
        generateDailyHabits(today);
        habitDisplay.add(habitsPane, BoxLayout.LINE_AXIS);
        message("Today");

        add(habitLabel, BorderLayout.NORTH);
        add(habitDisplay, BorderLayout.CENTER);
        add(habitBtn, BorderLayout.SOUTH);
    }

    // initialize habit panel display
    private void initializeDisplay() {

        habitDisplay.removeAll();
        title("Select Function: ");
        message(" ");

        habitDisplay.updateUI();
    }

    // initialize habit function buttons
    private void setHabitBtn() {
        habitBtn = new JPanel();
        habitBtn.setLayout(new FlowLayout());

        generateListBar();
        markBtn = new JButton("Mark");
        markBtn.setFont(btnFont);
        markBtn.setPreferredSize(buttonDimension);

        viewBtn = new JButton("View");
        viewBtn.setFont(btnFont);
        viewBtn.setPreferredSize(buttonDimension);

        editBtn = new JButton("Edit");
        editBtn.setFont(btnFont);
        editBtn.setPreferredSize(buttonDimension);

        monthBtn = new JButton("Month");
        monthBtn.setFont(btnFont);
        monthBtn.setPreferredSize(buttonDimension);

        habitBtn.add(listBar, FlowLayout.LEFT);
        habitBtn.add(markBtn, FlowLayout.CENTER);
        habitBtn.add(viewBtn, FlowLayout.RIGHT);
        habitBtn.add(editBtn, FlowLayout.LEADING);
        habitBtn.add(monthBtn, FlowLayout.TRAILING);

        addListener();
    }

    // add listeners to habit buttons
    private void addListener() {
        addList.addActionListener(this);
        removeList.addActionListener(this);
        markBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        editBtn.addActionListener(this);
        monthBtn.addActionListener(this);
    }

    // generate a list bar consists of add and remove items
    private void generateListBar() {
        listBar = new JMenuBar();
        listBar.setBorderPainted(true);
        listBar.setPreferredSize(buttonDimension);
        listMenu = new JMenu("List");
        listMenu.setPreferredSize(buttonDimension);
        listMenu.setFont(btnFont);
        addList = new JMenuItem("Add");
        addList.setFont(btnFont);
        removeList = new JMenuItem("remove");
        removeList.setFont(btnFont);

        listBar.add(listMenu);
        listMenu.add(addList);
        listMenu.addSeparator();
        listMenu.add(removeList);
    }

    // build a title label
    private void title(String s) {
        habitDisplay.remove(title);
        title = new JLabel(s);
        title.setFont(contentFont);

        habitDisplay.add(title, BoxLayout.X_AXIS);
        habitDisplay.updateUI();
    }

    // build a message label
    private void message(String s) {
        habitDisplay.remove(message);
        message = new JLabel(s);
        message.setFont(contentFont);
        habitDisplay.add(message, BoxLayout.Y_AXIS);
        habitDisplay.updateUI();
    }

    // perform actions after buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == addList) {
            initializeDisplay();
            addHabit();
        } else if (source == removeList) {
            initializeDisplay();
            removeHabit();
        } else if (source == markBtn) {
            initializeDisplay();
            markHabitSelectDate();
        } else if (source == viewBtn) {
            initializeDisplay();
            viewHabit();
        } else if (source == editBtn) {
            initializeDisplay();
            editHabit();
        } else if (source == monthBtn) {
            initializeDisplay();
            monthHabit();
        } else {
            confirmPerformed(source);
        }
    }

    // perform events after confirm buttons are clicked
    public void confirmPerformed(Object source) {
        if (source == confirmAdd) {
            addHabitPerform();
            habitDisplay.remove(habitName);
            habitDisplay.remove(habitsPane);
            habitDisplay.remove(confirmAdd);
        } else if (source == confirmRemove) {
            removeHabitPerform();
            habitDisplay.remove(habitName);
            habitDisplay.remove(habitsPane);
            habitDisplay.remove(confirmRemove);
        } else if (source == confirmDate) {
            markHabit();
            habitDisplay.remove(datePanel);
            habitDisplay.remove(confirmDate);
        } else {
            confirmPerformHelper(source);
        }
    }

    // confirm perform helper
    private void confirmPerformHelper(Object source) {
        if (source == confirmMark) {
            markHabitPerform();
            habitDisplay.remove(habitsPane);
            habitDisplay.remove(habitName);
            habitDisplay.remove(confirmMark);
        } else if (source == confirmView) {
            viewHabitPerform();
            habitDisplay.remove(datePanel);
            habitDisplay.remove(confirmView);
        } else if (source == confirmEdit) {
            editPerform();
            habitDisplay.remove(habitName);
            habitDisplay.remove(newHabitName);
            habitDisplay.remove(confirmEdit);
        } else if (source == confirmMonth) {
            monthPerform();
            habitDisplay.remove(monthPanel);
            habitDisplay.remove(confirmMonth);
        }
    }

    // generate a habit list viewer panel
    private void generateHabits() {
        JTextArea viewArea = new JTextArea(5, 10);
        viewArea.setPreferredSize(new Dimension(400, 200));
        viewArea.setFont(new Font("", Font.PLAIN, 25));
        viewArea.setText("Your current habit list: \n");
        for (String s : daySet.getSetHabitList().getHabitLabel()) {
            viewArea.append(s + "\n");
        }
        viewArea.setEditable(false);
        habitsPane = new JScrollPane(viewArea);
        habitsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        habitsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        habitsPane.setPreferredSize(new Dimension(400, 200));
    }

    // generate a daily habit list viewer panel (habits in given date)
    private void generateDailyHabits(Date date) {
        JTextArea viewArea = new JTextArea(5, 10);
        viewArea.setPreferredSize(new Dimension(400, 200));
        viewArea.setFont(new Font("", Font.PLAIN, 25));
        viewArea.setText(" Daily Habit List: \n");
        for (Habit h : daySet.getDay(date).getDailyHabitList().getHabitList()) {
            if (h.getIsDone()) {
                viewArea.append(h.getLabel() + ": Done! \n");
            } else {
                viewArea.append(h.getLabel() + ": Not done! \n");
            }
        }
        viewArea.setEditable(false);
        habitsPane = new JScrollPane(viewArea);
        habitsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        habitsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        habitsPane.setPreferredSize(new Dimension(400, 200));
    }


    // add habit to habitlist
    private void addHabit() {

        title("Add your habit!");
        message("Enter habit's name: ");
        generateHabits();
        habitName = new JTextField("Name");
        habitName.setFont(contentFont);
        addConfirm();

        habitDisplay.add(habitsPane, BoxLayout.LINE_AXIS);
        habitDisplay.add(confirmAdd, BoxLayout.PAGE_AXIS);
        habitDisplay.add(habitName, BoxLayout.PAGE_AXIS);

    }

    // build add confirm button
    private void addConfirm() {
        confirmAdd = new JButton("Confirm");
        confirmAdd.setFont(btnFont);
        confirmAdd.setPreferredSize(buttonDimension);
        confirmAdd.addActionListener(this);
    }

    // remove one habit from habit list
    private void removeHabit() {

        title("Remove your habit!");
        message("Enter habit's name: ");
        generateHabits();
        habitName = new JTextField("Name");
        habitName.setFont(contentFont);
        removeConfirm();

        habitDisplay.add(habitsPane, BoxLayout.LINE_AXIS);
        habitDisplay.add(confirmRemove, BoxLayout.PAGE_AXIS);
        habitDisplay.add(habitName, BoxLayout.PAGE_AXIS);

    }

    // build remove confirm button
    private void removeConfirm() {
        confirmRemove = new JButton("Confirm");
        confirmRemove.setFont(btnFont);
        confirmRemove.setPreferredSize(buttonDimension);
        confirmRemove.addActionListener(this);
    }

    // flip the status of one selected habit.
    private void markHabitSelectDate() {
        title("Mark habit!");
        message("Enter the date you want to mark:");
        generateDateInput();
        selectDateConfirm();

        habitDisplay.add(datePanel, BoxLayout.LINE_AXIS);
        habitDisplay.add(confirmDate, BoxLayout.PAGE_AXIS);
    }

    // build the select date confirm button
    private void selectDateConfirm() {
        confirmDate = new JButton("Confirm");
        confirmDate.setFont(btnFont);
        confirmDate.setPreferredSize(buttonDimension);
        confirmDate.addActionListener(this);
    }


    // mark one selected habit
    private void markHabit() {

        try {
            title("Mark one habit as done or undone!");
            message("Enter habit name: ");
            markInputYear = Integer.parseInt(dateField1.getText());
            markInputMonth = Integer.parseInt(dateField2.getText());
            markInputDate = Integer.parseInt(dateField3.getText());

            Date markDate = new Date(markInputYear, markInputMonth, markInputDate);
            generateDailyHabits(markDate);
            habitName = new JTextField("Name");
            habitName.setFont(contentFont);

            markConfirm();
            habitDisplay.add(habitsPane, BoxLayout.LINE_AXIS);
            habitDisplay.add(confirmMark, BoxLayout.PAGE_AXIS);
            habitDisplay.add(habitName, BoxLayout.PAGE_AXIS);
            habitDisplay.updateUI();
        } catch (DateErrorException | NumberFormatException e) {
            title("Select Function: ");
            message("Date Entered is invalid");
        }
    }

    // build a mark confirm button
    private void markConfirm() {
        confirmMark = new JButton("Confirm");
        confirmMark.setFont(btnFont);
        confirmMark.setPreferredSize(buttonDimension);
        confirmMark.addActionListener(this);
    }

    // view habit in given date
    private void viewHabit() {
        title("View Habit!");
        message("Enter Date: ");
        generateDateInput();
        habitDisplay.add(datePanel, BoxLayout.LINE_AXIS);

        viewConfirm();
        habitDisplay.add(confirmView, BoxLayout.PAGE_AXIS);
        habitDisplay.updateUI();
    }

    // build view confirm button
    private void viewConfirm() {
        confirmView = new JButton("Confirm");
        confirmView.setFont(btnFont);
        confirmView.setPreferredSize(buttonDimension);
        confirmView.addActionListener(this);
    }

    // edit one selected habit's name
    private void editHabit() {
        title("Edit Habit's name!");
        message("Enter name: ");

        habitName = new JTextField("Name");
        habitName.setFont(contentFont);
        newHabitName = new JTextField("New name");
        newHabitName.setFont(contentFont);
        habitDisplay.add(habitName, BoxLayout.LINE_AXIS);

        editConfirm();
        habitDisplay.add(confirmEdit, BoxLayout.PAGE_AXIS);
        habitDisplay.add(newHabitName, BoxLayout.PAGE_AXIS);

        habitDisplay.updateUI();
    }

    // build edit confirm button
    private void editConfirm() {
        confirmEdit = new JButton("Confirm");
        confirmEdit.setFont(btnFont);
        confirmEdit.setPreferredSize(buttonDimension);
        confirmEdit.addActionListener(this);
    }

    // view habits result in one month
    private void monthHabit() {
        title("View your habits record!");
        message("Enter year and month: ");
        generateMonthInput();

        habitDisplay.add(monthPanel, BoxLayout.LINE_AXIS);

        monthConfirm();
        habitDisplay.add(confirmMonth, BoxLayout.PAGE_AXIS);
        habitDisplay.updateUI();
    }

    // build month confirm button
    private void monthConfirm() {
        confirmMonth = new JButton("Confirm");
        confirmMonth.setFont(btnFont);
        confirmMonth.setPreferredSize(buttonDimension);
        confirmMonth.addActionListener(this);
    }

    // generate date inputs panel
    private void generateDateInput() {
        datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout());

        String year = Integer.toString(today.getYear());
        String month = Integer.toString(today.getMonth());
        String date = Integer.toString(today.getDay());
        dateField1 = new JTextField(year, 4);
        datePanel.add(dateField1, FlowLayout.LEFT);
        dateField1.setFont(labelFont);

        dateField2 = new JTextField(month, 2);
        datePanel.add(dateField2, FlowLayout.CENTER);
        dateField2.setFont(labelFont);

        dateField3 = new JTextField(date, 2);
        datePanel.add(dateField3, FlowLayout.RIGHT);
        dateField3.setFont(labelFont);
    }

    // generate month input panel
    private void generateMonthInput() {
        monthPanel = new JPanel();
        monthPanel.setLayout(new FlowLayout());
        String year = Integer.toString(today.getYear());
        String month = Integer.toString(today.getMonth());

        dateField1 = new JTextField(year, 4);
        monthPanel.add(dateField1, FlowLayout.LEFT);
        dateField1.setFont(labelFont);

        dateField2 = new JTextField(month, 2);
        monthPanel.add(dateField2, FlowLayout.CENTER);
        dateField2.setFont(labelFont);
    }


    // add a new habit
    private void addHabitPerform() {
        title("Select Function: ");
        String s = habitName.getText();

        Habit habit = new Habit(s);
        daySet.addDailyHabitList(habit);
        message(s + " has been added to your habit list!");
    }

    // remove the habit with a given name
    private void removeHabitPerform() {
        title("Select Function: ");
        String s = habitName.getText();

        Habit habit = new Habit(s);
        daySet.removeDailyHabitList(habit);
        message(s + " has been removed from your habit list!");
    }

    // flip the state of one particular day
    private void markHabitPerform() {
        title("Select Function:");
        Date habitDate = new Date(markInputYear, markInputMonth, markInputDate);
        String s = habitName.getText();

        for (Habit h : daySet.getDay(habitDate).getDailyHabitList().getHabitList()) {
            if (h.getLabel().equals(s)) {
                h.flipDone();
                message(s + "has been changed");
            }
        }

    }

    // search the habit in a particular date
    private void viewHabitPerform() {

        try {
            int inputYear = Integer.parseInt(dateField1.getText());
            int inputMonth = Integer.parseInt(dateField2.getText());
            int inputDate = Integer.parseInt(dateField3.getText());

            Date habitDate = new Date(inputYear, inputMonth, inputDate);
            title("Select Function");
            message(inputYear + "." + inputMonth + "." + inputDate);

            generateDailyHabits(habitDate);

            habitDisplay.add(habitsPane, BoxLayout.LINE_AXIS);
            habitDisplay.updateUI();
        } catch (DateErrorException | NumberFormatException e) {
            message("Date Entered is invalid");
        }
    }

    // change the name of one habit
    private void editPerform() {
        title("Select Function:");
        String name = habitName.getText();
        String newName = newHabitName.getText();
        Habit habit = new Habit(name);
        daySet.editDailyHabitList(habit, newName);
        habitDisplay.updateUI();
        message(name + " has been change to " + newName);

    }

    // view the statistic data of all habit done in one particular month
    private void monthPerform() {

        title("Select Function");
        try {
            int inputYear = Integer.parseInt(dateField1.getText());
            int inputMonth = Integer.parseInt(dateField2.getText());

            JTextArea viewArea = new JTextArea(5, 10);
            viewArea.setPreferredSize(new Dimension(400, 200));
            viewArea.setFont(new Font("", Font.PLAIN, 25));
            viewArea.setText("\n");

            for (Habit h : daySet.getSetHabitList().getHabitList()) {
                int i = daySet.countHabit(inputYear, inputMonth, h);
                viewArea.append("You have completed " + h.getLabel() + " " + i + " times." + "\n");
            }
            viewArea.setEditable(false);
            habitsPane = new JScrollPane(viewArea);
            habitsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            habitsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            habitsPane.setPreferredSize(new Dimension(400, 200));
            message("In " + inputYear + "." + inputMonth);

            habitDisplay.add(habitsPane);
        } catch (DateErrorException | NumberFormatException e) {
            message("Date Entered is invalid");
        }
    }
}
