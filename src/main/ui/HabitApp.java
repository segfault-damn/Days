package ui;

import model.DaySet;

import javax.swing.*;
import java.awt.*;

public class HabitApp extends JPanel {
    private DaySet daySet;

    private Font labelFont;
    private Font btnFont;

    public HabitApp(DaySet dayset) {
        this.daySet = dayset;


        labelFont = new Font("",Font.ITALIC,150);
        btnFont = new Font("",Font.BOLD,45);



    }
}