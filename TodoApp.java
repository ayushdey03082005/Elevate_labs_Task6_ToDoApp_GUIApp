import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TodoApp {

    public static void main(String[] args) {

        // 1. Create the main window
        JFrame frame = new JFrame("My To-Do List");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // 2. List to store tasks
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // 3. Input field at the top
        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));

        // 4. Buttons at the bottom
        JButton addButton    = new JButton("Add Task");
        JButton deleteButton = new JButton("Delete Task");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        // 5. Add everything to the frame
        frame.add(inputField,  BorderLayout.NORTH);
        frame.add(scrollPane,  BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // 6. Add Task button logic
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String task = inputField.getText().trim();
                if (!task.isEmpty()) {
                    listModel.addElement(task);
                    inputField.setText("");   // clear the field
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a task!");
                }
            }
        });

        // 7. Delete Task button logic
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a task to delete!");
                }
            }
        });

        // 8. Also add task when Enter key is pressed
        inputField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addButton.doClick();
                }
            }
        });

        // 9. Show the window
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
