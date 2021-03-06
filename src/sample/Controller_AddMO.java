package sample;

import Connectvy.ConnectionClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.DoubleStringConverter;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

public class Controller_AddMO implements Initializable {

    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);

    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    @FXML
    private JFXDatePicker Date_Warranty_AddMO;
    @FXML
    private JFXDatePicker Date_StartMo_AddMO;
    @FXML
    private JFXDatePicker Date_EndMO_AddMO;
    @FXML
    private JFXTextField Txfiled_SPCost_AddMO;
    @FXML
    private JFXTextField Txfiled_MOCost_AddMO;
    @FXML
    private JFXTextField Txfiled_VAT_AddMO;
    @FXML
    private JFXTextField Txfiled_TotalCost_AddMO;
    @FXML
    private JFXTextField Txfiled_MOnum_AddMO;
    @FXML
    private JFXTextArea Txfiled_ProplemDisc_AddMO;
    @FXML
    private ComboBox<String> Selct_Techichan_AddMO;
    @FXML
    private ComboBox<String> Selct_MoStatus_AddMO;
    ObservableList<String> ListOfStatus_EN = FXCollections.observableArrayList("created", "approved", "disapproved",
            "cannot be done", "under maintenance", "other defects has been detected", "repaired", "paid");
    ObservableList<String> ListOfStatus_AR = FXCollections.observableArrayList("تم الإنشاء", "تم الموافقة", "مرفوضة",
            "لا يمكن القيام بعملية الصيانة", "تحت الصيانة", "تم الكشف عن عيوب أخرى", "تم الاصلاح", "دفعت");

    ObservableList<String> ListOfTechichan = FXCollections.observableArrayList();

    @FXML
    private JFXTextField Txfiled_SpSerialN_AddMO;
    @FXML
    private JFXTextField Txfiled_SearchSP_AddMO;
    @FXML
    public JFXTextField Txfiled_CusName_AddMO;
    @FXML
    private JFXTextField Txfiled_CusMnum_AddMO;
    @FXML
    private JFXTextField Txfiled_DevSerialN_AddMO;
    @FXML
    private JFXTextField Txfiled_DevDiscription_AddMO;
    @FXML
    private JFXButton Btn_Print_AddMo;
    @FXML
    private JFXButton Btn_Cancel_AddMo;
    @FXML
    private JFXButton Btn_Delete_AddMo;
    @FXML
    private JFXButton Btn_Save_AddMo;
    @FXML
    private JFXButton Btn_Search_AddMo;
    @FXML
    private TableView<Controller_AddMO.AddSP> Table_AddSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.AddSP, String> Col_SPdisc_AddSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.AddSP, String> Col_SPname_AddSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.AddSP, Integer> Col_SPnum_AddSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.AddSP, Double> Col_SPprice_AddSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.AddSP, Double> Col_SPQuantity_AddSP_AddMO;

    ObservableList<Controller_AddMO.AddSP> list = FXCollections.observableArrayList();
    ObservableList<Controller_AddMO.SelectedSP> list2 = FXCollections.observableArrayList();
    ObservableList<Controller_AddMO.SelectedSP> loadlist = FXCollections.observableArrayList();
    @FXML
    private TableView<Controller_AddMO.SelectedSP> Table_SelectedSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.SelectedSP, String> Col_SPdisc_SelectedSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.SelectedSP, String> Col_SPname_SelectedSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.SelectedSP, Integer> Col_SPnum_SelectedSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.SelectedSP, String> Col_SPSN_SelectedSP_AddMO;

    ObservableList<Controller_AddMO.SelectedSP> SPSelected2, AllSP2;
    int count = 0;
    @FXML
    private TableColumn<Controller_AddMO.SelectedSP, Double> Col_SPprice_SelectedSP_AddMO;
    @FXML
    private JFXButton Btn_ReomveSP_AddMo;
    @FXML
    private JFXButton Btn_AddSP_AddMo;
    @FXML
    private JFXTextField Txfiled_SpPrice_AddMO;
    private int count_Language;
    @FXML
    private Label Lable_ifPaid_AddMO;

    @FXML
    private void M_Txfiled_SpSerialN_AddMO(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        if (Txfiled_SpSerialN_AddMO.getText().isEmpty()) {

            if (count_Language == 0) {
                alert.setContentText("Please enter SP Serial number");
            } else {
                alert.setContentText("الرجاء ادخال الرقم التسلسلي");

            }
            alert.showAndWait();
            return;
        }
        if (!Mumbervalid(Txfiled_SpPrice_AddMO.getText())) {

            if (count_Language == 0) {
                alert.setContentText("Please enter SP cost");
            } else {
                alert.setContentText("الرجاء ادخال تكلفة قطعة الغيار");

            }
            alert.showAndWait();
            return;
        }
        alert2.setTitle(null);
        alert2.setHeaderText(null);
        
        AllSP2 = Table_SelectedSP_AddMO.getItems();
        SPSelected2 = Table_SelectedSP_AddMO.getSelectionModel().getSelectedItems();

        double perSp_price = Double.parseDouble(Txfiled_SpPrice_AddMO.getText());

        for (int i = 0; i < SPSelected2.size(); i++) {
            try {
                loadlist.add(new Controller_AddMO.SelectedSP(SPSelected2.get(i).getSP2_Number(), SPSelected2.get(i).getSP2_Name(),
                        SPSelected2.get(i).getSP2_Description(), perSp_price, Txfiled_SpSerialN_AddMO.getText(), SPSelected2.get(i).getSP_Seq_Nber()));

                String sqlupdateAddSP = "UPDATE `require` SET `SERIAL_NUMBER` = '" + Txfiled_SpSerialN_AddMO.getText() + "', `Effective_Price` ='" + Txfiled_SpPrice_AddMO.getText() + "' WHERE MO_NBER= " + Txfiled_MOnum_AddMO.getText()
                        + " AND SP_NBER=" + SPSelected2.get(i).getSP2_Number() + " AND Seq_Nber=" + SPSelected2.get(i).getSP_Seq_Nber();
                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sqlupdateAddSP);

                SPSelected2.forEach(loadlist::remove);
                Table_SelectedSP_AddMO.getItems().setAll(loadlist);
                calculate();

                String sqlupdatePrice = "UPDATE `maintenance_operation` SET `MO_COST` = " + Txfiled_MOCost_AddMO.getText() + ", `SP_COST` = " + Txfiled_SPCost_AddMO.getText() + " WHERE `MO_NBER` = " + Txfiled_MOnum_AddMO.getText() + ";";

                statement1.executeUpdate(sqlupdatePrice);

                if (count_Language == 0) {
                    Txfiled_SpSerialN_AddMO.setText("Serial number");
                    Txfiled_SpPrice_AddMO.setText("Cost");

                    alert2.setContentText("Changes saved successfully");
                } else {
                    Txfiled_SpSerialN_AddMO.setText("الرقم التسلسلي");
                    Txfiled_SpPrice_AddMO.setText("السعر");
                    alert2.setContentText("تم حفظ التعديلات بنجاح");

                }

                alert2.showAndWait();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    public void SetMoStatus_language(int c) {

        count_Language = c;

        if (c == 0) {

            Selct_MoStatus_AddMO.setItems(ListOfStatus_EN);

        } else if (c == 1) {

            Selct_MoStatus_AddMO.setItems(ListOfStatus_AR);

        }

    }

    @FXML
    private void M_MousClicked_TabelSelecSP_AddMO(MouseEvent event) {

        SPSelected2 = Table_SelectedSP_AddMO.getSelectionModel().getSelectedItems();

        Txfiled_SpSerialN_AddMO.setText(SPSelected2.get(0).getSP_SN());
        Txfiled_SpPrice_AddMO.setText(String.valueOf(SPSelected2.get(0).getSP_Price2()));

    }

    @FXML
    private void M_Btn_ReomveSP_AddMo(ActionEvent event) {
        try {
            alert2.setTitle(null);
            alert2.setHeaderText(null);

            AllSP2 = Table_SelectedSP_AddMO.getItems();
            SPSelected2 = Table_SelectedSP_AddMO.getSelectionModel().getSelectedItems();

            Txfiled_SpSerialN_AddMO.setText(SPSelected2.get(0).getSP_SN());
            for (int i = 0; i < SPSelected2.size(); i++) {
                try {
                    String sqlDeletSP = "DELETE FROM `require` " + " WHERE MO_NBER= " + Txfiled_MOnum_AddMO.getText() + " AND SP_NBER=" + SPSelected2.get(i).getSP2_Number()
                            + " AND Seq_Nber=" + SPSelected2.get(i).getSP_Seq_Nber();
                    String sqlupdateSP = "UPDATE `spare_parts` SET `SP_Quantity` = SP_Quantity+1 WHERE `spare_parts`.`SP_NBER` =" + SPSelected2.get(i).getSP2_Number();

                    java.sql.Statement statement1 = connection.createStatement();
                    statement1.executeUpdate(sqlDeletSP);
                    statement1.executeUpdate(sqlupdateSP);

                    if (count_Language == 0) {
                        alert2.setContentText("The " + SPSelected2.get(0).getSP2_Name() + " has been removed from the MO");
                    } else {
                        alert2.setContentText("تم ازالة  " + SPSelected2.get(0).getSP2_Name() + "من عملية الصيانة");

                    }

                    alert2.showAndWait();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

            SPSelected2.forEach(loadlist::remove);
            Table_SelectedSP_AddMO.getItems().setAll(loadlist);
            loadData();
            calculate();
            String sqlupdatePrice = "UPDATE `maintenance_operation` SET `MO_COST` = " + Txfiled_MOCost_AddMO.getText() + ", `SP_COST` = " + Txfiled_SPCost_AddMO.getText() + " WHERE `MO_NBER` = " + Txfiled_MOnum_AddMO.getText() + ";";
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sqlupdatePrice);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    String INVOICE_NBER = "DD";
    String MO_State = "";

    public void Search_MO(int MO) {
        alert2.setTitle(null);
        alert2.setHeaderText(null);

        Btn_Search_AddMo.setDisable(true);
        Txfiled_ProplemDisc_AddMO.setDisable(false);
        Txfiled_CusMnum_AddMO.setDisable(false);
        Txfiled_SPCost_AddMO.setDisable(true);
        Txfiled_MOCost_AddMO.setDisable(false);
        Txfiled_DevSerialN_AddMO.setDisable(false);
        Txfiled_DevDiscription_AddMO.setDisable(false);
        Txfiled_SpSerialN_AddMO.setDisable(false);
        Txfiled_TotalCost_AddMO.setDisable(true);
        Txfiled_VAT_AddMO.setDisable(true);
        Txfiled_MOnum_AddMO.setDisable(false);
        Txfiled_SearchSP_AddMO.setDisable(false);

        Txfiled_SpPrice_AddMO.setDisable(false);

        Selct_Techichan_AddMO.setDisable(false);
        Selct_MoStatus_AddMO.setDisable(false);
        Date_Warranty_AddMO.setDisable(false);
        Date_StartMo_AddMO.setDisable(false);
        Date_EndMO_AddMO.setDisable(false);

        try {
            Connection connection = connectionClass.getConnection();
            Statement st = connection.createStatement();
            st.executeQuery("SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE MO_NBER = " + MO);

            ResultSet rs = st.getResultSet();

            if (rs.first()) {

                INVOICE_NBER = rs.getString("INVOICE_NBER");

                count = 2;
                Txfiled_MOnum_AddMO.setText(String.valueOf(MO));
                Txfiled_MOnum_AddMO.setDisable(true);
                Txfiled_CusName_AddMO.setText(rs.getString("CUS_NAME"));
                Txfiled_ProplemDisc_AddMO.setText(rs.getString("PROBLEM_DESC"));
                Txfiled_CusMnum_AddMO.setText(rs.getString("CUS_MOBILE_NBER"));
                Txfiled_SPCost_AddMO.setText(rs.getString("SP_COST"));
                Txfiled_MOCost_AddMO.setText(rs.getString("MO_COST"));
                Txfiled_DevSerialN_AddMO.setText(rs.getString("DEVICE_SN"));
                Txfiled_DevDiscription_AddMO.setText(rs.getString("DEVICE_DESC"));

                LocalDate WARRANTYDate = LocalDate.parse(rs.getString("WARRANTY"));
                LocalDate STARTINGDate = LocalDate.parse(rs.getString("STARTING_DATE"));
                LocalDate ENDINGDate = LocalDate.parse(rs.getString("ENDING_DATE"));

                Date_Warranty_AddMO.setValue(WARRANTYDate);
                Date_StartMo_AddMO.setValue(STARTINGDate);
                Date_EndMO_AddMO.setValue(ENDINGDate);

                Selct_MoStatus_AddMO.getSelectionModel().select(rs.getString("STATE"));
                MO_State = rs.getString("STATE");

                Selct_Techichan_AddMO.getSelectionModel().select(rs.getString("EMP_NAME"));

                if (rs.getString("STATE").equals("created") || rs.getString("STATE").equals("تم الإنشاء")) {
                    if (count_Language == 0) {
                        Selct_MoStatus_AddMO.getSelectionModel().select("created");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("تم الإنشاء");

                    }
                } else if (rs.getString("STATE").equals("approved") || rs.getString("STATE").equals("تم الموافقة")) {
                    if (count_Language == 0) {
                        Selct_MoStatus_AddMO.getSelectionModel().select("approved");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("تم الموافقة");

                    }

                } else if (rs.getString("STATE").equals("مرفوضة") || rs.getString("STATE").equals("disapproved")) {
                    if (count_Language == 0) {
                        Selct_MoStatus_AddMO.getSelectionModel().select("disapproved");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("مرفوضة");

                    }
                } else if (rs.getString("STATE").equals("لا يمكن القيام بعملية الصيانة") || rs.getString("STATE").equals("cannot be done")) {
                    if (count_Language == 0) {
                        Selct_MoStatus_AddMO.getSelectionModel().select("cannot be done");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("لا يمكن القيام بعملية الصيانة");

                    }
                } else if (rs.getString("STATE").equals("تم الكشف عن عيوب أخرى") || rs.getString("STATE").equals("other defects has been detected")) {
                    if (count_Language == 0) {
                        Selct_MoStatus_AddMO.getSelectionModel().select("other defects has been detected");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("تم الكشف عن عيوب أخرى");

                    }
                } else if (rs.getString("STATE").equals("تم الاصلاح") || rs.getString("STATE").equals("repaired")) {
                    if (count_Language == 0) {
                        Selct_MoStatus_AddMO.getSelectionModel().select("repaired");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("تم الاصلاح");

                    }
                } else if (rs.getString("STATE").equals("تحت الصيانة") || rs.getString("STATE").equals("under maintenance")) {
                    if (count_Language == 0) {
                        Selct_MoStatus_AddMO.getSelectionModel().select("under maintenance");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("تحت الصيانة");

                    }
                }
                Btn_Delete_AddMo.setDisable(false);
                Btn_Save_AddMo.setDisable(false);
                Btn_Print_AddMo.setDisable(false);
                Txfiled_CusName_AddMO.setDisable(true);
                Btn_Cancel_AddMo.setDisable(false);
                Btn_AddSP_AddMo.setDisable(false);
                Btn_ReomveSP_AddMo.setDisable(false);

                if (rs.getString("STATE").equals("دفعت") || rs.getString("STATE").equals("paid")) {
                    Btn_Delete_AddMo.setDisable(true);
                    Btn_Save_AddMo.setDisable(true);
                    Btn_Print_AddMo.setDisable(false);
                    Btn_Save_AddMo.setDisable(true);
                    Btn_Search_AddMo.setDisable(true);
                    Btn_AddSP_AddMo.setDisable(true);
                    Btn_ReomveSP_AddMo.setDisable(true);
                    Txfiled_SpSerialN_AddMO.setDisable(true);
                    Txfiled_SpPrice_AddMO.setDisable(true);
                    Txfiled_MOCost_AddMO.setDisable(true);
                    if (count_Language == 0) {

                        Lable_ifPaid_AddMO.setText("The MO is paid, can not be modified");
                        Selct_MoStatus_AddMO.getSelectionModel().select("paid");
                    } else {
                        Selct_MoStatus_AddMO.getSelectionModel().select("دفعت");
                        Lable_ifPaid_AddMO.setText("عملية الصيانة مدفوعة، لا يمكن التعديل عليها");

                    }
                }

                loadSpSelected(MO);

                calculate();

            } else {

                Statement st2 = connection.createStatement();
                Statement st3 = connection.createStatement();

                st2.executeQuery("SELECT * FROM `maintenance_operation` ORDER BY `MO_NBER` DESC LIMIT 1");
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {
                    st3.executeQuery("SELECT * FROM `maintenance_operation_backup` ORDER BY `MO_NBER` DESC LIMIT 1");
                    ResultSet rs3 = st3.getResultSet();

                    int MO_NBER = Integer.parseInt(rs2.getString("MO_NBER"));

                    if (rs3.first()) {
                        int MO_NBER_backup = Integer.parseInt(rs3.getString("MO_NBER"));

                        if (MO_NBER > MO_NBER_backup) {
                            monumber = MO_NBER;
                        } else {
                            monumber = MO_NBER_backup;

                        }
                    } else {
                        monumber = MO_NBER;
                    }
                    if (count_Language == 0) {
                        alert2.setContentText("New MO will be created");
                    } else {
                        alert2.setContentText(" سوف يتم انشاء عملية صيانة جديدة");

                    }
                    alert2.showAndWait();
                    count = 1;
                    monumber++;
                    Txfiled_MOnum_AddMO.setText(String.valueOf(monumber));
                    Txfiled_MOnum_AddMO.setDisable(true);
                    Btn_Delete_AddMo.setDisable(true);
                    Btn_Cancel_AddMo.setDisable(false);
                    Btn_Save_AddMo.setDisable(false);
                    Btn_Print_AddMo.setDisable(false);
                    Txfiled_CusName_AddMO.setDisable(true);
                }

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }



    public void Search(String Search, int Choose) {
        if (Choose == 2) {
            ResultSet rs = connectionClass.execQuery(Search);
            try {
                while (rs.next()) {
                    String mname = rs.getString("SP_NBER");
                    String mid = rs.getString("SP_NAME");
                    String mobile = rs.getString("DESCRIPTION");
                    String price = rs.getString("PRICE");

                    int SP_num = Integer.parseInt(mname);
                    int SP_quan = Integer.parseInt(rs.getString("SP_QUANTITY"));

                    double SP_Pri = Double.parseDouble(price);

                    list.add(new Controller_AddMO.AddSP(SP_num, mid, mobile, SP_Pri, SP_quan));

                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);

            }
            Table_AddSP_AddMO.getItems().setAll(list);

        } else if (Choose == 1) {

            ResultSet rs = connectionClass.execQuery(Search);

            try {
                if (rs.first()) {

                    Txfiled_CusName_AddMO.setText(rs.getString("CUS_NAME"));

                } else {
                    Txfiled_CusName_AddMO.clear();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }

    }

    @FXML
    private void M_Btn_AddSP_AddMo(ActionEvent event) {
        try {
            alert2.setTitle(null);
            alert2.setHeaderText(null);
            ObservableList<Controller_AddMO.AddSP> SPSelected, AllSP;
            AllSP = Table_AddSP_AddMO.getItems();
            SPSelected = Table_AddSP_AddMO.getSelectionModel().getSelectedItems();
            for (int i = 0; i < SPSelected.size(); i++) {

                int a = 0;
                int seqNumber = 1;
                for (SelectedSP loadlist1 : loadlist) {

                    if (loadlist.get(a).getSP2_Name().equals(SPSelected.get(0).getSP_Name())) {
                        loadlist.get(a).getSP_Seq_Nber();
                        seqNumber = loadlist.get(a).getSP_Seq_Nber() + 1;
                    }
                    a++;

                }
                if (SPSelected.get(0).getSP_Quantity() > 0) {

                    try {
                        loadlist.add(new Controller_AddMO.SelectedSP(SPSelected.get(0).getSP_Number(), SPSelected.get(0).getSP_Name(),
                                SPSelected.get(0).getSP_Description(), SPSelected.get(0).getSP_Price(), "null", seqNumber));

                        String sql1 = "INSERT INTO `require` VALUES(" + Txfiled_MOnum_AddMO.getText() + ",'" + SPSelected.get(0).getSP_Number() + "','"
                                + seqNumber + "','" + "Null'" + ",'" + SPSelected.get(0).getSP_Price() + "')";

                        String sqlupdateSP = "UPDATE `spare_parts` SET `SP_QUANTITY` = SP_Quantity-1 WHERE `spare_parts`.`SP_NBER` =" + SPSelected.get(0).getSP_Number();

                        java.sql.Statement statement1 = connection.createStatement();
                        statement1.executeUpdate(sql1);
                        statement1.executeUpdate(sqlupdateSP);

                        if (count_Language == 0) {
                            alert2.setContentText("A " + SPSelected.get(0).getSP_Name() + " has been added to the MO");
                        } else {
                            alert2.setContentText("تم اضافة  " + SPSelected.get(0).getSP_Name() + "لعملية الصيانة");

                        }
                        alert2.showAndWait();

                        loadData();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                } else {
                    Alert alert3 = new Alert(Alert.AlertType.WARNING);
                    alert3.setTitle(null);
                    alert3.setHeaderText(null);
                    if (count_Language == 0) {
                        alert3.setContentText("The spare part can not be added because it is out of stock");
                    } else {
                        alert3.setContentText("لا يمكنك اضافة قطعة الغيار , لقد نفذت كميتها");

                    }

                    alert3.showAndWait();
                }

            }
            Table_SelectedSP_AddMO.getItems().setAll(loadlist);
            calculate();
            String sqlupdatePrice = "UPDATE `maintenance_operation` SET `MO_COST` = " + Txfiled_MOCost_AddMO.getText() + ", `SP_COST` = " + Txfiled_SPCost_AddMO.getText() + " WHERE `MO_NBER` = " + Txfiled_MOnum_AddMO.getText() + ";";
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sqlupdatePrice);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    void loadWindow(String loc, String title) {
        try {

            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle(title);
            stage.show();
        } catch (IOException s) {
            JOptionPane.showMessageDialog(null, s);
        }
    }

    @FXML
    private void M_Btn_Print_AddMo(ActionEvent event) {
        if (count_Language == 0) {
            if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("paid")) {
                try {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('paid','دفعت') AND m.MO_NBER ='" + Txfiled_MOnum_AddMO.getText() + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();
                    if (rs2.first()) {

                        printreport print = new printreport();
                        String MOnumber = Txfiled_MOnum_AddMO.getText();

                        print.InvoiceEN(MOnumber);
                    } else {
                        printreport print = new printreport();
                        String MOnumber = Txfiled_MOnum_AddMO.getText();

                        print.InvoiceENGWSP(MOnumber);

                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                } catch (JRException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            } else if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("created") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("approved") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("under maintenance") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("other defects has been detected") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("disapproved") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("cannot be done") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("repaired")) {
                try {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected','disapproved','cannot be done','repaired','تم الإنشاء', 'تم الموافقة', 'تحت الصيانة', 'تم الكشف عن عيوب أخرى','مرفوضة','لا يمكن القيام بعملية الصيانة','تم الاصلاح') AND m.MO_NBER = '" + Txfiled_MOnum_AddMO.getText() + "'";

                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();
                    if (rs2.first()) {

                        printreport print = new printreport();
                        String MOnumber = Txfiled_MOnum_AddMO.getText();
                        print.financialassessmentEN(MOnumber);
                    } else {
                        printreport print = new printreport();
                        String MOnumber = Txfiled_MOnum_AddMO.getText();
                        print.financialassessmentENWSP(MOnumber);

                    }

                } catch (JRException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Can not print this MO");
                alert.showAndWait();
                return;

            }
        } else if (count_Language == 1) {
            if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("دفعت")) {
                try {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('paid','دفعت') AND m.MO_NBER ='" + Txfiled_MOnum_AddMO.getText() + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();
                    if (rs2.first()) {

                        printreport print = new printreport();
                        String MOnumber = Txfiled_MOnum_AddMO.getText();

                        print.InvoiceAR(MOnumber);
                    } else {
                        printreport print = new printreport();
                        String MOnumber = Txfiled_MOnum_AddMO.getText();

                        print.InvoiceARWSP(MOnumber);

                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                } catch (JRException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            } else if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("تم الإنشاء") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("تم الموافقة") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("تحت الصيانة") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("تم الكشف عن عيوب أخرى") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("مرفوضة") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("لا يمكن القيام بعملية الصيانة") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("تم الاصلاح")) {
                try {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected','disapproved','cannot be done','repaired','تم الإنشاء', 'تم الموافقة', 'تحت الصيانة', 'تم الكشف عن عيوب أخرى','مرفوضة','لا يمكن القيام بعملية الصيانة','تم الاصلاح') AND m.MO_NBER = '" + Txfiled_MOnum_AddMO.getText() + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();
                    if (rs2.first()) {

                        printreport print = new printreport();
                        String MOnumber = Txfiled_MOnum_AddMO.getText();
                        print.financialassessmentAR(MOnumber);
                    } else {
                        printreport print = new printreport();
                        String MOnumber = Txfiled_MOnum_AddMO.getText();
                        print.financialassessmentARWSP(MOnumber);

                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                } catch (JRException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("لايمكن الطباعة");
                alert.showAndWait();
                return;

            }

        }
    }

    @FXML
    private void M_Btn_Cancel_AddMo(ActionEvent event) {

        clear();

    }

    @FXML
    private void M_Btn_Delete_AddMo(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        if (count_Language == 0) {
            alert.setContentText("This MO will be deleted ");
        } else {
            alert.setContentText("  سوف يتم حذف عملية الصيانة ");

        }

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                String deletSP = "DELETE FROM  `require` " + " WHERE MO_NBER= " + Txfiled_MOnum_AddMO.getText();
                String sql1 = "DELETE FROM  `maintenance_operation` " + " WHERE MO_NBER= " + Txfiled_MOnum_AddMO.getText();
                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(deletSP);
                statement1.executeUpdate(sql1);
                clear();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

    }

    public void clear() {

        Btn_Search_AddMo.setDisable(false);

        Btn_Delete_AddMo.setDisable(true);
        Btn_Save_AddMo.setDisable(true);
        Btn_Print_AddMo.setDisable(true);
        Btn_Save_AddMo.setDisable(true);
        Btn_AddSP_AddMo.setDisable(true);
        Btn_ReomveSP_AddMo.setDisable(true);
        Btn_Cancel_AddMo.setDisable(true);

        Txfiled_MOnum_AddMO.setDisable(false);
        Txfiled_SpPrice_AddMO.clear();
        Txfiled_ProplemDisc_AddMO.clear();
        Txfiled_CusMnum_AddMO.clear();
        Txfiled_SPCost_AddMO.setText("0.00");
        Txfiled_MOCost_AddMO.setText("0.00");
        Txfiled_DevSerialN_AddMO.clear();
        Txfiled_DevDiscription_AddMO.clear();
        Txfiled_SpSerialN_AddMO.clear();
        Txfiled_TotalCost_AddMO.setText("0.00");
        Txfiled_VAT_AddMO.setText("0.00");
        Txfiled_MOnum_AddMO.clear();
        Txfiled_SearchSP_AddMO.clear();
        Txfiled_CusName_AddMO.clear();

        Txfiled_ProplemDisc_AddMO.setDisable(true);
        Txfiled_CusMnum_AddMO.setDisable(true);
        Txfiled_SPCost_AddMO.setDisable(true);
        Txfiled_MOCost_AddMO.setDisable(true);
        Txfiled_DevSerialN_AddMO.setDisable(true);
        Txfiled_DevDiscription_AddMO.setDisable(true);
        Txfiled_SpSerialN_AddMO.setDisable(true);
        Txfiled_TotalCost_AddMO.setDisable(true);
        Txfiled_VAT_AddMO.setDisable(true);

        Txfiled_SearchSP_AddMO.setDisable(true);

        Txfiled_SpPrice_AddMO.setDisable(true);

        Selct_Techichan_AddMO.setDisable(true);
        Selct_MoStatus_AddMO.setDisable(true);
        Date_Warranty_AddMO.setDisable(true);
        Date_StartMo_AddMO.setDisable(true);
        Date_EndMO_AddMO.setDisable(true);

        Selct_Techichan_AddMO.getSelectionModel().clearSelection();
        Selct_MoStatus_AddMO.getSelectionModel().clearSelection();
        Date_Warranty_AddMO.setValue(null);
        Date_StartMo_AddMO.setValue(null);
        Date_EndMO_AddMO.setValue(null);
        Table_SelectedSP_AddMO.getItems().clear();
        Lable_ifPaid_AddMO.setText("");

    }

    public static boolean Mumbervalid(String s) {
        if (s.isEmpty()) {
            return false;
        } else if (s.matches("[0-9]*")) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void M_Btn_Save_AddMo(ActionEvent event) {
        String subject = "";
        String messageText = "";
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        if (Txfiled_ProplemDisc_AddMO.getText().isEmpty()) {
            if (count_Language == 0) {
                alert.setContentText("Please enter the problem description ");
            } else {
                alert.setContentText("الرجاء إدخال وصف المشكلة ");

            }
            alert.showAndWait();
            return;

        }
        if (Txfiled_DevSerialN_AddMO.getText().isEmpty()) {
            if (count_Language == 0) {
                alert.setContentText("Please enter the device serial number ");
            } else {
                alert.setContentText("الرجاء إدخال الرقم التسلسلي للجهاز ");

            }
            alert.showAndWait();
            return;

        }
        if (Txfiled_DevDiscription_AddMO.getText().isEmpty()) {
            if (count_Language == 0) {
                alert.setContentText("Please enter the device description ");
            } else {
                alert.setContentText("الرجاء إدخال وصف الجهاز ");

            }
            alert.showAndWait();
            return;

        }
        if (Date_EndMO_AddMO.getValue() == null) {
            if (count_Language == 0) {
                alert.setContentText("Please enter  expected ending date of MO ");
            } else {
                alert.setContentText("الرجاء إدخال تاريخ الانتهاء المتوقع لعملية الصيانة ");

            }
            alert.showAndWait();
            return;

        }
        if (Date_StartMo_AddMO.getValue() == null) {
            if (count_Language == 0) {
                alert.setContentText("Please enter starting date of MO ");
            } else {
                alert.setContentText("الرجاء إدخال تاريخ بدء عملية الصيانة ");

            }
            alert.showAndWait();
            return;

        }
        if (Date_Warranty_AddMO.getValue() == null) {
            if (count_Language == 0) {
                alert.setContentText("Please enter ending warranty date ");
            } else {
                alert.setContentText("الرجاء إدخال تاريخ انتهاء الضمان ");

            }
            alert.showAndWait();
            return;

        }
        if (Selct_MoStatus_AddMO.getSelectionModel().isEmpty()) {
            if (count_Language == 0) {
                alert.setContentText("Please select the state of MO ");
            } else {
                alert.setContentText("الرجاء اختيار حالة عملية الصيانة ");

            }
            alert.showAndWait();
            return;

        }
        if (Selct_Techichan_AddMO.getSelectionModel().isEmpty()) {
            if (count_Language == 0) {
                alert.setContentText("Please select the technician of MO ");
            } else {
                alert.setContentText("الرجاء اختيار فني عملية الصيانة ");

            }
            alert.showAndWait();
            return;

        }
        if (Txfiled_MOCost_AddMO.getText().isEmpty()) {
            if (count_Language == 0) {
                alert.setContentText("Please enter the MO cost ");
            } else {
                alert.setContentText("الرجاء إدخال تكلفة عملية الصيانة ");

            }
            alert.showAndWait();
            return;

        }
        try {
            Statement st2 = connection.createStatement();
            st2.executeQuery("SELECT * FROM `employee`");
            ResultSet rs2 = st2.getResultSet();
            int IndexOFTech = 0;
            for (int i = 0; i < ListOfTechichan.size(); i++) {

                while (rs2.next()) {

                    if (Selct_Techichan_AddMO.getValue().equals(rs2.getString("EMP_NAME"))) {

                        IndexOFTech = Integer.parseInt(rs2.getString("EMPLOYEE_ID"));

                    }
                }
            }
            if (Txfiled_CusName_AddMO.getText().isEmpty()) {

                alert.setHeaderText(null);

                if (count_Language == 0) {
                    alert.setContentText("Please enter valid customer");
                } else {
                    alert.setContentText("الرجاء التاكد من اضافة عميل");

                }
                alert.showAndWait();

            } else {

                String AlertMessageAR = "";
                String AlertMessageEN = "";

                if (count == 1) {

                    String sql1 = "INSERT INTO `maintenance_operation` VALUES(" + monumber + "," + "'" + Selct_MoStatus_AddMO.getValue() + "'" + "," + "'" + Txfiled_MOCost_AddMO.getText()
                            + "'" + "," + "'" + Txfiled_SPCost_AddMO.getText() + "'" + "," + "'" + Date_StartMo_AddMO.getValue() + "'" + "," + "'" + Date_EndMO_AddMO.getValue() + "'" + "," + "'"
                            + Date_Warranty_AddMO.getValue() + "'" + "," + "'" + Txfiled_ProplemDisc_AddMO.getText() + "'" + "," + "'" + Txfiled_DevSerialN_AddMO.getText() + "'" + "," + "'" + Txfiled_DevDiscription_AddMO.getText()
                            + "'" + "," + "'" + IndexOFTech + "'" + "," + "'" + Txfiled_CusMnum_AddMO.getText() + "', NULL ,NULL" + ")";
                    java.sql.Statement statement1 = connection.createStatement();
                    statement1.executeUpdate(sql1);
                    if (count_Language == 0) {

                        alert2.setContentText(" A new MO has been created");
                    } else {
                        alert2.setContentText("تم انشاء عملية صيانة جديدة");

                    }

                    alert2.showAndWait();
                    clear();

                } else if (count == 2) {

                    if (MO_State.equalsIgnoreCase("paid") || MO_State.equalsIgnoreCase("دفعت")) {

                    } else {

                        String sql1 = "UPDATE  `maintenance_operation` SET STATE='" + Selct_MoStatus_AddMO.getValue() + "',MO_COST='" + Txfiled_MOCost_AddMO.getText() + "',SP_COST='" + Txfiled_SPCost_AddMO.getText()
                                + "',STARTING_DATE='" + Date_StartMo_AddMO.getValue() + "',ENDING_DATE='" + Date_EndMO_AddMO.getValue() + "',WARRANTY='" + Date_Warranty_AddMO.getValue() + "',PROBLEM_DESC='" + Txfiled_ProplemDisc_AddMO.getText()
                                + "',DEVICE_SN='" + Txfiled_DevSerialN_AddMO.getText() + "',DEVICE_DESC='" + Txfiled_DevDiscription_AddMO.getText() + "',EMPLOYEE_ID='" + IndexOFTech + "',CUS_MOBILE_NBER='" + Txfiled_CusMnum_AddMO.getText()
                                + "' WHERE MO_NBER= '" + Txfiled_MOnum_AddMO.getText() + "'";
                        java.sql.Statement statement1 = connection.createStatement();
                        statement1.executeUpdate(sql1);
                        AlertMessageAR += "تم حفظ التعديلات بنجاح";
                        AlertMessageEN += "Changes saved successfully";
                        if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("paid") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("دفعت")) {
                            if (INVOICE_NBER != null) {

                            } else {

                                Statement st3 = connection.createStatement();
                                st3.executeQuery("SELECT INVOICE_NBER FROM `maintenance_operation` ORDER BY `INVOICE_NBER` DESC LIMIT 1");
                                ResultSet rs3 = st3.getResultSet();
                                rs3.next();
                                INVOICE_NBER = rs3.getString("INVOICE_NBER");

                                if (INVOICE_NBER != null) {
                                    int INVOICE_NBER_ORg = Integer.parseInt(rs3.getString("INVOICE_NBER"));
                                    Statement st4 = connection.createStatement();
                                    st4.executeQuery("SELECT INVOICE_NBER FROM `maintenance_operation_backup` ORDER BY `INVOICE_NBER` DESC LIMIT 1");
                                    ResultSet rs5 = st4.getResultSet();
                                    rs5.next();

                                    INVOICE_NBER = rs5.getString("INVOICE_NBER");
                                    if (INVOICE_NBER != null) {
                                        int INVOICE_NBER_backup = Integer.parseInt(rs5.getString("INVOICE_NBER"));

                                        if (INVOICE_NBER_ORg > INVOICE_NBER_backup) {
                                            monumber = INVOICE_NBER_ORg;
                                        } else {
                                            monumber = INVOICE_NBER_backup;

                                        }

                                    } else {

                                        monumber = Integer.parseInt(rs3.getString("INVOICE_NBER"));
                                        monumber++;
                                    }
                                } else {
                                    st3.executeQuery("SELECT INVOICE_NBER FROM `maintenance_operation_backup` ORDER BY `INVOICE_NBER` DESC LIMIT 1");
                                    ResultSet rs4 = st3.getResultSet();
                                    rs4.next();
                                    INVOICE_NBER = rs4.getString("INVOICE_NBER");

                                    if (INVOICE_NBER != null) {

                                        monumber = Integer.parseInt(rs4.getString("INVOICE_NBER"));
                                        monumber++;
                                    } else {
                                        monumber = 1;
                                    }

                                }

                                String inv_num_date = "UPDATE  `maintenance_operation` SET INVOICE_DATE='" + LocalDate.now() + "',INVOICE_NBER='" + monumber + "' WHERE MO_NBER= '" + Txfiled_MOnum_AddMO.getText() + "'";
                                java.sql.Statement statement2 = connection.createStatement();
                                statement2.executeUpdate(inv_num_date);

                            }
                        }

                        if (count_Language == 0) {

                            alert2.setContentText(AlertMessageEN);
                        } else {
                            alert2.setContentText(AlertMessageAR);
                        }
                        alert2.showAndWait();

                        if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("approved") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("تم الموافقة")) {
                            if (count_Language == 0) {

                                alert2.setContentText("An alert is sent to the customer, which may take some time");
                            } else {
                                alert2.setContentText("يتم ارسال تنبية للعميل ,  قد يستغرق ذلك بعض الوقت");
                            }
                            alert2.showAndWait();

                            Statement st = connection.createStatement();
                            String CusEmail_query = "SELECT CUS_EMAIL FROM `customer` Where CUS_MOBILE_NBER =" + Txfiled_CusMnum_AddMO.getText();
                            st.executeQuery(CusEmail_query);
                            ResultSet rs = st.getResultSet();
                            String to = "";
                            if (rs.first()) {

                                to = rs.getString("CUS_EMAIL");
                            } else {
                            }

                            String host = "smtp.gmail.com";
                            String user = "abdualziz.alhazmi1997@gmail.com";
                            String pass = "Azoz789!@#";

                            String from = "abdualziz.alhazmi1997@gmail.com";

                            if (count_Language == 0) {

                                 subject = "approved ";
                                 messageText = "dear customer\n" +
"We would like to inform you that your approval has been received and the maintenance will be started as soon as possible";
                            } else {
                                 subject = "تم الموافقة";
                                 messageText = "عميلنا العزيز\n" +
"نود اعلامكم بانه تم استلام الموافقة منكم،  وسيتم البدء في عملية الصيانة في اقرب وقت  ";
                            }
                            boolean sessionDebug = false;

                            Properties props = System.getProperties();

                            props.put("mail.smtp.starttls.enable", "true");
                            props.put("mail.smtp.host", host);
                            props.put("mail.smtp.port", "587");
                            props.put("mail.smtp.auth", "true");
                            props.put("mail.smtp.starttls.required", "true");

                            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                            Session mailSession = Session.getDefaultInstance(props, null);
                            mailSession.setDebug(sessionDebug);
                            Message msg = new MimeMessage(mailSession);
                            msg.setFrom(new InternetAddress(from));
                            InternetAddress[] address = {new InternetAddress(to)};
                            msg.setRecipients(Message.RecipientType.TO, address);
                            msg.setSubject(subject);
                            msg.setSentDate(new Date());
                            msg.setText(messageText);
                            Transport transport = mailSession.getTransport("smtp");
                            transport.connect(host, user, pass);
                            transport.sendMessage(msg, msg.getAllRecipients());
                            transport.close();

                        } else if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("repaired") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("تم الاصلاح")) {
                            if (count_Language == 0) {

                                alert2.setContentText("An alert is sent to the customer, which may take some time");
                            } else {
                                alert2.setContentText("يتم ارسال تنبية للعميل ,  قد يستغرق ذلك بعض الوقت");
                            }
                            alert2.showAndWait();

                            Statement st = connection.createStatement();
                            String CusEmail_query = "SELECT CUS_EMAIL FROM `customer` Where CUS_MOBILE_NBER =" + Txfiled_CusMnum_AddMO.getText();
                            st.executeQuery(CusEmail_query);
                            ResultSet rs = st.getResultSet();
                            String to = "";
                            if (rs.first()) {

                                to = rs.getString("CUS_EMAIL");
                            } else {
                            }

                            String host = "smtp.gmail.com";
                            String user = "abdualziz.alhazmi1997@gmail.com";
                            String pass = "Azoz789!@#";

                            String from = "abdualziz.alhazmi1997@gmail.com";
                        if (count_Language == 0) {

                                 subject = "repaired";
                                 messageText = "dear customer\n" +
"We would like to inform you that the maintenance has been completed";
                            } else {
                                 subject = "تم الإصلاح ";
                                 messageText = "عميلنا العزيز\n" +
"نود اعلامكم بانه تم الإنتهاء من عملية الصيانة";
                            }

                            boolean sessionDebug = false;

                            Properties props = System.getProperties();

                            props.put("mail.smtp.starttls.enable", "true");
                            props.put("mail.smtp.host", host);
                            props.put("mail.smtp.port", "587");
                            props.put("mail.smtp.auth", "true");
                            props.put("mail.smtp.starttls.required", "true");

                            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                            Session mailSession = Session.getDefaultInstance(props, null);
                            mailSession.setDebug(sessionDebug);
                            Message msg = new MimeMessage(mailSession);
                            msg.setFrom(new InternetAddress(from));
                            InternetAddress[] address = {new InternetAddress(to)};
                            msg.setRecipients(Message.RecipientType.TO, address);
                            msg.setSubject(subject);
                            msg.setSentDate(new Date());
                            msg.setText(messageText);
                            Transport transport = mailSession.getTransport("smtp");
                            transport.connect(host, user, pass);
                            transport.sendMessage(msg, msg.getAllRecipients());
                            transport.close();

                        } else if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("other defects has been detected") || Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("تم الكشف عن عيوب أخرى")) {
                            if (count_Language == 0) {

                                alert2.setContentText("An alert is sent to the customer, which may take some time");
                            } else {
                                alert2.setContentText("يتم ارسال تنبية للعميل ,  قد يستغرق ذلك بعض الوقت");
                            }
                            alert2.showAndWait();
                            Statement st = connection.createStatement();
                            String CusEmail_query = "SELECT CUS_EMAIL FROM `customer` Where CUS_MOBILE_NBER =" + Txfiled_CusMnum_AddMO.getText();
                            st.executeQuery(CusEmail_query);
                            ResultSet rs = st.getResultSet();
                            String to = "";
                            if (rs.first()) {

                                to = rs.getString("CUS_EMAIL");
                            } else {
                            }

                            String host = "smtp.gmail.com";
                            String user = "abdualziz.alhazmi1997@gmail.com";
                            String pass = "Azoz789!@#";

                            String from = "abdualziz.alhazmi1997@gmail.com";
                                if (count_Language == 0) {

                                 subject = "other defects has been detected";
                                 messageText = "dear customer\n" +
"We would like to inform you that other defects have been detected and we are awaiting your approval for maintenance ";
                            } else {
                                 subject = "تم الكشف عن عيوب أخرى";
                                 messageText = "عميلنا العزيز\n" +
"نود اعلامكم بانه تم الكشف عن عيوب اخرى ، ونحن في انتظار موافقتكم على الصيانة  ";
                            }
                            boolean sessionDebug = false;

                            Properties props = System.getProperties();

                            props.put("mail.smtp.starttls.enable", "true");
                            props.put("mail.smtp.host", host);
                            props.put("mail.smtp.port", "587");
                            props.put("mail.smtp.auth", "true");
                            props.put("mail.smtp.starttls.required", "true");

                            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                            Session mailSession = Session.getDefaultInstance(props, null);
                            mailSession.setDebug(sessionDebug);
                            Message msg = new MimeMessage(mailSession);
                            msg.setFrom(new InternetAddress(from));
                            InternetAddress[] address = {new InternetAddress(to)};
                            msg.setRecipients(Message.RecipientType.TO, address);
                            msg.setSubject(subject);
                            msg.setSentDate(new Date());
                            msg.setText(messageText);
                            Transport transport = mailSession.getTransport("smtp");
                            transport.connect(host, user, pass);
                            transport.sendMessage(msg, msg.getAllRecipients());
                            transport.close();

                        }

                        clear();
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (AddressException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
    public int monumber = 0;

    @FXML
    private void M_Btn_Search_AddMo(ActionEvent event) {

        if (!Mumbervalid(Txfiled_MOnum_AddMO.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);

            if (count_Language == 0) {
                alert.setContentText("Please enter MO number");
            } else {
                alert.setContentText("الرجاء ادخال رقم عملية الصيانة");

            }
            alert.showAndWait();
            return;
        }
        int MO_number = Integer.parseInt(Txfiled_MOnum_AddMO.getText());
        Search_MO(MO_number);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources
    ) {

        connectionClass.connectDB();
        intilCol();
        loadData();
        loadTech();
        Selct_Techichan_AddMO.setItems(ListOfTechichan);

    }

    public void loadTech() {
        String query = "SELECT EMP_NAME FROM employee where JOP_TYPE= 'Technician' or JOP_TYPE='فني' ";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {

                ListOfTechichan.add(rs.getString("EMP_NAME"));

            }
            rs.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }

    }

    private void intilCol() {

        Col_SPnum_AddSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Number"));
        Col_SPname_AddSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Name"));
        Col_SPdisc_AddSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Description"));
        Col_SPprice_AddSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Price"));
        Col_SPQuantity_AddSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Quantity"));

        Col_SPnum_SelectedSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Number2"));
        Col_SPname_SelectedSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Name2"));
        Col_SPdisc_SelectedSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Description2"));
        Col_SPSN_SelectedSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_SN"));
        Col_SPprice_SelectedSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Price2"));
    }

    private void loadData() {
        list.clear();
        Table_AddSP_AddMO.getItems().clear();
        String query = "SELECT * FROM spare_parts";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {

                String mname = rs.getString("SP_NBER");
                String mid = rs.getString("SP_NAME");
                String mobile = rs.getString("DESCRIPTION");
                String price = rs.getString("PRICE");

                int SP_num = Integer.parseInt(mname);
                int SP_quan = Integer.parseInt(rs.getString("SP_QUANTITY"));

                double SP_Pri = Double.parseDouble(price);

                list.add(new Controller_AddMO.AddSP(SP_num, mid, mobile, SP_Pri, SP_quan));

            }
            rs.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        Table_AddSP_AddMO.getItems().setAll(list);


    }

    private void loadSpSelected(int MM) {
        int MO_num = MM;
        loadlist.clear();

        String SQLqq = "SELECT *\n"
                + "FROM   spare_parts s\n"
                + "JOIN   `require` r ON s.SP_NBER = r.SP_NBER WHERE MO_NBER= " + MO_num;
        ResultSet rs = connectionClass.execQuery(SQLqq);

        try {
            double a = 0.00;
            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    int SP_num = Integer.parseInt(rs.getString("SP_NBER"));
                    int SP_Seq = Integer.parseInt(rs.getString("Seq_Nber"));
                    int Mo_n = Integer.parseInt(rs.getString("MO_NBER"));

                    double SP_Pri = Double.parseDouble(rs.getString("PRICE"));

                    double SP_Pri2 = Double.parseDouble(rs.getString("Effective_Price"));

                    if (Mo_n == MO_num) {
                        a += SP_Pri2;

                        Txfiled_SPCost_AddMO.setText(String.valueOf(a));
                        loadlist.add(new Controller_AddMO.SelectedSP(SP_num, rs.getString("SP_NAME"), rs.getString("DESCRIPTION"), SP_Pri2, rs.getString("SERIAL_NUMBER"), SP_Seq));
                    }

                }
                Table_SelectedSP_AddMO.getItems().setAll(loadlist);
            }

            rs.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void calculate() {
        double spcost = 0;

        if (!loadlist.isEmpty()) {

            for (int i = 0; i < loadlist.size(); i++) {
                double[] mypriceArray = new double[loadlist.size()];
                mypriceArray[i] = loadlist.get(i).getSP_Price2();
                spcost += mypriceArray[i];
                Txfiled_SPCost_AddMO.setText(String.valueOf(spcost));
            }
        } else {

            Txfiled_SPCost_AddMO.setText(String.valueOf(0.00));
        }

        double costofmaint = Double.parseDouble(Txfiled_MOCost_AddMO.getText());
        double costofSP = spcost;
        double total = costofmaint + costofSP;
        double vat = total * 0.05;

        String saa = String.format("%.2f", vat);

        double Total = total + vat;
        Txfiled_VAT_AddMO.setText(String.valueOf(saa));
        Txfiled_TotalCost_AddMO.setText(String.valueOf(Total));

    }

    public int Choose = 0;

    @FXML
    private void M_Txfiled_CusMnum_AddMO(KeyEvent event) {
        Choose = 1;

        String sql1 = "SELECT * FROM `customer` WHERE `CUS_MOBILE_NBER` = '" + Txfiled_CusMnum_AddMO.getText() + "'";
        String trysql = "SELECT * FROM `customer` WHERE `CUS_MOBILE_NBER` LIKE '" + Txfiled_CusMnum_AddMO.getText() + "%';";
        Search(sql1, Choose);

    }

    @FXML
    private void M_Txfiled_SearchSP_AddMO(KeyEvent event) {
        Choose = 2;

        list.clear();
        if (Txfiled_SearchSP_AddMO.getText().isEmpty()) {
            String sql1 = "SELECT * FROM spare_parts";
            System.out.println(sql1);
            Search(sql1, Choose);

        } else {
            String sql1 = "SELECT * FROM spare_parts WHERE SP_NAME = '" + Txfiled_SearchSP_AddMO.getText() + "'";
            String trysql = "SELECT * FROM spare_parts WHERE SP_NAME LIKE '" + Txfiled_SearchSP_AddMO.getText() + "%';";
            System.out.println(trysql);
            Search(trysql, Choose);

        }
    }

    @FXML
    private void M_Txfiled_SPCost_AddMO(KeyEvent event) {
        calculate();
    }

    @FXML
    private void M_KeyReleased_TabelSelecSP_AddMO(KeyEvent event) {

        if (event.getCode().isNavigationKey() == true) {
            SPSelected2 = Table_SelectedSP_AddMO.getSelectionModel().getSelectedItems();

            Txfiled_SpSerialN_AddMO.setText(SPSelected2.get(0).getSP_SN());
            Txfiled_SpPrice_AddMO.setText(String.valueOf(SPSelected2.get(0).getSP_Price2()));

        }
    }

    public static class AddSP {

        private final SimpleIntegerProperty SP_Number;
        private final SimpleStringProperty SP_Name;
        private final SimpleStringProperty SP_Description;
        private final SimpleDoubleProperty SP_Price;
        private final SimpleIntegerProperty SP_Quantity;

        AddSP(int SP_Number, String SP_Name, String SP_Description, double SP_Price, int SP_Quantity) {
            this.SP_Number = new SimpleIntegerProperty(SP_Number);
            this.SP_Name = new SimpleStringProperty(SP_Name);
            this.SP_Description = new SimpleStringProperty(SP_Description);
            this.SP_Price = new SimpleDoubleProperty(SP_Price);
            this.SP_Quantity = new SimpleIntegerProperty(SP_Quantity);

        }

        public Integer getSP_Number() {
            return SP_Number.get();
        }

        public SimpleIntegerProperty SP_NumberProperty() {
            return SP_Number;
        }

        public String getSP_Name() {
            return SP_Name.get();
        }

        public SimpleStringProperty SP_NameProperty() {
            return SP_Name;
        }

        public String getSP_Description() {
            return SP_Description.get();
        }

        public SimpleStringProperty SP_DescriptionProperty() {
            return SP_Description;
        }

        public double getSP_Price() {
            return SP_Price.get();
        }

        public SimpleDoubleProperty SP_PriceProperty() {
            return SP_Price;
        }

        public Integer getSP_Quantity() {
            return SP_Quantity.get();
        }

        public SimpleIntegerProperty SP_QuantityProperty() {
            return SP_Quantity;
        }

    }

    public static class SelectedSP {

        private final SimpleIntegerProperty SP_Number2;
        private final SimpleStringProperty SP_Name2;
        private final SimpleStringProperty SP_Description2;
        private final SimpleDoubleProperty SP_Price2;
        private final SimpleStringProperty SP_SN;
        private final SimpleIntegerProperty SP_Seq_Nber;

        SelectedSP(Integer SP_Number2, String SP_Name2, String SP_Description2, double SP_Price2, String SP_SN, Integer SP_Seq_Nber) {
            this.SP_Number2 = new SimpleIntegerProperty(SP_Number2);
            this.SP_Name2 = new SimpleStringProperty(SP_Name2);
            this.SP_Description2 = new SimpleStringProperty(SP_Description2);
            this.SP_Price2 = new SimpleDoubleProperty(SP_Price2);
            this.SP_SN = new SimpleStringProperty(SP_SN);
            this.SP_Seq_Nber = new SimpleIntegerProperty(SP_Seq_Nber);

        }

        public Integer getSP2_Number() {
            return SP_Number2.get();
        }

        public SimpleIntegerProperty SP_Number2Property() {
            return SP_Number2;
        }

        public String getSP2_Name() {
            return SP_Name2.get();
        }

        public SimpleStringProperty SP_Name2Property() {
            return SP_Name2;
        }

        public String getSP2_Description() {
            return SP_Description2.get();
        }

        public SimpleStringProperty SP_Description2Property() {
            return SP_Description2;
        }

        public double getSP_Price2() {
            return SP_Price2.get();
        }

        public SimpleDoubleProperty SP_Price2Property() {
            return SP_Price2;
        }

        public String getSP_SN() {
            return SP_SN.get();
        }

        public SimpleStringProperty SP_SNProperty() {
            return SP_SN;
        }

        public Integer getSP_Seq_Nber() {
            return SP_Seq_Nber.get();
        }

        public SimpleIntegerProperty SP_Seq_NberProperty() {
            return SP_Seq_Nber;
        }

    }
}
