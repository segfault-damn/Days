package ui;

import exceptions.DateErrorException;
import model.Date;
import model.DaySet;
import model.entries.TodoEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// event panel
public class EventApp extends JPanel implements ActionListener {
    private DaySet daySet;
    private Date today;

    private Font labelFont;
    private Font btnFont;
    private Font contentFont;

    private JLabel eventLabel;

    private JPanel eventDisplay;
    private JPanel eventBtn;
    private JPanel datePanel;
    private JPanel timePanel;
    private JScrollPane viewEventPane;

    private JButton addBtn;
    private JButton modifyBtn;
    private JButton viewBtn;
    private JButton removeBtn;

    private JButton confirmAdd;
    private JButton confirmModify;
    private JButton confirmView;
    private JButton confirmRemove;
    private JButton confirmNewTime;

    private JLabel message;
    private JLabel title;

    private JTextField dateField1;
    private JTextField dateField2;
    private JTextField dateField3;

    private JTextField timeField1;
    private JTextField timeField2;

    private JTextField eventName;

    private int oldYear;
    private int oldMonth;
    private int oldDate;
    private int oldHour;
    private int oldMin;
    private String oldName;
    private Dimension buttonDimension;

    // construct a new event app
    public EventApp(DaySet dayset, Date today) {
        this.daySet = dayset;
        this.today = today;

        labelFont = new Font("", Font.ITALIC, 60);
        btnFont = new Font("", Font.BOLD, 20);
        contentFont = new Font("", Font.PLAIN, 40);
        buttonDimension = new Dimension(170,80);

        message = new JLabel("");
        title = new JLabel("");

        runEvent();
    }

    // run event
    private void runEvent() {
        generateDateInput();
        generateTimeInput();
        initializeEvent();
        setVisible(true);
    }

    //initialize events
    private void initializeEvent() {
        setLayout(new BorderLayout());

        eventLabel = new JLabel("Event");
        eventLabel.setFont(labelFont);

        eventDisplay = new JPanel();
        eventDisplay.setLayout(new BoxLayout(eventDisplay, 1));

        initializeDisplay();
        setEventBtn();

        initializeTodayEvent();


        eventDisplay.add(viewEventPane, BoxLayout.LINE_AXIS);

        add(eventLabel, BorderLayout.NORTH);
        add(eventDisplay, BorderLayout.CENTER);
        add(eventBtn, BorderLayout.SOUTH);
    }

    // initialize event display
    private void initializeDisplay() {

        eventDisplay.removeAll();
        title("Select Function: ");
        message(" ");

        eventDisplay.updateUI();
    }

    // intialize today's event
    private void initializeTodayEvent() {
        message("Your events today:");
        JTextArea viewArea = new JTextArea(5, 40);
        viewArea.setFont(new Font("", Font.PLAIN, 25));
        viewArea.setText(" ");
        viewArea.setEditable(false);

        viewEventPane = new JScrollPane(viewArea);
        viewEventPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        viewEventPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        for (TodoEvent event : daySet.getDay(today).getTodoEventList()) {
            if (event.getHour() < 10 && event.getMin() < 10) {
                viewArea.append(event.getLabel() + "\n0" + event.getHour() + ":0" + event.getMin());
            } else if (event.getHour() < 10) {
                viewArea.append(event.getLabel() + "\n0" + event.getHour() + ":" + event.getMin());
            } else if (event.getMin() < 10) {
                viewArea.append(event.getLabel() + "\n" + event.getHour() + ":0" + event.getMin());
            } else {
                viewArea.append(event.getLabel() + "\n" + event.getHour() + ":" + event.getMin());
            }
            viewArea.append("  \n");
        }

        eventDisplay.add(viewEventPane);
    }

