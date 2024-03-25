import javax.swing.*;

public class Expo {
    static double betAmount;
    static double houseEdge;
    static double initialBalance;
    static double totalBalance;
    static int totalBets;
    static double[] results;
    static JButton button;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::createAndShowGUI);
    }
}
