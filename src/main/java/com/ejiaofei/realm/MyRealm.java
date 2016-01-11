package com.ejiaofei.realm;

import com.ejiaofei.common.DbUtil;
import com.ejiaofei.common.dao.UserDao;
import com.ejiaofei.common.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.sql.Connection;

/**
 * Created by dingxin on 2016/1/3.
 */
public class MyRealm extends AuthorizingRealm {
    private UserDao userDao = new UserDao();
    private DbUtil dbUtil = new DbUtil();



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo =  new SimpleAuthorizationInfo();
        Connection conn = null;
        try {
            conn = dbUtil.getConn();
            simpleAuthorizationInfo.setRoles(userDao.getRoles(conn,userName));
            simpleAuthorizationInfo.setStringPermissions(userDao.getPermission(conn, userName));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeConn(conn);
        }
        return simpleAuthorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String)authenticationToken.getPrincipal();
        Connection conn = null;
        try {
            conn = dbUtil.getConn();
            User user = userDao.getByUserName(conn,userName);
            if (user != null) {
                AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),"");
                return authenticationInfo;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeConn(conn);
        }
        return null;
    }
}
