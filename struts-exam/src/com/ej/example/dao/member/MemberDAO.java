package com.ej.example.dao.member;

import com.ej.example.dao.DAOSupport;
import com.ej.example.datasource.member.MemberConnectionManager;
import com.ej.example.domain.MemberDTO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MemberDAO extends MemberConnectionManager implements DAOSupport<MemberDTO> {

    public List<MemberDTO> selectList(int page, int rowCount) throws SQLException {
        String query = "SELECT * FROM MEMBER ORDER BY CREATE_DATE DESC LIMIT ?, ?";
        return executeQuery(query, page, rowCount);
    }

    public int selectCount() throws SQLException {
        String query = "SELECT COUNT(*) FROM MEMBER";
        return executeUpdate(query);
    }

    public MemberDTO selectOne(int seq) throws SQLException {
        String query = "SELECT * FROM MEMBER WHERE SEQ = ? ";
        List<MemberDTO> dtoList = executeQuery(query, seq);
        return dtoList != null && dtoList.size() > 0 ? dtoList.get(0) : null;
    }

    public int insert(MemberDTO dto) throws SQLException {
        String query = "INSERT INTO test.member(  CREATE_DATE,EMAIL,NAME,PASSWORD,USER_ID) VALUES ( ?,?,?,?,?)";
        return executeUpdate(query, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()), dto.getEmail(), dto.getName(), dto.getPassword(), dto.getUserId());
    }

    public int update(MemberDTO dto) throws SQLException {
        String query = "UPDATE test.member SET  EMAIL = ?, NAME = ?, PASSWORD = ?, USER_ID = ? WHERE SEQ = ?";
        return executeUpdate(query, dto.getEmail(), dto.getName(), dto.getPassword(), dto.getUserId(), dto.getSeq());
    }

    public int delete(int seq) throws SQLException {
        String query = "DELETE FROM test.member WHERE SEQ = ?";
        return executeUpdate(query, seq);
    }
}
