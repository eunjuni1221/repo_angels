package com.angels.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.angels.common.interceptor.CheckLoginSessionInterceptor;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CheckLoginSessionInterceptor())
//				.order(1)
				.addPathPatterns("/*/*Xdm*", "/*/*/*/*Usr*")
				.excludePathPatterns(
//						"/resources/**",
						"/static/**",
//						"/v1/infra/member"
//						+ "/signUpUsrForm",
//						"/v1/infra/member/signinUsrForm",
						"/login/signinXdmProc",
//						"/v1/infra/member/signupIdCheckUsrProc",
//						"/v1/infra/member/sendMailGoogleCertificationUsrProc",
//						"/v1/infra/member/certificationCheckUsrProc",
//						"/v1/infra/member/memberUsrInst",
//						"/v1/infra/member/findIdUsrForm",
//						"/v1/infra/member/findPwdUsrForm",
//						"/v1/infra/index/indexUsrView",
//						"/v1/infra/member/findIdUsrProc",
//						"/v1/infra/member/modalTermsUsrView",
//						"/v1/infra/member/changPwdUsrProc",
//						"/v1/infra/member/withdrawUsrProc",
//						"/v1/mallbicycle/major/majorShowUsrAjaxList",
//						"/v1/mallbicycle/major/majorShowUsrLita",
//						"/v1/mallbicycle/major/majorShowUsrView",
//						"/adt/**",
//						"/xdm/**",
//						"/v1/infra/member/signupXdmForm",
//						"/v1/infra/member/signinXdmForm",
//						"/v1/infra/member/signinXdmProc",
						"/login/idNmCheckXdmProc"

		);
	}

}
