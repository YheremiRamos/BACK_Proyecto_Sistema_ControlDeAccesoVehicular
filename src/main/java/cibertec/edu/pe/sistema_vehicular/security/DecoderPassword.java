package cibertec.edu.pe.sistema_vehicular.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DecoderPassword {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = "sistema2023";
        String encodedPassword = "$2a$10$Z7/zwEFEV3L14ghsKapFj.tZKLiBvqCR84PwN9jG/bZA8ePDIUqru";

        boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
        System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);

    }

}
