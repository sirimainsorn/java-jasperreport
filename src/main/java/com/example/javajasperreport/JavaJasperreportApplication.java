package com.example.javajasperreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.javajasperreport.model.Invoice;


@SpringBootApplication
@RestController
public class JavaJasperreportApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaJasperreportApplication.class, args);
	}

	@GetMapping("/info")
    public String info() {
      return String.format("Jasper Report use Spring boot");
    }


	@GetMapping("/invoice")
    public ResponseEntity<byte[]> getInvoiceReport() {
		try {
			// create data
			List<Invoice> list = new ArrayList<Invoice>();
			list.add(new Invoice(1, "Product 1", "1", "200.00", "200.00"));
			list.add(new Invoice(2, "Product 2", "1", "200.00", "200.00"));
			list.add(new Invoice(3, "Product 3", "2", "200.00", "400.00"));
			list.add(new Invoice(4, "Product 4", "3", "200.00", "600.00"));
			list.add(new Invoice(5, "Product 5", "2", "200.00", "400.00"));
	
			//dynamic parameters required for report
			Map<String, Object> invParams = new HashMap<String, Object>();
			invParams.put("CompanyName", "Company Name");
			invParams.put("CompanyAddress1", "123 Street Address");
			invParams.put("CompanyAddress2", "City, State, Zip/Post Code");
			invParams.put("CompanyPhone", "Phone Number");
			invParams.put("CompanyEmail", "Email Address");
			invParams.put("DateInvoice", "Date: 01/01/2020");
			invParams.put("InvoiceNumber", "Invoice: IN123456789");
			invParams.put("invoiceData", new JRBeanCollectionDataSource(list));
			
			JasperPrint empReport = JasperFillManager.fillReport(
				JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:report..jrxml").getAbsolutePath()) // path of the jasper report
				, invParams // dynamic parameters
				, new JREmptyDataSource()
			);
			
			HttpHeaders headers = new HttpHeaders();
			//set the PDF format
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "invoice.pdf");
			//create the report in PDF format
			return new ResponseEntity<byte[]>
					(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);
		} catch(Exception e) {
			System.out.println("Welcome");
			System.out.println(e);
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
