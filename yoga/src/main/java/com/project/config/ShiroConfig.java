package com.project.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.realm.MyRealm;

/**
 * shiro配置
 * @author Administrator
 *
 */
@Configuration
public class ShiroConfig {
	/**
	 * 定义shiro核心过滤器
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		
		//注入安全管理器
		shiroFilter.setSecurityManager(securityManager);
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("/html/coach/coach.html", "authc");
		map.put("/logout", "logout");
		map.put("/**", "anon");
		shiroFilter.setFilterChainDefinitionMap(map);
		return shiroFilter;
	}
	
	/**
	 * 配置realm到安全管理器，并加入安全管理器到容器
	 * @param myRealm
	 * @return
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(MyRealm myRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myRealm);
		return securityManager;
	}
	
	/**
	 * 使用md5来进行密码加密
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		hashedCredentialsMatcher.setHashIterations(1024);
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}
	
	/**
	 * 将自定义realm加入容器
	 * @param hashedCredentialsMatcher
	 * @return
	 */
	@Bean
	public MyRealm myRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
		MyRealm userRealm = new MyRealm();
		userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
		return userRealm;
	}
	
	
	/**
	 * 暂时不太清楚这段代码具体干啥，网上搜的
	 * @param securityManager
	 * @return
	 */
	@Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
