package vn.hcmute.core.service;


import vn.hcmute.core.dto.Mail;

public interface MailService {
    void sendEmail(Mail mail, String template);
}