package com.example.dao;

import com.example.objects.Associate;

import java.util.List;

public interface  AssociateDao {
    void insertAssociate(Associate associate);
    void updateAssociate(Associate associate);
    int checkExistAssociateByCode(String stringContent);
    List<Associate> getAllAssociates();
    List<Associate> searchAllAssociatesHaveBirthdayMatchRequestedMonth(int month);
    void deleteAssociate(String code);
}
