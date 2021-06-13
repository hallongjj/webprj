package com.newlecture.web.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@Service
public class JDBCNoticeService implements NoticeService {

//    private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
//    private String driver = "oracle.jdbc.driver.OracleDriver";
//    private String uid = "NEWLEC";
//    private String pwd = "1111";

    @Autowired
    private DataSource dataSource;
    
    public List<Notice> getList(int page, String field, String query) throws ClassNotFoundException, SQLException {

        int start = 1 + (page - 1) * 5;
        int end = 5 * page;

        String sql = "select * from notice_view where "+field+" like ? and num between ? and ?";

//        Class.forName(driver);
//        Connection con = DriverManager.getConnection(url, uid, pwd);
        Connection con = dataSource.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, "%"+query+"%");
        st.setInt(2, start);
        st.setInt(3, end);
        ResultSet rs = st.executeQuery();

        List<Notice> list = new ArrayList<Notice>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String writerId = rs.getString("writer_id");
            Date regDate = rs.getDate("regdate");
            String content = rs.getString("content");
            String files = rs.getString("files");
            int hit = rs.getInt("hit");

            Notice notice = new Notice(id, title, writerId, regDate, content, files, hit);

            list.add(notice);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // Scalar값 = 단위값
    public int getCount() throws SQLException, ClassNotFoundException {
        int count = 0;

        String sql = "select COUNT(ID) COUNT FROM NOTICE";

//        Class.forName(driver);
//        Connection con = DriverManager.getConnection(url, uid, pwd);
        Connection con = dataSource.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        List<Notice> list = new ArrayList<Notice>();
        
        if(rs.next()) {
            count = rs.getInt("COUNT");
        }

        rs.close();
        st.close();
        con.close();

        return count;
    }

    public int insert(Notice notice) throws ClassNotFoundException, SQLException {

        String title = notice.getTitle();
        String writerId = notice.getWriterId();
        String content = notice.getContent();
        String files = notice.getFiles();

        String sql = "INSERT INTO notice (" + "    title," + "    writer_id," + "    content," + "    files"
                + ") VALUES (?,?,?,?)";

//        Class.forName(driver);
//        Connection con = DriverManager.getConnection(url, uid, pwd);
        Connection con = dataSource.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, title);
        st.setString(2, writerId);
        st.setString(3, content);
        st.setString(4, files);

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result;

    }

    public int update(Notice notice) throws ClassNotFoundException, SQLException {

        String title = notice.getTitle();
        String content = notice.getContent();
        String files = notice.getFiles();
        int id = notice.getId();

        String sql = "UPDATE notice " + "SET" + "    title = ?," + "     content = ?," + "     files=?" + "     "
                + "WHERE" + "    id = ?";

//        Class.forName(driver);
//        Connection con = DriverManager.getConnection(url, uid, pwd);
        Connection con = dataSource.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, title);
        st.setString(2, content);
        st.setString(3, files);
        st.setInt(4, id);

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result;
    }

    public int delete(int id) throws ClassNotFoundException, SQLException {

        String sql = "DELETE NOTICE WHERE id = ?";

//        Class.forName(driver);
//        Connection con = DriverManager.getConnection(url, uid, pwd);
        Connection con = dataSource.getConnection();
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, id);

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result;
    }

}
