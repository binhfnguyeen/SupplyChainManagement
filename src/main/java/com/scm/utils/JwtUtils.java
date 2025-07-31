/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.utils;

import com.google.protobuf.TextFormat.ParseException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.scm.pojo.User;
import com.scm.repositories.UserRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class JwtUtils {

    private static final String SECRECT = "tpn0vLsTEgz/b3zEp/V7w00v+tsfTFV3pi9BBqk0/bUMAzCDSfzI8TLddzqeGteh";
    private static final long EXPIRATION_MS = 86400000;

    @Autowired
    private UserRepository userRepository;

    public String generateToken(String username) throws Exception {
        JWSSigner signer = new MACSigner(SECRECT);
        User u = this.userRepository.getUserByUsername(username);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .issueTime(new Date())
                .claim("roles", List.of("ROLE_" + u.getRole()))
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader(JWSAlgorithm.HS256),
                claimsSet
        );

        signedJWT.sign(signer);
        return signedJWT.serialize();
    }

    public String validateTokenAndGetUsername(String token) throws Exception {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(SECRECT);

        if (signedJWT.verify(verifier)) {
            Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (expiration.after(new Date())) {
                return signedJWT.getJWTClaimsSet().getSubject();
            }
        }
        return null;
    }

    public List<String> getRolesFromToken(String token) throws Exception {
        SignedJWT signedJWT = SignedJWT.parse(token);
        System.out.println(">>token: " + signedJWT.getJWTClaimsSet().getClaim("roles"));
        return (List<String>) signedJWT.getJWTClaimsSet().getClaim("roles");
    }
}