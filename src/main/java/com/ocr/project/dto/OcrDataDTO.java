package com.ocr.project.dto;

public class OcrDataDTO {

	private String gasStationName;
	private String gasStationAddress1;
	private String gasStationAddress2;
	private String paymentDate;
	private String transactionNumber;
	private String litersFuel;
	private String totalPaid;
	private String invoiceNumber;
	
	public OcrDataDTO() {
		
	}
	
	public String getGasStationName() {
		return gasStationName;
	}
	public void setGasStationName(String gasStationName) {
		this.gasStationName = gasStationName;
	}
	public String getGasStationAddress1() {
		return gasStationAddress1;
	}
	public void setGasStationAddress1(String gasStationAddress1) {
		this.gasStationAddress1 = gasStationAddress1;
	}
	public String getGasStationAddress2() {
		return gasStationAddress2;
	}
	public void setGasStationAddress2(String gasStationAddress2) {
		this.gasStationAddress2 = gasStationAddress2;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public String getLitersFuel() {
		return litersFuel;
	}
	public void setLitersFuel(String litersFuel) {
		this.litersFuel = litersFuel;
	}
	public String getTotalPaid() {
		return totalPaid;
	}
	public void setTotalPaid(String totalPaid) {
		this.totalPaid = totalPaid;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Override
	public String toString() {
		return "OcrData [gasStationName=" + gasStationName + ", gasStationAddress1=" + gasStationAddress1
				+ ", gasStationAddress2=" + gasStationAddress2 + ", paymentDate=" + paymentDate + ", transactionNumber="
				+ transactionNumber + ", litersFuel=" + litersFuel + ", totalPaid=" + totalPaid + ", invoiceNumber="
				+ invoiceNumber + "]";
	}
	
	
}
