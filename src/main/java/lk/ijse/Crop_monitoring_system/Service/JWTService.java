package lk.ijse.Crop_monitoring_system.Service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String extractUserName(String token);
    String generateToken(UserDetails user);
    boolean validateToken(String token,UserDetails userDetails);
    String refreshToken(UserDetails userDetails);
}
