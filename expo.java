import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class expo {
    private static double betAmount;
    private static double houseEdge;
    private static double initialBalance;
    private static double totalBalance;
    private static int totalBets;
    private static double[] results;
    private static JButton button;
    private static String language;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> LanguageHandler.PickLangGUI());
    }

    public static void mainGUI() {
        JFrame frame = new JFrame(LanguageHandler.getWindowTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));


        JTextField betField = new JTextField();
        JTextField edgeField = new JTextField();
        JTextField balanceField = new JTextField();
        JTextField betsField = new JTextField();

        inputPanel.add(new JLabel(LanguageHandler.getBetLabel()));
        inputPanel.add(betField);
        inputPanel.add(new JLabel(LanguageHandler.getHouseEdgeLabel()));
        inputPanel.add(edgeField);
        inputPanel.add(new JLabel(LanguageHandler.getBalanceLabel()));
        inputPanel.add(balanceField);
        inputPanel.add(new JLabel(LanguageHandler.getBetsLabel()));
        inputPanel.add(betsField);

        button = new JButton(LanguageHandler.getSimulateButtonText());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    betAmount = Double.parseDouble(betField.getText());
                    houseEdge = Double.parseDouble(edgeField.getText());
                    totalBalance = Double.parseDouble(balanceField.getText());
                    initialBalance = totalBalance;
                    totalBets = Integer.parseInt(betsField.getText());

                    if (betAmount <= 0 || houseEdge < 0 || houseEdge > 100 || totalBalance <= 0 || totalBets <= 0) {
                        JOptionPane.showMessageDialog(frame, LanguageHandler.getValidationMessage());
                        return;
                    }

                    if (betAmount > totalBalance) {
                        JOptionPane.showMessageDialog(frame, LanguageHandler.getBetAmountExceedMessage());
                        return;
                    }

                    button.setEnabled(false);
                    simulate();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, LanguageHandler.getValidNumberMessage());
                }
            }
        });

        JButton returnButton = new JButton("↩");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LanguageHandler.PickLangGUI();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(button);

        JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        returnPanel.add(returnButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(returnPanel, BorderLayout.WEST);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private static void simulate() {
        results = new double[totalBets];
        Random random = new Random();

        int winCount = 0;
        int lossCount = 0;
        int currentWinStreak = 0;
        int currentLossStreak = 0;
        int longestWinStreak = 0;
        int longestLossStreak = 0;
        double totalWagered = 0;

        for (int i = 0; i < totalBets; i++) {
            if (totalBalance <= 0) {
                results = Arrays.copyOf(results, i);
                break;
            }

            boolean win = random.nextDouble() < (0.5 - houseEdge / 100);

            totalWagered += betAmount;

            if (win) {
                totalBalance += betAmount;
                winCount++;
                currentWinStreak++;
                currentLossStreak = 0;
                if (currentWinStreak > longestWinStreak) {
                    longestWinStreak = currentWinStreak;
                }
            } else {
                totalBalance -= betAmount;
                lossCount++;
                currentLossStreak++;
                currentWinStreak = 0;
                if (currentLossStreak > longestLossStreak) {
                    longestLossStreak = currentLossStreak;
                }
            }

            results[i] = totalBalance;
        }

        button.setEnabled(true);
        displayResults(winCount, lossCount, totalWagered, longestWinStreak, longestLossStreak);
    }

    private static void displayResults(int winCount, int lossCount, double totalWagered, int longestWinStreak, int longestLossStreak) {
        JFrame resultFrame = new JFrame(LanguageHandler.getSimulationResultsTitle());
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setSize(600, 400);
        resultFrame.setLocationRelativeTo(null);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(0, 2));

        statsPanel.add(new JLabel(LanguageHandler.getTotalProfitLossLabel() + (totalBalance - initialBalance)));
        statsPanel.add(new JLabel(LanguageHandler.getTotalBetsLabel() + results.length));
        statsPanel.add(new JLabel(LanguageHandler.getTotalWinsLabel() + winCount));
        statsPanel.add(new JLabel(LanguageHandler.getTotalLossesLabel() + lossCount));
        statsPanel.add(new JLabel(LanguageHandler.getLongestWinStreakLabel() + longestWinStreak));
        statsPanel.add(new JLabel(LanguageHandler.getLongestLossStreakLabel() + longestLossStreak));
        statsPanel.add(new JLabel(LanguageHandler.getTotalWageredLabel() + totalWagered));

        Object[] columnNames = {LanguageHandler.getBetNumberLabel(), LanguageHandler.getBalanceLabelHeader(), LanguageHandler.getOutcomeLabel()};
        Object[][] rowData = new Object[results.length][3];

        for (int i = 0; i < results.length; i++) {
            rowData[i][0] = i + 1;
            rowData[i][1] = results[i];
            if (i == 0) {
                rowData[i][2] = LanguageHandler.getStartLabel();
            } else {
                rowData[i][2] = results[i] > results[i - 1] ? LanguageHandler.getOutcomeWinLabel() : LanguageHandler.getOutcomeLossLabel();
            }
        }

        DefaultTableModel model = new DefaultTableModel(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                if (column == 2) {
                    String value = (String) getModel().getValueAt(row, column);
                    if (LanguageHandler.getOutcomeWinLabel().equals(value)) {
                        component.setBackground(Color.GREEN);
                    } else if (LanguageHandler.getOutcomeLossLabel().equals(value)) {
                        component.setBackground(Color.RED);
                    } else {
                        component.setBackground(Color.WHITE);
                    }
                } else {
                    component.setBackground(Color.WHITE);
                }
                return component;
            }
        };

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);

        resultFrame.setLayout(new BorderLayout());
        resultFrame.add(statsPanel, BorderLayout.NORTH);
        resultFrame.add(scrollPane, BorderLayout.CENTER);
        resultFrame.setVisible(true);
    }
}
