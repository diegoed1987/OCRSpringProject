package com.ocr.project.main;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import java.io.FileOutputStream;

import com.ocr.project.dto.OcrDataDTO;

import jakarta.annotation.PostConstruct;

@Component
public class OCRMain {
	
	@Autowired
    private Tesseract tess;
	
	private String pathFolder = Paths.get(System.getProperty("user.home"), "Documents", "Scanned Documents").toString();
	
	@PostConstruct
	public void scanImagesAndGenerateExcel() {

		File[] imageFiles = scanImages();
		
		if (imageFiles == null || imageFiles.length == 0) {
            System.out.println("No se encontraron imágenes en: " + pathFolder);
            return;
        }
		
		List<OcrDataDTO> ocrData= extractDataFromImages(imageFiles);
		createExcelFile(ocrData);
	}
	
	private File[] scanImages() {
		File folder = new File(pathFolder);
		File[] files = folder.listFiles(file -> file.isFile());
		return files;
	}
	
	private List<OcrDataDTO> extractDataFromImages(File[] listImages){
		
		List<OcrDataDTO> listOCR= new ArrayList<OcrDataDTO>();
		BufferedImage buffImg = null;
		String text = "";

		if(listImages.length > 0) {
			
			for(File file: listImages) {
				
				OcrDataDTO ocrDataDTO = new OcrDataDTO();
				
				try {
					buffImg = ImageIO.read(file);
				}catch(Exception e){
					e.getMessage();
				}
				
				try {
		            text = tess.doOCR(buffImg);
		        } catch (TesseractException e) {
		            System.err.println("Error OCR: " + e.getMessage());
		        }
				
				String[] lines = text.split("\\n");
				
				ocrDataDTO.setGasStationName(lines[1]!=null?lines[1]:"");
				ocrDataDTO.setGasStationAddress1(lines[3]!=null?lines[3]:"");
				ocrDataDTO.setPaymentDate(lines[6]!=null?lines[6]:"");
				ocrDataDTO.setTransactionNumber(lines[8]!=null?lines[8]:"");
				ocrDataDTO.setLitersFuel(lines[14]!=null?lines[14]:"");
				ocrDataDTO.setTotalPaid(lines[17]!=null?lines[17]:"");
				ocrDataDTO.setInvoiceNumber(lines[25]!=null?lines[25]:"");
				
				listOCR.add(ocrDataDTO);
			}
		}
		return listOCR;
	}
	
	private void createExcelFile(List<OcrDataDTO> listDataOCR) {
		
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Data Export");
		int rowNumber = 0;
		
		if (listDataOCR == null || listDataOCR.isEmpty()) {
            System.out.println("No hay datos OCR para exportar.");
            return;
        }
		
		Row headerRow = sheet.createRow(rowNumber);
	    headerRow.createCell(0).setCellValue("NOMBRE_ESTACION");
	    headerRow.createCell(1).setCellValue("DIRECCION_ESTACION");
	    headerRow.createCell(2).setCellValue("FECHA");
	    headerRow.createCell(3).setCellValue("NUMERO_TRANSACCION");
	    headerRow.createCell(4).setCellValue("LITROS_GAS");
	    headerRow.createCell(5).setCellValue("TOTAL_PAGADO");
	    headerRow.createCell(6).setCellValue("NUMERO_FACTURA");
	    
	    for (OcrDataDTO dataOcr : listDataOCR) {
	        Row row = sheet.createRow(rowNumber++);

	        row.createCell(0).setCellValue(dataOcr.getGasStationName());
	        row.createCell(1).setCellValue(dataOcr.getGasStationAddress1());
	        row.createCell(2).setCellValue(dataOcr.getPaymentDate());
	        row.createCell(3).setCellValue(dataOcr.getTransactionNumber());
	        row.createCell(4).setCellValue(dataOcr.getLitersFuel());
	        row.createCell(5).setCellValue(dataOcr.getTotalPaid());
	        row.createCell(6).setCellValue(dataOcr.getInvoiceNumber());
	    }
	    
	    String outputPath = Paths.get(System.getProperty("user.home"), "Desktop", "exported_data.xlsx").toString();
        try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
            workbook.write(outputStream);
            workbook.close();
            System.out.println("Archivo Excel creado en: " + outputPath);
        } catch (IOException e) {
        	System.err.println("Error creando archivo Excel: " + e.getMessage());
        }		
	}
}
