package org.shining.pagination.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.shining.pagination.model.dto.PageDTO;
import org.shining.pagination.model.dto.UserDTO;
import org.shining.pagination.repository.UserDAO;
import org.shining.pagination.util.PageUtil;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserDAO userDAO;
  private final PageUtil pageUtil;
  @Override
  public Map<String, Object> getUsers(PageDTO dto, HttpServletRequest request) {
    // 파라미터 sort 받기 (디폴트 DESC)
    // Optional<String> optSort = Optional.ofNullable(request.getParameter("sort"));
    // String sort = optSort.orElse("DESC");  // 잘못된 sort의 전달에 대한 대비책이 없다
    String sort = request.getParameter("sort");
    if(sort == null || !(sort.equalsIgnoreCase("ASC") || sort.equalsIgnoreCase("DESC")))
      sort = "DESC";
    // 전체 항목의 개수를 PageDTO 객체에 저장하기
    int itemCount = userDAO.getUserCount();
    dto.setItemCount(itemCount);
    // 페이징 정보 계산해서 PageDTO 객체에 저장하기 (PageDTO 객체에 페이징 위한 모든 정보가 저장 된다.)
    pageUtil.calculatePaging(dto);
    List<UserDTO> users = userDAO.getUsers(Map.of("offset", dto.getOffset(), "size", dto.getSize(), "sort", sort));
    // 페이징 UI(Html) 가져오기
    String pagingHtml = pageUtil.getPagingHtml(dto, request.getContextPath() + "/user/list", Map.of("size", dto.getSize(), "sort", sort));
    return Map.of("users", users, "pagingHtml", pagingHtml);
  }

}
