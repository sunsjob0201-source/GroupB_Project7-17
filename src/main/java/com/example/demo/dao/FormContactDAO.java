package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.FormContact;

@Repository
public class FormContactDAO {

    private final List<FormContact> contactList =
            new ArrayList<>();

    public boolean insert(
            FormContact contact) {

        try {

            contactList.add(contact);
            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public List<FormContact> findAll() {

        return new ArrayList<>(
                contactList);
    }
}