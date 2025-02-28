package org.sahni.services.impl;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.sahni.services.MailerService;

import static org.sahni.commons.Constants.USER_OTP_EMAIL_BODY;
import static org.sahni.commons.Constants.USER_OTP_EMAIL_SUBJECT;

@ApplicationScoped
public class MailerServiceImpl implements MailerService {

    @Inject
    ReactiveMailer reactiveMailer;

    @Override
    public Uni<Void> sendUserVerificationMail(String emailID, String code) {
        return reactiveMailer
                .send(Mail
                        .withHtml(
                                emailID,
                                USER_OTP_EMAIL_SUBJECT,
                                USER_OTP_EMAIL_BODY.replace("{}", code))
                );
    }
}
