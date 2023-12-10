package com.okbetltd.bets.registration;

import com.okbetltd.bets.appuser.AppUser;
import com.okbetltd.bets.appuser.AppUserRole;
import com.okbetltd.bets.appuser.AppUserService;
import com.okbetltd.bets.registration.token.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getUsername(),
                request.getPassword()
                )
        );
    }

//    @Transactional
//    public String confirmToken(String token) {
//        ConfirmationToken confirmationToken = confirmationTokenService
//                .getToken(token)
//                .orElseThrow(() ->
//                        new IllegalStateException("token not found"));
//
//        if (confirmationToken.getConfirmedAt() != null) {
//            throw new IllegalStateException("email already confirmed");
//        }
//
//        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
//
//        if (expiredAt.isBefore(LocalDateTime.now())) {
//            throw new IllegalStateException("token expired");
//        }
//
//        confirmationTokenService.setConfirmedAt(token);
//        appUserService.enableAppUser(
//                confirmationToken.getAppUser().getEmail());
//        return "confirmed";
//    }
}
