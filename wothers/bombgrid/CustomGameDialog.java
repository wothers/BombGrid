package wothers.bombgrid;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomGameDialog extends Dialog {
    private final Label widthLabel = new Label("Width (2-40):");
    private final Label heightLabel = new Label("Height (2-40):");
    private final Label bombCountLabel = new Label("Bomb count:");
    private final TextField widthField = new TextField(2);
    private final TextField heightField = new TextField(2);
    private final TextField bombCountField = new TextField(2);
    private final Button okButton = new Button("OK");
    private final Button cancelButton = new Button("Cancel");
    private final Panel fieldsPanel = new Panel(new GridLayout(6, 1));
    private final Panel buttonsPanel = new Panel(new FlowLayout());

    CustomGameDialog(GameWindow window) {
        super(window);

        fieldsPanel.add(widthLabel);
        fieldsPanel.add(widthField);
        fieldsPanel.add(heightLabel);
        fieldsPanel.add(heightField);
        fieldsPanel.add(bombCountLabel);
        fieldsPanel.add(bombCountField);
        okButton.addActionListener((e) -> {
            try {
                int width = Integer.parseInt(widthField.getText());
                int height = Integer.parseInt(heightField.getText());
                int bombCount = Integer.parseInt(bombCountField.getText());
                if (width > 1 && height > 1 && bombCount > 0) {
                    if (width <= 40 && height <= 40 && bombCount < width * height) {
                        window.newGame(width, height, bombCount);
                        dispose();
                    }
                }
            } catch (NumberFormatException nfe) {
            }
        });
        cancelButton.addActionListener((e) -> dispose());
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(fieldsPanel, BorderLayout.PAGE_START);
        add(buttonsPanel, BorderLayout.PAGE_END);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
