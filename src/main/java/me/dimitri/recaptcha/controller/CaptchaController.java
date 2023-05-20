package me.dimitri.recaptcha.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import me.dimitri.recaptcha.service.CaptchaService;

@Controller()
public class CaptchaController {

    private final CaptchaService captchaService;

    @Inject
    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @Post("/verify")
    public HttpResponse<?> verify(@Body String siteKey) {
        return captchaService.handleCaptcha(siteKey);
    }

}
