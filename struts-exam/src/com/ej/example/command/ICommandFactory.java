package com.ej.example.command;

import com.ej.example.action.CustomActionForward;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public interface ICommandFactory {
    CustomActionForward getForwardInstance(String command, HttpServletRequest request) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedEncodingException;
}
