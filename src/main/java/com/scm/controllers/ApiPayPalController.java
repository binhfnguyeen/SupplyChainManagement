/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.LinkDescription;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCaptureRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.PurchaseUnitRequest;
import com.scm.paypal.PayPalClient;
import com.scm.pojo.Donhangxuat;
import com.scm.services.DonHangXuatService;
import com.scm.services.HoaDonXuatService;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/api/paypal")
public class ApiPayPalController {

    @Autowired
    private DonHangXuatService dhxService;
    
    @Autowired
    private HoaDonXuatService hdxService;

//    @GetMapping("/create-order")
//    public RedirectView createOrder() throws IOException {
//        OrdersCreateRequest request = new OrdersCreateRequest();
//        request.header("prefer", "return=representation");
//        request.requestBody(new OrderRequest()
//                .checkoutPaymentIntent("CAPTURE")
//                .purchaseUnits(Arrays.asList(new PurchaseUnitRequest()
//                        .amountWithBreakdown(new AmountWithBreakdown()
//                                .currencyCode("USD")
//                                .value("10.00")
//                        )))
//                .applicationContext(new ApplicationContext()
//                        .returnUrl("http://localhost:8080/paypal/capture")
//                        .cancelUrl("http://localhost:8080/paypal/cancel")
//                )
//        );
//
//        Order order = PayPalClient.client.execute(request).result();
//        for (LinkDescription link : order.links()) {
//            if (link.rel().equals("approve")) {
//                return new RedirectView(link.href()); // Chuyển hướng người dùng đến PayPal
//            }
//        }
//
//        return new RedirectView("/error");
//    }

    @GetMapping("/capture")
    @ResponseBody
    public String capture(@RequestParam("token") String token, @RequestParam("orderId") Long orderId) throws IOException {
        OrdersCaptureRequest request = new OrdersCaptureRequest(token);
        request.requestBody(new OrderRequest());

        Order order = PayPalClient.client.execute(request).result();
        System.out.println("ID đơn hàng: " + orderId);

        // 🔸 Bạn có thể lưu trạng thái thanh toán vào DB tại đây
        // cập nhật đơn hàng: đơn đã thanh toán, trạng thái = "Đã thanh toán"
//        donHangXuatService.updateTrangThai(orderId, "Đã thanh toán");
        Donhangxuat dhx=this.dhxService.getDonhangxuatById(orderId.intValue());
        dhx.setTinhTrang("Đã thanh toán");
        this.dhxService.addOrUpdateDonHangXuat(dhx);
        
        hdxService.addHoaDonXuat(dhx);
        

        return "✅ Đơn hàng #" + orderId + " đã được thanh toán thành công. PayPal Order ID: " + order.id();
    }

    @GetMapping("/cancel")
    @ResponseBody
    public String cancel() {
        return "Thanh toán đã bị hủy.";
    }

    @GetMapping("/create-payment/{idDonHang}")
    public RedirectView createPaymentFromOrder(@PathVariable ("idDonHang") int idDonHang) throws IOException {
        // 🔸 Bước 1: Lấy thông tin đơn hàng xuất từ DB
        Donhangxuat donHang = dhxService.getDonhangxuatById(idDonHang);
        if (donHang == null) {
            return new RedirectView("/error?msg=Đơn hàng không tồn tại");
        }

        // 🔸 Bước 2: Chuyển đổi tiền từ VND → USD
        double rateVNDToUSD = 25000.0; // bạn có thể gọi API để lấy tỷ giá thật
        double amountUSD = donHang.getTongTien().doubleValue() / rateVNDToUSD;

        // 🔸 Bước 3: Tạo đơn hàng PayPal
        OrdersCreateRequest request = new OrdersCreateRequest();
        request.header("prefer", "return=representation");
//        Object[] amountUSD;

        request.requestBody(new OrderRequest()
                .checkoutPaymentIntent("CAPTURE")
                .purchaseUnits(Arrays.asList(
                        new PurchaseUnitRequest()
                                .referenceId("ORDER_" + donHang.getId())
                                .description("Thanh toán đơn hàng xuất #" + donHang.getId())
                                .amountWithBreakdown(new AmountWithBreakdown()
                                        .currencyCode("USD")
                                        .value(String.format("%.2f", amountUSD))
                                )))
                .applicationContext(new ApplicationContext()
                        .returnUrl("http://localhost:8080/SupplyChainManagement/api/paypal/capture?orderId=" + donHang.getId())
                        .cancelUrl("http://localhost:8080/SupplyChainManagement/api/paypal/cancel")
                )
        );

        // 🔸 Bước 4: Gửi yêu cầu đến PayPal
        Order order = PayPalClient.client.execute(request).result();

        for (LinkDescription link : order.links()) {
            if (link.rel().equals("approve")) {
                return new RedirectView(link.href());
            }
        }

        return new RedirectView("/error?msg=Không tạo được đơn hàng PayPal");
    }
}
