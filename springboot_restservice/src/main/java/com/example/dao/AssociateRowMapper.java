package com.example.dao;

import com.example.commonlib.CommonLib;
import com.example.objects.Associate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssociateRowMapper implements RowMapper {
    CommonLib commonLibrary = new CommonLib();

    //Map row method to query list result
    @Override
    public Associate mapRow(ResultSet resultSet, int i) throws SQLException {
        Associate anAssociate = new Associate();
        anAssociate.setCode(resultSet.getString("code"));
        anAssociate.setFullName(resultSet.getString("fullname"));
        anAssociate.setAddress(resultSet.getString("address"));
        anAssociate.setDateOfBirth(resultSet.getDate("dateofbirth"));
        anAssociate.setResourceManager(resultSet.getString("resourcemanager"));
        anAssociate.setRole(resultSet.getString("role"));
        anAssociate.setMetadata(commonLibrary.convertJsonToMap(resultSet.getString("metadata")));
        return anAssociate;
    }
}
