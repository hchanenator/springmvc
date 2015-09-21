/**
 * 
 */
package com.packt.webstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author herb
 *
 */
public class PromoCodeInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	private String promoCode;
	private String errorRedirect;
	private String offerRedirect;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final String givenPromoCode = null == request.getParameterValues("promo") ? "" : request.getParameterValues("promo")[0];
		
		if (request.getRequestURI().endsWith("products/specialOffer")) {
			if (givenPromoCode.equals(promoCode)) {
				logger.info(request.getRemoteUser() + "submitted matched promoCode: " + promoCode);
				response.sendRedirect(request.getContextPath() + "/" + offerRedirect);
			} else {
				logger.info(request.getRemoteUser() + " submitted promoCode[" + promoCode + "].  Does not match.");
				response.sendRedirect(errorRedirect);
			}
			return false;
		}
		return true;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getErrorRedirect() {
		return errorRedirect;
	}

	public void setErrorRedirect(String errorRedirect) {
		this.errorRedirect = errorRedirect;
	}

	public String getOfferRedirect() {
		return offerRedirect;
	}

	public void setOfferRedirect(String offerRedirect) {
		this.offerRedirect = offerRedirect;
	}
	
}
