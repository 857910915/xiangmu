package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.User;
import conn.MySQLConnection;

public class UserDao extends MySQLConnection{
	//获取用户列表
	public List<User> getUser() {
		List<User>userList=new ArrayList<User>();
		User user=new User();
		String sql="SELECT * FROM user";
		try {
			ResultSet rs=this.queryInfo(sql, null);
			while (rs.next()) {
				user.setUsername(rs.getString("username"));
				user.setUserpwd(rs.getNString("userpwd"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.connectionClose();
		}
		return userList;		
	}
	//根据用户名获取用户
	public User getUserByName(String username) {
		User user=null;
		String sql="SELECT * FROM usre WHERE username=?";
		try {
			ResultSet rs=this.queryInfo(sql, new String[]{username});
			if (rs.next()) {
				user=new User();
				user.setUsername(rs.getString("username"));
				user.setUserpwd(rs.getString("userpwd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.connectionClose();
		}
		
		return user;			
	}
	//修改用户信息
	public boolean editUser(User user) {
		boolean r = false;
        String sql = "UPDATE user SET userpwd = ? WHERE username = ?";
        try{
            int num = this.updataInfo(sql, new String[]{user.getUserpwd(),user.getUsername()});
            if(num > 0){
                r = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.connectionClose();
        }
        return r;
	}
	//添加用户
	public boolean addUser(User user) {
		boolean r=false;
		String sql="INSERT INTO user(username,userpwd)VALUES(?,?)";	
		try{			
            int num=this.updataInfo(sql, new String[]{user.getUsername(),user.getUserpwd()});
            if(num > 0){
                r = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.connectionClose();
        }
        return r;	
	}
	//删除用户
	public boolean deleteUserByName(String username) {
		boolean r=false;
		String sql="DELETE FROM user WHERE username=?";
		try {
			int num=this.updataInfo(sql,new String[]{username});
			if (num>0) {
				r=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			this.connectionClose();
		}
		return r;
	}
}
