package apelsin.controller;

import apelsin.payload.ApiResponse;
import apelsin.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/getInvoiceExpireDate")
    public HttpEntity<?> getInvoiceExpireDate() {
        ApiResponse invoiceExpireDate = invoiceService.getInvoiceExpireDate();
        return ResponseEntity.ok(invoiceExpireDate);
    }

    @GetMapping("/getInvoiceWrongDate")
    public HttpEntity<?> getInvoiceWrongDate() {
        ApiResponse invoicewrongDate = invoiceService.getInvoiceWrongDate();
        return ResponseEntity.ok(invoicewrongDate);
    }

    @GetMapping("/overInvoicePaid")
    public HttpEntity<?> getInvoiceOverPaid() {
        ApiResponse apiResponse = invoiceService.overPaid();
        return ResponseEntity.ok(apiResponse);
    }
}
