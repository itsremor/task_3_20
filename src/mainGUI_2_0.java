import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

public class mainGUI_2_0 extends JDialog{
    private JPanel programm;
    private JScrollPane List;
    private JScrollPane Queue;
    private JButton buttonLoadFromFile;
    private JButton buttonExitProgramm;
    private JButton buttonListToQueue;
    private JTable inpetTable;
    private JTable outputTable;
    private JButton buttonRemoveList;
    private JButton buttonRemoveQueue;
    private JLabel version;

    private Comparator<String> comparator = new MyComporator();

    private OwnQueue myQueue = new OwnQueue();
    private LinkedList<String> myList = new LinkedList<>();


    public mainGUI_2_0() {

        final int[] counter = {0};
        final int[] counter_queue = {0};

        setContentPane(programm);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        Object[] columns = {'№', "Слово"};

        DefaultTableModel tableInput = new DefaultTableModel();
        tableInput.setColumnIdentifiers(columns);
        DefaultTableModel tableOutput = new DefaultTableModel();

        tableOutput.setColumnIdentifiers(columns);
        inpetTable.setModel(tableInput);
        outputTable.setModel(tableOutput);

        Object[] row = new Object[2];

        buttonLoadFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();

                    try {
                        Scanner scn = new Scanner(file);
                        scn.useDelimiter("[^а-яА-Яa-zA-Z0-9_]+");

                        while (scn.hasNext()) {
                            myList.add(scn.next());
                            row[0] = counter[0] + 1;
                            row[1] = myList.get(counter[0]);
                            counter[0]++;
                            tableInput.addRow(row);
                        }

                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }

                }
        }
    });

        buttonRemoveList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (counter[0] > 0) {
                    tableInput.removeRow(tableInput.getRowCount() - 1);
                    counter[0]--;
                }
                myList.clear();
            }
        });

        buttonListToQueue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                while (counter_queue[0] > 0) {
                    tableOutput.removeRow(tableOutput.getRowCount() - 1);
                    counter_queue[0]--;
                }

                for (int i = 0; i < myList.size(); i++){
                    myQueue.add(myList.get(i));
                }

                for (int i = 0; i < myList.size(); i++){

                    row[0] = counter_queue[0] + 1;
                    row[1] = myQueue.peek();
                    counter_queue[0]++;
                    tableOutput.addRow(row);

                    myQueue.poll();
                }

            }
        });

        buttonRemoveQueue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (counter_queue[0] > 0) {
                    tableOutput.removeRow(tableOutput.getRowCount() - 1);
                    counter_queue[0]--;
                }
            }
        });

        buttonExitProgramm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
    }
}