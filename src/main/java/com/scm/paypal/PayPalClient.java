/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.paypal;

/**
 *
 * @author Admin
 */
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
public class PayPalClient {
    private static final String CLIENT_ID = "AVWMIgje4DokV20FBQMA4K4342piZKJXpqCvwhlchvAoP_2Ag7evD1LLOAKcRUtDjNOv16s9nV-osDx8";
    private static final String CLIENT_SECRET = "EGrb40lPnYHbOw7uSLJJ5PTBXCPacmb6rC6PCEB7UZl3rECC3fhe5vyZXfcJZAcKfYef6t6uhluDnFGz";

    private static final PayPalEnvironment environment =
        new PayPalEnvironment.Sandbox(CLIENT_ID, CLIENT_SECRET);

    public static final PayPalHttpClient client = new PayPalHttpClient(environment);
}
