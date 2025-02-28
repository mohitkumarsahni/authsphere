package org.sahni.services;

import io.smallrye.mutiny.Uni;

public interface MailerService {

    Uni<Void> sendUserVerificationMail(String emailID, String code);
}
