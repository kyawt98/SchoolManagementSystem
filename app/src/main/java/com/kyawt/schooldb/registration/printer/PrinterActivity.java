package com.kyawt.schooldb.registration.printer;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.kyawt.schooldb.MainActivity;
import com.kyawt.schooldb.R;
import com.kyawt.schooldb.bottomnav.HomeActivity;
import com.kyawt.schooldb.registration.RegistrationListActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PrinterActivity extends AppCompatActivity {

    Button btn_printer,btn_cancel;
    String register_date="", stu_name="", stu_nrc, stu_bd="", father_name="", father_nrc="", father_ph="", address="", email="",course_name="";
    int register_id=0,course_fees=0, course_duration=0;
    String r_id, c_fees, c_duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer);
        btn_printer = (Button) findViewById(R.id.btn_print);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrinterActivity.this, RegistrationListActivity.class);
                startActivity(intent);
            }
        });

        //        ----------- pass data by intent start-------------
        Bundle data = getIntent().getExtras();
        if (data != null){
            register_date = data.getString("key_for_register_date");
            stu_name = data.getString("key_for_student_name");
            stu_nrc = data.getString("key_for_student_nrc");
            stu_bd = data.getString("key_for_student_dob");
            father_name = data.getString("key_for_father_name");
            father_nrc = data.getString("key_for_father_nrc");
            father_ph = data.getString("key_for_father_ph");
            address = data.getString("key_for_address");
            email = data.getString("key_for_email");
            course_name = data.getString("key_for_course_name");
            course_fees = data.getInt("key_for_course_fees");
            course_duration = data.getInt("key_for_course_duration");
            register_id = data.getInt("register_id");
        }
//        ----------- pass data by intent end----------------

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        btn_printer.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                createPDFFile(Common.getAppPath(PrinterActivity.this) + "test_pdf.pdf");
                            }
                        });
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

    }

    private void createPDFFile(String path){
        if (new File(path).exists()){
            new File(path).delete();
        }

        try {
            Document document = new Document();
            //Save
            PdfWriter.getInstance(document,new FileOutputStream(path));

            //Open to write
            document.open();

            //Setting
            document.setPageSize(PageSize.A4);
            document.addCreationDate();
            document.addAuthor("Kyawt Kyawt San");
            document.addCreator("Kyawt");

            //title
            AddNewRegister(document, "Student Registration", Element.ALIGN_CENTER);

            // add more
            AddNewRegister(document, "Register ID: ", Element.ALIGN_LEFT);

            AddNewRegister(document,"#"+ register_id, Element.ALIGN_LEFT);

            AddNewRegister(document, "Register Date: ", Element.ALIGN_RIGHT);
            AddNewRegister(document,register_date, Element.ALIGN_RIGHT);
            addLineSeparator(document);

            AddNewRegister(document, "Student Name: ", Element.ALIGN_JUSTIFIED);
            AddNewRegister(document,stu_name, Element.ALIGN_RIGHT);
            addLineSeparator(document);

            AddNewRegister(document, "Student NRC: ", Element.ALIGN_JUSTIFIED);
            AddNewRegister(document,stu_nrc, Element.ALIGN_RIGHT);
            addLineSeparator(document);

            AddNewRegister(document, "Student Birthday: ", Element.ALIGN_JUSTIFIED);
            AddNewRegister(document,stu_bd, Element.ALIGN_RIGHT);
            addLineSeparator(document);

            AddNewRegister(document, "Father Name: ", Element.ALIGN_JUSTIFIED);
            AddNewRegister(document,father_name, Element.ALIGN_RIGHT);
            addLineSeparator(document);

            AddNewRegister(document, "Father NRC: ", Element.ALIGN_JUSTIFIED);
            AddNewRegister(document,father_nrc, Element.ALIGN_RIGHT);
            addLineSeparator(document);

            AddNewRegister(document, "Phone No: ", Element.ALIGN_JUSTIFIED);
            AddNewRegister(document,father_ph, Element.ALIGN_RIGHT);
            addLineSeparator(document);

            AddNewRegister(document, "Address: ", Element.ALIGN_JUSTIFIED);
            AddNewRegister(document,address, Element.ALIGN_RIGHT);
            addLineSeparator(document);

            AddNewRegister(document, "Email: ", Element.ALIGN_JUSTIFIED);
            AddNewRegister(document,email, Element.ALIGN_RIGHT);
            addLineSeparator(document);

            AddNewRegister(document, "Course Name: ", Element.ALIGN_JUSTIFIED);
            AddNewRegister(document,course_name, Element.ALIGN_RIGHT);
            addLineSeparator(document);

            AddNewRegister(document, "Fees: ", Element.ALIGN_JUSTIFIED);
            AddNewRegister(document,course_fees +"Kyats", Element.ALIGN_RIGHT);
            addLineSeparator(document);

            AddNewRegister(document, "Duration: ", Element.ALIGN_JUSTIFIED);
            AddNewRegister(document,course_duration +"Months", Element.ALIGN_RIGHT);
            addLineSeparator(document);

            document.close();

            Toast.makeText(this, "Created PDF", Toast.LENGTH_LONG ).show();

            printPDF(); // printer

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void printPDF() {
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        try {
            PrintDocumentAdapter printDocumentAdapter = new PdfDocumentAdapter(PrinterActivity.this, Common.getAppPath(PrinterActivity.this) + "test_pdf.pdf");
            printManager.print("Document", printDocumentAdapter, new PrintAttributes.Builder().build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void AddNewRegister(Document document, String text, int align) throws DocumentException {
        Chunk chunk = new Chunk(text);
        Paragraph paragraph = new Paragraph(chunk);
        paragraph.setAlignment(align);
        document.add(paragraph);
    }

    private void addLineSeparator(Document document) throws DocumentException {
        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(new BaseColor(0,0,0,68));
        addLineSpace(document);
        document.add(new Chunk(lineSeparator));
        addLineSpace(document);

    }

    private void addLineSpace(Document document) throws DocumentException {
        document.add(new Paragraph(""));
    }
}