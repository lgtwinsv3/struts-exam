package com.ej.example.action;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public interface IAction {

    CustomActionForward action(HttpServletRequest request) throws SQLException, UnsupportedEncodingException;
}
