
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.jvm.hotspot.oops.Array;
import sun.jvm.hotspot.oops.Instance;

import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.lang.String;

import java.net.URL;
import java.text.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Callable;


public class Controller implements Initializable{

    @FXML
    private Text itemValueText;

    @FXML
    private TextField knapsackCapacityTextField;

    @FXML
    private TextField itemValueTestField;

    @FXML
    private TextField itemWeightTextField;

    @FXML
    private Text chodeAlgoritmText;
    @FXML
    private Button solveButton;

    @FXML
    private Button addItemButton;

    @FXML
    private Label knapsackCapacityLabel;

    @FXML
    private Label itemValueLabel;

    @FXML
    private Label itemWeightLabel;

    @FXML
    private Label choiceAlgorithmLabel;

    @FXML
    private Button addCapacityButton;

    @FXML
    private Menu programMenuBar;

    @FXML
    private MenuItem exitMenu;

    @FXML
    private MenuItem aboutMenu;

    @FXML
    private MenuItem ukMenuItem;

    @FXML
    private MenuItem usMenuItem;

    @FXML
    private Menu languageChoice;

    @FXML
    private MenuItem plMennuItem;

    @FXML
    private TableView<Item> itemTable;

    @FXML
    private TableColumn<Item, String> weightTableColumn;

    @FXML
    private TableColumn<Item, Float> valueTableColumn;

    @FXML
    private TableColumn<Item, Integer> numberTableColumn;

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Label dateTime;

    @FXML
    private Label solutionCapacityLabel;

    @FXML
    private Label solutionPriceLabel;

    @FXML
    private Button solutionGreedy;

    @FXML
    private Button solutionRandom;
    @FXML
    private TextField solutionKnapsackPrice;


    public ObservableList<Item> itemList ;
    public ListProperty<Item> listProperty;
    public Item item;
    public Instancja j = new Instancja();
    public ResourceBundle bundles;
    public Locale locales = Locale.getDefault();

    @FXML
    private TextField solutionKnapsackCapacity;
    @FXML
    private TextArea solutionTextArea;

    public NumberFormat numberFormat;


    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {


        loadLang("pl");
        valueTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        weightTableColumn.setCellValueFactory(new PropertyValueFactory<>("Weight"));
        //itemTable.setItems(observableList);
        numberFormat = NumberFormat.getInstance(locales);
        



    }


        public ObservableList<Item> observableList = FXCollections.observableArrayList();




