package br.com.crudcidades;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CrudApp().setVisible(true);
            }
        });
    }
}
