package com.ejiaofei.common.dao;

import com.ejiaofei.common.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dingxin on 2016/1/3.
 */
public class UserDao {
    public User getByUserName(Connection conn,String userName) throws Exception {
        User user = null;
        String sql = "select * from t_user where userName = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,userName);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("userName"));
            user.setPassword(rs.getString("password"));
        }
        return user;
    }


    public Set<String> getRoles(Connection conn,String userName) throws Exception  {
        Set<String> roles = new HashSet<String>();
        String sql = "select * from t_user a,t_role b where a.roleId = b.id and userName = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,userName);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()) {
            roles.add(rs.getString("roleName"));
        }
        return roles;
    }
    public Set<String> getPermission(Connection conn,String userName) throws Exception {
        Set<String> permissons = new HashSet<String>();
        String sql = "select * from t_user a,t_role b,t_permission c where a.roleId = b.id and c.roleId = b.id  and userName = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,userName);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()) {
            permissons.add(rs.getString("permissionName"));
        }
        return permissons;
    }

}
