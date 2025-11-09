package com.otp.validation.storage;

import java.time.LocalDateTime;

public record OTPData(String otp, LocalDateTime expiry) {
}