   @FXML
    public void addItemClicked(MouseEvent mouseEvent) {


       String value = itemValueTestField.getText();
       String value2 = itemWeightTextField.getText();


       if (value.isEmpty() && value2.isEmpty()) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           ButtonType buttonTypeOne = new ButtonType("OK");
           alert.getButtonTypes().setAll(buttonTypeOne);
           alert.showAndWait();
           return;
       } else {


        Item item = new Item(Float.parseFloat(itemValueTestField.getText()),Integer.parseInt(itemWeightTextField.getText()));
        itemTable.getItems().add(item);
         j.addItems(item);
        numberTableColumn.setSortable(false);
        numberTableColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Integer>(itemTable.getItems().indexOf(column.getValue())));




       }
   }



     @FXML
    public void solveClicked(MouseEvent event){


         Greedy greedy = new Greedy();

         comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

             @Override
             public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                 if (arg1.equals("zachlanny")) {
                     System.out.println("Selected employee: ");
                 }
             }
         });



     }


    @FXML
    void randomClicked(MouseEvent event) {

        solutionTextArea.clear();
        solutionKnapsackPrice.clear();
        solutionKnapsackCapacity.clear();

        Greedy greedy = new Greedy();
        String value = Arrays.toString(greedy.greedyMethod(j).toArray());
        int tmp = greedy.Capacity;

        if(tmp==0){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            ButtonType buttonTypeOne = new ButtonType("OK");
            alert.getButtonTypes().setAll(buttonTypeOne);
            alert.showAndWait();
            return;
        }
        else{
            solutionTextArea.setText(value);
            solutionKnapsackCapacity.setText(Integer.toString(greedy.Capacity));
            solutionKnapsackPrice.setText(Integer.toString(greedy.Price));
        }


    }
    @FXML
    void greedyClicked(MouseEvent event) {


       solutionTextArea.clear();
       solutionKnapsackPrice.clear();
       solutionKnapsackCapacity.clear();


        RandomSearch randomSearch = new RandomSearch();
        String value = Arrays.toString(randomSearch.randMethood(j).toArray());
        int tmp = randomSearch.Capacity;
        if(tmp==0){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            ButtonType buttonTypeOne = new ButtonType("OK");
            alert.getButtonTypes().setAll(buttonTypeOne);
            alert.showAndWait();
            return;
        }
        else {

            solutionTextArea.setText(value);
            solutionKnapsackCapacity.setText(Integer.toString(randomSearch.Capacity));
            solutionKnapsackPrice.setText(Integer.toString(randomSearch.Price));

        }

    }

    @FXML
    void addCapacityClicked(MouseEvent event) {

            String value = knapsackCapacityTextField.getText();
       if (value.isEmpty()) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           ButtonType buttonTypeOne = new ButtonType("OK");
           alert.getButtonTypes().setAll(buttonTypeOne);
           alert.showAndWait();
           return;
       }
         else {
            j.setKnapsackCapacity(Integer.parseInt(knapsackCapacityTextField.getText()));
       }
    }
    @FXML
    private Button btnExit;

    @FXML
    void aboutAction(ActionEvent event)throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AboutDemo.fxml"));
        Parent root = fxmlLoader.load();
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(new Scene(root));
        secondaryStage.show();



    }


    @FXML
    void exitAction(ActionEvent event) {

        Platform.exit();
        System.exit(0);

    }

    @FXML
    void ukAction(ActionEvent event) {

       loadLang("gb");


    }

    @FXML
    void usAction(ActionEvent event) {
         loadLang("us");

    }

    @FXML
    void polishAction(ActionEvent event) {

         loadLang("pl");
    }


    void loadLang(String lang){

         locales = new Locale(lang);
       bundles = ResourceBundle.getBundle("messages",locales);
       LocalDate date = LocalDate.now();

       aboutMenu.setText(bundles.getString("aboutMenu"));
       addItemButton.setText(bundles.getString("addItemButton"));
       choiceAlgorithmLabel.setText(bundles.getString("algorithmLabel"));
       programMenuBar.setText(bundles.getString("applicationMenu"));
       exitMenu.setText(bundles.getString("exitMenu"));
       ukMenuItem.setText(bundles.getString("GBMenu"));
       itemValueLabel.setText(bundles.getString("itemValueLabel"));
       itemWeightLabel.setText(bundles.getString("itemWeightLabel"));
       addCapacityButton.setText(bundles.getString("knapsackCapacityButton"));
       knapsackCapacityLabel.setText(bundles.getString("knapsackCapacityLabel"));
       languageChoice.setText(bundles.getString("languageChoice"));
       numberTableColumn.setText(bundles.getString("numberColumn"));
       plMennuItem.setText(bundles.getString("polishMenu"));
       solveButton.setText(bundles.getString("solveButton"));
       usMenuItem.setText(bundles.getString("USMenu"));
       valueTableColumn.setText(bundles.getString("valueColumn"));
       weightTableColumn.setText(bundles.getString("weightColumn"));
        solutionCapacityLabel.setText(bundles.getString("solutionCapacityLabel"));
        solutionPriceLabel.setText(bundles.getString("solutionPriceLabel"));
        solutionGreedy.setText(bundles.getString("solutionGreedy"));
        solutionRandom.setText(bundles.getString("solutionRandom"));
        numberFormat = NumberFormat.getInstance(locales);


        valueTableColumn.setCellFactory(tc -> new TableCell<Item, Float>() {

            @Override
            protected void updateItem(Float price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    setText(numberFormat.format(price));
                }
            }
        });


       DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, locales);
       dateTime.setText(df.format(new Date()));

       comboBox.getItems().clear();
        comboBox.getItems().addAll(bundles.getString("greedy"), bundles.getString("rs"));


    }



}




