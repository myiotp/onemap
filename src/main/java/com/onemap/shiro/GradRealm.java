/**
 * 
 */
package com.onemap.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.onemap.domain.User;
import com.onemap.service.UserService;

/**
 * @author junpingwang
 * 
 */
public class GradRealm extends AuthorizingRealm {
	public static final String ADMIN = "admin";
	public static final String level1 = "level1";
	public static final String level2 = "level2";
	public static final String level3 = "level3";
	public static final String level4 = "level4";
	public static final String level5 = "level5";
	public static final String level6 = "level6";
	
	@Autowired
	private UserService userService;

	public GradRealm() {
		super();
		// 设置认证token的实现类
		setAuthenticationTokenClass(UsernamePasswordToken.class);
		// 设置加密算法
		setCredentialsMatcher(new HashedCredentialsMatcher(
				Md5Hash.ALGORITHM_NAME));

	}

	// 授权
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principalCollection) {
		User loginUser = (User) principalCollection.fromRealm(getName())
				.iterator().next();
		if (loginUser== null) {
			return null;
		}
		SimpleAuthorizationInfo result = new SimpleAuthorizationInfo();

		if(loginUser.getUsername().equals("admin")) {
			result.addRole(ADMIN);
		} else {
			int approverole = loginUser.getApproverole();
			result.addRole("approverole"+approverole);
		}
		

		return result;

	}

	// 认证
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		String password = new String(upToken.getPassword());
		try {
			User user = userService.getByUsername(username);
			if (user != null) {
				String password2 = user.getPassword();
//				System.out.println("password:"+password);
//				System.out.println("password2:"+password2);
				
				Md5Hash hash = new Md5Hash(password);
				//System.out.println("hash.toHex():" + hash.toHex());
				if(hash.toHex().equals(password2)) {
					return new SimpleAuthenticationInfo(user,
							password2, getName());
				} else {
					throw new IncorrectCredentialsException(); 
				}
				
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}