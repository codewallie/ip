package bro.ui;

import bro.tasks.Tasks;
import bro.utils.FileIO;

public class Bro {
    private Ui ui;

    public Bro() {
        try {
            this.ui = new Ui(
                new Tasks(FileIO.loadData())
            );
        } catch (Exception e) {
            System.out.println("Error initializing Bro.");
        }
    }

    public void run() {
        ui.run();
    }

    public static void main(String[] args) {
        new Bro().run();
    }
}
