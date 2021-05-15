package com.example.dao;

import com.example.commonlib.CommonLib;
import com.example.objects.Associate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AssociateDaoImpl extends JdbcDaoSupport implements AssociateDao {

    @Autowired
    DataSource dataSource;
    CommonLib commonLib = new CommonLib();

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void insertAssociate(Associate associate) {
        String sql = "INSERT INTO associates(code, fullName, address, dateOfBirth, resourceManager, metadata, role) VALUES ("
                + "'"
                + associate.getCode() + "','"
                + associate.getFullName() + "','"
                + associate.getAddress() + "','"
                + associate.getDateOfBirth() + "','"
                + associate.getResourceManager() + "','"
                + commonLib.convertMapToJson(associate.getMetadata()) + "','"
                + associate.getRole() + "'"
                + ")";

        getJdbcTemplate().update(sql);
    }

    @Override
    public void updateAssociate(Associate associate) {
            if (associate.getAddress() != null) {
                String sql = "UPDATE associates SET address = '" + associate.getAddress() + "', updated = NOW() WHERE code = '" + associate.getCode() + "'";
                getJdbcTemplate().update(sql);
            }
            if (associate.getDateOfBirth() != null) {
                String sql = "UPDATE associates SET dateofbirth = '" + associate.getDateOfBirth() + "', updated = NOW() WHERE code = '" + associate.getCode() + "'";
                getJdbcTemplate().update(sql);
            }
            if (associate.getResourceManager() != null) {
                String sql = "UPDATE associates SET resourcemanager = '" + associate.getResourceManager() + "', updated = NOW() WHERE code = '" + associate.getCode() + "'";
                getJdbcTemplate().update(sql);
            }
            if (associate.getRole() != null) {
                String sql = "UPDATE associates SET role = '" + associate.getRole() + "', updated = NOW() WHERE code = '" + associate.getCode() + "'";
                getJdbcTemplate().update(sql);
            }
            if (associate.getMetadata() != null) {
                String sql = "UPDATE associates SET metadata = '" + commonLib.convertMapToJson(associate.getMetadata()) + "', updated = NOW() WHERE code = '" + associate.getCode() + "'";
                getJdbcTemplate().update(sql);
            }
    }

    @Override
    public int checkExistAssociateByCode(String code) {
        String sql = "SELECT count(*) FROM associates where code = '" + code + "'";
        Integer numOfRow = getJdbcTemplate().queryForObject(sql, Integer.class);
        return numOfRow;
    }

    @Override
    public List<Associate> getAllAssociates() {
        String sql = "SELECT * FROM associates";
        List<Associate> associateList = getJdbcTemplate().query(sql, new AssociateRowMapper());
        return associateList;
    }

    @Override
    public List<Associate> searchAllAssociatesHaveBirthdayMatchRequestedMonth(int month) {
        String sql = "Select * from associates where EXTRACT(MONTH FROM dateofbirth) = " + month + "";
        List<Associate> associateList = getJdbcTemplate().query(sql, new AssociateRowMapper());
        return associateList;
    }

    @Override
    public void deleteAssociate(String code) {
        String sql = "DELETE FROM associates WHERE code = '" + code + "'";
        getJdbcTemplate().update(sql);
    }
}
