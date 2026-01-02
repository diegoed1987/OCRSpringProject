# OCR Spring Project

## Description
This project is a personal **Spring Boot** application that processes scanned images to extract data from ESSO gas station receipts using **Tesseract OCR** and exports the information to an **Excel file**.  

**Main features:**
- Automatically scans images from a specified folder.
- Extracts key information:
  - Gas station name
  - Address
  - Payment date
  - Transaction number
  - Fuel liters
  - Total paid
  - Invoice number
- Generates an Excel file with all extracted data.
- Built with Spring best practices: Tesseract beans, null-safe handling, dynamic paths, and basic logging.

---

## Technologies Used
- Java 17+
- Spring Boot
- Tesseract OCR (via [Tess4J](https://github.com/nguyenq/tess4j))
- Apache POI (for Excel generation)
- Maven for dependency management
