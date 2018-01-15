package vn.hcmute.core.service.impl;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.hcmute.core.dto.Mail;
import vn.hcmute.core.service.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration freeMarkerConfiguration;

    @Transactional
    public void sendEmail(Mail mail, String template) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(mail.getMailFrom());
            mimeMessageHelper.setTo(mail.getMailTo());
            mail.setMailContent(geContentFromTemplate(mail.getModel(), template));
            mimeMessageHelper.setText(mail.getMailContent(), true);
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String geContentFromTemplate(Map<String, Object> params, String template) {
        try {
            final StringWriter content = new StringWriter();
            StringTemplateLoader templateLoader = new StringTemplateLoader();
            templateLoader.putTemplate("template", template);
            freeMarkerConfiguration.setTemplateLoader(templateLoader);
            freeMarkerConfiguration.getTemplate("template", "UTF-8").process(params, content);
            return content.toString();
        } catch (TemplateException e) {
        } catch (IOException e) {
        }
        return StringUtils.EMPTY;
    }
}
