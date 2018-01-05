package com.ej.example.datasource.member;

import com.ej.example.datasource.ConnectionManager;
import com.ej.example.domain.MemberDTO;

import java.sql.SQLException;
import java.util.List;

public class MemberConnectionManager extends ConnectionManager<MemberDTO> {

    protected MemberDTO createDTO(Class<MemberDTO> cls) throws SQLException {
        MemberDTO dto = null;
        try {

            dto = cls.newInstance();
            dto.setSeq(rs.getInt("SEQ"));
            dto.setCreateDate(rs.getDate("CREATE_DATE"));
            dto.setEmail(rs.getString("EMAIL"));
            dto.setLoginDate(rs.getDate("LOGIN_DATE"));
            dto.setName(rs.getString("NAME"));
            dto.setPassword(rs.getString("PASSWORD"));
            dto.setUserId(rs.getString("USER_ID"));


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return dto;
    }

    protected List<MemberDTO> executeQuery(String query, Object... params) {
        return executeQuery(MemberDTO.class, query, params);
    }
}
