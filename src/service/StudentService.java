package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.Student;
import conn.MySQLConnection;

public class StudentService extends MySQLConnection{
	private Student stu;
	//获取学生信息
	public List<Student> getStudents() {
		List<Student>stuList=new ArrayList<Student>();
		
		String sql="SELECT * FROM studentinfo";	
		try {
			ResultSet rs= (ResultSet)this.queryInfo(sql, null);
			while (rs.next()) {
				Student stu=new Student();
				stu.setSid(rs.getString("sid"));
				stu.setSname(rs.getString("sname"));
				stu.setSsex(rs.getString("ssex"));
				stu.setSage(rs.getString("sage"));
				stu.setSmajor(rs.getString("smajor"));
				stu.setSphone(rs.getString("sphone"));
				stuList.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.connectionClose();
		}
		return stuList;	
	}
	
	//根据ID获取学生信息
	public Student getStuById(String sid) {
		stu = null;
		String sql="SELECT * FROM studentinfo WHERE sid=?";	
		try {
			ResultSet rs=this.queryInfo(sql, new String[]{sid});
			while (rs.next()) {
				stu=new Student();
				stu.setSid(rs.getString("sid"));
				stu.setSname(rs.getString("sname"));
				stu.setSsex(rs.getString("ssex"));
				stu.setSage(rs.getString("sage"));
				stu.setSmajor(rs.getString("smajor"));
				stu.setSphone(rs.getString("sphone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.connectionClose();
		}
		return stu;
	}
	
	//修改学生信息
	public boolean editStuInfo(Student stu) {
		boolean r = false;
        String sql = "UPDATE studentinfo SET sname = ?,ssex=?,sage=?,smajor=?,sphone=? WHERE sid = ?";
        try{
            int num = this.updataInfo(sql, new String[]{stu.getSname(),stu.getSsex(),stu.getSage(),stu.getSmajor(),stu.getSphone(),stu.getSid()});
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
	//添加学生信息
	public boolean addStuInfo(Student stu) {
		boolean r=false;
		String sql="INSERT INTO studentinfo(sname,ssex,sage,smajor,sphone)VALUES(?,?,?,?,?)";
		try{
            int num = this.updataInfo(sql, new String[]{stu.getSname(),stu.getSsex(),stu.getSage(),stu.getSmajor(),stu.getSphone()});
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
	//删除信息
		public boolean deleteStuById(String sid) {
			boolean r=false;
			String sql="DELETE FROM studentinfo WHERE sid=?";
			try {
				int num=this.updataInfo(sql,new String[]{sid});
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
