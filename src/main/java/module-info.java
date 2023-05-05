module com.core.mcprojetbibliotheque {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.io;
    requires AnimateFX;
    requires net.dv8tion.jda;
	requires okhttp3;

    opens com.core.mcprojetbibliotheque to javafx.fxml;
    exports com.core.mcprojetbibliotheque;
    exports com.core.mcprojetbibliotheque.Controller;
    opens com.core.mcprojetbibliotheque.Controller to javafx.fxml;
    exports com.core.mcprojetbibliotheque.Model;
    opens com.core.mcprojetbibliotheque.Model to javafx.fxml;
    exports com.core.mcprojetbibliotheque.Utils;
    opens com.core.mcprojetbibliotheque.Utils to javafx.fxml;
}