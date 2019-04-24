package sample;

import Connectvy.ConnectionClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Controller_ChangePassword {

    @FXML
    private JFXPasswordField Txfiled_OldPassword_Tools;
    @FXML
    private JFXPasswordField Txfiled_NewPassword_Tools;
    @FXML
    private JFXPasswordField Txfiled_ConfirmPassword_Tools;
    @FXML
    private JFXButton Btn_Save_Tools;
    @FXML
    private AnchorPane ChangePassword_Window;
    private int count_Language;
    public int Em_Id;
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    public void Set_count_Language(int c) {
        count_Language = c;

    }

    @FXML
    private void M_Btn_Save_Tools(ActionEvent event) {
        try {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle(null);
            alert2.setHeaderText(null);

            Statement st3 = connection.createStatement();
            String Query = "SELECT * FROM `employee` WHERE EMPLOYEE_ID=" + Em_Id;
            st3.executeQuery(Query);
            ResultSet rs3 = st3.getResultSet();
            if (rs3.first()) {

                if (rs3.getString("PASSWORD").equals(Txfiled_OldPassword_Tools.getText())) {

                    if (Txfiled_NewPassword_Tools.getText().isEmpty() == true) {
                        if (count_Language == 0) {
                            alert2.setContentText("Please, enter your new password");

                        } else {

                            alert2.setContentText("الرجاء, ادخال كلمة المرور الجديدة");

                        }
                        alert2.showAndWait();

                    } else {
                        if (Txfiled_NewPassword_Tools.getText().equals(Txfiled_ConfirmPassword_Tools.getText())) {

                            String sql1 = "UPDATE  `employee` SET   PASSWORD='" + Txfiled_ConfirmPassword_Tools.getText() + "' Where EMPLOYEE_ID=" + Em_Id;
                            java.sql.Statement statement1 = connection.createStatement();
                            statement1.executeUpdate(sql1);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(null);
                            alert.setHeaderText(null);

                            if (count_Language == 0) {
                                alert.setContentText("Password changed successfully");
                            } else {

                                alert.setContentText("تم تغير كلمة المرور بنجاح");

                            }
                            alert.showAndWait();
                            Stage stage2 = (Stage) ChangePassword_Window.getScene().getWindow();
                            stage2.close();

                        } else {
                            if (count_Language == 0) {
                                alert2.setContentText("Please make sure the passwords entered match");
                            } else {

                                alert2.setContentText("الرجاء, التأكد من تطابق كلمتي المرور المدخلة");

                            }

                            alert2.showAndWait();

                        }
                    }

                } else {
                    if (count_Language == 0) {
                        alert2.setContentText("The password is incorrect");
                    } else {

                        alert2.setContentText("كلمة المرور غير صحيحة");

                    }

                    alert2.showAndWait();

                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
