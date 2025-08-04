package org.shining.crud.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.shining.crud.model.dto.BoardDTO;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository  // DAO 레벨에서 사용하는 @Component (Spring Container에 BoardDAO 타입의 빈 생성)
public class BoardDAO {

  private final SqlSessionTemplate template;
  
  public List<BoardDTO> getBoards() {
    return template.selectList("mybatis.mapper.boardMapper.getBoards");
  }
  
  public Integer getBoardCount() {
    return template.selectOne("mybatis.mapper.boardMapper.getBoardCount");
  }
  
  public BoardDTO getBoardById(Integer bid) {
   return template.selectOne("mybatis.mapper.boardMapper.getBoardById", bid); 
  }
  
  public int insertBoard(BoardDTO board) {
    return template.insert("mybatis.mapper.boardMapper.insertBoard", board);
  }
  
  public int updateBoard(BoardDTO board) {
    return template.update("mybatis.mapper.boardMapper.updateBoard", board);
  }
  
  public int deleteBoardById(Integer bid) {
    return template.delete("mybatis.mapper.boardMapper.deleteBoardById", bid);
  }
}
