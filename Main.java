import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Main extends Application {
    private Bank bank = new Bank();  // Bank instance for operations

    @Override
    public void start(Stage primaryStage) {
        // Create TextField for amount input
        TextField amountTextField = new TextField();
        amountTextField.setPromptText("Enter Amount");

        // Create Labels
        Label balanceLabel = new Label("Current Balance: " + bank.getBalance());

        // Create Buttons for operations
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");

        // Event handler for Deposit Button
        depositButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountTextField.getText());
                BankOperationThread depositThread = new BankOperationThread("deposit", amount, bank);
                depositThread.start();
                balanceLabel.setText("Current Balance: " + bank.getBalance());
            } catch (NumberFormatException ex) {
                balanceLabel.setText("Invalid amount. Please enter a valid number.");
            }
        });

        // Event handler for Withdraw Button
        withdrawButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountTextField.getText());
                BankOperationThread withdrawThread = new BankOperationThread("withdraw", amount, bank);
                withdrawThread.start();
                balanceLabel.setText("Current Balance: " + bank.getBalance());
            } catch (NumberFormatException ex) {
                balanceLabel.setText("Invalid amount. Please enter a valid number.");
            }
        });

        // Layout for the UI
        VBox layout = new VBox(10);  // Vertical layout with spacing of 10px
        HBox buttonLayout = new HBox(10); // Horizontal layout for buttons
        buttonLayout.getChildren().addAll(depositButton, withdrawButton);

        layout.getChildren().addAll(amountTextField, buttonLayout, balanceLabel);

        // Scene and Stage
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setTitle("Multi-Threaded Banking App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
