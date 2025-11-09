package com.otp.validation.storage;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryOTPStore {

    private final Map<String, OTPData> store = new ConcurrentHashMap<>();

    public void saveOTP(String userId, String otp, LocalDateTime expiry) {
        store.put(userId, new OTPData(otp, expiry));
    }

    public boolean verifyOTP(String userId, String otp) {
        OTPData data = store.get(userId);
        if (data == null) return false;
        if(LocalDateTime.now().isAfter(data.expiry())) return false;
        return data.otp().equals(otp);
    }

    public void removeOTP(String userId) {
        store.remove(userId);
    }
}
