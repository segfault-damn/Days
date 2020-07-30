package model.entries;

import persistence.AnniversaryReader;
import persistence.Saveable;

import java.io.PrintWriter;

// a enumeration of mood
public enum Mood implements Saveable {
    Cheerful, Sad, Angry, Depressed, Calm, Energetic,Default;

    @Override
    public void save(PrintWriter printWriter) {

        printWriter.print(this.name());
        printWriter.print(AnniversaryReader.DELIMITER);
        printWriter.println("");
    }
}