    // initialize evnet funtion buttons
    private void setEventBtn() {
        eventBtn = new JPanel();
        eventBtn.setLayout(new FlowLayout());

        addBtn = new JButton("Add");
        addBtn.setFont(btnFont);
        addBtn.setPreferredSize(buttonDimension);

        modifyBtn = new JButton("Modify");
        modifyBtn.setFont(btnFont);
        modifyBtn.setPreferredSize(buttonDimension);

        viewBtn = new JButton("View");
        viewBtn.setFont(btnFont);
        viewBtn.setPreferredSize(buttonDimension);

        removeBtn = new JButton("Remove");
        removeBtn.setFont(btnFont);
        removeBtn.setPreferredSize(buttonDimension);

        eventBtn.add(addBtn, FlowLayout.LEFT);
        eventBtn.add(modifyBtn, FlowLayout.CENTER);
        eventBtn.add(viewBtn, FlowLayout.RIGHT);
        eventBtn.add(removeBtn, FlowLayout.LEADING);

        addBtn.addActionListener(this);
        modifyBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        removeBtn.addActionListener(this);
    }

    // build title label
    private void title(String s) {
        eventDisplay.remove(title);
        title = new JLabel(s);
        title.setFont(contentFont);

        eventDisplay.add(title, BoxLayout.X_AXIS);
        eventDisplay.updateUI();
    }

    // build message label
    private void message(String s) {
        eventDisplay.remove(message);
        message = new JLabel(s);

        message.setFont(contentFont);

        eventDisplay.add(message, BoxLayout.Y_AXIS);
        eventDisplay.updateUI();
    }

