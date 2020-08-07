package ui;

import exceptions.DateErrorException;
import model.Date;
import model.Day;
import model.DaySet;
import model.entries.Anniversary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnniversaryApp extends JPanel implements ActionListener {
    private JPanel datePanel;
    private JPanel anniBtn;
    private JLabel aniLabel;
    private JPanel aniDisplay;

    private Font labelFont;
    private Font contentFont;
    private Font btnFont;

    private JButton setBtn;
    private JButton viewBtn;
    private JButton editBtn;
    private JButton removeBtn;
    private JButton confirmSet;
    private JButton confirmEdit;
    private JButton confirmRemove;

    private JTextField dateField1;
    private JTextField dateField2;
    private JTextField dateField3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    private JScrollPane viewAnniPane;

    private JLabel message;
    private JLabel title;

    private DaySet daySet;
    private Date today;

    public AnniversaryApp(DaySet dayset,Date today) {
        this.daySet = dayset;
        this.today = today;

        labelFont = new Font("",Font.ITALIC,150);
        btnFont = new Font("",Font.BOLD,45);
        contentFont = new Font("",Font.PLAIN,80);

        textField1 = new JTextField();
        textField1.setFont(contentFont);
        textField2 = new JTextField();
        textField2.setFont(contentFont);
        textField3 = new JTextField();
        textField3.setFont(contentFont);
        message = new JLabel("");
        title = new JLabel("");

        runAnni();

    }

    private void runAnni() {
        initializeAnni();
        generateDateInput();
        setVisible(true);
    }

    private void initializeAnni() {
        setLayout(new BorderLayout());

        aniLabel = new JLabel("Anniversary: ");
        aniLabel.setFont(labelFont);

        aniDisplay = new JPanel();
        aniDisplay.setLayout(new BoxLayout(aniDisplay,1));

        initializeDisplay();
        setAnniversaryBtn();
        add(aniLabel,BorderLayout.NORTH);
        add(aniDisplay,BorderLayout.CENTER);
        add(anniBtn,BorderLayout.SOUTH);
    }

    private void initializeDisplay() {

        aniDisplay.removeAll();
        title("Select Function: ");
        message(" ");
        aniDisplay.updateUI();
    }

    private void setAnniversaryBtn() {
        anniBtn = new JPanel();
        anniBtn.setLayout(new FlowLayout());

        setBtn = new JButton("Set");
        setBtn.setFont(btnFont);
        setBtn.setPreferredSize(new Dimension(350,200));


        viewBtn = new JButton("View");
        viewBtn.setFont(btnFont);
        viewBtn.setPreferredSize(new Dimension(350,200));

        removeBtn = new JButton("Remove");
        removeBtn.setFont(btnFont);
        removeBtn.setPreferredSize(new Dimension(350,200));

        editBtn = new JButton("Edit");
        editBtn.setFont(btnFont);
        editBtn.setPreferredSize(new Dimension(350,200));



        anniBtn.add(setBtn,FlowLayout.LEFT);
        anniBtn.add(viewBtn,FlowLayout.CENTER);
        anniBtn.add(removeBtn,FlowLayout.RIGHT);
        anniBtn.add(editBtn,FlowLayout.LEADING);

        setBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        removeBtn.addActionListener(this);
        editBtn.addActionListener(this);
    }

    private void title(String s) {
        aniDisplay.remove(title);
        title = new JLabel(s);
        title.setFont(contentFont);

        aniDisplay.add(title,BoxLayout.X_AXIS);
        aniDisplay.updateUI();
    }

    private void message(String s) {
        aniDisplay.remove(message);
        message = new JLabel(s);

        message.setFont(contentFont);

        aniDisplay.add(message,BoxLayout.Y_AXIS);
        aniDisplay.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == setBtn) {
            initializeDisplay();
            setAnniversary();
        } else if (source == viewBtn) {
            initializeDisplay();
            viewAnni();
        } else if (source == removeBtn) {
            initializeDisplay();
            removeAnniversary();

        } else if (source == editBtn) {
            initializeDisplay();
            editAnniversary();
        } else if (source == confirmSet) {
            setAnniPerform();
            aniDisplay.remove(confirmSet);
        } else if (source == confirmRemove) {
            removeAnniPerform();
            aniDisplay.remove(datePanel);
            aniDisplay.remove(confirmRemove);
        } else if (source == confirmEdit) {
            editAnniPerform();
            aniDisplay.remove(textField1);
            aniDisplay.remove(confirmEdit);
        }
    }


    // set anniversary in given day
    private void setAnniversary() {

        title("Add your anniversary! ");
        textField1.setColumns(20);
        textField1.setText("NAME");
        textField1.setFont(contentFont);
        textField2.setColumns(30);
        textField2.setText("COMMENT");
        textField2.setFont(contentFont);

        setAnniSetConfirm();

        aniDisplay.add(datePanel,BoxLayout.LINE_AXIS);
        aniDisplay.add(confirmSet,BoxLayout.PAGE_AXIS);
        aniDisplay.add(textField2,BoxLayout.PAGE_AXIS);
        aniDisplay.add(textField1,BoxLayout.PAGE_AXIS);


        aniDisplay.updateUI();
    }



    private void setAnniSetConfirm() {
        confirmSet = new JButton("Confirm");
        confirmSet.setFont(btnFont);
        confirmSet.setPreferredSize(new Dimension(350,200));
        confirmSet.addActionListener(this);
    }

    private void removeAnniversary() {
        title("Remove Anniversary");
        message("on date: ");

        aniDisplay.add(datePanel);

        anniRemoveConfirm();
        aniDisplay.add(confirmRemove,BoxLayout.PAGE_AXIS);
        aniDisplay.updateUI();
    }

    private void anniRemoveConfirm() {
        confirmRemove = new JButton("Confirm");
        confirmRemove.setFont(btnFont);
        confirmRemove.setPreferredSize(new Dimension(350,200));
        confirmRemove.addActionListener(this);
    }

    private void editAnniversary() {
        title("Edit Anniversary!");
        message("Select by name: ");

        textField1.setText("NAME");
        aniDisplay.add(textField1,BoxLayout.LINE_AXIS);

        anniEditConfirm();
        aniDisplay.add(confirmEdit,BoxLayout.PAGE_AXIS);

        textField3.setText("NEW COMMENT");
        aniDisplay.add(textField3,BoxLayout.PAGE_AXIS);
        textField2.setText("NEW NAME");
        aniDisplay.add(textField2,BoxLayout.PAGE_AXIS);

        aniDisplay.updateUI();
    }

    private void anniEditConfirm() {
        confirmEdit = new JButton("Confirm");
        confirmEdit.setFont(btnFont);
        confirmEdit.setPreferredSize(new Dimension(350,200));
        confirmEdit.addActionListener(this);
    }

    private void generateDateInput() {
        datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout());

        dateField1 = new JTextField("YYYY",4);
        datePanel.add(dateField1,FlowLayout.LEFT);
        dateField1.setFont(labelFont);

        dateField2 = new JTextField("MM",2);
        datePanel.add(dateField2,FlowLayout.CENTER);
        dateField2.setFont(labelFont);

        dateField3 = new JTextField("DD",2);
        datePanel.add(dateField3,FlowLayout.RIGHT);
        dateField3.setFont(labelFont);


    }

    private void setAnniPerform() {
        int inputYear = Integer.parseInt(dateField1.getText());
        int inputMonth = Integer.parseInt(dateField2.getText());
        int inputDate = Integer.parseInt(dateField3.getText());
        String name = textField1.getText();
        String comment = textField2.getText();
        try {
            Date anniDate = new Date(inputYear, inputMonth, inputDate);

            Anniversary anniversary = new Anniversary(anniDate, name,comment);
            anniversary.setAnniversary();
            daySet.getDay(anniDate).setDayAnniversary(anniversary);
            message("Anniversary has been set!");
        } catch (DateErrorException a) {
            message("Date Entered is invalid");
        } finally {
            title("Select Function:");
        }
    }

    private void viewAnni() {
        JTextArea contents = new JTextArea(10,40);
        contents.setText("");
        contents.setFont(new Font("",Font.PLAIN,50));
        contents.setEditable(false);

        for (Day day : daySet.getDays()) {
            if (day.getAnniversary().getIsAnniversary()) {
                String s = day.getAnniversary().getDate().getMonth() + "."
                        + day.getAnniversary().getDate().getDay() + "\n"
                        + day.getAnniversary().getLabel() + "\n"
                        + "Comment: " + day.getAnniversary().getComment() + "\n"
                        + "You have passed " + daySet.calAnniversary(today, day.getAnniversary())
                        + " anniversary - start from " + day.getAnniversary().getDate().getYear() + "\n" + "\n";
                contents.append(s);
            }
        }
        viewAnniPane = new JScrollPane(contents);
        viewAnniPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        viewAnniPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        aniDisplay.add(viewAnniPane,BoxLayout.LINE_AXIS);
    }


    // remove one anniversary with given date
    private void removeAnniPerform() {
        int inputYear = Integer.parseInt(dateField1.getText());
        int inputMonth = Integer.parseInt(dateField2.getText());
        int inputDate = Integer.parseInt(dateField3.getText());

        try {
            Date date = new Date(inputYear, inputMonth, inputDate);
            daySet.getDay(date).removeDayAnniversary();
            message(daySet.getDay(date).getAnniversary().getLabel() + " has been removed");
        } catch (DateErrorException e) {
            message("Date Entered is invalid");
        } finally {
            title("Select Function: ");
        }
    }

    // edit the Anniversary's comment with given name
    private void editAnniPerform() {
        String s = textField1.getText();
        String name = textField2.getText();
        String comment = textField3.getText();
        for (Day day : daySet.getDays()) {
            if (day.getAnniversary().getLabel().equals(s)) {
                Anniversary anniversary = new Anniversary(day.getDate(),name,comment);
                anniversary.setAnniversary();
                day.setDayAnniversary(anniversary);
                title("Select Function: ");
                message(s + " has been changed to " + name);
            }
        }
    }
}
