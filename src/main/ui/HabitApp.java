package ui;

import exceptions.DateErrorException;
import model.Date;
import model.DaySet;
import model.entries.Habit;
import model.entries.Mood;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;


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

    public HabitApp(DaySet dayset, Date today) {
        this.daySet = dayset;
        this.today = today;

        labelFont = new Font("",Font.ITALIC,150);
        btnFont = new Font("",Font.BOLD,45);
        contentFont = new Font("",Font.PLAIN,80);

        message = new JLabel("");
        title = new JLabel("");

        runHabit();
    }

    private void runHabit() {
        generateDateInput();
        initializeHabit();
        setVisible(true);
    }

    private void initializeHabit() {
        setLayout(new BorderLayout());

        habitLabel = new JLabel("Habit");
        habitLabel.setFont(labelFont);

        habitDisplay = new JPanel();
        habitDisplay.setLayout(new BoxLayout(habitDisplay,1));

        initializeDisplay();
        setHabitBtn();
        generateDailyHabits(today);
        habitDisplay.add(habitsPane,BoxLayout.LINE_AXIS);
        message("Today");

        add(habitLabel,BorderLayout.NORTH);
        add(habitDisplay,BorderLayout.CENTER);
        add(habitBtn,BorderLayout.SOUTH);
    }

    private void initializeDisplay() {

        habitDisplay.removeAll();
        title("Select Function: ");
        message(" ");

        habitDisplay.updateUI();
    }

    private void setHabitBtn() {
        habitBtn = new JPanel();
        habitBtn.setLayout(new FlowLayout());

        generateListBar();
        markBtn = new JButton("Mark");
        markBtn.setFont(btnFont);
        markBtn.setPreferredSize(new Dimension(350,200));

        viewBtn = new JButton("View");
        viewBtn.setFont(btnFont);
        viewBtn.setPreferredSize(new Dimension(350,200));

        editBtn = new JButton("Edit");
        editBtn.setFont(btnFont);
        editBtn.setPreferredSize(new Dimension(350,200));

        monthBtn = new JButton("Month");
        monthBtn.setFont(btnFont);
        monthBtn.setPreferredSize(new Dimension(350,200));

        habitBtn.add(listBar,FlowLayout.LEFT);
        habitBtn.add(markBtn,FlowLayout.CENTER);
        habitBtn.add(viewBtn,FlowLayout.RIGHT);
        habitBtn.add(editBtn,FlowLayout.LEADING);
        habitBtn.add(monthBtn,FlowLayout.TRAILING);

        addList.addActionListener(this);
        removeList.addActionListener(this);
        markBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        editBtn.addActionListener(this);
        monthBtn.addActionListener(this);
    }

    private void generateListBar() {
        listBar = new JMenuBar();
        listBar.setBorderPainted(true);
        listBar.setPreferredSize(new Dimension(350,200));
        listMenu = new JMenu("List");
        listMenu.setPreferredSize(new Dimension(350,200));
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

    private void title(String s) {
        habitDisplay.remove(title);
        title = new JLabel(s);
        title.setFont(contentFont);

        habitDisplay.add(title,BoxLayout.X_AXIS);
        habitDisplay.updateUI();
    }

    private void message(String s) {
        habitDisplay.remove(message);
        message = new JLabel(s);

        message.setFont(contentFont);

        habitDisplay.add(message,BoxLayout.Y_AXIS);
        habitDisplay.updateUI();
    }

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
        } else if (source == confirmDate) {
            markHabit();
            habitDisplay.remove(datePanel);
            habitDisplay.remove(confirmDate);
        } else if (source == viewBtn) {
            initializeDisplay();
            viewHabit();
        } else if (source == editBtn) {
            initializeDisplay();
            editHabit();
        } else if (source == monthBtn) {
            initializeDisplay();
            monthHabit();
        } else if (source == confirmAdd) {
            addHabitPerform();
            habitDisplay.remove(habitName);
            habitDisplay.remove(habitsPane);
            habitDisplay.remove(confirmAdd);
        } else if (source == confirmRemove) {
            removeHabitPerform();
            habitDisplay.remove(habitName);
            habitDisplay.remove(habitsPane);
            habitDisplay.remove(confirmRemove);
        } else if (source == confirmMark) {
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

    private void generateHabits() {
        JTextArea viewArea = new JTextArea(5,10);
        viewArea.setPreferredSize(new Dimension(1000,500));
        viewArea.setFont(new Font("",Font.PLAIN,60));
        viewArea.setText(" Habit List: \n");
        for (String s : daySet.getSetHabitList().getHabitLabel()) {
            viewArea.append(s + "\n");
        }
        viewArea.setEditable(false);
        habitsPane = new JScrollPane(viewArea);
        habitsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        habitsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        habitsPane.setPreferredSize(new Dimension(1000,500));
    }

    private void generateDailyHabits(Date date) {
        JTextArea viewArea = new JTextArea(5,10);
        viewArea.setPreferredSize(new Dimension(1000,500));
        viewArea.setFont(new Font("",Font.PLAIN,60));
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
        habitsPane.setPreferredSize(new Dimension(1000,500));
    }


    // write today's diary
    private void addHabit() {

        title("Add your habit!");
        message("Enter habit's name: ");
        generateHabits();
        habitName = new JTextField("Name");
        habitName.setFont(contentFont);
        addConfirm();

        habitDisplay.add(habitsPane,BoxLayout.LINE_AXIS);
        habitDisplay.add(confirmAdd,BoxLayout.PAGE_AXIS);
        habitDisplay.add(habitName, BoxLayout.PAGE_AXIS);

    }

    private void addConfirm() {
        confirmAdd = new JButton("Confirm");
        confirmAdd.setFont(btnFont);
        confirmAdd.setPreferredSize(new Dimension(350,200));
        confirmAdd.addActionListener(this);
    }

    // remove one habit
    private void removeHabit() {

        title("Remove your habit!");
        message("Enter habit's name: ");
        generateHabits();
        habitName = new JTextField("Name");
        habitName.setFont(contentFont);
        removeConfirm();

        habitDisplay.add(habitsPane,BoxLayout.LINE_AXIS);
        habitDisplay.add(confirmRemove,BoxLayout.PAGE_AXIS);
        habitDisplay.add(habitName, BoxLayout.PAGE_AXIS);

    }

    private void removeConfirm() {
        confirmRemove = new JButton("Confirm");
        confirmRemove.setFont(btnFont);
        confirmRemove.setPreferredSize(new Dimension(350,200));
        confirmRemove.addActionListener(this);
    }


    private void markHabitSelectDate() {
        title("Mark habit!");
        message("Enter the date you want to mark:");
        generateDateInput();
        selectDateConfirm();

        habitDisplay.add(datePanel,BoxLayout.LINE_AXIS);
        habitDisplay.add(confirmDate,BoxLayout.PAGE_AXIS);
    }

    private void selectDateConfirm() {
        confirmDate = new JButton("Confirm");
        confirmDate.setFont(btnFont);
        confirmDate.setPreferredSize(new Dimension(350,200));
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
            habitDisplay.add(habitsPane,BoxLayout.LINE_AXIS);
            habitDisplay.add(confirmMark,BoxLayout.PAGE_AXIS);
            habitDisplay.add(habitName,BoxLayout.PAGE_AXIS);
            habitDisplay.updateUI();
        } catch (DateErrorException | NumberFormatException e) {
            title("Select Function: ");
            message("Date Entered is invalid");
        }
    }

    private void markConfirm() {
        confirmMark = new JButton("Confirm");
        confirmMark.setFont(btnFont);
        confirmMark.setPreferredSize(new Dimension(350,200));
        confirmMark.addActionListener(this);
    }

    private void viewHabit() {
        title("View Habit!");
        message("Enter Date: ");
        generateDateInput();
        habitDisplay.add(datePanel,BoxLayout.LINE_AXIS);

        viewConfirm();
        habitDisplay.add(confirmView,BoxLayout.PAGE_AXIS);
        habitDisplay.updateUI();
    }

    private void viewConfirm() {
        confirmView = new JButton("Confirm");
        confirmView.setFont(btnFont);
        confirmView.setPreferredSize(new Dimension(350,200));
        confirmView.addActionListener(this);
    }

    private void editHabit() {
        title("Edit Habit's name!");
        message("Enter name: ");

        habitName = new JTextField("Name");
        habitName.setFont(contentFont);
        newHabitName = new JTextField("New name");
        newHabitName.setFont(contentFont);
        habitDisplay.add(habitName,BoxLayout.LINE_AXIS);

        editConfirm();
        habitDisplay.add(confirmEdit,BoxLayout.PAGE_AXIS);
        habitDisplay.add(newHabitName,BoxLayout.PAGE_AXIS);

        habitDisplay.updateUI();
    }

    private void editConfirm() {
        confirmEdit = new JButton("Confirm");
        confirmEdit.setFont(btnFont);
        confirmEdit.setPreferredSize(new Dimension(350,200));
        confirmEdit.addActionListener(this);
    }

    private void monthHabit() {
        title("View your habits record!");
        message("Enter year and month: ");
        generateMonthInput();

        habitDisplay.add(monthPanel,BoxLayout.LINE_AXIS);

        monthConfirm();
        habitDisplay.add(confirmMonth,BoxLayout.PAGE_AXIS);
        habitDisplay.updateUI();
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

            habitDisplay.add(habitsPane,BoxLayout.LINE_AXIS);
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
            viewArea.setPreferredSize(new Dimension(1000, 500));
            viewArea.setFont(new Font("", Font.PLAIN, 60));
            viewArea.setText("\n");

            for (Habit h : daySet.getSetHabitList().getHabitList()) {
                int i = daySet.countHabit(inputYear, inputMonth, h);
                viewArea.append("You have completed " + h.getLabel() + " " + i + " times." + "\n");
            }
            viewArea.setEditable(false);
            habitsPane = new JScrollPane(viewArea);
            habitsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            habitsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            habitsPane.setPreferredSize(new Dimension(1000, 500));
            message("In " + inputYear + "." + inputMonth);

            habitDisplay.add(habitsPane);
        } catch (DateErrorException | NumberFormatException e) {
            message("Date Entered is invalid");
        }
    }
}