    // perform actions after buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == addBtn) {
            initializeDisplay();
            addEvent();
        } else if (source == modifyBtn) {
            initializeDisplay();
            modifyEvent();
        } else if (source == confirmNewTime) {
            eventDisplay.remove(timePanel);
            eventDisplay.remove(datePanel);
            eventDisplay.remove(confirmNewTime);
            modifyEventPerform();
        } else if (source == viewBtn) {
            initializeDisplay();
            viewEvent();
        } else if (source == removeBtn) {
            initializeDisplay();
            removeEvent();
        } else {
            confirmPerform(source);
        }
    }

    // perform buttons after confirm buttons are clicked
    private void confirmPerform(Object source) {
        if (source == confirmAdd) {
            eventDisplay.remove(timePanel);
            eventDisplay.remove(eventName);
            eventDisplay.remove(datePanel);
            eventDisplay.remove(confirmAdd);
            addEventPerform();

        } else if (source == confirmModify) {
            eventDisplay.remove(timePanel);
            eventDisplay.remove(eventName);
            eventDisplay.remove(datePanel);
            eventDisplay.remove(confirmModify);
            changeEventTime();

        } else if (source == confirmView) {
            eventDisplay.remove(datePanel);
            eventDisplay.remove(confirmView);
            viewEventPerform();

        } else if (source == confirmRemove) {
            eventDisplay.remove(timePanel);
            eventDisplay.remove(eventName);
            eventDisplay.remove(datePanel);
            eventDisplay.remove(confirmRemove);
            removeEventPerform();
        }
    }

    // add event
    private void addEvent() {

        title("Add Event");
        message("Enter date, time and name:");

        eventName = new JTextField("Name");
        eventName.setFont(contentFont);

        addConfirm();

        eventDisplay.add(datePanel, BoxLayout.LINE_AXIS);
        eventDisplay.add(confirmAdd, BoxLayout.PAGE_AXIS);
        eventDisplay.add(eventName, BoxLayout.PAGE_AXIS);
        eventDisplay.add(timePanel, BoxLayout.PAGE_AXIS);

        eventDisplay.updateUI();

    }

    // build add event confirm button
    private void addConfirm() {
        confirmAdd = new JButton("Confirm");
        confirmAdd.setFont(btnFont);
        confirmAdd.setPreferredSize(buttonDimension);
        confirmAdd.addActionListener(this);
    }

    // modify events
    private void modifyEvent() {
        title("Change Time of events");
        message("Enter date, time and name of target event:");
        generateTimeInput();

        eventName = new JTextField("Name");
        eventName.setFont(contentFont);

        selectConfirm();

        eventDisplay.add(datePanel, BoxLayout.LINE_AXIS);
        eventDisplay.add(confirmModify, BoxLayout.PAGE_AXIS);
        eventDisplay.add(eventName, BoxLayout.PAGE_AXIS);
        eventDisplay.add(timePanel, BoxLayout.PAGE_AXIS);

        eventDisplay.updateUI();
    }

    // build select event confirm button
    private void selectConfirm() {
        confirmModify = new JButton("Confirm");
        confirmModify.setFont(btnFont);
        confirmModify.setPreferredSize(buttonDimension);
        confirmModify.addActionListener(this);
    }


    // change event to new time
    private void changeEventTime() {
        try {
            title("Select Function:");
            oldYear = Integer.parseInt(dateField1.getText());
            oldMonth = Integer.parseInt(dateField2.getText());
            oldDate = Integer.parseInt(dateField3.getText());

            oldHour = Integer.parseInt(timeField1.getText());
            oldMin = Integer.parseInt(timeField2.getText());
            oldName = eventName.getText();
            Date eventDate = new Date(oldYear, oldMonth, oldDate);
            if (daySet.getDay(eventDate).getEvent(oldHour, oldMin, oldName) != null) {
                title("Change event!");
                message("Enter new date and time:");
                newTimeConfirm();
                eventDisplay.add(datePanel, BoxLayout.LINE_AXIS);
                eventDisplay.add(confirmNewTime, BoxLayout.PAGE_AXIS);
                eventDisplay.add(timePanel, BoxLayout.PAGE_AXIS);

            } else {
                message("Event not found");
            }

            eventDisplay.updateUI();
        } catch (DateErrorException | NumberFormatException e) {
            message("Date Entered is invalid");
        }
    }

    // build change to new time confirm
    private void newTimeConfirm() {
        confirmNewTime = new JButton("Confirm");
        confirmNewTime.setFont(btnFont);
        confirmNewTime.setPreferredSize(buttonDimension);
        confirmNewTime.addActionListener(this);
    }

    // view events
    private void viewEvent() {
        title("View Events on date:");
        message(" ");

        eventDisplay.add(datePanel, BoxLayout.LINE_AXIS);

        viewConfirm();
        eventDisplay.add(confirmView, BoxLayout.PAGE_AXIS);
        eventDisplay.updateUI();
    }

    // build view events confirm
    private void viewConfirm() {
        confirmView = new JButton("Confirm");
        confirmView.setFont(btnFont);
        confirmView.setPreferredSize(buttonDimension);
        confirmView.addActionListener(this);
    }

    // remove events
    private void removeEvent() {
        title("Remove Event");
        message("Enter date, time and name:");

        eventName = new JTextField("Name");
        eventName.setFont(contentFont);

        removeConfirm();

        eventDisplay.add(datePanel, BoxLayout.LINE_AXIS);
        eventDisplay.add(confirmRemove, BoxLayout.PAGE_AXIS);
        eventDisplay.add(eventName, BoxLayout.PAGE_AXIS);
        eventDisplay.add(timePanel, BoxLayout.PAGE_AXIS);

        eventDisplay.updateUI();
    }

    // build remove events confirm button
    private void removeConfirm() {
        confirmRemove = new JButton("Confirm");
        confirmRemove.setFont(btnFont);
        confirmRemove.setPreferredSize(buttonDimension);
        confirmRemove.addActionListener(this);
    }

    // build date input panel
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

    // build time input panel
    private void generateTimeInput() {
        timePanel = new JPanel();
        timePanel.setLayout(new FlowLayout());

        timeField1 = new JTextField("HH", 2);
        timePanel.add(timeField1, FlowLayout.LEFT);
        timeField1.setFont(labelFont);

        JLabel semicolon = new JLabel(":");
        timePanel.add(semicolon, FlowLayout.CENTER);
        semicolon.setFont(labelFont);

        timeField2 = new JTextField("MM", 2);
        timePanel.add(timeField2, FlowLayout.RIGHT);
        timeField2.setFont(labelFont);
    }


    // add an event in a particular day and particular time
    private void addEventPerform() {
        title("Select Function: ");

        try {
            int inputYear = Integer.parseInt(dateField1.getText());
            int inputMonth = Integer.parseInt(dateField2.getText());
            int inputDate = Integer.parseInt(dateField3.getText());

            int inputHour = Integer.parseInt(timeField1.getText());
            int inputMin = Integer.parseInt(timeField2.getText());
            Date eventDate = new Date(inputYear, inputMonth, inputDate);

            String name = eventName.getText();

            daySet.getDay(eventDate).addEvent(new TodoEvent(eventDate, name, inputHour, inputMin));
            message(name + " has been added.");
        } catch (DateErrorException | NumberFormatException e) {
            message("Date Entered is invalid");
        }

    }

    // change an event time
    private void modifyEventPerform() {
        title("Select Function:");
        try {
            int inputYear = Integer.parseInt(dateField1.getText());
            int inputMonth = Integer.parseInt(dateField2.getText());
            int inputDate = Integer.parseInt(dateField3.getText());

            int newHour = Integer.parseInt(timeField1.getText());
            int newMin = Integer.parseInt(timeField2.getText());
            Date eventDate = new Date(inputYear, inputMonth, inputDate);

            String name = eventName.getText();

            daySet.getDay(eventDate).getEvent(oldHour, oldMin, oldName).setTime(eventDate, newHour, newMin);
            message(name + " has been modified to a new time!");
        } catch (DateErrorException | NumberFormatException e) {
            message("Date Entered is invalid");
        }
    }

    // view all todoevent in a particular day
    private void viewEventPerform() {

        try {
            int inputYear = Integer.parseInt(dateField1.getText());
            int inputMonth = Integer.parseInt(dateField2.getText());
            int inputDate = Integer.parseInt(dateField3.getText());

            Date eventDate = new Date(inputYear, inputMonth, inputDate);

            message(inputYear + "." + inputMonth + "." + inputDate);

            JTextArea viewArea = new JTextArea(5, 40);

            viewEventPerformHelper(viewArea);

            for (TodoEvent event : daySet.getDay(eventDate).getTodoEventList()) {
                if (event.getHour() < 10 && event.getMin() < 10) {
                    viewArea.append(event.getLabel() + "\n0" + event.getHour() + ":0" + event.getMin());
                } else if (event.getHour() < 10) {
                    viewArea.append(event.getLabel() + "\n0" + event.getHour() + ":" + event.getMin());
                } else if (event.getMin() < 10) {
                    viewArea.append(event.getLabel() + "\n" + event.getHour() + ":0" + event.getMin());
                } else {
                    viewArea.append(event.getLabel() + "\n" + event.getHour() + ":" + event.getMin());
                }
                viewArea.append("  \n");
            }
        } catch (DateErrorException | NumberFormatException e) {
            message("Date Entered is invalid");
        }
    }

    // view event helper
    private void viewEventPerformHelper(JTextArea viewArea) {
        title("Your events on : ");
        viewArea.setFont(new Font("", Font.PLAIN, 25));
        viewArea.setText(" ");
        viewArea.setEditable(false);

        viewEventPane = new JScrollPane(viewArea);

        viewEventPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        viewEventPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        eventDisplay.add(viewEventPane, BoxLayout.LINE_AXIS);

    }


    // remove event perform
    private void removeEventPerform() {
        title("Select Function:");

        try {
            int inputYear = Integer.parseInt(dateField1.getText());
            int inputMonth = Integer.parseInt(dateField2.getText());
            int inputDate = Integer.parseInt(dateField3.getText());

            int inputHour = Integer.parseInt(timeField1.getText());
            int inputMin = Integer.parseInt(timeField2.getText());
            Date eventDate = new Date(inputYear, inputMonth, inputDate);

            String name = eventName.getText();
            daySet.getDay(eventDate).removeEvent(inputHour, inputMin, name);
            message(name + " has been removed");
        } catch (DateErrorException | NumberFormatException e) {
            message("Date Entered is invalid");
        }
    }
}
