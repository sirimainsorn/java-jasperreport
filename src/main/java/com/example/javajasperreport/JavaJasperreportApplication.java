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
			list.add(new Invoice(1, "Trucking", "1.00", "0.00000", "0.00"));
	
			//dynamic parameters required for report
			Map<String, Object> invParams = new HashMap<String, Object>();
			invParams.put("CompanyName", "RCL LOGISTICS COMPANY LIMITED ( Head Office )");
			invParams.put("CompanyAddress1", "127/18 PANJATHANI TOWER 14th FLOOR NONSEE ROAD,");
			invParams.put("CompanyAddress2", "CHONGNONSEE, YANNAWA, BANGKOK 10120");
			invParams.put("CompanyAddress3", "(TAX ID: 0105544033446) TEL NO. (02) 296-1288 FAX NO. (02) 296-1279");
			invParams.put("invoiceData", new JRBeanCollectionDataSource(list));
			
			JasperPrint empReport = JasperFillManager.fillReport(
				JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:report..jrxml").getAbsolutePath()) // path of the jasper report
				, invParams // dynamic parameters
				, new JREmptyDataSource()
			);
			
			HttpHeaders headers = new HttpHeaders();
			//set the PDF format
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "employees-details.pdf");
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
