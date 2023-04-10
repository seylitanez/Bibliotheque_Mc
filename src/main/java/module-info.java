module com.core.mcprojetbibliotheque {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.core.mcprojetbibliotheque to javafx.fxml;
    exports com.core.mcprojetbibliotheque;
    exports com.core.mcprojetbibliotheque.Controller;
    opens com.core.mcprojetbibliotheque.Controller to javafx.fxml;
    exports com.core.mcprojetbibliotheque.Model;
    opens com.core.mcprojetbibliotheque.Model to javafx.fxml;
}