import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;



public class AboutController extends Controller implements Initializable {

    @FXML
    private Label authorLabel;

    @FXML
    private Label textLabel;
    @FXML
    private javafx.scene.control.Button btnExit;

    Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
            set();
    }

    public void set(){
        locales = new Locale("us");
        bundles = ResourceBundle.getBundle("messages",locales);
        authorLabel.setText(bundles.getString("author"));
    }
}
