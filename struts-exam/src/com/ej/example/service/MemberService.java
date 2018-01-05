package com.ej.example.service;

import com.ej.example.dao.DAOSupport;
import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;

public class MemberService extends GenericService<MemberDTO, MemberDAO> implements IService<MemberDTO> {
    public MemberService(DAOSupport dao) {
        super(dao);
    }
}
