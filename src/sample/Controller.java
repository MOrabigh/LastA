package sample;

import Connectvy.ConnectionClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JRException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.swing.JOptionPane;

public class Controller implements Initializable {

    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);

    int count = 0;

    public ListView List_of_reports;
    ObservableList<String> items = FXCollections.observableArrayList();

    public ObservableList<MO> CurrnetList = FXCollections.observableArrayList();
    public ObservableList<MO> PendingList = FXCollections.observableArrayList();
    public ObservableList<MO> FinshedList = FXCollections.observableArrayList();
    public ObservableList<MO> PriveousList = FXCollections.observableArrayList();

    ObservableList<String> ListOfSuppliers = FXCollections.observableArrayList();
    ObservableList<AddSP> ListOFSelectedSP = FXCollections.observableArrayList();
    ObservableList<AddSP> ListOFSP = FXCollections.observableArrayList();

    @FXML
    public TableView<MO> Table_CurrentMO_MngMO;
    @FXML
    private TableColumn<MO, Double> Col_Cost_Current_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOEndDate_Current_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOtechin_Current_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_CusMobile_Current_MngMO;
    @FXML
    private TableColumn<MO, String> Col_CusName_Current_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_MOnum_Current_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOStatus_Current_MngMO;

    @FXML
    private TableView<MO> Table_FinshedMO_MngMO;
    @FXML
    private TableColumn<MO, Double> Col_MOCost_Finshed_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOEndDate_Finshed_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOtechin_Finshed_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_CusMobile_Finshed_MngMO;
    @FXML
    private TableColumn<MO, String> Col_CusName_Finshed_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_MOnum_Finshed_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOStatus_Finshed_MngMO;

    @FXML
    private TableView<MO> Table_PreviousMO_MngMO;
    @FXML
    private TableColumn<MO, Double> Col_MOCost_Previous_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOEndDate_Previous_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOtechin_Previous_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_CusMobile_Previous_MngMO;
    @FXML
    private TableColumn<MO, String> Col_CusName_Previous_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_MOnum_Previous_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOStatus_Previous_MngMO;

    @FXML
    private TableView<MO> Table_pendingMO_MngMO;
    @FXML
    private TableColumn<MO, Double> Col_MOCost_Pending_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_CusMobile_Pending_MngMO;
    @FXML
    private TableColumn<MO, String> Col_CusName_Pending_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_MOnum_Pending_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOtechin_Pending_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOStatus_Pending_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOEndDate_Pending_MngMO;
    @FXML
    private JFXButton Btn_Edit_MangeCurrentMO;
    @FXML
    private JFXTextField Txfiled_Search_MangeCurrentMO;
    public AnchorPane rootPage;
    @FXML
    private JFXDatePicker Date_REQdate_ReqSP;
    @FXML
    private JFXTextField Txfiled_REQnum_ReqSP;
    @FXML
    private JFXButton Btn_Print_ReqSP;
    @FXML
    private JFXButton Btn_Cancel_ReqSP;
    @FXML
    private JFXButton Btn_Delete_ReqSP;
    @FXML
    private JFXButton Btn_Save_ReqSP;
    @FXML
    private JFXButton Btn_Search_ReqSP;

    @FXML
    private TableView<AddSP> Table_SelectedSP_ReqSP;
    @FXML
    private TableColumn<AddSP, Integer> Col_SPQuantity_SelectedSP_ReqSP;
    @FXML
    private TableColumn<AddSP, String> Col_SPname_SelectedSP_ReqSP;
    @FXML
    private TableColumn<AddSP, Integer> Col_SPnum_SelectedSP_ReqSP;
    @FXML
    private JFXTextField Txfiled_QuanitiySP_ReqSP;
    @FXML
    private JFXButton Btn_RemoveSP_ReqSP;

    @FXML
    private TableView<AddSP> Table_AddSP_ReqSP;
    @FXML
    private TableColumn<AddSP, Integer> Col_SPQuantity_AddSP_ReqSP;
    @FXML
    private TableColumn<AddSP, String> Col_SPname_AddSP_ReqSP;
    @FXML
    private TableColumn<AddSP, Integer> Col_SPnum_AddSP_ReqSP;
    @FXML
    private JFXTextField Txfiled_SearchSP_ReqSP;
    @FXML
    private JFXButton Btn_AddSP_ReqSP;
    @FXML
    private ComboBox<String> Selct_Supplier_ReqSP;
    @FXML
    private Label SP_aboutTObeOUT_MainWindow;
    @FXML
    private Label PendingMO_MainWindow;
    @FXML
    private Label FinhedMO_MainWindow;
    @FXML
    private Label CurrentMO_MainWindow;

    @FXML
    private ImageView icMaonMove;
    @FXML
    private Label MainLable1;
    @FXML
    private JFXTextField Txfiled_Name_Customer;
    @FXML
    private JFXTextField Txfiled_Address_Customer;
    @FXML
    private JFXTextField Txfiled_MNum_Customer;
    @FXML
    private JFXTextField Txfiled_Email_Customer;
    @FXML
    private JFXButton Btn_ChangeMN_Customer;
    @FXML
    private JFXButton Btn_Save_Customer;
    @FXML
    private JFXButton Btn_Cancel_Customer;

    @FXML
    private JFXButton Btn_Delete_Customer;
    @FXML
    private JFXButton Btn_Search_Customer;

    @FXML
    private JFXTextField Txfiled_Name_SP;
    @FXML
    private JFXTextField Txfiled_Quantity_SP;
    @FXML
    private JFXTextField Txfiled_SPNum_SP;
    @FXML
    private JFXTextField Txfiled_Price_SP;
    @FXML
    private JFXTextArea Txfiled_Discription_SP;
    @FXML
    private JFXButton Btn_Cancel_SP;
    @FXML
    private JFXButton Btn_Delete_SP;
    @FXML
    private JFXButton Btn_Save_SP;

    @FXML
    private ToggleGroup ReportsDate;
    @FXML
    private Label MainLable;
    @FXML
    private JFXButton Btn_Cancel_Employee;

    @FXML
    private JFXButton Btn_Delete_Employee;

    @FXML
    private JFXButton Btn_Save_Employee;

    @FXML
    private JFXButton Btn_Search_Employee;

    @FXML
    private JFXTextField Txfiled_Name_Supplier;

    @FXML
    private JFXTextField Txfiled_Address_Supplier;

    @FXML
    private JFXTextField Txfiled_Num_Supplier;

    @FXML
    private JFXTextField Txfiled_MNum_Supplier;

    @FXML
    private JFXTextField Txfiled_Email_Supplier;

    @FXML
    private JFXButton Btn_Cancel_Supplier;

    @FXML
    private JFXButton Btn_Delete_Supplier;

    @FXML
    private JFXButton Btn_Save_Supplier;

    @FXML
    private JFXButton Btn_Search_Supplier;
    @FXML
    private JFXTextField Txfiled_Name_Employee;

    @FXML
    private JFXTextField Txfiled_MNum_Employee;

    @FXML
    private ComboBox<String> Selct_Sex_Employee;
    @FXML
    private ComboBox<String> Selct_Name_Employee;
    ObservableList<String> ListOfselectName = FXCollections.observableArrayList();
    @FXML
    private JFXTextField Txfiled_MO_Nber;

    @FXML
    private JFXTextField Txfiled_CUS_MNBER;
    @FXML
    private JFXDatePicker Date_Unill;

    @FXML
    private JFXDatePicker Date_StartFrom;

    @FXML
    private ComboBox<String> Selct_JType_Employee;
    ObservableList<String> ListOfJobtype_EN = FXCollections.observableArrayList("Administrator", "ReceptionDesk", "Technician");
    ObservableList<String> ListOfSex_EN = FXCollections.observableArrayList("Male", "Female");
    ObservableList<String> ListOfJobtype_AR = FXCollections.observableArrayList("اداري", "استقبال", "فني");
    ObservableList<String> ListOfSex_AR = FXCollections.observableArrayList("ذكر", "انثى");

    @FXML
    private JFXTextField Txfiled_Num_Employee;

    @FXML
    private JFXTextField Txfiled_Email_Employee;

    @FXML
    private JFXTextField Txfiled_Address_Employee;

    @FXML
    private JFXPasswordField Txfiled_Password_Employee;
    @FXML
    private JFXTextField Txfiled_minimumQuantity_SP;
    @FXML
    private JFXRadioButton Rad_Last_month;
    @FXML
    private JFXRadioButton Rad_LastThree_months;

    @FXML
    private JFXRadioButton Rad_This_Year;

    @FXML
    private JFXRadioButton Rad_Last_Year;

    @FXML
    private JFXRadioButton Rad_Choose_Period;
    @FXML
    public Tab Tab_CustomerMangment;
    public JFXTabPane FatharTap;
    @FXML
    public Tab Tab_SPMangment;
    @FXML
    public Tab Tab_ReqSP;
    @FXML
    public Tab Tab_SupliersMangment;
    @FXML
    public Tab Tab_EmployeeMangment;
    @FXML
    public Tab Tab_Reports;
    @FXML
    public Tab Tab_Tools;
    @FXML
    public Tab Main_Tab;
    @FXML
    public Tab Mangment_MO_Tab;

    public int count_Language;
    @FXML
    private Label Label_UserID_Name;
    @FXML
    private Label Label_UserJob;
    @FXML
    private HBox Hbox_Date_Reports;
    @FXML
    private HBox Hbox_Emolyee_Reports;
    @FXML
    private HBox Hbox_MO_Reports;
    @FXML
    private HBox Hbox_Customer_Reports;
    @FXML
    public JFXButton Btn_SaveDB_Tools;
    @FXML
    public JFXButton Btn_ArchiveDB_Tools;
    @FXML
    private JFXTextField Txfiled_Search_MangeFinshedMO;
    @FXML
    private JFXTextField Txfiled_Search_MangePreviousMO;
    @FXML
    private JFXTextField Txfiled_Search_MangePendingMO;
    @FXML
    private JFXButton Btn_Search_SP;
    @FXML
    private JFXButton Btn_RestoreDB_Tools;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectionClass.connectDB();

        intilCol();
        loadSuppliers();
        List_of_reports.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Selct_Name_Employee.setItems(ListOfselectName);
        loadEmp();
    }

    public void EmJob_SEX_lang(int c) {
        count_Language = c;
        if (count_Language == 0) {
            Selct_JType_Employee.setItems(ListOfJobtype_EN);
            Selct_Sex_Employee.setItems(ListOfSex_EN);

            List_of_reports.getItems().add("- current  maintenance operations");
            List_of_reports.getItems().add("- Finished maintenance operations");
            List_of_reports.getItems().add("- previous  maintenance operations");
            List_of_reports.getItems().add("- financial estimate of maintenance");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- list of customers");
            List_of_reports.getItems().add("- list of customer maintenance operations");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- list of employees");
            List_of_reports.getItems().add("- List of maintenance operations for an employee");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- list of Suppliers");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- Spare Parts about to be out of stock");
            List_of_reports.getItems().add("- Spare parts out of stock");

        } else if (count_Language == 1) {
            Selct_JType_Employee.setItems(ListOfJobtype_AR);
            Selct_Sex_Employee.setItems(ListOfSex_AR);

            List_of_reports.getItems().add("- عمليات الصيانة الحالية");
            List_of_reports.getItems().add("- عمليات الصيانة المنتهية");
            List_of_reports.getItems().add("- عمليات الصيانة السابقة");
            List_of_reports.getItems().add("- تقدير مالي عن عملية صيانة");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- قائمة بالعملاء");
            List_of_reports.getItems().add("- قائمة عمليات الصيانة لعميل");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- قائمة بالموظفين");
            List_of_reports.getItems().add("- قائمة عمليات الصيانة لموظف");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- قائمة بالمزودين");
            List_of_reports.getItems().add("---------------------------");
            List_of_reports.getItems().add("- قطع على وشك النفاذ");
            List_of_reports.getItems().add("- قطع الغيار التي نفذت كميتها");

        }
        loadAllMO();

    }

    private void intilCol() {

        Col_MOnum_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Number"));
        Col_CusName_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Name"));
        Col_CusMobile_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Mobile"));
        Col_MOtechin_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_technician"));
        Col_MOEndDate_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_EndDate"));
        Col_Cost_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_TotalCost"));
        Col_MOStatus_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Status"));

        Col_MOnum_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Number"));
        Col_CusName_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Name"));
        Col_CusMobile_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Mobile"));
        Col_MOtechin_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_technician"));
        Col_MOEndDate_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_EndDate"));
        Col_MOCost_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_TotalCost"));
        Col_MOStatus_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Status"));

        Col_MOnum_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Number"));
        Col_CusName_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Name"));
        Col_CusMobile_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Mobile"));
        Col_MOtechin_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_technician"));
        Col_MOEndDate_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_EndDate"));
        Col_MOCost_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_TotalCost"));
        Col_MOStatus_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Status"));

        Col_MOnum_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Number"));
        Col_CusName_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Name"));
        Col_CusMobile_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Mobile"));
        Col_MOtechin_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_technician"));
        Col_MOEndDate_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_EndDate"));
        Col_MOCost_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_TotalCost"));
        Col_MOStatus_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Status"));

        Col_SPnum_AddSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Number"));
        Col_SPname_AddSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Name"));
        Col_SPQuantity_AddSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Quantity"));

        Col_SPnum_SelectedSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Number"));
        Col_SPname_SelectedSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Name"));
        Col_SPQuantity_SelectedSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Quantity"));

    }

    public void loadAllMO() {

        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID";
        ResultSet rs = connectionClass.execQuery(query);

        try {
            while (rs.next()) {

                String MONber = rs.getString("MO_NBER");
                String mobile = rs.getString("CUS_MOBILE_NBER");
                String priceSP = rs.getString("SP_COST");
                String priceMO = rs.getString("MO_COST");
                int MO_num = Integer.parseInt(MONber);
                int CusMobile = Integer.parseInt(mobile);

                double total = Double.parseDouble(priceSP) + Double.parseDouble(priceMO);
                double vat = total * 0.05;
                double TotalCost = total + vat;

                String State;

                if (rs.getString("STATE").equals("created") || rs.getString("STATE").equals("تم الإنشاء")) {
                    if (count_Language == 0) {
                        State = "created";
                    } else {
                        State = "تم الإنشاء";

                    }
                    PendingList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                } else if (rs.getString("STATE").equals("approved") || rs.getString("STATE").equals("تم الموافقة")) {
                    if (count_Language == 0) {
                        State = "approved";

                    } else {
                        State = "تم الموافقة";

                    }
                    CurrnetList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                } else if (rs.getString("STATE").equals("مرفوضة") || rs.getString("STATE").equals("disapproved")) {
                    if (count_Language == 0) {
                        State = "disapproved";

                    } else {
                        State = "مرفوضة";

                    }
                    PriveousList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                } else if (rs.getString("STATE").equals("لا يمكن القيام بعملية الصيانة") || rs.getString("STATE").equals("cannot be done")) {
                    if (count_Language == 0) {
                        State = "cannot be done";
                    } else {
                        State = "لا يمكن القيام بعملية الصيانة";

                    }
                    PendingList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                } else if (rs.getString("STATE").equals("تم الكشف عن عيوب أخرى") || rs.getString("STATE").equals("other defects has been detected")) {

                    if (count_Language == 0) {
                        State = "other defects has been detected";

                    } else {
                        State = "تم الكشف عن عيوب أخرى";

                    }
                    PendingList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                } else if (rs.getString("STATE").equals("تم الاصلاح") || rs.getString("STATE").equals("repaired")) {

                    if (count_Language == 0) {
                        State = "repaired";

                    } else {
                        State = "تم الاصلاح";

                    }
                    FinshedList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                } else if (rs.getString("STATE").equals("تحت الصيانة") || rs.getString("STATE").equals("under maintenance")) {

                    if (count_Language == 0) {
                        State = "under maintenance";

                    } else {
                        State = "تحت الصيانة";

                    }
                    CurrnetList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                } else if (rs.getString("STATE").equalsIgnoreCase("دفعت") || rs.getString("STATE").equalsIgnoreCase("paid")) {
                    if (count_Language == 0) {

                        State = "paid";

                    } else {
                        State = "دفعت";

                    }
                    PriveousList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                }
            }
            rs.close();

            String SPqury = "SELECT SP_NBER FROM spare_parts WHERE `SP_QUANTITY` < `MINIMUM_QUANTITY_IN_STOCK` AND `SP_QUANTITY` > 0";
            ResultSet rs2 = connectionClass.execQuery(SPqury);
            int rowcount = 0;
            if (rs2.last()) {
                rowcount = rs2.getRow();
            }

            Table_CurrentMO_MngMO.getItems().setAll(CurrnetList);
            Table_FinshedMO_MngMO.getItems().setAll(FinshedList);
            Table_PreviousMO_MngMO.getItems().setAll(PriveousList);
            Table_pendingMO_MngMO.getItems().setAll(PendingList);
            PendingMO_MainWindow.setText(String.valueOf(PendingList.size()));
            CurrentMO_MainWindow.setText(String.valueOf(CurrnetList.size()));
            FinhedMO_MainWindow.setText(String.valueOf(FinshedList.size()));
            SP_aboutTObeOUT_MainWindow.setText(String.valueOf(rowcount));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void SP_Alert_Admin_EN() {

        try {
            String SPqury = "SELECT SP_NBER FROM spare_parts WHERE `SP_QUANTITY` < `MINIMUM_QUANTITY_IN_STOCK` AND `SP_QUANTITY` > 0";
            ResultSet rs2 = connectionClass.execQuery(SPqury);
            int rowcount = 0;
            if (rs2.last()) {
                rowcount = rs2.getRow();
            }
            if (rowcount > 0) {
                String alertMessage = "There are " + rowcount + "  Spare parts are about to be out of stock";

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText(alertMessage);
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void SP_Alert_Admin_AR() {

        try {
            String SPqury = "SELECT SP_NBER FROM spare_parts WHERE `SP_QUANTITY` < `MINIMUM_QUANTITY_IN_STOCK` AND `SP_QUANTITY` > 0";
            ResultSet rs2 = connectionClass.execQuery(SPqury);
            int rowcount = 0;
            if (rs2.last()) {
                rowcount = rs2.getRow();
            }

            if (rowcount > 0) {
                String alertMessage = "هناك " + rowcount + " قطع غيار على وشك النفاذ";

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText(alertMessage);
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    private void loadAllSP() {
        ListOFSP.clear();
        String query = "SELECT * FROM spare_parts";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {
                String SPnumber = rs.getString("SP_NBER");

                String SPquantity = rs.getString("SP_QUANTITY");

                int SP_num = Integer.parseInt(SPnumber);
                int SP_quantity = Integer.parseInt(SPquantity);

                ListOFSP.add(new AddSP(SP_num, rs.getString("SP_NAME"), SP_quantity));

            }
            rs.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        Table_AddSP_ReqSP.getItems().setAll(ListOFSP);

    }

    private void loadSpecifecSP() {
        try {
            ListOFSP.clear();
            String query = "SELECT * FROM `spare_parts`";
            ResultSet rs = connectionClass.execQuery(query);
            if (rs.isBeforeFirst() == false) {

                loadAllSP();
            } else {

                while (rs.next()) {
                    String SPnumber = rs.getString("SP_NBER");

                    String SPquantity = rs.getString("SP_QUANTITY");

                    int SP_num = Integer.parseInt(SPnumber);
                    int SP_quantity = Integer.parseInt(SPquantity);

                    ListOFSP.add(new AddSP(SP_num, rs.getString("SP_NAME"), SP_quantity));

                }

                rs.close();
                for (int i = 0; i < ListOFSP.size(); i++) {
                    for (int j = 0; j < ListOFSelectedSP.size(); j++) {
                        if (ListOFSP.get(i).getSP_Number().equals(ListOFSelectedSP.get(j).getSP_Number())) {
                            ListOFSP.remove(i);

                        }
                    }

                }

                Table_AddSP_ReqSP.getItems().setAll(ListOFSP);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    private void loadSpSelected() {
        ListOFSelectedSP.clear();
        String SQLqq = "SELECT * FROM `spare_parts` s JOIN `attach` r ON s.SP_NBER = r.SP_NBER WHERE r.REQUEST_NBER=" + Txfiled_REQnum_ReqSP.getText();

        ResultSet rs = connectionClass.execQuery(SQLqq);

        try {
            double a = 0.00;
            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    int SP_num = Integer.parseInt(rs.getString("SP_NBER"));
                    int SP_Quant = Integer.parseInt(rs.getString("Req_QUANTITY"));
                    ListOFSelectedSP.add(new AddSP(SP_num, rs.getString("SP_Name"), SP_Quant));
                }
                Table_SelectedSP_ReqSP.getItems().setAll(ListOFSelectedSP);
            }

            rs.close();

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

            stage.showAndWait();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void Btn_AddMO_MangeMO(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader();

        if (count_Language == 0) {
            loader.setLocation(getClass().getResource("/sample/AddMo_EN.fxml"));
            try {
                loader.load();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

            Controller_AddMO controller_AddMO = loader.getController();
            controller_AddMO.SetMoStatus_language(0);
        } else if (count_Language == 1) {
            loader.setLocation(getClass().getResource("/sample/AddMo_AR.fxml"));

            try {

                loader.load();
                Controller_AddMO controller_AddMO = loader.getController();
                controller_AddMO.SetMoStatus_language(1);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.showAndWait();

        RefreshMOTables();

    }

    public static boolean EmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
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

    public static boolean PhoneNvalid(String s) {

        if (s.matches("[0-9]*") && s.length() > 9) {
            return true;
        } else {
            return false;
        }

    }
    String CusMO = "";

    @FXML
    private void M_Btn_ChangeMN_Customer(ActionEvent event) {
        count = 3;
        Txfiled_MNum_Customer.setDisable(false);
        Txfiled_Name_Customer.setDisable(true);
        Txfiled_Email_Customer.setDisable(true);
        Txfiled_Address_Customer.setDisable(true);
        Btn_Save_Customer.setDisable(false);

        CusMO = Txfiled_MNum_Customer.getText();
    }

    @FXML
    private void M_Btn_Cancel_Customer(ActionEvent event) {
        ClearCus();

    }

    void ClearCus() {
        Txfiled_MNum_Customer.clear();
        Txfiled_Name_Customer.clear();
        Txfiled_Email_Customer.clear();
        Txfiled_Address_Customer.clear();

        Txfiled_MNum_Customer.setDisable(false);
        Txfiled_Name_Customer.setDisable(true);
        Txfiled_Email_Customer.setDisable(true);
        Txfiled_Address_Customer.setDisable(true);

        Btn_ChangeMN_Customer.setDisable(true);
        Btn_Cancel_Customer.setDisable(true);
        Btn_Delete_Customer.setDisable(true);
        Btn_Search_Customer.setDisable(false);
        Btn_Save_Customer.setDisable(true);
    }

    @FXML
    private void Btn_Edit_MangeFinshedMO(ActionEvent event) {
        openEdit(Table_FinshedMO_MngMO);

    }

    @FXML
    private void Btn_Edit_MangePreviousMO(ActionEvent event) {
        openEdit(Table_PreviousMO_MngMO);

    }

    public void openEdit(TableView TableName) {

        ObservableList<MO> SPSelected, AllSP;
        AllSP = TableName.getItems();
        SPSelected = TableName.getSelectionModel().getSelectedItems();
        if (TableName.getSelectionModel().getSelectedItems().isEmpty() == false) {
            FXMLLoader loader = new FXMLLoader();
            if (count_Language == 0) {

                loader.setLocation(getClass().getResource("/sample/AddMo_EN.fxml"));
                try {
                    loader.load();

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                Controller_AddMO controller_AddMO = loader.getController();
                controller_AddMO.SetMoStatus_language(0);

            } else if (count_Language == 1) {

                loader.setLocation(getClass().getResource("/sample/AddMo_AR.fxml"));
                try {
                    loader.load();

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                Controller_AddMO controller_AddMO = loader.getController();
                controller_AddMO.SetMoStatus_language(1);

            }
            Controller_AddMO controller_AddMO = loader.getController();
            controller_AddMO.Search_MO(SPSelected.get(0).getMO_Number());

            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.showAndWait();
            RefreshMOTables();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            if (count_Language == 0) {

                alert.setContentText("Please choose a maintenance process from the table");
                alert.showAndWait();
            } else if (count_Language == 1) {

                alert.setContentText("الرجاء اختيار عملية صيانة من الجدول");
                alert.showAndWait();

            }

        }

    }

    public void RefreshMOTables() {
        CurrnetList.clear();
        PendingList.clear();
        FinshedList.clear();
        PriveousList.clear();

        loadAllMO();
    }

    @FXML
    private void Btn_Edit_MangePendingMO(ActionEvent event) {
        openEdit(Table_pendingMO_MngMO);

    }

    @FXML
    private void M_Btn_Delete_Customer(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("");
            
              Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setTitle("");
            alert3.setHeaderText("");
            if (count_Language == 0) {
                alert.setContentText("This Cstomer will be deleted ");
            } else {
                alert.setContentText("  سوف يتم حذف هذا العميل ");

            }

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                String sql1 = "DELETE FROM  `customer`  WHERE CUS_MOBILE_NBER= " + Txfiled_MNum_Customer.getText();
                java.sql.Statement statement1 = connection.createStatement();
                ClearCus();
                try {
                    statement1.executeUpdate(sql1);

                    {
                        if (count_Language == 0) {

                            alert3.setHeaderText(null);
                            alert3.setContentText("Deleted Successfully");
                            alert3.showAndWait();
                            return;

                        } else if (count_Language == 1) {

                            alert3.setHeaderText(null);
                            alert3.setContentText("تم الحذف بنجاح");
                            alert3.showAndWait();
                            return;
                        }
                    }

                } catch (SQLException e) {
                    if (count_Language == 0) {

                        alert3.setHeaderText(null);
                        alert3.setContentText("Can not delete this customer");
                        alert3.showAndWait();
                        return;
                    } else if (count_Language == 1) {

                        alert3.setHeaderText(null);
                        alert3.setContentText("لا يمكن حذف هذا العميل");
                        alert3.showAndWait();
                    }

                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void M_Btn_Save_Customer(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        String email = Txfiled_Email_Customer.getText();

        if (!PhoneNvalid(Txfiled_MNum_Customer.getText())) {

            if (count_Language == 0) {
                alert.setContentText("Please enter a valid mobile number ");
            } else {
                alert.setContentText("الرجاء إدخال رقم هاتف صحيح");

            }
            alert.showAndWait();
            return;
        }
        if (Txfiled_Name_Customer.getText().isEmpty()) {

            if (count_Language == 0) {
                alert.setContentText("Please, enter the customer name.");
            } else {
                alert.setContentText("الرجاء إدخال اسم العميل");

            }
            alert.showAndWait();
            return;
        }

        if (!EmailValid(email)) {

            if (count_Language == 0) {
                alert.setContentText("Please enter a valid email address");
            } else {
                alert.setContentText("الرجاء إدخال عنوان بريد إلكتروني صحيح");

            }
            alert.showAndWait();
            return;
        }
        if (Txfiled_Address_Customer.getText().isEmpty()) {

            if (count_Language == 0) {
                alert.setContentText("Please enter customer address ");
            } else {
                alert.setContentText("الرجاء إدخال عنوان العميل");

            }
            alert.showAndWait();
            return;
        }

        if (count == 1) {
            try {
                String sqll = ("INSERT INTO customer (CUS_MOBILE_NBER,CUS_NAME,CUS_EMAIL,CUS_ADDRESS) VALUES('" + Txfiled_MNum_Customer.getText() + "','" + Txfiled_Name_Customer.getText() + "','" + Txfiled_Email_Customer.getText() + "','" + Txfiled_Address_Customer.getText() + "')");
                java.sql.Statement statement1 = connection.createStatement();

                statement1.executeUpdate(sqll);
                if (count_Language == 0) {

                    alert2.setContentText(" A new Customer has been created");
                } else {
                    alert2.setContentText("تم انشاء عميل جديد");

                }
                alert2.showAndWait();
                ClearCus();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        } else if (count == 2) {
            try {
                String sql1 = "UPDATE  `customer` SET CUS_NAME='" + Txfiled_Name_Customer.getText() + "',CUS_EMAIL='" + Txfiled_Email_Customer.getText() + "',CUS_ADDRESS='" + Txfiled_Address_Customer.getText()
                        + "' WHERE CUS_MOBILE_NBER= '" + Txfiled_MNum_Customer.getText() + "'";
                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sql1);
                if (count_Language == 0) {

                    alert2.setContentText(" Changes saved successfully");
                } else {
                    alert2.setContentText("تم حفظ التعديلات بنجاح");

                }

                alert2.showAndWait();
                ClearCus();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else if (count == 3) {

            try {
                String sqlSetGl = "set GLOBAL FOREIGN_key_checks=0";
                java.sql.Statement statement3 = connection.createStatement();
                statement3.executeUpdate(sqlSetGl);

                String sqll = "UPDATE customer SET CUS_MOBILE_NBER='" + Txfiled_MNum_Customer.getText() + "' WHERE CUS_NAME= '" + Txfiled_Name_Customer.getText() + "'";
                java.sql.Statement statement1 = connection.createStatement();

                String sqlupdatePrice = "UPDATE `maintenance_operation` SET `CUS_MOBILE_NBER` = " + Txfiled_MNum_Customer.getText() + " WHERE `CUS_MOBILE_NBER` = " + CusMO + ";";
                statement1.executeUpdate(sqll);
                java.sql.Statement statement2 = connection.createStatement();

                statement2.executeUpdate(sqlupdatePrice);
                if (count_Language == 0) {

                    alert2.setContentText("Mobile Number has been changed");
                } else {
                    alert2.setContentText("تم تغيير رقم الهاتف");

                }

                alert2.showAndWait();
                ClearCus();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

    }

    int SP_number = 0;
    int SUP_number = 0;
    int EMP_number = 0;

    @FXML
    private void M_Btn_Search_Customer(ActionEvent event) {
        alert2.setTitle(null);
        alert2.setHeaderText(null);

        if ((PhoneNvalid(Txfiled_MNum_Customer.getText()))) {

            Txfiled_MNum_Customer.setDisable(true);
            Txfiled_Name_Customer.setDisable(false);
            Txfiled_Email_Customer.setDisable(false);
            Txfiled_Address_Customer.setDisable(false);
            Btn_Search_Customer.setDisable(true);

            try {
                String sql1 = "SELECT * FROM `customer` WHERE `CUS_MOBILE_NBER` = '" + Txfiled_MNum_Customer.getText() + "'";
                Connection connection = connectionClass.getConnection();
                Statement st = connection.createStatement();
                st.executeQuery(sql1);
                ResultSet rs = st.getResultSet();
                if (rs.first()) {

                    count = 2;

                    Txfiled_Name_Customer.setText(rs.getString("CUS_NAME"));
                    Txfiled_Email_Customer.setText(rs.getString("CUS_EMAIL"));
                    Txfiled_Address_Customer.setText(rs.getString("CUS_ADDRESS"));

                    Btn_Delete_Customer.setDisable(false);
                    Btn_Cancel_Customer.setDisable(false);
                    Btn_Save_Customer.setDisable(false);
                    Btn_ChangeMN_Customer.setDisable(false);

                } else {

                    if (count_Language == 0) {
                        alert2.setContentText("New customer will be created");
                    } else {
                        alert2.setContentText(" سوف يتم انشاء عميل جديد");

                    }
                    alert2.showAndWait();
                    count = 1;

                    Btn_Cancel_Customer.setDisable(false);
                    Btn_Save_Customer.setDisable(false);
                    Btn_Delete_Customer.setDisable(true);
                    Btn_ChangeMN_Customer.setDisable(true);

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else {
            if (count_Language == 0) {
                alert2.setContentText("invalid Mobile Number");
            } else {
                alert2.setContentText(" رقم الهاتف خاطئ");

            }
            alert2.showAndWait();

        }
    }

    @FXML
    void M_Btn_Cancel_Employee(ActionEvent event) {
        clearEmp();
    }

    void clearEmp() {

        Txfiled_Num_Employee.setDisable(false);
        Txfiled_Num_Employee.clear();
        Txfiled_Name_Employee.clear();
        Txfiled_Email_Employee.clear();
        Txfiled_Address_Employee.clear();
        Txfiled_MNum_Employee.clear();
        Selct_JType_Employee.getSelectionModel().clearSelection();
        Selct_Sex_Employee.getSelectionModel().clearSelection();
        Txfiled_Password_Employee.clear();
        Btn_Save_Employee.setDisable(true);
        Btn_Delete_Employee.setDisable(true);
        Btn_Cancel_Employee.setDisable(true);
        Btn_Search_Employee.setDisable(false);

        Txfiled_Name_Employee.setDisable(true);
        Txfiled_Email_Employee.setDisable(true);
        Txfiled_Address_Employee.setDisable(true);
        Txfiled_MNum_Employee.setDisable(true);
        Selct_JType_Employee.setDisable(true);
        Selct_Sex_Employee.setDisable(true);
        Txfiled_Password_Employee.setDisable(true);

    }

    @FXML
    void M_Btn_Delete_Employee(ActionEvent event) {
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("");
            
             Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setTitle("");
            alert3.setHeaderText("");
            
            if (count_Language == 0) {
                alert.setContentText("This employee will be deleted ");
            } else {
                alert.setContentText("  سوف يتم حذف هذا الموظف ");

            }

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                String sql1 = "DELETE FROM  `employee`  WHERE EMPLOYEE_ID= " + Txfiled_Num_Employee.getText();
                java.sql.Statement statement1 = connection.createStatement();

                clearEmp();
                try {
                    statement1.executeUpdate(sql1);

                    {
                        if (count_Language == 0) {

                            alert3.setHeaderText(null);
                            alert3.setContentText("Deleted Successfully");
                            alert3.showAndWait();
                            return;
                        } else if (count_Language == 1) {

                            alert3.setHeaderText(null);
                            alert3.setContentText("تم الحذف بنجاح ");
                            alert3.showAndWait();
                            return;

                        }
                    }
                } catch (SQLException e) {
                    if (count_Language == 0) {

                        alert3.setHeaderText(null);
                        alert3.setContentText("Can not delete this employee");
                        alert3.showAndWait();
                        return;
                    } else if (count_Language == 1) {

                        alert3.setHeaderText(null);
                        alert3.setContentText("لا يمكن حذف هذا الموظف");
                        alert3.showAndWait();
                        return;
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void M_Btn_Save_Employee(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        if (!PhoneNvalid(Txfiled_MNum_Employee.getText())) {

            if (count_Language == 0) {
                alert.setContentText("Please enter a valid mobile number");
            } else {
                alert.setContentText("الرجاء إدخال رقم هاتف صحيح");

            }
            alert.showAndWait();
            return;
        }
        if (!EmailValid(Txfiled_Email_Employee.getText())) {

            if (count_Language == 0) {
                alert.setContentText("Please enter a valid email address");
            } else {
                alert.setContentText("الرجاء إدخال عنوان بريد الكتروني صحيح");

            }
            alert.showAndWait();
            return;
        }
        if (Txfiled_Name_Employee.getText().isEmpty()) {

            if (count_Language == 0) {
                alert.setContentText("Please enter the employee name");
            } else {
                alert.setContentText("الرجاء ادخال اسم الموظف");

            }
            alert.showAndWait();
            return;
        }
        if (Txfiled_Address_Employee.getText().isEmpty()) {

            if (count_Language == 0) {
                alert.setContentText("Please enter an address");
            } else {
                alert.setContentText("الرجاء إدخال العنوان");

            }
            alert.showAndWait();
            return;
        }
        if (Selct_JType_Employee.getSelectionModel().isEmpty()) {

            if (count_Language == 0) {
                alert.setContentText("Please select a jop type");
            } else {
                alert.setContentText("الرجاء اختيار نوع الوظيفة");

            }
            alert.showAndWait();
            return;
        }
        if (Selct_Sex_Employee.getSelectionModel().isEmpty()) {

            if (count_Language == 0) {
                alert.setContentText("Please select a sex");
            } else {
                alert.setContentText("الرجاء اختيار النوع");

            }
            alert.showAndWait();
            return;
        }
        if (Txfiled_Password_Employee.getText().isEmpty()) {

            if (count_Language == 0) {
                alert.setContentText("Please enter a password ");
            } else {
                alert.setContentText("الرجاء إدخال كلمة المرور");

            }
            alert.showAndWait();
            return;
        }

        if (count == 1) {
            try {
                String sqll = "INSERT INTO employee (EMPLOYEE_ID, EMP_NAME, EMP_EMAIL,EMP_ADDRESS,EMP_MOBILE_NBER,JOP_TYPE,SEX,PASSWORD) VALUES (" + EMP_number + "," + "'" + Txfiled_Name_Employee.getText() + "'" + "," + "'" + Txfiled_Email_Employee.getText()
                        + "'" + "," + "'" + Txfiled_Address_Employee.getText() + "'" + "," + "'" + Txfiled_MNum_Employee.getText() + "'" + "," + "'" + Selct_JType_Employee.getValue() + "'" + "," + "'" + Selct_Sex_Employee.getValue()
                        + "'" + "," + "'" + Txfiled_Password_Employee.getText() + "')";
                java.sql.Statement statement1 = connection.createStatement();

                statement1.executeUpdate(sqll);
                if (count_Language == 0) {

                    alert2.setContentText(" A new Employee has been created");
                } else {
                    alert2.setContentText("تم انشاء موظف جديد");
                }

                alert2.showAndWait();
                clearEmp();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else if (count == 2) {
            try {
                String sql1 = "UPDATE  `employee` SET   EMP_NAME='" + Txfiled_Name_Employee.getText() + "',EMP_EMAIL='" + Txfiled_Email_Employee.getText() + "',EMP_ADDRESS='" + Txfiled_Address_Employee.getText() + "',EMP_MOBILE_NBER='" + Txfiled_MNum_Employee.getText()
                        + "',JOP_TYPE='" + Selct_JType_Employee.getValue() + "',SEX='" + Selct_Sex_Employee.getValue() + "',PASSWORD='" + Txfiled_Password_Employee.getText()
                        + "'WHERE EMPLOYEE_ID=' " + Txfiled_Num_Employee.getText() + "'";

                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sql1);
                if (count_Language == 0) {

                    alert2.setContentText(" Changes saved successfully");
                } else {
                    alert2.setContentText("تم حفظ التعديلات بنجاح");

                }

                alert2.showAndWait();
                clearEmp();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

    }

    @FXML
    private void M_Btn_Search_Employee(ActionEvent event) {
        alert2.setTitle(null);
        alert2.setHeaderText(null);
        if (!Mumbervalid(Txfiled_Num_Employee.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            if (count_Language == 0) {
                alert.setContentText("Please enter employee number");
            } else {
                alert.setContentText("الرجاء ادخال رقم الموظف ");

            }
            alert.showAndWait();
            return;

        }

        Txfiled_Num_Employee.setDisable(true);
        Btn_Save_Employee.setDisable(false);
        Btn_Cancel_Employee.setDisable(false);
        Btn_Search_Employee.setDisable(true);

        Txfiled_Name_Employee.setDisable(false);
        Txfiled_Email_Employee.setDisable(false);
        Txfiled_Address_Employee.setDisable(false);
        Txfiled_MNum_Employee.setDisable(false);
        Selct_JType_Employee.setDisable(false);
        Selct_Sex_Employee.setDisable(false);
        Txfiled_Password_Employee.setDisable(false);
        try {
            Connection connection = connectionClass.getConnection();
            Statement st = connection.createStatement();
            st.executeQuery("SELECT * FROM `employee`  WHERE EMPLOYEE_ID = " + Txfiled_Num_Employee.getText());
            ResultSet rs = st.getResultSet();
            if (rs.first()) {

                if (rs.getString("EMPLOYEE_ID").equals(Txfiled_Num_Employee.getText())) {

                    count = 2;

                    Txfiled_Name_Employee.setText(rs.getString("EMP_NAME"));
                    Txfiled_Email_Employee.setText(rs.getString("EMP_EMAIL"));
                    Txfiled_Address_Employee.setText(rs.getString("EMP_ADDRESS"));
                    String emnumber = "0" + rs.getString("EMP_MOBILE_NBER");

                    Txfiled_MNum_Employee.setText(emnumber);

                    Txfiled_Password_Employee.setText(rs.getString("PASSWORD"));

                    if (rs.getString("JOP_TYPE").equals("فني") || rs.getString("JOP_TYPE").equals("Technician")) {
                        if (count_Language == 0) {
                            Selct_JType_Employee.getSelectionModel().select("Technician");
                        } else {
                            Selct_JType_Employee.getSelectionModel().select("فني");

                        }
                    } else if (rs.getString("JOP_TYPE").equals("اداري") || rs.getString("JOP_TYPE").equals("Administrator")) {
                        if (count_Language == 0) {
                            Selct_JType_Employee.getSelectionModel().select("Administrator");
                        } else {
                            Selct_JType_Employee.getSelectionModel().select("اداري");

                        }

                    } else if (rs.getString("JOP_TYPE").equals("استقبال") || rs.getString("JOP_TYPE").equals("ReceptionDesk")) {
                        if (count_Language == 0) {
                            Selct_JType_Employee.getSelectionModel().select("ReceptionDesk");
                        } else {
                            Selct_JType_Employee.getSelectionModel().select("استقبال");

                        }

                    }
                    if (rs.getString("SEX").equals("ذكر") || rs.getString("SEX").equals("Male")) {
                        if (count_Language == 0) {
                            Selct_Sex_Employee.getSelectionModel().select("Male");
                        } else {
                            Selct_Sex_Employee.getSelectionModel().select("ذكر");

                        }

                    } else if (rs.getString("SEX").equals("انثى") || rs.getString("SEX").equals("Female")) {
                        if (count_Language == 0) {
                            Selct_Sex_Employee.getSelectionModel().select("Female");
                        } else {
                            Selct_Sex_Employee.getSelectionModel().select("انثى");

                        }

                    }
                    Btn_Delete_Employee.setDisable(false);

                }
            } else {
                Statement st2 = connection.createStatement();
                st2.executeQuery("SELECT * FROM `employee` ORDER BY EMPLOYEE_ID DESC LIMIT 1");
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {

                    if (count_Language == 0) {
                        alert2.setContentText("New employee will be created");
                    } else {
                        alert2.setContentText(" سوف يتم انشاء موظف جديد");

                    }
                    alert2.showAndWait();

                    count = 1;
                    EMP_number = Integer.parseInt(rs2.getString("EMPLOYEE_ID"));
                    EMP_number++;
                    Txfiled_Num_Employee.setText(String.valueOf(EMP_number));

                    Btn_Delete_Employee.setDisable(true);

                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }

    }

    @FXML
    void M_Btn_Search_SP(ActionEvent event) throws SQLException {
        alert2.setTitle(null);
        alert2.setHeaderText(null);
        if (!Mumbervalid(Txfiled_SPNum_SP.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            if (count_Language == 0) {
                alert.setContentText("Please enter Spare part number");
            } else {
                alert.setContentText("الرجاء ادخال رقم قطعة الغيار ");

            }
            alert.showAndWait();
            return;

        }
        Btn_Search_SP.setDisable(true);
        Txfiled_Name_SP.setDisable(false);
        Txfiled_Price_SP.setDisable(false);
        Txfiled_Quantity_SP.setDisable(false);
        Txfiled_Discription_SP.setDisable(false);
        Txfiled_minimumQuantity_SP.setDisable(false);
        Btn_Save_SP.setDisable(false);
        Btn_Cancel_SP.setDisable(false);
        Txfiled_SPNum_SP.setDisable(true);

        Connection connection = connectionClass.getConnection();
        Statement st = connection.createStatement();
        st.executeQuery("SELECT * FROM `spare_parts`  WHERE SP_NBER = " + Txfiled_SPNum_SP.getText());
        ResultSet rs = st.getResultSet();
        if (rs.first()) {

            if (rs.getString("SP_NBER").equals(Txfiled_SPNum_SP.getText())) {

                count = 2;

                Txfiled_Name_SP.setText(rs.getString("SP_NAME"));
                Txfiled_Price_SP.setText(rs.getString("PRICE"));
                Txfiled_Quantity_SP.setText(rs.getString("SP_QUANTITY"));
                Txfiled_Discription_SP.setText(rs.getString("DESCRIPTION"));
                Txfiled_minimumQuantity_SP.setText(rs.getString("MINIMUM_QUANTITY_IN_STOCK"));

                Btn_Delete_SP.setDisable(false);

            }
        } else {

            Statement st2 = connection.createStatement();
            st2.executeQuery("SELECT * FROM spare_parts ORDER BY SP_NBER DESC LIMIT 1");
            ResultSet rs2 = st2.getResultSet();
            if (rs2.first()) {

                if (count_Language == 0) {
                    alert2.setContentText("New spare part will be created");
                } else {
                    alert2.setContentText("سوف يتم انشاء قطعة غيار جديدة");

                }
                alert2.showAndWait();

                count = 1;
                SP_number = Integer.parseInt(rs2.getString("SP_NBER"));
                SP_number++;
                Txfiled_SPNum_SP.setText(String.valueOf(SP_number));

                Btn_Delete_SP.setDisable(true);

            }
        }

    }

    @FXML
    private void M_Btn_Cancel_SP(ActionEvent event) {
        ClearSp();
    }

    void ClearSp() {
        Txfiled_SPNum_SP.setDisable(false);
        Txfiled_SPNum_SP.clear();
        Txfiled_Name_SP.clear();
        Txfiled_Price_SP.clear();
        Txfiled_Quantity_SP.clear();
        Txfiled_Discription_SP.clear();
        Txfiled_minimumQuantity_SP.clear();
        Txfiled_Name_SP.setDisable(true);
        Txfiled_Price_SP.setDisable(true);
        Txfiled_Quantity_SP.setDisable(true);
        Txfiled_Discription_SP.setDisable(true);
        Txfiled_minimumQuantity_SP.setDisable(true);
        Btn_Delete_SP.setDisable(true);
        Btn_Save_SP.setDisable(true);
        Btn_Cancel_SP.setDisable(true);
        Btn_Search_SP.setDisable(false);

    }

    @FXML
    private void M_Btn_Delete_SP(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("");
            
             Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setTitle("");
            alert3.setHeaderText("");
            if (count_Language == 0) {
                alert.setContentText("This spare part will be deleted ");
            } else {
                alert.setContentText("  سوف يتم حذف قطعة الغيار ");

            }

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                String sql1 = "DELETE FROM  `spare_parts`  WHERE SP_NBER= " + Txfiled_SPNum_SP.getText();
                java.sql.Statement statement1 = connection.createStatement();
                ClearSp();

                try {

                    statement1.executeUpdate(sql1);
                    {
                        if (count_Language == 0) {

                            alert3.setHeaderText(null);
                            alert3.setContentText("Deleted Successfully");
                            alert3.showAndWait();
                            return;

                        } else if (count_Language == 1) {

                            alert3.setHeaderText(null);
                            alert3.setContentText("تم الحذف بنجاح");
                            alert3.showAndWait();
                            return;
                        }
                    }
                } catch (SQLException e) {
                    if (count_Language == 0) {

                        alert3.setHeaderText(null);
                        alert3.setContentText("Can not delete this spare part");
                        alert3.showAndWait();
                        return;
                    } else if (count_Language == 1) {

                        alert3.setHeaderText(null);
                        alert3.setContentText("لا يمكن حذف  قطعة الغيار");
                        alert3.showAndWait();
                        return;
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    @FXML
    private void M_Btn_Save_SP(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);

        if (Txfiled_Name_SP.getText().isEmpty()) {
            if (count_Language == 0) {
                alert.setContentText("Please enter the name of spare part");
            } else {
                alert.setContentText("الرجاء إدخال اسم قطعة الغيار");

            }
            alert.showAndWait();
            return;

        }

        if (Txfiled_Price_SP.getText().isEmpty()) {
            if (count_Language == 0) {
                alert.setContentText("Please enter the price of spare part");
            } else {
                alert.setContentText("الرجاء إدخال سعر قطعة الغيار");

            }
            alert.showAndWait();
            return;

        }
        if (Txfiled_Quantity_SP.getText().isEmpty()) {
            if (count_Language == 0) {
                alert.setContentText("Please enter the quantity of spare part");
            } else {
                alert.setContentText("الرجاء إدخال كمية قطعة الغيار");

            }
            alert.showAndWait();
            return;

        }
        if (Txfiled_Discription_SP.getText().isEmpty()) {
            if (count_Language == 0) {
                alert.setContentText("Please enter the description of spare part");
            } else {
                alert.setContentText("الرجاء إدخال وصف قطعة الغيار");

            }
            alert.showAndWait();
            return;

        }
        if (Txfiled_minimumQuantity_SP.getText().isEmpty()) {
            if (count_Language == 0) {
                alert.setContentText("Please enter the minimum quantity of spare part");
            } else {
                alert.setContentText("الرجاء إدخال الحد الادنى  لقطعة الغيار");

            }
            alert.showAndWait();
            return;

        }

        if (count == 1) {
            try {
                String sqll = "INSERT INTO spare_parts (SP_NBER, SP_NAME, PRICE,SP_QUANTITY ,DESCRIPTION, MINIMUM_QUANTITY_IN_STOCK) VALUES (" + SP_number + "," + "'" + Txfiled_Name_SP.getText() + "'" + "," + "'" + Txfiled_Price_SP.getText()
                        + "'" + "," + "'" + Txfiled_Quantity_SP.getText() + "'" + "," + "'" + Txfiled_Discription_SP.getText() + "'" + "," + "'" + Txfiled_minimumQuantity_SP.getText() + "')";
                java.sql.Statement statement1 = connection.createStatement();

                statement1.executeUpdate(sqll);
                if (count_Language == 0) {

                    alert2.setContentText(" A new Spare part has been created");
                } else {
                    alert2.setContentText("تم انشاء قطعة غيار جديدة");

                }
                Btn_Delete_SP.setDisable(false);

                alert2.showAndWait();
                ClearSp();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        } else if (count == 2) {
            try {
                String sql1 = "UPDATE  `spare_parts` SET  SP_NAME='" + Txfiled_Name_SP.getText() + "',PRICE='" + Txfiled_Price_SP.getText() + "',SP_QUANTITY='" + Txfiled_Quantity_SP.getText() + "',DESCRIPTION='" + Txfiled_Discription_SP.getText() + "',MINIMUM_QUANTITY_IN_STOCK='" + Txfiled_minimumQuantity_SP.getText()
                        + " 'WHERE SP_NBER=' " + Txfiled_SPNum_SP.getText() + "'";

                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sql1);
                if (count_Language == 0) {

                    alert2.setContentText(" Changes saved successfully");
                } else {
                    alert2.setContentText("تم حفظ التعديلات بنجاح");

                }

                alert2.showAndWait();
                ClearSp();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        RefreshMOTables();

    }

    @FXML
    private void M_Btn_Save_Supplier(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        if (!PhoneNvalid(Txfiled_MNum_Supplier.getText())) {

            if (count_Language == 0) {
                alert.setContentText("Please enter valid mobile number");
            } else {
                alert.setContentText("الرجاء إدخال رقم هاتف صحيح");

            }
            alert.showAndWait();
            return;

        }
        if (!EmailValid(Txfiled_Email_Supplier.getText())) {

            if (count_Language == 0) {
                alert.setContentText("Please enter valid email address");
            } else {
                alert.setContentText("الرجاء إدخال عنوان بريد إلكتروني صحيح");

            }
            alert.showAndWait();
            return;

        }
        if (Txfiled_Name_Supplier.getText().isEmpty()) {

            if (count_Language == 0) {
                alert.setContentText("Please enter supplier name");
            } else {
                alert.setContentText("الرجاء إدخال اسم المزود");

            }
            alert.showAndWait();
            return;

        }
        if (Txfiled_Address_Supplier.getText().isEmpty()) {

            if (count_Language == 0) {
                alert.setContentText("Please enter supplier address");
            } else {
                alert.setContentText("الرجاء إدخال عنوان المزود");

            }
            alert.showAndWait();
            return;

        } else if (EmailValid(Txfiled_Email_Supplier.getText()) && (PhoneNvalid(Txfiled_MNum_Supplier.getText()))) {

            if (count == 1) {
                try {
                    String sqll = "INSERT INTO supplier (SUPPLIER_NBER, SUP_MOBILE_NBER, SUP_EMAIL,SUP_NAME ,SUP_ADDRESS) VALUES (" + SUP_number + "," + "'" + Txfiled_MNum_Supplier.getText() + "'" + "," + "'" + Txfiled_Email_Supplier.getText()
                            + "'" + "," + "'" + Txfiled_Name_Supplier.getText() + "'" + "," + "'" + Txfiled_Address_Supplier.getText() + "')";
                    java.sql.Statement statement1 = connection.createStatement();

                    statement1.executeUpdate(sqll);
                    if (count_Language == 0) {

                        alert2.setContentText(" A new Supplier has been created");
                    } else {
                        alert2.setContentText("تم انشاء مزود جديد");
                    }

                    alert2.showAndWait();
                    clearSUP();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

            } else if (count == 2) {

                try {
                    String sql1 = "UPDATE  `supplier` SET  SUP_MOBILE_NBER='" + Txfiled_MNum_Supplier.getText() + "',SUP_EMAIL='" + Txfiled_Email_Supplier.getText() + "',SUP_NAME='" + Txfiled_Name_Supplier.getText() + "',SUP_ADDRESS='" + Txfiled_Address_Supplier.getText()
                            + "' WHERE SUPPLIER_NBER= '" + Txfiled_Num_Supplier.getText() + "'";

                    java.sql.Statement statement1 = connection.createStatement();
                    statement1.executeUpdate(sql1);
                    if (count_Language == 0) {

                        alert2.setContentText(" Changes saved successfully");
                    } else {
                        alert2.setContentText("تم حفظ التعديلات بنجاح");

                    }

                    alert2.showAndWait();
                    clearSUP();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

        } else {

            if (count_Language == 0) {

                alert2.setContentText(" invalid Mobile Number or Email");
            } else {
                alert2.setContentText("رقم الهاتف او البريد الالكتروني خاطئ");
            }
            alert2.showAndWait();

        }

    }

    @FXML
    private void M_Btn_Search_Supplier(ActionEvent event) {
        alert2.setTitle(null);
        alert2.setHeaderText(null);
        if (!Mumbervalid(Txfiled_Num_Supplier.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            if (count_Language == 0) {
                alert.setContentText("Please enter supplier number");
            } else {
                alert.setContentText("الرجاء ادخال رقم المزود ");

            }
            alert.showAndWait();
            return;

        }

        Txfiled_Num_Supplier.setDisable(true);
        Btn_Save_Supplier.setDisable(false);
        Btn_Cancel_Supplier.setDisable(false);
        Btn_Search_Supplier.setDisable(true);

        Txfiled_MNum_Supplier.setDisable(false);
        Txfiled_Email_Supplier.setDisable(false);
        Txfiled_Name_Supplier.setDisable(false);
        Txfiled_Address_Supplier.setDisable(false);

        try {

            Connection connection = connectionClass.getConnection();
            Statement st = connection.createStatement();
            st.executeQuery("SELECT * FROM `supplier`  WHERE SUPPLIER_NBER = " + Txfiled_Num_Supplier.getText());
            ResultSet rs = st.getResultSet();
            if (rs.first()) {

                if (rs.getString("SUPPLIER_NBER").equals(Txfiled_Num_Supplier.getText())) {

                    count = 2;
                    String supnumber = "0" + rs.getString("SUP_MOBILE_NBER");
                    Txfiled_MNum_Supplier.setText(supnumber);

                    Txfiled_Email_Supplier.setText(rs.getString("SUP_EMAIL"));
                    Txfiled_Name_Supplier.setText(rs.getString("SUP_NAME"));
                    Txfiled_Address_Supplier.setText(rs.getString("SUP_ADDRESS"));

                    Btn_Delete_Supplier.setDisable(false);

                }
            } else {
                Statement st2 = connection.createStatement();
                st2.executeQuery("SELECT * FROM supplier ORDER BY SUPPLIER_NBER DESC LIMIT 1");
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {

                    if (count_Language == 0) {
                        alert2.setContentText("New supplier will be created");
                    } else {
                        alert2.setContentText("سوف يتم انشاء مزود جديد");

                    }
                    alert2.showAndWait();

                    count = 1;
                    SUP_number = Integer.parseInt(rs2.getString("SUPPLIER_NBER"));
                    SUP_number++;
                    Txfiled_Num_Supplier.setText(String.valueOf(SUP_number));
                    Btn_Delete_Supplier.setDisable(true);

                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    @FXML
    private void M_Btn_Delete_Supplier(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("");
             Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setTitle("");
            alert3.setHeaderText("");
            if (count_Language == 0) {
                alert.setContentText("This supplier will be deleted ");
            } else {
                alert.setContentText("  سوف يتم حذف هذا المزود ");

            }

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                String sql1 = "DELETE FROM  `supplier`  WHERE SUPPLIER_NBER= " + Txfiled_Num_Supplier.getText();
                java.sql.Statement statement1 = connection.createStatement();
                clearSUP();
                try {
                    statement1.executeUpdate(sql1);

                    {
                        if (count_Language == 0) {

                            alert3.setHeaderText(null);
                            alert3.setContentText("Deleted Successfully");
                            alert3.showAndWait();
                            return;
                        } else if (count_Language == 1) {

                            alert3.setHeaderText(null);
                            alert3.setContentText("تم الحذف بنجاح");
                            alert3.showAndWait();
                            return;

                        }

                    }
                } catch (SQLException e) {
                    if (count_Language == 0) {

                        alert3.setHeaderText(null);
                        alert3.setContentText("Can not delete this supplier");
                        alert3.showAndWait();
                        return;
                    } else if (count_Language == 1) {

                        alert3.setHeaderText(null);
                        alert3.setContentText("لا يمكن حذف هذا المزود");
                        alert3.showAndWait();
                        return;

                    }

                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void M_Btn_Cancel_Supplier(ActionEvent event) {

        clearSUP();
    }

    public void clearSUP() {
        Txfiled_Num_Supplier.setDisable(false);
        Txfiled_Num_Supplier.clear();
        Txfiled_MNum_Supplier.clear();
        Txfiled_Email_Supplier.clear();
        Txfiled_Name_Supplier.clear();
        Txfiled_Address_Supplier.clear();
        Btn_Delete_Supplier.setDisable(true);
        Btn_Cancel_Supplier.setDisable(true);
        Btn_Save_Supplier.setDisable(true);
        Btn_Search_Supplier.setDisable(false);
        Txfiled_Email_Supplier.setDisable(true);
        Txfiled_Name_Supplier.setDisable(true);
        Txfiled_Address_Supplier.setDisable(true);
        Txfiled_MNum_Supplier.setDisable(true);

    }

    private void M_MousClicked_listv(ActionEvent Event) {
        List_of_reports.setItems(items);

        List_of_reports.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void M_btn_print_reports(ActionEvent event) throws SQLException, JRException {
        if (count_Language == 1) {
            ObservableList<String> names;
            names = List_of_reports.getSelectionModel().getSelectedItems();

            String SelectedItem = (String) List_of_reports.getSelectionModel().getSelectedItem();

            LocalDate Date = LocalDate.now();
            if (SelectedItem.equalsIgnoreCase("- عمليات الصيانة السابقة")) {

                if (Rad_Last_month.isSelected()) {

                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','دفعت','disapproved','مرفوضة') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(1) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localdate = LocalDate.now();
                        LocalDate minusmonths = Date.minusMonths(1);
                        print.PreviousMO(localdate, minusmonths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("لايمكن طباعة هذا التقرير");
                        alert.showAndWait();
                        return;
                    }

                } else if (Rad_LastThree_months.isSelected()) {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','دفعت','disapproved','مرفوضة') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(3) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localdate = LocalDate.now();
                        LocalDate minusmonths = Date.minusMonths(3);
                        print.PreviousMO(localdate, minusmonths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("لايمكن طباعة هذا التقرير");
                        alert.showAndWait();
                        return;
                    }
                } else if (Rad_This_Year.isSelected()) {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','دفعت','disapproved','مرفوضة') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localdate = LocalDate.now();
                        LocalDate minusmonths = Date.minusMonths(12);
                        print.PreviousMO(localdate, minusmonths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("لايمكن طباعة هذا التقرير");
                        alert.showAndWait();
                        return;
                    }
                } else if (Rad_Last_Year.isSelected()) {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','دفعت','disapproved','مرفوضة') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localdate = LocalDate.now();
                        LocalDate minusmonths = Date.minusMonths(12);
                        print.PreviousMO(localdate, minusmonths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("لايمكن طباعة هذا التقرير");
                        alert.showAndWait();
                        return;
                    }

                } else if (Rad_Choose_Period.isSelected()) {
                    Statement st2 = connection.createStatement();

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','دفعت','disapproved','مرفوضة') AND m.STARTING_DATE >= '" + Date_StartFrom.getValue() + "' AND m.STARTING_DATE <= '" + Date_Unill.getValue() + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();
                    if (rs2.first()) {
                        printreport print = new printreport();
                        LocalDate startingdate = Date_StartFrom.getValue();
                        LocalDate untildate = Date_Unill.getValue();
                        print.PreviousMOPeriod(startingdate, untildate);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("لايمكن طباعة هذا التقرير");
                        alert.showAndWait();
                        return;
                    }
                }

            } else if (SelectedItem.equalsIgnoreCase("- عمليات الصيانة الحالية")) {
                if (Rad_Last_month.isSelected()) {
                    Statement st2 = connection.createStatement();

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance','تم الموافقة','تحت الصيانة') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(1) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();
                    if (rs2.first()) {
                        printreport print = new printreport();
                        LocalDate localdate = LocalDate.now();
                        LocalDate minusmonths = Date.minusMonths(1);
                        print.CurrentMO(localdate, minusmonths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("لايمكن طباعة هذا التقرير");
                        alert.showAndWait();
                        return;

                    }

                } else if (Rad_LastThree_months.isSelected()) {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance','تم الموافقة','تحت الصيانة') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(3) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();
                    if (rs2.first()) {
                        printreport print = new printreport();
                        LocalDate localdate = LocalDate.now();
                        LocalDate minusmonths = Date.minusMonths(3);
                        print.CurrentMO(localdate, minusmonths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("لايمكن طباعة هذا التقرير");
                        alert.showAndWait();
                        return;

                    }

                } else if (Rad_This_Year.isSelected()) {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance','تم الموافقة','تحت الصيانة') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();
                    if (rs2.first()) {
                        printreport print = new printreport();
                        LocalDate localdate = LocalDate.now();
                        LocalDate minusmonths = Date.minusMonths(12);
                        print.CurrentMO(localdate, minusmonths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("لايمكن طباعة هذا التقرير");
                        alert.showAndWait();
                        return;

                    }

                } else if (Rad_Last_Year.isSelected()) {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance','تم الموافقة','تحت الصيانة') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();
                    if (rs2.first()) {
                        printreport print = new printreport();
                        LocalDate localdate = LocalDate.now();
                        LocalDate minusmonths = Date.minusMonths(12);
                        print.CurrentMO(localdate, minusmonths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("لايمكن طباعة هذا التقرير");
                        alert.showAndWait();
                        return;

                    }
                } else if (Rad_Choose_Period.isSelected()) {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance','تم الموافقة','تحت الصيانة') AND m.STARTING_DATE >= '" + Date_StartFrom.getValue() + "' AND m.STARTING_DATE <= '" + Date_Unill.getValue() + "'";

                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();
                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate startingdate = Date_StartFrom.getValue();
                        LocalDate untildate = Date_Unill.getValue();
                        print.CurrentMOPeriod(startingdate, untildate);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("لايمكن طباعة هذا التقرير");
                        alert.showAndWait();
                        return;

                    }
                }

            } else if (SelectedItem.equalsIgnoreCase("- عمليات الصيانة المنتهية")) {
                if (Rad_Last_month.isSelected()) {
                    Statement st2 = connection.createStatement();

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('تم الاصلاح','repaired') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(1) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();
                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localdate = LocalDate.now();
                        LocalDate minusmonths = Date.minusMonths(1);
                        print.FinshedMO(localdate, minusmonths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("لايمكن طباعة هذا التقرير");
                        alert.showAndWait();
                        return;

                    }
                } else if (Rad_LastThree_months.isSelected()) {
                    Statement st2 = connection.createStatement();

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('تم الاصلاح','repaired') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(3) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();
                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localdate = LocalDate.now();
                        LocalDate minusmonths = Date.minusMonths(3);
                        print.FinshedMO(localdate, minusmonths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("لايمكن طباعة هذا التقرير");
                        alert.showAndWait();
                        return;

                    }

                } else if (Rad_This_Year.isSelected()) {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('تم الاصلاح','repaired') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();
                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localdate = LocalDate.now();
                        LocalDate minusmonths = Date.minusMonths(12);
                        print.FinshedMO(localdate, minusmonths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("لايمكن طباعة هذا التقرير");
                        alert.showAndWait();
                        return;

                    }

                } else if (Rad_Last_Year.isSelected()) {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('تم الاصلاح','repaired') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();
                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localdate = LocalDate.now();
                        LocalDate minusmonths = Date.minusMonths(12);
                        print.FinshedMO(localdate, minusmonths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("لايمكن طباعة هذا التقرير");
                        alert.showAndWait();
                        return;

                    }

                } else if (Rad_Choose_Period.isSelected()) {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('تم الاصلاح','repaired') AND m.STARTING_DATE >= '" + Date_StartFrom.getValue() + "' AND m.STARTING_DATE <= '" + Date_Unill.getValue() + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();
                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate startingdate = Date_StartFrom.getValue();
                        LocalDate untildate = Date_Unill.getValue();
                        print.FinshedMOPeriod(startingdate, untildate);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("لايمكن طباعة هذا التقرير");
                        alert.showAndWait();

                    }
                }

            } else if (SelectedItem.equalsIgnoreCase("- تقدير مالي عن عملية صيانة")) {
                if (!Mumbervalid(Txfiled_CUS_MNBER.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);

                    alert.setContentText("الرجاء ادخال رقم عملية الصيانة");

                    alert.showAndWait();
                    return;

                }
                Statement st2 = connection.createStatement();
                String q = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected','disapproved','cannot be done','repaired','تم الإنشاء', 'تم الموافقة', 'تحت الصيانة', 'تم الكشف عن عيوب أخرى','مرفوضة','لا يمكن القيام بعملية الصيانة','تم الاصلاح') AND m.MO_NBER = '" + Txfiled_MO_Nber.getText() + "'";
                st2.executeQuery(q);
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {

                    printreport print = new printreport();
                    String MOnumber = Txfiled_MO_Nber.getText();

                    print.FinancialassessReportAR(MOnumber);
                } else {
                    printreport print = new printreport();
                    String MOnumber = Txfiled_MO_Nber.getText();

                    print.FinancialassessReportARWSP(MOnumber);

                }
            } else if (SelectedItem.equalsIgnoreCase("- قائمة بالعملاء")) {
                String query = "SELECT * FROM `customer`";
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.CustomersList();

            } else if (SelectedItem.equalsIgnoreCase("- قائمة عمليات الصيانة لعميل")) {
                if (!Mumbervalid(Txfiled_CUS_MNBER.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);

                    alert.setContentText("الرجاء ادخل رقم هاتف العميل ");

                    alert.showAndWait();
                    return;

                }
                Statement st2 = connection.createStatement();
                String q = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE r.CUS_MOBILE_NBER = '" + Txfiled_CUS_MNBER.getText() + "'";
                st2.executeQuery(q);
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE r.CUS_MOBILE_NBER = '" + Txfiled_CUS_MNBER.getText() + "'";
                    java.sql.Statement statement1 = connection.createStatement();
                    printreport print = new printreport();

                    String CusMobileNumber = Txfiled_CUS_MNBER.getText();
                    print.ListofcustomersMOs(CusMobileNumber);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("العميل ليست لديه عملية صيانة");
                    alert.showAndWait();
                    return;
                }

            } else if (SelectedItem.equalsIgnoreCase("- قائمة بالموظفين")) {
                String query = "SELECT * FROM `employee`";
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.EmployeesList();

            } else if (SelectedItem.equalsIgnoreCase("- قائمة عمليات الصيانة لموظف")) {
                if (Selct_Name_Employee.getSelectionModel().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);

                    alert.setContentText("الرجاء اختيار فني عملية الصيانة ");

                    alert.showAndWait();
                    return;

                }
                Statement st2 = connection.createStatement();
                String q = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where e.EMP_NAME = '" + Selct_Name_Employee.getValue() + "'";
                st2.executeQuery(q);
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where e.EMP_NAME = '" + Selct_Name_Employee.getValue() + "'";
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    String EmployeeName = Selct_Name_Employee.getValue();
                    print.ListofaemployeesMOs(EmployeeName);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("الموظف ليست لديه عملية صيانة");
                    alert.showAndWait();
                    return;

                }

            } else if (SelectedItem.equalsIgnoreCase("- قائمة بالمزودين")) {
                String query = "SELECT * FROM `supplier`";
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.SuppliersList();

            } else if (SelectedItem.equalsIgnoreCase("- قطع على وشك النفاذ")) {
                String query = "SELECT * FROM spare_parts WHERE `SP_QUANTITY` < `MINIMUM_QUANTITY_IN_STOCK` AND `SP_QUANTITY` <>0";
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.SPstobeOUTOS();

            } else if (SelectedItem.equalsIgnoreCase("- قطع الغيار التي نفذت كميتها")) {
                String query = "SELECT * FROM spare_parts WHERE `SP_QUANTITY` = 0";
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.SPsOUTOS();

            }

        } else if (count_Language == 0) {
            ObservableList<String> names;
            names = List_of_reports.getSelectionModel().getSelectedItems();

            String SelectedItem = (String) List_of_reports.getSelectionModel().getSelectedItem();

            LocalDate Date = LocalDate.now();
            if (SelectedItem.equalsIgnoreCase("- previous  maintenance operations")) {
                if (Rad_Last_month.isSelected()) {

                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','دفعت','disapproved','مرفوضة')  AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(1) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localedate = LocalDate.now();
                        LocalDate minusmounths = Date.minusMonths(1);
                        print.PreviousMOEN(localedate, minusmounths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Can not print this report");
                        alert.showAndWait();
                        return;
                    }

                } else if (Rad_LastThree_months.isSelected()) {

                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','دفعت','disapproved','مرفوضة')  AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(3) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localedate = LocalDate.now();
                        LocalDate minusmounths = Date.minusMonths(3);
                        print.PreviousMOEN(localedate, minusmounths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Can not print this report");
                        alert.showAndWait();
                        return;
                    }
                } else if (Rad_This_Year.isSelected()) {

                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','دفعت','disapproved','مرفوضة')  AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localedate = LocalDate.now();
                        LocalDate minusmounths = Date.minusMonths(12);
                        print.PreviousMOEN(localedate, minusmounths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Can not print this report");
                        alert.showAndWait();
                        return;
                    }
                } else if (Rad_Last_Year.isSelected()) {

                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','دفعت','disapproved','مرفوضة')  AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localedate = LocalDate.now();
                        LocalDate minusmounths = Date.minusMonths(12);
                        print.PreviousMOEN(localedate, minusmounths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Can not print this report");
                        alert.showAndWait();
                        return;
                    }

                } else if (Rad_Choose_Period.isSelected()) {
                    Statement st2 = connection.createStatement();

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','دفعت','disapproved','مرفوضة') AND m.STARTING_DATE >= '" + Date_StartFrom.getValue() + "' AND m.STARTING_DATE <= '" + Date_Unill.getValue() + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate startingdate = Date_StartFrom.getValue();
                        LocalDate untildate = Date_Unill.getValue();
                        print.PreviousMOPeriodEN(startingdate, untildate);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Can not print this report  ");
                        alert.showAndWait();
                        return;

                    }
                }

            } else if (SelectedItem.equalsIgnoreCase("- current  maintenance operations")) {
                if (Rad_Last_month.isSelected()) {
                    Statement st2 = connection.createStatement();

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance','تم الموافقة','تحت الصيانة') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(1) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localedate = LocalDate.now();
                        LocalDate minusmounths = Date.minusMonths(1);
                        print.CurrentMOEN(localedate, minusmounths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Can not print this report  ");
                        alert.showAndWait();
                        return;

                    }

                } else if (Rad_LastThree_months.isSelected()) {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance','تم الموافقة','تحت الصيانة') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(3) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localedate = LocalDate.now();
                        LocalDate minusmounths = Date.minusMonths(3);
                        print.CurrentMOEN(localedate, minusmounths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Can not print this report  ");
                        alert.showAndWait();
                        return;

                    }

                } else if (Rad_This_Year.isSelected()) {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance','تم الموافقة','تحت الصيانة') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localedate = LocalDate.now();
                        LocalDate minusmounths = Date.minusMonths(12);
                        print.CurrentMOEN(localedate, minusmounths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Can not print this report  ");
                        alert.showAndWait();
                        return;

                    }

                } else if (Rad_Last_Year.isSelected()) {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance','تم الموافقة','تحت الصيانة') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localedate = LocalDate.now();
                        LocalDate minusmounths = Date.minusMonths(12);
                        print.CurrentMOEN(localedate, minusmounths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Can not print this report  ");
                        alert.showAndWait();
                        return;

                    }

                } else if (Rad_Choose_Period.isSelected()) {
                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance','تم الموافقة','تحت الصيانة') AND m.STARTING_DATE >= '" + Date_StartFrom.getValue() + "' AND m.STARTING_DATE <= '" + Date_Unill.getValue() + "'";

                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate startingdate = Date_StartFrom.getValue();
                        LocalDate untildate = Date_Unill.getValue();
                        print.CurrentMOPeriodEN(startingdate, untildate);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Can not print this report  ");
                        alert.showAndWait();
                        return;

                    }
                }

            } else if (SelectedItem.equalsIgnoreCase("- Finished maintenance operations")) {

                if (Rad_Last_month.isSelected()) {
                    Statement st2 = connection.createStatement();

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired','تم الاصلاح') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(1) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localedate = LocalDate.now();
                        LocalDate minusmounths = Date.minusMonths(1);
                        print.FinshedMOEN(localedate, minusmounths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Can not print this report  ");
                        alert.showAndWait();
                        return;

                    }

                } else if (Rad_LastThree_months.isSelected()) {
                    Statement st2 = connection.createStatement();

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired','تم الاصلاح') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(3) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localedate = LocalDate.now();
                        LocalDate minusmounths = Date.minusMonths(3);
                        print.FinshedMOEN(localedate, minusmounths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Can not print this report  ");
                        alert.showAndWait();
                        return;

                    }
                } else if (Rad_This_Year.isSelected()) {

                    Statement st2 = connection.createStatement();
                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired','تم الاصلاح') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localedate = LocalDate.now();
                        LocalDate minusmounths = Date.minusMonths(12);
                        print.FinshedMOEN(localedate, minusmounths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Can not print this report  ");
                        alert.showAndWait();
                        return;

                    }

                } else if (Rad_Last_Year.isSelected()) {
                    Statement st2 = connection.createStatement();

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired','تم الاصلاح') AND m.STARTING_DATE <= '" + LocalDate.now() + "' AND m.STARTING_DATE >= '" + Date.minusMonths(12) + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate localedate = LocalDate.now();
                        LocalDate minusmounths = Date.minusMonths(12);
                        print.FinshedMOEN(localedate, minusmounths);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Can not print this report  ");
                        alert.showAndWait();
                        return;

                    }

                } else if (Rad_Choose_Period.isSelected()) {
                    Statement st2 = connection.createStatement();

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired','تم الاصلاح') AND m.STARTING_DATE >= '" + Date_StartFrom.getValue() + "' AND m.STARTING_DATE <= '" + Date_Unill.getValue() + "'";
                    st2.executeQuery(query);
                    ResultSet rs2 = st2.getResultSet();

                    if (rs2.first()) {

                        printreport print = new printreport();
                        LocalDate startingdate = Date_StartFrom.getValue();
                        LocalDate untildate = Date_Unill.getValue();
                        print.FinshedMOPeriodEN(startingdate, startingdate);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Can not print this report  ");
                        alert.showAndWait();
                        return;

                    }
                }

            } else if (SelectedItem.equalsIgnoreCase("- financial estimate of maintenance")) {
                if (!Mumbervalid(Txfiled_CUS_MNBER.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);

                    alert.setContentText("Please enter MO number");

                    alert.showAndWait();
                    return;

                }
                Statement st2 = connection.createStatement();
                String q = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected','disapproved','cannot be done','repaired','تم الإنشاء', 'تم الموافقة', 'تحت الصيانة', 'تم الكشف عن عيوب أخرى','مرفوضة','لا يمكن القيام بعملية الصيانة','تم الاصلاح') AND m.MO_NBER = '" + Txfiled_MO_Nber.getText() + "'";
                st2.executeQuery(q);
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {

                    printreport print = new printreport();
                    String MOnumber = Txfiled_MO_Nber.getText();

                    print.FinancialassessReportENG(MOnumber);
                } else {
                    printreport print = new printreport();
                    String MOnumber = Txfiled_MO_Nber.getText();

                    print.FinancialassessReportENGWSP(MOnumber);

                }
            } else if (SelectedItem.equalsIgnoreCase("- list of customers")) {
                String query = "SELECT * FROM `customer`";
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.CustomersListEN();

            } else if (SelectedItem.equalsIgnoreCase("- list of customer maintenance operations")) {

                if (!Mumbervalid(Txfiled_CUS_MNBER.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);

                    alert.setContentText("Please enter customer mobile number");

                    alert.showAndWait();
                    return;

                }
                Statement st2 = connection.createStatement();
                String q = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE r.CUS_MOBILE_NBER = '" + Txfiled_CUS_MNBER.getText() + "'";
                st2.executeQuery(q);
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE r.CUS_MOBILE_NBER = '" + Txfiled_CUS_MNBER.getText() + "'";
                    java.sql.Statement statement1 = connection.createStatement();
                    printreport print = new printreport();

                    String CusMobileNumber = Txfiled_CUS_MNBER.getText();
                    print.ListofcustomersMOsEN(CusMobileNumber);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("This customer does not have maintenance operation");
                    alert.showAndWait();
                    return;
                }

            } else if (SelectedItem.equalsIgnoreCase("- list of employees")) {
                String query = "SELECT * FROM `employee`";
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.EmployeesListEN();

            } else if (SelectedItem.equalsIgnoreCase("- List of maintenance operations for an employee")) {
                if (Selct_Name_Employee.getSelectionModel().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);

                    alert.setContentText("Please select the technician of MO ");

                    alert.showAndWait();
                    return;

                }

                Statement st2 = connection.createStatement();
                String q = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where e.EMP_NAME = '" + Selct_Name_Employee.getValue() + "'";
                st2.executeQuery(q);
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {

                    String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where e.EMP_NAME = '" + Selct_Name_Employee.getValue() + "'";
                    java.sql.Statement statement1 = connection.createStatement();

                    printreport print = new printreport();
                    String EmployeeName = Selct_Name_Employee.getValue();
                    print.ListofaemployeesMOsEN(EmployeeName);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("This employee does not have maintenance operation");
                    alert.showAndWait();
                    return;

                }

            } else if (SelectedItem.equalsIgnoreCase("- list of Suppliers")) {
                String query = "SELECT * FROM `supplier`";
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.SuppliersListEN();

            } else if (SelectedItem.equalsIgnoreCase("- Spare Parts about to be out of stock")) {
                String query = "SELECT * FROM spare_parts WHERE `SP_QUANTITY` < `MINIMUM_QUANTITY_IN_STOCK` AND `SP_QUANTITY` <>0";
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.SPstobeOUTOSEN();

            } else if (SelectedItem.equalsIgnoreCase("- Spare parts out of stock")) {
                String query = "SELECT * FROM spare_parts WHERE `SP_QUANTITY` = 0";
                java.sql.Statement statement1 = connection.createStatement();

                printreport print = new printreport();
                print.SPsOUTOSEN();

            }

        }
    }

    public void loadEmp() {
        String query = "SELECT EMP_NAME FROM employee";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {

                ListOfselectName.add(rs.getString("EMP_NAME"));

            }
            rs.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    @FXML
    public void Mangment_MO_tab_selected(Event event) {

        if (count_Language == 0) {
            MainLable.setText("Maintenance Operations Management");

        } else if (count_Language == 1) {

            MainLable.setText("ادارة عمليات الصيانة");

        }

    }

    @FXML
    public void Mangment_Customer_tab_selected(Event event) {
        if (count_Language == 0) {
            MainLable.setText("Customers Management");

        } else if (count_Language == 1) {

            MainLable.setText("ادارة العملاء");

        }

    }

    @FXML
    public void Mangment_supliers_tab_selected(Event event) {
        if (count_Language == 0) {
            MainLable.setText("Suppliers Management");

        } else if (count_Language == 1) {

            MainLable.setText("ادارة المزودين");

        }
    }

    @FXML
    public void Mangment_Staff_tab_selected(Event event) {
        if (count_Language == 0) {
            MainLable.setText("Employees Management");

        } else if (count_Language == 1) {

            MainLable.setText("ادارة الموظفين");

        }
    }

    @FXML
    public void RequstSpearPart_tab_selected(Event event) {
        if (count_Language == 0) {
            MainLable.setText("Request Spare Parts");

        } else if (count_Language == 1) {

            MainLable.setText("طلب قطع غيار");

        }
    }

    @FXML
    public void Mangment_SpearParts_tab_selected(Event event) {
        if (count_Language == 0) {
            MainLable.setText("Spare Parts Management");

        } else if (count_Language == 1) {

            MainLable.setText("ادارة قطع الغيار");

        }
    }

    @FXML
    public void Mangment_Reports_tab_selected(Event event) {
        if (count_Language == 0) {
            MainLable.setText("Reports Management");

        } else if (count_Language == 1) {

            MainLable.setText("ادارة التقارير");

        }
    }

    @FXML
    private void Main_Tab(Event event) {
        MainLable.setText(" ");
    }

    @FXML
    private void Mangment_Tools_tab_selected(Event event) {
        if (count_Language == 0) {
            MainLable.setText("Tools");

        } else if (count_Language == 1) {

            MainLable.setText("الأدوات");

        }

    }
    ObservableList<AddSP> SPSelected2, AllSP2;

    @FXML
    private void M_Btn_RemoveSP_ReqSP(ActionEvent event) {
        alert2.setTitle(null);
        alert2.setHeaderText(null);
        AllSP2 = Table_SelectedSP_ReqSP.getItems();
        SPSelected2 = Table_SelectedSP_ReqSP.getSelectionModel().getSelectedItems();

        for (int i = 0; i < SPSelected2.size(); i++) {
            try {

                String sqlDeletSP = "DELETE FROM `attach` " + " WHERE SP_NBER= " + SPSelected2.get(i).getSP_Number() + " AND REQUEST_NBER=" + Txfiled_REQnum_ReqSP.getText();

                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sqlDeletSP);
                if (count_Language == 0) {
                    alert2.setContentText("The " + SPSelected2.get(0).getSP_Name() + " has been removed from the request");
                } else {
                    alert2.setContentText("تم ازالة  " + SPSelected2.get(0).getSP_Name() + "من الطلب");

                }

                alert2.showAndWait();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

        loadSpSelected();
        loadSpecifecSP();

    }

    @FXML
    private void M_Btn_AddSP_ReqSP(ActionEvent event) {
        alert2.setTitle(null);
        alert2.setHeaderText(null);
        ObservableList<AddSP> SPSelected, AllSP;
        AllSP = Table_AddSP_ReqSP.getItems();
        SPSelected = Table_AddSP_ReqSP.getSelectionModel().getSelectedItems();

        for (int i = 0; i < SPSelected.size(); i++) {
            try {

                ListOFSelectedSP.add(new AddSP(SPSelected.get(i).getSP_Number(), SPSelected.get(i).getSP_Name(), 1));

                String sql1 = "INSERT INTO `attach` VALUES(" + SPSelected.get(0).getSP_Number() + ",'" + Txfiled_REQnum_ReqSP.getText() + "','"
                        + 1 + "')";
                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sql1);

                if (count_Language == 0) {
                    alert2.setContentText("A " + SPSelected.get(0).getSP_Name() + " has been added to the request");
                } else {
                    alert2.setContentText("تم اضافة  " + SPSelected.get(0).getSP_Name() + "لهذا الطلب");

                }
                alert2.showAndWait();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

        loadSpSelected();
        loadSpecifecSP();

    }

    @FXML
    private void M_Btn_Print_ReqSP(ActionEvent event) {

        if (count_Language == 0) {
            try {
                String query = "SELECT * FROM `requested_spare_parts` s JOIN `attach` r ON s.REQUEST_NBER = r.REQUEST_NBER JOIN spare_parts p ON r.SP_NBER = p.SP_NBER WHERE s.REQUEST_NBER ='" + Txfiled_REQnum_ReqSP.getText() + "'";
                java.sql.Statement statement1 = connection.createStatement();
                printreport print = new printreport();
                String ss = Txfiled_REQnum_ReqSP.getText();

                print.RequestSPEN(ss);
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null, ex);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else if (count_Language == 1) {
            try {
                String query = "SELECT * FROM `requested_spare_parts` s JOIN `attach` r ON s.REQUEST_NBER = r.REQUEST_NBER JOIN spare_parts p ON r.SP_NBER = p.SP_NBER WHERE s.REQUEST_NBER ='" + Txfiled_REQnum_ReqSP.getText() + "'";
                java.sql.Statement statement1 = connection.createStatement();
                printreport print = new printreport();
                String ss = Txfiled_REQnum_ReqSP.getText();

                print.RequestSP(ss);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }
    }

    @FXML
    private void M_Btn_Cancel_ReqSP(ActionEvent event) {
        clear();
    }

    @FXML
    private void M_Btn_Delete_ReqSP(ActionEvent event) {

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            
             Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setTitle("");
            alert3.setHeaderText("");
            if (count_Language == 0) {
                alert.setContentText("This Request spare part will be deleted ");
            } else {
                alert.setContentText("  سوف يتم حذف طلب قطعة الغيار");

            }

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                String deletSP = "DELETE FROM  `attach` " + " WHERE REQUEST_NBER= " + Txfiled_REQnum_ReqSP.getText();
                String sql1 = "DELETE FROM  `requested_spare_parts` " + " WHERE REQUEST_NBER= " + Txfiled_REQnum_ReqSP.getText();

                java.sql.Statement statement1 = connection.createStatement();

                try {
                    statement1.executeUpdate(deletSP);
                    statement1.executeUpdate(sql1);
                    clear();
                    {
                        if (count_Language == 0) {
                            alert3.setHeaderText(null);
                            alert3.setContentText("Deleted Successfully");
                            alert3.showAndWait();
                            return;

                        } else if (count_Language == 1) {
                            alert3.setHeaderText(null);
                            alert3.setContentText("تم الحذف بنجاح");
                            alert3.showAndWait();
                            return;

                        }

                    }
                } catch (SQLException e) {
                    if (count_Language == 0) {

                        alert3.setHeaderText(null);
                        alert3.setContentText("Can not delete this request spare part");
                        alert3.showAndWait();
                        return;
                    } else if (count_Language == 1) {

                        alert3.setHeaderText(null);
                        alert3.setContentText("لا يمكن حذف طلب  قطعة الغيار");
                        alert3.showAndWait();
                        return;
                    }
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void M_Btn_Save_ReqSP(ActionEvent event) {
        alert2.setTitle(null);
        alert2.setHeaderText(null);
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            if (Date_REQdate_ReqSP.getValue() == null) {

                if (count_Language == 0) {
                    alert.setContentText("Please enter request date");
                } else {
                    alert.setContentText("الرجاء إدخال تاريخ الطلب");

                }
                alert.showAndWait();
                return;

            }
            if (Selct_Supplier_ReqSP.getSelectionModel().isEmpty()) {

                if (count_Language == 0) {
                    alert.setContentText("Please select supplier");
                } else {
                    alert.setContentText("الرجاء اختيار المزود");

                }
                alert.showAndWait();
                return;

            }
            Statement st2 = connection.createStatement();
            st2.executeQuery("SELECT * FROM `supplier`");
            ResultSet rs2 = st2.getResultSet();
            int IndexOFTech = 0;
            for (int i = 0; i < ListOfSuppliers.size(); i++) {

                while (rs2.next()) {

                    if (Selct_Supplier_ReqSP.getValue().equals(rs2.getString("SUP_NAME"))) {

                        IndexOFTech = Integer.parseInt(rs2.getString("SUPPLIER_NBER"));

                    }
                }
            }

            if (count == 1) {

                String sql1 = "INSERT INTO `requested_spare_parts` VALUES(" + monumber + "," + "'" + Date_REQdate_ReqSP.getValue() + "'" + "," + "'" + IndexOFTech + "')";
                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sql1);
                if (count_Language == 0) {

                    alert2.setContentText(" A new Request Spare Part has been created");
                } else {
                    alert2.setContentText("تم انشاء طلب غيار جديد ");
                }
                alert2.showAndWait();

            } else if (count == 2) {
                String sql1 = "UPDATE  `requested_spare_parts` SET REQUEST_DATE='" + Date_REQdate_ReqSP.getValue() + "',SUPPLIER_NBER='" + IndexOFTech
                        + "' WHERE REQUEST_NBER= '" + Txfiled_REQnum_ReqSP.getText() + "'";
                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sql1);
                if (count_Language == 0) {

                    alert2.setContentText(" Changes saved successfully");
                } else {
                    alert2.setContentText("تم حفظ التعديلات بنجاح");

                }

                alert2.showAndWait();

            }

            clear();
            count = 2;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void clear() {
        Btn_AddSP_ReqSP.setDisable(true);
        Btn_Cancel_ReqSP.setDisable(true);
        Btn_Delete_ReqSP.setDisable(true);
        Btn_RemoveSP_ReqSP.setDisable(true);
        Btn_Save_ReqSP.setDisable(true);
        Btn_Print_ReqSP.setDisable(true);
        Btn_Search_ReqSP.setDisable(false);
        Txfiled_REQnum_ReqSP.setDisable(false);
        Txfiled_REQnum_ReqSP.clear();
        Selct_Supplier_ReqSP.getSelectionModel().clearSelection();
        Date_REQdate_ReqSP.setValue(null);
        Table_SelectedSP_ReqSP.getItems().clear();
        Table_AddSP_ReqSP.getItems().clear();
        ListOFSelectedSP.clear();
        ListOFSP.clear();
        Selct_Supplier_ReqSP.setDisable(true);
        Date_REQdate_ReqSP.setDisable(true);
        Txfiled_QuanitiySP_ReqSP.setDisable(true);
        Txfiled_SearchSP_ReqSP.setDisable(true);

        if (count_Language == 0) {
            Txfiled_QuanitiySP_ReqSP.setText("Quantity");
            Txfiled_SearchSP_ReqSP.setText("Search");

        } else {
            Txfiled_QuanitiySP_ReqSP.setText("الكمية");
            Txfiled_SearchSP_ReqSP.setText("بحث");

        }

    }

    public void loadSuppliers() {
        String query = "SELECT SUP_NAME FROM supplier";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {

                ListOfSuppliers.add(rs.getString("SUP_NAME"));

            }
            rs.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        Selct_Supplier_ReqSP.setItems(ListOfSuppliers);

    }

    public int monumber = 0;

    @FXML
    private void M_Btn_Search_ReqSP(ActionEvent event) {

        try {
            if (!Mumbervalid(Txfiled_REQnum_ReqSP.getText())) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                if (count_Language == 0) {
                    alert.setContentText("Please enter Request number");
                } else {
                    alert.setContentText("الرجاء ادخال رقم الطلب ");

                }
                alert.showAndWait();

                return;

            }
            Btn_Search_ReqSP.setDisable(true);
            Btn_Save_ReqSP.setDisable(false);
            Btn_Cancel_ReqSP.setDisable(false);
            Selct_Supplier_ReqSP.setDisable(false);
            Date_REQdate_ReqSP.setDisable(false);

            Connection connection = connectionClass.getConnection();
            Statement st = connection.createStatement();
            st.executeQuery("SELECT * FROM `requested_spare_parts` r JOIN `supplier` s ON r.SUPPLIER_NBER  = s.SUPPLIER_NBER  WHERE REQUEST_NBER = " + Txfiled_REQnum_ReqSP.getText());
            ResultSet rs = st.getResultSet();

            if (rs.first()) {

                if (rs.getString("REQUEST_NBER").equals(Txfiled_REQnum_ReqSP.getText())) {

                    count = 2;

                    Txfiled_REQnum_ReqSP.setDisable(true);

                    LocalDate REQUEST_DATE = LocalDate.parse(rs.getString("REQUEST_DATE"));

                    Date_REQdate_ReqSP.setValue(REQUEST_DATE);

                    Selct_Supplier_ReqSP.getSelectionModel().select(rs.getString("SUP_NAME"));

                    Btn_Print_ReqSP.setDisable(false);
                    Btn_Delete_ReqSP.setDisable(false);
                    Txfiled_QuanitiySP_ReqSP.setDisable(false);
                    Txfiled_SearchSP_ReqSP.setDisable(false);
                    Btn_AddSP_ReqSP.setDisable(false);
                    Btn_RemoveSP_ReqSP.setDisable(false);
                    loadSpSelected();
                    loadSpecifecSP();

                }

            } else {
                
                

                Statement st2 = connection.createStatement();
                st2.executeQuery("SELECT * FROM `requested_spare_parts` ORDER BY `REQUEST_NBER` DESC LIMIT 1");
                ResultSet rs2 = st2.getResultSet();
                if (rs2.first()) {
                     if (count_Language == 0) {
                    alert2.setContentText("New request for spare part will be created");
                } else {
                    alert2.setContentText("سوف يتم انشاء طلب قطع غيار جديدة");

                }
                alert2.showAndWait();
                    count = 1;
                    monumber = Integer.parseInt(rs2.getString("REQUEST_NBER"));
                    monumber++;
                    Txfiled_REQnum_ReqSP.setText(String.valueOf(monumber));
                    Txfiled_REQnum_ReqSP.setDisable(true);
                    Btn_Delete_ReqSP.setDisable(true);
                    Btn_Print_ReqSP.setDisable(true);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void M_MousClicked_TabelSelecSP_ReqSP(MouseEvent event) {
        SPSelected2 = Table_SelectedSP_ReqSP.getSelectionModel().getSelectedItems();

        Txfiled_QuanitiySP_ReqSP.setText(String.valueOf(SPSelected2.get(0).getSP_Quantity()));

    }

    @FXML
    private void M_Txfiled_QuanitiySP_ReqSP(ActionEvent event) {

        if (!Mumbervalid(Txfiled_QuanitiySP_ReqSP.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            if (count_Language == 0) {
                alert.setContentText("Please enter the quantity");
            } else {
                alert.setContentText("الرجاء ادخال الكمية ");

            }
            alert.showAndWait();
            return;

        }

        alert2.setTitle(null);
        alert2.setHeaderText(null);
        AllSP2 = Table_SelectedSP_ReqSP.getItems();
        SPSelected2 = Table_SelectedSP_ReqSP.getSelectionModel().getSelectedItems();
        int perSp_Quant = Integer.parseInt(Txfiled_QuanitiySP_ReqSP.getText());

        for (int i = 0; i < SPSelected2.size(); i++) {
            try {
                ListOFSelectedSP.add(new AddSP(SPSelected2.get(i).getSP_Number(), SPSelected2.get(i).getSP_Name(), perSp_Quant));

                String sqlupdateAddSP = "UPDATE `attach` SET `Req_QUANTITY` = '" + Txfiled_QuanitiySP_ReqSP.getText() + "' WHERE REQUEST_NBER= " + Txfiled_REQnum_ReqSP.getText()
                        + " AND SP_NBER=" + SPSelected2.get(i).getSP_Number();
                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sqlupdateAddSP);

                SPSelected2.forEach(ListOFSelectedSP::remove);
                Table_SelectedSP_ReqSP.getItems().setAll(ListOFSelectedSP);

                if (count_Language == 0) {
                    Txfiled_QuanitiySP_ReqSP.setText("Quantity");

                    alert2.setContentText("Changes saved successfully");
                } else {
                    Txfiled_QuanitiySP_ReqSP.setText("الكمية");
                    alert2.setContentText("تم حفظ التعديلات بنجاح");

                }

                alert2.showAndWait();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    @FXML
    private void M_Txfiled_SearchSP_ReqSP(KeyEvent event) {
        Choose = 2;

        ListOFSP.clear();

        String id1 = Txfiled_SearchSP_ReqSP.getText();

        String sql1 = "SELECT * FROM spare_parts WHERE SP_NAME = '" + Txfiled_SearchSP_ReqSP.getText() + "'";
        String trysql = "SELECT * FROM spare_parts WHERE SP_NAME LIKE '" + Txfiled_SearchSP_ReqSP.getText() + "%';";
        Search(trysql, Choose);

    }

    @FXML
    private void M_Txfiled_Search_MangeCurrentMO(KeyEvent event) {
        Choose = 3;

        String sql1 = "SELECT * FROM `maintenance_operation` WHERE `MO_NBER` = '" + Txfiled_Search_MangeCurrentMO.getText() + "'";
        String trysql = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID "
                + "WHERE `MO_NBER` LIKE '" + Txfiled_Search_MangeCurrentMO.getText() + "%' AND STATE IN ('approved' ,'under maintenance','تم الموافقة' ,'تحت الصيانة'); ";
        Search(trysql, Choose);

    }

    @FXML
    private void Txfiled_Search_MangeFinshedMO(KeyEvent event) {
        Choose = 4;

        String sql1 = "SELECT * FROM `maintenance_operation` WHERE `MO_NBER` = '" + Txfiled_Search_MangeFinshedMO.getText() + "'";
        String trysql = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID "
                + "WHERE `MO_NBER` LIKE '" + Txfiled_Search_MangeFinshedMO.getText() + "%' AND STATE IN ('repaired','تم الاصلاح'); ";
        Search(trysql, Choose);
    }

    @FXML
    private void Txfiled_Search_MangePreviousMO(KeyEvent event) {
        Choose = 5;

        String sql1 = "SELECT * FROM `maintenance_operation` WHERE `MO_NBER` = '" + Txfiled_Search_MangePreviousMO.getText() + "'";
        String trysql = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID "
                + "WHERE `MO_NBER` LIKE '" + Txfiled_Search_MangePreviousMO.getText() + "%' AND STATE IN ('paid' ,'disapproved','دفعت' ,'مرفوضة'); ";
        Search(trysql, Choose);
    }

    @FXML
    private void Txfiled_Search_MangePendingMO(KeyEvent event) {
        Choose = 6;

        String sql1 = "SELECT * FROM `maintenance_operation` WHERE `MO_NBER` = '" + Txfiled_Search_MangePendingMO.getText() + "'";
        String trysql = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID "
                + "WHERE `MO_NBER` LIKE '" + Txfiled_Search_MangePendingMO.getText() + "%' AND STATE IN( 'other defects has been detected' ,'cannot be done' ,'created'"
                + ",'تم الكشف عن عيوب أخرى' ,'لا يمكن القيام بعملية الصيانة' ,'تم الإنشاء'); ";
        Search(trysql, Choose);
    }

    void CheckListReportVisabil_EN(String ListName) {
        if (ListName.equalsIgnoreCase("- current  maintenance operations") || ListName.equalsIgnoreCase("- Finished maintenance operations")
                || ListName.equalsIgnoreCase("- previous  maintenance operations")) {
            Hbox_Date_Reports.setVisible(true);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(false);

        } else if (ListName.equalsIgnoreCase("- financial estimate of maintenance")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(true);

        } else if (ListName.equalsIgnoreCase("- list of customers") || ListName.equalsIgnoreCase("- list of employees") || ListName.equalsIgnoreCase("- list of Suppliers")
                || ListName.equalsIgnoreCase("- Spare Parts about to be out of stock") || ListName.equalsIgnoreCase("- Spare parts out of stock")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(false);

        } else if (ListName.equalsIgnoreCase("- list of customer maintenance operations")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(true);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(false);
        } else if (ListName.equalsIgnoreCase("- List of maintenance operations for an employee")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(true);
            Hbox_MO_Reports.setVisible(false);
        }

    }

    void CheckListReportVisabil_AR(String ListName) {
        if (ListName.equalsIgnoreCase("- عمليات الصيانة الحالية") || ListName.equalsIgnoreCase("- عمليات الصيانة المنتهية")
                || ListName.equalsIgnoreCase("- عمليات الصيانة السابقة")) {
            Hbox_Date_Reports.setVisible(true);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(false);

        } else if (ListName.equalsIgnoreCase("- تقدير مالي عن عملية صيانة")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(true);

        } else if (ListName.equalsIgnoreCase("- قائمة بالعملاء") || ListName.equalsIgnoreCase("- قائمة بالموظفين") || ListName.equalsIgnoreCase("- قائمة بالمزودين")
                || ListName.equalsIgnoreCase("- قطع على وشك النفاذ") || ListName.equalsIgnoreCase("- قطع الغيار التي نفذت كميتها")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(false);

        } else if (ListName.equalsIgnoreCase("- قائمة عمليات الصيانة لعميل")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(true);
            Hbox_Emolyee_Reports.setVisible(false);
            Hbox_MO_Reports.setVisible(false);
        } else if (ListName.equalsIgnoreCase("- قائمة عمليات الصيانة لموظف")) {

            Hbox_Date_Reports.setVisible(false);
            Hbox_Customer_Reports.setVisible(false);
            Hbox_Emolyee_Reports.setVisible(true);
            Hbox_MO_Reports.setVisible(false);
        }

    }

    @FXML
    private void M_Btn_RestoreDB_Tools(ActionEvent event) {

        try {
       

            FileChooser fileChooser = new FileChooser();
            Window stage = null;
            File selectedDirectory = fileChooser.showOpenDialog(stage);

            if (selectedDirectory == null) {
            } else {
                     String sqlDrop = "DROP DATABASE mo_db";
            String sqlCreate = "CREATE DATABASE mo_db";
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sqlDrop);
            statement1.executeUpdate(sqlCreate);
                String Path = selectedDirectory.getAbsolutePath();

                String[] executeCmd = new String[]{"C:\\xampp\\mysql\\bin\\mysql.exe", "mo_db", "--user=" + "root", "--password=" + "", "-e", " source " + Path};

                Process runtimeProcess;
                try {

                    runtimeProcess = Runtime.getRuntime().exec(executeCmd);

                    int processComplete = runtimeProcess.waitFor();
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);

                    if (processComplete == 0) {
                        alert.setContentText("Restored Succuss");
                    } else {
                        alert.setContentText("Can't Restored");
                    }
                    alert.showAndWait();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    @FXML
    private void M_Btn_SaveDB_Tools(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Window stage = null;
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory == null) {
        } else {

            String executeCmd = "C:\\xampp\\mysql\\bin\\mysqldump.exe -u root  mo_db -r ";
            String Path = selectedDirectory.getAbsolutePath();
            Path = Path + "\\SaveDB_" + LocalDate.now() + ".sql";
            Process runtimeProcess;
            try {

                runtimeProcess = Runtime.getRuntime().exec(executeCmd + Path);

                int processComplete = runtimeProcess.waitFor();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);

                if (processComplete == 0) {
                    alert.setContentText("Backup created successfully");
                } else {
                    alert.setContentText("Could not create the backup");
                }
                alert.showAndWait();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }

    }

    @FXML
    private void M_Btn_ArchiveDB_Tools(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader();
        if (count_Language == 0) {
            loader.setLocation(getClass().getResource("/sample/ForArchive_Date_EN.fxml"));
            try {
                loader.load();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

            Controller_ArchiveDate controller_ArchiveDate = loader.getController();
            controller_ArchiveDate.Set_count_Language(0);

        } else if (count_Language == 1) {
            loader.setLocation(getClass().getResource("/sample/ForArchive_Date_AR.fxml"));

            try {

                loader.load();
                Controller_ArchiveDate controller_ArchiveDate = loader.getController();
                controller_ArchiveDate.Set_count_Language(0);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.showAndWait();

    }

    @FXML
    private void M_KeyReleased_TabelSelecSP_ReqSP(KeyEvent event) {
        SPSelected2 = Table_SelectedSP_ReqSP.getSelectionModel().getSelectedItems();

        Txfiled_QuanitiySP_ReqSP.setText(String.valueOf(SPSelected2.get(0).getSP_Quantity()));
    }
    String GetSelectedItem;

    @FXML
    private void M_ReportList_MouseClicked(MouseEvent event) {

        GetSelectedItem = (String) List_of_reports.getSelectionModel().getSelectedItem();
        if (count_Language == 0) {
            CheckListReportVisabil_EN(GetSelectedItem);
        } else {

            CheckListReportVisabil_AR(GetSelectedItem);
        }
    }

    @FXML
    private void M_ReportList_KeyReleased(KeyEvent event) {
        if (event.getCode().isNavigationKey() == true) {
            GetSelectedItem = (String) List_of_reports.getSelectionModel().getSelectedItem();
            if (count_Language == 0) {
                CheckListReportVisabil_EN(GetSelectedItem);
            } else {

                CheckListReportVisabil_AR(GetSelectedItem);
            }

        }
    }

    public static class AddSP {

        private final SimpleIntegerProperty SP_Number;
        private final SimpleStringProperty SP_Name;
        private final SimpleIntegerProperty SP_Quantity;

        AddSP(int SP_Number, String SP_Name, int SP_Quantity) {
            this.SP_Number = new SimpleIntegerProperty(SP_Number);
            this.SP_Name = new SimpleStringProperty(SP_Name);
            this.SP_Quantity = new SimpleIntegerProperty(SP_Quantity);

        }

        public Integer getSP_Number() {
            return SP_Number.get();
        }

        public String getSP_Name() {
            return SP_Name.get();
        }

        public Integer getSP_Quantity() {
            return SP_Quantity.get();
        }

    }

    public static class MO {

        private final SimpleIntegerProperty MO_Number;
        private final SimpleStringProperty Cus_Name;
        private final SimpleIntegerProperty Cus_Mobile;
        private final SimpleStringProperty MO_technician;
        private final SimpleStringProperty MO_EndDate;
        private final SimpleDoubleProperty MO_TotalCost;
        private final SimpleStringProperty MO_Status;

        MO(Integer MO_Number, String Cus_Name, Integer Cus_Mobile, String MO_technician, String MO_EndDate, double MO_TotalCost, String MO_Status) {
            this.MO_Number = new SimpleIntegerProperty(MO_Number);
            this.Cus_Name = new SimpleStringProperty(Cus_Name);
            this.Cus_Mobile = new SimpleIntegerProperty(Cus_Mobile);
            this.MO_technician = new SimpleStringProperty(MO_technician);
            this.MO_EndDate = new SimpleStringProperty(MO_EndDate);
            this.MO_TotalCost = new SimpleDoubleProperty(MO_TotalCost);
            this.MO_Status = new SimpleStringProperty(MO_Status);

        }

        public Integer getMO_Number() {
            return MO_Number.get();
        }

        public String getCus_Name() {
            return Cus_Name.get();
        }

        public Integer getCus_Mobile() {
            return Cus_Mobile.get();
        }

        public String getMO_technician() {
            return MO_technician.get();
        }

        public String getMO_EndDate() {
            return MO_EndDate.get();
        }

        public Double getMO_TotalCost() {
            return MO_TotalCost.get();
        }

        public String getMO_Status() {
            return MO_Status.get();
        }
    }

    @FXML
    private void M_Btn_Edit_MangeCurrentMO(ActionEvent event) {

        openEdit(Table_CurrentMO_MngMO);
    }
    int Choose = 0;

    public void Search(String Search, int Choose) {
        String State;
        if (Choose == 2) {
            ResultSet rs = connectionClass.execQuery(Search);
            try {
                while (rs.next()) {
                    String mname = rs.getString("SP_NBER");
                    String mid = rs.getString("SP_NAME");
                    String mobile = rs.getString("DESCRIPTION");
                    String price = rs.getString("PRICE");

                    int SP_num = Integer.parseInt(mname);
                    int SP_Quant = Integer.parseInt(rs.getString("SP_QUANTITY"));

                    ListOFSP.add(new AddSP(SP_num, rs.getString("SP_Name"), SP_Quant));
                }

                for (int i = 0; i < ListOFSP.size(); i++) {
                    for (int j = 0; j < ListOFSelectedSP.size(); j++) {
                        if (ListOFSP.get(i).getSP_Number().equals(ListOFSelectedSP.get(j).getSP_Number())) {
                            ListOFSP.remove(i);

                        }

                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);

            }
            Table_AddSP_ReqSP.getItems().setAll(ListOFSP);

        } else if (Choose == 1) {

            ResultSet rs = connectionClass.execQuery(Search);
            try {
                while (rs.next()) {

                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);

            }

        } else if (Choose == 3) {

            ResultSet rs = connectionClass.execQuery(Search);
            CurrnetList.clear();
            try {
                while (rs.next()) {

                    String MONber = rs.getString("MO_NBER");
                    String mobile = rs.getString("CUS_MOBILE_NBER");
                    String priceSP = rs.getString("SP_COST");
                    String priceMO = rs.getString("MO_COST");
                    int MO_num = Integer.parseInt(MONber);
                    int CusMobile = Integer.parseInt(mobile);
                    double TotalCost = Double.parseDouble(priceSP) + Double.parseDouble(priceMO);
                    if (rs.getString("STATE").equals("approved") || rs.getString("STATE").equals("تم الموافقة")) {
                        if (count_Language == 0) {
                            State = "approved";

                        } else {
                            State = "تم الموافقة";

                        }
                        CurrnetList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                    } else if (rs.getString("STATE").equals("تحت الصيانة") || rs.getString("STATE").equals("under maintenance")) {

                        if (count_Language == 0) {
                            State = "under maintenance";

                        } else {
                            State = "تحت الصيانة";

                        }
                        CurrnetList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);

            }
            Table_CurrentMO_MngMO.getItems().setAll(CurrnetList);

        } else if (Choose == 4) {

            ResultSet rs = connectionClass.execQuery(Search);
            FinshedList.clear();
            try {
                while (rs.next()) {

                    String MONber = rs.getString("MO_NBER");
                    String mobile = rs.getString("CUS_MOBILE_NBER");
                    String priceSP = rs.getString("SP_COST");
                    String priceMO = rs.getString("MO_COST");
                    int MO_num = Integer.parseInt(MONber);
                    int CusMobile = Integer.parseInt(mobile);
                    double TotalCost = Double.parseDouble(priceSP) + Double.parseDouble(priceMO);
                    if (rs.getString("STATE").equals("تم الاصلاح") || rs.getString("STATE").equals("repaired")) {

                        if (count_Language == 0) {
                            State = "repaired";

                        } else {
                            State = "تم الاصلاح";

                        }
                        FinshedList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                    }

                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);

            }
            Table_FinshedMO_MngMO.getItems().setAll(FinshedList);

        } else if (Choose == 5) {

            ResultSet rs = connectionClass.execQuery(Search);
            PriveousList.clear();
            try {
                while (rs.next()) {

                    String MONber = rs.getString("MO_NBER");
                    String mobile = rs.getString("CUS_MOBILE_NBER");
                    String priceSP = rs.getString("SP_COST");
                    String priceMO = rs.getString("MO_COST");
                    int MO_num = Integer.parseInt(MONber);
                    int CusMobile = Integer.parseInt(mobile);
                    double TotalCost = Double.parseDouble(priceSP) + Double.parseDouble(priceMO);
                    if (rs.getString("STATE").equals("مرفوضة") || rs.getString("STATE").equals("disapproved")) {
                        if (count_Language == 0) {
                            State = "disapproved";

                        } else {
                            State = "مرفوضة";

                        }
                        PriveousList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                    } else if (rs.getString("STATE").equals("دفعت") || rs.getString("STATE").equals("paid")) {
                        if (count_Language == 0) {
                            State = "paid";

                        } else {
                            State = "دفعت";

                        }
                        PriveousList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);

            }
            Table_PreviousMO_MngMO.getItems().setAll(PriveousList);

        } else if (Choose == 6) {

            ResultSet rs = connectionClass.execQuery(Search);
            PendingList.clear();
            try {
                while (rs.next()) {

                    String MONber = rs.getString("MO_NBER");
                    String mobile = rs.getString("CUS_MOBILE_NBER");
                    String priceSP = rs.getString("SP_COST");
                    String priceMO = rs.getString("MO_COST");
                    int MO_num = Integer.parseInt(MONber);
                    int CusMobile = Integer.parseInt(mobile);
                    double TotalCost = Double.parseDouble(priceSP) + Double.parseDouble(priceMO);
                    if (rs.getString("STATE").equals("created") || rs.getString("STATE").equals("تم الإنشاء")) {
                        if (count_Language == 0) {
                            State = "created";
                        } else {
                            State = "تم الإنشاء";

                        }
                        PendingList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                    } else if (rs.getString("STATE").equals("لا يمكن القيام بعملية الصيانة") || rs.getString("STATE").equals("cannot be done")) {
                        if (count_Language == 0) {
                            State = "cannot be done";
                        } else {
                            State = "لا يمكن القيام بعملية الصيانة";

                        }
                        PendingList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                    } else if (rs.getString("STATE").equals("تم الكشف عن عيوب أخرى") || rs.getString("STATE").equals("other defects has been detected")) {

                        if (count_Language == 0) {
                            State = "other defects has been detected";

                        } else {
                            State = "تم الكشف عن عيوب أخرى";

                        }
                        PendingList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, State));

                    }

                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);

            }
            Table_pendingMO_MngMO.getItems().setAll(PendingList);

        }

    }

    public int Em_Id;

    @FXML
    private void M_Btn_ChangePassword_Tools(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader();
        if (count_Language == 0) {
            loader.setLocation(getClass().getResource("/sample/ChangePassword_EN.fxml"));
            try {
                loader.load();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

            Controller_ChangePassword changePassword = loader.getController();
            changePassword.Set_count_Language(0);

        } else if (count_Language == 1) {
            loader.setLocation(getClass().getResource("/sample/ChangePassword_AR.fxml"));

            try {

                loader.load();
                Controller_ChangePassword changePassword = loader.getController();
                changePassword.Set_count_Language(1);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }

        Controller_ChangePassword changePassword = loader.getController();
        changePassword.Em_Id = Em_Id;

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.showAndWait();

    }

    public void SetUserinformation(String Name, String Job) {

        if (Job.equals("فني") || Job.equals("Technician")) {
            if (count_Language == 0) {

                Label_UserJob.setText("Technician");
                Label_UserID_Name.setText(Em_Id + "- " + Name);
            } else {
                Label_UserJob.setText("فني");
                Label_UserID_Name.setText(Name + " -" + Em_Id);
            }
        } else if (Job.equals("اداري") || Job.equals("Administrator")) {
            if (count_Language == 0) {

                Label_UserJob.setText("Administrator");
                Label_UserID_Name.setText(Em_Id + "- " + Name);
            } else {
                Label_UserJob.setText("اداري");
                Label_UserID_Name.setText(Name + " -" + Em_Id);

            }
        } else if (Job.equals("استقبال") || Job.equals("ReceptionDesk")) {
            if (count_Language == 0) {

                Label_UserJob.setText("ReceptionDesk");
                Label_UserID_Name.setText(Em_Id + "- " + Name);
            } else {
                Label_UserJob.setText("استقبال");
                Label_UserID_Name.setText(Name + " -" + Em_Id);

            }
        }

    }
}
