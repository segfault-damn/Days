package ui;

import exceptions.DateErrorException;
import model.Date;
import model.DaySet;
import model.entries.Diary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiaryApp extends JPanel implements ActionListener {
    private DaySet daySet;
    private Date today;

    private Font labelFont;
    private Font btnFont;
    private Font contentFont;

    private JLabel diaryLabel;

    private JPanel diaryDisplay;
    private JPanel diaryBtn;
    private JPanel datePanel;

    private JButton writeBtn;
    private JButton modifyBtn;
    private JButton viewBtn;
    private JButton viewWithTagBtn;

    private JButton confirmWrite;
    private JButton confirmModify;
    private JButton confirmView;
    private JButton confirmTag;

    private JLabel message;
    private JLabel title;

    private JTextField dateField1;
    private JTextField dateField2;
    private JTextField dateField3;

    private JScrollPane writingPane;

    private JTextArea writingArea;
    private JTextField writingTag;
    private JLabel writingTagLabel;

    public DiaryApp(DaySet dayset, Date today) {
        this.daySet = dayset;
        this.today = today;

        labelFont = new Font("", Font.ITALIC, 150);
        btnFont = new Font("", Font.BOLD, 45);
        contentFont = new Font("", Font.PLAIN, 80);

        message = new JLabel("");
        title = new JLabel("");

        runDiary();
    }

    private void runDiary() {
        generateDateInput();
        initializeDiary();
        setVisible(true);
    }

    private void initializeDiary() {
        setLayout(new BorderLayout());

        diaryLabel = new JLabel("Diary");
        diaryLabel.setFont(labelFont);

        diaryDisplay = new JPanel();
        diaryDisplay.setLayout(new BoxLayout(diaryDisplay, 1));

        initializeDisplay();
        setDiaryBtn();

        add(diaryLabel, BorderLayout.NORTH);
        add(diaryDisplay, BorderLayout.CENTER);
        add(diaryBtn, BorderLayout.SOUTH);
    }

    private void initializeDisplay() {

        diaryDisplay.removeAll();
        title("Select Function: ");
        message(" ");
        diaryDisplay.updateUI();
    }

    private void setDiaryBtn() {
        diaryBtn = new JPanel();
        diaryBtn.setLayout(new FlowLayout());

        writeBtn = new JButton("Write");
        writeBtn.setFont(btnFont);
        writeBtn.setPreferredSize(new Dimension(350, 200));

        modifyBtn = new JButton("Modify");
        modifyBtn.setFont(btnFont);
        modifyBtn.setPreferredSize(new Dimension(350, 200));

        viewBtn = new JButton("View");
        viewBtn.setFont(btnFont);
        viewBtn.setPreferredSize(new Dimension(350, 200));

        viewWithTagBtn = new JButton("Tag");
        viewWithTagBtn.setFont(btnFont);
        viewWithTagBtn.setPreferredSize(new Dimension(350, 200));


        diaryBtn.add(writeBtn, FlowLayout.LEFT);
        diaryBtn.add(modifyBtn, FlowLayout.CENTER);
        diaryBtn.add(viewBtn, FlowLayout.RIGHT);
        diaryBtn.add(viewWithTagBtn, FlowLayout.LEADING);

        writeBtn.addActionListener(this);
        modifyBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        viewWithTagBtn.addActionListener(this);
    }

    private void title(String s) {
        diaryDisplay.remove(title);
        title = new JLabel(s);
        title.setFont(contentFont);

        diaryDisplay.add(title, BoxLayout.X_AXIS);
        diaryDisplay.updateUI();
    }

    private void message(String s) {
        diaryDisplay.remove(message);
        message = new JLabel(s);

        message.setFont(contentFont);

        diaryDisplay.add(message, BoxLayout.Y_AXIS);
        diaryDisplay.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == writeBtn) {
            initializeDisplay();
            writeDiary();
        } else if (source == modifyBtn) {
            initializeDisplay();
            modifyDiary();
        } else if (source == viewBtn) {
            initializeDisplay();
            viewDiary();

        } else if (source == viewWithTagBtn) {
            initializeDisplay();
            viewWithTag();
        } else {
            confirmPerform(source);
        }
    }

    private void confirmPerform(Object source) {
        if (source == confirmWrite) {
            writeDiaryPerform();
            diaryDisplay.remove(writingArea);
            diaryDisplay.remove(writingTag);
            diaryDisplay.remove(writingTagLabel);
            diaryDisplay.remove(confirmWrite);
        } else if (source == confirmModify) {
            modifyDiaryPerform();
            diaryDisplay.remove(writingArea);
            diaryDisplay.remove(writingTag);
            diaryDisplay.remove(writingTagLabel);
            diaryDisplay.remove(datePanel);
            diaryDisplay.remove(confirmModify);
        } else if (source == confirmView) {
            viewDiaryPerform();
            diaryDisplay.remove(datePanel);
            diaryDisplay.remove(confirmView);
        } else if (source == confirmTag) {
            tagPerform();
            diaryDisplay.remove(writingTag);
            diaryDisplay.remove(confirmTag);
        }
    }


    // write today's diary
    private void writeDiary() {

        title("Write your diary Here!");
        message(today.getYear() + "/" + today.getMonth() + "/" + today.getDay());

        writingArea = new JTextArea(5, 40);
        writingArea.setFont(new Font("", Font.PLAIN, 50));
        JScrollPane writingPane = new JScrollPane(writingArea);
        writingPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        writingPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        writingTagLabel = new JLabel("Enter tag (or leave blank): ");
        writingTagLabel.setFont(contentFont);
        writingTag = new JTextField(10);
        writingTag.setSize(new Dimension(400, 100));
        writingTag.setText("");
        writingTag.setFont(contentFont);

        writeConfirm();

        diaryDisplay.add(writingPane, BoxLayout.LINE_AXIS);
        diaryDisplay.add(confirmWrite, BoxLayout.PAGE_AXIS);
        diaryDisplay.add(writingTag, BoxLayout.PAGE_AXIS);
        diaryDisplay.add(writingTagLabel, BoxLayout.PAGE_AXIS);

        diaryDisplay.updateUI();

    }

    private void writeConfirm() {
        confirmWrite = new JButton("Confirm");
        confirmWrite.setFont(btnFont);
        confirmWrite.setPreferredSize(new Dimension(350, 200));
        confirmWrite.addActionListener(this);
    }


    // modify diary
    private void modifyDiary() {
        title("Modify selected diary!");
        message("Enter Date: ");
        writingArea = new JTextArea(5, 40);
        writingArea.setFont(new Font("", Font.PLAIN, 60));
        writingPane = new JScrollPane(writingArea);
        writingPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        writingPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        writingTagLabel = new JLabel("Enter tag (or leave blank): ");
        writingTagLabel.setFont(contentFont);
        writingTag = new JTextField(10);
        writingTag.setSize(new Dimension(400, 100));
        writingTag.setText("");
        writingTag.setFont(contentFont);

        modifyConfirm();
        diaryDisplay.add(datePanel, BoxLayout.LINE_AXIS);
        diaryDisplay.add(confirmModify, BoxLayout.PAGE_AXIS);
        diaryDisplay.add(writingTag, BoxLayout.PAGE_AXIS);
        diaryDisplay.add(writingTagLabel, BoxLayout.PAGE_AXIS);
        diaryDisplay.add(writingPane, BoxLayout.PAGE_AXIS);

        diaryDisplay.updateUI();
    }

    private void modifyConfirm() {
        confirmModify = new JButton("Confirm");
        confirmModify.setFont(btnFont);
        confirmModify.setPreferredSize(new Dimension(350, 200));
        confirmModify.addActionListener(this);
    }

    private void viewDiary() {
        title("View Diary on date:");
        message(" ");

        diaryDisplay.add(datePanel, BoxLayout.LINE_AXIS);

        viewConfirm();
        diaryDisplay.add(confirmView, BoxLayout.PAGE_AXIS);
        diaryDisplay.updateUI();
    }

    private void viewConfirm() {
        confirmView = new JButton("Confirm");
        confirmView.setFont(btnFont);
        confirmView.setPreferredSize(new Dimension(350, 200));
        confirmView.addActionListener(this);
    }

    private void viewWithTag() {
        title("View diaries using tag!");
        message("Enter tag: ");

        writingTag = new JTextField("");
        writingTag.setFont(contentFont);
        writingTag.setSize(new Dimension(500, 200));
        diaryDisplay.add(writingTag, BoxLayout.LINE_AXIS);

        tagConfirm();
        diaryDisplay.add(confirmTag, BoxLayout.PAGE_AXIS);

        diaryDisplay.updateUI();
    }

    private void tagConfirm() {
        confirmTag = new JButton("Confirm");
        confirmTag.setFont(btnFont);
        confirmTag.setPreferredSize(new Dimension(350, 200));
        confirmTag.addActionListener(this);
    }

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


    // write diary in given date
    private void writeDiaryPerform() {
        title("Select Function: ");
        String s = writingArea.getText();
        String tag = writingTag.getText();
        Diary d = new Diary(today);
        d.setContent(s);
        daySet.getDay(today).setDiary(d);
        if (tag.equals("")) {
            message("Today's diary has been created without tag!");
        } else {
            message("Today's diary has been created! Tag: " + tag);
            d.setTag(tag);
        }
    }

    // modify the diary in the given day
    private void modifyDiaryPerform() {
        title("Select Function:");
        String s = writingArea.getText();
        String tag = writingTag.getText();


        try {
            int inputYear = Integer.parseInt(dateField1.getText());
            int inputMonth = Integer.parseInt(dateField2.getText());
            int inputDate = Integer.parseInt(dateField3.getText());
            Date diaryDate = new Date(inputYear, inputMonth, inputDate);
            daySet.getDay(diaryDate).getDiary().setContent(s);
            daySet.getDay(diaryDate).getDiary().setTag(tag);
            message("Diary has been modified on " + inputYear + "." + inputMonth + "." + inputDate);
        } catch (DateErrorException | NumberFormatException e) {
            message("Date Entered is invalid");
        }
    }

    // view diary of given date
    private void viewDiaryPerform() {

        try {
            int inputYear = Integer.parseInt(dateField1.getText());
            int inputMonth = Integer.parseInt(dateField2.getText());
            int inputDate = Integer.parseInt(dateField3.getText());

            Date diaryDate = new Date(inputYear, inputMonth, inputDate);
            title(inputYear + "." + inputMonth + "." + inputDate);
            message("Tag: " + daySet.getDay(diaryDate).getDiary().getTag());

            JTextArea viewArea = new JTextArea(5, 40);
            viewArea.setFont(new Font("", Font.PLAIN, 60));
            viewArea.setText(daySet.getDay(diaryDate).getDiary().getContent());
            viewArea.setEditable(false);
            writingPane = new JScrollPane(viewArea);
            writingPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            writingPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            diaryDisplay.add(writingPane, BoxLayout.LINE_AXIS);
            diaryDisplay.updateUI();
        } catch (DateErrorException | NumberFormatException e) {
            message("Date Entered is invalid");
        }
    }

    // view diary with given tag
    private void tagPerform() {
        title("Select Function:");
        String tag = writingTag.getText();

        JTextArea contents = new JTextArea(10, 40);
        contents.setText("");
        contents.setFont(new Font("", Font.PLAIN, 60));
        contents.setEditable(false);
        for (Diary d : daySet.searchByTag(tag)) {


            String s = d.getDate().getYear() + "." + d.getDate().getMonth() + "."
                    + d.getDate().getDay() + "\n" + d.getContent() + "\n";
            contents.append(s);
        }
        writingPane = new JScrollPane(contents);
        writingPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        writingPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        message("All Diaries with tag: " + tag);
        diaryDisplay.add(writingPane, BoxLayout.LINE_AXIS);
        diaryDisplay.updateUI();

    }
}
