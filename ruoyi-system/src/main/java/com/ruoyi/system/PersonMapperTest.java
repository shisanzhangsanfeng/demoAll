package com.ruoyi.system;


import com.ruoyi.system.domain.PersonRecord;
import com.ruoyi.system.mapper.PersonMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

import java.util.List;

import static com.ruoyi.system.PersonDynamicSqlSupport.*;

public class PersonMapperTest {

    private SqlSessionFactory sqlSessionFactory;


  /*  @Test
    void testSelect() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PersonMapper mapper = session.getMapper(PersonMapper.class);


            List<PersonRecord> rows = mapper.select(c ->
                    c.where(id, isEqualTo(1))
                            .or(occupation, isNull()));

            assertThat(rows).hasSize(3);
        }
    }*/
/*   @Test
    void testGeneralSelect() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PersonMapper mapper = session.getMapper(PersonMapper.class);

            SelectStatementProvider selectStatement = select(id.as("A_ID"), firstName, birthDate, employed,
                    occupation, addressId)
                    .from(person)
                    .where(id, isEqualTo(1))
                    .or(occupation, isNull())
                    .build()
                    .render(RenderingStrategies.MYBATIS3);

            List<PersonRecord> rows = mapper.selectMany(selectStatement);
            assertThat(rows).hasSize(3);
        }
    }*/

/*    @Test
    void testGeneralDelete() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PersonMapper mapper = session.getMapper(PersonMapper.class);

            DeleteStatementProvider deleteStatement = deleteFrom(person)
                    .where(occupation, isNull())
                    .build()
                    .render(RenderingStrategies.MYBATIS3);

            int rows = mapper.delete(deleteStatement);
            assertThat(rows).isEqualTo(2);
        }
    }*/

}
