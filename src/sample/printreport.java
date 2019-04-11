    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import Connectvy.ConnectionClass;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;


/**
 *
 * @author algha
 */
public class printreport extends JFrame {
    ConnectionClass connectionClass = new ConnectionClass();
    // we call conneClass  that we make it up
    Connection connection = connectionClass.getConnection();
     PreparedStatement pst = null;
     ResultSet rs =null;
     
     public printreport() throws HeadlessException{
        
     } 
   
    public void InvoiceEN(String ss) throws JRException {

        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\INVOICEENG.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('paid','دفعت') AND m.MO_NBER ='" + ss + "'";

        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    public void financialassessmentEN(String ff) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\FinancialassessmentENG.jrxml");
          String A = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected','disapproved','cannot be done','repaired','تم الإنشاء', 'تم الموافقة', 'تحت الصيانة', 'تم الكشف عن عيوب أخرى','مرفوضة','لا يمكن القيام بعملية الصيانة','تم الاصلاح') AND m.MO_NBER = '" + ff + "'";
    //String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approve', 'under maintenance', 'other defects has been detected') AND m.MO_NBER = '" + ff + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(A);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    public void InvoiceAR(String ss) throws JRException {

        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\INVOICEAR.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('paid','دفعت') AND m.MO_NBER ='" + ss + "'";

        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    public void financialassessmentAR(String ff) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\financialassessmentAR.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected','disapproved','cannot be done','repaired','تم الإنشاء', 'تم الموافقة', 'تحت الصيانة', 'تم الكشف عن عيوب أخرى','مرفوضة','لا يمكن القيام بعملية الصيانة','تم الاصلاح') AND m.MO_NBER = '" + ff + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    public void PreviousMO(LocalDate bb, LocalDate ss) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\PreviousMO.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','دفعت','disapproved','مرفوضة') AND m.STARTING_DATE <= '" + bb + "' AND m.STARTING_DATE >= '" + ss + "'";
        System.out.println(query);
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    public void CurrentMO(LocalDate bb, LocalDate ss) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\CurrentMO.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance','تم الموافقة','تحت الصيانة') AND m.STARTING_DATE <= '" + bb + "' AND m.STARTING_DATE >= '" + ss + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void FinshedMO(LocalDate bb, LocalDate ss) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\FinshedMO.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('تم الاصلاح','repaired') AND m.STARTING_DATE <= '" + bb + "' AND m.STARTING_DATE >= '" + ss + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void CustomersList() throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\CustomersList.jrxml");
        //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
        // JRDesignQuery jrquery = new JRDesignQuery();
        //jrquery.setText(query);
        // jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void EmployeesList() throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\EmployeesList.jrxml");
        //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
        // JRDesignQuery jrquery = new JRDesignQuery();
        //jrquery.setText(query);
        // jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void SuppliersList() throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\SuppliersList.jrxml");
        //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
        // JRDesignQuery jrquery = new JRDesignQuery();
        //jrquery.setText(query);
        // jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void SPsOUTOS() throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\SPs OUTOS.jrxml");
        //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
        // JRDesignQuery jrquery = new JRDesignQuery();
        //jrquery.setText(query);
        // jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void SPstobeOUTOS() throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\SPs to be OUTOS.jrxml");
        //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
        // JRDesignQuery jrquery = new JRDesignQuery();
        //jrquery.setText(query);
        // jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void ListofaemployeesMOs(String Number) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\List of a employee's MOs .jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where e.EMP_NAME = '" + Number + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    public void ListofcustomersMOs(String bb, String Number) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\List of a customer's MOs .jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where m.MO_NBER = '" + bb + "'OR r.CUS_MOBILE_NBER = '" + Number + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    public void FinancialassessReportAR(String bb, String Number) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\FinancialassessmentreportAR.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected','disapproved','cannot be done','repaired','تم الإنشاء', 'تم الموافقة', 'تحت الصيانة', 'تم الكشف عن عيوب أخرى','مرفوضة','لا يمكن القيام بعملية الصيانة','تم الاصلاح') AND m.MO_NBER = '" + bb + "' OR r.CUS_MOBILE_NBER = '" + Number + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);

        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    void PreviousMOPeriod(LocalDate AA, LocalDate MM) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\PreviousMO.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapproved','دفعت','مرفوضة') AND m.STARTING_DATE >= '" + AA + "' AND m.STARTING_DATE <= '" + MM + "'";
        System.out.println(query);
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    void CurrentMOPeriod(LocalDate AA, LocalDate MM) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\CurrentMO.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance','تم الموافقة','تحت الصيانة') AND m.STARTING_DATE >= '" + AA + "' AND m.STARTING_DATE <= '" + MM + "'";
        System.out.println(query);
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    public void FinshedMOPeriod(LocalDate AA, LocalDate MM) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\FinshedMO.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired','تم الاصلاح') AND m.STARTING_DATE >= '" + AA + "' AND m.STARTING_DATE <= '" + MM + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void PreviousMOEN(LocalDate bb, LocalDate ss) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\PreviousMOEN.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapproved','دفعت','مرفوضة') AND m.STARTING_DATE <= '" + bb + "' AND m.STARTING_DATE >= '" + ss + "'";
        System.out.println(query);
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    public void CurrentMOEN(LocalDate bb, LocalDate ss) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\CurrentMOEN.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance','تم الموافقة','تحت الصيانة') AND m.STARTING_DATE <= '" + bb + "' AND m.STARTING_DATE >= '" + ss + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void FinshedMOEN(LocalDate bb, LocalDate ss) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\FinshedMOEN.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired','تم الاصلاح') AND m.STARTING_DATE <= '" + bb + "' AND m.STARTING_DATE >= '" + ss + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void CustomersListEN() throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\CustomersListEN.jrxml");
        //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
        // JRDesignQuery jrquery = new JRDesignQuery();
        //jrquery.setText(query);
        // jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void EmployeesListEN() throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\EmployeesListEN.jrxml");
        //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
        // JRDesignQuery jrquery = new JRDesignQuery();
        //jrquery.setText(query);
        // jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void SuppliersListEN() throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\SuppliersListEN.jrxml");
        //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
        // JRDesignQuery jrquery = new JRDesignQuery();
        //jrquery.setText(query);
        // jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void SPsOUTOSEN() throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\SPs OUTOSEN.jrxml");
        //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
        // JRDesignQuery jrquery = new JRDesignQuery();
        //jrquery.setText(query);
        // jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void SPstobeOUTOSEN() throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\SPs to be OUTOSEN.jrxml");
        //  String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','disapprove')";
        // JRDesignQuery jrquery = new JRDesignQuery();
        //jrquery.setText(query);
        // jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void ListofaemployeesMOsEN(String ss) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\List of a employee's MOsEN.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where e.EMP_NAME = '" + ss + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    public void ListofcustomersMOsEN(String bb, String ss) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\List of a customer's MOsEN.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID Where m.MO_NBER = '" + bb + "'OR r.CUS_MOBILE_NBER = '" + ss + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    public void FinancialassessReportENG(String bb, String ss) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\FinancialassessmentreportENG.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected','disapproved','cannot be done','repaired','تم الإنشاء', 'تم الموافقة', 'تحت الصيانة', 'تم الكشف عن عيوب أخرى','مرفوضة','لا يمكن القيام بعملية الصيانة','تم الاصلاح') AND m.MO_NBER = '" + bb + "' OR r.CUS_MOBILE_NBER = '" + ss + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);

        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    void PreviousMOPeriodEN(LocalDate AA, LocalDate MM) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\PreviousMOEN.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','دفعت','disapproved','مرفوضة') AND m.STARTING_DATE >= '" + AA + "' AND m.STARTING_DATE <= '" + MM + "'";
        System.out.println(query);
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    void CurrentMOPeriodEN(LocalDate AA, LocalDate MM) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\CurrentMOEN.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('approved','under maintenance','تم الموافقة','تحت الصيانة') AND m.STARTING_DATE >= '" + AA + "' AND m.STARTING_DATE <= '" + MM + "'";
        System.out.println(query);
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    public void FinshedMOPeriodEN(LocalDate AA, LocalDate MM) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\FinshedMOEN.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('repaired','تم الاصلاح') AND m.STARTING_DATE >= '" + AA + "' AND m.STARTING_DATE <= '" + MM + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);
    }

