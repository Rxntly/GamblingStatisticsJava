import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LanguageHandler {
    private static String language;

    public static void setLanguage(String lang) {
        language = lang;
    }

    public static void PickLangGUI() {
        JFrame frame = new JFrame("Language Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1));

        // Creating buttons
        JButton englishButton = new JButton("English");
        JButton russianButton = new JButton("Russian");
        JButton arabicButton = new JButton("Arabic");
        JButton frenchButton = new JButton("French");

        // Adding ActionListener to each language

        englishButton.addActionListener(languageSelectionListener("English", frame));
        russianButton.addActionListener(languageSelectionListener("Russian", frame));
        arabicButton.addActionListener(languageSelectionListener("Arabic", frame));
        frenchButton.addActionListener(languageSelectionListener("French", frame));

        // Adding the buttons to the panel

        panel.add(englishButton);
        panel.add(arabicButton);
        panel.add(frenchButton);
        panel.add(russianButton);

        // Adding the buttons panel to the GUI

        frame.getContentPane().add(panel);

        frame.setVisible(true);
    }

    private static ActionListener languageSelectionListener(String lang, JFrame frame) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                language = lang;
                frame.dispose();
                SwingUtilities.invokeLater(() -> expo.mainGUI());
            }
        };
    }

    public static String getWindowTitle() {
        switch (language) {
            case "Russian":
                return "Имитация азартных игр";
            case "Arabic":
                return "محاكاة القمار";
            case "French":
                return "Simulation de jeu";
            default:
                return "Gambling Simulation";
        }
    }

    public static String getBetLabel() {
        switch (language) {
            case "Russian":
                return "Сумма ставки ($) :";
            case "Arabic":
                return " مبلغ الرهان ($) ";
            case "French":
                return "Montant de la mise ($) :";
            default:
                return "Bet Amount ($) :";
        }
    }

    public static String getHouseEdgeLabel() {
        switch (language) {
            case "Russian":
                return "Преимущество казино (%):";
            case "Arabic":
                return "حافة البيت (%)";
            case "French":
                return "Avantage de la maison (%) :";
            default:
                return "House Edge (%):";
        }
    }

    public static String getBalanceLabel() {
        switch (language) {
            case "Russian":
                return "Общий баланс :";
            case "Arabic":
                return "الرصيد الإجمالي ";
            case "French":
                return "Solde total :";
            default:
                return "Total Balance :";
        }
    }

    public static String getBetsLabel() {
        switch (language) {
            case "Russian":
                return "Всего ставок :";
            case "Arabic":
                return "مجموع الرهانات ";
            case "French":
                return "Total des paris :";
            default:
                return "Total Bets :";
        }
    }

    public static String getSimulateButtonText() {
        switch (language) {
            case "Russian":
                return "Симулировать";
            case "Arabic":
                return "محاكاة";
            case "French":
                return "Simuler";
            default:
                return "Simulate";
        }
    }

    public static String getValidationMessage() {
        switch (language) {
            case "Russian":
                return "Пожалуйста, введите положительные значения. Преимущество казино должно быть от 0 до 100.";
            case "Arabic":
                return "الرجاء إدخال قيم إيجابية. يجب أن تكون حافة البيت بين 0 و 100.";
            case "French":
                return "Veuillez saisir des valeurs positives. L'avantage de la maison doit être compris entre 0 et 100.";
            default:
                return "Please enter positive values. House edge must be between 0 and 100.";
        }
    }

    public static String getBetAmountExceedMessage() {
        switch (language) {
            case "Russian":
                return "Сумма ставки не может быть больше общего баланса. Пожалуйста, введите заново.";
            case "Arabic":
                return "لا يمكن أن يكون مبلغ الرهان أكبر من الرصيد الإجمالي. يرجى إعادة الإدخال.";
            case "French":
                return "Le montant de la mise ne peut pas être supérieur au solde total. Veuillez réessayer.";
            default:
                return "Bet amount cannot be greater than total balance. Please re-enter.";
        }
    }

    public static String getValidNumberMessage() {
        switch (language) {
            case "Russian":
                return "Пожалуйста, введите допустимые числа во всех полях.";
            case "Arabic":
                return "الرجاء إدخال أرقام صالحة في جميع الحقول.";
            case "French":
                return "Veuillez saisir des chiffres valides dans tous les champs.";
            default:
                return "Please enter valid numbers in all fields.";
        }
    }

    public static String getSimulationResultsTitle() {
        switch (language) {
            case "Russian":
                return "Результаты симуляции";
            case "Arabic":
                return "نتائج المحاكاة";
            case "French":
                return "Résultats de la simulation";
            default:
                return "Simulation Results";
        }
    }

    public static String getTotalProfitLossLabel() {
        switch (language) {
            case "Russian":
                return "Общий доход/убыток: ";
            case "Arabic":
                return "إجمالي الربح / الخسارة: ";
            case "French":
                return "Profit / Perte totale :";
            default:
                return "Total Profit/Loss: ";
        }
    }

    public static String getTotalBetsLabel() {
        switch (language) {
            case "Russian":
                return "Всего ставок: ";
            case "Arabic":
                return "مجموع الرهانات: ";
            case "French":
                return "Total des paris: ";
            default:
                return "Total Bets: ";
        }
    }

    public static String getTotalWinsLabel() {
        switch (language) {
            case "Russian":
                return "Всего побед: ";
            case "Arabic":
                return "إجمالي الانتصارات: ";
            case "French":
                return "Total des victoires: ";
            default:
                return "Total Wins: ";
        }
    }

    public static String getTotalLossesLabel() {
        switch (language) {
            case "Russian":
                return "Общие потери: ";
            case "Arabic":
                return "إجمالي الخسائر: ";
            case "French":
                return "Total des pertes: ";
            default:
                return "Total Losses: ";
        }
    }

    public static String getLongestWinStreakLabel() {
        switch (language) {
            case "Russian":
                return "Самая длинная серия побед: ";
            case "Arabic":
                return "أطول سلسلة انتصارات: ";
            case "French":
                return "La plus longue série de victoires: ";
            default:
                return "Longest Win Streak: ";
        }
    }

    public static String getLongestLossStreakLabel() {
        switch (language) {
            case "Russian":
                return "Самая длинная серия потери: ";
            case "Arabic":
                return "أطول سلسلة خسائر: ";
            case "French":
                return "La plus longue série de pertes: ";
            default:
                return "Longest Loss Streak: ";
        }
    }

    public static String getTotalWageredLabel() {
        switch (language) {
            case "Russian":
                return "Общая сумма ставок: ";
            case "Arabic":
                return "إجمالي المراهنات: ";
            case "French":
                return "Total des paris: ";
            default:
                return "Total Wagered: ";
        }
    }

    public static String getBetNumberLabel() {
        switch (language) {
            case "Russian":
                return "Номер ставки";
            case "Arabic":
                return "رقم الرهان";
            case "French":
                return "Numéro de mise";
            default:
                return "Bet Number";
        }
    }

    public static String getBalanceLabelHeader() {
        switch (language) {
            case "Russian":
                return "Баланс";
            case "Arabic":
                return "الرصيد";
            case "French":
                return "Solde";
            default:
                return "Balance";
        }
    }

    public static String getOutcomeLabel() {
        switch (language) {
            case "Russian":
                return "Результат";
            case "Arabic":
                return "النتيجة";
            case "French":
                return "Résultat";
            default:
                return "Outcome";
        }
    }

    public static String getStartLabel() {
        switch (language) {
            case "Russian":
                return "Начало";
            case "Arabic":
                return "بداية";
            case "French":
                return "Début";
            default:
                return "Start";
        }
    }

    public static String getOutcomeWinLabel() {
        switch (language) {
            case "Russian":
                return "Победа";
            case "Arabic":
                return "فوز";
            case "French":
                return "Gagner";
            default:
                return "Win";
        }
    }

    public static String getOutcomeLossLabel() {
        switch (language) {
            case "Russian":
                return "Потеря";
            case "Arabic":
                return "الخسارة";
            case "French":
                return "Perte";
            default:
                return "Loss";
        }
    }
}
