package org.shining.hierarchy.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.shining.hierarchy.model.dto.BbsDTO;
import org.shining.hierarchy.model.dto.PageDTO;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BbsDAO {

  private final SqlSessionTemplate sqlSession;
  
  public Integer insertParentBbs(BbsDTO bbsDTO) {
    return sqlSession.insert("mybatis.mapper.bbsMapper.insertParentBbs", bbsDTO);
  }
  public Integer updateGroupId(BbsDTO bbsDTO) {
    return sqlSession.update("mybatis.mapper.bbsMapper.updateGroupId", bbsDTO);
  }
  public Integer updateGroupOrder(BbsDTO bbsDTO) {
    return sqlSession.update("mybatis.mapper.bbsMapper.updateGroupOrder", bbsDTO);
  }
  public Integer insertChildBbs(BbsDTO bbsDTO) {
    return sqlSession.insert("mybatis.mapper.bbsMapper.insertChildBbs", bbsDTO);
  }
  public Integer deleteBbsById(Integer bbsId) {
    return sqlSession.insert("mybatis.mapper.bbsMapper.deleteBbsById", bbsId);
  }
  public Integer getBbsCount() {
    return sqlSession.selectOne("mybatis.mapper.bbsMapper.getBbsCount");
  }
  public List<BbsDTO> getBbsList(PageDTO dto) {
    return sqlSession.selectList("mybatis.mapper.bbsMapper.getBbsList", dto);
  }
}
