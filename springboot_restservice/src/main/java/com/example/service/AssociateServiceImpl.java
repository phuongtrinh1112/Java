package com.example.service;

import com.example.dao.AssociateDao;
import com.example.service.AssociateService;
import com.example.objects.Associate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssociateServiceImpl implements AssociateService {
    public List<Associate> listAssociates = new ArrayList<>();

    @Autowired
    AssociateDao associateDao;

    @Override
    public boolean addAssociate(Associate associate) {
        if (associateDao.checkExistAssociateByCode(associate.getCode()) == 0){
            associateDao.insertAssociate(associate);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean updateAssociate(Associate associate) {
            if (associateDao.checkExistAssociateByCode(associate.getCode()) > 0){
                associateDao.updateAssociate(associate);
                return true;
            }
            else{
                return false;
            }
    }

    @Override
    public boolean updateAssociateWithCode(Associate associate, String code) {
        if (associateDao.checkExistAssociateByCode(code) > 0){
            associateDao.updateAssociate(associate);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean deleteAssociateWithCode(String code) {
        if (associateDao.checkExistAssociateByCode(code) > 0){
            associateDao.deleteAssociate(code);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public List<Associate> filterAssociatesHaveBirthdayMappingSelectedMonth(int month) {
        List<Associate> associateList = associateDao.searchAllAssociatesHaveBirthdayMatchRequestedMonth(month);
        return associateList;
    }

    @Override
    public List<Associate> getAllAssociates() {
        return associateDao.getAllAssociates();
    }
}
