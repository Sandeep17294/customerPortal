package com.aetins.customerportal.core.pojo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author avinash
 *
 */
@Service
public class ActiveUserStore {

    public List<String> users;

    public ActiveUserStore() {
        users = new ArrayList<String>();
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
