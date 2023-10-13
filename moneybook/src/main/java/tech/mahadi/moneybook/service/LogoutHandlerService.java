package tech.mahadi.moneybook.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import tech.mahadi.moneybook.repository.TokenRepository;

@Service
@RequiredArgsConstructor
public class LogoutHandlerService implements LogoutHandler {
    private final TokenRepository tokenRepository;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String header = request.getHeader("authorization");
        if(header == null && !header.startsWith("Bearer ")){
            return;
        }
        final String jwtToken = header.substring(7);
        if(tokenRepository.existsByToken(jwtToken)){
            tokenRepository.deleteByToken(jwtToken);
        }
    }
}
