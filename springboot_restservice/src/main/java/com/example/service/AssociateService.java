package com.example.service;

import com.example.objects.Associate;

import java.util.List;

public interface AssociateService {
    boolean addAssociate(Associate associate);
    boolean updateAssociate(Associate associate);
    boolean updateAssociateWithCode(Associate associate, String code);
    boolean deleteAssociateWithCode(String code);
    List<Associate> filterAssociatesHaveBirthdayMappingSelectedMonth(int month);
    List<Associate> getAllAssociates();
}
