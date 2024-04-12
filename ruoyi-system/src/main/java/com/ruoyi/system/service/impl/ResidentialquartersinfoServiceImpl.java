package com.ruoyi.system.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ResidentialquartersinfoMapper;
import com.ruoyi.system.domain.Residentialquartersinfo;
import com.ruoyi.system.service.IResidentialquartersinfoService;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-21
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class ResidentialquartersinfoServiceImpl implements IResidentialquartersinfoService {
    @Autowired(required = false)
    private ResidentialquartersinfoMapper residentialquartersinfoMapper;
    @Autowired
    private DataSourceTransactionManager transactionManager;


    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Residentialquartersinfo selectResidentialquartersinfoById(Integer id) {
        return residentialquartersinfoMapper.selectResidentialquartersinfoById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param residentialquartersinfo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Residentialquartersinfo> selectResidentialquartersinfoList(Residentialquartersinfo residentialquartersinfo) {
        return residentialquartersinfoMapper.selectResidentialquartersinfoList(residentialquartersinfo);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param residentialquartersinfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertResidentialquartersinfo(Residentialquartersinfo residentialquartersinfo)  {
        int addFlag=residentialquartersinfoMapper.insertResidentialquartersinfo(residentialquartersinfo);

        //少一段 throw new SQLException("发生异常了..");

        return addFlag;
    }

    /**
     * @Autowired
     *     private PlatformTransactionManager transactionManager;
     *
     *     public void myTransactionalMethod() {
     *         // 定义事务属性
     *         DefaultTransactionDefinition def = new DefaultTransactionDefinition();
     *         def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
     *         def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
     *         def.setTimeout(30);
     *         def.setReadOnly(false);
     *
     *         // 开始事务
     *         TransactionStatus status = transactionManager.getTransaction(def);
     *
     *         try {
     *             // 在事务中执行数据库操作
     *             jdbcTemplate.update("INSERT INTO my_table (name, age) VALUES (?, ?)", "Alice", 25);
     *
     *             // 提交事务
     *             transactionManager.commit(status);
     *         } catch (Exception ex) {
     *             // 回滚事务
     *             transactionManager.rollback(status);
     *         }
     *     }
     * @param residentialquartersinfo
     * @return
     * @throws Exception
     */

    @Override
    public int insertResidentialquartersinfoNotRollBackFor(Residentialquartersinfo residentialquartersinfo)  {
        //int addFlag=residentialquartersinfoMapper.insertResidentialquartersinfo(residentialquartersinfo);

        int addFlag=-1;
        // 定义事务属性
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // explicitly setting the transaction name is something that can only be done programmatically
        def.setName("SomeTxName");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        // 开始事务
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            addFlag=residentialquartersinfoMapper.insertResidentialquartersinfo(residentialquartersinfo);
            transactionManager.commit(status);
            // execute your business logic here
            //db operation
        } catch (Exception ex) {
            transactionManager.rollback(status);
            throw ex;
        }
        return addFlag;
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param residentialquartersinfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateResidentialquartersinfo(Residentialquartersinfo residentialquartersinfo) {
        return residentialquartersinfoMapper.updateResidentialquartersinfo(residentialquartersinfo);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteResidentialquartersinfoByIds(Integer[] ids) {
        return residentialquartersinfoMapper.deleteResidentialquartersinfoByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteResidentialquartersinfoById(Integer id) {
        return residentialquartersinfoMapper.deleteResidentialquartersinfoById(id);
    }
}
