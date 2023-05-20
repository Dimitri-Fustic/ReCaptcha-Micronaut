package me.dimitri.recaptcha.service;


import io.micronaut.http.HttpResponse;
import jakarta.inject.Singleton;
import me.dimitri.recaptcha.util.VerifyCaptcha;

@Singleton
public class CaptchaService {

    public HttpResponse<?> handleCaptcha(String siteKey) {
        if (!VerifyCaptcha.isValid(siteKey)) {
            return HttpResponse.badRequest().body("Captcha Failed");
        }
        return HttpResponse.ok().body("Captcha Success");
    }

}
