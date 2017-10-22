package com.taotao.manage.interceptor.shiro;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 1、subject(主体)请求验证，调用subject.login(token)
 * 2、SecurityManager(安全验证器)执行验证
 * 3、SecurityManager通过ModularRealmAuthenticator进行验证
 * 4、ModularRealmAuthenticator将token传给realm，realm根据token中用户信息从数据库查询用户信息(包括身份和凭证)
 * 5、realm如果查询不到用户给ModularRealmAuthenticator放回null，ModularRealmAuthenticator抛出异常(用户不存在)
 * 6、realm如果查到用户给ModularRealmAuthenticator返回AuthenticatorInfo(验证信息)
 *
 */
public class ShiroRealm extends AuthorizingRealm {

	/*
	 * 登录信息和用户验证信息验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String username = (String)token.getPrincipal();  				//得到用户名
		String password = new String((char[])token.getCredentials()); 	//得到密码
		//password="999";



	     if(null != username){
	    	 return new SimpleAuthenticationInfo(username, password, getName());
	     }else{
	    	 return null;
	     }
	}
	
	/*
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		System.out.println("========2");
		return null;
	}

}
