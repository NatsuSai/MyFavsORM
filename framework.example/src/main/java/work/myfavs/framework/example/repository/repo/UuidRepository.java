package work.myfavs.framework.example.repository.repo;

import work.myfavs.framework.example.repository.BaseRepository;
import work.myfavs.framework.example.domain.entity.Uuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import work.myfavs.framework.orm.DBTemplate;

/**
 * Uuid Repository
 */
@Repository
public class UuidRepository extends BaseRepository<Uuid> {

  /**
   * 构造方法
   *
   * @param dbTemplate DBTemplate
   */
  @Autowired
  public UuidRepository(DBTemplate dbTemplate) {

    super(dbTemplate);
  }
}