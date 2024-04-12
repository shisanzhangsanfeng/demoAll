package com.ruoyi.web.controller.test;

import com.ruoyi.system.domain.PersonRecord;
import com.ruoyi.system.mapper.PersonMapper;
import org.apache.ibatis.session.*;
import org.junit.Test;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

import java.sql.Connection;
import java.util.List;

import static org.mybatis.dynamic.sql.delete.DeleteDSL.deleteFrom;
import static org.mybatis.dynamic.sql.select.SelectDSL.select;

public class testGeneralSelect {
    SqlSessionFactory sqlSessionFactory= new SqlSessionFactory() {
        @Override
        public SqlSession openSession() {
            return null;
        }

        @Override
        public SqlSession openSession(boolean autoCommit) {
            return null;
        }

        @Override
        public SqlSession openSession(Connection connection) {
            return null;
        }

        @Override
        public SqlSession openSession(TransactionIsolationLevel level) {
            return null;
        }

        @Override
        public SqlSession openSession(ExecutorType execType) {
            return null;
        }

        @Override
        public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
            return null;
        }

        @Override
        public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
            return null;
        }

        @Override
        public SqlSession openSession(ExecutorType execType, Connection connection) {
            return null;
        }

        @Override
        public Configuration getConfiguration() {
            return null;
        }
    };

/*  void testGeneralSelect() {
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
    }
   */

   /* @Test
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
