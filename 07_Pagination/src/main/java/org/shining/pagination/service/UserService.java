package org.shining.pagination.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.shining.pagination.model.dto.PageDTO;

public interface UserService {

  Map<String, Object> getUsers(PageDTO dto, HttpServletRequest request);
}
