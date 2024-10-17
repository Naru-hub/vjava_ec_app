package com.example.vjava_ec.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vjava_ec.entity.Item;
import com.example.vjava_ec.repository.user.ItemMapper;

@Service
public class TestItemService {
    @Autowired
    private ItemMapper itemMapper;

    public List<Item> getAllItems() {
        return itemMapper.findAll();
    }

}