    public void RequestSPEN(String ss) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\RequestSPEN.jrxml");
        String query = "SELECT * FROM `requested_spare_parts` s JOIN `attach` r ON s.REQUEST_NBER = r.REQUEST_NBER JOIN spare_parts p ON r.SP_NBER = p.SP_NBER WHERE s.REQUEST_NBER ='" + ss + "'";

        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

    public void RequestSP(String ss) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\RequestSP.jrxml");
        String query = "SELECT * FROM `requested_spare_parts` s JOIN `attach` r ON s.REQUEST_NBER = r.REQUEST_NBER JOIN spare_parts p ON r.SP_NBER = p.SP_NBER WHERE s.REQUEST_NBER ='" + ss + "'";

        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }
      public void InvoiceENGWSP(String ss) throws JRException {

        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\INVOICEENGWSP.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','دفعت') AND m.MO_NBER ='" + ss + "'";

        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }
    public void financialassessmentENWSP(String ff) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\FinancialassessmentENGWSP.jrxml");
          String A = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected','disapproved','cannot be done','repaired','تم الإنشاء', 'تم الموافقة', 'تحت الصيانة', 'تم الكشف عن عيوب أخرى','مرفوضة','لا يمكن القيام بعملية الصيانة','تم الاصلاح') AND m.MO_NBER = '" + ff + "'";
    //String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID JOIN `require` a ON m.MO_NBER = a.MO_NBER JOIN `spare_parts` s ON a.SP_NBER = s.SP_NBER WHERE m.STATE IN ('created', 'approve', 'under maintenance', 'other defects has been detected') AND m.MO_NBER = '" + ff + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(A);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }
    public void InvoiceARWSP(String ss) throws JRException {

        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\INVOICEARWSP.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('paid','دفعت') AND m.MO_NBER ='" + ss + "'";

        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }
      public void financialassessmentARWSP(String ff) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\financialassessmentARWSP.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected','disapproved','cannot be done','repaired','تم الإنشاء', 'تم الموافقة', 'تحت الصيانة', 'تم الكشف عن عيوب أخرى','مرفوضة','لا يمكن القيام بعملية الصيانة','تم الاصلاح') AND m.MO_NBER = '" + ff + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }
       public void FinancialassessReportARWSP(String bb, String ss) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\FinancialassessmentReportARWSP.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected','disapproved','cannot be done','repaired','تم الإنشاء', 'تم الموافقة', 'تحت الصيانة', 'تم الكشف عن عيوب أخرى','مرفوضة','لا يمكن القيام بعملية الصيانة','تم الاصلاح') AND m.MO_NBER = '" + bb + "' OR r.CUS_MOBILE_NBER = '" + ss + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);

        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }
       public void FinancialassessReportENGWSP(String bb, String ss) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\LastA\\src\\sample\\FinancialassessmentreportENGWSP.jrxml");
        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE m.STATE IN ('created', 'approved', 'under maintenance', 'other defects has been detected','disapproved','cannot be done','repaired','تم الإنشاء', 'تم الموافقة', 'تحت الصيانة', 'تم الكشف عن عيوب أخرى','مرفوضة','لا يمكن القيام بعملية الصيانة','تم الاصلاح') AND m.MO_NBER = '" + bb + "' OR r.CUS_MOBILE_NBER = '" + ss + "'";
        JRDesignQuery jrquery = new JRDesignQuery();
        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);

        jrquery.setText(query);
        jasperDesign.setQuery(jrquery);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint Jasperprint = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(Jasperprint);
        viewer.setOpaque(true);
        viewer.setVisible(true);

        this.add(viewer);
        this.setSize(900, 500);
        this.setVisible(true);

    }

}



