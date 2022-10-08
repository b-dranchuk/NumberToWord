package com.dranchuk.numbertoword;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.function.UnaryOperator;

import static com.dranchuk.numbertoword.Converter.buildString;
import static com.dranchuk.numbertoword.Setting.*;

public class Controller {
    @FXML
    private CheckMenuItem centByWord;
    @FXML
    private CheckMenuItem capitalizedText;
    @FXML
    private CheckMenuItem firstCapitalized;
    @FXML
    private TextField currencyField1;
    @FXML
    private Menu languageMenu;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField sumField;
    @FXML
    private CheckBox vatFlag;
    @FXML
    private TextField vatField;
    @FXML
    private TextField currencyField;
    @FXML
    private CheckBox vatByNumber;
    @FXML
    private CheckBox vatByWord;
    @FXML
    private CheckBox vatInSum;
    @FXML
    private CheckBox vatOutSum;
    @FXML
    private TextArea resultField;
    @FXML
    private CheckMenuItem serviceIsOn;
    ClipBoardListener clipBoardListener = new ClipBoardListener();


    private static final ToggleGroup langGroup = new ToggleGroup();

    @FXML
    private void initialize() {
        for (Enum<?> val : Language.values()) {
            RadioMenuItem r = new RadioMenuItem(Language.values()[val.ordinal()].getName());
            r.setId(val.name());
            r.setToggleGroup(langGroup);
            languageMenu.getItems().add(r);
        }
        langGroup.selectedToggleProperty().addListener(
                (observable, oldValue, newValue) -> setLanguage(Language.valueOf((((RadioMenuItem) newValue).getId()))));
        langGroup.getToggles().get(0).setSelected(true);

        vatByNumber.selectedProperty().addListener((observable, oldValue, newValue) -> {
            setVatByNumber(newValue);
            vatByWord.setSelected(oldValue);
        });
        vatByWord.selectedProperty().addListener((observable, oldValue, newValue) -> vatByNumber.setSelected(oldValue));
        vatByNumber.setSelected(true);

        vatInSum.selectedProperty().addListener((observable, oldValue, newValue) -> {
            setAddVat(newValue);
            vatOutSum.setSelected(oldValue);
        });
        vatOutSum.selectedProperty().addListener((observable, oldValue, newValue) -> vatInSum.setSelected(oldValue));
        vatInSum.setSelected(true);

        vatFlag.selectedProperty().addListener((observable, oldValue, newValue) -> setVat(newValue));
        vatFlag.setSelected(true);
        centByWord.selectedProperty().addListener((observable, oldValue, newValue) -> setCentByWord(newValue));
        capitalizedText.selectedProperty().addListener((observable, oldValue, newValue) -> setCapitalizedText(newValue));
        firstCapitalized.selectedProperty().addListener((observable, oldValue, newValue) -> setFirstCapitalized(newValue));

        UnaryOperator<TextFormatter.Change> doubleFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("([1-9][0-9]*[.,]?[0-9]*)?")) {
                return change;
            }
            return null;
        };

        priceField.setTextFormatter(new TextFormatter<>(doubleFilter));
        quantityField.setTextFormatter(new TextFormatter<>(doubleFilter));
        sumField.setTextFormatter(new TextFormatter<>(doubleFilter));
        vatField.setTextFormatter(new TextFormatter<>(doubleFilter));

        currencyField.textProperty().addListener((observableValue, s, t1) -> setCurrency(t1));
        currencyField1.textProperty().addListener((observableValue, s, t1) -> setCurrency1(t1));
        setCurrency("грн.");
        setCurrency1("коп.");

        priceField.textProperty().addListener((observableValue, s, t1) -> ChangeSum());
        quantityField.textProperty().addListener((observableValue, s, t1) -> ChangeSum());
        vatFlag.selectedProperty().addListener((observableValue, s, t1) -> {
            ChangeSum();
            setVat(t1);
        });
        vatField.textProperty().addListener((observableValue, s, t1) -> ChangeSum());

        serviceIsOn.selectedProperty().addListener((observable, oldValue, newValue) -> clipBoardListener.setEnough(oldValue));
        clipBoardListener.start();
    }

    @FXML
    private void onTransformButtonClick() {
        double sum = Double.parseDouble(sumField.getText().replaceAll(",", "."));
        if (!vatField.getText().equals("")) {
            resultField.setText(buildString(sum, Double.parseDouble(vatField.getText().replaceAll(",", "."))));
        } else {
            resultField.setText(buildString(sum, 0));
        }
    }

    @FXML
    private void ChangeSum() {
        if (!priceField.getText().isBlank() & !quantityField.getText().isBlank()) {
            double result = Double.parseDouble(priceField.getText()) * Integer.parseInt(quantityField.getText());
            sumField.setText(String.format("%.2f", result));
        }
    }

    public void onAboutButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Про програму");
        alert.setHeaderText("Дана програма допомагає \nпереводити числа в пропис");
        alert.setContentText("Сервіс роботи з буфером обміну підхоплює підтримуваний шаблон, " +
                "згідно налаштувань програми його обробляє, та вставляє результат до буферу." +
                "\n\nТобто згорнувши програму можна будь-де набрати шаблон," +
                "виділити, і послідовним натисканням Ctrl+C та Ctrl+V" +
                "отримати результат на місці шаблону" +
                "\n\nНаразі підтримувані шаблони:" +
                "\n\n(Ціна) (кількість) (ставка ПДВ) - \"100.50 5 20\"" +
                "\nбуде порахована сума ціна*кількість та додано ПДВ");

        alert.showAndWait();
    }
}