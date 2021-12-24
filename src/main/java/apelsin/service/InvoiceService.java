package apelsin.service;

import apelsin.entity.Invoice;
import apelsin.payload.ApiResponse;
import apelsin.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;

    public ApiResponse getInvoiceExpireDate() {
        List<Invoice> allInvoiceIssued = invoiceRepository.getAllInvoiceIssued();
        return new ApiResponse("Success", true, allInvoiceIssued);
    }

    public ApiResponse getInvoiceWrongDate() {
        List<?> invoiceWrongDate = invoiceRepository.getInvoiceWrongDate();
        return new ApiResponse("Succes", true, invoiceWrongDate);
    }

    public ApiResponse overPaid() {
        List<?> overallInvoicePaid = invoiceRepository.getOverallInvoicePaid();
        return new ApiResponse("Success", true, overallInvoicePaid);
    }
}
