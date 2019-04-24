package sample;

import Connectvy.ConnectionClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Controller_ArchiveDate {

    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    @FXML
    private JFXDatePicker Date_Archive;
    @FXML
    private JFXButton Btn_Archive_ArchiceDate;
    private int count_Language;
    @FXML
    private AnchorPane Archive_Window;
    String INVOICE_NBER = "DD";

    public void Set_count_Language(int c) {
        count_Language = c;

    }

    @FXML
    private void M_Btn_Archive_ArchiceDate(ActionEvent event) {

        if (Date_Archive.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);

            if (count_Language == 0) {
                alert.setContentText("Please enter the value");
            } else {
                alert.setContentText("الرجاء إدخال القيمة");

            }
            alert.showAndWait();

        } else {
            try {
                String query2 = "SELECT * FROM `maintenance_operation` ";
                ResultSet rs3 = connectionClass.execQuery(query2);

                String query = "SELECT * FROM `maintenance_operation` Where `STARTING_DATE` < \"" + Date_Archive.getValue() + "\" ";
                ResultSet rs = connectionClass.execQuery(query);
                int rowcount3 = 0;
                if (rs3.last()) {
                    rowcount3 = rs3.getRow();
                }
                int rowcount = 0;
                if (rs.last()) {
                    rowcount = rs.getRow();
                }
                if (rowcount == rowcount3) {
                    Alert alert3 = new Alert(Alert.AlertType.WARNING);
                    alert3.setTitle(null);
                    alert3.setHeaderText(null);
                    if (count_Language == 0) {
                        alert3.setContentText("You can not archive all MO");
                    } else {
                        alert3.setContentText("لاتستطيع ارشفة جميع عمليات الصيانة");

                    }

                    alert3.showAndWait();

                } else {

                    if (rowcount > 0) {
                        rs.beforeFirst();
                        while (rs.next()) {

                            String query_requier = "SELECT * FROM `require` Where `MO_NBER` =" + rs.getString("MO_NBER");
                            ResultSet rs2 = connectionClass.execQuery(query_requier);

                            INVOICE_NBER = rs.getString("INVOICE_NBER");
                            String insert_to_MO;
                            if (INVOICE_NBER != null) {

                                insert_to_MO = "INSERT INTO `maintenance_operation_backup` VALUES(" + rs.getString("MO_NBER") + "," + "'" + rs.getString("STATE") + "'" + "," + "'" + rs.getString("MO_COST")
                                        + "'" + "," + "'" + rs.getString("SP_COST") + "'" + "," + "'" + rs.getString("STARTING_DATE") + "'" + "," + "'" + rs.getString("ENDING_DATE") + "'" + "," + "'"
                                        + rs.getString("WARRANTY") + "'" + "," + "'" + rs.getString("PROBLEM_DESC") + "'" + "," + "'" + rs.getString("DEVICE_SN") + "'" + "," + "'" + rs.getString("DEVICE_DESC")
                                        + "'" + "," + "'" + rs.getString("EMPLOYEE_ID") + "'" + "," + "'" + rs.getString("CUS_MOBILE_NBER") + "','" + rs.getString("INVOICE_DATE") + "'," + rs.getString("INVOICE_NBER") + ")";
                            } else {
                                insert_to_MO = "INSERT INTO `maintenance_operation_backup` VALUES(" + rs.getString("MO_NBER") + "," + "'" + rs.getString("STATE") + "'" + "," + "'" + rs.getString("MO_COST")
                                        + "'" + "," + "'" + rs.getString("SP_COST") + "'" + "," + "'" + rs.getString("STARTING_DATE") + "'" + "," + "'" + rs.getString("ENDING_DATE") + "'" + "," + "'"
                                        + rs.getString("WARRANTY") + "'" + "," + "'" + rs.getString("PROBLEM_DESC") + "'" + "," + "'" + rs.getString("DEVICE_SN") + "'" + "," + "'" + rs.getString("DEVICE_DESC")
                                        + "'" + "," + "'" + rs.getString("EMPLOYEE_ID") + "'" + "," + "'" + rs.getString("CUS_MOBILE_NBER") + "'," + "NULL" + "," + "NULL" + ")";
                            }
                            java.sql.Statement statement1 = connection.createStatement();

                            statement1.executeUpdate(insert_to_MO);
                            while (rs2.next()) {

                                String insert_to_requir = "INSERT INTO `require_backup` VALUES(" + rs2.getString("MO_NBER") + ",'" + rs2.getString("SP_NBER") + "','"
                                        + rs2.getString("Seq_Nber") + "','" + rs2.getString("SERIAL_NUMBER") + "'" + ",'" + rs2.getString("Effective_Price") + "')";
                                statement1.executeUpdate(insert_to_requir);
                            }
                            String deletSP = "DELETE FROM  `require` " + " WHERE MO_NBER= " + rs.getString("MO_NBER");
                            String sql1 = "DELETE FROM  `maintenance_operation` " + " WHERE MO_NBER= " + rs.getString("MO_NBER");
                            statement1.executeUpdate(deletSP);
                            statement1.executeUpdate(sql1);
                        }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(null);
                        alert.setHeaderText(null);

                        if (count_Language == 0) {
                            alert.setContentText("Successfully archived");
                        } else {

                            alert.setContentText("تم الأرشفة بنجاح");

                        }
                        alert.showAndWait();
                        Stage stage2 = (Stage) Archive_Window.getScene().getWindow();
                        stage2.close();

                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle(null);
                        alert.setHeaderText(null);

                        if (count_Language == 0) {
                            alert.setContentText("There are no maintenance operations before the specified date");
                        } else {

                            alert.setContentText("لا توجد عمليات صيانة قبل التاريخ المحدد");

                        }
                        alert.showAndWait();

                    }

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }
    }
}
