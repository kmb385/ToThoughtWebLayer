package org.tothought.spring.utilities;

import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RecaptchaService {

	private static final String CAPTCHA_ERROR_MSG = "Token entered did not match the displayed message.  Please try again.";

	@Value("${recaptcha.public}")
	private String publicKey;

	@Value("${recaptcha.private}")
	private String privateKey;

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getErrorMsg() {
		return CAPTCHA_ERROR_MSG;
	}

	public String getRecaptcha() {
		ReCaptcha recaptcha = ReCaptchaFactory.newReCaptcha(publicKey, privateKey, false);
		return recaptcha.createRecaptchaHtml(null, null);
	}

	public boolean isValid(HttpServletRequest request) {
		ReCaptcha recaptcha = ReCaptchaFactory.newReCaptcha(publicKey, privateKey, false);
		ReCaptchaResponse response = recaptcha.checkAnswer(request.getRemoteAddr(),
				request.getParameter("recaptcha_challenge_field"),
				request.getParameter("recaptcha_response_field"));
		return response.isValid();
	}

}
