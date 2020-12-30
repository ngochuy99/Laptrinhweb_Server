//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;
import model.Answer;
import model.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ClientList extends JFrame {
    DefaultTableModel model;
    private String[] columnNames = new String[]{"Số TT", "Mã SV", "Họ và Tên", "IP", "Nhóm", "Đăng ký", "CAESAR", "SecondChar", "-", "USCLN", "Prime"};
    private Object[][] data;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem1;
    private JScrollPane jScrollPane1;
    private JTable jTabClientList;

    public ClientList() {
        this.initComponents();
        this.model = new DefaultTableModel(this.data, this.columnNames);
        this.jTabClientList.setModel(this.model);
    }

    public static void access$000(ClientList this$0, WindowEvent evt) {
    }

    public static void access$100(ClientList this$0, ActionEvent evt) {
    }

    public void addNewRow(Student sv) {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        this.model.addRow(new Object[]{this.model.getRowCount(), sv.getMaSV(), sv.getHovaten(), sv.getIP(), sv.getGroup(), dateString});
    }

    public void addNewRows(Answer answer) {
        this.model.addRow(new Object[]{this.model.getRowCount() + 1, answer.getStudent().getMaSV(), answer.getStudent().getHovaten(), answer.getStudent().getIP(), answer.getStudent().getGroup(), answer.isAlreadyRegistration() ? "Yes" : "No", answer.getViewString()[0], answer.getViewString()[1], answer.getViewString()[2], answer.getViewString()[3], answer.getViewString()[4]});
    }

    public void updateAnswerView(Answer answer) {
        if (this.model.getRowCount() == 0) {
            this.addNewRows(answer);
        } else {
            Vector rows = this.model.getDataVector();
            Iterator it = rows.iterator();
            int rowPos = 0;
            boolean isUpdate = false;

            while(it.hasNext()) {
                ++rowPos;
                Vector next = (Vector)it.next();
                Iterator itItem = next.iterator();
                if (next != null && next.get(1).toString().equalsIgnoreCase(answer.getStudent().getMaSV())) {
                    isUpdate = true;
                    this.model.setValueAt(answer.getStudent().getIP(), rowPos - 1, 3);
                    this.model.setValueAt(answer.getStudent().getGroup(), rowPos - 1, 4);
                    this.model.setValueAt(answer.isAlreadyRegistration() ? "Yes" : "No", rowPos - 1, 5);
                    this.model.setValueAt(answer.getViewString()[0], rowPos - 1, 6);
                    this.model.setValueAt(answer.getViewString()[1], rowPos - 1, 7);
                    this.model.setValueAt(answer.getViewString()[2], rowPos - 1, 8);
                    this.model.setValueAt(answer.getViewString()[3], rowPos - 1, 9);
                    this.model.setValueAt(answer.getViewString()[4], rowPos - 1, 10);
                    break;
                }
            }

            if (!isUpdate) {
                this.addNewRows(answer);
            }

        }
    }

    private void initComponents() {
        this.jMenu1 = new JMenu();
        this.jScrollPane1 = new JScrollPane();
        this.jTabClientList = new JTable();
        this.jMenuBar1 = new JMenuBar();
        this.jMenu2 = new JMenu();
        this.jMenuItem1 = new JMenuItem();
        this.jMenu1.setText("jMenu1");
        this.setDefaultCloseOperation(3);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                ClientList.this.windowClosing(evt);
            }
        });
        this.jTabClientList.setModel(new DefaultTableModel(new Object[][]{new Object[0], new Object[0], new Object[0], new Object[0]}, new String[0]));
        this.jScrollPane1.setViewportView(this.jTabClientList);
        this.jMenu2.setText("Export Excel");
        this.jMenuItem1.setText("Save Excel");
        this.jMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ClientList.this.jMenuItem1ActionPerformed(evt);
            }
        });
        this.jMenu2.add(this.jMenuItem1);
        this.jMenuBar1.add(this.jMenu2);
        this.setJMenuBar(this.jMenuBar1);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -1, 507, 32767).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -1, 339, 32767).addContainerGap()));
        this.pack();
    }

    private void windowClosing(WindowEvent evt) {
    }

    private void jMenuItem1ActionPerformed(ActionEvent evt) {
        Workbook wb = new HSSFWorkbook();
        CreationHelper createhelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");
        Row firstRow = sheet.createRow(0);

        for(int j = 0; j < this.model.getColumnCount(); ++j) {
            firstRow.createCell(j).setCellValue(this.columnNames[j]);
        }

        Row row = null;
        Cell cell = null;

        for(int i = 0; i < this.model.getRowCount(); ++i) {
            row = sheet.createRow(i + 1);

            for(int j = 0; j < this.model.getColumnCount(); ++j) {
                cell = row.createCell(j);
                cell.setCellValue(String.valueOf(this.model.getValueAt(i, j)));
            }
        }

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
            Date currentTime = new Date();
            formatter.format(currentTime);
            FileOutputStream out = new FileOutputStream("Kip4.xls");
            wb.write(out);
            out.close();
        } catch (FileNotFoundException var12) {
            Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, (String)null, var12);
        } catch (IOException var13) {
            Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, (String)null, var13);
        }

    }

    public static void main(String[] args) {
        try {
            LookAndFeelInfo[] var1 = UIManager.getInstalledLookAndFeels();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                LookAndFeelInfo info = var1[var3];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException var5) {
            Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, (String)null, var8);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClientList client = new ClientList();
                client.setVisible(true);
                client.addNewRow((Student)null);
            }
        });
    }
}
